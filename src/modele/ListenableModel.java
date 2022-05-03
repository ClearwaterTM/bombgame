package modele;

/**
 * <b>ListenableModel est une interface qui sert pour rendre écoutable les objets.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */

public interface ListenableModel
{
	/**
	 * <b>Ajouter un objet à notre liste d'objets écouteurs.</b>
     * @param e
     * 	L'objet à rajouter au liste.
     */
	public void addListener(ModelListener e);
	/**
	 * <b>Supprimer un objet de notre liste d'objets écouteurs.</b>
     * @param e
     * 	L'objet à supprimer du liste.
     */
	public void removeListener(ModelListener e);
	
}
