package InterfaceGraphique;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import BaseDeDonnées.Utilisateur;
import Listeners.LoginActionListener;

/**
 * Interface relative au Poids
 * 
 * @author Wilhelm1er
 */
public class Login {
	private JFrame frameLogin = new JFrame("Login");
	private Utilisateur user = new Utilisateur();

	private JButton new_user = new JButton("Créer utilisateur");
	private JButton connexion = new JButton("Connexion");
	private JTextField inputLogin = new JTextField("", 8);
	private JPasswordField inputMdp = new JPasswordField("", 8);
	private JLabel erreur = new JLabel("Erreur, utilisateur inconnu ou mot de passe erroné");
	private String name = "";

	/**
	 * Crée et active l'interface de login.
	 */
	public void login() {

		frameLogin.setLayout(new BorderLayout());
		frameLogin.setSize(300, 300);
		frameLogin.setLocationRelativeTo(null);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogin.setVisible(true);

		JPanel panelNorth = new JPanel();
		JPanel panelSouth = new JPanel();
		JPanel panelCenter = new JPanel();

		JLabel labelLogin = new JLabel("Login:");
		JLabel labelMdp = new JLabel("Mot de passe:");
		inputLogin.setSize(10, 20);
		inputMdp.setSize(10, 20);
		
		labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
		labelMdp.setHorizontalAlignment(SwingConstants.CENTER);
		inputMdp.setHorizontalAlignment(SwingConstants.CENTER);
		inputLogin.setHorizontalAlignment(SwingConstants.CENTER);

		new_user.setSize(10, 20);
		connexion.setSize(10, 20);

		new_user.addActionListener(new LoginActionListener(this));
		connexion.addActionListener(new LoginActionListener(this));

		JLabel labelWelcome = new JLabel("Application training");

		panelNorth.add(labelWelcome);

		panelCenter.setLayout(new GridLayout(6, 1));
		panelSouth.setLayout(new GridLayout(6, 1));

		inputLogin.setText("");
		inputMdp.setText("");

		frameLogin.add(panelSouth, BorderLayout.SOUTH);
		frameLogin.add(panelCenter, BorderLayout.CENTER);

		frameLogin.getRootPane().setDefaultButton(connexion);

		JLabel listUser = new JLabel("Utilisateurs connus: ");
		listUser.setHorizontalAlignment(SwingConstants.CENTER);
		panelSouth.add(listUser);

		for (String s : user.selectAll()) {
			JLabel label = new JLabel(s);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setVisible(true);
			panelSouth.add(label);
		}

		inputMdp.setEchoChar('*');
		erreur.setVisible(false);

// A CORRIGER
		// UTILITE?
		InputMap im = connexion.getInputMap();
		im.put(KeyStroke.getKeyStroke("ENTER"), "pressed");
		im.put(KeyStroke.getKeyStroke("released ENTER"), "released");

		panelCenter.add(labelLogin);
		panelCenter.add(inputLogin);
		panelCenter.add(labelMdp);
		panelCenter.add(inputMdp);

		panelCenter.add(new_user);
		panelCenter.add(connexion);

		panelCenter.add(erreur);

	}

	public JFrame getFrame() {
		return frameLogin;
	}

	public JPasswordField getInputMdp() {
		return inputMdp;
	}

	public JLabel getErreur() {
		return erreur;
	}

	public JTextField getInputLogin() {
		return inputLogin;
	}

	public JButton getNew_user() {
		return new_user;
	}

	public JButton getConnexion() {
		return connexion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}