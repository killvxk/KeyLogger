package com.project.m2ssi.enregistreur.implementation;

import static com.project.m2ssi.util.Assertions.require;
import static com.project.m2ssi.util.Assertions.requireNotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.zeddev.litelogger.Logger;

import com.project.m2ssi.dialog.NouvelEnregistreurDialog;
import com.project.m2ssi.enregistreur.EnregistreurDeDonnees;
import com.project.m2ssi.enregistreur.EnregistreurDeDonneesObservateur;
import com.project.m2ssi.enregistreur.EntreeDuJournal;
import com.project.m2ssi.enregistreur.ResumeEnregistreurDeDonnees;

public final class EnregistreurDeDonneesComposite extends ResumeEnregistreurDeDonnees implements EnregistreurDeDonneesObservateur {

	private final Logger enregistreur = Logger.getLogger(this);
	private final List<EntreeDuJournal> entreeDuJournals = new ArrayList<>();

	private final List<EnregistreurDeDonnees> enregistreurDeDonnees = new ArrayList<>();
	private File fichierJournal = null;
	public EnregisteurGlobale global = new EnregisteurGlobale();
	
	public EnregistreurDeDonneesComposite() {
		super();
	}

	@Override
	public String type() {
		return "Enregistreur";
	}

	public EnregistreurDeDonnees getEnregistreurDeDonneesComposite(int index) {

		require(index >= 0 && index < enregistreurDeDonnees.size());

		synchronized (enregistreurDeDonnees) {
			return enregistreurDeDonnees.get(index);
		}

	}

	public void ajouterEnregistreur(final EnregistreurDeDonnees logger) throws IOException {

		requireNotNull(logger);

		synchronized (enregistreurDeDonnees) {

			logger.setEnregistrement(isEnregistrement());

			logger.ajouterObservateur(this);
			enregistreurDeDonnees.add(logger);
			
		}

	}

	public void supprimerEnregistreur(final EnregistreurDeDonnees logger) throws IOException {

		requireNotNull(logger);

		synchronized (enregistreurDeDonnees) {

			logger.supprimerObservateur(this);
			enregistreurDeDonnees.remove(logger);

		}

	}

	public void retirerEnregistreur(int index) {
		require(index >= 0 && index < enregistreurDeDonnees.size());
		enregistreurDeDonnees.remove(index);
	}
	public boolean containsLogger(EnregistreurDeDonnees logger) {
		requireNotNull(logger);
		return enregistreurDeDonnees.contains(logger);
	}

	public void effacerTout() throws IOException {
		entreeDuJournals.clear();
	}

	public List<EnregistreurDeDonnees> getEnregistreurDeDonneesComposite() {
		return new ArrayList<>(enregistreurDeDonnees);
	}
	
	public List<EntreeDuJournal> entreeDuJournals() {
		return new ArrayList<>(entreeDuJournals);
	}

	public void setFichierJournal(File file) throws IOException {
		
		requireNotNull(file);
		
		if (file.exists()) {
			require(file.isFile());
			this.fichierJournal = file;
		} else {
			File remove = new File(file+".txt");
			remove.createNewFile();
			FileWriter fichier = new FileWriter(remove.getPath());
			if(EnregisteurGlobale.CharTyped.size()>0 && NouvelEnregistreurDialog.CharTyped==1){
				fichier.write("\r\nCharTyped  = "+EnregisteurGlobale.CharTyped.toString());
			}
			if(EnregisteurGlobale.keyPressed.size()>0 && NouvelEnregistreurDialog.KeyPressed==1){
				fichier.write("\r\nkeyPressed  = "+EnregisteurGlobale.keyPressed.toString());
			}
			if(EnregisteurGlobale.keyReleased.size()>0 && NouvelEnregistreurDialog.KeyReleased==1){
				fichier.write("\r\nkey Released  = "+EnregisteurGlobale.keyReleased.toString());
			}
			if(EnregisteurGlobale.MouseClick.size()>0 && NouvelEnregistreurDialog.MouseClick==1){
				fichier.write("\r\nMouse Click  = "+EnregisteurGlobale.MouseClick.toString());
			}
			if(EnregisteurGlobale.MousePressed.size()>0 && NouvelEnregistreurDialog.MousePressed==1){
				fichier.write("\r\nMouse Pressed  = "+EnregisteurGlobale.MousePressed.toString());
			}
			if(EnregisteurGlobale.MouseReleased.size()>0 && NouvelEnregistreurDialog.MouseReleased==1){
				fichier.write("\r\nMouse Released  = "+EnregisteurGlobale.MouseReleased.toString());
			}
			if(EnregisteurGlobale.MouseMovement.size()>0 && NouvelEnregistreurDialog.MouseMovement==1){
				fichier.write("\r\nMouse Movement  = "+EnregisteurGlobale.MouseMovement.toString());
			}
			fichier.close();
			this.fichierJournal = remove;
		}
		
		
	}
	
	public File getFichierJournal() {
		return fichierJournal;
	}
	
	@Override
	public void informerJournal(final EnregistreurDeDonnees logger, final EntreeDuJournal logEntry) {

		requireNotNull(logEntry);

		if (isEnregistrement()) {

			entreeDuJournals.add(logEntry);
			notifierEnregistreurDonneesObservateurs(logger, logEntry);

		}

	}

	@Override
	public final void setEnregistrement(boolean enregistrement) {

		synchronized (enregistreurDeDonnees) {
			for (EnregistreurDeDonnees logger : enregistreurDeDonnees)
				logger.setEnregistrement(enregistrement);

			super.setEnregistrement(enregistrement);

		}

	}

	@Override
	public String toString() {

		final StringBuilder log = new StringBuilder();
		EnregistreurDeDonnees lastLogger = null;

		for (EntreeDuJournal logEntry : entreeDuJournals) {

			if (lastLogger == null) {
				lastLogger = logEntry.getParent();
			} else if (enregistreur != lastLogger) {

				if (log.charAt(log.length()-1) != '\n')
					log.append("\n");

				lastLogger = logEntry.getParent();

			}

			log.append(logEntry);

		}
		
		return log.toString();

	}

}