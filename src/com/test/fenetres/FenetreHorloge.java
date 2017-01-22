package com.test.fenetres;


import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sdz.model.Horloge;
import com.sdz.observer.Observateur;

public class FenetreHorloge extends JFrame {
  private JLabel label = new JLabel();
  private Horloge horloge;
	
  public FenetreHorloge(){
    //On initialise la JFrame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setSize(200, 80);
		
    //On initialise l'horloge
    this.horloge = new Horloge();
    //On place un �couteur sur l'horloge
    this.horloge.addObservateur(new Observateur(){
      public void update(String hour) {
        label.setText(hour);
      }
    });
		
    //On initialise le JLabel
    Font police = new Font("DS-digital", Font.TYPE1_FONT, 30);
    this.label.setFont(police);
    this.label.setHorizontalAlignment(JLabel.CENTER);
    //On ajoute le JLabel � la JFrame
    this.getContentPane().add(this.label, BorderLayout.CENTER);		
    this.setVisible(true);
    this.horloge.run();
  }

  //M�thode main() lan�ant le programme
  public static void main(String[] args){
    Fenetre fen = new Fenetre();
  }
}