package com.invaders.levels;

import com.invaders.game.InvadersLauncher;

public class LevelOne extends Window {

	public LevelOne(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
	}

	@Override
	public void show() {
		super.show();
		enemiesRow = factory.createEnemyRow(1, "basic");
	}

	@Override
	public void render(float delta) {
		super.renderGame();
		doAction();
	}

	@Override
	public void dispose() {
		this.dispose();
	}
}
