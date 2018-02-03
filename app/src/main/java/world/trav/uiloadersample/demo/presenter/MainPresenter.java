package world.trav.uiloadersample.demo.presenter;

import android.support.test.espresso.idling.CountingIdlingResource;

import world.trav.uiloadersample.demo.view.MainView;
import world.trav.uiloadersample.util.AppExecutor;

/**
 * Created by HJ Chin on 28/1/2018.
 */

public class MainPresenter {

    private MainView view;
    private CountingIdlingResource countingIdlingResource;

    public MainPresenter(MainView view, CountingIdlingResource countingIdlingResource){
        this.view = view;
        this.countingIdlingResource = countingIdlingResource;
    }

    public void load(){
        load(false);
    }

    private void load(boolean error){

        countingIdlingResource.increment();
        AppExecutor.getInstance().getDiskIOExecutor().execute(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(error){
                AppExecutor.getInstance().getMainThreadExecutor().execute(()-> view.showError());
            }else{
                AppExecutor.getInstance().getMainThreadExecutor().execute(()-> view.showContent());
            }
            countingIdlingResource.decrement();
        });
    }

    public void loadWithTimeOut(){
        load(true);
    }
}
