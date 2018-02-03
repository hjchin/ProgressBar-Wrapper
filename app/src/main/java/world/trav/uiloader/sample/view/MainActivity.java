package world.trav.uiloader.sample.view;

import android.databinding.DataBindingUtil;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import world.trav.uiloader.LoaderView;
import world.trav.uiloader.R;
import world.trav.uiloader.databinding.ActivityMainBinding;
import world.trav.uiloader.menu.MenuActivity;
import world.trav.uiloader.sample.presenter.MainPresenter;
import world.trav.uiloader.Loader;

import static world.trav.uiloader.menu.MenuActivity.MENU_LOAD_DATA;
import static world.trav.uiloader.menu.MenuActivity.MENU_LOAD_DATA_ERROR1;
import static world.trav.uiloader.menu.MenuActivity.MENU_LOAD_DATA_ERROR2;
import static world.trav.uiloader.menu.MenuActivity.MENU_SHOW_CONTENT;
import static world.trav.uiloader.menu.MenuActivity.MENU_SHOW_ERROR;
import static world.trav.uiloader.menu.MenuActivity.MENU_SHOW_LOADER;

public class MainActivity extends AppCompatActivity implements MainView{

    private static final int LOAD_N_SUCCEED = 1;
    private static final int LOAD_N_FAIL = 2;

    private MainPresenter presenter;
    private Loader loader;
    private static CountingIdlingResource countingIdlingResource = new CountingIdlingResource("counter1");
    private int actionAfterClickRetry = LOAD_N_SUCCEED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        loader = new Loader();

        ((LoaderView)dataBinding.container).setLoader(loader);
        presenter = new MainPresenter(this, countingIdlingResource);

        int menu = getIntent().getIntExtra(MenuActivity.MENU,0);

        switch (menu){
            case MENU_SHOW_CONTENT:
                loader.showContent();
                break;
            case MENU_SHOW_LOADER:
                loader.showLoader();
                break;
            case MENU_SHOW_ERROR:
                loader.showError("error message", new Loader.Callback() {
                    @Override
                    public void onRetryClick() {
                        presenter.load();
                    }
                });
                break;
            case MENU_LOAD_DATA:
                loader.showLoader();
                presenter.load();
                break;
            case MENU_LOAD_DATA_ERROR1:
                actionAfterClickRetry = LOAD_N_SUCCEED;
                loader.showLoader();
                presenter.loadWithTimeOut();
                break;
            case MENU_LOAD_DATA_ERROR2:
                actionAfterClickRetry = LOAD_N_FAIL;
                loader.showLoader();
                presenter.loadWithTimeOut();
                break;
        }
    }

    @Override
    public void showContent() {
        loader.showContent();
    }

    @Override
    public void showError() {
        loader.showError("error message", new Loader.Callback() {
            @Override
            public void onRetryClick() {
                if(actionAfterClickRetry == LOAD_N_SUCCEED){
                    presenter.load();
                }else{
                    presenter.loadWithTimeOut();
                }
            }
        });
    }

    public static CountingIdlingResource getCountingIdlingResource(){
        return countingIdlingResource;
    }
}
