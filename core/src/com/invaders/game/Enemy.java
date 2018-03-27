package com.invaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
	private int resistencia;
	private float velocidad;
	private Sprite sprite;
	private Texture image;
	private int xCoord;
	private int yCoord;
	
	public Enemy(int xCoord, int yCoord, int velocidad, int resistencia) {
		image = new Texture("images/enemy1.png");
		sprite = new Sprite(image, 32, 32);
		this.yCoord = yCoord;
		this.xCoord = xCoord;
		this.resistencia = resistencia;
		this.velocidad = velocidad;
	}
	
	public void render(final SpriteBatch batch) {
		batch.draw(sprite, this.xCoord, this.yCoord);
	}
	
	public void move() {
		this.xCoord += velocidad;
	}
	
	public int getXCoord() {
		return this.xCoord;
	}
	
	public int getYCoord() {
		return this.yCoord;
	}
	
	public Texture getImage() {
		return this.image;
	}
}
