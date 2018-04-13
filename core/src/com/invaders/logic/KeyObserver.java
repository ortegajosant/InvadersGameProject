package com.invaders.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.invaders.server.ControlServer;

public class KeyObserver {
	
	private static KeyObserver instance;
	private ControlServer wirelessControl;
	
	private KeyObserver() {
		
	}
	
	public static KeyObserver getObserverInstance() {
		if(instance == null) {
			instance = new KeyObserver();
		}
		return instance;
	}
	
	public boolean keyRight(){
		if (wirelessControl != null && wirelessControl.getMove() != 0) {
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT) 
					|| wirelessControl.getMove() == 1) {
				return true;
			}
			return false;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			return true;
		}
		return false;
	
		
	}
	
	public boolean keyLeft() {
		if (wirelessControl != null && wirelessControl.getMove() != 0) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)
					|| wirelessControl.getMove() == 2) {
				return true;
			}
			return false;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			return true;
		}
	
	return false;
		
	}
	
	public boolean keyUp() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			return true;
		}
		return false;
	}
	
	public boolean keyDown() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && !Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			return true;
		}
		return false;
	}
	
	public boolean keySpace() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			return true;
		}
		return false;
	}

	public boolean keyEnter() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			return true;
		}
		return false;
	}
	
	public void runWirelessControl() {
		wirelessControl = ControlServer.getInstance();
	}
	
}
