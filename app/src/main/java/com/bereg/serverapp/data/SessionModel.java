package com.bereg.serverapp.data;

import com.bereg.serverapp.Utils.ServerStatus;

import java.util.List;

/**
 * Created by 1 on 06.05.2018.
 */

public class SessionModel {

    private List<String> messagesLog;
    private ServerStatus serverStatus;

    public List<String> getMessagesLog() {
        return messagesLog;
    }

    public void setMessagesLog(List<String> messagesLog) {
        this.messagesLog = messagesLog;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerStatus serverStatus) {
        this.serverStatus = serverStatus;
    }
}
