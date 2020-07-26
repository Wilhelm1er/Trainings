package Application;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Scanner;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Swing.Principal;

/**
 * Methode d'affichage en fonction de l'entrainement selectionné
 * 
 */
public class Timer {

	private bdd.TrainingBdd Training = new bdd.TrainingBdd();
	private Principal principal=new Principal();

	private int duree_corde=principal.getDureeCorde();
	private int duree_pause=principal.getDureePause();
	private int nbre_serie=principal.getNbreSerie();
	private File bip = new File("resources/buzzer1.wav");
	private Timestamp timestamp_1 = new Timestamp(System.currentTimeMillis());

	public Timer(Map<String, Integer> List, String type, String name, Date date, String level)
			throws InterruptedException {
		// Session de base
		
		// Switch selon le choix entre renforcement et gainage
		switch (type) {
		case "Renforcement":
			for (int i = 1; i <= nbre_serie; i++) {
				
				System.out.println(" ");
				System.out.println("#####################################");
				System.out.println("          SERIE NUMERO: " + i);
				System.out.println("#####################################");
				System.out.println(" ");
				for (String mapentry : List.keySet()) {
					this.corde_a_sauter();
					System.out.println("Exercice: " + mapentry + " - " + List.get(mapentry) + " Répétitions.");
					this.promptEnterKey();
				}
				if (i < nbre_serie) {
					this.corde_a_sauter();
					System.out.println("PAUSE de 3min");
					this.pause(180);
				}
			}
			this.corde_a_sauter();
			break;
		case "Musculation":
			for (int i = 1; i <= nbre_serie; i++) {
				
				System.out.println(" ");
				System.out.println("#####################################");
				System.out.println("          SERIE NUMERO: " + i);
				System.out.println("#####################################");
				System.out.println(" ");
				for (String mapentry : List.keySet()) {
					System.out.println("Exercice: " + mapentry + " - " + List.get(mapentry) + " Répétitions.");
					this.promptEnterKey();
					this.pause(duree_pause);
				}
				if (i < nbre_serie) {
					System.out.println("PAUSE de 3min");
					this.pause(180);
				}
			}
			break;
		case "Gainage":
			for (int i = 1; i <= nbre_serie; i++) {
				
				System.out.println(" ");
				System.out.println("#####################################");
				System.out.println("          SERIE NUMERO: " + i);
				System.out.println("#####################################");
				System.out.println(" ");
				for (String mapentry : List.keySet()) {
					this.corde_a_sauter();
					System.out.println("Exercice: " + mapentry + " - " + List.get(mapentry) + " Secondes.");
					this.pause(List.get(mapentry));
					this.promptEnterKey();
				}
				if (i < nbre_serie) {
					this.corde_a_sauter();
					System.out.println("PAUSE de 3min");
					this.pause(180);
				}
			}
			this.corde_a_sauter();
			break;
		}
		System.out.println("Session TERMINEE");
		System.out.println(" ");
		// Prise de l'instant de fin de l'entrainement
		Timestamp timestamp_2 = new Timestamp(System.currentTimeMillis());
		// Conversion de la durée de l'entrainement.
		long diff = timestamp_2.getTime() - timestamp_1.getTime();
		int seconds = (int) (diff / 1000) % 60;
		int minutes = (int) ((diff / (1000 * 60)) % 60);
		int hours = (int) ((diff / (1000 * 60 * 60)) % 24);
		// Affichage de la durée de l'entrainement
		System.out.println("Durée de l'entrainement: " + minutes + " minutes " + seconds + " secondes.");
		System.out.println(" ");
		System.out.println("#####################################");
		System.out.println(" ");
		// Switch selon le choix entre renforcement et gainage
		switch (type) {
		case "Renforcement":
			// Ajout training dans la BDD
			Training.ajout_training(name, date, type, nbre_serie, duree_corde, level, diff);
			break;
		case "Musculation":
			// Ajout training dans la BDD
			Training.ajout_autre(name, date, type, nbre_serie, duree_pause, level, diff);
			break;
		case "Gainage":
			// Ajout training dans la BDD
			Training.ajout_training(name, date, type, nbre_serie, duree_corde, level, diff);
			break;
		}

	}

	/**
	 * Methode d'attente d'appui sur la touche ENTRER
	 * 
	 */
	public void promptEnterKey() {
		System.out.println(" ");
		System.out.println(" Appuyer sur \"ENTRER\" pour continuer");
		System.out.println("#####################################");
		System.out.println(" ");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode d'affichage de l'exercice corde à sauter
	 * 
	 */
	public void corde_a_sauter() throws InterruptedException {
		System.out.println("CORDE A SAUTER");
		for (int i = duree_corde; i > 0; i--) {
			System.out.println(i);
			Thread.sleep(1000);
			if (i == 5) {
				this.bip();
			}
		}
	}

	/**
	 * Methode pour creer une pause
	 * 
	 * @param int en secondes.
	 */
	public void pause(int time) throws InterruptedException {
		for (int i = time; i > 0; i--) {
			System.out.println(i);
			Thread.sleep(1000);
			if (i == 5) {
				this.bip();
			}
		}
	}

	/**
	 * Methode pour jouer le bip sonore
	 * 
	 */
	public void bip() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(bip));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * Methode pour lancer le demarrage
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void demarrage() throws InterruptedException {
		System.out.print("Démarrage dans 5 secondes: ");

		for (int i = 5; i > 0; i--) {
			if (i == 4) {
				this.bip();
			}
			System.out.print(i);
			Thread.sleep(1000);
		}
	}

	/**
	 * Methode pour lancer le processus avec un temps de corde a sauter
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void process_CordeASauter() throws InterruptedException {
		// Choix durée corde à sauter
		System.out.println("#####################################");
		System.out.println(" ");
		System.out.println("Choisissez votre temps de corde à sauter: (en secondes)");
		duree_corde = new Scanner(System.in).nextInt();
		System.out.println(" ");
		// Choix du nombre de série
		System.out.println("#####################################");
		System.out.println(" ");
		System.out.println("Nombre de série à effectuer: ");
		nbre_serie = new Scanner(System.in).nextInt();
		System.out.println("#####################################");
		System.out.println(" ");
		System.out.println("          Installez vous... ");
		System.out.println("                et          ");

		this.promptEnterKey();

		this.demarrage();

		// Prise de l'instant de demarrage de l'entrainement
		timestamp_1 = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Methode pour lancer le processus avec un temps de pause
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void process_AvecPause() throws InterruptedException {
		// Choix durée du temps de pause
		System.out.println("#####################################");
		System.out.println(" ");
		System.out.println("Choisissez votre temps de pause: (en secondes)");
		duree_pause = new Scanner(System.in).nextInt();
		System.out.println(" ");
		// Choix du nombre de série
		System.out.println("#####################################");
		System.out.println(" ");
		System.out.println("Nombre de série à effectuer: ");
		nbre_serie = new Scanner(System.in).nextInt();
		System.out.println("#####################################");
		System.out.println(" ");
		System.out.println("          Installez vous... ");
		System.out.println("                et          ");

		this.promptEnterKey();

		this.demarrage();

		// Prise de l'instant de demarrage de l'entrainement
		timestamp_1 = new Timestamp(System.currentTimeMillis());
	}
}