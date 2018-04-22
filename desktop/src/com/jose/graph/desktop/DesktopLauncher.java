package com.jose.graph.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.invaders.game.InvadersLauncher;

/**
 * Launcher principal del juego - Desde aquí se hace la llamada a InvadersGameLauncher
 * @author jorte
 *
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Invaders";
		config.height = 500;
		config.width = 800;
		config.fullscreen = false;
		config.resizable = false;
		config.x = -1;
		config.y = -1;
		new LwjglApplication(new InvadersLauncher(), config);
		
		
	}
}
