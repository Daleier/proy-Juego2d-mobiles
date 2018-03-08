package pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import game.HighScores;

/**
 * Created by dalei on 14/02/2018.
 */

public class PantallaPuntuacion implements Screen, InputProcessor{

    private Game juego;
    private SpriteBatch spriteBatch;
    private BitmapFont bitMapFont;
    private float height;
    private float width;

    public PantallaPuntuacion(Game juego){
        this.juego = juego;
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        spriteBatch = new SpriteBatch();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/mad_college.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)(width * 0.04f) ;
        bitMapFont = generator.generateFont(parameter);
        bitMapFont.setColor(Color.RED);
        generator.dispose();
        Gdx.input.setInputProcessor(this);
        HighScores.load();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.7f, 0.8f, 0.9f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        bitMapFont.draw(spriteBatch, "PUNTUACIONES", width * 0.33f, height * 0.9f);
        float drawheight = 0.7f;
        float heighDiff = 0.2f;
        for(String s: HighScores.highscores){
            bitMapFont.draw(spriteBatch, new StringBuilder().append(s), width*0.48f, height * drawheight);
            drawheight -= heighDiff;
        }
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
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
        bitMapFont.dispose();
        spriteBatch.dispose();
        Gdx.input.setInputProcessor(null);
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
        dispose();
        juego.setScreen(new PantallaInicio(juego));
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
