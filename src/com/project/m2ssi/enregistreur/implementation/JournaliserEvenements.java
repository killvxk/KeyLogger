package com.project.m2ssi.enregistreur.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.zeddev.litelogger.Logger;

import com.project.m2ssi.enregistreur.JournaliserEvenement;
import com.project.m2ssi.enregistreur.implementation.evenement.EvenementCle;
import com.project.m2ssi.enregistreur.implementation.evenement.SourisCliqueEvenement;
import com.project.m2ssi.enregistreur.implementation.evenement.SourisMovedEvenement;
import com.project.m2ssi.enregistreur.implementation.evenement.SourisPressedEvenement;
import com.project.m2ssi.enregistreur.implementation.evenement.SourisReleasedEvenement;

public class JournaliserEvenements {

	private static final Logger enregistreur = Logger.getLogger(JournaliserEvenements.class);
	private static final List<String> TYPES = new ArrayList<>();
	private static final Map<String, Class> CLASSES = new HashMap<>();

	static {
		final JournaliserEvenement[] instances = {
			new EvenementCle(),
			new SourisCliqueEvenement(),
			new SourisMovedEvenement(),
			new SourisPressedEvenement(),
			new SourisReleasedEvenement()
		};
		for (JournaliserEvenement eventInstance : instances) {
			CLASSES.put(eventInstance.type(), eventInstance.getClass());
			TYPES.add(eventInstance.type());
		}

	}

	private JournaliserEvenements() {
	}
	public static JournaliserEvenement newLogEvent(String type) {

		assert(type != null);

		if (CLASSES.containsKey(type)) {

			try {
				return (JournaliserEvenement) CLASSES.get(type).newInstance();
			} catch (InstantiationException | IllegalAccessException ex) {
				enregistreur.warning("Impossible d'instancier logentry instance de type %s.", ex, type);
				return null;
			}

		} else {
			return null;
		}

	}

	public static List<String> typeList() {
		return new ArrayList<>(TYPES);
	}

}
