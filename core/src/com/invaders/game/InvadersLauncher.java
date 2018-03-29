package com.invaders.game;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.invaders.levels.MainMenu;


public class InvadersLauncher extends Game {
	public SpriteBatch batch;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenu(this));
	}

}
