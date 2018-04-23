package com.invaders.server;

import java.net.*;
import java.io.*;

/**
 * Singleton
 * Contiene toda la lógica para la creación del servidor que se comunica con el cliente(Celular) y así recibir sus indicaciones
 * @author jorte
 *
 */
public class ControlServer implements Runnable {
	private static ControlServer instance;
	private ServerSocket serverSocket;
	private Socket socket;
	private int port = 5555;
	private PrintWriter send;
	private ObjectInputStream getMessage;
	private int move;
	private int shot;
	private String stats;

	private ControlServer() {
		Thread serverThread = new Thread(this);
		serverThread.start();
		stats = "Level: Main Menu | Current: void | Next: void | Score: 0\n";
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
	 * Ejecuta toda la lógica del servidor, pone abierto el servidor, recibe y envía datos
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
				// Asigna valores a las variables que influyen en el juego
				shot = messageGetted[1];
				move = messageGetted[0];

				// Envía Array al cliente para que muestre en pantalla los datos de la partida
				send = new PrintWriter(socket.getOutputStream());
				send.write(stats);
				send.println(stats);
				send.flush();
				
				socket.close();
			}
		} catch (Exception e) {
			System.out.println(e);
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
		this.stats = "Level: " + level + " | Current: " + current + " | Next: " + next + " | Score: " + score + "\n";
	}
}
