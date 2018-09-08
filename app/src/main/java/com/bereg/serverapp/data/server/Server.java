package com.bereg.serverapp.data.server;

import android.util.Log;

import com.bereg.serverapp.Utils.ServerStatus;
import com.bereg.serverapp.data.core.Message;
import com.bereg.serverapp.data.core.SenderType;
import com.bereg.serverapp.data.core.communication.MessageReader;
import com.bereg.serverapp.data.core.communication.MessageWriter;
import com.bereg.serverapp.models.MessageModel;

import org.joda.time.DateTime;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by 1 on 08.03.2018.
 */

public class Server {

    private static final String TAG = Server.class.getSimpleName();
    private static final int PORT = 8080;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private MessageModel message;
    private Observable<MessageModel> messagesObservable;
    private BehaviorSubject<ServerStatus> serverStatusBuffer = BehaviorSubject.create();

    public Server() {

        try {
            serverSocket = new ServerSocket(Server.PORT);
            //serverSocket.setSoTimeout(10000);
        }catch (Exception e) {
            Log.e(TAG, "serverSocket cannot be created");
        }
    }

    public BehaviorSubject<ServerStatus> getBehaviorSubject() {

        return serverStatusBuffer;
    }

    public Observable<MessageModel> getMessagesObservable() {
        return messagesObservable;
    }

    public void run() {

        try {
            //Цикл ожидания подключений
            //while (!sessionRunningStatus) {
            Log.e(TAG, "Waiting connection on port:" + Server.PORT + serverSocket + clientSocket);

            serverStatusBuffer.onNext(ServerStatus.SERVER_STARTED);
            Log.e(TAG, "serverStatusBuffer.onNext(true)");
            clientSocket = serverSocket.accept();
            Log.e(TAG, "New client connected to server");

            ClientSessionInner clientSession = new ClientSessionInner();
            //Запуск логики работы с клиентом
            clientSession.startSession();
            Log.e(TAG, "New session started");
            //}

            serverSocket.close();
        } catch (Exception e) {
            Log.e(TAG, "catch" + e.toString());
            e.printStackTrace();
        }
    }

    class ClientSessionInner {

        final MessageReader reader;
        final MessageWriter writer;
        private boolean sessionEndedStatus = false;
        private String msg;
        private boolean newMessageReady =false;

        ClientSessionInner() throws Exception{
            this.reader = new MessageReader(clientSocket.getInputStream());
            this.writer = new MessageWriter(clientSocket.getOutputStream());
        }

        void startSession() {

            messagesObservable = Observable.create(new ObservableOnSubscribe<MessageModel>() {

                @Override
                public void subscribe(ObservableEmitter<MessageModel> e) throws Exception {
                    while (!sessionEndedStatus) {
                        if (newMessageReady) {
                            e.onNext(message);
                            newMessageReady = false;
                            Log.e(TAG, "observable.create:   " + message);
                        }
                    }
                    e.onComplete();
                }
            });

            serverStatusBuffer.onNext(ServerStatus.SESSION_STARTED);
            Log.e(TAG, "startSession   " + "in");
            try {
                msg = "";
                while (!msg.equals(Message.HELLO)) {
                    msg = reader.readMessage();
                    Log.e(TAG, "while:   " + msg);
                }
                message = new MessageModel(SenderType.CLIENT, msg);
                waitTillMessageEmitted();
                getInfo();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void getInfo() {
            try {
                writer.writeMessage(Message.HELLO);
                message = new MessageModel(SenderType.SERVER, Message.HELLO);
                Log.e(TAG, "writer:HELLO");
                waitTillMessageEmitted();

                msg = reader.readMessage();
                message = new MessageModel(SenderType.CLIENT, msg);
                Log.e(TAG, "reader:   " + message + msg);
                waitTillMessageEmitted();

                if (msg.equals(Message.READY_REQUEST)) {
                    writer.writeMessage(Message.READY_RESPONSE);
                    message = new MessageModel(SenderType.SERVER, Message.READY_RESPONSE);
                    Log.e(TAG, "writer:READY_RESPONSE");
                    waitTillMessageEmitted();

                    msg = reader.readMessage();
                    message = new MessageModel(SenderType.CLIENT, msg);
                    Log.e(TAG, "reader:   " + message + msg);
                    waitTillMessageEmitted();

                    if (msg.equals(Message.GET_INFORMATION)) {
                        writer.writeMessage(Message.TEMPLATE);
                        message = new MessageModel(SenderType.SERVER, Message.TEMPLATE);
                        Log.e(TAG, "writer:TEMPLATE");
                        waitTillMessageEmitted();
                    }
                }
                sessionEndedStatus = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void waitTillMessageEmitted() {

            newMessageReady = true;
            while (newMessageReady) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                    Log.e(TAG, "waitTillMessageEmitted");
                } catch (Exception e) {
                    Log.e(TAG, "waitTillMessageEmitted:   " + e.toString());
                }
            }
        }
    }
}
