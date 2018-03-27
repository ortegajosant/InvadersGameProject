package com.invaders.game;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InvadersLauncher extends Game {
	SpriteBatch batch;
	LevelOne levelOne ;
	@Override
	public void create () {
		batch = new SpriteBatch();
		levelOne = new LevelOne(this);
		setScreen(levelOne);
	}

}
