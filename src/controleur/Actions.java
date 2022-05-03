package controleur;

import modele.*;
import vue.*;

/**
 * <b>Actions est une classe qui sert comme controleur du jeu. Elle envoie et met à jour les données du jeu et interface graphique.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */

public class Actions
{
	/**
     * 	<b>Le noyeau du jeu.</b>
     */
	public Game gameContainer;
	/**
     * 	<b>L'interface graphique du jeu.</b>
     */
	public VueGUI gameWindow;
	
    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise le controleur, le noyau du jeu et l'interface graphique.</p>
     */
	public Actions()
	{
		this.gameContainer = new Game(GameSettings.humanPlayers,GameSettings.cpuPlayers);
		this.gameWindow = new VueGUI(this.gameContainer,this);
	}
	
	/**
     * 	<b>Démarrer le boucle du jeu.</b>
     */
	public void start()
	{
		this.gameContainer.startGameLoop();
		while(!(this.gameContainer.isOver()))
		{
			;
		}
		System.out.println("Le jeu a terminé!");
		this.gameWindow.endGamePopUp();
	}
	
    /**
     * 	<b>Tirer dans une direction avec le joueur actuelle.</b>
     * @param direction
     * 		Le direction du tir.
     */
	public void shoot(int direction)
	{
		this.gameContainer.promptFireAction(this.gameContainer.getCurrentPlayer(), direction);
	}
	
    /**
     * 	<b>Déplacer le joueur actuelle dans le sens de l'argument.</b>
     * @param moveNum
     * 		Le direction du mouvement.
     */
	public void movePlayer(int moveNum)
	{
		this.gameContainer.gameBoard.movePlayer(this.gameContainer.getCurrentPlayer(), moveNum);
	}
	
    /**
     * 	<b>Déposer une bombe dans la direction de l'argument</b>
     * @param moveNum
     * 		La direction du dépôt du bombe.
     * @param visibility
     * 		Le visibilité du bombe.
     */
	public void placeBomb(int moveNum, int visibility)
	{
		this.gameContainer.gameBoard.addBombToBoard(this.gameContainer.getCurrentPlayer(), moveNum, visibility);
	}
	
    /**
     * 	<b>Tirer dans une direction avec le joueur actuelle.</b>
     * @param moveNum
     * 		La direction du dépôt du mine.
     * @param visibility
     * 		Le visibilité du mine.
     */
	public void placeMine(int moveNum, int visibility)
	{
		this.gameContainer.gameBoard.addMineToBoard(this.gameContainer.getCurrentPlayer(), moveNum, visibility);
	}
	
    /**
     * 	<b>Vérifier si une case dans la direction recherché est libre.</b>
     * @param moveNum
     * 		Le direction du case cherché.
     * @return <code>true</code> si le case est vide, sinon <code>false</code>.
     */
	public boolean checkIfWeaponSpaceFree(int moveNum)
	{
		return(this.gameContainer.isBombCaseFree(this.gameContainer.getCurrentPlayer(), moveNum));
	}
	
    /**
     * 	<b>Activer le bouclier du joueur actuelle.</b>
     */
	public void raiseShield()
	{
		this.gameContainer.getCurrentPlayer().shield = true;
	}
}
