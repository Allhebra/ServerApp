package com.bereg.serverapp.data.core.communication;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 1 on 08.03.2018.
 */

public class MessageReader {

    private final DataInputStream dis;

    public MessageReader(InputStream is) {
        this.dis = new DataInputStream(is);
    }

    public String readMessage() throws IOException {

        return dis.readUTF();
    }
}
