package pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import javax.rmi.CORBA.Util;

import controlador.Controlador;
import controlador.ControladorContact;
import game.Audio;
import game.HighScores;
import game.Utiles;
import modelo.Hud;
import modelo.Mundo;
import renderer.Renderer;

/**
 * Created by dalei on 13/02/2018.
 */

public class PantallaJuego implements Screen, InputProcessor{

    public static boolean pause;
    public static boolean gameOver;
    private Game game;
    private Renderer renderer;
    private Mundo mundo;
    private Controlador controlador;

    public PantallaJuego(Game game){
		Utiles.imprimirLog("PantallaJuego","Constructor","Creado objeto PantallaJuego");
		this.game = game;
        mundo = new Mundo();
        this.renderer = new Renderer(mundo);
        this.controlador = new Controlador(mundo);
        Audio.musicaJuego.play();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Input.Keys.SPACE:
            case Input.Keys.W:
			case Input.Keys.UP:
				controlador.pulsarTecla(Controlador.Keys.ARRIBA);
				break;
            case Input.Keys.A:
			case Input.Keys.LEFT:
				controlador.pulsarTecla(Controlador.Keys.IZQUIERDA);
				break;
            case Input.Keys.D:
			case Input.Keys.RIGHT:
                controlador.pulsarTecla(Controlador.Keys.DERECHA);
				break;

		}
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
		switch (keycode) {
            case Input.Keys.SPACE:
		    case Input.Keys.W:
			case Input.Keys.UP:
				controlador.liberarTecla(Controlador.Keys.ARRIBA);
				break;
            case Input.Keys.A:
			case Input.Keys.LEFT:
				controlador.liberarTecla(Controlador.Keys.IZQUIERDA);
				break;
            case Input.Keys.D:
			case Input.Keys.RIGHT:
				controlador.liberarTecla(Controlador.Keys.DERECHA);
				break;
		}
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 temporal= new Vector3(screenX,screenY,0);
        Circle dedo = new Circle(temporal.x,temporal.y,2);
        float heigth = Gdx.graphics.getHeight();
        float widht = Gdx.graphics.getWidth();

        if(Intersector.overlaps(dedo, new Rectangle(Hud.pause.getX(), heigth-Hud.pause.getY()-Hud.pause.getHeight(),
                Hud.pause.getWidth(), Hud.pause.getHeight()))){
            if(Mundo.isMusicaOn()){
                Audio.soundButton.play();
            }
            pause = true;
        }else if(Intersector.overlaps(dedo, new Rectangle(Hud.menu.getX(), heigth-Hud.menu.getY()-Hud.menu.getHeight(),
                Hud.menu.getWidth(), Hud.menu.getHeight()))){
            if(Mundo.isMusicaOn()){
                Audio.soundButton.play();
            }
            if(Audio.musicaJuego.isPlaying()){
                Audio.musicaJuego.stop();
            }
            game.setScreen(new PantallaInicio(game));
        }else if(Intersector.overlaps(dedo, new Rectangle(Hud.upArrow.getX(), heigth-Hud.upArrow.getY()-Hud.upArrow.getHeight(),
                Hud.upArrow.getWidth(), Hud.upArrow.getHeight()))){
            System.out.println("arriba");
            this.controlador.pulsarTecla((Controlador.Keys. ARRIBA));
        }else if(Intersector.overlaps(dedo, new Rectangle(Hud.leftArrow.getX(), heigth-Hud.leftArrow.getY()-Hud.leftArrow.getHeight(),
                Hud.leftArrow.getWidth(), Hud.leftArrow.getHeight()))){
            System.out.println("izquierda");
            this.controlador.pulsarTecla(Controlador.Keys.IZQUIERDA);
        }else if(Intersector.overlaps(dedo, new Rectangle(Hud.rightArrow.getX(), heigth-Hud.rightArrow.getY()-Hud.rightArrow.getHeight(),
                Hud.rightArrow.getWidth(), Hud.rightArrow.getHeight()))){
            System.out.println("derecha");
            this.controlador.pulsarTecla(Controlador.Keys.DERECHA);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        controlador.liberarTecla(Controlador.Keys.DERECHA);
        controlador.liberarTecla(Controlador.Keys.IZQUIERDA);
        controlador.liberarTecla(Controlador.Keys.ARRIBA);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void render(float delta) {
        if (Mundo.isMusicaOn()) {
            Audio.musicaJuego.setVolume(1f);
        } else {
            Audio.musicaJuego.setVolume(0f);
        }
        controlador.update(delta);
        renderer.render(delta);
        if (pause) {
            if (Audio.musicaJuego.isPlaying()) {
                Audio.musicaJuego.pause();
            }
            game.setScreen(new PantallaPausa(game, this));
            return;
        }
        if (gameOver) {
            if (Audio.musicaJuego.isPlaying()) {
                Audio.musicaJuego.pause();
            }
            HighScores.engadirPuntuacion(mundo.getPj().getCoins() * 10 + mundo.getCronometro());
            System.out.println(mundo.getPj().getCoins() * 10 + mundo.getCronometro());
            game.setScreen(new PantallaPuntuacion(game));
            return;
        }
    }
    @Override
    public void resize(int width, int height) {
        Gdx.input.setInputProcessor(this);
        renderer.resize(width, height);
    }

    @Override
    public void pause() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        mundo.getWorld().dispose();
        renderer.dispose();
        Gdx.input.setInputProcessor(null);
    }
}
