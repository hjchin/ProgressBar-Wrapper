package world.trav.uiloader;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

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
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

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

        onView(withId(R.id.retry_button)).perform(click());
        onView(withId(R.id.loading_spinner)).check(matches(isDisplayed()));
    }

    @Test
    public void testShowContent(){
        onView(withId(R.id.btnShowContent)).perform(click());
        onView(withId(R.id.content_container)).check(matches(isDisplayed()));
    }

    /*
        TODO:
            1. test simple data loading,
            2. test connection time out, load again and succeed
            3. test connection time out, load again and fail

     */


}
