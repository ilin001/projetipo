package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	//TODO Constructeur(s)
	public Car(Game game, Case leftPosition, boolean leftToRight){
		this.game = game;
		this.leftPosition = new Case(leftPosition.absc - this.length + 1, leftPosition.ord);
		this.leftToRight = leftToRight;
		this.length = game.randomGen.nextInt(3);
	}

	//TODO : ajout de methodes

	public void displace(boolean b) { // déplace la voiture si l'entrée est true
		if(b) {
			if(this.leftToRight) {
				this.leftPosition = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
			}
			else {
				this.leftPosition = new Case(this.leftPosition.absc - 1, this.leftPosition.ord);
			}
		}
		this.addToGraphics();

	}

	public boolean inLimits() {	// Vérifie si la voiture se situe dans la zone du jeu
		return this.leftPosition.absc + this.length > 0 || this.leftPosition.absc < this.game.width;
	}



	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

	public boolean aboveCase(Case c) {	// Vérifie si un objet se situe au meme emplacement d'un autre objet
		if(c.ord == this.leftPosition.ord) {
			if(c.absc >= this.leftPosition.absc && c.absc < this.leftPosition.absc + this.length) {
				return true;
			}
		}
		return false;
	}

}
