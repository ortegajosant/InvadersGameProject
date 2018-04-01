package com.invaders.logic.row;

import com.invaders.datastructures.SimpleList;
import com.invaders.game.InvadersLauncher;
import com.invaders.levels.Window;
import com.invaders.logic.Bullet;

public abstract class AbstractEnemyRow {
	protected int speed;

	public void sortRow(float xCoord, int limit) {
		
	}

	public void makeRow(boolean newRow) {
		
	}

	public abstract int deleteEnemy(SimpleList<Bullet> bullets);

	public void deleteRow() {
		
	}

	public void moveRow(float deltaTime) {
		
	}

	public void showRow(InvadersLauncher invaderslauncher) {
		
	}
	
	public void changeBoss() {
		
	}
	
	public abstract boolean isRowEmpty();
	
	public void rowWin(Window currentWindow) {
		
	}

}
