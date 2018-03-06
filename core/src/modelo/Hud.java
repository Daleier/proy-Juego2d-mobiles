package modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.xml.ws.Dispatch;

/**
 * Created by dalei on 03/03/2018.
 */

public class Hud {

    public Stage stage;
    private Mundo mundo;
    Label tiempo;
    public Hud(Mundo mundo, SpriteBatch spriteBatch){
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        this.stage = new Stage(new FitViewport(width, height), spriteBatch);
        this.mundo = mundo;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/dsdigit.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)(width * 0.05f) ;
        BitmapFont bitMapFont = generator.generateFont(parameter); // font size in pixels
        LabelStyle labelStyle = new LabelStyle(bitMapFont, Color.YELLOW);
        Table tabla = new Table();
        tabla.setFillParent(true);
        tabla.defaults().space(width*0.05f);
        tabla.defaults().fillX();
        tabla.align(Align.top| Align.center);
        tiempo = new Label("Titulo", labelStyle);
        tiempo.setBounds(10,30,width,height* 0.95f);
        tiempo.setAlignment(Align.center);
        tiempo.setPosition(width * 0.2f, height * 0.95f);
        tabla.add(tiempo);
        stage.addActor(tabla);
    }

    public void update() {
        tiempo.setText(Integer.toString(mundo.getCronometro()));
    }
}
