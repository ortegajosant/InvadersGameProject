package com.invaders.rowlogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.invaders.datastructures.CircularList;
import com.invaders.datastructures.SimpleList;
import com.invaders.datastructures.SimpleNode;
import com.invaders.game.InvadersLauncher;
import com.invaders.levelgraphics.Window;
import com.invaders.logic.Bullet;
import com.invaders.logic.Enemy;

/**
 * Contiene toda la lógica y funcionamiento para la hilera Clase D
 * @author jorte
 *
 */
public class ClassDRow extends AbstractEnemyRow {

	private CircularList<Enemy> row;

	public ClassDRow(int speed) {
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
		int strengthBoss = (int) (Math.random() * 4) + 2;
		int strength = (int) (Math.random() * 4) + 2;
		System.out.println(randomIndex + " " + strengthBoss);
		for (int i = 0; i < 11; i++) {
			if (i == randomIndex) {
				row.add(new SimpleNode<Enemy>(
						new Enemy(strengthBoss, new Texture("images/enemy2.png"), xCoord, 420, speed, true, true, 50)));
			} else {
				row.add(new SimpleNode<Enemy>(
						new Enemy(strength, new Texture("images/enemy1.png"), xCoord, 420, speed, false, true, 30)));
				strength = (int) (Math.random() * 5) + 1;
			}
			xCoord += 65;
		}
		bubbleSortRow();
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
								bubbleSortRow();
								sortRow(xCoord + 32.5f, row.getLength());
							}
						} else if (row.getLength() > 1) {
							bubbleSortRow();
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

	/**
	 * Realiza el ordenamiento para los enemigos, se ordenan de mayor a menor resistencia
	 */
	public void bubbleSortRow() {
		int number = row.getLength();
		for (int i = 0; i < number - 1; i++) {
			for (int j = 0; j < (number - i) - 1; j++) {
				if (row.find(j).getStrength() < row.find(j + 1).getStrength()) {
					
					Enemy current = row.find(j);
					
					Enemy next = row.find(j + 1);

					float xCoordCurrent = current.getXCoord();

					float xCoordNext = next.getXCoord();

					float yCoordCurrent = current.getYCoord();

					float yCoordNext = next.getYCoord();

					boolean directionCurrent = current.getDirection();
					boolean directionNext = next.getDirection();

					int strengthCurrent = current.getStrength();

					int strengthNext = next.getStrength();

					if (current.getIsBoss()) {

						row.replace(j + 1,
								new SimpleNode<Enemy>(new Enemy(strengthCurrent, new Texture("images/enemy2.png"),
										xCoordNext, yCoordNext, speed, true, directionCurrent, 50)));

						row.replace(j, new SimpleNode<Enemy>(new Enemy(strengthNext, new Texture("images/enemy1.png"),
								xCoordCurrent, yCoordCurrent, speed, false, directionNext, 30)));

					} else if (next.getIsBoss()) {

						row.replace(j + 1,
								new SimpleNode<Enemy>(new Enemy(strengthCurrent, new Texture("images/enemy1.png"),
										xCoordNext, yCoordNext, speed, false, directionCurrent, 30)));

						row.replace(j, new SimpleNode<Enemy>(new Enemy(strengthNext, new Texture("images/enemy2.png"),
								xCoordCurrent, yCoordCurrent, speed, true, directionNext, 50)));

					} else {
						row.replace(j + 1,
								new SimpleNode<Enemy>(new Enemy(strengthCurrent, new Texture("images/enemy1.png"),
										xCoordNext, yCoordNext, speed, false, directionCurrent, 30)));

						row.replace(j, new SimpleNode<Enemy>(new Enemy(strengthNext, new Texture("images/enemy1.png"),
								xCoordCurrent, yCoordCurrent, speed, false, directionNext, 30)));
					}
				}
			}
		}
		System.out.println("Nuevo orden");
		for (int i = 0; i < row.getLength(); i++) {
			System.out.print(row.find(i).getStrength() + " ");
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

	/**
	 * Mismo funcionamiento de changeBoss de la clase padre pero con Overload
	 * @param toDelete Enemy
	 */
	public void changeBoss(Enemy toDelete) {
		if (row.getLength() > 1) {
			row.remove(toDelete);
			int indexNewBoss = (int) (Math.random() * row.getLength());
			float xCoordNewBoss = row.find(indexNewBoss).getXCoord();
			float yCoordNewBoss = row.find(indexNewBoss).getYCoord();
			boolean direction = row.find(indexNewBoss).getDirection();
			int strength = (int) ((Math.random() * 4) + 2);
			row.replace(indexNewBoss, new SimpleNode<Enemy>(new Enemy(strength, new Texture("images/enemy2.png"),
					xCoordNewBoss, yCoordNewBoss, speed, true, direction, 30)));
		} else {
			deleteRow();
		}
	}

	public boolean isRowEmpty() {
		return row.isEmpty();
	}
	
	@Override
	public void rowWin(Window currentWindow) {
		if (row.getLast().getDato().getYCoord() < 50) {
			currentWindow.finishGame(currentWindow);
		}
	}
}
