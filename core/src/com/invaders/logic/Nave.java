package com.invaders.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Nave {
	private static Nave instance;
	private float speed;
	private Animation<TextureRegion> animation;
	private static TextureRegion[] sprites;
	private TextureRegion sprite;
	private float xCoord;
	private static Texture image;
	private Rectangle rectangleCollision;	
	private float tiempo;
	
	private Nave() {
		if (image ==  null) {
			image = new Texture("images/nave.png");
		}
		this.speed = 100;
		TextureRegion[][] temp = TextureRegion.split(image, image.getWidth()/2, image.getHeight());
		sprites = new TextureRegion[2];
		sprites[0] = temp[0][0];
		sprites[1] = temp[0][1];
		animation = new Animation<>(1/4f, sprites);
		this.xCoord = 250;
		rectangleCollision = new Rectangle(this.xCoord, 30, 32, 32);
		
	}
	
	public static Nave getInstance() {
		if (instance == null) {
			instance = new Nave();
		}
		return instance;
	}
	
	/**
	 * Dibuja la nave en pantalla
	 * @param batch Recibe el "lienzo" donde colocar el objeto nave.
	 */
	public void render(final SpriteBatch batch) {
		tiempo += Gdx.graphics.getDeltaTime();
		sprite = animation.getKeyFrame(tiempo, true);
		setRectanglePosition();
		batch.draw(sprite, this.xCoord, 30);
	}
	
	public void changePosition(boolean doMotion, float delta) {
		if (doMotion) {
			if (this.getXCoord() < Gdx.graphics.getWidth() - this.getSprite().getRegionWidth()) {
				this.setXCoord(this.getXCoord() + (this.getSpeed() * delta));
			}
		} else {
			if (this.getXCoord() > 0) {
				this.setXCoord(this.getXCoord() - (this.getSpeed() * delta));
			}
		}
	}
	
	public TextureRegion getSprite() {
		return this.sprite;
	}
	
	public float getSpeed() {
		return this.speed;
	}

	public void setXCoord(float xCoord) {
		this.xCoord = xCoord;
	}
	
	public float getXCoord() {
		return this.xCoord;
	}
	
	public void setRectanglePosition() {
		rectangleCollision.setX(this.xCoord);
	}
	
	public Rectangle getRectangle() {
		return rectangleCollision;
	}
}
