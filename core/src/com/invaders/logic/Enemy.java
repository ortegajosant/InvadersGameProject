package com.invaders.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
	private int strength;
	private Animation<TextureRegion> animation;
	private TextureRegion[] sprites;
	private float tiempo;
	TextureRegion frame;
	private float xCoord;
	private float yCoord;
	private float speed;
	private boolean direction;
	private Rectangle rectangleCollision;
	private boolean isBoss;
	private int score;
	
	public Enemy(int endurance, Texture image, float xCoord, float yCoord, float speed, boolean boss, boolean direction, int score) {
		this.strength = endurance;
		this.tiempo = 0;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.speed = speed;
		this.isBoss = boss;
		this.direction = direction;
		setTexture(image);
		rectangleCollision = new Rectangle(this.xCoord, this.yCoord, 32, 32);
		this.score = score;
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
			this.yCoord -= 750 * deltatime;
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
	
	public int getStrength() {
		return this.strength;
	}

	public void reduceStrength() {
		this.strength--;
	}
	
	public void setStrength(int i) {
		this.strength = i;
	}
	
	public void setBoss(boolean boss) {
		this.isBoss = boss;
	}
	
	public void setTexture(Texture texture) {
		TextureRegion[][] temp = TextureRegion.split(texture, texture.getWidth() / 2, texture.getHeight());
		sprites = new TextureRegion[2];
		sprites[0] = temp[0][0];
		sprites[1] = temp[0][1];
		animation = new Animation<>(1/2f, sprites);
	}
	
	public boolean getIsBoss() {
		return this.isBoss;
	}
	public boolean getDirection() {
		return this.direction;
	}
	
	public int getScore() {
		return this.score;
	}
}
