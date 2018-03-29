package com.invaders.levels;

import com.invaders.game.InvadersLauncher;

public class LevelThree extends Window {

	public LevelThree(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
	}
	
	@Override
	public void show() {
		super.show();
		enemiesRow = factory.createEnemyRow(3, "b");
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
