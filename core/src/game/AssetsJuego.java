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
	public static Texture pause;
	public static Texture menu;
	public static Texture sound;
	public static Texture sound_inactive;
	public static Texture leftarrow;
	public static Texture rightarrow;
	public static Texture uparrow;
	public static Texture timer;
	public static Texture health;
	public static Texture icoCoin;

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
		zombieMale = new Animation(0.3f, zombiem);

		atlas = new TextureAtlas("personajes/zombies/zombief.txt");
		Array<Sprite> zombief = atlas.createSprites();
		zombieFemale = new Animation(0.3f, zombief);

		atlas = new TextureAtlas("personajes/coins/coin.txt");
		Array<Sprite> coins = atlas.createSprites();
		coin = new Animation(0.5f, coins);

		FileHandle imageFileHandle = Gdx.files.internal("iconos/pause.png");
		pause = new Texture(imageFileHandle);

		imageFileHandle = Gdx.files.internal("iconos/menue.png");
		menu = new Texture(imageFileHandle);

		imageFileHandle = Gdx.files.internal("iconos/sound.png");
		sound = new Texture(imageFileHandle);

		imageFileHandle = Gdx.files.internal("iconos/sound_inactive.png");
		sound_inactive = new Texture(imageFileHandle);

		imageFileHandle = Gdx.files.internal("iconos/leftarrow.png");
		leftarrow = new Texture(imageFileHandle);

		imageFileHandle = Gdx.files.internal("iconos/rightarrow.png");
		rightarrow = new Texture(imageFileHandle);

		imageFileHandle = Gdx.files.internal("iconos/uparrow.png");
		uparrow = new Texture(imageFileHandle);

		imageFileHandle = Gdx.files.internal("iconos/timer.png");
		timer = new Texture(imageFileHandle);

		imageFileHandle = Gdx.files.internal("iconos/health.png");
		health = new Texture(imageFileHandle);

		imageFileHandle = Gdx.files.internal("iconos/ico_coin.png");
		icoCoin = new Texture(imageFileHandle);
	}


	/**
	 * Método encargado de liberar todas as texturas
	 */
	public static void liberarTexturas(){
		atlas.dispose();
		pause.dispose();
		menu.dispose();
		sound.dispose();
		sound_inactive.dispose();
		leftarrow.dispose();
		rightarrow.dispose();
		uparrow.dispose();
		timer.dispose();
		health.dispose();
		icoCoin.dispose();
	}

}