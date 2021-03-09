/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Moteur.binaire.MotBinaire;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mathy
 */
public class PermutationTest {
    
    public PermutationTest() {
    }
    
   
    /**
     * Test of permuterMot8 method, of class Permutation.
     */
    @Test
    public void testPermuterMot8() {
        System.out.println("permuterMot8");
        MotBinaire initial = new MotBinaire("10110101");
        Permutation instance = new Permutation();
        MotBinaire expResult = new MotBinaire("01011011");
        MotBinaire result = instance.permuterMot8(initial);
        assertEquals(expResult.toString(), result.toString());

    }
    
}
