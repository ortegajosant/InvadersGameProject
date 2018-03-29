package com.invaders.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.invaders.game.InvadersLauncher;
import com.invaders.logic.KeyObserver;

public class MainMenu extends Window {
	private static Texture titleImage;
	private static Texture playButton;
	private static Texture exitButton;
	private static Texture arrowImage;
	private static Texture controlButton;
	private byte arrowLocation = 1;
	private float yCoordArrow;
	private KeyObserver keyObserver;
	private int width;

	public MainMenu(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);

	}

	@Override
	public void show() {
		if (titleImage == null) {
			titleImage = new Texture("images/invaders.png");
			playButton = new Texture("images/play.png");
			exitButton = new Texture("images/exit.png");
			arrowImage = new Texture("images/playLogo.png");
			controlButton = new Texture("images/control.png");
			keyObserver = KeyObserver.getObserverInstance();
			this.yCoordArrow = 200;
			this.width = Gdx.graphics.getWidth();
		}
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

		invadersLauncher.batch.end();
	}

	public void doAction() {

		if (arrowLocation == 1) {
			if (keyObserver.keyDown()) {
				this.arrowLocation += 1;
				this.yCoordArrow -= 75;
			}
		} else if (arrowLocation == 2) {
			if (keyObserver.keyUp()) {
				this.arrowLocation -= 1;
				this.yCoordArrow += 75;
			} else if (keyObserver.keyDown()) {
				this.arrowLocation += 1;
				this.yCoordArrow -= 75;
			}
		} else if (this.arrowLocation == 3) {
			if (keyObserver.keyUp()) {
				this.arrowLocation -= 1;
				this.yCoordArrow += 75;
			}
		}
		if (keyObserver.keyEnter()) {
			if (arrowLocation == 3) {
				Gdx.app.exit();
			} else if (arrowLocation == 2) {
				// Aquí se abre el servidor
			} else if (arrowLocation == 1) {
				this.dispose();
				invadersLauncher.setScreen(new LevelOne(invadersLauncher));
			}
		}
	}

}
