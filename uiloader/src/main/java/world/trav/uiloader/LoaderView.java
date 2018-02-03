package world.trav.uiloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import world.trav.uiloader.R;
/**
 * Created by HJ Chin on 3/2/2018.
 */

public class LoaderView extends FrameLayout {

    public LoaderView(@NonNull Context context) {
        super(context);
    }

    public LoaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.loader_view,this,true);
    }

    public void setLoader(Loader loader){

        if(this.getChildCount() != 2){
            throw new RuntimeException("Loader View can appends only one direct child");
        }

        loader.setViews(this.findViewById(R.id.loading_container),this.getChildAt(1));
        loader.showLoader();
    }
}
