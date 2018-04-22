package com.invaders.levelgraphics;

import com.badlogic.gdx.Gdx;
import com.invaders.game.InvadersLauncher;

/**
 * Contiene toda la configuración del nivel 3
 * @author jorte
 *
 */
public class LevelThree extends Window {

	public LevelThree(InvadersLauncher invadersLauncher) {
		super(invadersLauncher);
		rowList = new String[]{"Class A", "Class B", "Class C", "Class D"};
		rowNumber = 5;
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
		enemyMovement = Gdx.audio.newSound(Gdx.files.internal("music/fastinvader3.ogg"));
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
