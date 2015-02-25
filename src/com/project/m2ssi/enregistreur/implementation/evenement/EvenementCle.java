package com.project.m2ssi.enregistreur.implementation.evenement;

import static com.project.m2ssi.util.Assertions.requireNotNull;

import org.jnativehook.keyboard.NativeKeyEvent;

import com.project.m2ssi.enregistreur.JournaliserEvenement;

public class EvenementCle extends JournaliserEvenement {
	public static enum Type {

		PRESSED("pressed"), RELEASED("released"), TYPED("typed");

		private String type;

		private Type(String type) {
			this.type = type;
		}
		public static Type getByName(String typeName) {
			
			requireNotNull(typeName);
			
			if (typeName.equals("pressed")) {
				return PRESSED;
			} else if (typeName.equals("released")) {
				return RELEASED;
			} else if (typeName.equals("typed")) {
				return TYPED;
			} else {
				
				throw new IllegalArgumentException(
					String.format("Unknown key event type %s.", typeName)
				);
				
			}
			
		}

		@Override
		public String toString() {
			return type;
		}

	}

	private Type eventType = null;
	private int keyCode = -1;
	private char ch = (char) -1;

	public EvenementCle() {
	}

	public EvenementCle(Type eventType, int keyCode, char ch) {
		this.eventType = eventType;
		this.keyCode = keyCode;
		this.ch = ch;
	}

	public Type getEventType() {
		return eventType;
	}

	public void setEventType(Type eventType) {
		this.eventType = eventType;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public char getChar() {
		return ch;
	}

	public void setChar(char ch) {
		this.ch = ch;
	}

	@Override
	public String type() {
		return "KeyEvent";
	}
	

	@Override
	public String toString() {

		if (keyCode < 0) {
			return String.format("%s %s", Character.toString(ch), eventType.toString());
		} else {
			return String.format("%s %s", NativeKeyEvent.getKeyText(keyCode), eventType.toString());
		}

	}
}
