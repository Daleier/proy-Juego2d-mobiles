package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import pantallas.PantallaJuego;


public class Juego extends Game {

	@Override
	public void create () {
		Utiles.imprimirLog("Juego","Constructor","Creado objeto juego");
		setScreen(new PantallaJuego(this));
		AssetsJuego.cargarTexturas();
	}


	@Override
	public void dispose () {
		AssetsJuego.liberarTexturas();
		super.dispose();
	}
}
