package pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

import controlador.Controlador;
import game.Utiles;
import modelo.Mundo;
import renderer.Renderer;

/**
 * Created by dalei on 13/02/2018.
 */

public class PantallaJuego implements Screen, InputProcessor{

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
    }

    @Override
    public boolean keyDown(int keycode) {
		switch(keycode){
			case Input.Keys.UP:
				controlador.pulsarTecla(Controlador.Keys.ARRIBA);
				break;
			case Input.Keys.DOWN:
				controlador.pulsarTecla(Controlador.Keys.ABAJO);
				break;
			case Input.Keys.LEFT:
				controlador.pulsarTecla(Controlador.Keys.IZQUIERDA);
				break;
			case Input.Keys.RIGHT:
				controlador.pulsarTecla(Controlador.Keys.DERECHA);
				break;
		}
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Utiles.imprimirLog("PantallaJuego","keyUp","Moviendo");
		switch (keycode) {
			case Input.Keys.UP:
				controlador.liberarTecla(Controlador.Keys.ARRIBA);
				break;
			case Input.Keys.DOWN:
				controlador.liberarTecla(Controlador.Keys.ABAJO);
				break;
			case Input.Keys.LEFT:
				controlador.liberarTecla(Controlador.Keys.IZQUIERDA);
				break;
			case Input.Keys.RIGHT:
				controlador.liberarTecla(Controlador.Keys.DERECHA);
				break;
		}
        if(keycode == Input.Keys.NUM_1)
            renderer.getMapa().getLayers().get(0).setVisible(!renderer.getMapa().getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            renderer.getMapa().getLayers().get(1).setVisible(!renderer.getMapa().getLayers().get(1).isVisible());
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        controlador.update(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
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
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        mundo.getWorld().dispose();
        Gdx.input.setInputProcessor(null);
    }
}
