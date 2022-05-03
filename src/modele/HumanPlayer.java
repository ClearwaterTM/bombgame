package modele;

import java.util.*;

import modele.Case.Type;

/**
 * <b>AbstractPlayer est une classe qui répresente un joueur humain. Elle hérite de AbstractPlayer.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */

public class HumanPlayer extends AbstractPlayer
{
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise le joueur humain avec les variables nécessaires.</p>
     *
     * @param playerNum
     * 		La taille du plateau.
     * @param boardSize
     * 		La taille du plateau.
     * @param gameBoard
     * 		La taille du plateau.
     */
	public HumanPlayer(int playerNum, int boardSize, Board gameBoard)
	{
		Random rand = new Random();
		boolean validCoords = false;


		this.symbol = Integer.toString(playerNum).charAt(0);
		this.energy = GameSettings.playerStartingEnergy;
		this.isAlive = true;
		
		//Tant que une case valide n'a pas été trouvé, génerer des coordonées aléatoires pour définir l'endroit de départ du joueur.
		while(!(validCoords))
		{
			Coords newCoords = new Coords(rand.nextInt(boardSize),rand.nextInt(boardSize));
			if(gameBoard.gameBoard[newCoords.x][newCoords.y].caseType == Type.EMPTY)
			{
				this.coordinates = newCoords;
				validCoords = true;
			}
		}
		
		//Ajouter un listener au joueur.
		this.addListener(null);
	}
	

}
