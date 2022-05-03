package vue;

import modele.*;

/**
 * <b>VueProxyBoard est une classe qui s'attache � un objet de type ProxyBoard pour �tre �cout�.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public class VueProxyBoard implements ModelListener
{
	
	/**
	 * <b>Le ProxyBoard � s'attacher.</b>
	 */
	public ProxyBoard proxyBoard;
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise le Vue du ProxyBoard.</p>
     *
     * @param proxBoard
     * 			Le proxyBoard � s'attacher.
     */
	public VueProxyBoard(ProxyBoard proxBoard)
	{
		this.proxyBoard = proxBoard;
		this.proxyBoard.addListener(this);
	}
	
	/**
	 * <b>Mettre le ProxyBoard � jour.</b>
	 */
	public void updateModel(Object source)
	{
		this.proxyBoard.showProxyBoard();
	}

}
