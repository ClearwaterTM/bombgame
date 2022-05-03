package modele;

/**
 * <b>EnergyZone est une classe qui represente une zone d'�nergie sur le plateau du jeu.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public class EnergyZone extends Case
{
	/**
	 * <b>La symbole r�presentant l'obstacle.</b>
	 */
	public Type symbol;
	/**
	 * <b>La quantit� d'�nergie offert par la zone.</b>
	 */
	public int energyPool = 10;
}
