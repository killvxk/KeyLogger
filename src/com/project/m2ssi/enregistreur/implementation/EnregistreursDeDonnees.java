package com.project.m2ssi.enregistreur.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.m2ssi.enregistreur.EnregistreurDeDonnees;
public final class EnregistreursDeDonnees {
	private static final Map<String, EnregistreurDeDonnees> EnregistreurDeDonnees = new HashMap<>();
	private static final List<String> TYPES = new ArrayList<>();

	static {
		final EnregistreurDeDonnees[] instances = {
			new CharTypedEnregistreur(),
			new EnregistreurKeyPressed(),
			new EnregistreurKeyReleased(),
			new SourisCliqueEvenements(),
			new SourisPressedEvenements(),
			new SourisReleasedEvenements(),
			new SourisMovementEvenements()
		};

		for (EnregistreurDeDonnees loggerInstance : instances) {
			EnregistreurDeDonnees.put(loggerInstance.type(), loggerInstance);
			TYPES.add(loggerInstance.type());
		}

	}

	private EnregistreursDeDonnees() {
	}

	public static EnregistreurDeDonnees newDataLogger(String type) {

		assert(type != null);

		if (EnregistreurDeDonnees.containsKey(type)) {
			return EnregistreurDeDonnees.get(type);
		} else {
			return null;
		}

	}

	public static List<String> typeList() {
		return new ArrayList<>(TYPES);
	}

}
