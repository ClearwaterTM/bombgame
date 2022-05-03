package modele.weapons;

import modele.*;

/**
 * Cette classe représente une bombe.
 */
public class Bomb extends AbstractFixedWeapon {
    /**
     * Represente le nombre de tours avant que la bombe n'explose.
     */
    private int countdown;

    /**
     * Constructeur de la classe.
     * @param damage nombre de dommages qu'inflige la bombe.
     * @param tile les coordonnées de la bombe.
     * @param owner indique le joueur qui a posé l'arme.
     * @param invisible indique si l'arme est visible ou non.
     * @param countdown indique le nombre de tours avant que la bombe n'explose.
     */
    public Bomb(int damage, Coords tile, AbstractPlayer owner, boolean invisible, int countdown){
        super(damage, tile, owner, invisible);
        this.countdown = countdown;
        
    }

    @Override
    /**
     * V�rifier si un joueur se trouve dans la port�e d'une explosion.
     * @param board
     * 	Le plateau du jeu � verifier.
     * @return True si un joueur se trouve dans la port�e de l'explosion, False sinon.
     */
	public boolean isExploding(Board gameBoard)
    {
        this.countdown -= 1;
        if (this.playerOnWeapon(gameBoard) != null) {
            return true;
        }

        return (this.countdown <= 0);

	}
    

    /**
     * Obtenir la dur�e de vie du bombe.
     * 
     * @return La dur�e du vie du bombe.
     */
    public int getCountdown()
    {
    	return this.countdown;
    }
    
    
    @Override
    /**
     * Exploser une bombe.
     * @param board
     * 	Le plateau du jeu � verifier.
     */
    public void explode(Board board) {
    	System.out.println("BOOM");
    	Coords currentBombTile = this.tile;
    	
    	for(int a = currentBombTile.x-1; a <= currentBombTile.x+1; a++)
    	{
    		for(int b = currentBombTile.y-1; b <= currentBombTile.y+1; b++)
    		{
    			
    			//V�rifier qu'on n'est pas hors de port�e
    			if(a >= 0 && a <= board.boardSize-1 && b >= 0 && b <= board.boardSize-1)
    			{
                    if (board.gameBoard[a][b].caseState instanceof AbstractPlayer)
                    {
                
                    	AbstractPlayer hitPlayer = (AbstractPlayer) board.gameBoard[a][b].caseState;
                        System.out.println("Le joueur " + hitPlayer.symbol + " se trouve dans l'explosion!");
                        if(hitPlayer.shield)
                        {
                        	System.out.println("Le bouclier a bloqu� l'explosion!");
                        	hitPlayer.shield = false;
                        }
                        else
                        {
                        	hitPlayer.energy -= this.damage;
                        	System.out.println("Le joueur a pris " + this.damage + " points de d�g�ts! Energie restant: " + hitPlayer.energy);
                        }

                    }
    			}
    		}
    	}
    	
    }


}