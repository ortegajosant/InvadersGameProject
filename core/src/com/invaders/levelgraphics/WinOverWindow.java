package com.invaders.levelgraphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.invaders.game.InvadersLauncher;
import com.invaders.logic.KeyObserver;

/**
 * Se encarga de crear una nueva ventana en caso de que se gane o se pierda
 * 
 * @author jorte
 *
 */
public class WinOverWindow extends Window {
	private boolean win;
	private Texture winOverImage;
	private Texture continueImage;
	private Music winOverMusic;
	private float timeText;

	public WinOverWindow(InvadersLauncher invadersLauncher, boolean win) {
		super(invadersLauncher);
		this.win = win;
		keyObserver = KeyObserver.getObserverInstance();
		timeText = 0;
	}

	@Override
	public void show() {
		if (win) {
			winOverImage = new Texture(Gdx.files.internal("images/win.png"));
			continueImage = new Texture("images/continueWin.png");
			winOverMusic = Gdx.audio.newMusic(Gdx.files.internal("music/winTheme.ogg"));
			gameText = new BitmapFont(Gdx.files.internal("font/mercutio_basic.fnt"),
					Gdx.files.internal("font/mercutio_basic_0.png"), false);
		} else {
			winOverImage = new Texture("images/gameOver.png");
			continueImage = new Texture("images/continueOver.png");
			winOverMusic = Gdx.audio.newMusic(Gdx.files.internal("music/gameOverTheme.ogg"));

		}
		winOverMusic.play();
	}

	@Override
	public void render(float delta) {
		renderGame();
		doAction();
		timeText += Gdx.graphics.getDeltaTime();
	}

	@Override
	public void renderGame() {
		if (win) {
			Gdx.gl.glClearColor(1, 1, 1, 1);
		} else {
			Gdx.gl.glClearColor(0, 0, 0, 1);

		}
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		invadersLauncher.batch.begin();
		invadersLauncher.batch.draw(winOverImage, Gdx.graphics.getWidth() / 2 - (winOverImage.getWidth() / 2),
				Gdx.graphics.getHeight() / 2 - (winOverImage.getHeight() / 2 - 50));
		if (win) {
			renderWin(invadersLauncher);
		} else {
			renderGameOver(invadersLauncher);
		}

		if (!winOverMusic.isPlaying()) {
			winOverMusic.play();
		}
		invadersLauncher.batch.end();
	}

	@Override
	public void doAction() {
		if (keyObserver.keyEnter()) {
			nextLevel();
		}
	}

	@Override
	public void nextLevel() {
		this.dispose();
		winOverMusic.stop();
		winOverMusic.dispose();
		invadersLauncher.setScreen(new MainMenu(invadersLauncher));
	}

	public void renderWin(InvadersLauncher invadersLauncher) {
		if (timeText >= 6f) {
			if (timeText >= 6.5f) {
				timeText = 5.6f;
			}
			invadersLauncher.batch.draw(continueImage, Gdx.graphics.getWidth() / 2 - (continueImage.getWidth() / 2),
					50);
		}
		gameText.draw(invadersLauncher.batch, "Your Score is: " + gameScore,
				Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2 - 50);

	}

	public void renderGameOver(InvadersLauncher invadersLauncher) {
		if (timeText >= 17f) {
			if (timeText >= 18f) {
				timeText = 16f;
			}
			invadersLauncher.batch.draw(continueImage, Gdx.graphics.getWidth() / 2 - (continueImage.getWidth() / 2),
					50);
		}
	}
}
