package modele;

/**
 * <b>ListenableModel est une interface qui sert pour rendre �coutable les objets.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */

public interface ListenableModel
{
	/**
	 * <b>Ajouter un objet � notre liste d'objets �couteurs.</b>
     * @param e
     * 	L'objet � rajouter au liste.
     */
	public void addListener(ModelListener e);
	/**
	 * <b>Supprimer un objet de notre liste d'objets �couteurs.</b>
     * @param e
     * 	L'objet � supprimer du liste.
     */
	public void removeListener(ModelListener e);
	
}
