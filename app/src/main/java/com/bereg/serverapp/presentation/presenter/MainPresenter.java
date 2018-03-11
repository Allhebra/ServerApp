package com.bereg.serverapp.presentation.presenter;

import android.content.Context;
import android.util.Log;

import com.bereg.serverapp.Utils.Screens;
import com.bereg.serverapp.data.server.Server;
import com.bereg.serverapp.domain.ConnectionInteractor;
import com.bereg.serverapp.ui.activity.MainActivity;

import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 08.03.2018.
 */

public class MainPresenter implements ConnectionInteractor.IpListener{

    private static final String TAG = MainPresenter.class.getSimpleName();
    private ConnectionInteractor mConnectionInteractor;
    private Router mRouter;
    //private MainActivity mMainActivity;

    public MainPresenter(ConnectionInteractor connectionInteractor, Router router) {
        Log.e(TAG, "MainPresenter");
        mConnectionInteractor = connectionInteractor;
        mConnectionInteractor.setListener(this);
        mRouter = router;
    }

    public void onStartClicked() {

        try {
            //mConnectionInteractor.startServer();
            mRouter.navigateTo(Screens.INFO_SCREEN);
        }catch (Exception e) {
            Log.e(TAG, "onStartClicked   catch");
            Log.e(TAG, e.toString());
            //Log.e(TAG, e.getMessage());
        }
    }

    /*public void onAttachView(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }*/

    @Override
    public void onIpChanged(String ip) {

        Log.e(TAG, "MainPresenter   onIpChanged" + ip);
        //mMainActivity.setText(ip);
    }

    @Override
    public void onServerStarted(boolean isStarted) {
        Log.e(TAG, "MainPresenter   onServerStarted" + isStarted);
    }
}
