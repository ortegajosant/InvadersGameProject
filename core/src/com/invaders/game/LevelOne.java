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
	Nave player;
	Enemy enemy;
	ListaSimple<Bullet> bullets;

	public LevelOne(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
	}

	@Override
	public void show() {
		player = new Nave();
		enemy = new Enemy(0, new Texture("images/enemy1.png"));
		bullets = new ListaSimple<>();
	}

	@Override
	public void render(float delta) {
		renderGame();
		doAction();
	}

	public void renderGame() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		invadersLauncher.batch.begin();
		for (int i = 0; i < bullets.getlength(); i++) {
			bullets.find(i).render(invadersLauncher.batch);
		}
		player.render(invadersLauncher.batch);
		enemy.render(invadersLauncher.batch);

		invadersLauncher.batch.end();
	}

	public void doAction() {
		
		boolean rigth = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean space = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);

		float delta = Gdx.graphics.getDeltaTime();

		if (rigth) {
			player.setXCoord(player.getXCoord() + (player.getSpeed() * delta));
		}
			
		else if (left) {
			player.setXCoord(player.getXCoord() - (player.getSpeed() * delta));
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

//		for (int i = 0; i < bullets.getlength(); i++) {
//
//			if (bullets.find(i).getRemove()) {
//				bullets.remove(bullets.find(i));
//				System.out.println("eliminando 1");
//				System.out.println(bullets.getlength());
//			}
//		}
	}

	@Override
	public void dispose() {
		invadersLauncher.batch.dispose();

	}
}
