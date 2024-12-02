package algorithmeStrategy;

import java.util.ArrayList;
import java.util.List;

import model.GameState;

public class SosStrategyOptimal implements GameStrategy {

	private int nbOfnodes = 0;
	private int move = -1;

	@Override
	public void evaluatePosition(GameState initState, int player, int profondeur) {
		this.nbOfnodes = 0;
		this.move = -1;
		sosAlgorithme(initState, player, profondeur, 1);
	}

	public List<Double> sosAlgorithme(GameState state, int player, int depth, double bound) {
		List<Integer> actions = state.getModel().possibleActions(player);
		if (depth == 0 || state.getGameStatus() || actions.isEmpty()) {
			List<Double> res = state.getValue();
			res = this.matrixVectorMul(res, state);
			this.move = -1;
			return res;
		}
		this.nbOfnodes++;
		GameState firstChild = state.nextGen(player, actions.get(0));
		int move = actions.get(0);
		actions.remove(0);
		List<Double> best = sosAlgorithme(firstChild, firstChild.getNextPlayer(), depth - 1, 1);

		for (int action : actions) {
			if (best.get(player - 1) >= bound) {
				move = action;
				return best;
			}
			GameState next = state.nextGen(player, action);
			List<Double> curent = this.sosAlgorithme(next, next.getNextPlayer(), depth - 1, 1 - best.get(player - 1));
			if (curent.get(player - 1) > best.get(player - 1)) {
				best = curent;
				move = action;
			}
		}
		this.move = move;
		return best;
	}
	
	public List<Double> matrixVectorMul(List<Double> vector, GameState state) {
		int rows = state.getSosMatrix().length;
		int cols = state.getSosMatrix()[0].length;
		List<Double> result = new ArrayList<>();
		for (int i = 0; i < rows; i++) {
			double sum = 0.0;
			for (int j = 0; j < cols; j++) {
				sum += state.getSosMatrix()[i][j] * vector.get(j);
			}
			result.add(sum);
		}
		return result;
	}
	
	
//------------------------------------------Getters and Setters---------------------------------------------------------------------------------------

	public void setMove(int move) {
		this.move = move;
	}

	@Override
	public int getMove() {
		return this.move;
	}
	@Override
	public int getNodes() {
		return nbOfnodes;
	}
	
	@Override
	public String toString() {
		return "SOS OPTIMAL IA";
	}

}
