package Moteur.binaire.boxes;

import Moteur.binaire.MotBinaire;


/**
 * Interface des boxs (SBox et PBox)
 */
public interface IBox {
    public MotBinaire appliquer(MotBinaire entree);
}
