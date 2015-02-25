package com.project.m2ssi.enregistreur.implementation;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseMotionListener;

import com.project.m2ssi.dialog.NouvelEnregistreurDialog;
import com.project.m2ssi.enregistreur.EntreeDuJournal;
import com.project.m2ssi.enregistreur.ResumeEnregistreurDeDonnees;
import com.project.m2ssi.enregistreur.implementation.evenement.SourisMovedEvenement;
public final class SourisMovementEvenements extends ResumeEnregistreurDeDonnees
		implements NativeMouseMotionListener {
	public SourisMovementEvenements() {
		super();
		GlobalScreen.addNativeMouseMotionListener(this);
	}

	@Override
	public void fermer() {
		super.fermer();
		GlobalScreen.removeNativeMouseMotionListener(this);
	}

	@Override
	public String type() {
		return "MouseMovement";
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent event) {

		SourisMovedEvenement evenementMove = new SourisMovedEvenement(event);
		System.out.println(NouvelEnregistreurDialog.MouseMovement);
		if(NouvelEnregistreurDialog.MouseMovement==1)
			EnregisteurGlobale.MouseMovement.add(evenementMove.toString());
		EntreeDuJournal logEntry = new EntreeDuJournal(this, evenementMove.toString(), evenementMove);
		notifierEnregistreurDonneesObservateurs(this, logEntry);

	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent nme) {
	}

}

