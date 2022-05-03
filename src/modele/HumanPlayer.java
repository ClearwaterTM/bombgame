package modele;

import java.util.*;

import modele.Case.Type;

/**
 * <b>AbstractPlayer est une classe qui r�presente un joueur humain. Elle h�rite de AbstractPlayer.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */

public class HumanPlayer extends AbstractPlayer
{
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise le joueur humain avec les variables n�cessaires.</p>
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
		
		//Tant que une case valide n'a pas �t� trouv�, g�nerer des coordon�es al�atoires pour d�finir l'endroit de d�part du joueur.
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
