package Application;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Scanner;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import InterfaceGraphique.InterfaceTraining;
import InterfaceGraphique.InterfaceSession;

/**
 * Methode d'affichage en fonction de l'entrainement selectionné
 * 
 */
public class Session {

	private BaseDeDonnées.TrainingBdd Training = new BaseDeDonnées.TrainingBdd();
	private InterfaceTraining IntTraining = new InterfaceTraining();
	private InterfaceSession Intsession = new InterfaceSession();
	
	private int duree_corde=IntTraining.getDureeCorde();
	private int duree_pause=IntTraining.getDureePause();
	private int nbre_serie=IntTraining.getNbreSerie();
	private File bip = new File("resources/buzzer1.wav");
	private Timestamp timestamp_1 = new Timestamp(System.currentTimeMillis());

	public Session(Map<String, Integer> List, String type, String name, Date date, String level)
			throws InterruptedException {
		
		// Switch selon le choix entre renforcement et gainage
		switch (type) {
		case "Renforcement":
			for (int i = 1; i <= nbre_serie; i++) {
				Intsession.getSerie().setText("n° "+i);
				Intsession.getPanelPrincipal().repaint();
				
				
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
			Intsession.setTypeTraining("Musculation");
			Intsession.getPanelPrincipal().repaint();
			for (int i = 1; i <= nbre_serie; i++) {
			Intsession.getSerie().setText("n° "+i);
			}
			break;
		case "Gainage":
			Intsession.setTypeTraining("Gainage");
			Intsession.getPanelPrincipal().repaint();
			for (int i = 1; i <= nbre_serie; i++) {
			Intsession.getSerie().setText("n° "+i);
			}
			
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
	 * @throws InterruptedException
	 */
	public void corde_a_sauter() throws InterruptedException {
		Intsession.getExercice().setText("Corde à sauter");
		Intsession.getPanelPrincipal().repaint();
		for (int i = duree_corde; i > 0; i--) {
			Intsession.getTimeCurrent().setText(""+i);
			Intsession.getPanelPrincipal().repaint();
			Thread.sleep(1000);
			if (i == 5) {
				this.bip();
			}
		}
	}

	/**
	 * Methode pour creer une pause
	 * 
	 * @param time Temps en secondes.
	 * 
	 * @throws InterruptedException
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