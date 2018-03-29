package com.invaders.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.invaders.game.InvadersLauncher;
import com.invaders.logic.KeyObserver;
import com.invaders.logic.Nave;
import com.invaders.logic.Weapon;
import com.invaders.logic.row.EnemiesFactoryRow;
import com.invaders.logic.row.EnemyRowInterface;

public abstract class Window implements Screen {
	protected InvadersLauncher invadersLauncher;
	protected Nave player;
	protected EnemyRowInterface enemiesRow;
	protected EnemiesFactoryRow factory;
	protected KeyObserver keyObserver;
	protected Weapon bullets;

	public Window(InvadersLauncher invadersLauncher) {
		this.invadersLauncher = invadersLauncher;
	}

	@Override
	public void show() {
		player = Nave.getInstance();
		keyObserver = KeyObserver.getObserverInstance();
		factory = EnemiesFactoryRow.getInstance();
		bullets = new Weapon();
	}

	@Override
	public void render(float delta) {
	}

	/**
	 * Ejecuta las acciones que entran por el teclado.
	 */
	public void doAction() {
		float delta = Gdx.graphics.getDeltaTime();
		// MOVIMIENTOS DE LA NAVE
		// Movimiento hacia la derecha
		if (keyObserver.keyRight()) {
			player.changePosition(true, delta);
		}

		// Movimientos hacia la izquierda
		else if (keyObserver.keyLeft()) {
			player.changePosition(false, delta);
		}

		// Disparos
		else if (keyObserver.keySpace()) {
			bullets.addBullet(player);
		}

		bullets.shotBullet(delta);
		enemiesRow.deleteEnemy(bullets.getBullets());
		enemiesRow.moveRow(delta);
	}

	public void renderGame() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		invadersLauncher.batch.begin();
		player.render(invadersLauncher.batch);

		bullets.show(invadersLauncher);
		enemiesRow.showRow(invadersLauncher);

		invadersLauncher.batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
