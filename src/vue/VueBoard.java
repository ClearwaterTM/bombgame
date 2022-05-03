package vue;

import modele.*;

/**
 * <b>VueBoard est une classe qui s'attache à un objet de type Board pour être écouté.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public class VueBoard implements ModelListener
{
	/**
	 * <b>Le Board à s'attacher.</b>
	 */
	public Board board;
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise le Vue du Board.</p>
     *
     * @param board
     * 			Le Board à s'attacher.
     */
	public VueBoard(Board board)
	{
		this.board = board;
		this.board.addListener(this);
	}
	
	/**
	 * <b>Mettre le Board à jour.</b>
	 */
	public void updateModel(Object source)
	{
		this.board.showBoard();
	}
}
