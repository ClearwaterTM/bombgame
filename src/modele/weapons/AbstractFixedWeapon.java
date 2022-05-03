package modele.weapons;

import modele.*;

/**
 * Cette classe est destiné à être herité par {@link Bomb} et {@link Mine}. Elle represente n'importe quelle arme fixe.
 */
public abstract class AbstractFixedWeapon extends AbstractWeapon {
    /**
     * Représente les coordonnées de la case sur laquelle est posée l'arme.
     */
    protected Coords tile;
    /**
     * Représente le joueur ayant posé la bombe.
     */
    protected AbstractPlayer owner;
    /**
     * Indique si une arme est invisible aux yeux des joueurs ou non.
     */
    protected boolean invisible;

    /**
     * Constructeur de la classe.
     * @param damage nombre de dommages qu'inflige l'arme.
     * @param tile les coordonnées de la case.
     * @param owner le joueur ayant posé l'arme.
     * @param invisible indique si l'arme est invisible ou non.
     */
    public AbstractFixedWeapon (int damage, Coords tile, AbstractPlayer owner, boolean invisible) {
        super(damage);
        this.tile = tile;
        this.owner = owner;
        this.invisible = invisible;
    }

    /**
     * Accesseur de Owner.
     * @return le joueur ayant posé l'arme.
     */
    public AbstractPlayer getOwner() {
        return this.owner;
    }
    
    /**
     * Accesseur de Player.
     * @return le joueur ayant posé l'arme.
     */
    public AbstractPlayer getPlayer()
    {
    	return this.owner;
    }

    /**
     * Accesseur du visibilité.
     * @return le joueur ayant posé l'arme.
     */
    public boolean getVisibility()
    {
    	return this.invisible;
    }
    /**
     * Vérifie s'il y a un joueur sur l'arme.
     * @param board la grille de jeu
     * @return <code>null</code> s'il n'y a pas de joueur, le joueur en question sinon.
     */
    public AbstractPlayer playerOnWeapon (Board board) {
        Case currentTile = board.gameBoard[this.tile.x][this.tile.y];
        Object[] objectsOnBomb = currentTile.objectList;
        for (int i = 0; i < objectsOnBomb.length; i++) {
            if (objectsOnBomb[i] instanceof AbstractPlayer) {
                return (AbstractPlayer) objectsOnBomb[i];
            }
        }
        return null;
    }

    /**
     * Indique si l'arme explose ou non. Doit être appelé à chaque tour.
     * @param board la grille de jeu.
     * @return <code>true</code> si l'arme explose, sinon <code>false</code>.
     */
    public abstract boolean isExploding(Board board);

    /**
     * GÃ¨re le comportement de l'arme (si elle explose ou non).
     * @param board la grille du jeu.
     */
    public abstract void explode(Board board);
}