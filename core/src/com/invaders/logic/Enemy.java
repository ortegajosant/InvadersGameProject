package com.invaders.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy {
	private int resistencia;
	private Animation<TextureRegion> animation;
	private TextureRegion[] sprites;
	private float tiempo;
	TextureRegion frame;
	
	public Enemy(int resistencia, Texture image) {
		Texture imageH = image;
		this.resistencia = resistencia;
		this.tiempo = 0;
		TextureRegion[][] temp = TextureRegion.split(imageH, imageH.getWidth() / 2, imageH.getHeight());
		sprites = new TextureRegion[2];
		sprites[0] = temp[0][0];
		sprites[1] = temp[0][1];
		animation = new Animation<>(1/2f, sprites);
		
	}
	
	public void render(final SpriteBatch batch) {
		tiempo += Gdx.graphics.getDeltaTime();
		frame = animation.getKeyFrame(tiempo, true);
		batch.draw(frame, 25, 25);
	}
	
	public void move() {
		
	}
}
