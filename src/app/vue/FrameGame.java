package vue;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import algorithmeStrategy.GameStrategy;
import algorithmeStrategy.MaxNStrategy;
import algorithmeStrategy.ParanoidStrategie;
import algorithmeStrategy.SoSStrategy;
import model.GameState;
import model.Grille;

public class FrameGame extends JComponent {

	private Grille grid = new Grille(30, 30, 3, 2);
	private Color[] color = { new Color(255, 102, 102), // Light Red 1
			new Color(102, 204, 255), // Light Blue 2
			new Color(255, 204, 102), // Light Orange 3
			new Color(153, 255, 153), // Light Green 4
			new Color(255, 153, 255), // Light Magenta 5
			new Color(255, 255, 102) // Light Yellow 6
	};

	public void startGame() {
		int nbOfGames = 0;
		while (nbOfGames < 10) {
			// init
			grid.grilleInit();

			// player placement manual
			// grid.randomSpawn();
			// for 30 x 30

			grid.manualPlayerSpawn(1, 3, 3);
			grid.manualPlayerSpawn(2, 3, 17);
			grid.manualPlayerSpawn(3, 28, 18);

			/*
			 * //for 20 x 20 grid.manualPlayerSpawn(1,3, 3); grid.manualPlayerSpawn(2,18,
			 * 3); grid.manualPlayerSpawn(3, 1, 10);
			 */
			// grid.manualPlayerSpawn(4, 10, 20);

			// player depth set
			ArrayList<Integer> depth = new ArrayList<>();
			depth.add(3);
			depth.add(3);
			depth.add(3);
			grid.playerDepthInit(depth);

			// teams set
			Set<Integer> set = new HashSet<>();
			set.add(1);
			set.add(2);
			grid.teamAffectation(set, 1);
			Set<Integer> set2 = new HashSet<>();
			set2.add(3);
			grid.teamAffectation(set2, 2);

			// strat set
			GameStrategy strategy = new SoSStrategy();
			GameState game = new GameState(grid);
			grid.initSosMatrix();
			int move;
			long startTime = System.nanoTime();
			Set<Integer> excludedPlayers = new HashSet<>();
			while (!game.getGameStatus()) {

				int[] nextMove = new int[grid.getNbJoueurs()];
				for (int player = 1; player <= grid.getNbJoueurs(); player++) {
					strategy.evaluatePosition(game, player, this.grid.getPalyerDepth().get(player));
					move = strategy.getMove();
					if (move == -1) {
						excludedPlayers.add(player);
					}
					nextMove[player - 1] = move;
				}
				for (int player = 0; player < nextMove.length; player++) {
					grid.move(player + 1, nextMove[player]);
				}
				GameState.debug(game.getSosMatrix());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
			long endTime = System.nanoTime();
			long elapsedTime = (endTime - startTime) / 1000000;
			System.out.println("Time : " + elapsedTime);
			System.out.println("winner : " + game.getWinner(excludedPlayers));
			System.out.println("end");
			nbOfGames++;
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		int frameHeight = 600;
		int frameWidth = 600;
		int gridHeight = frameHeight / grid.getHauteur();
		int gridWidth = frameWidth / grid.getLargeur();
		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < grid.getHauteur(); i++) {
			for (int j = 0; j < grid.getLargeur(); j++) {
				g2.setColor(Color.black);
				g2.drawRect(j * gridWidth, i * gridHeight, gridWidth, gridHeight);
				if (grid.getGrille()[i][j] > 0) {
					g2.setColor(this.color[grid.getGrille()[i][j] - 1]);
					g2.fillRect(j * gridWidth, i * gridHeight, gridWidth, gridHeight);
				} else if (grid.getGrille()[i][j] == -1) {
					g2.setColor(Color.blue);
					g2.fillRect(j * gridWidth - 4, i * gridHeight - 4, gridWidth, gridHeight);
				} else if (i == 0 || j == 0 || j == this.grid.getLargeur() - 1 || i == this.grid.getHauteur() - 1) {
					g2.setColor(Color.black);
					g2.fillRect(j * gridWidth, i * gridHeight, gridWidth, gridHeight);
				}
			}
		}
	}
}
