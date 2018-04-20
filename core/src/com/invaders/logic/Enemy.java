package com.invaders.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Contiene lo necesario para crear un nuevo Enemigo
 * @author jorte
 *
 */
public class Enemy {
	private int strength;
	private Animation<TextureRegion> animation;
	private TextureRegion[] sprites;
	private float animationTime;
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
		this.animationTime = 0;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.speed = speed;
		this.isBoss = boss;
		this.direction = direction;
		setTexture(image);
		rectangleCollision = new Rectangle(this.xCoord, this.yCoord, 32, 32);
		this.score = score;
	}
	
	/**
	 * Dibuja el enemigo en la pantalla
	 * @param batch lienzo sobre el cual se pintan los enemigos
	 */
	public void render(final SpriteBatch batch) {
		setRectanglePosition();
 		animationTime += Gdx.graphics.getDeltaTime();
		frame = animation.getKeyFrame(animationTime, true);
		batch.draw(frame, xCoord, yCoord);
	}
	
	/**
	 * Realiza el movimiento en pantalla para el enemigo
	 * @param deltatime float (Diferencia de tiempo entre un frame y otro )
	 * @param isLimit boolean (Indica si se llega a un límite en la pantalla)
	 */
	public void move(float deltatime, boolean isLimit) {
		if (!isLimit) {
			if (direction) {
				xCoord += this.speed * deltatime;
			} else {
				xCoord -= this.speed * deltatime;
			}
		} else {
			if (!direction) {
				xCoord += (this.speed + 5) * deltatime;
			} else {
				xCoord -= (this.speed + 5) * deltatime;
			}
			this.yCoord -= 750 * deltatime;
			this.direction = !direction;
		}
	}
	
	/**
	 * Establece coordenadas en X
	 * @param xCoord float
	 */
	public void setXCoord(float xCoord) {
		this.xCoord = xCoord;
	}
	
	/**
	 * Establece coordenadas en Y
	 * @param yCoord float
	 */
	public void setYCoord(float yCoord) {
		this.yCoord = yCoord;
	}
	
	/**
	 * Retorna coordenadas en X del enemigo
	 * @return xCoord Float
	 */
	public float getXCoord() {
		return xCoord;
	}
	
	/**
	 * Retorna el Rectangle que controla las colisiones en pantalla
	 * @return Rectangle (rectangleCollision)
	 */
	public Rectangle getRectangle() {
		return rectangleCollision;
	}
	
	/**
	 * Establece las nuevas coordenadas para el Rectangle
	 */
	public void setRectanglePosition() {
		rectangleCollision.setX(this.xCoord);
		rectangleCollision.setY(this.yCoord);
	}

	/**
	 * Retorna coordenada en Y
	 * @return Float
	 */
	public float getYCoord() {
		return this.yCoord;
	}
	
	public int getStrength() {
		return this.strength;
	}

	/**
	 * Reduce la resistencia de los enemigos
	 */
	public void reduceStrength() {
		this.strength--;
	}
	
	/**
	 * Establece la resistencia de los enmigos
	 * @param i Integer
	 */
	public void setStrength(int i) {
		this.strength = i;
	}
	
	/**
	 * Establece si el enemigo es un boss o no
	 * @param boss Boolean 
	 */
	public void setBoss(boolean boss) {
		this.isBoss = boss;
	}
	
	/**
	 * Establece el arreglo de texturas para el enemigo
	 * @param texture Texture
	 */
	public void setTexture(Texture texture) {
		TextureRegion[][] temp = TextureRegion.split(texture, texture.getWidth() / 2, texture.getHeight());
		sprites = new TextureRegion[2];
		sprites[0] = temp[0][0];
		sprites[1] = temp[0][1];
		animation = new Animation<>(1/2f, sprites);
	}
	
	/**
	 * Retorna si es jefe o no
	 * @return isBoss
	 */
	public boolean getIsBoss() {
		return this.isBoss;
	}
	
	/**
	 * Retorna la direcctión en la que se está moviendo el enemigo
	 * @return True / False
	 */
	public boolean getDirection() {
		return this.direction;
	}
	
	/**
	 * Retorna el puntaje que genera el enemigo
	 * @return Integer
	 */
	public int getScore() {
		return this.score;
	}
}
