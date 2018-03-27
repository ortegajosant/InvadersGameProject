package com.invaders.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Nave {
	private Bala bala;
	private Sprite sprite;
	private Texture image;
	private float speed;
	
	public Nave() {
		this.image = new Texture("images/nave.png");
		this.sprite = new Sprite(image, 32, 32);
		this.sprite.setPosition(250, 30);
		this.speed = 50;
	}
	public void render(final SpriteBatch batch) {
		sprite.draw(batch);
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public float getSpeed() {
		return this.speed;
	}

}
