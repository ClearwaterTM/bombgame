 package vue;

import modele.*;

/**
 * <b>VuePlayer est une classe qui s'attache � un objet de type AbstractPlayer pour �tre �cout�.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public class VuePlayer implements ModelListener
{
	/**
	 * <b>Le AbstractPlayer � s'attacher.</b>
	 */
	public AbstractPlayer player;
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise le Vue du AbstractPlayer.</p>
     *
     * @param player
     * 			Le AbstractPlayer � s'attacher.
     */
	public VuePlayer(AbstractPlayer player)
	{
		this.player = player;
		this.player.addListener(this);
	}
	
	/**
	 * <b>Mettre le AbstractPlayer � jour.</b>
	 */
	public void updateModel(Object source)
	{
		this.player.showCoords();
	}
}
