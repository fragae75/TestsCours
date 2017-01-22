package com.test.fenetres;

import java.io.*;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D; 
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

 

public class Panneau extends JPanel { 

  public void paintComponent(Graphics g){

    //Vous verrez cette phrase chaque fois que la méthode sera invoquée
//    System.out.println("Je suis exécutée !"); 

    int x1 = this.getWidth()/4;
    int y1 = this.getHeight()/4;                      
    g.fillOval(x1, y1, this.getWidth()/2, this.getHeight()/2);

    g.drawOval(2*x1, 2*y1, this.getWidth()/2, this.getHeight()/2);
    g.drawRect(10, 10, 50, 60);
    g.fillRect(65, 65, 30, 40);
    g.drawLine(0, 0, this.getWidth(), this.getHeight());
    g.drawLine(0, this.getHeight(), this.getWidth(), 0);             
  	
    Font font = new Font("Courier", Font.BOLD, 20);
    g.setFont(font);
    g.setColor(Color.red);        
    g.drawString("Tiens ! Le Site du Zéro !", 10, 20);

    
    try {

    	File fichierImage = new File("D:\\image.jpg");
    	Image img = ImageIO.read(fichierImage);

//        g.drawImage(img, 0, 0, this);

        //Pour une image de fond
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

      } catch (IOException e) {

        e.printStackTrace();

      }                
    
    Graphics2D g2d = (Graphics2D)g;
    GradientPaint gp, gp2, gp3, gp4, gp5, gp6; 

    gp = new GradientPaint(0, 0, Color.RED, 20, 0, Color.magenta, true);
    gp2 = new GradientPaint(20, 0, Color.magenta, 40, 0, Color.blue, true);
    gp3 = new GradientPaint(40, 0, Color.blue, 60, 0, Color.green, true);
    gp4 = new GradientPaint(60, 0, Color.green, 80, 0, Color.yellow, true);
    gp5 = new GradientPaint(80, 0, Color.yellow, 100, 0, Color.orange, true);
    gp6 = new GradientPaint(100, 0, Color.orange, 120, 0, Color.red, true);

    g2d.setPaint(gp);
    g2d.fillRect(0, 0, 20, this.getHeight());               
    g2d.setPaint(gp2);
    g2d.fillRect(20, 0, 20, this.getHeight());
    g2d.setPaint(gp3);
    g2d.fillRect(40, 0, 20, this.getHeight());
    g2d.setPaint(gp4);
    g2d.fillRect(60, 0, 20, this.getHeight());
    g2d.setPaint(gp5);
    g2d.fillRect(80, 0, 20, this.getHeight());
    g2d.setPaint(gp6);
    g2d.fillRect(100, 0, 40, this.getHeight());
   
  }
}