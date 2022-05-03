package modele;

/**
 * <b>Coords est une classe qui represente les coordonées des differents.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */

public class Coords
{
	/**
	 * <b>Coordonée x.</b>
	 */
	public int x;
	/**
	 * <b>Coordonée y.</b>
	 */
	public int y;
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise les coordonnées.</p>
     * @param inputX
     * 		Le coordonnée x.
     * @param inputY
     * 		Le coordonnée y.
     */
	public Coords(int inputX, int inputY)
	{
		this.x = inputX;
		this.y = inputY;
	}
	
	@Override
	/**
	 * <b>Affichage des coordonées.</b>
	 */
	public String toString()
	{
		return("(" + this.x + " - " + this.y + ")");
	}

}
