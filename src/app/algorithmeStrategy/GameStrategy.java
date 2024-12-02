package algorithmeStrategy;

import model.GameState;

public interface GameStrategy {
	public void evaluatePosition(GameState initState, int player, int profondeur);
	public int getMove();
	public int getNodes();
}
