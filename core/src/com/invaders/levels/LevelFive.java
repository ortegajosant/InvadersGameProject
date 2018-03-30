package com.invaders.levels;

import com.invaders.game.InvadersLauncher;

public class LevelFive extends Window {

	public LevelFive(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
	}

	@Override
	public void show() {
		super.show();
		enemiesRow = factory.createEnemyRow(5, "d");
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
