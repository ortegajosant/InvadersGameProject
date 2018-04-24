package com.invaders.rowlogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

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
	
	public void update(float deltaTime) {
		yCoord -= 150*deltaTime;
		if (this.yCoord <= 0) {
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
