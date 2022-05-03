package modele.weapons;

import modele.*;

/**
 * Cette classe represente une mine.
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public class Mine extends AbstractFixedWeapon {


    /**
		<b>Le dur�e de vie du mine.</b>
     */
	public int lifetime;
	
    /**
     * Constructeur de la classe.
     * @param damage nombre de dommages qu'inflige l'arme.
     * @param tile les coordonnées de la mine.
     * @param owner indique le joueur qui a posé l'arme.
     * @param invisible indique si l'arme est visible ou non.
     */
    public Mine(int damage, Coords tile, AbstractPlayer owner, boolean invisible)
    {
        super(damage, tile, owner, invisible);
        this.lifetime = 15;
    }

    @Override
    /**
     * V�rifier si un joueur se trouve dans la port�e d'une explosion.
     * @param board
     * 	Le plateau du jeu � verifier.
     */
    public boolean isExploding(Board board) {
        return (this.playerOnWeapon(board) != null);
    }

    @Override
    /**
     * Exploser une mine.
     * @param board
     * 	Le plateau du jeu � verifier.
     */
    public void explode(Board board) {
    	AbstractPlayer playerOnMine = this.playerOnWeapon(board);
        playerOnMine.energy -= this.damage; // We know that `playerOnMine` can't be `null`
    	System.out.println("Le joueur a pris" + this.damage  + " points de d�g�ts! Energie restant: " + playerOnMine.energy);
    }
}