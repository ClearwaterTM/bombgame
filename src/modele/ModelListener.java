package modele;

/**
 * <b>ProxyBoard est une interface permettant une classe � �tre �cout�.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public interface ModelListener
{
	/**
	 * <b>Mettre l'objet � jour.</b>
	 * 
     * @param source
     * 		L'object � mettre � jour.
	 * 
	 */
	public void updateModel(Object source);
}
