package com.invaders.levels;

import com.invaders.game.InvadersLauncher;

public class LevelOne extends Window {

	private int rowNumber;

	public LevelOne(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
		this.rowNumber = 4;
	}

	@Override
	public void show() {
		super.show();
		enemiesRow = factory.createEnemyRow(1, "Basic");
		levelNumber = 1;
		newRow = "Basic";
	}

	@Override
	public void render(float delta) {
		super.renderGame();
		super.doAction();
		nextRow();
		
	}

	public void nextRow() {
		if (rowNumber != 0) {
			if (enemiesRow.isRowEmpty()) {
				this.rowNumber--;
				enemiesRow.makeRow(false);
			}
		} else {
			nextLevel();
		}
	}
	
	@Override
	public void nextLevel() {
		this.dispose();
		invadersLauncher.setScreen(new LevelTwo(invadersLauncher));
		
	}

}
