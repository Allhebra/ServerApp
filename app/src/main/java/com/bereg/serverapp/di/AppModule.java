package com.bereg.serverapp.di;

import com.bereg.serverapp.App;
import com.bereg.serverapp.data.server.Server;
import com.bereg.serverapp.domain.ConnectionInteractor;
import com.bereg.serverapp.presentation.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 08.03.2018.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    MainPresenter provideMainPresenter(ConnectionInteractor connectionInteractor, Router router) {
        return new MainPresenter(connectionInteractor, router);
    }

    @Provides
    @Singleton
    ConnectionInteractor provideConnectionInteractor(Server server) {
        return new ConnectionInteractor(server);
    }

    @Provides
    @Singleton
    Router provideRouter() {
        return App.getInstance().getRouter();
    }

    @Provides
    @Singleton
    Server provideServer() {
        return new Server();
    }
}
