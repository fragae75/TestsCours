package com.test.fichiers;

import java.io.Serializable;

public class Game implements Serializable{
	private String nom, style;
	private double prix;
	//Maintenant, cette variable ne sera pas sérialisée
	//Elle sera tout bonnement ignorée !
	private transient Notice notice;
	// private Notice notice;

	public Game(String nom, String style, double prix) {
		this.nom = nom;
	    this.style = style;
	    this.prix = prix;
	    this.notice = new Notice();
	  }

	  public String toString(){
//	    return "Nom du jeu : " + this.nom + "\n style de jeu : " + this.style + "\n Prix du jeu : " + this.prix + "\n Notice : " + this.notice.toString();
	    return "Nom du jeu : " + this.nom + "\n style de jeu : " + this.style + "\n Prix du jeu : " + this.prix;
	  } 
}