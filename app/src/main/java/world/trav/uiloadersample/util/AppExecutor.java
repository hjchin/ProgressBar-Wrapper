package world.trav.uiloadersample.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by HJ Chin on 3/2/2018.
 */

public class AppExecutor{

    private static AppExecutor appExecutor;
    private DiskIOExecutor diskIOExecutor;
    private MainThreadExecutor mainThreadExecutor;

    private AppExecutor(){
        diskIOExecutor = new DiskIOExecutor();
        mainThreadExecutor = new MainThreadExecutor();
    }

    public static AppExecutor getInstance(){
        if(appExecutor == null){
            appExecutor = new AppExecutor();
        }
        return appExecutor;
    }

    public DiskIOExecutor getDiskIOExecutor(){
        return diskIOExecutor;
    }

    public MainThreadExecutor getMainThreadExecutor(){
        return mainThreadExecutor;
    }

    public static class DiskIOExecutor implements Executor{

        private final Executor executor;

        DiskIOExecutor(){
            executor = Executors.newSingleThreadExecutor();
        }

        @Override
        public void execute(@NonNull Runnable runnable) {
            executor.execute(runnable);
        }
    }

    public static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
