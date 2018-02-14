package game;

import com.badlogic.gdx.Gdx;

public class Utiles {
    private static final String LOG = "LOG";

    /**
     * Método para imprimir mensaxes de log no Eclipse.
     * Usado para depuración (debugger)
     * @param clase: nome da clase de onde se chama
     * @param metodo: nome do método de onde se chama
     * @param mensaxe: mensaxe a imprimir
     */
    public static void imprimirLog(String clase, String metodo, String mensaxe){
        Gdx.app.log(LOG, clase + ":"+metodo+":"+mensaxe);
    }
}
