package com.project.m2ssi.enregistreur;

import static com.project.m2ssi.util.Assertions.requireNotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class ResumeEnregistreurDeDonnees implements EnregistreurDeDonnees {

	private final List<EnregistreurDeDonneesObservateur> observateurs = new ArrayList<>();

	private final EnregistreurDeDonneesNotificationThread notifierThread;

	private boolean enregistrement = true;

	protected ResumeEnregistreurDeDonnees() {
		notifierThread = new EnregistreurDeDonneesNotificationThread(this, observateurs);
		notifierThread.start();

	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
		fermer();
	}

	@Override
	public void fermer() {
		notifierThread.fermer();

	}

	@Override
	public void ajouterObservateur(EnregistreurDeDonneesObservateur observer) {

		requireNotNull(observer);
		
		synchronized (observateurs) {
			observateurs.add(observer);
		}

	}

	@Override
	public void supprimerObservateur(EnregistreurDeDonneesObservateur observateur) {

		requireNotNull(observateur);
		
		synchronized (observateurs) {
			observateurs.remove(observateur);
		}

	}

	protected void notifierEnregistreurDonneesObservateurs(final EnregistreurDeDonnees enregistreur, final EntreeDuJournal entreeDuJournal) {

		requireNotNull(entreeDuJournal);
		
		if (isEnregistrement()) {
			notifierThread.notifyEvent(entreeDuJournal);

		}

	}

	@Override
	public boolean isEnregistrement() {
		return enregistrement;
	}

	@Override
	public void setEnregistrement(boolean enregistrement) {
		this.enregistrement = enregistrement;
	}

	@Override
	public String toString() {
		return type();
	}

	@Override
	public abstract String type();

}
