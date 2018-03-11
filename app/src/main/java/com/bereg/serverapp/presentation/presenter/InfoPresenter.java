package com.bereg.serverapp.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bereg.serverapp.Utils.NetworkUtils;
import com.bereg.serverapp.domain.ConnectionInteractor;
import com.bereg.serverapp.presentation.view.InfoView;

import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 08.03.2018.
 */

@InjectViewState
public class InfoPresenter extends MvpPresenter<InfoView> implements ConnectionInteractor.IpListener {

    private static final String TAG = InfoPresenter.class.getSimpleName();

    private ConnectionInteractor mConnectionInteractor;
    private Router mRouter;

    public InfoPresenter(ConnectionInteractor connectionInteractor, Router router) {

        mConnectionInteractor = connectionInteractor;
        mRouter = router;
        mConnectionInteractor.setListener(this);
    }

    @Override
    public void onIpChanged(String ip) {
        Log.e(TAG, "InfoPresenter   onIpChanged" + ip);
    }

    @Override
    public void onServerStarted(boolean isStarted) {
        Log.e(TAG, "InfoPresenter   onServerStarted" + isStarted);
        getViewState().showConnectionInfo("Сервер запущен" + "\nIP: " + NetworkUtils.getIPAddress(true) + "\nPort: 8080");
    }
}
