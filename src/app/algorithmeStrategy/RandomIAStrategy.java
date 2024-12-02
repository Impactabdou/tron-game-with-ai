package algorithmeStrategy;

import java.util.List;
import java.util.Random;

import model.GameState;

public class RandomIAStrategy implements GameStrategy {
	private int nbOfnodes = 0;
	private int move = -1;

	@Override
	public void evaluatePosition(GameState initState, int player, int profondeur) {
		this.nbOfnodes = 0;
		List<Integer> actions = initState.getModel().possibleActions(player);
		if(actions.isEmpty()) {
			move = -1 ; 
			initState.getValue();
		}else {
			int index = (int) Math.floor(Math.random() * actions.size()) ; 
			move = actions.get(index);
		}
	}

//------------------------------------------Getters and Setters---------------------------------------------------------------------------------------

	@Override
	public int getNodes() {
		return nbOfnodes;
	}

	public void setMove(int move) {
		this.move = move;
	}

	@Override
	public int getMove() {
		return this.move;
	}
	
	@Override
	public String toString() {
		return "Random IA";
	}

}
