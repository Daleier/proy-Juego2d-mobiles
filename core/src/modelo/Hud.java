package modelo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;

import javax.xml.ws.Dispatch;

/**
 * Created by dalei on 03/03/2018.
 */

public class Hud {

    public Stage stage;
    public Hud(){
        BitmapFont bitMapFont = new BitmapFont(); // TODO personalizar
        LabelStyle labelStyle = new LabelStyle(bitMapFont, Color.YELLOW);
        Table tabla = new Table();
        tabla.setFillParent(true);
        tabla.defaults().space(5);
        tabla.defaults().fillX();
        tabla.align(Align.top| Align.center);
    }
}
