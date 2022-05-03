package modele;

import java.util.*;

import modele.Case.Type;
import modele.weapons.Mine;

/**
 * <b>RandomCpu est une classe  qui represente un joueur ordinateur qui prend des coups à l'aléatoire.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public class RandomCpu extends AbstractPlayer
{
	/**
     * 	<b>Un générateur de nombre aléatoire.</b>
     */
	private Random rand;
	/**
     * 	<b>Un booléan qui dicte si le joueur a déjà tenté de tirer vers un autre joueur.</b>
     */
	private boolean hasAlreadyShot;
	
    /**
     * <b>Constructeur de classe</b>
     *
     * <p>Initalise le joueur ordinateur.</p>
     *
     * @param playerNum
     * 			La symbole du joueur representé sur le plateau du jeu.
     * 
     * @param boardSize
     * 			La taille du plateau.
     * 
     * @param gameBoard
     * 			Le plateau du jeu.
     */
	public RandomCpu(int playerNum, int boardSize, Board gameBoard)
	{
		this.rand = new Random();
		boolean validCoords = false;

		this.symbol = Integer.toString(playerNum).charAt(0);
		this.energy = GameSettings.playerStartingEnergy;
		this.hasAlreadyShot = false;
		this.isAlive = true;
		
		
		while(!(validCoords))
		{
			Coords newCoords = new Coords(rand.nextInt(boardSize),rand.nextInt(boardSize));
			if(gameBoard.gameBoard[newCoords.x][newCoords.y].caseType == Type.EMPTY)
			{
				this.coordinates = newCoords;
				validCoords = true;
			}
		}
	}
	
    /**
     * 	<b>Vérifier si une case dans la direction recherché est libre.</b>
     * @param moveType
     * 		Le direction du case cherché.
     * 
     * @param gameBoard
     * 		Le plateau du jeu.
     * 
     * @return <code>true</code> si le case est vide, sinon <code>false</code>.
     */
	public boolean isCaseFree(int moveType, Board gameBoard)
	{
		int moveX = 0;
		int moveY = 0;
		
		Coords currentPlayerCoords = this.coordinates;
		System.out.println(currentPlayerCoords);
		
		switch(moveType)
		{
		case 1: {moveX = 0; moveY = -1;break;} //LEFT
		case 2: {moveX = 0; moveY = 1;break;} //RIGHT
		case 3: {moveX = -1; moveY = 0;break;} //UP
		case 4: {moveX = 1; moveY = 0;break;} //DOWN
		}
		
		//Vérifier qu'on ne va pas hors de portée.
		if (((currentPlayerCoords.x + moveX < 0) || (currentPlayerCoords.x + moveX > gameBoard.boardSize))
		|| ((currentPlayerCoords.y + moveY < 0) || (currentPlayerCoords.y + moveY > gameBoard.boardSize)))
		{
			return false;
		}
		//Vérifier si la case à une bombe ou une mine, pour éviter qu'on se déplace sur notre arme.
		else
			{
				if(gameBoard.gameBoard[currentPlayerCoords.x + moveX][currentPlayerCoords.y + moveY].caseState instanceof Mine)
				{
					Mine bmb = (Mine)gameBoard.gameBoard[currentPlayerCoords.x + moveX][currentPlayerCoords.y + moveY].caseState;
					if(bmb.getPlayer() == this)
					{
						return false;
					}
					else
					{
						return true;
					}
				}
			}
		return true;
	}
	
	
    /**
     * 	<b>Vérifier si un joueur se trouve sur la même ligne horiontal/vertical que le propre joueur.</b>
     * @param proxyBoard
     * 		Le proxy du plateau du jeu.
     * 
     * @return Un numéro representant la direction du joueur. 0 si aucun joueur a été trouvé.
     */
	public int getEnemyInVicinity(ProxyBoard proxyBoard)
	{
		Coords currentCpuCoords = this.coordinates;

		//Vérifier à gauche
		for(int y = currentCpuCoords.y; y > 0; y--)
		{	
			if(proxyBoard.currentBoard.gameBoard[currentCpuCoords.x][y].caseState instanceof Obstacle)
			{
				break;
			}
			
			if(proxyBoard.currentBoard.gameBoard[currentCpuCoords.x][y].caseState instanceof AbstractPlayer)
			{
				AbstractPlayer plr = (AbstractPlayer)proxyBoard.currentBoard.gameBoard[currentCpuCoords.x][y].caseState;
				System.out.println(plr.symbol);
				if(plr.symbol != this.symbol)
				{
					return 1;
				}
			}
		}
		
		//Vérifier à droite
		for(int y = currentCpuCoords.y; y < GameSettings.boardSize-1; y++)
		{	
			if(proxyBoard.currentBoard.gameBoard[currentCpuCoords.x][y].caseState instanceof Obstacle)
			{
				break;
			}
			
			if(proxyBoard.currentBoard.gameBoard[currentCpuCoords.x][y].caseState instanceof AbstractPlayer)
			{
				AbstractPlayer plr = (AbstractPlayer)proxyBoard.currentBoard.gameBoard[currentCpuCoords.x][y].caseState;
				if(plr.symbol != this.symbol)
				{
					return 2;
				}
			}
		}
		
		//Vérifier en haut
		for(int x = currentCpuCoords.x; x > 0; x--)
		{	
			if(proxyBoard.currentBoard.gameBoard[x][currentCpuCoords.y].caseState instanceof Obstacle)
			{
				break;
			}
			
			if(proxyBoard.currentBoard.gameBoard[x][currentCpuCoords.y].caseState instanceof AbstractPlayer)
			{
				AbstractPlayer plr = (AbstractPlayer)proxyBoard.currentBoard.gameBoard[x][currentCpuCoords.y].caseState;
				if(plr.symbol != this.symbol)
				{
					return 3;
				}
			}
		}
		
		//Vérifier en bas
		for(int x = currentCpuCoords.x; x < GameSettings.boardSize-1; x++)
		{	
			if(proxyBoard.currentBoard.gameBoard[x][currentCpuCoords.y].caseState instanceof Obstacle)
			{
				break;
			}
			
			if(proxyBoard.currentBoard.gameBoard[x][currentCpuCoords.y].caseState instanceof AbstractPlayer)
			{
				AbstractPlayer plr = (AbstractPlayer)proxyBoard.currentBoard.gameBoard[x][currentCpuCoords.y].caseState;
				if(plr.symbol != this.symbol)
				{
					return 4;
				}
			}
		}
		
		return 0;
	}
	
    /**
     * 	<b>Vérifier si un joueur se trouve sur la même ligne horiontal/vertical que le propre joueur.</b>
     * @param board
     * 		Le plateau du jeu.
     * 
     * @param proxyBoard
     * 		Le proxy du plateau du jeu.
     * 
     * @return Un ArrayList de données qui seront envoyé au classe Game pour traiter et effectuer l'action prise par le joueur ordinateur.
     */
	public ArrayList<Integer> calculateAction(Board board, ProxyBoard proxyBoard)
	{
		System.out.println("CPU TURN");
		if(this.getEnemyInVicinity(proxyBoard) != 0)
		{
			if(!(this.hasAlreadyShot))
			{
				//Si on peut directement voir un ennemi, les tirer dessus.
				ArrayList<Integer> shootData = new ArrayList<Integer>();
				
				shootData.add(4);
				shootData.add(this.getEnemyInVicinity(proxyBoard));
				
				this.hasAlreadyShot = true;
				
				return(shootData);
			}
			else
			{
				
				//Si on a déjà tiré, essayer de se déplacer.
				
				//Chercher tous les cases disponibles, et chosir à l'aléatoire une case auquel se déplacer.
				int enemyDirection = this.getEnemyInVicinity(proxyBoard);

				ArrayList<Integer> availableCases = new ArrayList<Integer>();
				
				for(int a = 1; a <= 4; a++)
				{
					if(this.isCaseFree(a, board))
					{
						availableCases.add(a);
					}
				}
				
				ArrayList<Integer> dataToReturn = new ArrayList<Integer>();
				dataToReturn.add(1); //Move
				dataToReturn.add(availableCases.get(this.rand.nextInt(availableCases.size()))); //Direction to move in
				System.out.println(dataToReturn);
				
				this.hasAlreadyShot = false;
				
				return dataToReturn;

			}
			
		}
		else
		{
			//S'il n'y a aucun autre joueur dans une ligne droite, vérifier si un joueur est à une distance minimum du joueur ordinateur.
			//Si oui, poser une bombe/mine dans leur direction.
			
			for(int x = 0; x < board.boardSize;x++)
			{
				for (int y = 0; y < board.boardSize;y++)
				{
					if(board.gameBoard[x][y].caseState instanceof AbstractPlayer)
					{
						AbstractPlayer plr = (AbstractPlayer)board.gameBoard[x][y].caseState;
						//Vérifie qu'on ne compare pas contre nous-même.
						if(plr.symbol != this.symbol)
						{
							//Vérifier la distance entre nous et l'autre joueur.
							int distance = Math.abs(plr.coordinates.x - this.coordinates.x) + Math.abs(plr.coordinates.y - this.coordinates.y);
							
							if(distance <= 4)
							{
								//Trouvez la direction générale de l'autre joueur.
								int playerDirection = 0;
								
								if(plr.coordinates.x > this.coordinates.x && plr.coordinates.y > this.coordinates.y)
								{
									//Down-right
									playerDirection = 4;
									
								}
								else if (plr.coordinates.x < this.coordinates.x && plr.coordinates.y > this.coordinates.y)
								{
									//Up-Right
									playerDirection = 2;
								}
								else if (plr.coordinates.x < this.coordinates.x && plr.coordinates.y < this.coordinates.y)
								{
									//Up-Left
									playerDirection = 8;
								}
								else if (plr.coordinates.x > this.coordinates.x && plr.coordinates.y < this.coordinates.y)
								{
									//Down-left
									playerDirection = 4;
								}
								else if (plr.coordinates.x > this.coordinates.x)
								{
									//Down
									playerDirection = 1;
								}
								else if (plr.coordinates.y > this.coordinates.y)
								{
									//Right
									playerDirection = 3;
								}
								else if (plr.coordinates.x < this.coordinates.x)
								{
									//Up
									playerDirection = 5;
								}
								else if (plr.coordinates.y < this.coordinates.y)
								{
									//Left
									playerDirection = 7;
								}
								
								//Définir à l'aléatoire si on pose une bombe ou une mine, et son visibilité.
								
								int weaponChoice = rand.nextInt(1);
								int visibilityChoice = rand.nextInt(1);
								
								//Enfin, faire l'action.
								ArrayList<Integer> dataToReturn = new ArrayList<Integer>();
								
								dataToReturn.add(weaponChoice == 0 ? 2 : 3);
								dataToReturn.add(playerDirection);
								dataToReturn.add(visibilityChoice == 0 ? 2 : 3);
								return(dataToReturn);
								
								
								
							}
						}
					}
				}
			}
		}
		//Si aucune autre action est possible, activer le bouclier.
		ArrayList<Integer> dataToReturn = new ArrayList<Integer>();
		
		dataToReturn.add(5);
		return(dataToReturn);
	}
}
