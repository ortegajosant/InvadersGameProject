package com.invaders.logic.row;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.invaders.datastructures.SimpleList;
import com.invaders.datastructures.SimpleNode;
import com.invaders.game.InvadersLauncher;
import com.invaders.logic.Bullet;
import com.invaders.logic.Enemy;

public class ClassARow implements EnemyRowInterface {

	private SimpleList<Enemy> row;

	public ClassARow() {
		makeRow();
	}

	@Override
	public void makeRow() {
		row = new SimpleList<>();
		float xCoord = 51;
		int randomIndex = (int) (Math.random() * 11);
		int strength = (int) (Math.random() * 4) + 2;
		System.out.println(randomIndex + " " + strength);
		for (int i = 0; i < 11; i++) {
			if (i == randomIndex) {
				row.add(new SimpleNode<Enemy>(new Enemy(strength, new Texture("images/enemy2.png"), xCoord, 490, 40, true, true)));
			} else {
				row.add(new SimpleNode<Enemy>(new Enemy(1, new Texture("images/enemy3.png"), xCoord, 490, 40, false, true)));
			}
			xCoord += 65;
		}
	}

	@Override
	public void deleteEnemy(SimpleList<Bullet> bullets) {
		if (bullets.getLength() > 0 && row.getLength() > 0) {
			for (int i = 0; i < bullets.getLength(); i++) {
				for (int j = 0; j < row.getLength(); j++) {
					if (bullets.find(i).getRectangle().overlaps(row.find(j).getRectangle())) {
						bullets.find(i).setRemove(true);
						float xCoord = row.getFirst().getDato().getXCoord();
						Enemy temp = row.find(j);
						temp.reduceStrength();
						if (temp.getStrength() == 0) {
							if (temp.getIsBoss()) {
								deleteRow();
							} else {
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
	}

	@Override
	public void deleteRow() {
		row.erase();
	}

	@Override
	public void reformRow() {
		// TODO Auto-generated method stub

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

	@Override
	public void changeBoss() {
		// TODO Auto-generated method stub
		
	}
}
