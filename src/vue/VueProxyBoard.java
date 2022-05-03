package vue;

import modele.*;

/**
 * <b>VueProxyBoard est une classe qui s'attache à un objet de type ProxyBoard pour être écouté.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public class VueProxyBoard implements ModelListener
{
	
	/**
	 * <b>Le ProxyBoard à s'attacher.</b>
	 */
	public ProxyBoard proxyBoard;
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise le Vue du ProxyBoard.</p>
     *
     * @param proxBoard
     * 			Le proxyBoard à s'attacher.
     */
	public VueProxyBoard(ProxyBoard proxBoard)
	{
		this.proxyBoard = proxBoard;
		this.proxyBoard.addListener(this);
	}
	
	/**
	 * <b>Mettre le ProxyBoard à jour.</b>
	 */
	public void updateModel(Object source)
	{
		this.proxyBoard.showProxyBoard();
	}

}
