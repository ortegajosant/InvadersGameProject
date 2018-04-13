package com.invaders.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.json.*;

public class ClientControl {
	public static void main(String[] args) throws JSONException {
		Socket client;
		int puerto = 5555;
		String ip = "127.0.0.1";
		ObjectOutputStream send;
		ObjectInputStream getMail;
		String[] data;

		// Creación del JSON con los datos para el servidor
		JSONObject jObjt = new JSONObject();
		jObjt.put("direction", "derecha");
		jObjt.put("shot", true);

		int[] data2 = new int[] { 0, 1 };
		try {
			client = new Socket(ip, puerto);

			// "SE ENVÍA EL OBJETO JSON
			send = new ObjectOutputStream(client.getOutputStream());
			send.writeObject(data2);
			// SE RECIBE EL MENSAJE DEL SERVIDOR
			getMail = new ObjectInputStream(client.getInputStream());
			data = (String[]) getMail.readObject();
			System.out.println(data[0] + data[1] + data[2] + data[3]);
			client.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
