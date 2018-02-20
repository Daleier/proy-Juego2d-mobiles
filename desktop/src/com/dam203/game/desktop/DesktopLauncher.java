package com.dam203.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.Juego;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Juego(), config);
		config.title = "Juego 29BPDJ";
		config.width = 960;
		config.height = 480;
		config.resizable=false;
	}
}
