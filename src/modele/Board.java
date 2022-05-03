package modele;

import java.util.*;

import modele.Case.Type;
import modele.weapons.Bomb;
import modele.weapons.Mine;

/**
 * <b>Board est une classe qui represente le plateau du jeu.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */


public class Board extends AbstractModelListener
{
	/**
     * 	<b>Le plateau du jeu, correspondant � un ensemble de objets de type Case.</b>
     */
	
	public Case[][] gameBoard;
	
	/**
     * 	<b>La taille de notre plateau.</b>
     */
	public int boardSize;
	
	/**
     * 	<b>Mettre � jour le plateau en notifiant son listener.</b>
     */
	public void update()
	{
		this.fireChange();
	}
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise le plateau de jeu et les variables n�cessaires.</p>
     *
     * @param inputBoardSize
     * 			La taille du plateau.
     */
	public Board(int inputBoardSize)
	{
		this.gameBoard = new Case[inputBoardSize][inputBoardSize];
		this.boardSize = inputBoardSize;
		for (int a = 0; a < inputBoardSize; a++)
		{
			for (int b = 0; b < inputBoardSize; b++)
			{
				this.gameBoard[a][b] = new Case();
			}
		}
		//Generer les zones d'�nergie et les obstacles.
		this.generateEnergyZones();
		this.generateObstacles();
	}

	
    /**
     * 	<b>D�placer le joueur sur le plateau. Ce fonction renvoie le joueur deplac�.</b>
     * @param inputPlayer
     * 		Le joueur � deplacer.
     * @param moveType
     *		La direction � d�placer le joueur.
     * @return Le joueur deplac�.
     */
	public AbstractPlayer movePlayer(AbstractPlayer inputPlayer,int moveType)
	{
		//Effacer la case actuelle auquel le joueur se trouve.
		this.gameBoard[inputPlayer.coordinates.x][inputPlayer.coordinates.y].caseState = null;
		this.gameBoard[inputPlayer.coordinates.x][inputPlayer.coordinates.y].caseType = Type.EMPTY;

		switch(moveType)
		{
			//Gauche
			case 1:
			{
				inputPlayer.coordinates.y -= 1;
				break;
			}
			//Droite
			case 2:
			{
				inputPlayer.coordinates.y += 1;
				break;
			}
				
			//Haut
			case 3:
			{
				inputPlayer.coordinates.x -= 1;
				break;
			}
			//Bas
			case 4:
			{
				inputPlayer.coordinates.x += 1;
				break;
			}
			default:
			{
				System.out.println("Mouvement invalide.");
				break;
			}
			
		}
		
		if(this.gameBoard[inputPlayer.coordinates.x][inputPlayer.coordinates.y].caseType == Type.ENERGY)
		{
			System.out.println("Une zone d'�nergie a �t� atteinte.");
			EnergyZone eZone = (EnergyZone)this.gameBoard[inputPlayer.coordinates.x][inputPlayer.coordinates.y].caseState;
			inputPlayer.energy += eZone.energyPool;
			System.out.println("Le joueur � recuper� " + eZone.energyPool + " �nergie.");
		}
		if(this.gameBoard[inputPlayer.coordinates.x][inputPlayer.coordinates.y].caseType == Type.MINE)
		{
			System.out.println("Une mine a �t� declench�!");
			this.gameBoard[inputPlayer.coordinates.x][inputPlayer.coordinates.y].objectList[0] = inputPlayer;
			Mine mne = (Mine)this.gameBoard[inputPlayer.coordinates.x][inputPlayer.coordinates.y].caseState;
			mne.explode(this);
			this.gameBoard[inputPlayer.coordinates.x][inputPlayer.coordinates.y].objectList[0] = null;
		}
		if(this.gameBoard[inputPlayer.coordinates.x][inputPlayer.coordinates.y].caseType == Type.BOMB)
		{
			System.out.println("Une bombe a �t� declench�!");
			Bomb bmb = (Bomb)this.gameBoard[inputPlayer.coordinates.x][inputPlayer.coordinates.y].caseState;
			bmb.explode(this);
			
			
		}
		this.gameBoard[inputPlayer.coordinates.x][inputPlayer.coordinates.y].caseType = Type.PLAYER;
		this.gameBoard[inputPlayer.coordinates.x][inputPlayer.coordinates.y].caseState = inputPlayer;
		
		inputPlayer.energy -= 2;
		
		return inputPlayer;
	}
	
    /**
     * 	<b>Ajouter une bombe au plateau du jeu.</b>
     * @param inputPlayer
     * 		Le joueur qui d�pose le bombe
     * @param position
     *		La direction � d�poser la bombe.
     * @param visibility
     *		La visibilit� du bombe.
     */
	public void addBombToBoard(AbstractPlayer inputPlayer, int position, int visibility)
	{
		
		System.out.println(inputPlayer.coordinates.x + "-" + inputPlayer.coordinates.y + "-" + position);

		int posX = inputPlayer.coordinates.x;
		int posY = inputPlayer.coordinates.y;
		
		
		if(weaponOutOfBounds(posX,posY,position))
		{
			System.out.println("Arme est hors de plateau");
			return;
		}
		
		switch(position)
		{
			case 1: {posX--; break;} //North
			case 2: {posX--; posY++; break;} //North East
			case 3: {posY++; break;} //East
			case 4: {posX++; posY++; break;} //South-East
			case 5: {posX++; break;} //South
			case 6: {posX++; posY--; break;} //South-West
			case 7: {posY--; break;} //West
			case 8: {posX--; posY--; break;} //North-West
			default: {break;}

		}
		
		
		this.gameBoard[posX][posY].caseType = Type.BOMB;
		this.gameBoard[posX][posY].caseState = new Bomb(10, new Coords(posX,posY),inputPlayer,(visibility == 1 ? true : false),GameSettings.bombLifetime);
		this.gameBoard[posX][posY].objectList[0] = this.gameBoard[posX][posY].caseState;
		this.update();
	}

    /**
     * 	<b>V�rifier si le d�pot d'une arme s'effectue bien dans le plateau du jeu.</b>
     * @param posX
     * 		Coordon�e X de l'arme � d�poser.
     * @param posY
     *		Coordon�e Y de l'arme � d�poser.
     * @param position
     *		La direction du d�p�t de l'arme.
     *
     * @return True si l'arme est bien dans le plateau, False sinon.
     */
	public boolean weaponOutOfBounds(int posX, int posY, int position)
	{
		System.out.println(position);
		switch(position)
		{
		
			case 1: //Up
			{
				if(posX == 0)
				{
				return true;
				}
				else {return false;}
			}
		
			case 3: //Right
			{
				if(posY == this.boardSize-1)
				{
				return true;
				}
				else {return false;}
			}
		
			case 5: //Down
			{
				if(posX == this.boardSize-1)
				{
				return true;
				}
				else {return false;}
			}
			case 7: //Left
			{
				if(posY == 0)
				{
				return true;
				}
				else {return false;}
			}
			default: {return false;}
		}
	}
	
    /**
     * 	<b>Ajouter une mine au plateau du jeu.</b>
     * @param inputPlayer
     * 		Le joueur qui d�pose le mine.
     * @param position
     *		La direction � d�poser la mine.
     * @param visibility
     *		La visibilit� du mine.
     */
	public void addMineToBoard(AbstractPlayer inputPlayer, int position, int visibility)
	{
		
		
		System.out.println(inputPlayer.coordinates.x + "-" + inputPlayer.coordinates.y);
		
		int posX = inputPlayer.coordinates.x;
		int posY = inputPlayer.coordinates.y;
		
		if(weaponOutOfBounds(posX,posY,position))
		{
			System.out.println("Arme est hors de plateau");
			return;
		}
		else
		{
			switch(position)
			{
				case 1: {posX--; break;} //North
				case 2: {posX--; posY++; break;} //North East
				case 3: {posY++; break;} //East
				case 4: {posX++; posY++; break;} //South-East
				case 5: {posX++; break;} //South
				case 6: {posX++; posY--; break;} //South-West
				case 7: {posY--; break;} //West
				case 8: {posX--; posY--; break;} //North-West
				default: {break;}
			}
			
			this.gameBoard[posX][posY].caseType = Type.MINE;
			this.gameBoard[posX][posY].caseState = new Mine(10,new Coords(posX,posY),inputPlayer,(visibility == 1 ? true : false));
			this.gameBoard[posX][posY].objectList[0] = this.gameBoard[posX][posY].caseState;
			this.update();
			}
		}
	
	
    /**
     * 	<b>Afficher le plateau du jeu.</b>
     */
	public void showBoard()
	{
		System.out.println("Plateau du jeu:");
		
		for (int a = 0; a < this.boardSize; a++)
		{
			for (int b = 0; b < this.boardSize; b++)
			{
				System.out.print(this.gameBoard[a][b] + " ");
			}
			System.out.println("");
		}
		System.out.println("------");
	}
	
    /**
     * 	<b>Initialiser les joueurs, et les mettre sur le plateau du jeu.</b>
     * @param playerList
     * 		Une arrayList de tous les joueurs.
     */
	public void initializePlayers(ArrayList<AbstractPlayer> playerList)
	{
		for(AbstractPlayer currentPlayer : playerList)
		{
			this.gameBoard[currentPlayer.coordinates.x][currentPlayer.coordinates.y].caseState = currentPlayer;
			this.gameBoard[currentPlayer.coordinates.x][currentPlayer.coordinates.y].caseType = Type.PLAYER;
		}
		this.update();
	}
	
    /**
     * 	<b>G�nerer les zones d'�nergie sur le plateau du jeu.</b>
     */
	public void generateEnergyZones()
	{
		Random random = new Random();

		//R�cuperer le nombre de zones � placer a partir des parametres du jeu.
		int zonesToPlace = GameSettings.energyZonesToPlace;

		while(zonesToPlace != 0)
		{
			//G�n�rer des coordon�es x et y al�atoires, v�rifier si la case correspondante et libre, puis inserer le zone d'�nergie dedans.
			int randomX = random.nextInt(this.boardSize);
			int randomY = random.nextInt(this.boardSize);
			
			if(this.gameBoard[randomX][randomY].caseType == Type.EMPTY)
			{
				this.gameBoard[randomX][randomY].caseType = Type.ENERGY;
				this.gameBoard[randomX][randomY].caseState = new EnergyZone();
				zonesToPlace -= 1;
			}
		}
	}
	
    /**
     * 	<b>G�nerer les obstacles sur le plateau du jeu.</b>
     */
	public void generateObstacles()
	{
		Random random = new Random();
		
		//R�cuperer le nombre d'obstacles � placer a partir des parametres du jeu.
		int zonesToPlace = GameSettings.obstaclesToPlace;
	
		while(zonesToPlace != 0)
		{
			//G�n�rer des coordon�es x et y al�atoires, v�rifier si la case correspondante et libre, puis inserer l'obstacle dedans.
			int randomX = random.nextInt(this.boardSize);
			int randomY = random.nextInt(this.boardSize);
			
			if(this.gameBoard[randomX][randomY].caseType == Type.EMPTY)
			{
				this.gameBoard[randomX][randomY].caseType = Type.OBSTACLE;
				this.gameBoard[randomX][randomY].caseState = new Obstacle();
				zonesToPlace -= 1;
			}
		}
	}
	


	
}
