package modele;

/**
 * <b>EnergyZone est une classe qui represente une zone d'énergie sur le plateau du jeu.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public class EnergyZone extends Case
{
	/**
	 * <b>La symbole répresentant l'obstacle.</b>
	 */
	public Type symbol;
	/**
	 * <b>La quantité d'énergie offert par la zone.</b>
	 */
	public int energyPool = 10;
}
