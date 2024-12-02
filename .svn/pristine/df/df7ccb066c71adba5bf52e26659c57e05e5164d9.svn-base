package algorithmeStrategy;

import java.util.List;
import model.GameState;

public class MaxNOptimal implements GameStrategy {
	private int nbOfnodes = 0;
	private int move = -1;

	@Override
	public void evaluatePosition(GameState initState, int player, int profondeur) {
		this.nbOfnodes = 0;
		this.move = -1;
		maxN(initState, player, profondeur, 1);
	}

	public List<Double> maxN(GameState state, int player, int depth, double bound) {
		List<Integer> actions = state.getModel().possibleActions(player);
		if (depth == 0 || state.getGameStatus() || actions.isEmpty()) {
			this.move = -1;
			return state.getValue();
		}
		this.nbOfnodes++;
		GameState firstChild = state.nextGen(player, actions.get(0));
		int move = actions.get(0);
		actions.remove(0);
		List<Double> best = maxN(firstChild, firstChild.getNextPlayer(), depth - 1, 1);
		for (int action : actions) {
			if (best.get(player - 1) >= bound) {
				move = action;
				return best;
			}
			GameState next = state.nextGen(player, action);
			List<Double> curent = this.maxN(next, next.getNextPlayer(), depth - 1, 1 - best.get(player - 1));
			if (curent.get(player - 1) > best.get(player - 1)) {
				best = curent;
				move = action;
			}
		}
		this.move = move;
		return best;
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
		return "MAXN OPTIMAL IA";
	}
}
