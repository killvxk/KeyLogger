package com.project.m2ssi.enregistreur.implementation;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.project.m2ssi.enregistreur.EntreeDuJournal;
import com.project.m2ssi.enregistreur.ResumeEnregistreurDeDonnees;
import com.project.m2ssi.enregistreur.implementation.evenement.EvenementCle;

public final class EnregistreurKeyReleased extends ResumeEnregistreurDeDonnees implements NativeKeyListener {

	public EnregistreurKeyReleased() {
		super();
		GlobalScreen.addNativeKeyListener(this);
	}

	@Override
	public String type() {
		return "KeyReleased";
	}

	@Override
	public void fermer() {
		super.fermer();
		GlobalScreen.removeNativeKeyListener(this);
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent event) {
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent event) {

		EvenementCle evenementCle = new EvenementCle(EvenementCle.Type.RELEASED, event.getKeyCode(), event.getKeyChar());
		String key = NativeKeyEvent.getKeyText(event.getKeyCode());
		EnregisteurGlobale.keyReleased.add(key);
		EntreeDuJournal logEntry = new EntreeDuJournal(this, String.format("%s ", key), evenementCle);
		notifierEnregistreurDonneesObservateurs(this, logEntry);

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent event) {
	
	}

}
