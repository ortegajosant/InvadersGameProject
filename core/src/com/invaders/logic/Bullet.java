package com.invaders.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Contiene las características de una bala
 * @author jorte
 *
 */
public class Bullet {
	private static Texture sprite;
	private float yCoord, xCoord;
	private boolean remove = false;
	private Rectangle rectangleCollision;
	
	public Bullet(float xCoord) {
		if(sprite== null) {
			sprite = new Texture("images/bullet.png");
		}
		this.yCoord = 50;
		this.xCoord = xCoord;
		this.rectangleCollision = new Rectangle(this.xCoord, this.yCoord, sprite.getWidth(), sprite.getHeight());
	}
	
	/**
	 * Designa el valor de remove para cada bullet en el juego, cuando esta colisiona el valor cambia a true y se elimina del juego.
	 * @param deltaTime 
	 */

	/**
	 * Mueve la bala a lo largo de la pantalla, verifica si esta se ha salido del rango de pantalla
	 * @param deltaTime Float (tiempo entre un Frame y otro)
	 */
	public void update(float deltaTime) {
		yCoord += 400 * deltaTime;
		if(yCoord > Gdx.graphics.getHeight()) {
			this.remove = true;
		}
	}
	
	/**
	 * Pinta la bala en una coordenada específica
	 * @param batch Lienzo donde pintar (Pantalla principal)
	 */
	public void render(SpriteBatch batch) {
		setRectanglePosition();
		batch.draw(sprite, this.xCoord, this.yCoord);
		
	}
	
	/**
	 * Retorna valor booleano que indica si la bala es candidata a eliminarse
	 * @return True / False
	 */
	public boolean getRemove() {
		return remove;
	}
	
	/**
	 * Retorna el atributo Rectangle, con este se controlan las colisiones
	 * @return Rectangle (rectangleCollision)
	 */
	public Rectangle getRectangle() {
		return rectangleCollision;
	}
	
	/**
	 * Reestablece la posición del rectángulo de colisiones.
	 */
	public void setRectanglePosition() {
		rectangleCollision.setY(this.yCoord);
	}

	/**
	 * Establece el valor para remove, este hace que la bala se pueda eliminar o no
	 * @param b True / 
	 */
	public void setRemove(boolean b) {
		this.remove = true;
		
	}
	
}
