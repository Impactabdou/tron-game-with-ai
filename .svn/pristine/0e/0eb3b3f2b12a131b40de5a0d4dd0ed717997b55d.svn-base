package algorithmeStrategy;

import java.util.ArrayList;
import java.util.List;
import model.GameState;

public class ParanoidStrategie implements GameStrategy {
	private int nbOfnodes = 0;
	private int move = -1;
	private int originalPlayer;

	@Override
	public void evaluatePosition(GameState initState, int player, int profondeur) {
		this.nbOfnodes = 0;
		this.move = -1;
		originalPlayer = player;
		paranoid(initState, player, profondeur);
	}

	@Override
	public int getMove() {
		return this.move;
	}

	public List<Double> paranoid(GameState state, int player, int profondeur) {
		List<Integer> actions = state.getModel().possibleActions(player);
		;
		if (profondeur == 0 || state.getGameStatus() || actions.isEmpty()) {
			List<Double> res = state.getValue();
			this.move = -1;
			return res;
		}
		this.nbOfnodes++;
		double valMax = Double.NEGATIVE_INFINITY;
		double valMin = Double.POSITIVE_INFINITY;
		List<Double> res = new ArrayList<>();
		int move = -1;
		for (int action : actions) {
			GameState next = state.nextGen(player, action);
			List<Double> tempRes = this.paranoid(next, next.getNextPlayer(), profondeur - 1);
			double sum = getSum(tempRes);
			if (player == this.originalPlayer) {
				if (sum > valMin) {
					res = tempRes;
					valMin = sum;
					move = action;
				}
			} else {
				if (sum < valMax) {
					valMax = sum;
					move = action;
					res = tempRes;
				}
			}
		}
		this.move = move;
		return res;
	}

	public double getSum(List<Double> list) {
		double sum = 0;
		for (int nb = 0; nb < list.size(); nb++) {
			if (nb + 1 != this.originalPlayer) {
				sum += list.get(nb);
			}
		}
		return sum;
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
		return "Paranoid IA";
	}

}