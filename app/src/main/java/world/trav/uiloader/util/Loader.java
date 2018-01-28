package world.trav.uiloader.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import world.trav.uiloader.R;

/**
 * Created by HJ Chin on 28/1/2018.
 */

public class Loader {

    private View loaderView;
    private View contentView;
    private ProgressBar progressBar;
    private ViewGroup errorContainer;
    private View retryButton;
    private TextView errorTextView;
    private final int animeDuration;

    public interface Callback{
        void onRetryClick();
    }

    public Loader(View loaderView, View contentView){
        this.loaderView = loaderView;
        this.contentView = contentView;

        progressBar = loaderView.findViewById(R.id.loading_spinner);
        errorContainer = loaderView.findViewById(R.id.loading_error_container);
        retryButton = loaderView.findViewById(R.id.retry_button);
        errorTextView = loaderView.findViewById(R.id.error_message);

        progressBar.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);

        animeDuration = loaderView.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime);

        showLoader();
    }

    public void showLoader(){
        showLoaderView();

        progressBar.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);
        contentView.setVisibility(View.GONE);
    }

    private void showLoaderView() {
        loaderView.setVisibility(View.VISIBLE);
        loaderView.setAlpha(1f);
    }

    public void showError(String errorMessage, Callback callback){
        showLoaderView();

        progressBar.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
        errorTextView.setText(errorMessage);
        retryButton.setOnClickListener((View v)->{
            showLoader();
            callback.onRetryClick();
        });
        contentView.setVisibility(View.GONE);
    }

    public void showContent(){

        contentView.setVisibility(View.INVISIBLE);
        contentView.setAlpha(0f);
        contentView.setVisibility(View.VISIBLE);

        loaderView.animate().alpha(0f).setDuration(animeDuration);

        contentView.animate().alpha(1f).setDuration(animeDuration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                loaderView.setVisibility(View.GONE);
            }
        });
    }


}
