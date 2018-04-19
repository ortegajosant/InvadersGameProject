package com.invaders.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.invaders.server.ControlServer;

/**
 * Controla todas las entradas de usuario, por teclado o por el servidor.
 * @author jorte
 *
 */
public class KeyObserver {
	
	private static KeyObserver instance;
	private ControlServer wirelessControl;
	
	private KeyObserver() {
		
	}
	
	/**
	 * Verifica que solo existe una instancia de keyObserver y así solo crear una
	 * @return KeyObserver
	 */
	public static KeyObserver getObserverInstance() {
		if(instance == null) {
			instance = new KeyObserver();
		}
		return instance;
	}
	
	/**
	 * Indica si se requiere la acción para que la nave se mueva hacia la derecha
	 * @return Boolean True / False
	 */
	public boolean keyRight(){
		if (wirelessControl != null && wirelessControl.getMove() != 0) {
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT) 
					|| wirelessControl.getMove() == 1) {
				return true;
			}
			return false;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			return true;
		}
		return false;
	
		
	}
	
	/**
	 * Indica si se requiere que la nave se mueva para la izquierda
	 * @return True / False
	 */
	public boolean keyLeft() {
		if (wirelessControl != null && wirelessControl.getMove() != 0) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)
					|| wirelessControl.getMove() == 2) {
				return true;
			}
			return false;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			return true;
		}
	
	return false;
		
	}
	
	/**
	 * Indica si se accionó la tecla hacia arriba
	 * @return True / False
	 */
	public boolean keyUp() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Indica si se accionó la tecla hacia abajo
	 * @return True / False
	 */
	public boolean keyDown() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && !Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Indica si se ha iniciado la acción para disparar
	 * @return True / False
	 */
	public boolean keySpace() {
		if (wirelessControl != null) {
			if(wirelessControl.getDisparo() == 1) {
				wirelessControl.setDisparo(0);
				return true;
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			return true;
		}
		return false;
	}

	/**
	 * Indica si se ha presionado la tecla Enter
	 * @return True / False
	 */
	public boolean keyEnter() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Establece los datos a enviar para el cliente del servidor
	 * @param level número de nivel
	 * @param current hilera de enemigos actual 
	 * @param next Hilera de enemigos siguiente
	 * @param score Puntaje de la partida
	 */
	public void setStatsServer(int level, String current, String next, int score) {
		String scoreW = String.valueOf(score);
		String levelW = String.valueOf(level);
		wirelessControl.setStats(levelW, current, next, scoreW);
	}
	
	/**
	 * Inicia el servidor
	 */
	public void runWirelessControl() {
		wirelessControl = ControlServer.getInstance();
	}

	/**
	 * Retorna la instancia del servidor
	 * @return ControlServer
	 */
	public ControlServer getWirelessControl() {
		return wirelessControl;
	}
	
}
