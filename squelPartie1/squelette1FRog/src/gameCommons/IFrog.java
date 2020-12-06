package gameCommons;

import environment.Environment;
import util.Case;
import util.Direction;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement 
	 * @return
	 */
	public Direction getDirection();
	
	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key); // Changement

	/**
	 * Deplace la grenouille encore une fois ou le fait reculer
	 * @param key
	 */
	public void Glisse(Direction key); // Changement
	public void Bloque(Direction key); // Changement

}
