package com.invaders.server;

import java.net.*;
import java.io.*;

/**
 * Singleton
 * Contiene toda la l�gica para la creaci�n del servidor que se comunica con el cliente(Celular) y as� recibir sus indicaciones
 * @author jorte
 *
 */
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
		stats = new String[] {"Main Menu", "void", "void", "0"};
	}

	/**
	 * Verifica si ya existe una instancia del servidor
	 * @return ControlServer
	 */
	public static ControlServer getInstance() {
		if (instance == null) {
			System.out.println("Se ha iniciado el servidor");
			instance = new ControlServer();
		}
		return instance;
	}

	/**
	 * Configura el movimiento para la nave
	 * @param move Int
	 */
	public void setMove(int move) {
		this.move = move;
	}

	/**
	 * Retorna el tipo de movimiento para la nave
	 * @return integer
	 */
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
	/**
	 * Ejecuta toda la l�gica del servidor, pone abierto el servidor, recibe y env�a datos
	 */
	public void run() {
		
		try {
			serverSocket = new ServerSocket(port);
			System.out.println(InetAddress.getLocalHost().getHostAddress());
			System.out.println(port);
			while (true) {
				socket = new Socket();
				socket = serverSocket.accept();

				// Recibe el mensaje del cliente
				getMessage = new ObjectInputStream(socket.getInputStream());

				int[] messageGetted = (int[]) getMessage.readObject();
				System.out.println(messageGetted[0]);
				System.out.println("SHOT"+messageGetted[1]);
				// Asigna valores a las variables que influyen en el juego
				shot = messageGetted[1];
				move = messageGetted[0];

				// Env�a JSON al cliente para que muestre en pantalla los datos de la partida
				send = new ObjectOutputStream(socket.getOutputStream());
				send.writeObject(stats);

				socket.close();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Configura los datos a enviar para el cliente
	 * @param level Nivel
	 * @param current Hilera actual
	 * @param next Hilera siguiente
	 * @param score Puntaje de partida
	 */
	public void setStats(String level, String current, String next, String score) {
		stats[0] = level;
		stats[1] = current;
		stats[2] = next;
		stats[3] = score;
	}
}
