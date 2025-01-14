package model;

import java.awt.Point;
import java.util.*;

public class GameState {

	private Grille model;
	private Map<Integer, Integer> playersArea;
	private List<Double> gameValue;
	private List<Integer> excludedPlayers;
	private int gameArea;
	private int nextPlayer;
	private int chosenMove;

	public GameState(Grille model) {
		this.model = model;
		this.playersArea = new HashMap<>();
		this.gameValue = new ArrayList<>();
		this.excludedPlayers = new ArrayList<>();
	}

//----------------------------------EVALUATION-----------------------------------------------------------------------------------------------------

	/*
	 * getPlayerDistance(int player) : BFS search of a chosen player starting from
	 * his current game position
	 */
	public Map<Point, Integer> getPlayerDistance(int player) {
		Point point = this.model.getPlayersPos().get(player);
		Map<Point, Integer> distance = new HashMap<>();
		Set<Point> nodes = new HashSet<Point>();
		Set<Point> exploredPoints = new HashSet<Point>();
		distance.put(point, 0);
		nodes.add(point);
		int track = 1;
		while (!nodes.isEmpty()) {
			Set<Point> nodesToKeep = new HashSet<>();
			for (Point node : nodes) {
				if (!exploredPoints.contains(node)) {
					for (int i = 0; i < 4; i++) {
						Point newPoint = this.model.actions((int) node.getX(), (int) node.getY(), i);
						if (newPoint != null) {
							distance.putIfAbsent(newPoint, track);
							nodesToKeep.add(newPoint);
						}
					}
					exploredPoints.add(node);
				}
			}
			track++;
			nodes.clear();
			nodes.addAll(nodesToKeep);
		}
		return distance;
	}

	/*
	 * gridCutout() : based on Vorono� diagram the grid is cut out based on the
	 * frontier created by each player controlled area
	 */

	public void gridCutout() {
		Map<Integer, Map<Point, Integer>> playersDistance = new HashMap<>();
		Map<Integer, Integer> playerArea = new HashMap<>();

		// Generating the distance for each player
		for (int player : this.model.getPlayersPos().keySet()) {
			playersDistance.put(player, this.getPlayerDistance(player));
			playerArea.put(player, 0);
		}
		double min = Double.POSITIVE_INFINITY;
		int minArg = -1;
		for (int row = 0; row < this.model.getHauteur(); row++) {
			for (int col = 0; col < this.model.getLargeur(); col++) {
				Point tempPoint = new Point(row, col);
				minArg = -1;
				min = (double) (1.0 / 0.0);
				for (Integer player : playersDistance.keySet()) {
					if (playersDistance.get(player).containsKey(tempPoint)) {
						if (playersDistance.get(player).get(tempPoint) < min) {
							min = playersDistance.get(player).get(tempPoint);
							minArg = player;
						} else if (playersDistance.get(player).get(tempPoint) == min) {
							minArg = -1;
						}
					}

				}
				if (!model.collision(tempPoint.x, tempPoint.y) && minArg > 0) {
					min = playerArea.get(minArg);
					playerArea.put(minArg, (int) min + 1);
				}
			}
		}
		this.playersArea.putAll(playerArea);
		this.gameArea = this.getCurrentArea(playerArea);
	}

	/*
	 * getValue() : Evaluation function of the games
	 */
	public List<Double> getValue() {
		this.gridCutout();
		List<Double> res = new ArrayList<>();
		for (int player : this.model.getPlayersPos().keySet()) {
			res.add((double) this.playersArea.get(player) / this.gameArea);
		}
		this.gameValue = res;
		return res;
	}

//------------------------------Game Parameters-------------------------------------------------------------------------------------
	/*
	 * getting the current area of the game
	 */
	public int getCurrentArea(Map<Integer, Integer> playerArea) {
		int sum = 0;
		for (Integer playerInGame : this.playersArea.keySet()) {
			sum += this.playersArea.get(playerInGame);
		}
		return sum;
	}

	/*
	 * Checking game status
	 */
	public boolean getGameStatus() {
		if(this.excludedPlayers.size() == this.model.getNbJoueurs()) {
			return true;
		}
		for (Double score : this.gameValue) {
			if (score == 1) {
				return true;
			}
		}
		return false;
	}

	public void excludedPlayersCheck() {
		for (int i = 0; i < this.gameValue.size(); i++) {
			if (this.gameValue.get(i) == 0.0) {
				this.excludedPlayers.add(i);
			}
		}
	}

	public int getWinner() {
		for (int i = 0; i < this.gameValue.size(); i++) {
			if (!this.excludedPlayers.contains(i)) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * Methode to generate the next game state
	 */
	public GameState nextGen(int player, int action) {
		Grille nextGrid = new Grille(this.model.getHauteur(), this.model.getLargeur(), this.model.getNbJoueurs(),
				this.model.getNbEquipes());
		nextGrid.setTeams(this.model.getTeams());
		nextGrid.setGrille(this.model.getTempGrid());
		nextGrid.setPlayersPos(this.model.getTempPlayerPos());
		nextGrid.setSosMatrix(this.getSosMatrix());
		nextGrid.move(player, action);
		GameState next = new GameState(nextGrid);
		this.nextPlayer(next, player);
		return next;
	}

	/*
	 * Calculating the next player
	 */
	private void nextPlayer(GameState next, int player) {
		if (player + 1 > this.model.getPlayersPos().size()) {
			next.setNextPlayer(1);
		} else {
			next.setNextPlayer(player + 1);
		}
	}

	/*
	 * Game state bound : used for pruning
	 */
	public double getBound() {
		int sum = 0;
		for (double val : this.gameValue) {
			sum += val;
		}
		return sum;
	}

//----------------------------------Getters and Setters-----------------------------------------------------------------------------
	public int getNextPlayer() {
		return nextPlayer;
	}

	public void setNextPlayer(int nextPlayer) {
		this.nextPlayer = nextPlayer;
	}

	public void setModel(Grille model) {
		this.model = model;
	}

	public Grille getModel() {
		return this.model;
	}

	public Map<Integer, Integer> getPlayersArea() {
		return playersArea;
	}

	public void setPlayersArea(Map<Integer, Integer> playersArea) {
		this.playersArea = playersArea;
	}

	public int getGameArea() {
		return gameArea;
	}

	public void setGameArea(int gameArea) {
		this.gameArea = gameArea;
	}

	public int getChosenMove() {
		return chosenMove;
	}

	public void setChosenMove(int chosenMove) {
		this.chosenMove = chosenMove;
	}

	public double[][] getSosMatrix() {
		return this.model.getSosMatrix();
	}

	public List<Double> getGameValue() {
		return gameValue;
	}

	public void setGameValue(List<Double> gameValue) {
		this.gameValue = gameValue;
	}

	public List<Integer> getExcludedPlayers() {
		return excludedPlayers;
	}

	public void setExcludedPlayers(List<Integer> excludedPlayers) {
		this.excludedPlayers = excludedPlayers;
	}

	// -----------------------------------HashCode and
	// Equals------------------------------------------------------------------------------
	@Override
	public int hashCode() {
		return this.model.toString().hashCode();
	}

//-------------------------------------------DEBUG-----------------------------------------------------------------------------------------
	public static void debug(double[][] temp) {
		StringBuilder str = new StringBuilder("");
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				str.append("  " + temp[i][j] + "  ");
			}
			str.append("\n");
		}
		System.out.println(str);
	}

	public int getWinner(Set<Integer> excludedPlayers) {
		Set<Integer> allPlayers = new HashSet<>();
		for (Integer player : this.model.getPlayersPos().keySet()) {
			allPlayers.add(player);
		}
		for (Integer player : allPlayers) {
			if (!excludedPlayers.contains(player)) {
				return player;
			}
		}
		return -1;
	}

}
