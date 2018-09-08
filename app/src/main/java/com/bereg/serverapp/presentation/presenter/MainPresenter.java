package com.bereg.serverapp.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.serverapp.Utils.Screens;
import com.bereg.serverapp.Utils.ServerStatus;
import com.bereg.serverapp.data.server.Server;
import com.bereg.serverapp.domain.ConnectionInteractor;
import com.bereg.serverapp.presentation.view.MainView;

import io.reactivex.CompletableObserver;
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
public class MainPresenter extends MvpPresenter<MainView> /*implements ConnectionInteractor.StatusListener*/{

    private static final String TAG = MainPresenter.class.getSimpleName();
    private ConnectionInteractor mConnectionInteractor;
    private Router mRouter;
    public Disposable disposable;

    public MainPresenter(ConnectionInteractor connectionInteractor, Router router) {
        mConnectionInteractor = connectionInteractor;
        //mConnectionInteractor.setListener(this);
        mRouter = router;
        Log.e(TAG, "Constructor" + connectionInteractor + router);
    }

    public void onStartClicked() {

        try {
            mRouter.navigateTo(Screens.INFO_SCREEN);
            mConnectionInteractor.getServerStatusBuffer()
                    .subscribe(new Consumer<ServerStatus>() {
                        @Override
                        public void accept(ServerStatus aBoolean) throws Exception {
                            Log.e(TAG, "onStartClicked:   accept   hide view state" + aBoolean);
                            if (aBoolean==ServerStatus.SERVER_STARTED) {
                                getViewState().hideWidgets();
                            }
                        }
                    });
        }catch (Exception e) {
            Log.e(TAG, "onStartClicked   catch");
            Log.e(TAG, e.toString());
            //Log.e(TAG, e.getMessage());
        }
    }
}
