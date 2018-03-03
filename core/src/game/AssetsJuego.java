package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;


/**
 * Created by dam203 on 20/02/2018.
 */

public class AssetsJuego {

	private static TextureAtlas atlas;
	public static Animation pjIdle;
	public static Animation pjJumping;
	public static Animation pjFalling;
	public static Animation pjRunning;
	public static Animation zombieMale;
	public static Animation zombieFemale;
	public static Animation coin;

	public static void cargarTexturas(){

		atlas = new TextureAtlas("personajes/prota/idle/idle.txt");
		Array<Sprite> idle = atlas.createSprites();
		pjIdle = new Animation(0.5f, idle);

		atlas = new TextureAtlas("personajes/prota/jump/jump.txt");
		Array<Sprite> jumping = atlas.createSprites();
		pjJumping = new Animation(0.5f, jumping);

		atlas = new TextureAtlas("personajes/prota/fall/fall.txt");
		Array<Sprite> falling = atlas.createSprites();
		pjFalling = new Animation(0.5f, falling);

		atlas = new TextureAtlas("personajes/prota/run/run.txt");
		Array<Sprite> running = atlas.createSprites();
		pjRunning = new Animation(0.5f, running);

		atlas = new TextureAtlas("personajes/zombies/zombiem.txt");
		Array<Sprite> zombiem = atlas.createSprites();
		zombieMale = new Animation(0.5f, zombiem);

		atlas = new TextureAtlas("personajes/zombies/zombief.txt");
		Array<Sprite> zombief = atlas.createSprites();
		zombieFemale = new Animation(0.5f, zombief);

		atlas = new TextureAtlas("personajes/coins/coin.txt");
		Array<Sprite> coins = atlas.createSprites();
		coin = new Animation(0.5f, coins);
	}


	/**
	 * Método encargado de liberar todas as texturas
	 */
	public static void liberarTexturas(){
		atlas.dispose();
	}

}