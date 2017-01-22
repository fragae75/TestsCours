package com.test.fenetres;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Locale;

import javax.swing.JPanel;


public class PanneauAnime extends JPanel {
  private int posX = -50;
  private int posY = -50;
  private int prevPosX = -50;
  private int prevPosY = -50;

  //Les variables d�finies auparavant ne changent pas
  //On y ajoute nos deux couleurs
  private Color couleurForme = Color.red;
  private Color couleurFond = Color.white;

  private int drawSize = 50;
  //Un bool�en pour le mode morphing 
  //Un autre pour savoir si la taille doit �tre r�duite
  public boolean morph = false, reduce = false;
 
  //Le compteur de rafra�chissements
  private int increment = 0;
  private String forme = TestFenetres.ROND;
  
  public void paintComponent(Graphics g){

    //On choisit une couleur de fond pour le rectangle
    g.setColor(couleurFond);
    //On le dessine de sorte qu'il occupe toute la surface
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
//    g.fillOval(prevPosX, prevPosY, 50, 50);
    //On red�finit une couleur pour le rond
    g.setColor(couleurForme);
    //On le dessine aux coordonn�es souhait�es
//    prevPosX=posX;
//    prevPosY=posY;
    if (!morph)
    	draw(g);
    else
    	drawMorph(g);
  }

  private void draw(Graphics g){

	  if (forme.toLowerCase().equals(TestFenetres.ROND.toLowerCase())){
		  g.fillOval(posX, posY, 50, 50);
	  }
		
	  if (forme.toLowerCase().equals(TestFenetres.CARRE.toLowerCase()) ||
		  forme.toLowerCase().equals(TestFenetres.CARR�.toLowerCase())){
	     g.fillRect(posX, posY, 50, 50);
	  }
	  
      if(this.forme.equals(TestFenetres.TRIANGLE.toLowerCase())){
        //Calcul des sommets            
        //Le sommet 1 se situe � la moiti� du c�t� sup�rieur du carr�
        int s1X = posX + 25;
        int s1Y = posY;
        //Le sommet 2 se situe en bas � droite
        int s2X = posX + 50;
        int s2Y = posY + 50;
        //Le sommet 3 se situe en bas � gauche
        int s3X = posX;
        int s3Y = posY + 50;      
        //Nous cr�ons deux tableaux de coordonn�es
        int[] ptsX = {s1X, s2X, s3X};
        int[] ptsY = {s1Y, s2Y, s3Y};      
        //Nous utilisons la m�thode fillPolygon()
        g.fillPolygon(ptsX, ptsY, 3);
      }
      if(this.forme.equals(TestFenetres.ETOILE.toLowerCase())){      
        //Pour l'�toile, on se contente de tracer des lignes dans le carr�
        //correspondant � peu pr�s � une �toile...
        //Mais ce code peut �tre am�lior� !
        int s1X = posX + 25;
        int s1Y = posY;
        int s2X = posX + 50;
        int s2Y = posY + 50;        
        g.drawLine(s1X, s1Y, s2X, s2Y);      
        int s3X = posX;
        int s3Y = posY + 17;
        g.drawLine(s2X, s2Y, s3X, s3Y);      
        int s4X = posX + 50;
        int s4Y = posY + 17;
        g.drawLine(s3X, s3Y, s4X, s4Y);            
        int s5X = posX;
        int s5Y = posY + 50;
        g.drawLine(s4X, s4Y, s5X, s5Y);       
        g.drawLine(s5X, s5Y, s1X, s1Y);  
      }
  }
  

  public int getPosX() {
    return posX;
  }


  public void setPosX(int posX) {
    this.posX = posX;
  }


  public int getPosY() {
    return posY;
  }


  public void setPosY(int posY) {
    this.posY = posY;
  }
  
  public void setForme(String form){
	  this.forme = form.toLowerCase();
  }

  
  //M�thode qui peint le morphing
  private void drawMorph(Graphics g){
    //On incr�mente
    increment++;
    //On regarde si on doit r�duire ou non
    if(drawSize >= 50) reduce = true;
    if(drawSize <= 10) reduce = false;    
    if(reduce)
      drawSize = drawSize - getUsedSize();
    else
      drawSize = drawSize + getUsedSize();
    
    if(this.forme.equals(TestFenetres.ROND.toLowerCase())){
      g.fillOval(posX, posY, drawSize, drawSize); 
    }
//    if(this.forme.equals(TestFenetres.CARRE.toLowerCase())){
    if (forme.toLowerCase().equals(TestFenetres.CARRE.toLowerCase()) ||
  	  forme.toLowerCase().equals(TestFenetres.CARR�.toLowerCase())){
      g.fillRect(posX, posY, drawSize, drawSize);
    }
    if(this.forme.equals(TestFenetres.TRIANGLE.toLowerCase())){        
      int s1X = posX + drawSize/2;
      int s1Y = posY;
      int s2X = posX + drawSize;
      int s2Y = posY + drawSize;
      int s3X = posX;
      int s3Y = posY + drawSize;      
      int[] ptsX = {s1X, s2X, s3X};
      int[] ptsY = {s1Y, s2Y, s3Y};      
      g.fillPolygon(ptsX, ptsY, 3);
    }
    if(this.forme.equals(TestFenetres.ETOILE.toLowerCase())){      
      int s1X = posX + drawSize/2;
      int s1Y = posY;
      int s2X = posX + drawSize;
      int s2Y = posY + drawSize;      
      g.drawLine(s1X, s1Y, s2X, s2Y);      
      int s3X = posX;
      int s3Y = posY + drawSize/3;
      g.drawLine(s2X, s2Y, s3X, s3Y);      
      int s4X = posX + drawSize;
      int s4Y = posY + drawSize/3;
      g.drawLine(s3X, s3Y, s4X, s4Y);                   
      int s5X = posX;
      int s5Y = posY + drawSize;
      g.drawLine(s4X, s4Y, s5X, s5Y);       
      g.drawLine(s5X, s5Y, s1X, s1Y);  
    }    
  }
  
  //Retourne le nombre � retrancher ou � ajouter pour le morphing
  private int getUsedSize(){
    int res = 0;
    //Si le nombre de tours est de dix, on r�initialise l'incr�ment et on retourne 1
    if(increment / 10 == 1){
      increment = 0;
      res = 1;
    }    
    return res;
  }
  
  public int getDrawSize(){
    return drawSize;
  }
  
  public boolean isMorph(){
    return morph;
  }
  
  public void setMorph(boolean bool){
    this.morph = bool;
    //On r�initialise la taille
    drawSize = 50;
  }

	//M�thode qui red�finit la couleur du fond
	public void setCouleurFond(Color color){
	  this.couleurFond = color;
	}
	
	//M�thode qui red�finit la couleur de la forme
	public void setCouleurForme(Color color){
	  this.couleurForme = color;
	}

}
