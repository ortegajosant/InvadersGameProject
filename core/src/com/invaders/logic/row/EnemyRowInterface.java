package com.invaders.logic.row;

import com.invaders.datastructures.SimpleList;
import com.invaders.game.InvadersLauncher;
import com.invaders.logic.Bullet;

public interface EnemyRowInterface {

	public void sortRow(float xCoord, int limit);

	public void makeRow();

	public void deleteEnemy(SimpleList<Bullet> bullets);

	public void deleteRow();

	public void reformRow();

	public void moveRow(float deltaTime);

	public void showRow(InvadersLauncher invaderslauncher);

}
