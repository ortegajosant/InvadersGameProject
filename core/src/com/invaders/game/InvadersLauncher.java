package com.invaders.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InvadersLauncher extends Game {
	SpriteBatch batch;
	Enemy e1;
	Nave player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Nave();
	}

	@Override
	public void render () {
		renderGame();
		doAction();
	}
	
	public void renderGame() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		player.render(batch);
		batch.end();
	}
	public void doAction() {
		boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		
		float delta = Gdx.graphics.getDeltaTime();
		
		if(right) 
			player.getSprite().setPosition(player.getSprite().getX() + (player.getSpeed() * delta), 30);
		else if (left) 
			player.getSprite().setPosition(player.getSprite().getX() - (player.getSpeed() * delta), 30);
		
		
	}
	@Override
	public void dispose () {
		batch.dispose();
		
	}
}
