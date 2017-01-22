package com.test.fenetres;

/*
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetreAvecBoutons extends JFrame {

  private JPanel pan = new JPanel();
  private JButton bouton = new JButton("Mon bouton");

  public FenetreAvecBoutons(){
    this.setTitle("Animation");
    this.setSize(300, 150);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    //Ajout du bouton à notre content pane
    pan.add(bouton);
    this.setContentPane(pan);
    this.setVisible(true);
  }       
}
*/

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetreAvecBoutonsCardLayout extends JFrame{

  CardLayout clCardLayout = new CardLayout();
  JPanel contentJPanel = new JPanel();
  //Liste des noms de nos conteneurs pour la pile de cartes
  String[] listContentCardName = {"CARD_1", "CARD_2", "CARD_3"};
  int indice = 0;

  public FenetreAvecBoutonsCardLayout(){
    this.setTitle("CardLayout");
    this.setSize(300, 120);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
		
    //On crée trois conteneurs de couleur différente
    JPanel card1 = new JPanel();
    card1.setBackground(Color.blue);		
    JPanel card2 = new JPanel();
    card2.setBackground(Color.red);		
    JPanel card3 = new JPanel();
    card3.setBackground(Color.green);

    JPanel boutonPane = new JPanel();
    JButton bouton = new JButton("Contenu suivant");
    //Définition de l'action du bouton
    bouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        //Via cette instruction, on passe au prochain conteneur de la pile
        clCardLayout.next(contentJPanel);
      }
    });
		
    JButton bouton2 = new JButton("Contenu par indice");
    //Définition de l'action du bouton2
    bouton2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){				
        if(++indice > 2)
          indice = 0;
        //Via cette instruction, on passe au conteneur correspondant au nom fourni en paramètre
        clCardLayout.show(contentJPanel, listContentCardName[indice]);
      }
    });
		
    boutonPane.add(bouton);
    boutonPane.add(bouton2);
    //On définit le layout
    contentJPanel.setLayout(clCardLayout);
    //On ajoute les cartes à la pile avec un nom pour les retrouver
    contentJPanel.add(card1, listContentCardName[0]);
    contentJPanel.add(card2, listContentCardName[1]);
    contentJPanel.add(card3, listContentCardName[2]);

    this.getContentPane().add(boutonPane, BorderLayout.NORTH);
    this.getContentPane().add(contentJPanel, BorderLayout.CENTER);
    this.setVisible(true);
  }	
}