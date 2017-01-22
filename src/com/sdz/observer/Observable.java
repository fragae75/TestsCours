package com.sdz.observer;

/*
 * cf Interagir avec les boutons : rendre le traitement indépendant de la couche de présentation
 * updateObservateur() <=> update()
 */


public interface Observable {
  public void addObservateur(Observateur obs);
  public void updateObservateur();
  public void delObservateur();
}