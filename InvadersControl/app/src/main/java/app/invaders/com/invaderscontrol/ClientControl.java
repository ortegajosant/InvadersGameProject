package app.invaders.com.invaderscontrol;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by jorte on 15/4/2018.
 */

public class ClientControl implements Runnable{

    private static ClientControl instance;
    private int port;
    private String ipAdress;
    private String data = "";
    private int move = 0;
    private int shot = 0;

    private ClientControl(String ipAdress, int port) {
        this.ipAdress = ipAdress;
        this.port = port;
    }

    public static ClientControl getInstance(String ip, int port) {
        if (instance == null) {
            instance = new ClientControl(ip, port);
        }
        return instance;
    }

    @Override
    public void run() {
        int[] data2 = new int[]{move, shot};
        try {
            Socket client = new Socket(ipAdress, port);
            // "SE ENVÍA EL Array con datos
            ObjectOutputStream message = new ObjectOutputStream(client.getOutputStream());
            BufferedReader messageGetted = new BufferedReader(new InputStreamReader(client.getInputStream()));
            message.writeObject(data2);

            // SE RECIBE EL MENSAJE DEL SERVIDOR
            this.data = messageGetted.readLine();
            setShot(0);
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readMessage() {
        return this.data;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public void setShot(int shot) {
        this.shot = shot;
    }
}
