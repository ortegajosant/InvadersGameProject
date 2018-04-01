package com.invaders.levels;

import com.invaders.game.InvadersLauncher;

public class LevelThree extends Window {

	public LevelThree(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
		rowList = new String[]{"Basic", "Class A", "Class B"};
		rowNumber = 8;
		int index = (int) (Math.random() * 3);
		lastRow = rowList[index];
		index = (int) (Math.random() * 3);
		newRow = rowList[index];
		levelNumber = 3;
	}
	
	@Override
	public void show() {
		super.show();
		enemiesRow = factory.createEnemyRow(3, lastRow);
	}

	@Override
	public void render(float delta) {
		super.renderGame();
		super.doAction();
		nextRow(3);
	}

	@Override
	public void nextLevel() {
		this.dispose();
		invadersLauncher.setScreen(new LevelFour(invadersLauncher));
	}
}
