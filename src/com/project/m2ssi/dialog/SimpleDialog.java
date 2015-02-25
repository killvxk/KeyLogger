
package com.project.m2ssi.dialog;

import java.awt.Frame;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import net.zeddev.litelogger.Logger;

public final class SimpleDialog {

	private static final Logger enregistreur = Logger.getLogger(SimpleDialog.class);

	private SimpleDialog() {
	}

	public static void message(Frame parent, String title, String msg) {
		JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.PLAIN_MESSAGE);
	}
	public static void warning(Frame parent, String msg) {
		JOptionPane.showMessageDialog(parent, msg, "WARNING", JOptionPane.WARNING_MESSAGE);
	}
	public static void error(Frame parent, String msg) {
		JOptionPane.showMessageDialog(parent, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	public static boolean okcancel(Frame parent, String title, String msg) {

		return JOptionPane.showConfirmDialog(
			parent, msg, title,
			JOptionPane.OK_CANCEL_OPTION,
			JOptionPane.PLAIN_MESSAGE
		) == JOptionPane.OK_OPTION;

	}
	public static boolean yesno(Frame parent, String title, String msg) {

		return JOptionPane.showConfirmDialog(
			parent, msg, title,
			JOptionPane.YES_NO_OPTION,
			JOptionPane.PLAIN_MESSAGE
		) == JOptionPane.YES_OPTION;

	}
	public static File saveFile(final Frame parent) {

		JFileChooser fileChooser = new JFileChooser();
		int ret = fileChooser.showSaveDialog(parent);
		if (ret == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();

		} else if (ret == JFileChooser.ERROR_OPTION) {
			enregistreur.warning("Une erreur est survenue avec sélecteur de fichier lors de l'enregistrement.");
			return null;
		} else {
			enregistreur.info("L'utilisateur a annulé fichier de sauvegarde.");
			return null;
		}

	}
}
