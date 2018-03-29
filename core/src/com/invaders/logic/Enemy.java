package com.invaders.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
	private int endurance;
	private Animation<TextureRegion> animation;
	private TextureRegion[] sprites;
	private float tiempo;
	TextureRegion frame;
	private float xCoord;
	private float yCoord;
	private float speed;
	private boolean direction = true;
	private Rectangle rectangleCollision;
	
	public Enemy(int endurance, Texture image, float xCoord, float yCoord, float speed) {
		Texture imageH = image;
		this.endurance = endurance;
		this.tiempo = 0;
		TextureRegion[][] temp = TextureRegion.split(imageH, imageH.getWidth() / 2, imageH.getHeight());
		sprites = new TextureRegion[2];
		sprites[0] = temp[0][0];
		sprites[1] = temp[0][1];
		animation = new Animation<>(1/2f, sprites);
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.speed = speed;
		rectangleCollision = new Rectangle(this.xCoord, this.yCoord, 32, 32);
		
	}
	
	public void render(final SpriteBatch batch) {
		setRectanglePosition();
 		tiempo += Gdx.graphics.getDeltaTime();
		frame = animation.getKeyFrame(tiempo, true);
		batch.draw(frame, xCoord, yCoord);
	}
	
	public void move(float deltatime, boolean limit) {
		if (!limit) {
			if (direction) {
				xCoord += this.speed * deltatime;
			} else {
				xCoord -= this.speed * deltatime;
			}
		} else {
			if (xCoord < 50) {
				xCoord += (this.speed + 1) * deltatime;
			} else {
				xCoord -= (this.speed + 1) * deltatime;
			}
			this.yCoord -= 500 * deltatime;
			this.direction = !direction;
		}
	}
	
	public void setXCoord(float xCoord) {
		this.xCoord = xCoord;
	}
	
	public void setYCoord(float yCoord) {
		this.yCoord = yCoord;
	}
	
	public float getXCoord() {
		return xCoord;
	}
	
	public Rectangle getRectangle() {
		return rectangleCollision;
	}
	
	public void setRectanglePosition() {
		rectangleCollision.setX(this.xCoord);
		rectangleCollision.setY(this.yCoord);
	}

	public float getYCoord() {
		// TODO Auto-generated method stub
		return this.yCoord;
	}
	
	public int getEndurance() {
		return this.endurance;
	}
}
