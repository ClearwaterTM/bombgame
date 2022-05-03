package modele;

import java.util.*;

import modele.weapons.Mine;



/**
 * <b>AbstractPlayer est une classe abstract qui represente nos joueurs. Ces derniers utiliseront des classes qui h�ritent de cette classe.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public abstract class AbstractPlayer extends AbstractModelListener
{
	/**
     * 	<b>Les coordonn�es du joueur.</b>
     */
	public Coords coordinates;
	
	/**
     * 	<b>La symbole du joueur, qui sera utilis� pour le r�presenter sur le plateau du jeu.</b>
     */
	public char symbol;
	
	/**
     * 	<b>L'�nergie du joueur.</b>
     */
	public int energy = GameSettings.playerStartingEnergy;
	/**
     * 	<b>Le bouclier du joueur. Son statut est represent� par un boolean (True si activ�, False sinon).</b>
     */
	public boolean shield;
	/**
     * 	<b>Boolean r�presentant si le joueur est vivante (True) ou non (False).</b>
     */
	public boolean isAlive;
	
	/**
     * 	<b>Afficher les coordon�es actuelles du joueur.</b>
     */
	public void showCoords()
	{
		System.out.println("(" + this.coordinates.x + " - " + this.coordinates.y + ")");
	}
}
