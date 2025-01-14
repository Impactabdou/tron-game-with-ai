package experimentation;
import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithmeStrategy.GameStrategy;
import algorithmeStrategy.MaxNOptimal;
import algorithmeStrategy.MaxNStrategy;
import algorithmeStrategy.ParanoidStrategie;
import algorithmeStrategy.RandomIAStrategy;
import algorithmeStrategy.SoSStrategy;
import algorithmeStrategy.SosStrategyOptimal;
import model.GameState;
import model.Grille;
import util.CSV;

public class Demo {

	private GameStrategy[] strategies;
	private String[] strategiesString ; 
	private int[] gameParameters;
	private int nbOfGmaes;
	private Map<String, Integer> res;
	private Map<Integer, List<Integer>> teams;
	private int[] depthVec;
	private CSV csv;
//longeur de grille / profondeur
	public Demo(String[] strategies, int[] gameParameters, int[] depthVec,
			Map<Integer, List<Integer>> teams,int nbOfGames) {
		this.strategiesString = strategies;
		this.strategies = new GameStrategy[gameParameters[2]];
		this.gameParameters = gameParameters;
		this.nbOfGmaes = nbOfGames;
		this.teams = teams;
		this.depthVec = depthVec;
		this.csv = new CSV();
		res = new HashMap<>();
	}
	
	public void init() {
		int nb = 0 ; 
		for(String stratString : this.strategiesString) {
			this.strategies[nb] = this.strategyFact(stratString);
			res.put(stratString, 0);
			nb++;
		}
	}

	public void startDemo() {
		this.init();
		Grille grid = new Grille(this.gameParameters[0], this.gameParameters[1], this.gameParameters[2],
				this.gameParameters[3]);
		
		int gamesDone = 0;
		int move = -1;

		while (gamesDone < this.nbOfGmaes) {
			System.out.println(gamesDone);
			grid.grilleInit();
			grid.setTeams(teams);
			grid.randomSpawn();
			grid.initSosMatrix();
			GameState game = new GameState(grid);
			try {
                Thread.sleep(1000); // or TimeUnit.MILLISECONDS.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                // Handle interruption if needed
            }
			String lastGrid = grid.toString();
			int count = 0 ;
			boolean bug = false; 
			while (!game.getGameStatus()) {
				if(count > 10){
					bug = true;
					break;
				}
				lastGrid = grid.toString();
				int[] nextMove = new int[grid.getNbJoueurs()];
				for (int player = 1; player <= grid.getNbJoueurs(); player++) {
					this.strategies[player - 1].evaluatePosition(game, player, this.depthVec[player - 1]);
					move = this.strategies[player - 1].getMove();
					nextMove[player - 1] = move;
				}
				for (int player = 0; player < nextMove.length; player++) {
					grid.move(player + 1, nextMove[player]);
				}
				if(lastGrid.equals(grid.toString())){
					count++;
				}
				game.excludedPlayersCheck();
			}
			if(!bug){
				int winner = game.getWinner();
				if (winner >= 0) {
					int nb = 1 + this.res.get(this.strategies[winner].toString());
					this.res.put(this.strategies[winner].toString(), nb);
				}
				Point p = grid.getInitCoordonees().get(winner);
				int x = (int) p.getX(); 
				int y = (int) p.getY(); 
	
				this.csv.addDataCSV(this.strategies[winner].toString(), "" + grid.getNbJoueurs(), "" + grid.getNbEquipes(),
						grid.getHauteur() + "x" + grid.getLargeur(), this.tableToString(this.strategiesString) ,this.tableToString(this.depthVec),"x="+x+" y="+y);
			}
			gamesDone++;
			System.out.println(res);
		}
	}
	public GameStrategy strategyFact(String stratName) {
		switch (stratName) {
        case "Random IA":
            return new RandomIAStrategy();
        case "MaxN IA":
            return new MaxNStrategy();
        case "Paranoid IA":
            return new ParanoidStrategie();
        case "SOS IA":
            return new SoSStrategy();
        case "SOS OPTIMAL IA":
            return new SosStrategyOptimal();
        case "MAXN OPTIMAL IA":
            return new MaxNOptimal();
        default:
            return null;
    } 
	}
	private String tableToString(String[] tab){
		StringBuilder res = new StringBuilder();
		for(int i = 0 ; i<tab.length ; i++){
			res.append(tab[i]).append(" ");
		}
		return res.toString() ; 
	}
	private String tableToString(int[] tab){
		StringBuilder res = new StringBuilder();
		for(int i = 0 ; i<tab.length ; i++){
			res.append(tab[i]).append(" ");
		}
		return res.toString() ; 
	}
}
