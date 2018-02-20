package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dam203 on 20/02/2018.
 */

public class AssetsJuego {

	public static Texture texturePJ;

	public static void cargarTexturas(){
		FileHandle imageFileHandle = Gdx.files.internal("personajes/adventurer_stand.png");
		texturePJ = new Texture(imageFileHandle);

	}


	/**
	 * Método encargado de liberar todas as texturas
	 */
	public static void liberarTexturas(){
		texturePJ.dispose();
	}

}
