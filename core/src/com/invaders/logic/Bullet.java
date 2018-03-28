package com.invaders.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
	private byte damage;
	private static Texture sprite;
	private float yCoord, xCoord;
	private boolean remove = false;
	private Rectangle rectangleCollision;
	
	public Bullet(float xCoord) {
		if(sprite== null) {
			sprite = new Texture("images/bullet.png");
		}
		this.yCoord = 40;
		this.xCoord = xCoord;
		this.damage = 1;
		this.rectangleCollision = new Rectangle(this.xCoord, this.yCoord, sprite.getWidth(), sprite.getHeight());
	}

	public float getYCoord() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Designa el valor de remove para cada bullet en el juego, cuando esta colisiona el valor cambia a true y se elimina del juego.
	 * @param deltaTime 
	 */
	public void update(float deltaTime) {
		yCoord += 400 * deltaTime;
		if(yCoord > Gdx.graphics.getHeight()) {
			this.remove = true;
		}
	}
	
	public void render(SpriteBatch batch) {
		setRectanglePosition();
		batch.draw(sprite, this.xCoord, this.yCoord);
		
	}
	
	public boolean getRemove() {
		return remove;
	}
	
	public Rectangle getRectangle() {
		return rectangleCollision;
	}
	
	public void setRectanglePosition() {
		rectangleCollision.setY(this.yCoord);
	}

	public void setRemove(boolean b) {
		this.remove = true;
		
	}
	
}
