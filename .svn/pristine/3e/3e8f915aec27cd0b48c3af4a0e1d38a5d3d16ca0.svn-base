package algorithmeStrategy;

import java.util.ArrayList;
import java.util.List;
import model.GameState;

public class MaxNStrategy implements GameStrategy {
	private int nbOfnodes = 0;
	private int move = -1;

	@Override
	public void evaluatePosition(GameState initState, int player, int profondeur) {
		this.nbOfnodes = 0;
		this.move = -1;
		maxN(initState, player, profondeur);
	}

	public List<Double> maxN(GameState state, int player, int profondeur) {
		List<Integer> actions = state.getModel().possibleActions(player);
		if (profondeur == 0 || state.getGameStatus() || actions.isEmpty()) {
			this.move = -1;
			return state.getValue();
		}
		this.nbOfnodes++;
		List<Double> valMax = new ArrayList<>();
		for (int i = 0; i < state.getModel().getNbJoueurs(); i++) {
			valMax.add(Double.NEGATIVE_INFINITY);
		}
		int move = -1;
		for (int action : actions) {
			GameState next = state.nextGen(player, action);
			List<Double> res = this.maxN(next, next.getNextPlayer(), profondeur - 1);
			if (res.get(player - 1) > valMax.get(player - 1)) {
				valMax = res;
				move = action;
			}
		}
		this.move = move;
		return valMax;
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
		return "MaxN IA";
	}

}