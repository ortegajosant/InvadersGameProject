package com.invaders.rowlogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Contiene la funcionalidad de las balas enemigas
 * 
 * @author jorte
 *
 */
public class EnemyBullet {
	private static Texture sprite;
	private float xCoord, yCoord;
	private boolean remove = false;
	private Rectangle rectangleCollision;

	public EnemyBullet(float xCoord, float yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		if (sprite == null) {
			sprite = new Texture("images/enemyBullet.png");
		}
		this.rectangleCollision = new Rectangle(this.xCoord, this.yCoord, sprite.getWidth(), sprite.getHeight());
	}

	/**
	 * Actualiza la información de la bala para que se redibuje en pantalla
	 * 
	 * @param deltaTime
	 *            float / Tiempo entre un frame y otro
	 */
	public void update(float deltaTime) {
		yCoord -= 150 * deltaTime;
		if (this.yCoord <= 0) {
			this.remove = true;
		}
	}

	/**
	 * Dibuja los elementos en pantalla
	 * 
	 * @param batch
	 *            SpriteBatch principal
	 */
	public void render(SpriteBatch batch) {
		setRectanglePosition();
		batch.draw(sprite, this.xCoord, this.yCoord);

	}

	/**
	 * Retorna el valor de si una bala puede eliminarse
	 * 
	 * @return boolean True / False
	 */
	public boolean getRemove() {
		return remove;
	}

	/**
	 * Retorna el rectangulo de colisiones
	 * 
	 * @return Rectangle
	 */
	public Rectangle getRectangle() {
		return rectangleCollision;
	}

	/**
	 * Configura la información de posiciones del rectangulo
	 */
	public void setRectanglePosition() {
		rectangleCollision.setY(this.yCoord);
	}

	/**
	 * Configura el valor de verdad para el atributo remove
	 * 
	 * @param b
	 *            True / False
	 */
	public void setRemove(boolean b) {
		this.remove = true;

	}

}
