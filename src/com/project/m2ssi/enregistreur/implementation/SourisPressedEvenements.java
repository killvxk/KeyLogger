package com.project.m2ssi.enregistreur.implementation;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import com.project.m2ssi.dialog.NouvelEnregistreurDialog;
import com.project.m2ssi.enregistreur.EntreeDuJournal;
import com.project.m2ssi.enregistreur.ResumeEnregistreurDeDonnees;
import com.project.m2ssi.enregistreur.implementation.evenement.SourisPressedEvenement;

public final class SourisPressedEvenements extends ResumeEnregistreurDeDonnees
		implements NativeMouseListener {
	public SourisPressedEvenements() {
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
		return "MousePressed";
	}

	@Override
	public void nativeMouseClicked(final NativeMouseEvent event) {
	}
	
	@Override
	public void nativeMousePressed(NativeMouseEvent event) {

		SourisPressedEvenement evenementClique =
			new SourisPressedEvenement(event);
		if(NouvelEnregistreurDialog.MousePressed==1)
			EnregisteurGlobale.MousePressed.add(evenementClique.toString());
		EntreeDuJournal logEntry = new EntreeDuJournal(this, evenementClique.toString(), evenementClique);
		notifierEnregistreurDonneesObservateurs(this, logEntry);

	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent event) {
	}

}
