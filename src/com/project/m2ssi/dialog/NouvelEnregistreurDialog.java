package com.project.m2ssi.dialog;

import java.awt.Component;
import java.awt.Frame;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;

import com.project.m2ssi.enregistreur.EnregistreurDeDonnees;
import com.project.m2ssi.enregistreur.implementation.EnregistreursDeDonnees;

public final class NouvelEnregistreurDialog extends JDialog {

	private boolean cancelledFlag = false;

	public static int CharTyped = 0;
	public static int KeyPressed = 0;
	public static int KeyReleased = 0;
	
	
	public static int MouseMovement = 0;
	public static int MouseClick = 0;
	public static int MousePressed = 0;
	public static int MouseReleased = 0;
	
	
	private final static int SPACING = 10;
	
	private final JButton btnNon = new JButton("Non");
	private final JButton btnOui = new JButton("Oui");
	private final JComboBox cboType = new JComboBox();
	private final JLabel TypeEnregistreur = new JLabel();
	
	public NouvelEnregistreurDialog(Frame parent, boolean modal) {

		super(parent, modal);

		initialisation();
		construireForme();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		setTitle("ajouter enregistreur");
		setName("");
		
		setLocationRelativeTo(null);

	}
	
	private void initialisation() {
		
		TypeEnregistreur.setText("Type d'enregistreur");
		
		initTypes();
		
		btnNon.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNonActionPerformed(evt);
			}
		});
		
		btnOui.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				

				if(cboType.getSelectedItem().toString().equals("CharTyped"))
					CharTyped=1;

				if(cboType.getSelectedItem().toString().equals("KeyPressed"))
					KeyPressed=1;

				if(cboType.getSelectedItem().toString().equals("KeyReleased"))
					KeyReleased=1;
				
				if(cboType.getSelectedItem().toString().equals("MouseMovement"))
					MouseMovement=1;
				if(cboType.getSelectedItem().toString().equals("MouseClick"))
						MouseClick=1;
				if(cboType.getSelectedItem().toString().equals("MousePressed"))
					MousePressed=1;
				if(cboType.getSelectedItem().toString().equals("MouseReleased"))
					MouseReleased=1;
				btnOuiActionPerformed(evt);
			}
		});
		
	}

	private void initTypes() {

		cboType.addItem("-- Sélectionnez le type d'enregistreur --");

		for (String type : EnregistreursDeDonnees.typeList())
			cboType.addItem(type);

		cboType.setSelectedIndex(0);

	}
	
	private void ajouterLigne(Box box, JComponent comp) {
		
		box.add(comp);
		comp.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		box.add(Box.createVerticalStrut(SPACING));
		
	}
	
	private Box buttonBox() {
		
		Box buttons = new Box(BoxLayout.LINE_AXIS);
		
		buttons.add(btnOui);
		buttons.add(Box.createHorizontalStrut(SPACING));
		buttons.add(btnNon);
		
		return buttons;
		
	}
	
	private void construireForme() {
		
		Box outer = new Box(BoxLayout.LINE_AXIS);
		outer.add(Box.createHorizontalStrut(SPACING));
		
			Box inner = new Box(BoxLayout.PAGE_AXIS);
			inner.add(Box.createVerticalStrut(SPACING));
			
			ajouterLigne(inner, TypeEnregistreur);
			ajouterLigne(inner, cboType);
			ajouterLigne(inner, buttonBox());
			
			outer.add(inner);
		
		outer.add(Box.createHorizontalStrut(SPACING));
		add(outer);
		
		pack();
		
	}

	public EnregistreurDeDonnees getFormeEnregistreur() {

		setVisible(true);

		if (cancelledFlag) {
			return null;
		} else {
			return EnregistreursDeDonnees.newDataLogger(cboType.getSelectedItem().toString());
		}

	}

	private void btnOuiActionPerformed(java.awt.event.ActionEvent evt) {
		setVisible(false);
	}

	private void btnNonActionPerformed(java.awt.event.ActionEvent evt) {
		
		setVisible(false);
		dispose();
		
		cancelledFlag = true;

	}
}
