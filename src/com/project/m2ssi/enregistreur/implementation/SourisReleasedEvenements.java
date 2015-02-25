package com.project.m2ssi.enregistreur.implementation;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import com.project.m2ssi.dialog.NouvelEnregistreurDialog;
import com.project.m2ssi.enregistreur.EntreeDuJournal;
import com.project.m2ssi.enregistreur.ResumeEnregistreurDeDonnees;
import com.project.m2ssi.enregistreur.implementation.evenement.SourisReleasedEvenement;
public final class SourisReleasedEvenements extends ResumeEnregistreurDeDonnees
		implements NativeMouseListener {
	public SourisReleasedEvenements() {
		super();
		GlobalScreen.addNativeMouseListener(this);
	}

	@Override
	public void fermer() {
		super.fermer();
		GlobalScreen.removeNativeMouseListener(this);
	}

	@Override
	public String type() {
		return "MouseRelease";
	}

	@Override
	public void nativeMouseClicked(final NativeMouseEvent event) {
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent event) {
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent event) {

		SourisReleasedEvenement evenementClique =
			new SourisReleasedEvenement(event);
		if(NouvelEnregistreurDialog.MouseReleased==1)
			EnregisteurGlobale.MouseReleased.add(evenementClique.toString());
		EntreeDuJournal logEntry = new EntreeDuJournal(this, evenementClique.toString(), evenementClique);
		notifierEnregistreurDonneesObservateurs(this, logEntry);

	}

}
