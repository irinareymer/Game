package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import Controller.MyGdxGame;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "RODDERBY";
		config.width = 1280;
		config.height = 720;
		config.resizable = false;
		config.forceExit = false;

		new LwjglApplication(new MyGdxGame(), config);
	}
}
