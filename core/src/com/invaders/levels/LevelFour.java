package com.invaders.levels;

import com.badlogic.gdx.Gdx;
import com.invaders.game.InvadersLauncher;

public class LevelFour extends Window {

	public LevelFour(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
		rowList = new String[] { "Basic", "Class A", "Class B", "Class C", "Class D", "Class E" };
		rowNumber = 12;
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
		enemyMovement = Gdx.audio.newSound(Gdx.files.internal("music/fastinvader4.ogg"));
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
		invadersLauncher.setScreen(new MainMenu(invadersLauncher));
	}

}
