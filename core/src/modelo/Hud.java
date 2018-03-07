package modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import game.AssetsJuego;

/**
 * Created by dalei on 03/03/2018.
 */

public class Hud {

    public Stage stage;
    private Mundo mundo;
    Label tiempo;
    Label vidas;
    Label coins;

    public static Image menu;
    public static Image pause;
    public static Image leftArrow;
    public static Image rightArrow;
    public static Image upArrow;

    public Hud(Mundo mundo, SpriteBatch spriteBatch){
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        this.stage = new Stage(new FitViewport(width, height), spriteBatch);
        this.mundo = mundo;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/mad_college.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)(width * 0.04f) ;
        BitmapFont bitMapFont = generator.generateFont(parameter); // font size in pixels
        LabelStyle labelStyle = new LabelStyle(bitMapFont, Color.RED);

        Image timer = new Image(AssetsJuego.timer);
        timer.setPosition(width*0.40f, height*0.875f);
        timer.setSize(width*0.08f, height*0.1f);
        stage.addActor(timer);

        tiempo = new Label("0", labelStyle);
        tiempo.setPosition(width * 0.48f, height * 0.87f);
        stage.addActor(tiempo);

        Image icoCoin = new Image(AssetsJuego.icoCoin);
        icoCoin.setPosition(width * 0.64f, height*0.875f);
        icoCoin.setSize(width * 0.07f, height * 0.09f);
        stage.addActor(icoCoin);

        coins = new Label("0", labelStyle);
        coins.setPosition(width * 0.72f, height * 0.87f);
        stage.addActor(coins);

        Image health = new Image(AssetsJuego.health);
        health.setPosition(width*0.83f, height*0.875f);
        health.setSize(width*0.07f, height*0.09f);
        stage.addActor(health);

        vidas = new Label("0", labelStyle);
        vidas.setPosition(width * 0.91f, height * 0.87f);
        stage.addActor(vidas);

        menu = new Image(AssetsJuego.menu);
        menu.setPosition(width*0.01f, height*0.86f);
        menu.setSize(width*0.07f, height*0.12f);
        stage.addActor(menu);

        pause = new Image(AssetsJuego.pause);
        pause.setPosition(width*0.1f, height*0.86f);
        pause.setSize(width*0.08f, height*0.12f);
        stage.addActor(pause);

        leftArrow = new Image(AssetsJuego.leftarrow);
        leftArrow.setPosition(width*0.03f, height*0.05f);
        leftArrow.setSize(width*0.08f, height*0.12f);
        stage.addActor(leftArrow);

        rightArrow = new Image(AssetsJuego.rightarrow);
        rightArrow.setPosition(width*0.13f, height*0.05f);
        rightArrow.setSize(width*0.08f, height*0.12f);
        stage.addActor(rightArrow);

        upArrow = new Image(AssetsJuego.uparrow);
        upArrow.setPosition(width*0.87f, height*0.05f);
        upArrow.setSize(width*0.08f, height*0.12f);
        stage.addActor(upArrow);
    }

    public void update() {
        tiempo.setText(Integer.toString(mundo.getCronometro()));
        vidas.setText(Integer.toString(mundo.getPj().getVidas_restantes()));
        coins.setText(Integer.toString(mundo.getPj().getCoins()));
    }
}
