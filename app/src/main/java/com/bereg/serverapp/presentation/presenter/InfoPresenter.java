package com.bereg.serverapp.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.bereg.serverapp.Utils.NetworkUtils;
import com.bereg.serverapp.Utils.ServerStatus;
import com.bereg.serverapp.domain.ConnectionInteractor;
import com.bereg.serverapp.models.MessageModel;
import com.bereg.serverapp.presentation.view.InfoView;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 08.03.2018.
 */

@InjectViewState
public class InfoPresenter extends MvpPresenter<InfoView> /*implements ConnectionInteractor.StatusListener */{

    private static final String TAG = InfoPresenter.class.getSimpleName();

    private ConnectionInteractor mConnectionInteractor;
    private Router mRouter;
    public Disposable disposable;

    public InfoPresenter(ConnectionInteractor connectionInteractor, Router router) {

        mConnectionInteractor = connectionInteractor;
        mRouter = router;
        Log.e(TAG, "Constructor" + connectionInteractor + router);
    }

    @Override
    protected void onFirstViewAttach() {

        mConnectionInteractor.getServerStatusBuffer()
                .subscribe(new Consumer<ServerStatus>() {
                    @Override
                    public void accept(ServerStatus aBoolean) throws Exception {
                        if (aBoolean == ServerStatus.SERVER_STARTED) {
                            getViewState().showConnectionInfo("Сервер запущен" + "\nIP: " + NetworkUtils.getIPAddress(true) + "\nPort: 8080");
                        }
                        if (aBoolean == ServerStatus.SESSION_STARTED) {
                            onSessionStarted();
                        }
                    }
                });
    }

    private void onSessionStarted() {

        Log.e(TAG, "onSessionStarted:  ");
        mConnectionInteractor.getMessagesObservable()
                .subscribe(new Consumer<MessageModel>() {
                    @Override
                    public void accept(MessageModel messages) throws Exception {
                        Log.e(TAG, "onAcceptConsumer" + messages);
                        getViewState().addMessageToLog(messages);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mRouter.showSystemMessage(throwable.toString());
                        Log.e(TAG, "onErrorConsumer" + throwable.toString());
                    }
                });
    }
}
