package com.dam203.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.Juego;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Marta Adventure";
		config.width = 960;
		config.height = 480;
		config.addIcon("iconos/icono.png", Files.FileType.Internal);
		config.resizable=false;
		new LwjglApplication(new Juego(), config);
	}
}
