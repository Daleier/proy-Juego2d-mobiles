package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by dalei on 08/03/2018.
 */

public class Audio {
    public static Sound soundCoin;
    public static Sound soundZombie;
    public static Sound soundButton;
    public static Sound soundDead;
    public static Music musicaJuego;

    public static void cargarAudio(){
        soundCoin = Gdx.audio.newSound(Gdx.files.internal("audio/coin.mp3"));
        soundDead = Gdx.audio.newSound(Gdx.files.internal("audio/wscream.wav"));
        soundZombie = Gdx.audio.newSound(Gdx.files.internal("audio/zombie.mp3"));
        soundButton = Gdx.audio.newSound(Gdx.files.internal("audio/button.mp3"));
        musicaJuego = Gdx.audio.newMusic(Gdx.files.internal("audio/towntheme.mp3"));
        musicaJuego.setLooping(true);
    }

    public static void dispose(){
        soundCoin.dispose();
        soundDead.dispose();
        soundZombie.dispose();
        soundButton.dispose();
        musicaJuego.dispose();
    }
}
