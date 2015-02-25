package com.project.m2ssi.enregistreur.implementation.evenement;

import org.jnativehook.mouse.NativeMouseEvent;

import com.project.m2ssi.enregistreur.JournaliserEvenement;

public abstract class EvenementSouris extends JournaliserEvenement {

   private int x, y;

   protected EvenementSouris() {
	   x = y = -1;
   }

   protected EvenementSouris(final NativeMouseEvent event) {
	   setX(event.getX());
	   setY(event.getY());
   }

   public final int getX() {
	   return x;
   }

   public final void setX(int x) {
	   this.x = x;
   }

   public final int getY() {
	   return y;
   }

   public final void setY(int y) {
	   this.y = y;
   }

   protected String buttonName(int button) {

		switch (button) {

			case NativeMouseEvent.NOBUTTON:
				return "no button";

			case NativeMouseEvent.BUTTON1:
				return "left";

			case NativeMouseEvent.BUTTON2:
				return "right";

			case NativeMouseEvent.BUTTON3:
				return "middle";

			case NativeMouseEvent.BUTTON4:
				return "button 4";

			case NativeMouseEvent.BUTTON5:
				return "button 5";

			default:
				return String.valueOf(button);

		}

	}

	protected String posString(int x, int y) {

		StringBuilder pos = new StringBuilder();

		pos.append("(");
		pos.append(x);
		pos.append(", ");
		pos.append(y);
		pos.append(")");

		return pos.toString();

	}

}
