package com.project.m2ssi;

import static com.project.m2ssi.util.Assertions.requireNotNull;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.project.m2ssi.enregistreur.EnregistreurDeDonnees;
import com.project.m2ssi.enregistreur.EnregistreurDeDonneesObservateur;
import com.project.m2ssi.enregistreur.EntreeDuJournal;
import com.project.m2ssi.enregistreur.JournaliserEvenement;
import com.project.m2ssi.enregistreur.implementation.evenement.EvenementSouris;

public final class EnregistreurPanel extends JPanel implements EnregistreurDeDonneesObservateur {

	private final EnregistreurDeDonnees enregistreur;
	private JTextArea textEntreeJournal = new JTextArea();

	public EnregistreurPanel(final EnregistreurDeDonnees enregistreur) {

		super();
		
		requireNotNull(enregistreur);

		this.enregistreur = enregistreur;
		enregistreur.ajouterObservateur(this);

		initialisation();
		construireForme();
		
	}
	
	private void initialisation() {

		textEntreeJournal.setEditable(false);
		textEntreeJournal.setFont(new Font("Serif", Font.PLAIN, 20));
		textEntreeJournal.setColumns(20);
		textEntreeJournal.setRows(5);
		textEntreeJournal.setLineWrap(true);
		
	}
	
	private void construireForme() {
		
		setLayout(new GridLayout(0, 1));
		
		JScrollPane scrollentreeJournal = new javax.swing.JScrollPane();

		scrollentreeJournal.setDoubleBuffered(true);
		scrollentreeJournal.setEnabled(false);
		
		scrollentreeJournal.setViewportView(textEntreeJournal);
		
		add(scrollentreeJournal);
		
	}
	
	public JTextArea getTextEntreeJournal() {
		return textEntreeJournal;
	}

	public void fermer() {
		enregistreur.supprimerObservateur(this);
	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
		fermer();
	}

	private JournaliserEvenement journaliserEvenement = null;

	private void ajouterJournal(final EnregistreurDeDonnees enregistreur, final EntreeDuJournal entreeDuJournal) {

		StringBuilder entreeJournal = new StringBuilder(getTextEntreeJournal().getText());

		if (journaliserEvenement == null) {
			journaliserEvenement = entreeDuJournal.getevenement();
		} else if (journaliserEvenement.type().equals(entreeDuJournal.getevenement().type())) {

			if (entreeJournal.charAt(entreeJournal.length()-1) != '\n')
				entreeJournal.append("");

			journaliserEvenement = entreeDuJournal.getevenement();

		}

		String entryText = entreeDuJournal.toString();
		if (entreeDuJournal.getevenement() instanceof EvenementSouris)
			entryText += "\n";
		
		entreeJournal.append(entryText);
		
		getTextEntreeJournal().setText(entreeJournal.toString());
		getTextEntreeJournal().setCaretPosition(
			getTextEntreeJournal().getText().length() - 1
		);


	}

	@Override
	public void informerJournal(final EnregistreurDeDonnees enregistreur, final EntreeDuJournal entreeDuJournal) {

		ajouterJournal(enregistreur, entreeDuJournal);

	}

}
