package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;
import controleur.Actions;
import modele.Game;

/**
 * <b>MoveListener est une classe qui sert comme listener pour tout bouton appuyé dans l'interface graphique. Elle implément l'interface ActionListener.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */

public class MoveListener implements ActionListener
{

	/**
	 * <b>Le contenu du jeu.</b>
	 */
	public Game game;
	/**
	 * <b>Le controleur du jeu.</b>
	 */
	public Actions control;
	
	/**
	 * <b>Boolean pour empêcher la fin d'un tour si une action qui ne termines pas encore le tour a été prise.</b>
	 */
	public boolean dontEndTurn;
	
	
    /**
     * <b>Constructeur de classe</b>
     *
     * <p>Initalise le listener.</p>
     *
     * @param game
     * 		Le contenu du jeu.
     * @param controller
     * 		Le controleur du programme.
     */
	public MoveListener(Game game, Actions controller)
	{
		this.game = game;
		this.control = controller;
	}
	
	
	
	/**
	 * <b>Traiter une action qui a été choisie par le joueur par l'interface graphique.</b>
	 * 
	 * @param currentAction
     * 	L'action a prendre.
	 * @param visibility
     * 	La visibilité du mine/bombe.
	 * @param actionDirection
     * 	La direction de l'action.
	 */
	public void processAction(int currentAction, boolean visibility, int actionDirection)
	{
		//0 - tirer, 1 - bombe, 2 - mine
		switch(currentAction)
		{
			case 0:
			{
				if(actionDirection % 2 != 0)
				{
					switch(actionDirection)
					{
						case 1: {actionDirection = 3; break;}
						case 3: {actionDirection = 2; break;}
						case 5: {actionDirection = 4; break;}
						case 7: {actionDirection = 1; break;}
						default: {break;}
					}
					this.control.shoot(actionDirection);
				}
				else
				{
					System.out.println("Tir invalide.");
					System.out.println(actionDirection % 2);
				}
				break;
			}
			case 1:{this.control.placeBomb(actionDirection, (visibility ? 1 : 0));break;}
			case 2:{this.control.placeMine(actionDirection, (visibility ? 1 : 0));break;}
			default:{break;}
		}
	}
	
	/**
	 * <b>Quand un bouton a été appuyé, analyser et traiter les information reçues pour réaliser le coup demandé du joueur.</b>
	 * @param event
     * 	L'évenèment à traiter.
	 */
	public void actionPerformed(ActionEvent event)
	{
		JButton pressedButton = (JButton)event.getSource();
		String label = pressedButton.getLabel();
		
		switch(label)
		{
			//Depot des armes
			case "N": {this.processAction(this.control.gameWindow.currentAction,this.control.gameWindow.currentVisibility,1);break;}
			case "NE": {this.processAction(this.control.gameWindow.currentAction,this.control.gameWindow.currentVisibility,2);break;}
			case "E": {this.processAction(this.control.gameWindow.currentAction,this.control.gameWindow.currentVisibility,3);break;}
			case "SE": {this.processAction(this.control.gameWindow.currentAction,this.control.gameWindow.currentVisibility,4);break;}
			case "S": {this.processAction(this.control.gameWindow.currentAction,this.control.gameWindow.currentVisibility,5);break;}
			case "SW": {this.processAction(this.control.gameWindow.currentAction,this.control.gameWindow.currentVisibility,6);break;}
			case "W": {this.processAction(this.control.gameWindow.currentAction,this.control.gameWindow.currentVisibility,7);break;}
			case "NW": {this.processAction(this.control.gameWindow.currentAction,this.control.gameWindow.currentVisibility,8);break;}
		
			//Mouvement
			case "Gauche":
				{
					if(this.game.isCaseFree(this.game.getCurrentPlayer(), 1))
					{
						this.control.movePlayer(1);
						
					}
					break;
					
				}
			case "Droite": 
			{
				if(this.game.isCaseFree(this.game.getCurrentPlayer(), 2))
				{
					this.control.movePlayer(2);
				}
				break;
				
			}
			case "Haut":
			{
				if(this.game.isCaseFree(this.game.getCurrentPlayer(), 3))
				{
					this.control.movePlayer(3);	
				}
				break;
				
			}
			case "Bas":
			{
				if(this.game.isCaseFree(this.game.getCurrentPlayer(), 4))
				{
					this.control.movePlayer(4);
				}
				break;
			}
			
			//Autres actions
			case "Attendre":
				{
					break;
				}
			case "Changer d'action":
			{
				if(this.control.gameWindow.currentAction == 2)
				{
					this.control.gameWindow.currentAction = 0;
				}
				else
				{
					this.control.gameWindow.currentAction++;
				}
				
				this.control.gameWindow.currentActionLabel.setText("Action actuel:" + this.control.gameWindow.getCurrentAction());
				
				
				//Eviter la fin du tour lors du changement d'arme.
				this.dontEndTurn = true;
				break;
			}
			case "Changer de visibilité":
			{
				this.control.gameWindow.currentVisibility = !(this.control.gameWindow.currentVisibility);
				
				this.control.gameWindow.currentWeaponVisibility.setText("Visibilité: " + (this.control.gameWindow.currentVisibility ? "A tous" : "Au joueur actuel"));
				
				//Eviter la fin du tour lors du changement de visibilité.
				this.dontEndTurn = true;
				break;
			}
			case "Bouclier":
			{
				this.control.raiseShield();
				break;
			}
		}
		if(!(this.dontEndTurn))
		{
			System.out.println("Fin du tour");
			this.control.gameContainer.awaitingPlayerInput = false;
		}
		else
		{
			this.dontEndTurn = false;
		}
	}
}
