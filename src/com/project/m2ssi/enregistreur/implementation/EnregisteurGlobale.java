package com.project.m2ssi.enregistreur.implementation;

import java.util.Vector;

public class EnregisteurGlobale {

	public static Vector<String> CharTyped;
	public static Vector<String> keyPressed;
	public static Vector<String> keyReleased;
	
	public static Vector<String> MouseClick;
	public static Vector<String> MousePressed;
	public static Vector<String> MouseReleased;
	public static Vector<String> MouseMovement;
	
	public EnregisteurGlobale() {
		CharTyped = new Vector<>();
		keyPressed = new Vector<>();
		keyReleased = new Vector<>();
		
		MouseClick = new Vector<>();
		MousePressed = new Vector<>();
		MouseReleased = new Vector<>();
		MouseMovement = new Vector<>();
	}
	public EnregisteurGlobale(Vector<String> charTyped, Vector<String> keyPressed,
			Vector<String> keyReleased, Vector<String> mouseClick,
			Vector<String> mousePressed, Vector<String> mouseReleased,
			Vector<String> mouseMovement) {
		super();
		CharTyped = charTyped;
		this.keyPressed = keyPressed;
		this.keyReleased = keyReleased;
		MouseClick = mouseClick;
		MousePressed = mousePressed;
		MouseReleased = mouseReleased;
		MouseMovement = mouseMovement;
	}
	public Vector<String> getCharTyped() {
		return CharTyped;
	}
	public void add(String logMsg) {
		CharTyped.add(logMsg);
	}
	public Vector<String> getKeyPressed() {
		return keyPressed;
	}
	public void setKeyPressed(Vector<String> keyPressed) {
		this.keyPressed = keyPressed;
	}
	public Vector<String> getKeyReleased() {
		return keyReleased;
	}
	public void setKeyReleased(Vector<String> keyReleased) {
		this.keyReleased = keyReleased;
	}
	public Vector<String> getMouseClick() {
		return MouseClick;
	}
	public void setMouseClick(Vector<String> mouseClick) {
		MouseClick = mouseClick;
	}
	public Vector<String> getMousePressed() {
		return MousePressed;
	}
	public void setMousePressed(Vector<String> mousePressed) {
		MousePressed = mousePressed;
	}
	public Vector<String> getMouseReleased() {
		return MouseReleased;
	}
	public void setMouseReleased(Vector<String> mouseReleased) {
		MouseReleased = mouseReleased;
	}
	public Vector<String> getMouseMovement() {
		return MouseMovement;
	}
	public void setMouseMovement(Vector<String> mouseMovement) {
		MouseMovement = mouseMovement;
	}
	@Override
	public String toString() {
		return "LoggerGlobal [CharTyped=" + CharTyped + ", keyPressed="
				+ keyPressed + ", keyReleased=" + keyReleased + ", MouseClick="
				+ MouseClick + ", MousePressed=" + MousePressed
				+ ", MouseReleased=" + MouseReleased + ", MouseMovement="
				+ MouseMovement + "]";
	}
	
}
