package game;

import com.badlogic.gdx.Game;

import pantallas.PantallaJuego;


public class Juego extends Game {

	@Override
	public void create () {
		Utiles.imprimirLog("Juego","Constructor","Creado objeto juego");
		setScreen(new PantallaJuego(this));
	}


	@Override
	public void dispose () {
		super.dispose();

	}
}
