package com.project.m2ssi.enregistreur.implementation.evenement;

import org.jnativehook.mouse.NativeMouseEvent;
public final class SourisCliqueEvenement extends EvenementSouris {

	private int buttonCode = -1;
	private String button = null;
	private int clickCount = -1;

	public SourisCliqueEvenement() {
	}

	public SourisCliqueEvenement(final NativeMouseEvent event) {
		super(event);
		setButtonCode(event.getButton());
		setButton(buttonName(event.getButton()));
		setClickCount(event.getClickCount());
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

	public final int getClickCount() {
		return clickCount;
	}

	public final void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	@Override
	public String type() {
		return "MouseClicked";
	}

	@Override
	public String toString() {

		StringBuilder msg = new StringBuilder();

		msg.append("Mouse clicked - ");
		msg.append(getButton());
		msg.append(" at ");
		msg.append(posString(getX(), getY()));
		msg.append(".");

		return msg.toString();

	}

 }
