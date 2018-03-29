package com.invaders.logic.row;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.invaders.datastructures.SimpleList;
import com.invaders.datastructures.SimpleNode;
import com.invaders.game.InvadersLauncher;
import com.invaders.logic.Bullet;
import com.invaders.logic.Enemy;

public class BasicRow implements EnemyRowInterface {

	private SimpleList<Enemy> row;

	public BasicRow() {
		makeRow();
	}

	/**
	 * Reacomoda los enemigos en la hilera
	 */
	@Override
	public void sortRow(float xCoord, int limit) {
		float firstXCoord = xCoord;
		for (int i = 0; i < limit; i++) {
			row.find(i).setXCoord(firstXCoord);
			firstXCoord += 65;
		}
	}

	/**
	 * Crea nueva hilera.
	 */
	@Override
	public void makeRow() {
		row = new SimpleList<>();
		float xCoord = 51;
		for (int i = 0; i < 11; i++) {
			row.add(new SimpleNode<Enemy>(new Enemy(0, new Texture("images/enemy3.png"), xCoord, 490, 30)));
			xCoord += 65;
		}
	}

	@Override
	/**
	 * Elimina enemigos de la hilera y del mapa
	 */
	public void deleteEnemy(SimpleList<Bullet> bullets) {
		if (bullets.getLength() > 0 && row.getLength() > 0) {
			for (int i = 0; i < bullets.getLength(); i++) {
				for (int j = 0; j < row.getLength(); j++) {
					if (bullets.find(i).getRectangle().overlaps(row.find(j).getRectangle())) {
						bullets.find(i).setRemove(true);
						row.remove(row.find(j));
						if (row.getLength() > 1) {
							sortRow(row.getFirst().getDato().getXCoord() + 32f, row.getLength());
						}
						break;
					}
				}
			}
		}
	}

	@Override
	/**
	 * Elimina la hilera por completo.
	 */
	public void deleteRow() {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Recrea la hilera de nuevo.
	 */
	public void reformRow() {
		float xCoord = 51;
		for (int i = 0; i < 11; i++) {
			row.add(new SimpleNode<Enemy>(new Enemy(0, new Texture("images/enemy3.png"), xCoord, 490, 30)));
			xCoord += 65;
		}
	}

	@Override
	public void showRow(InvadersLauncher invadersLauncher) {
		if (row.getLength() > 0) {
			for (int i = 0; i < row.getLength(); i++) {
				row.find(i).render(invadersLauncher.batch);
			}
		}

	}

	public SimpleList<Enemy> getRow() {
		return row;
	}

	@Override
	public void moveRow(float deltaTime) {
		if (row.getLength() > 0) {
			if (row.find(row.getLength() - 1).getXCoord() < Gdx.graphics.getWidth() - 42
					&& row.find(0).getXCoord() > 10) {
				for (int i = 0; i < row.getLength(); i++) {
					row.find(i).move(deltaTime, false);
				}
			} else {
				for (int i = 0; i < row.getLength(); i++) {
					row.find(i).move(deltaTime, true);
				}
			}
		}

	}

}