package world.trav.uiloader.sample.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import world.trav.uiloader.R;
import world.trav.uiloader.databinding.ActivityMainBinding;
import world.trav.uiloader.sample.model.Item;
import world.trav.uiloader.sample.presenter.MainPresenter;
import world.trav.uiloader.util.Loader;

public class MainActivity extends AppCompatActivity implements MainView{

    private MainPresenter presenter;
    private ActivityMainBinding dataBinding;
    private Loader loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dataBinding.btnShowContent.setOnClickListener((View v)->loader.showContent());
        dataBinding.btnShowLoader.setOnClickListener((View v)-> loader.showLoader());
        dataBinding.btnShowError.setOnClickListener((View v)->{

            loader.showError("error message", new Loader.Callback() {
                @Override
                public void onRetryClick() {
                    presenter.load();
                }
            });
        });
        dataBinding.btnClear.setOnClickListener((View v)->loader.showLoader());

        loader = new Loader(dataBinding.loadingContainer, dataBinding.contentContainer);
        presenter = new MainPresenter(this);


    }

    @Override
    public void showContent(ArrayList<Item> list) {

    }
}
