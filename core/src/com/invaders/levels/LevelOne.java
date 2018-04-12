package com.invaders.levels;

import com.badlogic.gdx.Gdx;
import com.invaders.game.InvadersLauncher;

public class LevelOne extends Window {

	public LevelOne(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
		rowList = new String[]{"Basic", "Class A"};
		rowNumber = 4;
		int index = (int) (Math.random() * rowList.length);
		lastRow = rowList[index];
		index = (int) (Math.random() * rowList.length);
		newRow = rowList[index];
		levelNumber = 1;
	}

	@Override
	public void show() {
		super.show();
		enemiesRow = factory.createEnemyRow(2, lastRow);
		enemyMovement =  Gdx.audio.newSound(Gdx.files.internal("music/fastinvader1.ogg"));
		scoreGame = 0;
	}

	@Override
	public void render(float delta) {
		super.renderGame();
		super.doAction();
		nextRow(1);
		
	}
	
	@Override
	public void nextLevel() {
		this.dispose();
		invadersLauncher.setScreen(new LevelTwo(invadersLauncher));
	}

}
