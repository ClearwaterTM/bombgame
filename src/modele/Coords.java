package modele;

/**
 * <b>Coords est une classe qui represente les coordon�es des differents.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */

public class Coords
{
	/**
	 * <b>Coordon�e x.</b>
	 */
	public int x;
	/**
	 * <b>Coordon�e y.</b>
	 */
	public int y;
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise les coordonn�es.</p>
     * @param inputX
     * 		Le coordonn�e x.
     * @param inputY
     * 		Le coordonn�e y.
     */
	public Coords(int inputX, int inputY)
	{
		this.x = inputX;
		this.y = inputY;
	}
	
	@Override
	/**
	 * <b>Affichage des coordon�es.</b>
	 */
	public String toString()
	{
		return("(" + this.x + " - " + this.y + ")");
	}

}
