package com.invaders.logic;

import com.invaders.datastructures.SimpleList;
import com.invaders.game.InvadersLauncher;
import com.invaders.datastructures.SimpleNode;

public class Weapon {
	private SimpleList<Bullet> bullets;
	
	public Weapon() {
		bullets = new SimpleList<>();
	}
	
	public void shotBullet(float deltaTime) {
		for (int i = 0; i < bullets.getLength(); i++) {
			bullets.find(i).update(deltaTime);
			if (bullets.find(i).getRemove()) {
				bullets.remove(bullets.find(i));
			}
		}
	}
	
	public void show(InvadersLauncher invadersLauncher) {
		for (int i = 0; i < bullets.getLength(); i++) {
			bullets.find(i).render(invadersLauncher.batch);
		}
	}
	
	public void addBullet(Nave player) {
		bullets.add(new SimpleNode<Bullet>(new Bullet(player.getXCoord() + 12)));
	}
	
	public SimpleList<Bullet> getBullets(){
		return bullets;
	}
}
