package com.invaders.levels;

import com.invaders.game.InvadersLauncher;

public class LevelSix extends Window{
	
	public LevelSix(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
		rowList = new String[]{"Basic", "Class A", "Class B", "Class C", "Class D", "Class E"};
		rowNumber = 12;
		int index = (int) (Math.random() * 3);
		lastRow = rowList[index];
		index = (int) (Math.random() * 3);
		newRow = rowList[index];
		levelNumber = 6;
	}

	@Override
	public void show() {
		super.show();
		enemiesRow = factory.createEnemyRow(6, "e");
	}

	@Override
	public void render(float delta) {
		super.renderGame();
		super.doAction();
	}

	@Override
	public void nextLevel() {
		this.dispose();
		invadersLauncher.setScreen(new MainMenu(invadersLauncher));
	}

}
