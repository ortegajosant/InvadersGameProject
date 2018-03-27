package com.invaders.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
	private byte damage;
	private static Texture sprite;
	private float yCoord, xCoord;
	private boolean remove = false;
	
	public Bullet(float xCoord) {
		if(sprite== null) {
			sprite = new Texture("images/bullet.png");
		}
		this.yCoord = 40;
		this.xCoord = xCoord;
		this.damage = 1;
	}

	public float getYCoord() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void update(float deltaTime) {
		yCoord += 400 * deltaTime;
		if(yCoord > Gdx.graphics.getHeight()) {
			this.remove = true;
		}
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(sprite, this.xCoord, this.yCoord);
		
	}
	
	public boolean getRemove() {
		return remove;
	}
	
}
