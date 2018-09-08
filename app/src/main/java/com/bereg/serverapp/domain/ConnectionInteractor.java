package com.bereg.serverapp.domain;

import android.util.Log;

import com.bereg.serverapp.Utils.ServerStatus;
import com.bereg.serverapp.data.server.Server;
import com.bereg.serverapp.models.MessageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by 1 on 09.03.2018.
 */

public class ConnectionInteractor {

    private static final String TAG = ConnectionInteractor.class.getSimpleName();

    private Server mServer;
    //public List<String> messages;

    public ConnectionInteractor() {

        mServer = new Server();
        //messages = new ArrayList<>();
    }

    public void startServer() {

        Log.e(TAG, "startServer:  " + mServer + this);

        try {
            mServer.run();
        } catch (Exception e) {
            Log.e(TAG, "catch");
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
    }

    public Observable<ServerStatus> getServerStatusBuffer() {

        Log.e(TAG, "getServerStatusBuffer:   ");
        return mServer.getBehaviorSubject()
                .hide()
                .subscribeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MessageModel> getMessagesObservable() {

        return mServer.getMessagesObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
