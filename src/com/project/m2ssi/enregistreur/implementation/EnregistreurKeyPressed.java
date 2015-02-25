package com.project.m2ssi.enregistreur.implementation;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.project.m2ssi.enregistreur.EntreeDuJournal;
import com.project.m2ssi.enregistreur.ResumeEnregistreurDeDonnees;
import com.project.m2ssi.enregistreur.implementation.evenement.EvenementCle;

public final class EnregistreurKeyPressed extends ResumeEnregistreurDeDonnees implements NativeKeyListener {

	public EnregistreurKeyPressed() {
		super();
		GlobalScreen.addNativeKeyListener(this);
	}

	@Override
	public String type() {
		return "KeyPressed";
	}

	@Override
	public void fermer() {
		super.fermer();
		GlobalScreen.removeNativeKeyListener(this);
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent event) {

		EvenementCle evenementCle = new EvenementCle(EvenementCle.Type.PRESSED, event.getKeyCode(), event.getKeyChar());
		String key = NativeKeyEvent.getKeyText(event.getKeyCode());
		EnregisteurGlobale.keyPressed.add(key);
		EntreeDuJournal logEntry = new EntreeDuJournal(this, String.format("%s ", key), evenementCle);
		notifierEnregistreurDonneesObservateurs(this, logEntry);

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent event) {
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent event) {
	}

}
