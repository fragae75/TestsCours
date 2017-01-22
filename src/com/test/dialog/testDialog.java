package com.test.dialog;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class testDialog extends JFrame {

	  private JButton bouton = new JButton("Appel à la ZDialog");

	  public testDialog(){      
	    this.setTitle("Ma JFrame");
	    this.setSize(300, 100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);      
	    this.getContentPane().setLayout(new FlowLayout());
	    this.getContentPane().add(bouton);
	    bouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        ZDialog zd = new ZDialog(null, "Coucou les ZérOs", true);
	        ZDialogInfo zInfo = zd.showZDialog(); 
	        JOptionPane jop = new JOptionPane();
	        jop.showMessageDialog(null, zInfo.toString(), "Informations personnage", JOptionPane.INFORMATION_MESSAGE);
	      }         
	    });      
	    this.setVisible(true);      
	  }

	public static void main(String[] args) {
		  testDialog  fen = new testDialog();

	}
}


/*
	public class testDialog {
	  public static void main(String[] args) {      
	    JOptionPane jop1, jop2, jop3;      
	    jop1 = new JOptionPane();
	    ImageIcon img = new ImageIcon("images/information.jpg");
	    jop1.showMessageDialog(null, "Message informatif", "Information", JOptionPane.INFORMATION_MESSAGE, img);      
	    jop2 = new JOptionPane();
	    img = new ImageIcon("images/warning.jpg");
	    jop2.showMessageDialog(null, "Message préventif", "Attention", JOptionPane.WARNING_MESSAGE, img);      
	    jop3 = new JOptionPane();
	    img = new ImageIcon("images/erreur.jpg");
	    jop3.showMessageDialog(null, "Message d'erreur", "Erreur", JOptionPane.ERROR_MESSAGE, img);            
	    JOptionPane jop = new JOptionPane();      
	    int option = jop.showConfirmDialog(null, 
	        "Voulez-vous lancer l'animation ?", 
	        "Lancement de l'animation", 
	        JOptionPane.YES_NO_OPTION, 
	        JOptionPane.QUESTION_MESSAGE);
	    if(option == JOptionPane.NO_OPTION)
		    System.out.println("no option");
	    else if (option == JOptionPane.CANCEL_OPTION)
		    System.out.println("cancel option");
	    else if (option == JOptionPane.CLOSED_OPTION)
	    {
		    System.out.println("closed option");
		    
		    String[] sexe = {"masculin", "féminin", "indéterminé"};
		    JOptionPane jop6 = new JOptionPane(), jop7 = new JOptionPane();
		    String nom = (String)jop6.showInputDialog(null, 
		      "Veuillez indiquer votre sexe !",
		      "Gendarmerie nationale !",
		      JOptionPane.QUESTION_MESSAGE,
		      null,
		      sexe,
		      sexe[2]);
		    jop7.showMessageDialog(null, "Votre sexe est " + nom, "Etat civil", JOptionPane.INFORMATION_MESSAGE);
	    }
	    else
	    {
		    System.out.println("yes option");

		    JOptionPane jop4 = new JOptionPane(), jop5 = new JOptionPane();
		    String nom = jop4.showInputDialog(null, "Veuillez décliner votre identité !", "Gendarmerie nationale !", JOptionPane.QUESTION_MESSAGE);
		    jop5.showMessageDialog(null, "Votre nom est " + nom, "Identité", JOptionPane.INFORMATION_MESSAGE);
	    }
	  }
	}
	
	
*/
