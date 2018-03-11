package com.bereg.serverapp.domain;

import android.util.Log;

import com.bereg.serverapp.Utils.NetworkUtils;
import com.bereg.serverapp.data.server.Server;
import com.bereg.serverapp.presentation.presenter.InfoPresenter;
import com.bereg.serverapp.ui.activity.MainActivity;

/**
 * Created by 1 on 09.03.2018.
 */

public class ConnectionInteractor {

    private static final String TAG = ConnectionInteractor.class.getSimpleName();

    public Server mServer;
    public String ip = "default";
    public IpListener ipListener;

    public ConnectionInteractor(Server server) {

        mServer = server;
    }

    public void startServer() {

        Log.e(TAG, "startServer:  " + mServer + this);
        //ipListener.onIpChanged(NetworkUtils.getIPAddress(true));

        //new Thread(mServer).start();
        try {
            ipListener.onServerStarted(true);
            mServer.run();
        }catch (Exception e) {
            ipListener.onServerStarted(false);
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
        ipListener.onIpChanged(ip);
    }

    public void setListener(IpListener listener) {
        Log.e(TAG, "setListener");
        ipListener = listener;
        if (ipListener instanceof InfoPresenter) {
            Log.e(TAG, "instanceof");
            //ip = mServer.ss.getInetAddress().toString();
            //ipListener.onIpChanged(ip);
        }
    }

    public interface IpListener {
        void onIpChanged(String ip);
        void onServerStarted(boolean isStarted);
    }
}
