package com.invaders.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.invaders.datastructures.SimpleList;
import com.invaders.datastructures.SimpleNode;
import com.invaders.game.InvadersLauncher;
import com.invaders.logic.Enemy;
import com.invaders.logic.KeyObserver;

public class MainMenu extends Window {
	private static Texture titleImage;
	private static Texture playButton;
	private static Texture exitButton;
	private static Texture arrowImage;
	private static Texture controlButton;
	private byte arrowLocation = 1;
	private float yCoordArrow;
	private int width;
	private static Music mainTheme;
	private static Sound navigateSound;
	private float enemiesSpawnTimer = 1.6f;
	private SimpleList<Enemy> enemies;

	public MainMenu(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
		enemies = new SimpleList<Enemy>();
	}

	@Override
	public void show() {
		if (titleImage == null) {
			titleImage = new Texture("images/invaders.png");
			playButton = new Texture("images/play.png");
			exitButton = new Texture("images/exit.png");
			arrowImage = new Texture("images/playLogo.png");
			controlButton = new Texture("images/control.png");
			
			
		}
		keyObserver = KeyObserver.getObserverInstance();
		this.yCoordArrow = 200;
		this.width = Gdx.graphics.getWidth();
		if (mainTheme == null) {
			mainTheme = Gdx.audio.newMusic(Gdx.files.internal("music/mainMenuThemeMoskau.ogg"));
			navigateSound = Gdx.audio.newSound(Gdx.files.internal("music/navigateMenu.ogg"));
			
		}
		mainTheme.play();
	}

	public void render(float delta) {
		renderGame();
		doAction();
		
	}

	public void renderGame() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		invadersLauncher.batch.begin();
		invadersLauncher.batch.draw(titleImage, this.width / 2 - titleImage.getWidth() / 2, 300);
		invadersLauncher.batch.draw(playButton, this.width / 2 - playButton.getWidth() / 2, 200);
		invadersLauncher.batch.draw(exitButton, this.width / 2 - exitButton.getWidth() / 2, 50);
		invadersLauncher.batch.draw(controlButton, this.width / 2 - controlButton.getWidth() / 2, 125);
		invadersLauncher.batch.draw(arrowImage, this.width / 2 - 150, yCoordArrow);

		spawnEnemies();
		if (enemies.getLength() > 0) {
			for (int i = 0; i < enemies.getLength(); i++) {
				enemies.find(i).render(invadersLauncher.batch);
			}
		}
		invadersLauncher.batch.end();
	}

	public void doAction() {
		if (arrowLocation == 1) {
			if (keyObserver.keyDown()) {
				navigateSound.play();
				this.arrowLocation += 1;
				this.yCoordArrow -= 75;
			}
		} else if (arrowLocation == 2) {
			if (keyObserver.keyUp()) {
				navigateSound.play();
				this.arrowLocation -= 1;
				this.yCoordArrow += 75;
			} else if (keyObserver.keyDown()) {
				navigateSound.play();
				this.arrowLocation += 1;
				this.yCoordArrow -= 75;
			}
		} else if (this.arrowLocation == 3) {
			if (keyObserver.keyUp()) {
				navigateSound.play();
				this.arrowLocation -= 1;
				this.yCoordArrow += 75;
			}
		}
		
		if (keyObserver.keyEnter()) {
			if (arrowLocation == 3) {
				Gdx.app.exit();
			} else if (arrowLocation == 2) {
				keyObserver.runWirelessControl();
			} else if (arrowLocation == 1) {
				nextLevel();
			}
		}
	}
	
	public void spawnEnemies() {
		float delta = Gdx.graphics.getDeltaTime();
		enemiesSpawnTimer += delta;
		if (enemiesSpawnTimer >= 0.3f) {
			float yCoord = (float)(Math.random() * (Gdx.graphics.getHeight() - 41));
			enemies.add(new SimpleNode<Enemy>(new Enemy(0, new Texture("images/enemy3.png"), -42, yCoord, 200, false, true, 0)));
			enemiesSpawnTimer = 0;
		} 
		for (int i = 0; i < enemies.getLength(); i++) {
			if(enemies.find(i).getXCoord() > Gdx.graphics.getWidth()) {
				enemies.remove(i);
			} else {
				enemies.find(i).move(delta, false);
			}
		}
	}
	
	@Override
	public void nextLevel() {
		this.dispose();
		mainTheme.stop();
		mainTheme.dispose();
		invadersLauncher.setScreen(new LevelOne(invadersLauncher));
	}
}
