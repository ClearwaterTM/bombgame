package vue;

import java.awt.*;

import javax.swing.*;
import controleur.Actions;
import modele.Game;

/**
 * <b>VueGUI est une classe qui represente l'interface graphique du jeu.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */
public class VueGUI {
	
	/**
	 * <b>Le contenu du jeu.</b>
	 */
	private Game gameContent;
	/**
	 * <b>Le controleur du programme pour récuperer tous les informations du jeu.</b>
	 */
	private Actions control;
	
	/**
	 * <b>L'action actuellement choisie dans l'interface graphique.</b>
	 */
	public int currentAction;
	/**
	 * <b>La visibilité de l'arme actuellement choisie dans l'interface graphique.</b>
	 */
	public boolean currentVisibility;
	
	/**
	 * <b>Le panneau de texte du action chosie.</b>
	 */
	public JLabel currentActionLabel;
	/**
	 * <b>Le panneau de texte du visibilité de l'arme chosie.</b>
	 */
	public JLabel currentWeaponVisibility;

    /**
     * 	<b>Constructeur de classe</b>
     *
     * <p>Initalise et affiche l'interface graphique.</p>
     *
     * @param gameContent
     * 		Le contenu du jeu.
     * @param controller
     * 		Le controleur du programme.
     */
	public VueGUI(Game gameContent, Actions controller)
	{
		this.gameContent = gameContent;
		this.control = controller;
		{
			  //Créer le Jframe
		      JFrame f = new JFrame("Jeu");  
		      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      
		      //Créer les JPanel
		      JPanel panel = new JPanel();
		      JPanel panel2 = new JPanel();
		      
		      //Spécifier la position et la taille des JPanel
		      panel.setBounds(0,500,500,150);
		      panel2.setBounds(0,0,500,500); 
		      
		      //Spécifier la couleur d'arrière-plan du JPanel
		      panel.setBackground(Color.lightGray);
		      panel2.setBackground(Color.black);
		      
		      //Créer les boutons
		      JButton btnup = new JButton("Haut"); 
		      JButton btnleft = new JButton("Gauche");
		      JButton btndown = new JButton("Bas");
		      JButton btnrght = new JButton("Droite");
		      JButton btn2 = new JButton("Attendre");
		      JButton btnChangeWeapon = new JButton("Changer d'action");
		      JButton btnChangeVisibility = new JButton("Changer de visibilité");
		      JButton btn6 = new JButton("Bouclier");
		      
		      //Création des boutons pour le depot des armes (mines+bombes)
		      JButton btnWeaponNorth = new JButton("N");
		      JButton btnWeaponNorthEast = new JButton("NE");
		      JButton btnWeaponEast = new JButton("E");
		      JButton btnWeaponSouthEast = new JButton("SE");
		      JButton btnWeaponSouth = new JButton("S");
		      JButton btnWeaponSouthWest = new JButton("SW");
		      JButton btnWeaponWest = new JButton("W");
		      JButton btnWeaponNorthWest = new JButton("NW");
		      
		      
		      this.currentActionLabel = new JLabel("Action actuel:" + this.getCurrentAction());
		      this.currentWeaponVisibility = new JLabel("Visibilité: " + (this.currentVisibility ? "A tous" : "Seul au joueur"));
		      
		      currentActionLabel.setBounds(195,560,150,25);
		      f.add(currentActionLabel);
		      
		      currentWeaponVisibility.setBounds(195,580,150,25);
		      f.add(currentWeaponVisibility);
		      
		      //Spécifier la position et la taille du bouton 
		      btnup.setBounds(30,525,25,25);
		      btnleft.setBounds(5,550,25,25);
		      btndown.setBounds(30,575,25,25);
		      btnrght.setBounds(55,550,25,25);
		      
		      
		      //btnsett.setBounds(350,575,100,25);
		      
		      //Spécifier la position et tailles des boutons des depots d'armes
		      btnWeaponNorth.setBounds(415,535,20,20);
		      btnWeaponNorthEast.setBounds(440,535,20,20);
		      btnWeaponEast.setBounds(440,560,20,20);
		      btnWeaponSouthEast.setBounds(440,585,20,20);
		      btnWeaponSouth.setBounds(415,585,20,20);
		      btnWeaponSouthWest.setBounds(390,585,20,20);
		      btnWeaponWest.setBounds(390,560,20,20);
		      btnWeaponNorthWest.setBounds(390,535,20,20);
		      
		      //Spécifier la couleur d'arrière-plan du bouton
		      btnup.setBackground(Color.WHITE); 
		      btnleft.setBackground(Color.WHITE); 
		      btndown.setBackground(Color.WHITE); 
		      btnrght.setBackground(Color.WHITE); 
		      btn2.setBackground(Color.WHITE);
		      btnChangeWeapon.setBackground(Color.WHITE); 
		      btnChangeVisibility.setBackground(Color.WHITE);   
		      btn6.setBackground(Color.WHITE); 
		      
		      //Ajouter les boutons au JPanel
		      f.add(btnup);
		      f.add(btnleft); 
		      f.add(btndown); 
		      f.add(btnrght); 
		      panel.add(btn2);
		      panel.add(btnChangeWeapon);
		      panel.add(btnChangeVisibility);
		      panel.add(btn6);
		      
		      //Ajouter les directions des depots d'armes au JPanel.
		      f.add(btnWeaponNorth);
		      f.add(btnWeaponNorthEast);
		      f.add(btnWeaponEast);
		      f.add(btnWeaponSouthEast);
		      f.add(btnWeaponSouth);
		      f.add(btnWeaponSouthWest);
		      f.add(btnWeaponWest);
		      f.add(btnWeaponNorthWest);
		      
		      
		      //Ajouter les JPanel au JFrame
		      f.add(panel);
		      f.add(panel2);
		      
		      //Configuration du JFrame
		      f.setSize(500,650);  
		      f.setLayout(null); 
		      f.setResizable(false);
		      f.setVisible(true);  
		      
		      //Ajouter les listeners pour chaque bouton
		      btnup.addActionListener(new MoveListener(this.gameContent,this.control));
		      btnleft.addActionListener(new MoveListener(this.gameContent,this.control));
		      btndown.addActionListener(new MoveListener(this.gameContent,this.control));
		      btnrght.addActionListener(new MoveListener(this.gameContent,this.control));
		      btn2.addActionListener(new MoveListener(this.gameContent,this.control));
		      btnChangeWeapon.addActionListener(new MoveListener(this.gameContent,this.control));
		      btnChangeVisibility.addActionListener(new MoveListener(this.gameContent,this.control));
		      btn6.addActionListener(new MoveListener(this.gameContent,this.control));
		      
		      //Ajout des listeners pour les boutons de depot d'arme
		      btnWeaponNorth.addActionListener(new MoveListener(this.gameContent,this.control));
		      btnWeaponNorthEast.addActionListener(new MoveListener(this.gameContent,this.control));
		      btnWeaponEast.addActionListener(new MoveListener(this.gameContent,this.control));
		      btnWeaponSouthEast.addActionListener(new MoveListener(this.gameContent,this.control));
		      btnWeaponSouth.addActionListener(new MoveListener(this.gameContent,this.control));
		      btnWeaponSouthWest.addActionListener(new MoveListener(this.gameContent,this.control));
		      btnWeaponWest.addActionListener(new MoveListener(this.gameContent,this.control));
		      btnWeaponNorthWest.addActionListener(new MoveListener(this.gameContent,this.control));
		      
		   }
	}
	
	/**
	 * <b>Obtenir l'action choisie dans l'interface graphique.</b>
	 * 
     * @return Le String du action active.
	 */
	 public String getCurrentAction()
	 {
		 switch(this.currentAction)
		 {
		 		case 0:{return "Tirer";}
		 		case 1:{return "Bombe";}
		 		case 2:{return "Mine";}
		 		default:{return "";}
		 }
	 }
	 
	/**
	 * <b>Afficher une fenêtre à la fin du jeu avec le vainqueur.</b>
	 */
	 public void endGamePopUp()
	    {
		 	String message = "Le joueur " + this.control.gameContainer.getWinner().symbol + " a gagne la partie";
	        JOptionPane.showMessageDialog(null, message,"FELICITATIONS",JOptionPane.INFORMATION_MESSAGE);
	        System.exit(0);
	    }
	
	/**
	* <b>Mettre le VueGUI à jour.</b>
	 * @param source
     * 	Le VueGUI à mettre à jour.
	*/
	public void modelUpdated(Object source)
	{
		;
	}
}