package com.invaders.logic.row;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.invaders.datastructures.CircularList;
import com.invaders.datastructures.SimpleList;
import com.invaders.datastructures.SimpleNode;
import com.invaders.game.InvadersLauncher;
import com.invaders.levels.Window;
import com.invaders.logic.Bullet;
import com.invaders.logic.Enemy;

public class ClassCRow extends AbstractEnemyRow {
	private CircularList<Enemy> row;

	public ClassCRow(int speed) {
		this.speed = speed;
		makeRow(true);
	}

	@Override
	public void makeRow(boolean newRow) {
		if (newRow) {
			row = new CircularList<>();
		}
		float xCoord = 51;
		int randomIndex = (int) (Math.random() * 11);
		int strength = (int) (Math.random() * 4) + 2;
		System.out.println(randomIndex + " " + strength);
		for (int i = 0; i < 11; i++) {
			if (i == randomIndex) {
				row.add(new SimpleNode<Enemy>(
						new Enemy(strength, new Texture("images/enemy2.png"), xCoord, 420, speed, true, true, 50)));
			} else {
				row.add(new SimpleNode<Enemy>(
						new Enemy(1, new Texture("images/enemy3.png"), xCoord, 420, speed, false, true, 20)));
			}
			xCoord += 65;
		}
	}

	@Override
	public int deleteEnemy(SimpleList<Bullet> bullets) {
		int score = 0;
		if (bullets.getLength() > 0 && row.getLength() > 0) {
			for (int i = 0; i < bullets.getLength(); i++) {
				for (int j = 0; j < row.getLength(); j++) {
					if (bullets.find(i).getRectangle().overlaps(row.find(j).getRectangle())) {
						bullets.find(i).setRemove(true);
						float xCoord = row.getLast().getDato().getXCoord();
						Enemy temp = row.find(j);
						temp.reduceStrength();
						if (temp.getStrength() == 0) {
							if (temp.getIsBoss()) {
								score = temp.getScore();
								changeBoss(temp);
							} else {
								score = temp.getScore();
								row.remove(temp);
							}
							if (row.getLength() > 1) {
								sortRow(xCoord + 32.5f, row.getLength());
							}
						}
						break;
					}
				}
			}
		}
		return score;
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

	@Override
	public void showRow(InvadersLauncher invadersLauncher) {
		if (row.getLength() > 0) {
			for (int i = 0; i < row.getLength(); i++) {
				row.find(i).render(invadersLauncher.batch);
			}
		}
	}

	@Override
	public void sortRow(float xCoord, int limit) {
		float firstXCoord = xCoord;
		for (int i = 0; i < limit; i++) {
			row.find(i).setXCoord(firstXCoord);
			firstXCoord += 65;
		}
	}

	public void changeBoss(Enemy toDelete) {
		if (row.getLength() > 1) {
			row.remove(toDelete);
			int indexNewBoss = (int) (Math.random() * row.getLength());
			float xCoordNewBoss = row.find(indexNewBoss).getXCoord();
			float yCoordNewBoss = row.find(indexNewBoss).getYCoord();
			boolean direction = row.find(indexNewBoss).getDirection();
			int strength = (int) ((Math.random() * 4) + 2);
			row.replace(indexNewBoss, new SimpleNode<Enemy>(new Enemy(strength, new Texture("images/enemy2.png"),
					xCoordNewBoss, yCoordNewBoss, speed, true, direction, 50)));
		} else {
			deleteRow();
		}
	}

	public void deleteRow() {
		row.erase();
	}

	public boolean isRowEmpty() {
		return row.isEmpty();
	}
	
	@Override
	public void rowWin(Window currentWindow) {
		if (row.getLast().getDato().getYCoord() < 0) {
			currentWindow.finishGame(currentWindow);
		}
	}
}
