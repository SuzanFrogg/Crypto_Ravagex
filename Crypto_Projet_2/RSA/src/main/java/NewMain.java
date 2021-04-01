package main.java;


import challenges.Challenge;
import challenges.GenererClePublique;

import exceptions.ExceptionCryptographie;
import protocoles.Protocole;
import protocoles.ProtocoleRSA;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matthieu
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws exceptions.ExceptionCryptographie
     */
    public static void main(String[] args) throws ExceptionCryptographie {
        Protocole rsa = new ProtocoleRSA();
        rsa.executer();
    /*  import donnees.NombreBinaire; 
        NombreBinaire x = new NombreBinaire("0011111100011000");
        int s = 0;
        for(int i = x.getTaille(); i >0;i--) {
            System.out.println("i : "+i+" - "+x.get(i));
            if(x.get(i)) {
                s++;
            }
            else {
                System.out.println("out");
                break;
            }
        }  
        System.out.println("s : " +s);
        NombreBinaire d = new NombreBinaire();
        for(int j =0; j<x.getTaille()-s;j++) {
            d.set(j, x.get(j));
        }
        System.out.println(d);
*/
    }
    
}
