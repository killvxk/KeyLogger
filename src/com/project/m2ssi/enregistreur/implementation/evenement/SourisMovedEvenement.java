package com.project.m2ssi.enregistreur.implementation.evenement;

import org.jnativehook.mouse.NativeMouseEvent;

public final class SourisMovedEvenement extends EvenementSouris {

	public SourisMovedEvenement() {
		super();
	}

	public SourisMovedEvenement(final NativeMouseEvent event) {
		super(event);
	}

	@Override
	public String type() {
		return "MouseMoved";
	}

	@Override
	public String toString() {

		StringBuilder msg = new StringBuilder();

		msg.append("Mouse moved - at ");
		msg.append(posString(getX(), getY()));
		msg.append(".");

		return msg.toString();

	}

}
