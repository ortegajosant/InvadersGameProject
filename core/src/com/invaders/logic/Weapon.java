package com.invaders.logic;

import com.invaders.datastructures.SimpleList;
import com.invaders.game.InvadersLauncher;
import com.invaders.datastructures.SimpleNode;

/**
 * Elabora un conjunto de Bullet para que sea más simple manejar el disparo de estas.
 * @author jorte
 */
public class Weapon {
	private SimpleList<Bullet> bullets;
	
	public Weapon() {
		bullets = new SimpleList<>();
	}
	
	/**
	 * Mueve cada bala en pantalla
	 * @param deltaTime Tiempo entre el último frame y el siguiente
	 */
	public void shotBullet(float deltaTime) {
		for (int i = 0; i < bullets.getLength(); i++) {
			bullets.find(i).update(deltaTime);
			if (bullets.find(i).getRemove()) {
				bullets.remove(bullets.find(i));
			}
		}
	}
	
	/**
	 * Crea cada bala en pantalla
	 * @param invadersLauncher Launcher
	 */
	public void show(InvadersLauncher invadersLauncher) {
		for (int i = 0; i < bullets.getLength(); i++) {
			bullets.find(i).render(invadersLauncher.batch);
		}
	}
	
	/**
	 * Añade una nueva bala a la lista para ser disparada
	 * @param player Spaceship, Obtiene la coordenada
	 */
	public void addBullet(Spaceship player) {
		bullets.add(new SimpleNode<Bullet>(new Bullet(player.getXCoord() + 12)));
	}
	
	/**
	 * Retorna la lista de balas (Bullet)
	 * @return SimpleList (Bullet)
	 */
	public SimpleList<Bullet> getBullets(){
		return bullets;
	}
}
