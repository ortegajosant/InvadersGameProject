package com.invaders.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.invaders.datastructures.SimpleList;
import com.invaders.datastructures.SimpleNode;
import com.invaders.game.InvadersLauncher;
import com.invaders.logic.KeyObserver;
import com.invaders.logic.Bullet;
import com.invaders.logic.Nave;
import com.invaders.logic.row.EnemiesFactoryRow;
import com.invaders.logic.row.EnemyRowInterface;

public class LevelOne extends Window {
	private static Nave player;
	private EnemyRowInterface enemiesRow;
	private SimpleList<Bullet> bullets;
	private EnemiesFactoryRow factory;
	private KeyObserver keyObserver;

	public LevelOne(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
	}

	@Override
	public void show() {
		player = new Nave();
		bullets = new SimpleList<>();
		keyObserver = KeyObserver.getObserverInstance();
		factory = EnemiesFactoryRow.getInstance();
		enemiesRow = factory.createEnemyRow(1, "basic");
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
		player.render(invadersLauncher.batch);
		for (int i = 0; i < bullets.getLength(); i++) {
			bullets.find(i).render(invadersLauncher.batch);
		}
		enemiesRow.showRow(invadersLauncher);

		invadersLauncher.batch.end();
	}

	/**
	 * Ejecuta las acciones por entrada de teclado.
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
			bullets.add(new SimpleNode<Bullet>(new Bullet(player.getXCoord() + 12)));
		}

		//
		for (int i = 0; i < bullets.getLength(); i++) {
			bullets.find(i).update(delta);
			if (bullets.find(i).getRemove()) {
				bullets.remove(bullets.find(i));
			}
		}

		enemiesRow.deleteEnemy(bullets);
		enemiesRow.moveRow(delta);

	}

	@Override
	public void dispose() {
		this.dispose();
	}
}
