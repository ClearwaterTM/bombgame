package modele;

import modele.Case.Type;
import modele.weapons.Bomb;
import modele.weapons.Mine;


/**
 * <b>ProxyBoard est une classe qui represente ce que chaque joueur voit sur son plateau du jeu (implementation du pattern Proxy).</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public class ProxyBoard extends AbstractModelListener
{
	/**
	 * <b>Le plateau global actuel</b>
	 */
	public Board currentBoard;
	/**
	 * <b>Le joueur actuel.</b>
	 */
	public AbstractPlayer currentPlayer;
	
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise le proxy du plateau.</p>
     *
     * @param inputBoard
     * 			Le plateau du jeu actuel.
     * @param inputPlayer
     * 			Le joueur actuel.
     */
	public ProxyBoard(Board inputBoard, AbstractPlayer inputPlayer)
	{
		this.currentBoard = inputBoard;
		this.currentPlayer = inputPlayer;
	}
	
	
	/**
	 * <b>Mettre le plateau du jeu proxy à jour.</b>
	 */
	public void update()
	{
		this.showProxyBoard();
	}
	
	/**
	 * <b>Afficher le plateau proxy.</b>
	 */
	public void showProxyBoard()
	{
		System.out.println("Current player: " + this.currentPlayer.symbol);
		
		for (int a = 0; a < this.currentBoard.boardSize; a++)
		{
			for (int b = 0; b < this.currentBoard.boardSize; b++)
			{
				
				//Vérifier s'il y a une bombe, et s'il a été deposé par le même joueur que le joueur actuel.
				//Si oui, afficher la bombe. Si non, afficher une case vide.
				if(this.currentBoard.gameBoard[a][b].caseType == Type.BOMB)
				{
					Bomb currentBomb = (Bomb)this.currentBoard.gameBoard[a][b].caseState;
					
					if(currentBomb.getOwner() == this.currentPlayer)
					{
						System.out.print(this.currentBoard.gameBoard[a][b] + " ");
					}
					else
					{
						if(currentBomb.getVisibility())
						{
							System.out.print(this.currentBoard.gameBoard[a][b] + " ");
						}
						else
						{
							System.out.print("-" + " ");
						}
					}
				}
				
				//De même, mais avec les mines.
				else if(this.currentBoard.gameBoard[a][b].caseType == Type.MINE)
				{
					Mine currentMine = (Mine)this.currentBoard.gameBoard[a][b].caseState;
					
					if(currentMine.getOwner() == this.currentPlayer)
					{
						System.out.print(this.currentBoard.gameBoard[a][b] + " ");
					}
					else
					{
						if(currentMine.getVisibility())
						{
							System.out.print(this.currentBoard.gameBoard[a][b] + " ");
						}
						else
						{
							System.out.print("-" + " ");
						}
					}
				}
				else
				{
					System.out.print(this.currentBoard.gameBoard[a][b] + " ");
				}

			}
			System.out.println("");
		}
		System.out.println("------");
	}
	
	
	
}
