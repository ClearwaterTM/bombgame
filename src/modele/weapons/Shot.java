package modele.weapons;

import modele.*;

/**
 * Cette classe représente un tir.
 */
public class Shot extends AbstractWeapon {
    /**
     * Représente la portée du tir.
     */
    private int range;
    /**
     * Représente le vecteur de direction.
     */
    private Coords directionVector;
    /**
     * Représente les coordonnées d'origine du tir.
     */
    private Coords origin;
    /**
     * Représente la grille du jeu.
     */
    private Board board;

    /**
     * Constructeur de la classe.
     * @param damage nombre de dommages qu'inflige l'arme.
     * @param range indique la portée du tir.
     * @param direction indique la direction du tir.
     * @param origin indique les coordonnées d'origine du tir.
     * @param board la grille de jeu.
     */
    public Shot(int damage, int range, DirectionEnum direction, Coords origin, Board board) {
        super(damage);
        this.range = range;
        this.origin = origin;
        this.board = board;

        if (direction == DirectionEnum.LEFT) {
            this.directionVector = new Coords(0,-1);
        } else if (direction == DirectionEnum.RIGHT) {
            this.directionVector = new Coords(0,1);
        } else if (direction == DirectionEnum.UP) {
            this.directionVector = new Coords(-1,0);
        }   else {
            this.directionVector = new Coords(1,0);
        }

        for (int i = 1; i <= range; i++) {
            Coords currentTileCoords = new Coords(origin.x + i*this.directionVector.x,
                                                  origin.y + i*this.directionVector.y);
            
          
            if(currentTileCoords.x == -1 || currentTileCoords.x == board.boardSize || currentTileCoords.y == -1 || currentTileCoords.y == board.boardSize)
            {
            	System.out.println("Le tir est hors de portée!");
            	break;
            }
           
            //Vérifie qu'on ne sort pas du plateau du jeu avec le tir.
            
            //UP OOB
            if(currentTileCoords.x <= 0 && direction == DirectionEnum.UP && !(this.board.gameBoard[currentTileCoords.x][currentTileCoords.y].caseState instanceof AbstractPlayer)) {break;}
            //DOWN OOB
            if(currentTileCoords.x >= board.boardSize-1 && direction == DirectionEnum.DOWN && !(this.board.gameBoard[currentTileCoords.x][currentTileCoords.y].caseState instanceof AbstractPlayer)) {break;}
            //LEFT OOB
            if(currentTileCoords.y <= 0 && direction == DirectionEnum.LEFT && !(this.board.gameBoard[currentTileCoords.x][currentTileCoords.y].caseState instanceof AbstractPlayer)) {break;}
            //RIGHT OOB
            if(currentTileCoords.x >= board.boardSize-1 && direction == DirectionEnum.RIGHT && !(this.board.gameBoard[currentTileCoords.x][currentTileCoords.y].caseState instanceof AbstractPlayer)) {break;}
            

            if (this.board.gameBoard[currentTileCoords.x][currentTileCoords.y].objectList.length == 0)
            {
                continue;
            }
            else
            {
                boolean doShootStop = false;
                
                ///Code de détection de joueur.
                Object currentCaseObject = this.board.gameBoard[currentTileCoords.x][currentTileCoords.y].caseState;
                if(currentCaseObject instanceof Obstacle)
                {
                	doShootStop = true;
                	break;
                }
                if(currentCaseObject instanceof Bomb)
                {
                	Bomb bmb = (Bomb)currentCaseObject;
                	bmb.explode(board);
                	doShootStop = true;
                	break;
                }
                if(currentCaseObject instanceof AbstractPlayer)
                {
                	System.out.println("Un joueur a été frappé!");

                	AbstractPlayer hitPlayer = (AbstractPlayer)currentCaseObject;
                	if(hitPlayer.shield)
                	{
                		//Si le bouclier est activé, on bloque le tir.
                		System.out.println("Le bouclier du joueur à bloqué le tir!");
                		hitPlayer.shield = false;
                		
                	}
                	else
                	{
                        hitPlayer.energy -= this.damage;
                        System.out.println("Le joueur " + hitPlayer.symbol + " à " + hitPlayer.energy + " d'énergie restante.");
                	}

                
                	doShootStop = true;
                	break;
                }
                if (doShootStop) {
                    break;
                }
            }
        }
    }
}