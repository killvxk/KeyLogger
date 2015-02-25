package com.project.m2ssi.enregistreur.implementation;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import com.project.m2ssi.dialog.NouvelEnregistreurDialog;
import com.project.m2ssi.enregistreur.EntreeDuJournal;
import com.project.m2ssi.enregistreur.ResumeEnregistreurDeDonnees;
import com.project.m2ssi.enregistreur.implementation.evenement.SourisCliqueEvenement;
public final class SourisCliqueEvenements extends ResumeEnregistreurDeDonnees
		implements NativeMouseListener {
	public SourisCliqueEvenements() {
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
		return "MouseClick";
	}

	@Override
	public void nativeMouseClicked(final NativeMouseEvent event) {

		SourisCliqueEvenement evenementClique =
			new SourisCliqueEvenement(event);
		if(NouvelEnregistreurDialog.MouseClick==1)
			EnregisteurGlobale.MouseClick.add(evenementClique.toString());
		EntreeDuJournal logEntry = new EntreeDuJournal(this, evenementClique.toString(), evenementClique);
		notifierEnregistreurDonneesObservateurs(this, logEntry);

	}

	@Override
	public void nativeMousePressed(NativeMouseEvent event) {
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent event) {
	}

}

