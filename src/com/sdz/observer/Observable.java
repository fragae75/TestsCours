package com.sdz.observer;

/*
 * cf Interagir avec les boutons : rendre le traitement ind�pendant de la couche de pr�sentation
 * updateObservateur() <=> update()
 */


public interface Observable {
  public void addObservateur(Observateur obs);
  public void updateObservateur();
  public void delObservateur();
}