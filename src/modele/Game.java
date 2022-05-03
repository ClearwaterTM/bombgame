package modele;

import java.util.*;

import modele.Case.Type;
import modele.weapons.*;
import vue.*;

/**
 * <b>Game est une classe qui represente les donn�es et boucle principale du jeu.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */

public class Game
{
	/**
     * 	<b>Le plateau du jeu, correspondant � un ensemble de objets de type Case.</b>
     */
	public Board gameBoard;
	
	/**
     * 	<b>Le vue, correspondant au vue �coutable de notre plateau du jeu.</b>
     */
	public VueBoard boardView;
	
	/**
     * 	<b>Un ArrayList de AbstractPlayers correspondant aux liste de joueurs qui participent au jeu.</b>
     */
	private ArrayList<AbstractPlayer> currentPlayers;
	
	/**
     * 	<b>Un ArrayList de VuePlayers correspondant aux vues de chaque joueur.</b>
     */
	
	private ArrayList<VuePlayer> currentPlayerViews;
	
	/**
     * 	<b>Un Iterator de AbstractPlayer correspondant aux vues de chaque joueur.</b>
     */
	
	
	/**
     * 	<b>Le joueur actuel, de type AbstractPlayer.</b>
     */
	private AbstractPlayer currentPlayer;
	
	/**
     * 	<b>Boolean pour attendre le choix de coup du joueur actuel via l'interface graphique.</b>
     */
	public boolean awaitingPlayerInput;
	
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise le boucle de jeu et les variables n�cessaires.</p>
     *
     * @param humanPlayers
     * 			Nombre de joueurs humains.
     * @param cpuPlayers
     * 			Nombre de joueurs ordinateurs
     */
	public Game(int humanPlayers, int cpuPlayers)
	{
		this.awaitingPlayerInput = false;
		
		this.gameBoard = new Board(GameSettings.boardSize);
		this.boardView = new VueBoard(this.gameBoard);
		
		this.currentPlayers = new ArrayList<AbstractPlayer>();
		this.currentPlayerViews = new ArrayList<VuePlayer>();
		
		for(int a = 0; a < humanPlayers; a++)
		{
			AbstractPlayer humanToAdd = new HumanPlayer(a+1,this.gameBoard.boardSize,this.gameBoard);
			this.currentPlayers.add(humanToAdd);
			this.currentPlayerViews.add(new VuePlayer(humanToAdd));
			
		}
		
		for(int b = 0; b < cpuPlayers; b++)
		{
			AbstractPlayer cpuToAdd = new RandomCpu(humanPlayers+b+1,this.gameBoard.boardSize,this.gameBoard);
			this.currentPlayers.add(cpuToAdd);
			this.currentPlayerViews.add(new VuePlayer(cpuToAdd));
			
		}
	}
	
	
    /**
     * 	<b>R�cuperer le joueur en train de jouer.</b>
     * @return Le joueur actuel.
     */
	public AbstractPlayer getCurrentPlayer()
	{
		return this.currentPlayer;
	}
	
    /**
     * 	<b>Traiter la fin de chaque tour du joueur. Ce fonction renvoie le plateau du jeu mise � jour.</b>
     * @param gameBoard
     * 		Le plateau du jeu � mettre � jour.
     * @return Le plateau du jeu mise � jour.
     */
	public Board processEndOfTurn(Board gameBoard)
	{
		//Parcourir le plateau du jeu, et d�crementer la dur�e de vie de chaque bombe.
		for (int x = 0; x < gameBoard.boardSize; x++)
		{
			for (int y = 0; y < gameBoard.boardSize; y++)
			{
				if(gameBoard.gameBoard[x][y].caseState instanceof Bomb)
				{
					Bomb bmb = (Bomb)gameBoard.gameBoard[x][y].caseState;
					
					//Exploser toute bombe auquel son dur�e de vie � atteint 0.
					if(bmb.isExploding(gameBoard))
					{
						bmb.explode(gameBoard);
						gameBoard.gameBoard[x][y].caseState = null;
						gameBoard.gameBoard[x][y].caseType = Type.EMPTY;
						
						//Il est aussi n�cessaire de supprimer la bombe explos� du plateau du jeu proxy.
						this.boardView.board.gameBoard[x][y].caseState = null;
						this.boardView.board.gameBoard[x][y].caseType = Type.EMPTY;
						
					}
				}
			}
		}
		
		//V�rifier si un jouer est mort. Si oui, le supprimer du liste de joueurs jouant.
		for(Iterator<AbstractPlayer> playerIt = this.currentPlayers.iterator();playerIt.hasNext();)
		{
			AbstractPlayer plr = playerIt.next();
			if(plr.energy <= 0 && plr.isAlive)
			{
				System.out.println("Le joueur " + plr.symbol + " a �t� elimin�.");
				
				this.gameBoard.gameBoard[plr.coordinates.x][plr.coordinates.y].caseState = null;
				this.gameBoard.gameBoard[plr.coordinates.x][plr.coordinates.y].caseType = Type.EMPTY;
				
				this.boardView.board.gameBoard[plr.coordinates.x][plr.coordinates.y].caseState = null;
				this.boardView.board.gameBoard[plr.coordinates.x][plr.coordinates.y].caseType = Type.EMPTY;
				
				plr.isAlive = false;
				
			}
		}
		return gameBoard;
	}
	
    /**
     * 	<b>Traiter la d�but de chaque tour du joueur. Ce fonction renvoie le joueur courant.</b>
     * @param inputPlayer
     * 		Le joueur auquel son tour se joue.
     * @return Le joueur trait�.
     */
	public AbstractPlayer processStartOfTurn(AbstractPlayer inputPlayer)
	{
		System.out.println("C'est au tour du joueur " + inputPlayer.symbol + ".");
		//D�sactiver le bouclier du joueur si ce premier est activ�.
		inputPlayer.shield = false;
		return inputPlayer;
	}

	
    /**
     * 	<b>V�rifier si une case est libre et disponible pour le depot d'une bombe ou une mine. Ce fonction renvoie True si la case est libre, et False sinon.</b>
     * @param inputPlayer
     * 		Le joueur auquel son tour se joue.
     * 
     * @param position
     * 		La case a verifier, represent� par une valeur de 1 a 8.
     * 
     * @return True si la case est libre, false sinon.
     */
	public boolean isBombCaseFree(AbstractPlayer inputPlayer, int position)
	{
		int moveX = 0;
		int moveY = 0;
		
		Coords currentPlayerCoords = inputPlayer.coordinates;
	
		switch(position)
		{
			case 1: {moveX = -1; moveY = 0;break;} //NORD
			case 2: {moveX = 1; moveY = -1;break;} //NORD-EST
			case 3: {moveX = 0; moveY = 1;break;} //EST
			case 4: {moveX = 1; moveY = 1;break;} //SUD-EST
			case 5: {moveX = 1; moveY = 0;break;} //SUD
			case 6: {moveX = -1; moveY = 1;break;} //SUD-OUEST
			case 7: {moveX = 0; moveY = -1;break;} //OUEST
			case 8: {moveX = -1; moveY = -1;break;} //NORD-OUEST
		}
		
		//V�rifier qu'on ne sort pas du plateau du jeu.
		if (((currentPlayerCoords.x + moveX < 0) || (currentPlayerCoords.x + moveX > this.gameBoard.boardSize-1))
		|| ((currentPlayerCoords.y + moveY < 0) || (currentPlayerCoords.y + moveY > this.gameBoard.boardSize-1)))
		{
			System.out.println("Hors de port�e.");
			return false;
		}
		
		//V�rifier que la case est bien libre.
		else if(this.gameBoard.gameBoard[currentPlayerCoords.x + moveX][currentPlayerCoords.y + moveY].caseState == null) 
		{
			return true;
		}
		else
		{
			System.out.println("La case choisie n'est pas libre.");
			return false;
		}
		
	}
	
	
    /**
     * 	<b>V�rifier si une case est libre et disponible pour d�placer un joueur dessus. Ce fonction renvoie True si la case est libre, et False sinon.</b>
     * @param inputPlayer
     * 		Le joueur auquel son tour se joue.
     * 
     * @param moveType
     * 		La case a verifier, represent� par une valeur de 1 a 4.
     * 
     * @return true si la case est libre, false sinon.
     */
	public boolean isCaseFree(AbstractPlayer inputPlayer, int moveType)
	{
		int moveX = 0;
		int moveY = 0;
		
		Coords currentPlayerCoords = inputPlayer.coordinates;
		
		switch(moveType)
		{
			case 1: {moveX = 0; moveY = -1;break;} //GAUCHE
			case 2: {moveX = 0; moveY = 1;break;} //DROITE
			case 3: {moveX = -1; moveY = 0;break;} //HAUT
			case 4: {moveX = 1; moveY = 0;break;} //BAS
		}
		
		//V�rifier qu'on ne sort pas du plateau du jeu.
		if (((currentPlayerCoords.x + moveX < 0) || (currentPlayerCoords.x + moveX >= this.gameBoard.boardSize))
		|| ((currentPlayerCoords.y + moveY < 0) || (currentPlayerCoords.y + moveY >= this.gameBoard.boardSize)))
		{
			System.out.println("Hors de port�e.");
			return false;
		}
		//V�rifier que la case porte une bombe ou une mine. Si oui, on doit empecher le mouvement du joueur sur son propre arme!
		else if (this.gameBoard.gameBoard[currentPlayerCoords.x + moveX][currentPlayerCoords.y + moveY].caseState instanceof AbstractFixedWeapon)
		{
			AbstractFixedWeapon wpn = (AbstractFixedWeapon)this.gameBoard.gameBoard[currentPlayerCoords.x + moveX][currentPlayerCoords.y + moveY].caseState;
			if(wpn.getPlayer() == inputPlayer)
			{
				System.out.println("On ne peut pas d�placer sur votre bombe/mine!");
				return false;
			}
			else
			{
				return true;
			}
		}
		
		//V�rifier que la case est bien libre.
		else if((this.gameBoard.gameBoard[currentPlayerCoords.x + moveX][currentPlayerCoords.y + moveY].caseState == null) 
		||(this.gameBoard.gameBoard[currentPlayerCoords.x + moveX][currentPlayerCoords.y + moveY].caseState instanceof EnergyZone))
		{
			return true;
		}
		else
		{
			System.out.println("La case choisie n'est pas libre.");
			return false;
		}
	}	

    /**
     * 	<b>Obtenir le direction de tir si un joueur decide de tirer lors de son tour.</b>
     * @param fireDir
     * 		Le direction de tir, represent� par une valeur de 1 � 4.
     * @return L'�num du direction obtenu.
     */
	public DirectionEnum getFireDirection(int fireDir)
	{
		switch(fireDir)
		{
			case 1: return DirectionEnum.LEFT;
			case 2: return DirectionEnum.RIGHT;
			case 3: return DirectionEnum.UP;
			case 4: return DirectionEnum.DOWN;
			default: return null;
		}
	}
    /**
     * 	<b>Signaler au jeu de tirer dans une direction demand�, en cr�ent un objet Shot.</b>
     * @param inputPlayer
     * 		Le joueur auquel son tour se joue.
     * 
     * @param fireDirection
     * 		La direction � tirer dedans, represent� par une valeur de 1 a 4.
     */
	public void promptFireAction(AbstractPlayer inputPlayer, int fireDirection)
	{
		Shot shot = new Shot(5,this.gameBoard.boardSize,this.getFireDirection(fireDirection),inputPlayer.coordinates,this.gameBoard);
	}
	
    /**
     * 	<b>Activer le bouclier du joueur.</b>
     * @param inputPlayer
     * 		Le joueur auquel son tour se joue.
     */
	public void activateShield(AbstractPlayer inputPlayer)
	{
		inputPlayer.shield = true;
	}
	
    /**
     * 	<b>V�rifier si la partie est termin�, en v�rifiant s'il n'y a qu'une seule joueur toujours vivante.</b>
     * 
     * @return True si la partie est termin�, sinon False.
     */
	public boolean isOver()
	{
		int alivePlayers = 0;
		
		for (AbstractPlayer plr : this.currentPlayers)
		{
			if(plr.isAlive)
			{
				alivePlayers++;
			}
		}
		
		if(alivePlayers == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
    /**
     * 	<b>R�cuprer le joueur qui a gagn� la partie.</b>
     * @return Le joueur qui a gagn� la partie.
     */
	public AbstractPlayer getWinner()
	{
		for (AbstractPlayer player : this.currentPlayers)
		{
			if(player.isAlive)
			{
				return(player);
			}
		}
		return null;
	}
	
    /**
     * 	<b>D�marrer le boucle du jeu principale.</b>
     */
	public void startGameLoop()
	{
		System.out.println("D�marrage du jeu...");
		
		//Initialiser tous les joueurs, leurs vues, et leurs plateaus du jeu en proxy.
		gameBoard.initializePlayers(this.currentPlayers);
		ProxyBoard proxBoard = new ProxyBoard(this.gameBoard,this.currentPlayers.get(0));
		VueProxyBoard vueProxBoard = new VueProxyBoard(proxBoard);
		
		//Le boucle du jeu principale se d�marre ici.
		while(!this.isOver())
		{
			for(Iterator<AbstractPlayer> playerIterator = this.currentPlayers.iterator(); playerIterator.hasNext();)
			{
				if(this.isOver())
				{
					break;
				}
				else
				{
					AbstractPlayer nextPlayer = playerIterator.next();
					nextPlayer = this.processStartOfTurn(nextPlayer);
					if(nextPlayer.isAlive)
					{
						this.currentPlayer = nextPlayer;
						proxBoard.currentPlayer = nextPlayer;
						
						System.out.println("Plateau du jeu:");
						proxBoard.update();
						
						if(nextPlayer instanceof HumanPlayer)
						{
							
							this.awaitingPlayerInput = true;
							while(awaitingPlayerInput == true)
							{
								//Tant que le joueur n'a pas saisi son coup avec l'interface graphique, nous l'attendons ici.
								try
								{
								    Thread.sleep(500);
								}
								catch(InterruptedException ex)
								{
								    Thread.currentThread().interrupt();
								}
								
							}
						}
						else if(nextPlayer instanceof RandomCpu)
						{
							System.out.println("CPU turn");
							RandomCpu cpu = (RandomCpu)nextPlayer;
							ArrayList<Integer> cpuMoveData = cpu.calculateAction(gameBoard, proxBoard);
							
							//Traiter les donn�es envoy�s par le joueur ordinateur pour jouer son coup.
							if(cpuMoveData != null)
							{
								switch(cpuMoveData.get(0))
								{
									case 1: //Se d�placer
									{
										System.out.println("Le joueur" + nextPlayer.symbol + " se d�place.");
										this.gameBoard.movePlayer(nextPlayer, cpuMoveData.get(1));
										break;
									}
									case 2: //D�poser bombe
									{
										System.out.println("Le joueur" + nextPlayer.symbol + " pose une bombe.");
										this.gameBoard.addBombToBoard(nextPlayer, cpuMoveData.get(1), cpuMoveData.get(2));
										break;
									}
									case 3: //D�poser mine
									{
										System.out.println("Le joueur" + nextPlayer.symbol + " pose une mine.");
										this.gameBoard.addMineToBoard(nextPlayer, cpuMoveData.get(1), cpuMoveData.get(2));
										break;
									}
									case 4: //Tirer
									{
										System.out.println("Le joueur" + nextPlayer.symbol + " tire!");
										Shot shot = new Shot(5,GameSettings.boardSize,this.getFireDirection(cpuMoveData.get(1)),new Coords(nextPlayer.coordinates.x,nextPlayer.coordinates.y),this.gameBoard);
										break;
									}
									case 5: //Activer bouclier
									{
										System.out.println("Le joueur" + nextPlayer.symbol + " l�ve son bouclier.");
										nextPlayer.shield = true;
										break;
									}
									case 6: //Ne rien faire
									{
										break;
									}
								}
							
							}
						}
						proxBoard.currentBoard = this.gameBoard;
						
						this.gameBoard = processEndOfTurn(this.gameBoard);
					}
				}
			}
		}
		System.out.println("Game over");
		
	}

}
