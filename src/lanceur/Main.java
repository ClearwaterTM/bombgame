package lanceur;

import controleur.*;
import modele.GameSettings;

/**
 * <b>Main est notre point d'entrée principale dans le programme.</b>
 * 
 * @author Daniel MURRAY, Océane PERROUAULT, Jules ROCHE, Joshua AUBRY
 */

public class Main
{
	/**
	 * <b>Notre point d'entrée dans le programme.</b>
	 */
	public static void main(String[] args)
	{
		GameSettings.setGameVariables();
		Actions gameContainer = new Actions();
		
		gameContainer.start();

	}

}

//Final checklist
//Code cleanup
//Finish the randomCpu class
//Remove any non-used classes - cleaing up
//Javadoc & report - working on Javadoc, others are doing report
//If we can get the grid to appear in the interface then brilliant