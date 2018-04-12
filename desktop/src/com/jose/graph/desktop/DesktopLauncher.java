package com.jose.graph.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.invaders.game.InvadersLauncher;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Invaders";
		config.height = 500;
		config.width = 800;
		config.fullscreen = false;
		config.resizable = true;
		config.x = -1;
		config.y = -1;
		new LwjglApplication(new InvadersLauncher(), config);
		
		
	}
}
