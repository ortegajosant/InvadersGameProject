package com.invaders.levels;

import com.invaders.game.InvadersLauncher;

public class LevelTwo extends Window {

	public LevelTwo(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
	}
	
	@Override
	public void show() {
		super.show();
		enemiesRow = factory.createEnemyRow(2, "a");
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
