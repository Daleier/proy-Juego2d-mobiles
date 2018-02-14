package pantallas;

import com.badlogic.gdx.Game;
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
        this.game = game;
        mundo = new Mundo();
        this.renderer = new Renderer(mundo);
        this.controlador = new Controlador(mundo);
        Utiles.imprimirLog("PantallaJuego","Constructor","Creado objeto PantallaJuego");

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
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

    }

    @Override
    public void render(float delta) {
        controlador.update(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
