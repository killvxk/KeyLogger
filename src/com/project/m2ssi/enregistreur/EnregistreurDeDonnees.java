package com.project.m2ssi.enregistreur;

public interface EnregistreurDeDonnees {
	public String type();

	public void fermer();
	public void ajouterObservateur(final EnregistreurDeDonneesObservateur observateur);

	public void supprimerObservateur(final EnregistreurDeDonneesObservateur observateur);
	public boolean isEnregistrement();
	public void setEnregistrement(boolean record);

	@Override
	public String toString();

}
