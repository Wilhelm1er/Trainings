import java.util.HashMap;
import java.util.Map;

public class Training {
	
	private Map<String, Integer> List_1 = new HashMap<String, Integer>();
	private Map<String, Integer> List_2 = new HashMap<String, Integer>();
	
	public Map<String, Integer> Renforcement(int level) {
		switch (level) {
		case 1:
			List_1.put("Abdo rameur", 15);
			List_1.put("Bras tendu coude", 10);
			List_1.put("Tractions", 5);
			List_1.put("Mountain Climbers", 15);
			List_1.put("Pompes", 5);
			break;
		case 2:
			List_1.put("Abdo rameur", 20);
			List_1.put("Bras tendu coude", 12);
			List_1.put("Tractions", 7);
			List_1.put("Mountain Climbers", 18);
			List_1.put("Pompes", 7);
			break;
		case 3:
			List_1.put("Abdo rameur", 25);
			List_1.put("Bras tendu coude", 20);
			List_1.put("Tractions", 15);
			List_1.put("Mountain Climbers", 25);
			List_1.put("Pompes", 15);
			break;
		case 4:
			List_1.put("Abdo rameur", 30);
			List_1.put("Bras tendu coude", 25);
			List_1.put("Tractions", 20);
			List_1.put("Mountain Climbers", 30);
			List_1.put("Pompes", 20);
			break;
		}
		return List_1;
	}
	
	public Map<String, Integer> getList_1() {
		return List_1;
	}

	public Map<String, Integer> getList_2() {
		return List_2;
	}

	public  Map<String, Integer> Gainage(int level) {
		switch (level) {
		case 1:
			List_2.put("La Planche", 60);
			List_2.put("Superman", 60);
			List_2.put("La Planche Latérale Gauche", 45);
			List_2.put("La Planche Latérale Droite", 45);
			List_2.put("La Planche Dos", 60);
			break;
		case 2:
			List_2.put("La Planche", 60);
			List_2.put("La Planche Latérale Gauche avec torsion", 30);
			List_2.put("La Planche Latérale Droite avec torsion", 30);
			List_2.put("La Planche 1 bras 1 jambe levée gauche", 30);
			List_2.put("La Planche 1 bras 1 jambe levée droit", 30);
			break;
		}
		return List_1;	
	} 		

	public void choix_Renforcement() {
		System.out.println("Session: ");
		for (String s : List_1.keySet()) {
			System.out.println("Exercices: " + s + " Répétitions: " + List_1.get(s));
		}
	}
	public void choix_Gainage() {
		System.out.println("Session: ");
		for (String s : List_2.keySet()) {
			System.out.println("Exercices: " + s + " Répétitions: " + List_2.get(s));
		}
	}
}