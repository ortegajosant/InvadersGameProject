package com.invaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.invaders.datastructures.ListaSimple;
import com.invaders.datastructures.NodoSimple;
import com.invaders.game.InvadersLauncher;
import com.invaders.logic.Bullet;
import com.invaders.logic.Enemy;
import com.invaders.logic.Nave;

public class LevelOne extends Window {
	private static Nave player;
	private ListaSimple<Bullet> bullets;
	private ListaSimple<Enemy> enemies;

	public LevelOne(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
	}

	@Override
	public void show() {
		player = new Nave();
		bullets = new ListaSimple<>();
		createFormation(51, Gdx.graphics.getHeight(), 7);
		
	}

	@Override
	public void render(float delta) {
		renderGame();
		doAction();
		doCollision();
		//enemiesMovement();
	}

	/**
	 * Verifica las colisiones de las balas con las hileras de enemigos. Aún hay que hacerle arreglos.
	 */
	private void doCollision() {
		if (bullets.getlength() > 0 && enemies.getlength() > 0) {
			for (int i = 0; i < bullets.getlength(); i++) {
				for (int j = 0; j < enemies.getlength(); j++) {
					if (bullets.find(i).getRectangle().overlaps(enemies.find(j).getRectangle())) {
						bullets.find(i).setRemove(true);
						enemies.remove(enemies.find(j));
						break;
					}
				}
			}
		}
	}

	public void renderGame() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		invadersLauncher.batch.begin();
		player.render(invadersLauncher.batch);
		for (int i = 0; i < bullets.getlength(); i++) {
			bullets.find(i).render(invadersLauncher.batch);
		}
		if (enemies.getlength() > 0) {
			for (int i = 0; i < enemies.getlength(); i++) {
				enemies.find(i).render(invadersLauncher.batch);
			}
		}

		invadersLauncher.batch.end();
	}
	
	/**
	 * Crea hileras de enemigos
	 */
	public void createFormation(float firstXCoord, float firstYCoord, int limit) {
		enemies = new ListaSimple<>();
		float xCoord =  firstXCoord;
		for (int i = 0; i < limit; i++) {
			enemies.add(new NodoSimple<Enemy>(new Enemy(0, new Texture("images/enemy2.png"), xCoord, firstYCoord)));
			xCoord += 65;
		}
		
	}
	public void doAction() {
		
		boolean rigth = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean space = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);

		float delta = Gdx.graphics.getDeltaTime();

		if (rigth && !left) {
			if (player.getXCoord() < 500 - player.getSprite().getRegionWidth()) {
				player.setXCoord(player.getXCoord() + (player.getSpeed() * delta));
			}
		}
			
		else if (left && !rigth) {
			if (player.getXCoord() > 0) {
				player.setXCoord(player.getXCoord() - (player.getSpeed() * delta));
			}
		}
			
		else if (space) {
			bullets.add(new NodoSimple<Bullet>(new Bullet(player.getXCoord() + 12)));
		}
		
		for (int i = 0; i < bullets.getlength(); i++) {
			bullets.find(i).update(delta);
			if (bullets.find(i).getRemove()) {
				bullets.remove(bullets.find(i));
			}			
		}
		
		if(enemies.getlength() > 0) {
			if (enemies.find(enemies.getlength() - 1).getXCoord() < Gdx.graphics.getWidth() - 42 && enemies.find(0).getXCoord() > 10) {
				for (int i = 0; i < enemies.getlength(); i++) {
					enemies.find(i).move(delta, false);
				}
			} else {
				for (int i = 0; i < enemies.getlength(); i++) {
					enemies.find(i).move(delta, true);
				}
			}
		}
	}

	@Override
	public void dispose() {
		invadersLauncher.batch.dispose();
	}
}
