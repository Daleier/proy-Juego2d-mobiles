package renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import game.Utiles;
import modelo.Mundo;

/**
 * Created by dalei on 14/02/2018.
 */

public class Renderer implements InputProcessor{

    private Mundo mundo;
    private boolean debugger=false;
    private SpriteBatch spritebatch;
    private OrthographicCamera camera;
    private ShapeRenderer shaperender;

    public Renderer(Mundo mundo){
        this.mundo = mundo;
        camera = new OrthographicCamera();
        spritebatch = new SpriteBatch();
        shaperender = new ShapeRenderer();
        Gdx.input.setInputProcessor(this);
        Utiles.imprimirLog("Renderer","Constructor","Creado objeto renderer");
    }

    public void render(float delta){
        Gdx.gl.glClearColor(0f, 0.5f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spritebatch.begin();

        spritebatch.end();
        if(debugger){
            debug();
        }
    }

    public void debug() {

    }

    public void resize(int width, int height) {
        camera.setToOrtho(false,Mundo.ANCHO_MUNDO,Mundo.ALTO_MUNDO);
        camera.update();
        spritebatch.setProjectionMatrix(camera.combined);
        shaperender.setProjectionMatrix(camera.combined);
    }

    public void dispose() {
        Gdx.input.setInputProcessor(null);
        spritebatch.dispose();
        shaperender.dispose();
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
}
