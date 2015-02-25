package com.project.m2ssi;

import static com.project.m2ssi.util.Assertions.requireNotEquals;
import static com.project.m2ssi.util.Assertions.requireNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import net.zeddev.litelogger.Logger;

import com.project.m2ssi.keylogger.configuration;

public enum Icones {

	INSTANCE;

	public static final String EXT = ".png";

	private final Logger enregistreur = Logger.getLogger(this);

	private Map<String, Icon> icones = new HashMap<>();

	public static Icones getInstance() {
		return INSTANCE;
	}

	private Icon chargementIcone(String icone) {

		String resource = configuration.ICON_DIR + "/" + icone + EXT;

		return new ImageIcon(getClass().getResource(resource));

	}

	public Icon getIcon(final String icone) {
		
		requireNotNull(icone != null);
		requireNotEquals(icone, "");

		if (!icones.containsKey(icone))
			icones.put(icone, chargementIcone(icone));

		return icones.get(icone);

	}

}
