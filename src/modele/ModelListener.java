package modele;

/**
 * <b>ProxyBoard est une interface permettant une classe à être écouté.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public interface ModelListener
{
	/**
	 * <b>Mettre l'objet à jour.</b>
	 * 
     * @param source
     * 		L'object à mettre à jour.
	 * 
	 */
	public void updateModel(Object source);
}
