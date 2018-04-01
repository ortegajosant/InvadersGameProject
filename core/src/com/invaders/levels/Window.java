package com.invaders.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.invaders.game.InvadersLauncher;
import com.invaders.logic.KeyObserver;
import com.invaders.logic.Nave;
import com.invaders.logic.Weapon;
import com.invaders.logic.row.EnemiesFactoryRow;
import com.invaders.logic.row.AbstractEnemyRow;

public abstract class Window implements Screen {
	protected InvadersLauncher invadersLauncher;
	protected Nave player;
	protected AbstractEnemyRow enemiesRow;
	protected EnemiesFactoryRow factory;
	protected KeyObserver keyObserver;
	protected Weapon bullets;
	protected String[] rowList;
	protected int rowNumber;
	protected String lastRow;
	protected String newRow;
	private static BitmapFont levelName;
	protected int levelNumber;
	private static ShapeRenderer shape;
	private static int scoreGame;

	public Window(InvadersLauncher invadersLauncher) {
		this.invadersLauncher = invadersLauncher;

	}

	@Override
	public void show() {
		player = Nave.getInstance();
		keyObserver = KeyObserver.getObserverInstance();
		factory = EnemiesFactoryRow.getInstance();
		bullets = new Weapon();
		levelName = new BitmapFont(Gdx.files.internal("font/mercutio_basic.fnt"),
				Gdx.files.internal("font/mercutio_basic_0.png"), false);
		shape = new ShapeRenderer();
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
		scoreGame += enemiesRow.deleteEnemy(bullets.getBullets());
		enemiesRow.moveRow(delta);
	}

	public void renderGame() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeType.Filled);
		shape.setColor(1, 1, 1, 0);
		shape.rect(15, Gdx.graphics.getHeight() - 38, 100, 30);
		shape.rect(Gdx.graphics.getWidth()/2 - 115,  Gdx.graphics.getHeight() - 38, 230, 30);
		shape.rect(Gdx.graphics.getWidth() - 210, Gdx.graphics.getHeight() - 38, 190, 30);
		shape.end();
		invadersLauncher.batch.begin();
		player.render(invadersLauncher.batch);
		bullets.show(invadersLauncher);
		levelName.draw(invadersLauncher.batch, "Level " + levelNumber, 20, Gdx.graphics.getHeight() - 10);
		levelName.draw(invadersLauncher.batch, "Score: " + scoreGame, Gdx.graphics.getWidth() - 205, Gdx.graphics.getHeight() - 10);
		levelName.draw(invadersLauncher.batch, "Next Row: " + newRow, Gdx.graphics.getWidth()/2 - 110,  Gdx.graphics.getHeight() - 10);
		enemiesRow.showRow(invadersLauncher);
		invadersLauncher.batch.end();
	}

	public void nextRow(int level) {
		if (rowNumber != 0) {
			if (enemiesRow.isRowEmpty()) {
				int index = (int) (Math.random() * rowList.length);
				if (lastRow.equals(newRow)) {
					lastRow = newRow;
					newRow = rowList[index];
					enemiesRow.makeRow(false);
				} else {
					lastRow = newRow;
					newRow = rowList[index];
					System.out.println("La siguiente hilera es " + newRow);
					enemiesRow = factory.createEnemyRow(level, lastRow);
				}
				rowNumber--;
			}
		} else {
			nextLevel();
		}
	}

	public abstract void nextLevel();

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
