package net.zicron.litw.io;

import java.io.*;
import java.net.Socket;

public class NetConnection {
    private String server;
    private int port;

    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public NetConnection(String server, int port){
        this.server = server;
        this.port = port;
    }

    public void connect() throws IOException {
        socket = new Socket(server, port);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    public void sendCommand(String command) throws IOException {
        output.writeUTF(command);
        output.flush();
    }

    public Object getObject() throws IOException{
        return input.read();
    }

    public int getInt() throws IOException{
        return input.readInt();
    }

    public String getString() throws IOException{
        return input.readUTF();
    }

    public void sendInt(int val) throws IOException {
        output.writeInt(val);
        output.flush();
    }

    public byte[] getByteArray() throws IOException{
        int length = getInt();
        byte[] data = null;
        if(length > 0){
            data = new byte[length];
            input.read(data, 0, length);
        }

        return data;
    }
}
