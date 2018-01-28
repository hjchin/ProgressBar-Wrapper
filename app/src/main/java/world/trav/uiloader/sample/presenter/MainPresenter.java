package world.trav.uiloader.sample.presenter;

import world.trav.uiloader.sample.model.Item;
import world.trav.uiloader.sample.view.MainView;

/**
 * Created by HJ Chin on 28/1/2018.
 */

public class MainPresenter {

    private MainView view;

    public MainPresenter(MainView view){
        this.view = view;
        load();
    }

    public void load(){
        view.showContent(Item.list);
        //view.showError();
    }
}
