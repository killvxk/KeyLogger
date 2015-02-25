package com.project.m2ssi.keylogger;

public enum configuration {
	INSTANCE;

	public final String NAME = "KeyLogger : Mohammed El Mounjide";

	public final String FULL_NAME = NAME + " ";


	public static final String ICON_DIR = "/com/project/m2ssi/icones";

	public final String OS = System.getProperty("os.name").trim().toLowerCase();

	private configuration() {
	}
	
	public String userName() {
		return System.getProperty("user.name").toLowerCase();
	}

	
	public boolean isUnix() {
		return OS.matches(".*(nix|nux|aix)");
	}
	
	public boolean isWindows() {
		return OS.matches(".*win.*");
	}
	
	public boolean isOSX() {
		return OS.matches("os x");
	}
	
	public boolean isMac() {
		return OS.matches("mac");
	}
	
	public boolean isSolaris() {
		return OS.matches("solaris|sunos");
	}

}
