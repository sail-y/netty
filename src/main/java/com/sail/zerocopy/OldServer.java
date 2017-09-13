package com.sail.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yangfan
 * @date 2017/09/12
 */
public class OldServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8899);

        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try {
                byte[] byteArry = new byte[4096];

                while (true) {
                    int readCount = dataInputStream.read(byteArry, 0, byteArry.length);

                    if (-1 == readCount) {
                        break;
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
