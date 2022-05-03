package modele;

import java.util.*;

import modele.weapons.Mine;



/**
 * <b>AbstractPlayer est une classe abstract qui represente nos joueurs. Ces derniers utiliseront des classes qui héritent de cette classe.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public abstract class AbstractPlayer extends AbstractModelListener
{
	/**
     * 	<b>Les coordonnées du joueur.</b>
     */
	public Coords coordinates;
	
	/**
     * 	<b>La symbole du joueur, qui sera utilisé pour le répresenter sur le plateau du jeu.</b>
     */
	public char symbol;
	
	/**
     * 	<b>L'énergie du joueur.</b>
     */
	public int energy = GameSettings.playerStartingEnergy;
	/**
     * 	<b>Le bouclier du joueur. Son statut est representé par un boolean (True si activé, False sinon).</b>
     */
	public boolean shield;
	/**
     * 	<b>Boolean répresentant si le joueur est vivante (True) ou non (False).</b>
     */
	public boolean isAlive;
	
	/**
     * 	<b>Afficher les coordonées actuelles du joueur.</b>
     */
	public void showCoords()
	{
		System.out.println("(" + this.coordinates.x + " - " + this.coordinates.y + ")");
	}
}
