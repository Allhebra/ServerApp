package com.bereg.serverapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.bereg.serverapp.models.MessageModel;

import java.util.List;

/**
 * Created by 1 on 08.03.2018.
 */

public interface InfoView extends MvpView {

    void showConnectionInfo(String ip);

    void addMessageToLog(MessageModel messages);
}
