package com.invaders.rowlogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.invaders.datastructures.CircularDoubleList;
import com.invaders.datastructures.SimpleList;
import com.invaders.datastructures.DoubleNode;
import com.invaders.game.InvadersLauncher;
import com.invaders.levelgraphics.Window;
import com.invaders.logic.Bullet;
import com.invaders.logic.Enemy;

public class ClassERow extends AbstractEnemyRow {
	private CircularDoubleList<Enemy> row;
	private int quadrant;
	private float rotationTime = 0;

	public ClassERow(int speed) {
		this.speed = speed;
		makeRow(true);
	}

	@Override
	public void makeRow(boolean newRow) {
		if (newRow) {
			row = new CircularDoubleList<>();
		}
		float xCoord = 251;
		int strength = (int) (Math.random() * 4) + 2;
		for (int i = 0; i < 9; i++) {
			if (i == 4) {
				row.add(new DoubleNode<Enemy>(new Enemy(strength, new Texture("images/enemy2.png"), xCoord,
						Gdx.graphics.getHeight() - 150, speed, true, true, 50)));
			} else {
				row.add(new DoubleNode<Enemy>(new Enemy(1, new Texture("images/enemy3.png"), xCoord,
						Gdx.graphics.getHeight() - 150, speed, false, true, 20)));
			}
			xCoord += 65;
		}
		quadrant = 2;
	}

	@Override
	public int deleteEnemy(SimpleList<Bullet> bullets) {
		int score = 0;
		if (bullets.getLength() > 0 && row.getLength() > 0) {
			for (int i = 0; i < bullets.getLength(); i++) {
				for (int j = 0; j < row.getLength(); j++) {
					if (bullets.find(i).getRectangle().overlaps(row.find(j).getRectangle())) {
						bullets.find(i).setRemove(true);
						Enemy temp = row.find(j);
						temp.reduceStrength();
						float xCoord = row.getFirst().getDato().getXCoord();
						float yCoord = row.getFirst().getDato().getYCoord();
						if (temp.getStrength() == 0) {
							if (temp.getIsBoss()) {
								score = temp.getScore();
								row.remove(temp);
							} else {
								score = temp.getScore();
								row.remove(temp);
							}
							if (row.getLength() >= 1) {
								sortRow(xCoord, yCoord, row.getLength());
								changeBoss();
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
	public void deleteRow() {
		row.erase();
	}

	@Override
	public void moveRow(float deltaTime) {
		if (rotationTime >= 0.1f) {
			rotateAction(deltaTime);
			rotationTime = 0;
		}
		rotationTime += deltaTime;
		if (row.getLength() > 0) {
			if (row.find((int) row.getLength() / 2).getXCoord() < Gdx.graphics.getWidth()
					- (32 + 50 * (row.getLength() / 2))
					&& row.find((int) row.getLength() / 2).getXCoord() > 50 * (row.getLength() / 2)) {
				for (int i = 0; i < row.getLength(); i++) {
					row.find(i).move(deltaTime, false);
				}
			} else {
				for (int i = 0; i < row.getLength(); i++) {
					row.find(i).move(deltaTime, true);
				}

			}
		}
		else {
			deleteRow();
		}

	}

	/**
	 * Realiza el movimiento circular de la hilera
	 * 
	 * @param deltatime
	 *            (Float) Tiempo entre un frame y otro
	 */
	public void rotateAction(float deltatime) {
		int mitad = row.getLength() / 2;
		float xRadialCoord = (float) (65 * Math.sin(Math.PI / 3) * deltatime);
		float yRadialCoord = (float) (65 * Math.cos(Math.PI / 3) * deltatime);
		for (int i = 0; i < row.getLength(); i++) {
			Enemy enemy = row.find(i);
			if (row.getFirst().getDato().getXCoord() < row.find(mitad).getXCoord()
					&& row.getFirst().getDato().getYCoord() >= row.find(mitad).getYCoord()) {
				quadrant = 2;
				if (i < mitad) {
					enemy.setXCoord(enemy.getXCoord() + xRadialCoord * (mitad - i));
					enemy.setYCoord(enemy.getYCoord() + yRadialCoord * (mitad - i));
				} else if (i > mitad) {
					enemy.setXCoord(enemy.getXCoord() - xRadialCoord * (i - mitad));
					enemy.setYCoord(enemy.getYCoord() - yRadialCoord * (i - mitad));
				}
			} else if (row.getFirst().getDato().getXCoord() >= row.find(mitad).getXCoord()
					&& row.getFirst().getDato().getYCoord() > row.find(mitad).getYCoord()) {
				quadrant = 1;
				if (i < mitad) {
					enemy.setXCoord(enemy.getXCoord() + xRadialCoord * (mitad - i));
					enemy.setYCoord(enemy.getYCoord() - yRadialCoord * (mitad - i));
				} else if (i > mitad) {
					enemy.setXCoord(enemy.getXCoord() - xRadialCoord * (i - mitad));
					enemy.setYCoord(enemy.getYCoord() + yRadialCoord * (i - mitad));
				}
			} else if (row.getFirst().getDato().getXCoord() >= row.find(mitad).getXCoord()
					&& row.getFirst().getDato().getYCoord() < row.find(mitad).getYCoord()) {
				quadrant = 4;
				if (i < mitad) {
					enemy.setXCoord(enemy.getXCoord() - xRadialCoord * (mitad - i));
					enemy.setYCoord(enemy.getYCoord() - yRadialCoord * (mitad - i));
				} else if (i > mitad) {
					enemy.setXCoord(enemy.getXCoord() + xRadialCoord * (i - mitad));
					enemy.setYCoord(enemy.getYCoord() + yRadialCoord * (i - mitad));
				}
			} else {
				quadrant = 3;
				if (i < mitad) {
					enemy.setXCoord(enemy.getXCoord() - xRadialCoord * (mitad - i));
					enemy.setYCoord(enemy.getYCoord() + yRadialCoord * (mitad - i));
				} else if (i > mitad) {
					enemy.setXCoord(enemy.getXCoord() + xRadialCoord * (i - mitad));
					enemy.setYCoord(enemy.getYCoord() - yRadialCoord * (i - mitad));
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

	/**
	 * Realiza el reordenamiento de los enemigos
	 * 
	 * @param xCoord
	 *            Posición en x inicial
	 * @param yCoord
	 *            Posición en y inicial
	 * @param limit
	 *            límite de enemigos a ordenar
	 */
	public void sortRow(float xCoord, float yCoord, int limit) {
		float deltatime = Gdx.graphics.getDeltaTime();
		float yCoordDistance = (float) (950 * Math.cos(Math.PI / 3) * deltatime);
		float xCoordDistance = (float) (950 * Math.sin(Math.PI / 3) * deltatime);
		if (quadrant == 2) {
			float firstXCoord = xCoord + xCoordDistance;
			float firstYCoord = yCoord;
			for (int i = 0; i < limit; i++) {
				row.find(i).setXCoord(firstXCoord);
				row.find(i).setYCoord(firstYCoord);
				firstXCoord += xCoordDistance;
				firstYCoord -= yCoordDistance;
			}
		} else if (quadrant == 1) {
			float firstXCoord = xCoord - xCoordDistance;
			float firstYCoord = yCoord;
			for (int i = 0; i < limit; i++) {
				row.find(i).setXCoord(firstXCoord);
				row.find(i).setYCoord(firstYCoord);
				firstXCoord -= xCoordDistance;
				firstYCoord -= yCoordDistance;
			}
		} else if (quadrant == 4) {
			float firstXCoord = xCoord - xCoordDistance;
			float firstYCoord = yCoord;
			for (int i = 0; i < limit; i++) {
				row.find(i).setXCoord(firstXCoord);
				row.find(i).setYCoord(firstYCoord);
				firstXCoord -= xCoordDistance;
				firstYCoord += yCoordDistance;
			}
		} else if (quadrant == 3) {
			float firstXCoord = xCoord + xCoordDistance;
			float firstYCoord = yCoord;
			for (int i = 0; i < limit; i++) {
				row.find(i).setXCoord(firstXCoord);
				row.find(i).setYCoord(firstYCoord);
				firstXCoord += xCoordDistance;
				firstYCoord += yCoordDistance;
			}
		}

	}

	@Override
	public void changeBoss() {
		if (!row.find((int) (row.getLength() / 2)).getIsBoss()) {
			int strength = 0;
			for (int i = 0; i < row.getLength(); i++) {
				if (row.find(i).getIsBoss()) {
					strength = row.find(i).getStrength();
					row.find(i).setBoss(false);
					row.find(i).setStrength(1);
					row.find(i).setTexture(new Texture("images/enemy3.png"));
					break;
				}
			}
			if (strength == 0) {
				strength = (int) (Math.random() * 4 + 2);
			}

			row.find((int) (row.getLength() / 2)).setBoss(true);
			row.find((int) (row.getLength() / 2)).setStrength(strength);
			row.find((int) (row.getLength() / 2)).setTexture(new Texture("images/enemy2.png"));
		}
	}

	public boolean isRowEmpty() {
		System.out.println(row.getLength());
		return row.isEmpty();
	}

	@Override
	public void rowWin(Window currentWindow) {
		for (int i = 0; i < row.getLength(); i++) {
			if (row.find(i).getYCoord() < 50) {
				currentWindow.finishGame(currentWindow);
			}
		}
	}
}
