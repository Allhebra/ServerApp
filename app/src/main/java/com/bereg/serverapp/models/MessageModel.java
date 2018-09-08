package com.bereg.serverapp.models;

import org.joda.time.DateTime;

/**
 * Created by 1 on 11.06.2018.
 */

public class MessageModel {

    private String sender;
    private String message;
    private DateTime timestamp;

    public MessageModel(String sender, String message) {
        this.sender = sender;
        this.message = message;
        this.timestamp = DateTime.now();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public String getTimestampAsString() {
        return timestamp.toString();
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageModel that = (MessageModel) o;

        if (!getSender().equals(that.getSender())) return false;
        if (!getMessage().equals(that.getMessage())) return false;
        return getTimestamp().equals(that.getTimestamp());
    }

    @Override
    public int hashCode() {
        int result = getSender().hashCode();
        result = 31 * result + getMessage().hashCode();
        result = 31 * result + getTimestamp().hashCode();
        return result;
    }
}
