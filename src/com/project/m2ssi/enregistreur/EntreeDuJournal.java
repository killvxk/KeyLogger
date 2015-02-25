package com.project.m2ssi.enregistreur;

import static com.project.m2ssi.util.Assertions.ensure;
import static com.project.m2ssi.util.Assertions.ensureNotEquals;
import static com.project.m2ssi.util.Assertions.ensureNotNull;
import static com.project.m2ssi.util.Assertions.require;
import static com.project.m2ssi.util.Assertions.requireNotEquals;
import static com.project.m2ssi.util.Assertions.requireNotNull;
import net.zeddev.litelogger.Logger;

public class EntreeDuJournal {
	
	private static final Logger enregistreur = Logger.getLogger(EntreeDuJournal.class);

	private EnregistreurDeDonnees parent = null;
	private String message = null;
	private JournaliserEvenement evenement = null;
	
	private long horodatage = System.currentTimeMillis();
	public EntreeDuJournal(final EnregistreurDeDonnees parent, final String message, final JournaliserEvenement evenement) {
		requireNotNull(parent);
		requireNotNull(message);
		requireNotEquals(message, "");
		requireNotNull(evenement);
		
		this.parent = parent;
		this.message = message;
		this.evenement = evenement;
		
	}
	public EntreeDuJournal() {
	}

	public EnregistreurDeDonnees getParent() {
		return parent;
	}

	public void setParent(EnregistreurDeDonnees parent) {
		
		requireNotNull(parent);
		
		this.parent = parent;
		
	}

	public String getMessage() {
		
		ensureNotNull(message);
		ensureNotEquals(message, "");
		
		return message;
		
	}

	public void setMessage(String message) {
		
		requireNotNull(message);
		requireNotEquals(message, "");
		
		this.message = message;
		
	}

	public JournaliserEvenement getevenement() {
		
		ensureNotNull(evenement);
		
		return evenement;
		
	}

	public void setevenement(JournaliserEvenement evenement) {
		
		requireNotNull(evenement);
		
		this.evenement = evenement;
		
	}

	public long gethorodatage() {
		
		ensure(horodatage >= 0);
		
		return horodatage;
		
	}

	public void sethorodatage(long horodatage) {
		
		require(horodatage >= 0);
		
		this.horodatage = horodatage;
		
	}
	
	@Override
	public String toString() {
		return getMessage();
	} 

}