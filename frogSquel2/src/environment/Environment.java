package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;


public class Environment implements IEnvironment {
		
	//TODO
    // Environne globale composé d'une route de plusieurs voies
    private Game game;
    private ArrayList<Lane> roads = new ArrayList<>();

    public Environment(Game game) {
       this.game = game;
       this.roads.add(new Lane(game,0,0.0));
       for(int i = 1; i < game.height -1; i++) {
           this.roads.add(new Lane(game, i));
       }
       this.roads.add(new Lane(game, game.height,0.0));

        }


    @Override
    public boolean isSafe(Case c) {
      return this.roads.get(c.ord).isSafe(c);
    }

    public boolean onTrap(Case c) { // Changement
        return this.roads.get(c.ord).onTrap(c);
    }
    public boolean onGlass(Case c) { // Changement
        return this.roads.get(c.ord).onGlass(c);
    }
    public boolean onWall(Case c) { // Changement
        return this.roads.get(c.ord).onWall(c);
    }

    @Override
    public boolean isWinningPosition(Case c) {
        return c.ord == game.height -1;
    }
    @Override
    public void update() {
        for(Lane lane: this.roads) {
            lane.update();
        }
    }

}
