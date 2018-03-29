package com.invaders.levels;

import com.invaders.game.InvadersLauncher;

public class LevelFour extends Window {

	public LevelFour(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
	}
	
	@Override
	public void show() {
		super.show();
		enemiesRow = factory.createEnemyRow(4, "c");
	}

	@Override
	public void render(float delta) {
		super.renderGame();
		super.doAction();
	}

	@Override
	public void dispose() {
		this.dispose();
	}

}
