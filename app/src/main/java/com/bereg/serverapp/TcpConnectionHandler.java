package com.bereg.serverapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 1 on 08.03.2018.
 */

public class TcpConnectionHandler {

    private static final String TAG = TcpConnectionHandler.class.getSimpleName();
    private static final int PORT = 1883;

    private static InputStream socketInputStream;
    private static OutputStream socketOutputStream;
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void connect() throws Exception{

        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket = serverSocket.accept();

        socketInputStream = socket.getInputStream();
        socketOutputStream = socket.getOutputStream();

        in = new DataInputStream(socketInputStream);
        out = new DataOutputStream(socketOutputStream);
    }

    public static void send() throws Exception{

        String line = null;
        while(true) {
            line = in.readUTF();
            out.writeUTF(line);
            out.flush();
        }
    }
}
