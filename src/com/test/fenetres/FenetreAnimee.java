package com.test.fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 

public class FenetreAnimee extends JFrame implements ActionListener {

  private PanneauAnime pan = new PanneauAnime();
  private BoutonGere boutonGo = new BoutonGere("Go");
  private BoutonGere boutonStop = new BoutonGere("Stop");
  private JPanel container = new JPanel();
  private JPanel containerBoutons = new JPanel();
  private JPanel containerLabels = new JPanel();
  
  private JLabel label1 = new JLabel();
  private JLabel label2 = new JLabel("Mon deuxième JLabel");
  
  //Compteur de clics
  private int compteur = 0;

  
  public FenetreAnimee(){        
    this.setTitle("Animation");
    this.setSize(300, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    label1.setText("Mon premier JLabel");

    //Définition d'une police d'écriture pour Label1
    Font police = new Font("Tahoma", Font.BOLD, 16);
    //On l'applique au JLabel
    label1.setFont(police);
    //Changement de la couleur du texte
    label1.setForeground(Color.blue);
    //On modifie l'alignement du texte grâce aux attributs statiques
    //de la classe JLabel
    label1.setHorizontalAlignment(JLabel.CENTER);
    label2.setHorizontalAlignment(JLabel.CENTER);
    
    //Nous ajoutons notre fenêtre à la liste des auditeurs de notre bouton
    // => actionPerformed() de la classe
    boutonGo.addActionListener(this);
    boutonStop.addActionListener(this);    
    //Ce sont maintenant nos classes internes qui écoutent nos boutons 
    // => actionPerformed() de la classe BoutonGere
    boutonGo.addActionListener(new BoutonListener());
//    boutonGo.setPreferredSize(new Dimension(this.getWidth()/2, boutonGo.getHeight()));
    boutonStop.addActionListener(new Bouton2Listener());    
    boutonStop.setPreferredSize(new Dimension(this.getWidth()/2, boutonStop.getHeight()));
    containerBoutons.setBackground(Color.white);
    containerBoutons.setLayout(new BorderLayout());
//    containerBoutons.add(boutonGo, BorderLayout.NORTH);
//    containerBoutons.add(boutonStop, BorderLayout.SOUTH);
    containerBoutons.add(boutonGo, BorderLayout.EAST);
    containerBoutons.add(boutonStop, BorderLayout.WEST);
    containerLabels.setBackground(Color.white);
    containerLabels.setLayout(new BorderLayout());
    containerLabels.add(label1, BorderLayout.NORTH);
    containerLabels.add(label2, BorderLayout.SOUTH);
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    container.add(containerLabels, BorderLayout.NORTH);
    container.add(pan, BorderLayout.CENTER);
    container.add(containerBoutons, BorderLayout.SOUTH);
    
    this.setContentPane(container);
    this.setVisible(true);    
    
    go();
  }

  //Classe écoutant notre premier bouton
  class BoutonListener implements ActionListener{
    //Redéfinition de la méthode actionPerformed()
    public void actionPerformed(ActionEvent arg0) {
      label2.setText("Vous avez cliqué sur le bouton 1");  
      repaint();
    }
  }
      
  //Classe écoutant notre second bouton
  class Bouton2Listener implements ActionListener{
    //Redéfinition de la méthode actionPerformed()
    public void actionPerformed(ActionEvent e) {
      label2.setText("Vous avez cliqué sur le bouton 2");    
      repaint();
    }
  }      
  
  //Méthode qui sera appelée lors d'un clic sur le bouton
  // Implémentation de l'interface ActionListener
  public void actionPerformed(ActionEvent arg0) {      
	    //Lorsque l'on clique sur le bouton, on met à jour le JLabel
    this.compteur++;
    label1.setText("Vous avez cliqué " + this.compteur + " fois");
    repaint();
/*
    if(arg0.getSource() == bouton)
    	label2.setText("Vous avez cliqué sur le bouton 1");

    if(arg0.getSource() == bouton2)
    	label2.setText("Vous avez cliqué sur le bouton 2");  
*/
    
} 

  private void go(){

	  //Les coordonnées de départ de notre rond
	  int x = pan.getPosX(), y = pan.getPosY();
	  //Le booléen pour savoir si l'on recule ou non sur l'axe x
	  boolean backX = false;
	  //Le booléen pour savoir si l'on recule ou non sur l'axe y
	  boolean backY = false;
	  //Dans cet exemple, j'utilise une boucle while
	  //Vous verrez qu'elle fonctionne très bien
	  while(true){
	    //Si la coordonnée x est inférieure à 1, on avance
	    if(x < 1)
	      backX = false;
	    //Si la coordonnée x est supérieure à la taille du Panneau moins la taille du rond, on recule
	    if(x > pan.getWidth()-50)
	      backX = true;

	    //Idem pour l'axe y
	    if(y < 1)
	      backY = false;
	    if(y > pan.getHeight()-50)
	      backY = true;

	    //Si on avance, on incrémente la coordonnée
	    //backX est un booléen, donc !backX revient à écrire

	    //if (backX == false)
	    if(!backX)
	      pan.setPosX(++x);
	    //Sinon, on décrémente
	    else
	      pan.setPosX(--x);

	    //Idem pour l'axe Y
	    if(!backY)
	      pan.setPosY(++y);
	    else
	      pan.setPosY(--y);

	    //On redessine notre Panneau
	    pan.repaint();

	    //Comme on dit : la pause s'impose ! Ici, trois millièmes de seconde
	    try {
	      Thread.sleep(3);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }
	}
	  
}