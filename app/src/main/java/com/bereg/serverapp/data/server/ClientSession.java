package com.bereg.serverapp.data.server;

import java.io.IOException;
import java.net.Socket;

import com.bereg.serverapp.data.core.Message;
import com.bereg.serverapp.data.core.communication.MessageReader;
import com.bereg.serverapp.data.core.communication.MessageWriter;

/**
 * Created by 1 on 08.03.2018.
 */

//Основная логика клиента
public class ClientSession/* extends Thread */{
    private final Socket socket;
    private final MessageReader reader;
    private final MessageWriter writer;

    public ClientSession(final Socket socket/*, final Context context*/) throws IOException {
        this.socket = socket;
        this.reader = new MessageReader(socket.getInputStream());
        this.writer = new MessageWriter(socket.getOutputStream());
    }

    public void run() {
        String msg;
        try {
            msg = reader.readMessage();
            if(msg.equals(Message.HELLO)) {
                writer.writeMessage(Message.HELLO);
                msg = reader.readMessage();
                if(msg.equals(Message.READY_REQUEST)) {
                    writer.writeMessage(Message.READY_RESPONSE);
                    msg = reader.readMessage();
                    if (msg.equals(Message.GET_INFORMATION)) {
                        writer.writeMessage(Message.TEMPLATE);
                    }
                }
            }

            this.doWork();

            this.socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWork() {}
}
