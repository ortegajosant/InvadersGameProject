package com.invaders.rowlogic;

import com.invaders.datastructures.SimpleList;
import com.invaders.game.InvadersLauncher;
import com.invaders.levelgraphics.Window;
import com.invaders.logic.Bullet;

/**
 * Clase abstracta que contiene toda la lógica para cada hilera de enemigos
 * @author jorte
 *
 */
public abstract class AbstractEnemyRow {
	protected int speed;

	/**
	 * Reacomoda la hilera de enemigos cada vez que un enemigo muere
	 * @param xCoord Primera coordenada en X
	 * @param limit Límite de la cantidad de enemigos a reordenar
	 */
	public void sortRow(float xCoord, int limit) {
		
	}

	/**
	 * Crea una nueva hilera de enemigos
	 * @param newRow True / False (Verifica si debe crear un nuevo tipo de hilera o debe rellenarla)
	 */
	public void makeRow(boolean newRow) {
		
	}

	/**
	 * Elimina un enemigo de la hilera
	 * @param bullets Lista de balas en pantalla
	 * @return Integer
	 */
	public abstract int deleteEnemy(SimpleList<Bullet> bullets);

	/**
	 * Elimina la hilera por completo
	 */
	public void deleteRow() {
		
	}

	/**
	 * Realiza el movimiento de los enmigos en pantalla
	 * @param deltaTime Distancia entre cada fotograma
	 */
	public void moveRow(float deltaTime) {
		
	}

	/**
	 * Pinta cada enemigo en pantalla
	 * @param invaderslauncher Batch de la librería gráfica, el lienzo en donde pintar
	 */
	public void showRow(InvadersLauncher invaderslauncher) {
		
	}
	
	/**
	 * Realiza un cambio de jefe en la hilera
	 */
	public void changeBoss() {
		
	}
	
	/**
	 * Verifica si la hilera está vacía
	 * @return True / false
	 */
	public abstract boolean isRowEmpty();
	
	/**
	 * Verifica si la hilera a logrado llegar hasta abajo
	 * @param currentWindow Nivel actual
	 */
	public void rowWin(Window currentWindow) {
		
	}

}
