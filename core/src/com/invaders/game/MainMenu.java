package com.invaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainMenu extends Window{
	private static Texture titleImage;
	private static Texture playButton;
	private static Texture exitButton;
	private static Texture arrowImage;
	private static Texture controlButton;
	private byte arrowLocation = 1;
	private float xCoordArrow;

	public MainMenu(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
		
		
	}
	
	@Override
	public void show() {
		if (titleImage == null){
			titleImage = new Texture("images/invaders.png");
			playButton = new Texture("images/play.png");
			exitButton = new Texture("images/exit.png");
			arrowImage = new Texture("images/playLogo.png");
			controlButton = new Texture("images/control.png");
			this.xCoordArrow = 200;
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
		invadersLauncher.batch.draw(titleImage, 12, 300);
		invadersLauncher.batch.draw(playButton, 194, 200);
		invadersLauncher.batch.draw(exitButton, 196, 50);
		invadersLauncher.batch.draw(controlButton, 153, 125);
		invadersLauncher.batch.draw(arrowImage, 125, xCoordArrow);
		
		
		invadersLauncher.batch.end();
		
	}
	
	public void doAction() {
		boolean up = Gdx.input.isKeyJustPressed(Input.Keys.UP);
		boolean down = Gdx.input.isKeyJustPressed(Input.Keys.DOWN);
		boolean enter = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
		
		if (arrowLocation == 1) {
			if (down) {
				this.arrowLocation += 1;
				this.xCoordArrow -= 75;
			}
		} else if (arrowLocation == 2) {
			if (up) {
				this.arrowLocation -= 1 ;
				this.xCoordArrow += 75;
			} else if (down){
				this.arrowLocation += 1;
				this.xCoordArrow -= 75;
			}
		} else if (this.arrowLocation == 3) {
			if (up) {
				this.arrowLocation -= 1;
				this.xCoordArrow += 75;
			}
		} 
		if (enter) {
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
