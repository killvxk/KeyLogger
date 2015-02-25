package com.project.m2ssi;

import static com.project.m2ssi.util.Assertions.requireNotNull;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import net.zeddev.litelogger.LogLevel;
import net.zeddev.litelogger.Logger;
import net.zeddev.litelogger.builtin.MsgBoxLogHandler;
import net.zeddev.litelogger.builtin.WindowLogHandler;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import com.project.m2ssi.dialog.NouvelEnregistreurDialog;
import com.project.m2ssi.dialog.SimpleDialog;
import com.project.m2ssi.enregistreur.EnregistreurDeDonnees;
import com.project.m2ssi.enregistreur.implementation.EnregistreurDeDonneesComposite;
import com.project.m2ssi.keylogger.configuration;

public final class KeyLoggerInterface extends JFrame implements NativeMouseListener {

	private final Logger enregistreur = Logger.getLogger(this);
	private final WindowLogHandler fenetreJournal = new WindowLogHandler();

	private final EnregistreurDeDonneesComposite enregistreurDeDonneesComposite;
	
	
	private JButton Enregister = new JButton();
	private JButton Ajouter = new JButton();
	private JButton Retirer = new JButton();
	private JButton Efface = new JButton();
	private JButton Pause = new JButton();
	
	
	
	private JTabbedPane tabs = new JTabbedPane();

	public KeyLoggerInterface() {
		this(new EnregistreurDeDonneesComposite());
	}
	
	public KeyLoggerInterface(EnregistreurDeDonneesComposite enregistreurDeDonneesComposite) {
		
		requireNotNull(enregistreurDeDonneesComposite);

		this.enregistreurDeDonneesComposite = enregistreurDeDonneesComposite;
		initialisationEnregistreurTabs();
		
		initialisation();
		construireForme();

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setTitle(configuration.INSTANCE.FULL_NAME);
		
		setMinimumSize(new Dimension(500, 400));
		
		ImageIcon ico = (ImageIcon) Icones.getInstance().getIcon("logo");
		setIconImage(ico.getImage());

		setLocationRelativeTo(null);

		Logger.addObserver(new MsgBoxLogHandler(LogLevel.WARNING));
		Logger.addObserver(fenetreJournal);
		
		GlobalScreen.addNativeMouseListener(this);

	}
	
	public void fermer() {

		enregistreurDeDonneesComposite.fermer();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				dispose();
			}
		});

	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
		
		fermer();
		
	}

	private void initialisationEnregistreurTabs() {
		
		ajouterEnregistreurTab(enregistreurDeDonneesComposite);
		
		for (EnregistreurDeDonnees enregistreurDeDonnees : enregistreurDeDonneesComposite.getEnregistreurDeDonneesComposite())
			ajouterEnregistreurTab(enregistreurDeDonnees);
		
	}

	private void supprimerEnregistreurTabs() {

		while (tabs.getTabCount() > 0){
			tabs.remove(0);
		}
	}

	private void ReinitialiseEnregistreurDeDonneesCompositeVue() {

		supprimerEnregistreurTabs();

		initialisationEnregistreurTabs();

	}
	
	private void ajouterEnregistreurTab(final EnregistreurDeDonnees enregistreur) {

		assert(enregistreur != null);

		EnregistreurPanel enregistreurPanel = new EnregistreurPanel(enregistreur);

		tabs.add(enregistreur.type(), enregistreurPanel);

	}

	private void initialisation() {

		Enregister.setIcon(Icones.getInstance().getIcon("Enregistrer"));
		Enregister.setToolTipText("Réglez le fichier journal pour les entrées du journal sont stockés automatiquement.");
		Enregister.setFocusable(false);

		Ajouter.setIcon(Icones.getInstance().getIcon("Ajouter"));
		Ajouter.setToolTipText("Ajouter un nouvel enregistreur à KeyLogger.");

		Retirer.setIcon(Icones.getInstance().getIcon("Retirer"));
		Retirer.setToolTipText("Retirer l'enregistreur sélectionné dans KeyLogger.");

		Efface.setIcon(Icones.getInstance().getIcon("Efface"));
		Efface.setToolTipText("Efface toutes les entrées du journal de tous les enregistreurs.");

		Pause.setIcon(Icones.getInstance().getIcon("Pause"));
		Pause.setToolTipText("Mettre en pause/reprendre l'enregistrement des événements.");
		Pause.setFocusable(false);

		initListeners();
		
	}
		
	private void initListeners() {
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});
		
		Enregister.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				EnregisterActionPerformed(evt);
			}
		});
		

		Ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				AjouterActionPerformed(event);
			}
		});

		Efface.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				EffaceActionPerformed(evt);
			}
		});

		Retirer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				RetirerActionPerformed(event);
			}
		});

		Pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				PauseActionPerformed(event);
			}
		});
		
	}
	
	
	private JPanel construireToolbar() {
		
		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(true);
		toolbar.setRollover(true);
		
		toolbar.add(Enregister);
		
		toolbar.add(new JToolBar.Separator());
		
		toolbar.add(Ajouter);
		toolbar.add(Retirer);
		toolbar.add(Efface);
		
		toolbar.add(new JToolBar.Separator());
		
		toolbar.add(Pause);
		
		toolbar.add(new JToolBar.Separator());
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		panel.add(toolbar);
		
		return panel;
		
	}
	
	private void construireForme() {
		
		setLayout(new BorderLayout());
		
		add(construireToolbar(), BorderLayout.NORTH);
		add(tabs, BorderLayout.CENTER);
		
		pack();
		
	}
	

	private void setFichierJournal() {

		File fichierJournal = SimpleDialog.saveFile(this);
		try {

			if (fichierJournal != null) {
				enregistreurDeDonneesComposite.setFichierJournal(fichierJournal);
			}

		} catch (IOException ex) {
			
		}

	}
	
	private void ajouterEnregistreurDeDonnees() {

		NouvelEnregistreurDialog nouvelEnregistreurDialog = new NouvelEnregistreurDialog(this, true);
		EnregistreurDeDonnees enregistreurDeDonnees = nouvelEnregistreurDialog.getFormeEnregistreur();
		
		if (enregistreurDeDonnees == null)
			return;

		try {
			enregistreurDeDonneesComposite.ajouterEnregistreur(enregistreurDeDonnees);
		} catch (IOException ex) {

			String msg =
				String.format(enregistreurDeDonnees.type());
			enregistreur.error(msg, ex);

			return;

		}

		ajouterEnregistreurTab(enregistreurDeDonnees);

		enregistreur.info(String.format(enregistreurDeDonnees.type()));

	}

	private void supprimerEnregistreurDeDonnees() {
		if (tabs.getSelectedIndex() > 0) {
			EnregistreurDeDonnees enregistreurDeDonnees = enregistreurDeDonneesComposite.getEnregistreurDeDonneesComposite(tabs.getSelectedIndex() - 1);
			if (enregistreurDeDonnees instanceof EnregistreurDeDonneesComposite) {
				enregistreur.error("la suppression n'est pas effectué !");
			} else {

				try {
					enregistreurDeDonneesComposite.supprimerEnregistreur(enregistreurDeDonnees);
				} catch (IOException ex) {

					String msg =
						String.format(enregistreurDeDonnees.type());
					enregistreur.error(msg,ex);


				}

				tabs.remove(tabs.getSelectedIndex());

			}
			if(enregistreurDeDonnees.toString().trim().equals("CharTyped"))
				NouvelEnregistreurDialog.CharTyped=0;
			if(enregistreurDeDonnees.toString().trim().equals("KeyPressed"))
				NouvelEnregistreurDialog.KeyPressed=0;
			if(enregistreurDeDonnees.toString().trim().equals("KeyReleased"))
				NouvelEnregistreurDialog.KeyReleased=0;
			if(enregistreurDeDonnees.toString().trim().equals("MouseClick"))
				NouvelEnregistreurDialog.MouseClick=0;
			if(enregistreurDeDonnees.toString().trim().equals("MousePressed"))
				NouvelEnregistreurDialog.MousePressed=0;
			if(enregistreurDeDonnees.toString().trim().equals("MouseReleased"))
				NouvelEnregistreurDialog.MouseReleased=0;
			if(enregistreurDeDonnees.toString().trim().equals("MouseMovement"))
				NouvelEnregistreurDialog.MouseMovement=0;
			
			enregistreur.info(String.format(enregistreurDeDonnees.type()));
		}

	}
	
	private void effacerTout() {

		try {
			enregistreurDeDonneesComposite.effacerTout();
		} catch (IOException ex) {
			enregistreur.error("erreur de suppression", ex);
		}

		ReinitialiseEnregistreurDeDonneesCompositeVue();


	}

	private void Quitter() {

		if (enregistreurDeDonneesComposite.getFichierJournal() == null) {

			boolean Quitter = SimpleDialog.yesno(
				this, "Quitter",
				"Les données du journal peut être perdu.\n"
				+ "Etes-vous sûr de vouloir Quitter?"
			);

			if (!Quitter) {
				return;
			}

		}

		setVisible(false);
		System.exit(0);

	}
	
	
	private void formWindowClosing(WindowEvent evt) {
		Quitter();
	}
	
	private void EnregisterActionPerformed(java.awt.event.ActionEvent evt) {
		setFichierJournal();
	}

	
	private void AjouterActionPerformed(final ActionEvent event) {
		ajouterEnregistreurDeDonnees();
	}

	private void RetirerActionPerformed(final ActionEvent event) {
		supprimerEnregistreurDeDonnees();
	}
	
	private void EffaceActionPerformed(final ActionEvent event) {
		effacerTout();
	}
	
	private void PauseActionPerformed(final ActionEvent event) {

		enregistreurDeDonneesComposite.setEnregistrement(!enregistreurDeDonneesComposite.isEnregistrement());

		if (enregistreurDeDonneesComposite.isEnregistrement()) {
			Pause.setIcon(Icones.getInstance().getIcon("pause"));
		} else {

			Pause.setIcon(Icones.getInstance().getIcon("Reprendre"));
		}

	}

	@Override
	public void nativeMouseClicked(NativeMouseEvent event) {

	}

	@Override
	public void nativeMousePressed(NativeMouseEvent event) {
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent nme) {
	}	
	
}
