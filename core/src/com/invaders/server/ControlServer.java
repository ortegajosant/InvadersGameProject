package com.invaders.server;

import java.net.*;
import java.io.*;
import org.json.*;

public class ControlServer implements Runnable {
	private static ControlServer instance;
	private ServerSocket serverSocket;
	private Socket socket;
	private int port = 5555;
	private ObjectOutputStream send;
	private ObjectInputStream getMessage;
	private int move;
	private int shot;
	private String[] stats;

	private ControlServer() {
		Thread serverThread = new Thread(this);
		serverThread.start();
		stats = new String[] { "Main Menu", "nothing", "change", "0" };
	}

	public static ControlServer getInstance() {
		if (instance == null) {
			System.out.println("Se ha iniciado el servidor");
			instance = new ControlServer();
		}
		return instance;
	}

	public void setMove(int move) {
		this.move = move;
	}

	public int getMove() {
		return move;
	}

	public void setDisparo(int disparo) {
		this.shot = disparo;
	}

	public int getDisparo() {
		return this.shot;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				socket = new Socket();
				socket = serverSocket.accept();

				// Recibe el mensaje del cliente
				getMessage = new ObjectInputStream(socket.getInputStream());

				int[] messageGetted = (int[]) getMessage.readObject();
				System.out.println(messageGetted[0]);
				// Asigna valores a las variables que influyen en el juego
				shot = messageGetted[1];
				move = messageGetted[0];

				// Envía JSON al cliente para que muestre en pantalla los datos de la partida
				send = new ObjectOutputStream(socket.getOutputStream());
				send.writeObject(stats);

				socket.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void setStats(String level, String current, String next, String score) throws JSONException {
		stats[0] = level;
		stats[1] = current;
		stats[2] = next;
		stats[3] = score;

	}
}
