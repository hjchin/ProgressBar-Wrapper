package world.trav.uiloader;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import world.trav.uiloader.menu.MenuActivity;
import world.trav.uiloader.sample.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by HJ Chin on 28/1/2018.
 */

public class LoaderTest {

    @Rule
    public ActivityTestRule<MenuActivity> activityTestRule = new ActivityTestRule<>(MenuActivity.class);

    @Before
    public void setup(){
        IdlingRegistry.getInstance().register(MainActivity.getCountingIdlingResource());
    }

    @Test
    public void testShowLoader(){
        onView(withId(R.id.btnShowLoader)).perform(click());
        onView(withId(R.id.loading_spinner)).check(matches(isDisplayed()));
    }

    @Test
    public void testShowError(){
        onView(withId(R.id.btnShowError)).perform(click());
        onView(withId(R.id.loading_error_container)).check(matches(isDisplayed()));
        onView(withId(R.id.error_message)).check(matches(withText("error message")));
    }

    @Test
    public void testShowContent(){
        onView(withId(R.id.btnShowContent)).perform(click());
        onView(withId(R.id.content_container)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoadData(){
        onView(withId(R.id.btnLoadData)).perform(click());
        onView(withId(R.id.content_container)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoadDataError1(){
        onView(withId(R.id.btnLoadError1)).perform(click());
        onView(withId(R.id.loading_error_container)).check(matches(isDisplayed()));
        onView(withId(R.id.error_message)).check(matches(withText("error message")));
        onView(withId(R.id.retry_button)).perform(click());
        onView(withId(R.id.content_container)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoadDataError2(){
        onView(withId(R.id.btnLoadError2)).perform(click());
        onView(withId(R.id.loading_error_container)).check(matches(isDisplayed()));
        onView(withId(R.id.error_message)).check(matches(withText("error message")));
        onView(withId(R.id.retry_button)).perform(click());
        onView(withId(R.id.loading_error_container)).check(matches(isDisplayed()));
        onView(withId(R.id.error_message)).check(matches(withText("error message")));
    }

    @After
    public void finish(){
        IdlingRegistry.getInstance().unregister(MainActivity.getCountingIdlingResource());
    }

    /*
        TODO:
            2. test connection time out, load again and succeed
            3. test connection time out, load again and fail

     */


}
