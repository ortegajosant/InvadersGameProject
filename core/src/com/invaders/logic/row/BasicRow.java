package com.invaders.logic.row;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.invaders.datastructures.SimpleList;
import com.invaders.datastructures.SimpleNode;
import com.invaders.game.InvadersLauncher;
import com.invaders.levels.Window;
import com.invaders.logic.Bullet;
import com.invaders.logic.Enemy;

public class BasicRow extends AbstractEnemyRow {

	private SimpleList<Enemy> row;

	public BasicRow(int speed) {
		super.speed = speed;
		makeRow(true);
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
	public void makeRow(boolean newRow) {
		if (newRow) {
			row = new SimpleList<>();
		}
		float xCoord = 51;
		for (int i = 0; i < 11; i++) {
			row.add(new SimpleNode<Enemy>(
					new Enemy(0, new Texture("images/enemy3.png"), xCoord, 420, super.speed, false, true, 20)));
			xCoord += 65;
		}
	}

	@Override
	/**
	 * Elimina enemigos de la hilera y del mapa
	 */
	public int deleteEnemy(SimpleList<Bullet> bullets) {
		int score = 0;
		if (bullets.getLength() > 0 && row.getLength() > 0) {
			for (int i = 0; i < bullets.getLength(); i++) {
				for (int j = 0; j < row.getLength(); j++) {
					if (bullets.find(i).getRectangle().overlaps(row.find(j).getRectangle())) {
						bullets.find(i).setRemove(true);
						float xCoord = row.getFirst().getDato().getXCoord();
						score = row.find(j).getScore();
						row.remove(row.find(j));
						if (row.getLength() > 1) {
							sortRow(xCoord + 32.5f, row.getLength());
						}
						break;
					}
				}
			}
		}
		return score;
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

	public boolean isRowEmpty() {
		return row.isEmpty();
	}

	public void rowWin(Window currentWindow) {
		if (row.getFirst().getDato().getYCoord() < 50) {
			currentWindow.finishGame(currentWindow);
		}
	}
}
