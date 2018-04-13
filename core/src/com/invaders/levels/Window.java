package com.invaders.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
	protected static int scoreGame;
	protected static Sound bulletSound;
	protected static Sound enemyDead;
	protected static Sound enemyMovement;
	private float movementTime;
	private float gunActivation;
	protected static Sound levelUpSound;

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
		bulletSound = Gdx.audio.newSound(Gdx.files.internal("music/shoot.ogg"));
		enemyDead = Gdx.audio.newSound(Gdx.files.internal("music/invaderKilled.ogg"));
		levelUpSound = Gdx.audio.newSound(Gdx.files.internal("music/levelUp.ogg"));
		levelUpSound.play();
		movementTime = 0;
		gunActivation = 0;
	}

	@Override
	public void render(float delta) {
	}

	/**
	 * Ejecuta las acciones que entran por el teclado.
	 */
	public void doAction() {
		float delta = Gdx.graphics.getDeltaTime();
		gunActivation += delta;
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
		if (keyObserver.keySpace() && gunActivation > 0.7f) {
			bulletSound.play();
			bullets.addBullet(player);
			gunActivation = 0;
		}

		else if (keyObserver.keyEnter()) {
			pause();
		}

		bullets.shotBullet(delta);
		int lastScore = scoreGame;
		scoreGame += enemiesRow.deleteEnemy(bullets.getBullets());
		if (scoreGame != lastScore) {
			enemyDead.play();
		}
		enemiesRow.moveRow(delta);
		movementTime += delta;
		if (0.5f < movementTime) {
			enemyMovement.play();
			movementTime = 0;
		}
		if (!enemiesRow.isRowEmpty()) {
			enemiesRow.rowWin(this);
		}
	}

	public void renderGame() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeType.Filled);
		shape.rect(15, Gdx.graphics.getHeight() - 38, Gdx.graphics.getWidth() -25, 30);
		shape.end();
		invadersLauncher.batch.begin();
		player.render(invadersLauncher.batch);
		bullets.show(invadersLauncher);
		levelName.draw(invadersLauncher.batch, "Level " + levelNumber + "          Current: " + lastRow 
				+ "         Next: " + newRow + "         Score: " + scoreGame, 20, Gdx.graphics.getHeight() - 10);
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

	public void finishGame(Window currentWindow) {
		currentWindow.dispose();
		bulletSound.dispose();
		enemyDead.dispose();
		enemyMovement.dispose();
		invadersLauncher.setScreen(new MainMenu(invadersLauncher));
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
		this.dispose();
	}

	@Override
	public void dispose() {

	}

	public int getScoreGame() {
		return scoreGame;
	}

}
