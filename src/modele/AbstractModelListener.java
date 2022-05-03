package modele;

import java.util.*;

/**
 * <b>AbstractModelListener est une classe abstraite qui permet des classes � �tre �cout�s pour �tre inform� de leurs changements.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public abstract class AbstractModelListener implements ListenableModel
{
	/**
	 * <b>Notre liste de objets portant des �couteurs.</b>
	 */
	public List<ModelListener> listeners = new ArrayList<ModelListener>();

	/**
	 * <b>Ajouter un objet � notre liste d'objets �couteurs.</b>
	 */
	public void addListener(ModelListener e)
	{
		this.listeners.add(e);
	}
	
	/**
	 * <b>Supprimer un objet de notre liste d'objets �couteurs.</b>
	 */
	public void removeListener(ModelListener e)
	{
		this.listeners.remove(e);
	}
	
	/**
	 * <b>Notre liste de objets portant des �couteurs.</b>
	 */
	protected void fireChange()
	{
		for(int a=0; a < this.listeners.size(); a++)
		{
			this.listeners.get(a).updateModel(this);
		}
	}
}
