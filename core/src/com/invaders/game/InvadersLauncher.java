package com.invaders.game;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.invaders.levelgraphics.MainMenu;

/**
 * Launcher del juego Invaders
 * @author jorte
 *
 */
public class InvadersLauncher extends Game {
	public SpriteBatch batch;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenu(this));
	}

}
