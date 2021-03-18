package main.java;


import challenges.Challenge;
import challenges.EstInferieur;
import exceptions.ExceptionCryptographie;

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
        Challenge challenge = new EstInferieur();
    }
    
}
