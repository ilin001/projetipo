package gameCommons;

import java.awt.Color;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;
import util.Case;
import util.Direction;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;

	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant d�placement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
	}

	/**
	 * Lie l'objet frog � la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		// TODO
		if(environment.isSafe(this.frog.getPosition()) == false ) { // teste si sur la case actuelle la grenouille meurt
			if(environment.onTrap(this.frog.getPosition()) == false) {
				this.graphic.endGameScreen("Perdu");                    // si elle meurt, on affiche l'ecran de fin de jeu "perdu"
				return true;
			}
		}
		return false;
	}
	public boolean testGlisse() {
		// TODO

		if(environment.onGlass(this.frog.getPosition()) == true) { // Changement
			frog.Glisse(frog.getDirection());
			return true;
		}

		return false;
	}
	public boolean testBloque() {
		// TODO

		if(environment.onWall(this.frog.getPosition()) == true) { // Changement
			frog.Bloque(frog.getDirection());
			return true;
		}

		return false;
	}




	/**
	 * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagn�e
	 */
	public boolean testWin() {
		// TODO
		if(environment.isWinningPosition(this.frog.getPosition()) == true ) { // teste si la case actuelle est la case d'arrivee
			this.graphic.endGameScreen("Victoire");							  // si elle l'est, on affiche l'ecran de fin de jeu "victoire"
			return true;
		}
		return false;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		testGlisse(); // Changement
		testBloque(); // Changement
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		testLose();
		testWin();
	}

}
