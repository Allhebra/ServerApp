package com.bereg.serverapp.data.core.communication;

/**
 * Created by 1 on 08.03.2018.
 */

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MessageWriter {

    private final DataOutputStream out;

    public MessageWriter(OutputStream os) {
        this.out = new DataOutputStream(os);
    }

    public void writeMessage(String message) throws IOException {

        out.writeUTF(message);
        out.flush();
    }
}
