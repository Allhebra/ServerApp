package com.bereg.serverapp;

import android.app.Application;
import android.util.Log;

import com.bereg.serverapp.di.AppComponent;
import com.bereg.serverapp.di.AppModule;
import com.bereg.serverapp.di.DaggerAppComponent;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 08.03.2018.
 */

public class App extends Application {

    private static final String TAG = App.class.getSimpleName();
    private static App instance;
    private Cicerone<Router> cicerone;
    public Router mRouter;
    public NavigatorHolder mNavigatorHolder;

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
        instance = this;
        appComponent = DaggerAppComponent.create();
                //.builder()
                //.appModule(new AppModule(getApplicationContext()))
                //.build();
        cicerone = Cicerone.create();
        Log.e(TAG, String.valueOf(appComponent) + String.valueOf(cicerone));
    }

    public NavigatorHolder getNavigatorHolder() {
        Log.e(TAG, "getNavigatorHolder");
        if (mNavigatorHolder == null) mNavigatorHolder = cicerone.getNavigatorHolder();
        return mNavigatorHolder;
    }

    public Router getRouter() {
        Log.e(TAG, "getRouter");
        if (mRouter == null) mRouter = cicerone.getRouter();
        return mRouter;
    }

    public static App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
