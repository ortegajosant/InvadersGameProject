package com.invaders.levelgraphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.invaders.game.InvadersLauncher;
import com.invaders.logic.KeyObserver;
import com.invaders.logic.Spaceship;
import com.invaders.logic.Weapon;
import com.invaders.rowlogic.AbstractEnemyRow;
import com.invaders.rowlogic.EnemiesFactoryRow;

public abstract class Window implements Screen {
	protected InvadersLauncher invadersLauncher;
	protected Spaceship player;
	protected AbstractEnemyRow enemiesRow;
	protected EnemiesFactoryRow factory;
	protected KeyObserver keyObserver;
	protected Weapon bullets;
	protected String[] rowList;
	protected int rowNumber;
	protected String lastRow;
	protected String newRow;
	protected static BitmapFont gameText;
	protected int levelNumber;
	private static ShapeRenderer scoreBar;
	protected static int gameScore;
	protected static Sound bulletSound;
	protected static Sound enemyDead;
	protected static Sound enemyMovement;
	private float movementTime;
	private float gunActivation;
	protected static Sound levelUpSound;

	public Window(InvadersLauncher invadersLauncher) {
		this.invadersLauncher = invadersLauncher;

	}

	/**
	 * Crea los objetos para mostrarlos en pantalla
	 */
	@Override
	public void show() {
		player = Spaceship.getInstance();
		keyObserver = KeyObserver.getObserverInstance();
		factory = EnemiesFactoryRow.getInstance();
		bullets = new Weapon();
		gameText = new BitmapFont(Gdx.files.internal("font/mercutio_basic.fnt"),
				Gdx.files.internal("font/mercutio_basic_0.png"), false);
		scoreBar = new ShapeRenderer();
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
		if (keyObserver.keySpace() && gunActivation > 0.4f) {
			bulletSound.play();
			bullets.addBullet(player);
			gunActivation = 0;
		}
		
		bullets.shotBullet(delta);
		int lastScore = gameScore;
		gameScore += enemiesRow.deleteEnemy(bullets.getBullets());
		if (gameScore != lastScore) {
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

	/**
	 * Ejecuta el renderizado del juego, dibuja todos los objetos
	 */
	public void renderGame() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		scoreBar.begin(ShapeType.Filled);
		scoreBar.rect(15, Gdx.graphics.getHeight() - 38, Gdx.graphics.getWidth() -25, 30);
		scoreBar.end();
		invadersLauncher.batch.begin();
		player.render(invadersLauncher.batch);
		bullets.show(invadersLauncher);
		gameText.draw(invadersLauncher.batch, "Level " + levelNumber + "          Current: " + lastRow, 20, Gdx.graphics.getHeight() - 10);
		gameText.draw(invadersLauncher.batch, "Next: " + newRow, Gdx.graphics.getWidth() - 370, Gdx.graphics.getHeight() - 10);
		gameText.draw(invadersLauncher.batch, "Score: " + gameScore, Gdx.graphics.getWidth() - 170, Gdx.graphics.getHeight() - 10);
		enemiesRow.showRow(invadersLauncher);
		invadersLauncher.batch.end();
		if (keyObserver.getWirelessControl() != null) {
			keyObserver.setStatsServer(levelNumber, lastRow, newRow, gameScore);
		}
		
	}

	/**
	 * Retorna una nueva hilera de enemigos para el nivel actual
	 * @param level int, Nivel en juego
	 */
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

	/**
	 * Realiza el cambio de nivel
	 */
	public abstract void nextLevel();

	/**
	 * El jugador pierde y se devuelve al menú principal
	 * @param currentWindow Window
	 */
	public void finishGame(Window currentWindow) {
		currentWindow.dispose();
		bulletSound.dispose();
		enemyDead.dispose();
		enemyMovement.dispose();
		invadersLauncher.setScreen(new WinOverWindow(invadersLauncher, false));
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
		return gameScore;
	}

}
