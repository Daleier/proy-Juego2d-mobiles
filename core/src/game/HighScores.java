package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class HighScores {
    public static String[] highscores = { "0", "0", "0" };
	public static String archivoHighscores = "highscores.dat";

	public static void load() {
		FileHandle arquivo = Gdx.files.external(HighScores.archivoHighscores);
		if (!arquivo.exists()) {
			HighScores.save();
			System.out.println("Non encontrado");
		}
		String scores = arquivo.readString();
		highscores = scores.split(",");
	}

	public static void engadirPuntuacion(int puntuacion) {
		boolean encontrado = false;
		int temporal;
		if (puntuacion > Integer.parseInt(highscores[0])) {
			temporal = Integer.parseInt(highscores[0]);
			highscores[0] = Integer.toString(puntuacion);
			puntuacion = temporal;
			encontrado = true;
		}
		if (puntuacion > Integer.parseInt(highscores[1])) {
			temporal = Integer.parseInt(highscores[1]);
			highscores[1] = Integer.toString(puntuacion);
			puntuacion = temporal;
			encontrado = true;
		}
		if (puntuacion > Integer.parseInt(highscores[2])) {
			highscores[2] = Integer.toString(puntuacion);
			encontrado = true;
		}
		if (encontrado)
			HighScores.save();
	}

	private static void save() {
		FileHandle arquivo = Gdx.files.external(HighScores.archivoHighscores);
		arquivo.writeString(HighScores.highscores[0] + ",", false);
		arquivo.writeString(HighScores.highscores[1] + ",", true);
		arquivo.writeString(HighScores.highscores[2], true);

		/*for(int n = 1; n<highscores.length;n++){
			arquivo.writeString(HighScores.highscores[n] + ",", true);
		}*/
	}
}