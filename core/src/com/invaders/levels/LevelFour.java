package com.invaders.levels;

import com.invaders.game.InvadersLauncher;

public class LevelFour extends Window {

	public LevelFour(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
		rowList = new String[]{"Class A", "Class B", "Class C"};
		rowNumber = 8;
		int index = (int) (Math.random() * 3);
		lastRow = rowList[index];
		index = (int) (Math.random() * 3);
		newRow = rowList[index];
		levelNumber = 4;
	}
	
	@Override
	public void show() {
		super.show();
		enemiesRow = factory.createEnemyRow(4, lastRow);
	}

	@Override
	public void render(float delta) {
		super.renderGame();
		super.doAction();
		nextRow(4);
	}

	@Override
	public void nextLevel() {
		this.dispose();
		invadersLauncher.setScreen(new LevelFive(invadersLauncher));
	}

}
