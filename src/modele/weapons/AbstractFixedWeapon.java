package modele.weapons;

import modele.*;

/**
 * Cette classe est destin� � �tre herit� par {@link Bomb} et {@link Mine}. Elle represente n'importe quelle arme fixe.
 */
public abstract class AbstractFixedWeapon extends AbstractWeapon {
    /**
     * Repr�sente les coordonn�es de la case sur laquelle est pos�e l'arme.
     */
    protected Coords tile;
    /**
     * Repr�sente le joueur ayant pos� la bombe.
     */
    protected AbstractPlayer owner;
    /**
     * Indique si une arme est invisible aux yeux des joueurs ou non.
     */
    protected boolean invisible;

    /**
     * Constructeur de la classe.
     * @param damage nombre de dommages qu'inflige l'arme.
     * @param tile les coordonn�es de la case.
     * @param owner le joueur ayant pos� l'arme.
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
     * @return le joueur ayant pos� l'arme.
     */
    public AbstractPlayer getOwner() {
        return this.owner;
    }
    
    /**
     * Accesseur de Player.
     * @return le joueur ayant pos� l'arme.
     */
    public AbstractPlayer getPlayer()
    {
    	return this.owner;
    }

    /**
     * Accesseur du visibilit�.
     * @return le joueur ayant pos� l'arme.
     */
    public boolean getVisibility()
    {
    	return this.invisible;
    }
    /**
     * V�rifie s'il y a un joueur sur l'arme.
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
     * Indique si l'arme explose ou non. Doit �tre appel� � chaque tour.
     * @param board la grille de jeu.
     * @return <code>true</code> si l'arme explose, sinon <code>false</code>.
     */
    public abstract boolean isExploding(Board board);

    /**
     * Gère le comportement de l'arme (si elle explose ou non).
     * @param board la grille du jeu.
     */
    public abstract void explode(Board board);
}