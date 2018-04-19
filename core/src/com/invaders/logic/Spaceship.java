package com.invaders.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Contiene las características necesarias 
 * @author jorte
 *
 */
public class Spaceship {
	private static Spaceship instance;
	private float speed;
	private Animation<TextureRegion> animation;
	private static TextureRegion[] sprites;
	private TextureRegion sprite;
	private float xCoord;
	private static Texture image;
	private Rectangle rectangleCollision;	
	private float animationTime;
	
	private Spaceship() {
		if (image ==  null) {
			image = new Texture("images/nave.png");
		}
		this.speed = 170;
		TextureRegion[][] temp = TextureRegion.split(image, image.getWidth()/2, image.getHeight());
		sprites = new TextureRegion[2];
		sprites[0] = temp[0][0];
		sprites[1] = temp[0][1];
		animation = new Animation<>(1/4f, sprites);
		this.xCoord = 250;
		rectangleCollision = new Rectangle(this.xCoord, 30, 32, 32);
		
	}

	/**
	 * Inicializa la nave jugador
	 * @return Spaceship
	 */
	public static Spaceship getInstance() {
		if (instance == null) {
			instance = new Spaceship();
		}
		return instance;
	}
	
	/**
	 * Dibuja la nave en pantalla
	 * @param batch Recibe el "lienzo" donde colocar el objeto nave.
	 */
	public void render(final SpriteBatch batch) {
		animationTime += Gdx.graphics.getDeltaTime();
		sprite = animation.getKeyFrame(animationTime, true);
		setRectanglePosition();
		batch.draw(sprite, this.xCoord, 30);
	}
	
	/**
	 * Cambia la posición de la nave.
	 * @param direction Indica la dirección en la que se debe mover la nave
	 * @param delta Tiempo entre un Frame y otro
	 */
	public void changePosition(boolean direction, float delta) {
		if (direction) {
			if (this.getXCoord() < Gdx.graphics.getWidth() - this.getSprite().getRegionWidth()) {
				this.setXCoord(this.getXCoord() + (this.getSpeed() * delta));
			}
		} else {
			if (this.getXCoord() > 0) {
				this.setXCoord(this.getXCoord() - (this.getSpeed() * delta));
			}
		}
	}
	
	/**
	 * Retorna Textura
	 * @return TextureRegion
	 */
	public TextureRegion getSprite() {
		return this.sprite;
	}
	
	/**
	 * Retorna la velocidad del enemigo
	 * @return float
	 */
	public float getSpeed() {
		return this.speed;
	}

	/**
	 * Establece la coordenada en X
	 * @param xCoord Float
	 */
	public void setXCoord(float xCoord) {
		this.xCoord = xCoord;
	}
	
	/**
	 * Retorna la coordenada en X
	 * @return float
	 */
	public float getXCoord() {
		return this.xCoord;
	}
	
	/**
	 * Establece las coordenadas de Rectangle, este controla las colisiones
	 */
	public void setRectanglePosition() {
		rectangleCollision.setX(this.xCoord);
	}
	
	/**
	 * Retorna rectangleCollision
	 * @return Rectangle
	 */
	public Rectangle getRectangle() {
		return rectangleCollision;
	}
}
