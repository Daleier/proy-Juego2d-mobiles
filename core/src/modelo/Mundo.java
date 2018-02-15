package modelo;

/**
 * Created by dalei on 14/02/2018.
 */

public class Mundo {
    public static final int ANCHO_MUNDO = 480;
    public static final int ALTO_MUNDO = 800;
    private final int TEMPO_INICIAL_CRONOMETRO = 0;
    private float cronometro;

    public Mundo() {
        cronometro = TEMPO_INICIAL_CRONOMETRO;
    }

    public int getCronometro() {
        return (int)cronometro;
    }

    public void setCronometro(float cronometro) {
        this.cronometro = cronometro;
    }

    public void updateCronometro(float delta) {
        cronometro += delta; //cambiar a -= para cuenta regresiva
    }

}
