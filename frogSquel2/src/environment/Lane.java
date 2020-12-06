package environment;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private ArrayList<Trap> traps = new ArrayList<>(); // Changement
	private ArrayList<Glass> glasses = new ArrayList<>(); // Changement
	private ArrayList<Wall> walls = new ArrayList<>(); // Changement
	private boolean leftToRight;
	private double density;
	private int tic =0;

	// TODO : Constructeur(s)
	public Lane(Game game, int ord, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops);
		this.leftToRight = game.randomGen.nextBoolean();
		this.density = density;
		for(int i=0; i < 4* game.width; i++) {
			this.displaceCars(true);
			this.mayAddCar();
			this.mayAddTrap(); // Changement
			this.mayAddGlass(); // Changement
			this.mayAddWall(); // Changement
		}
	}

	public Lane(Game game, int ord) {
		this(game,ord,game.defaultDensity);
	}

	private void deleteCars() {
		ArrayList<Car> deletedCars = new ArrayList<>();
		for(Car car : this.cars) {
			if(!car.inLimits()) {
				deletedCars.add(car);
			}
		}
		for(Car car:deletedCars) {
			this.cars.remove(car);
		}
			}

	private void displaceCars(boolean b) {
		for (Car car: this.cars) {
			car.displace(b);
		}
			this.deleteCars();
	}





	public void update() {

		// TODO

		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e

		this.tic++;
		if(this.tic <= this.speed) {
			this.displaceCars(false);
		}
		else {
			this.displaceCars(true);
			this.mayAddCar();
			this.mayAddTrap(); // Changement
			this.mayAddGlass(); // Changement
			this.mayAddWall(); // Changement
			this.tic = 0;
		}


	}

	// TODO : ajout de methodes

	public boolean isSafe(Case c) {
		for(Car car : this.cars) {
			if(car.aboveCase(c)) {
				return true;
			}
		}
		return false;
	}
	public boolean onTrap(Case c) { // Comme isSafe, il verifie si frog est sur la meme case que Trap
		for(Trap trap : this.traps) {
			if(trap.aboveCase(c)) {
				return true;
			}
		}
		return false;
	}
	public boolean onGlass(Case c) { // Changement
		for(Glass glass : this.glasses) {
			if(glass.aboveCase(c)) {
				return true;
			}
		}
		return false;
	}
	public boolean onWall(Case c) { // Changement
		for(Wall wall : this.walls) {
			if(wall.aboveCase(c)) {
				return true;
			}
		}
		return false;
	}




	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}


	private void mayAddTrap() { // Il sert à créer les pieges quand la partie commence:
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density/2) { // Sa probabilité d'apparaitre est plus faible que Car
				traps.add(new Trap(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}
	private void mayAddGlass() { // Idem
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density/2) {
				glasses.add(new Glass(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}
	private void mayAddWall() { // Idem
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density/2) {
				walls.add(new Wall(game, getBeforeFirstCase()));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
