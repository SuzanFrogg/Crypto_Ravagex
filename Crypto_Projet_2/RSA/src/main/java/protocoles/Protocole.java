/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocoles;

import exceptions.ExceptionCryptographie;

/**
 *
 * @author simonetma
 */
public interface Protocole {

    /**
     * Exercute le protocole
     * @throws ExceptionCryptographie si une erreur a eu lieu durant le protocole
     */
    public void executer() throws ExceptionCryptographie;
}
