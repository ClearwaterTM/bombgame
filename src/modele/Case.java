package modele;

/**
 * <b>Case est une classe abstract qui represente une case sur un plateau du jeu.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public class Case
{
	/**
     * 	<b>Une structure qui liste tous les �tats possibles qu'une case peut contenir.</b>
     */
	public enum Type
	{
		EMPTY,
		PLAYER,
		BOMB,
		MINE,
		OBSTACLE,
		ENERGY
	}
	
	/**
     * 	<b>Le liste des objets se trouvant sur ce case.</b>
     */
	public Object[] objectList;
	/**
     * 	<b>L'objet principale qui se trouve sur ce case.</b>
     */
	public Object caseState;
	
	/**
     * 	<b>Le type de case. Ce variable est d�fini avec l'aide du enum Type dans ce meme classe.</b>
     */
	public Type caseType;
	
    /**
     * <b>Constructeur de classe</b>
     *
     * <p>Initalise la case.</p>
     *
     * @param inputState
     * 			L'�tat initiale du case.
     */
	public Case(Object inputState) {
		this.caseType = Type.EMPTY;
		this.objectList = new Object[8];
	}
	
    /**
     * <b>Constructeur de classe</b>
     *
     * <p>Initalise la case.</p>
     *
     */
	public Case()
	{
		this(null);
	}
	
	/**
     * 	<b>Affichage visuelle du case. Cet fonction est utilis� pour ensuite afficher notre plateau du jeu dans la classe Board.</b>
     * @return Une caract�re qui represente la case.
     */
	@Override
	public String toString()
	{
		if(this.caseType == Type.EMPTY)
		{
			return("-");
		}
		if(this.caseType == Type.PLAYER)
		{
			AbstractPlayer plr = (AbstractPlayer)this.caseState;
			return("" + plr.symbol);
		}
		
		if(this.caseType == Type.ENERGY)
		{
			return("#");
		}
		if(this.caseType == Type.OBSTACLE)
		{
			return("O");
		}
		if(this.caseType == Type.BOMB)
		{
			return("*");
		}
		if(this.caseType == Type.MINE)
		{
			return("+");
		}
		return(" ");
	}
	
}
