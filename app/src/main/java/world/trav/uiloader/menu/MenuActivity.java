package world.trav.uiloader.menu;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import world.trav.uiloader.R;
import world.trav.uiloader.databinding.ActivityMenuBinding;
import world.trav.uiloader.sample.view.MainActivity;

public class MenuActivity extends AppCompatActivity {

    public static final int MENU_SHOW_CONTENT = 1;
    public static final int MENU_SHOW_LOADER = 2;
    public static final int MENU_SHOW_ERROR = 3 ;
    public static final int MENU_LOAD_DATA = 4;
    public static final int MENU_LOAD_DATA_ERROR1 = 5;
    public static final int MENU_LOAD_DATA_ERROR2 = 6;
    public static final String MENU = "menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMenuBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_menu);
        binding.btnShowContent.setOnClickListener((View v)->startAct(MENU_SHOW_CONTENT));
        binding.btnShowLoader.setOnClickListener((View v)->startAct(MENU_SHOW_LOADER));
        binding.btnShowError.setOnClickListener((View v)->startAct(MENU_SHOW_ERROR));
        binding.btnLoadData.setOnClickListener((View v)->startAct(MENU_LOAD_DATA));
        binding.btnLoadError1.setOnClickListener((View v)->startAct(MENU_LOAD_DATA_ERROR1));
        binding.btnLoadError2.setOnClickListener((View v)->startAct(MENU_LOAD_DATA_ERROR2));
    }

    private void startAct(int selectedMenuId){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(MENU,selectedMenuId);
        startActivity(i);
    }
}
