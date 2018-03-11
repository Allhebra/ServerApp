package com.bereg.serverapp.di;

import com.bereg.serverapp.domain.ConnectionInteractor;
import com.bereg.serverapp.presentation.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 1 on 08.03.2018.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    MainPresenter getMainPresenter();
    ConnectionInteractor getConnectionInteractor();
}
