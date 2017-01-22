package com.test.fenetres;

import javax.swing.JFrame;
import java.awt.Color; 
import javax.swing.JPanel;

public class Fenetre extends JFrame {

	public Fenetre() {
	    this.setTitle("Ma première fenêtre Java");
	    this.setSize(400, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	
	    //Instanciation d'un objet JPanel
//	    JPanel pan = new JPanel();
	    //Définition de sa couleur de fond
//	    pan.setBackground(Color.ORANGE);        
	    //On prévient notre JFrame que notre JPanel sera son content pane
//	    this.setContentPane(pan);               

	    this.setContentPane(new Panneau());

	    this.setVisible(true);	    

	}

}
