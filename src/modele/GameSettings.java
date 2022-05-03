package modele;

import java.io.File;
import java.util.Scanner;

/**
 * <b>AbstractPlayer est une classe qui sert comme gestionnaire des param�tres du jeu.</b>
 * 
 * @author Daniel MURRAY, Oc�ane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */


public class GameSettings
{
	
	/**
     * 	<b>Les param�tres du jeu. Celles-ci seront d�finis dans la fonction setGameVariables dans la m�me classe.</b>
     */
	public static int humanPlayers;
	public static int cpuPlayers;
	public static int boardSize;
	public static int bombLifetime;
	public static int playerStartingEnergy;
	public static int obstaclesToPlace;
	public static int energyZonesToPlace;
	
	
	/**
     * 	<b>Charger les variables du param�tre du jeu � partir du fichier settings.txt.</b>
     */
	public static void setGameVariables()
	{
		try
		{
			File gameSettingsFile = new File("settings.txt");
			Scanner fileReader = new Scanner(gameSettingsFile);
			String bin;
			
			//Ignorer chaque line entre chaque variable. Ceci permet les commentaires dans le fichier.
			
			bin = fileReader.nextLine();
			humanPlayers = Integer.parseInt(fileReader.nextLine());
			bin = fileReader.nextLine();
			cpuPlayers = Integer.parseInt(fileReader.nextLine());
			bin = fileReader.nextLine();
			boardSize = Integer.parseInt(fileReader.nextLine());
			bin = fileReader.nextLine();
			bombLifetime = Integer.parseInt(fileReader.nextLine());
			bin = fileReader.nextLine();
			playerStartingEnergy = Integer.parseInt(fileReader.nextLine());
			bin = fileReader.nextLine();
			obstaclesToPlace = Integer.parseInt(fileReader.nextLine());
			bin = fileReader.nextLine();
			energyZonesToPlace = Integer.parseInt(fileReader.nextLine());
			
			fileReader.close();
			
			//V�rifier que tous les param�tres sont dans une port�e d�fini.
			
			//Il doit y a voir au moins 2 joueurs sur le plateau - Soit 2 joueurs, soit 1 humain et un ordinateur, ou 2 ordinateurs.
			if((humanPlayers < 1) || (humanPlayers == 0 && cpuPlayers < 2))
			{
				throw new Exception();
			}
			
			//La taille du plateau du jeu doit etre entre 3 et 20 cases.
			if(boardSize < 3 || boardSize > 20)
			{
				throw new Exception();
			}
			
			//La dur�e de vie d'une bombe doit etre entre 5 et 15 tours.
			if(bombLifetime < 5 || bombLifetime > 15)
			{
				throw new Exception();
			}
			
			//L'�nergie de d�part d'un joueur doit etre entre 25 et 100.
			if(playerStartingEnergy < 25 || playerStartingEnergy > 100)
			{
				throw new Exception();
			}
			
			
		}
		//S'il n'est pas possible de traiter les param�tres du jeu (fichier manquant, variables mals formul�s, etc), le jeu va
		//alors utiliser des valeurs par d�faut.
		catch (Exception e)
		{
			System.out.println("Un erreur est survenu.");
		    System.out.println("Le fichier settings.txt n'a pas pu etre trouv�, ou contient des parametres incorrectes.");
		    System.out.println("Utilisation des valeurs de jeu par d�faut.");
		    
		    humanPlayers = 2;
		    cpuPlayers = 0;
		    boardSize = 7;
			bombLifetime = 10;
			playerStartingEnergy = 30;
			obstaclesToPlace = 5;
			energyZonesToPlace = 5;
		      
		}
	
	}
}
