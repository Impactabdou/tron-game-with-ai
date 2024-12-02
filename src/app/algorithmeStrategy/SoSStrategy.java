package algorithmeStrategy;

import java.util.ArrayList;
import java.util.List;
import model.GameState;

public class SoSStrategy implements GameStrategy {
	private int move = -1;
	private int nbOfnodes;

	@Override
	public void evaluatePosition(GameState initState, int player, int profondeur) {
		this.nbOfnodes = 0;
		this.move = -1;
		sosAlgorithme(initState, player, profondeur);
	}

	public List<Double> sosAlgorithme(GameState state, int player, int profondeur) {
		List<Integer> actions = state.getModel().possibleActions(player);
		if (profondeur == 0 || state.getGameStatus() || actions.isEmpty()) {
			List<Double> res = state.getValue();
			res = this.matrixVectorMul(res, state);
			this.move = -1;
			return res;
		}
		this.nbOfnodes++;
		List<Double> valMax = new ArrayList<>();
		for (int i = 0; i < state.getModel().getNbJoueurs(); i++) {
			valMax.add(Double.NEGATIVE_INFINITY);
		}
		int move = -1;
		for (int action : actions) {
			GameState next = state.nextGen(player, action);
			List<Double> res = this.sosAlgorithme(next, next.getNextPlayer(), profondeur - 1);
			if (res.get(player - 1) > valMax.get(player - 1)) {
				valMax = res;
				move = action;
			}
		}
		this.move = move;
		return valMax;
	}

	@Override
	public int getMove() {
		return this.move;
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
	public int getNodes() {
		return nbOfnodes;
	}
	@Override
	public String toString() {
		return "SOS IA";
	}
	

}
