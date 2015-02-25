package com.project.m2ssi.enregistreur;
import static com.project.m2ssi.util.Assertions.requireNotNull;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import net.zeddev.litelogger.Logger;

public final class EnregistreurDeDonneesNotificationThread extends Thread {
	public static final int QUEUE_SIZE = 25;
	public static final int WAIT_CYCLE = 1000 / (QUEUE_SIZE * 4);
	private final Logger enregistreur = Logger.getLogger(this);
	private EnregistreurDeDonnees enregistreurDeDonnees;
	private final List<EnregistreurDeDonneesObservateur> observateurs;
	private final Queue<EntreeDuJournal> logSpool = new ArrayBlockingQueue<>(QUEUE_SIZE);
	private boolean fonctionnement = false;
	public EnregistreurDeDonneesNotificationThread(EnregistreurDeDonnees enregistreurDeDonnees, List<EnregistreurDeDonneesObservateur> observateurs) {

		super();

		requireNotNull(enregistreurDeDonnees);
		requireNotNull(observateurs);
		setName(String.format("%s notification thread", enregistreurDeDonnees.type()));
		setPriority(Thread.MIN_PRIORITY);
		setDaemon(true);

		this.observateurs = observateurs;
		this.enregistreurDeDonnees = enregistreurDeDonnees;

	}
	public void notifyEvent(EntreeDuJournal logEntry) {

		requireNotNull(logEntry);
		
		synchronized (logSpool) {
			logSpool.offer(logEntry);
		}

	}
	public void fermer() {
		fonctionnement = false;
	}
	private void notifyobservateurs(EntreeDuJournal logEntry) {

		requireNotNull(logEntry);
		
		synchronized (observateurs) {

			for (EnregistreurDeDonneesObservateur observer : observateurs)
				observer.informerJournal(enregistreurDeDonnees, logEntry);

		}

	}

	@Override
	public void run() {

		fonctionnement = true;

		while (fonctionnement) {

			synchronized(logSpool) {
				if (!logSpool.isEmpty())
					notifyobservateurs(logSpool.remove());

			}

			try {
				Thread.sleep(WAIT_CYCLE);
			} catch (InterruptedException ex) {	}

		}

	}

}
