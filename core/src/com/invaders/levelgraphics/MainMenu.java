package com.invaders.levelgraphics;

import java.net.InetAddress;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.invaders.datastructures.SimpleList;
import com.invaders.datastructures.SimpleNode;
import com.invaders.game.InvadersLauncher;
import com.invaders.logic.Enemy;
import com.invaders.logic.KeyObserver;

/**
 * Contiene toda la lógica para el menún principal
 * 
 * @author jorte
 *
 */
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
	private boolean showServer;
	private ShapeRenderer serverMessage;
	private static BitmapFont serverText;

	public MainMenu(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
		enemies = new SimpleList<Enemy>();
		showServer = false;
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
		serverMessage = new ShapeRenderer();
		serverText = new BitmapFont(Gdx.files.internal("font/mercutio_basic.fnt"),
				Gdx.files.internal("font/mercutio_basic_0.png"), false);
		mainTheme.play();

	}

	@Override
	public void render(float delta) {
		renderGame();
		doAction();

	}

	@Override
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
		if (showServer) {
			serverMessage.begin(ShapeType.Filled);
			serverMessage.rect(Gdx.graphics.getWidth() / 2 - 230, Gdx.graphics.getHeight() / 2 - 150, 460, 300);
			serverMessage.end();
			serverMessage.begin(ShapeType.Line);
			serverMessage.rect(Gdx.graphics.getWidth() / 2 - 240, Gdx.graphics.getHeight() / 2 - 160, 480, 320);
			serverMessage.end();
			try {
				invadersLauncher.batch.begin();
				serverText.draw(invadersLauncher.batch, "C O N T R O L  S E R V E R", Gdx.graphics.getWidth() / 2 - 130, Gdx.graphics.getHeight() / 2 + 120);
				serverText.draw(invadersLauncher.batch, "IP:    " + InetAddress.getLocalHost().getHostAddress(),
						Gdx.graphics.getWidth() / 2 - 105, Gdx.graphics.getHeight() / 2 + 70);
				serverText.draw(invadersLauncher.batch, "PORT: " + 5555, Gdx.graphics.getWidth() / 2 - 65,
						Gdx.graphics.getHeight() / 2 + 15);
				serverText.draw(invadersLauncher.batch, "Press ENTER to continue", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - 100);
				invadersLauncher.batch.end();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}

	@Override
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
			if (showServer) {
				showServer = false;
			} else if (arrowLocation == 3) {
				Gdx.app.exit();
			} else if (arrowLocation == 2) {
				showServer = true;
				keyObserver.runWirelessControl();
			} else if (arrowLocation == 1) {
				nextLevel();
			}
		}
	}

	/**
	 * Realiza la animación de los enmigos en pantalla
	 */
	public void spawnEnemies() {
		float delta = Gdx.graphics.getDeltaTime();
		enemiesSpawnTimer += delta;
		if (enemiesSpawnTimer >= 0.3f) {
			float yCoord = (float) (Math.random() * (Gdx.graphics.getHeight() - 41));
			enemies.add(new SimpleNode<Enemy>(
					new Enemy(0, new Texture("images/enemy3.png"), -42, yCoord, 200, false, true, 0)));
			enemiesSpawnTimer = 0;
		}
		for (int i = 0; i < enemies.getLength(); i++) {
			if (enemies.find(i).getXCoord() > Gdx.graphics.getWidth()) {
				enemies.remove(i);
			} else {
				enemies.find(i).move(delta, false);
			}
		}
	}

	public void text() {

	}

	@Override
	public void nextLevel() {
		serverText.dispose();
		this.dispose();
		mainTheme.stop();
		mainTheme.dispose();
		invadersLauncher.setScreen(new LevelOne(invadersLauncher));
	}
}
