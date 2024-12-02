package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Grille {
	private Integer[][] grille;
	private final int hauteur;
	private final int largeur;
	private final int nbJoueurs;
	private final int nbEquipes;
	private Map<Integer, List<Integer>> teams;
	private Map<Integer, Point> playersPos;
	private final Map<Integer, Integer> palyerDepth;
	public double[][] sosMatrix;
	private List<Point> initCoordonees ; 

	public Grille(int hauteur, int largeur, int nbJoueurs, int nbEquipes) {
		this.grille = new Integer[hauteur][largeur];
		this.playersPos = new HashMap<>();
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.nbEquipes = nbEquipes;
		this.nbJoueurs = nbJoueurs;
		this.teams = new HashMap<>();
		this.palyerDepth = new HashMap<>();
		this.initCoordonees = new ArrayList<>();
	}

	public void grilleInit() {
		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				this.grille[i][j] = 0;
			}
		}
	}

	public void playerDepthInit(List<Integer> depthList) {
		for (int i = 0; i < depthList.size(); i++) {
			this.palyerDepth.put(i + 1, depthList.get(i));
		}
	}

//----------------------------------------------SPAWN----------------------------------------------------------------------------------------------------------------------------------------

	/*
	 * Random spawn the snakes
	 */
	public void randomSpawn() {
		if (this.nbJoueurs > (this.hauteur * this.largeur) - ((this.hauteur - 2) * 2 + (this.largeur * 2))) {
			System.out.println("not enough space");
			return;
		}
		int joueur = 1;
		int randomPosX, randomPosY;
		Random random = new Random();
		while (joueur <= this.nbJoueurs) {
			randomPosX = random.nextInt(this.hauteur - 2) + 1;
			randomPosY = random.nextInt(this.largeur - 2) + 1;
			if (this.grille[randomPosX][randomPosY] == 0) {
				this.playersPos.put(joueur, new Point(randomPosX, randomPosY));
				this.initCoordonees.add(new Point(randomPosX,randomPosY));
				this.grille[randomPosX][randomPosY] = joueur;
				joueur++;
			}
		}
	}

	public void manualPlayerSpawn(int player, int x, int y) {
		this.playersPos.put(player, new Point(x, y));
		this.initCoordonees.add(new Point(x,y));
		this.grille[x][y] = player;
	}

//------------------------------------------TEAM MANAGEMENT------------------------------------------------------------------------------------------------------------------------------------------------

	// Manual team affectation
	public void teamAffectationKeyeEntry() {
		Scanner sc = new Scanner(System.in);
		int nb = 1;
		String stop;
		int player;
		while (nb <= this.nbEquipes) {
			List<Integer> players = new ArrayList<>();
			System.out.println("Enter the players of team : " + nb);
			stop = "N";
			while (stop == "N") {
				player = sc.nextInt();
				if (player > 0 && player <= this.nbJoueurs) {
					players.add(player);
				}
				System.out.println("Do you want to add more players to team : "+nb+" (Y/N ?)");
				stop = sc.next();
			}
			this.teams.putIfAbsent(nb, players);
			nb++;
		}

	}

	public void teamAffectation(List<Integer> players, int team) {
		this.teams.putIfAbsent(team, players);
	}

	// Equitable team affectation

	public boolean teamAffectationAuto() {
		if ((((double) this.nbJoueurs / this.nbEquipes) % 2 == 0 || this.nbEquipes == this.nbJoueurs)
				&& this.nbEquipes <= this.nbJoueurs) {
			int ratio = this.nbJoueurs / this.nbEquipes;
			int playerCount = 1;
			for (int team = 1; team <= this.nbEquipes; team++) {
				List<Integer> teamList = new ArrayList<>();
				for (int player = 0; player < ratio; player++) {
					teamList.add(playerCount);
					playerCount++;
				}
				this.teams.put(team, teamList);
			}
			return true;
		}
		return false;
	}

//--------------------------------------------GAME MECHANICS---------------------------------------------------------------------------------------------------------------------------------
	/*
	 * Game mechanics
	 */
	/*
	 * movements 0->right 1->left 2->down 3->up
	 */
	public boolean move(int player, int direction) {
		if (this.playersPos.containsKey(player)) {
			Point playerPos = this.playersPos.get(player);
			Point newPoint = this.actions((int) playerPos.getX(), (int) playerPos.getY(), direction);
			if (newPoint != null) {
				this.playersPos.put(player, new Point((int) newPoint.getX(), (int) newPoint.getY()));
				this.grille[(int) newPoint.getX()][(int) newPoint.getY()] = player;
				this.grille[(int) playerPos.getX()][(int) playerPos.getY()] = -1;
			} else {
				return true;
			}
			return false;
		}
		return true;
	}

	public Point actions(int posX, int posY, int direction) {
		switch (direction) {
		case 0:
			if (!collision(posX, posY + 1)) {
				return new Point(posX, posY + 1);
			}
			break;
		case 1:
			if (!collision(posX, posY - 1)) {
				return new Point(posX, posY - 1);
			}
			break;
		case 2:
			if (!collision(posX + 1, posY)) {
				return new Point(posX + 1, posY);
			}
			break;
		case 3:
			if (!collision(posX - 1, posY)) {
				return new Point(posX - 1, posY);
			}
			break;
		default:
			return null;
		}
		return null;
	}

	/*
	 * Methode to get all possible actions for player x
	 */
	public List<Integer> possibleActions(int player) {
		if (this.playersPos.containsKey(player)) {
			List<Integer> res = new ArrayList<>();
			Point playerPos = this.playersPos.get(player);
			for (int i = 0; i < 4; i++) {
				if (this.actions(playerPos.x, playerPos.y, i) != null) {
					res.add(i);
				}
			}
			return res;
		}
		return new ArrayList<Integer>();
	}

	/*
	 * player end Game
	 */
	// wall and border collision : checks if the player collided with a wall or
	// border

	public boolean collision(int posX, int posY) {
		return posX == this.hauteur - 1 || posY == this.largeur - 1 || posX == 0 || posY == 0
				|| this.grille[posX][posY] == -1 || this.grille[posX][posY] >= 1;
	}

	public boolean gameStatus() {
		return this.playersPos.size() == 0 || this.playersPos.size() == 1;
	}

	/*
	 * Setting the sos Matrix for SOS IA
	 */

	public void initSosMatrix() {
		double[][] res = new double[this.nbJoueurs][this.nbJoueurs];
		Map<Integer, List<Integer>> teams = this.teams;
		for (int row = 0; row < res.length; row++) {
			for (int col = 0; col < res.length; col++) {
				if (row == col) {
					res[row][col] = 1;
				} else {
					for (Integer nb : teams.keySet()) {
						List<Integer> team = teams.get(nb);
						if (team.contains(row + 1)) {
							if (team.contains(col + 1)) {
								res[row][col] = 1;
							} else {
								res[row][col] = -1;
							}
						}
					}
				}
			}
		}
		this.sosMatrix = res;
	}

//----------------------------------------------------GRID MANAGEMENT-------------------------------------------------------------------------------------------------------------------------------------------------------

	/*
	 * Grid copy
	 */
	public Integer[][] getTempGrid() {
		Integer[][] tempGrid = new Integer[this.hauteur][this.hauteur];
		for (int i = 0; i < this.hauteur; i++) {
			System.arraycopy(this.grille[i], 0, tempGrid[i], 0, this.largeur);
		}
		return tempGrid;
	}

	/*
	 * Player pos copy
	 */
	public Map<Integer, Point> getTempPlayerPos() {
		Map<Integer, Point> temp = new HashMap<>(this.playersPos);
		return temp;
	}

	// Grid print
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("");
		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				if (this.grille[i][j] == -1) {
					str.append(" # ");
				} else if (this.grille[i][j] == 0) {
					str.append(" O ");
				} else {
					str.append(" " + this.grille[i][j] + " ");
				}
			}
			str.append("\n");
		}
		return str.toString();
	}

//----------------------------------------------GETTERS AND SETTERS-------------------------------------------------------------

	public Integer[][] getGrille() {
		return grille;
	}

	public void setGrille(Integer[][] grille) {
		this.grille = grille;
	}

	public int getHauteur() {
		return hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public int getNbEquipes() {
		return nbEquipes;
	}

	public Map<Integer, List<Integer>> getTeams() {
		return teams;
	}

	public Map<Integer, Point> getPlayersPos() {
		return playersPos;
	}

	public void setPlayersPos(Map<Integer, Point> playersPos) {
		this.playersPos = playersPos;
	}

	public Map<Integer, Integer> getPalyerDepth() {
		return palyerDepth;
	}

	public void setTeams(Map<Integer, List<Integer>> teams) {
		this.teams = teams;
	}

	public double[][] getSosMatrix() {
		return sosMatrix;
	}

	public void setSosMatrix(double[][] sosMatrix) {
		this.sosMatrix = sosMatrix;
	}

	public List<Point> getInitCoordonees(){
		return this.initCoordonees;
	}

}
