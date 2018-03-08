package pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import game.AssetsJuego;
import game.Audio;
import modelo.Mundo;

/**
 * Created by dalei on 14/02/2018.
 */

public class PantallaPausa implements Screen, InputProcessor {

    private Game juego;
    private PantallaJuego pantallaJuego;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private BitmapFont bitMapFont;

    public PantallaPausa(Game juego, PantallaJuego pantallaJuego){
        this.juego = juego;
        this.pantallaJuego = pantallaJuego;
        this.spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/mad_college.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)(Gdx.graphics.getWidth() * 0.04f) ;
        bitMapFont = generator.generateFont(parameter);
        bitMapFont.setColor(Color.RED);
        generator.dispose();
        Gdx.input.setInputProcessor(this);
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
        if(Mundo.isMusicaOn()){
            spriteBatch.draw(AssetsJuego.sound, Gdx.graphics.getWidth() * 0.85f, Gdx.graphics.getHeight() * 0.80f,
                    Gdx.graphics.getWidth() * 0.08f, Gdx.graphics.getHeight() * 0.1f);
        }else{
            spriteBatch.draw(AssetsJuego.sound_inactive, Gdx.graphics.getWidth() * 0.87f, Gdx.graphics.getHeight() * 0.80f,
                    Gdx.graphics.getWidth() * 0.06f, Gdx.graphics.getHeight() * 0.1f);
        }
        bitMapFont.draw(spriteBatch, "PAUSA",Gdx.graphics.getWidth() * 0.42f, Gdx.graphics.getHeight() * 0.5f);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

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
        Vector3 temp = new Vector3(screenX,screenY,0);
        camera.unproject(temp);
        Circle dedo = new Circle(temp.x,temp.y,2);

        if(Intersector.overlaps(dedo, new Rectangle(Gdx.graphics.getWidth() * 0.85f, Gdx.graphics.getHeight() * 0.80f,
                Gdx.graphics.getWidth() * 0.08f, Gdx.graphics.getHeight() * 0.1f))){ // sonido
            Mundo.setMusicaOn(!Mundo.isMusicaOn());
            Preferences prefs = Gdx.app.getPreferences("preferencias.dat");
            prefs.putBoolean("musicaOn", Mundo.isMusicaOn());
            prefs.flush();
        } else {
            Audio.musicaJuego.play();
            PantallaJuego.pause = false;
            juego.setScreen(this.pantallaJuego);
        }
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
