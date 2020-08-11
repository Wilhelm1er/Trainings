package InterfaceGraphique;

import javax.swing.JFrame;

import Listeners.MenuActionListener;

/**
 * Interface principale
 * 
 * @author Wilhelm1er
 */
public class InterfacePrincipale {

	private JFrame framePrincipal = new JFrame("Training App");
	private Menu menu = new Menu();
	
	private String name;

	/**
	 * Frame d'affichage de l'interface principale
	 *
	 */
	public void interfacePrincipale() {
		framePrincipal.getContentPane().removeAll();
		System.out.println("nom: "+name);
		menu.setMenuUser(name);
		framePrincipal.setJMenuBar(menu.menu());

		framePrincipal.setSize(600, 500);
		//framePrincipal.setLayout(new BorderLayout());
		framePrincipal.setLocationRelativeTo(null);
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.setVisible(true);

		framePrincipal.revalidate();

		menu.getQuitter().addActionListener(new MenuActionListener(this));
		menu.getLogout().addActionListener(new MenuActionListener(this));
		menu.getRenforcement().addActionListener(new MenuActionListener(this));
		menu.getMusculation().addActionListener(new MenuActionListener(this));
		menu.getGainage().addActionListener(new MenuActionListener(this));
		menu.getChallenge().addActionListener(new MenuActionListener(this));
		menu.getPoids().addActionListener(new MenuActionListener(this));
		menu.getRapports().addActionListener(new MenuActionListener(this));

	}

	public JFrame getFrame() {
		return framePrincipal;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}