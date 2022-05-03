 package vue;

import modele.*;

/**
 * <b>VuePlayer est une classe qui s'attache à un objet de type AbstractPlayer pour être écouté.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public class VuePlayer implements ModelListener
{
	/**
	 * <b>Le AbstractPlayer à s'attacher.</b>
	 */
	public AbstractPlayer player;
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise le Vue du AbstractPlayer.</p>
     *
     * @param player
     * 			Le AbstractPlayer à s'attacher.
     */
	public VuePlayer(AbstractPlayer player)
	{
		this.player = player;
		this.player.addListener(this);
	}
	
	/**
	 * <b>Mettre le AbstractPlayer à jour.</b>
	 */
	public void updateModel(Object source)
	{
		this.player.showCoords();
	}
}
