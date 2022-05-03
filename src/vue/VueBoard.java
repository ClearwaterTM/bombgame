package vue;

import modele.*;

/**
 * <b>VueBoard est une classe qui s'attache � un objet de type Board pour �tre �cout�.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public class VueBoard implements ModelListener
{
	/**
	 * <b>Le Board � s'attacher.</b>
	 */
	public Board board;
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise le Vue du Board.</p>
     *
     * @param board
     * 			Le Board � s'attacher.
     */
	public VueBoard(Board board)
	{
		this.board = board;
		this.board.addListener(this);
	}
	
	/**
	 * <b>Mettre le Board � jour.</b>
	 */
	public void updateModel(Object source)
	{
		this.board.showBoard();
	}
}
