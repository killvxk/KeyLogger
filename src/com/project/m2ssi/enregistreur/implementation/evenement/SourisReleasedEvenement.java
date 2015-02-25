package com.project.m2ssi.enregistreur.implementation.evenement;

import org.jnativehook.mouse.NativeMouseEvent;

public final class SourisReleasedEvenement extends EvenementSouris {

	private int buttonCode = -1;
	private String button = null;

	public SourisReleasedEvenement() {
	}

	public SourisReleasedEvenement(final NativeMouseEvent event) {
		super(event);
		setButtonCode(event.getButton());
		setButton(buttonName(event.getButton()));
	}

	public final int getButtonCode() {
		return buttonCode;
	}

	public final void setButtonCode(int buttonCode) {
		this.buttonCode = buttonCode;
	}

	public final String getButton() {
		return button;
	}

	public final void setButton(String button) {
		this.button = button;
	}

	@Override
	public String type() {
		return "MouseReleased";
	}
	

	@Override
	public String toString() {

		StringBuilder msg = new StringBuilder();

		msg.append("Mouse released - ");
		msg.append(getButton());
		msg.append(" at ");
		msg.append(posString(getX(), getY()));
		msg.append(".");

		return msg.toString();

	}
}
