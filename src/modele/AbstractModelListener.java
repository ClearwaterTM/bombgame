package modele;

import java.util.*;

/**
 * <b>AbstractModelListener est une classe abstraite qui permet des classes à être écoutés pour être informé de leurs changements.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public abstract class AbstractModelListener implements ListenableModel
{
	/**
	 * <b>Notre liste de objets portant des écouteurs.</b>
	 */
	public List<ModelListener> listeners = new ArrayList<ModelListener>();

	/**
	 * <b>Ajouter un objet à notre liste d'objets écouteurs.</b>
	 */
	public void addListener(ModelListener e)
	{
		this.listeners.add(e);
	}
	
	/**
	 * <b>Supprimer un objet de notre liste d'objets écouteurs.</b>
	 */
	public void removeListener(ModelListener e)
	{
		this.listeners.remove(e);
	}
	
	/**
	 * <b>Notre liste de objets portant des écouteurs.</b>
	 */
	protected void fireChange()
	{
		for(int a=0; a < this.listeners.size(); a++)
		{
			this.listeners.get(a).updateModel(this);
		}
	}
}
