package com.invaders.levels;

import com.invaders.game.InvadersLauncher;

public class LevelFive extends Window {

	public LevelFive(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
		rowList = new String[]{"Class A", "Class B", "Class C", "Class D"};
		rowNumber = 10;
		int index = (int) (Math.random() * 3);
		lastRow = rowList[index];
		index = (int) (Math.random() * 3);
		newRow = rowList[index];
		levelNumber = 5;
	}

	@Override
	public void show() {
		super.show();
		enemiesRow = factory.createEnemyRow(5, lastRow);
	}

	@Override
	public void render(float delta) {
		super.renderGame();
		super.doAction();
		nextRow(5);
	}


	@Override
	public void nextLevel() {
		this.dispose();
		invadersLauncher.setScreen(new LevelSix(invadersLauncher));
	}

}
