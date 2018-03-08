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
		int i = 0;
		while ((i < HighScores.highscores.length) && (!encontrado)) {
			if (puntuacion > Integer.parseInt(HighScores.highscores[i])) {
				encontrado=true;
				for (int j=(highscores.length-1); j > i; j-- ){
					HighScores.highscores[j] = HighScores.highscores[j-1];
				}
				HighScores.highscores[i] =  Integer.toString(puntuacion);
			} else
				i++;
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