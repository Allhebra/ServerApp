package com.bereg.serverapp.data.server;

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 1 on 08.03.2018.
 */

public class Server/* implements Runnable */{

    private static final String TAG = Server.class.getSimpleName();
    public ServerSocket ss;
    public Socket clientSocket;
    private final int port;

    public Server() {
        this.port = 8080;
    }

    //@Override
    public void run() throws Exception {
        //try {
            ss = new ServerSocket(this.port);

            //Цикл ожидания подключений
            /*while(!this.context.stopFlag) {*/
            Log.e(TAG, "Waiting connection on port:" + this.port + ss + clientSocket);

                clientSocket = ss.accept();
                Log.e(TAG, "New client connected to server");

                ClientSession clientSession = new ClientSession(clientSocket);
                //Запуск логики работы с клиентом
                //clientSession.start();
            clientSession.run();
            /*}*/

            ss.close();

        /*} catch (IOException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }*/
    }
}
