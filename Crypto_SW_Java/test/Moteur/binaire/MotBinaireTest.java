/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moteur.binaire;

import java.util.ArrayList;
import java.util.BitSet;
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
public class MotBinaireTest {
    
    public MotBinaireTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getBitSet method, of class MotBinaire.
     */
    @Test
    public void testGetBitSet() {
        System.out.println("getBitSet");
        MotBinaire instance = new MotBinaire("011011");
        BitSet expResult = new BitSet();
        expResult.set(0,5,true);
        expResult.set(2,false);
        BitSet result = instance.getBitSet();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTaille method, of class MotBinaire.
     */
    @Test
    public void testGetTaille() {
        System.out.println("getTaille");
        MotBinaire instance = new MotBinaire("01000001");
        int expResult = 8;
        int result = instance.getTaille();
        assertEquals(expResult, result);

    }

    /**
     * Test of asInteger method, of class MotBinaire.
     */
    @Test
    public void testAsInteger() {
        System.out.println("asInteger");
        MotBinaire instance = new MotBinaire("01000001");
        int expResult = 65;
        int result = instance.asInteger();
        assertEquals(expResult, result);

    }

    /**
     * Test of asString method, of class MotBinaire.
     */
    @Test
    public void testAsString() {
        System.out.println("asString");
        MotBinaire instance = new MotBinaire("01000010011011110110111001101010011011110111010101110010");//
        String expResult = "Bonjour";
        assertEquals(expResult,instance.asString());        
    }

    /**
     * Test of toString method, of class MotBinaire.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MotBinaire instance = new MotBinaire("110");
        String expResult = "110";
        String result = instance.toString();
        assertEquals(expResult, result);
    }


    /**
     * Test of xor method, of class MotBinaire.
     */
    @Test
    public void testXor() {
        System.out.println("xor");
        MotBinaire mot1 = new MotBinaire("11101110");
        MotBinaire mot2 = new MotBinaire("01100101");
        MotBinaire expResult = new MotBinaire("10001011");
        MotBinaire result = mot1.xor(mot2);
        assertEquals(expResult.toString(), result.toString());

    }


    /**
     * Test of additionMod2p32 method, of class MotBinaire.
     */
    @Test
    public void testAdditionMod2p32() {
        MotBinaire mot1 = new MotBinaire(5);
        MotBinaire mot2 = new MotBinaire(36);
        
        MotBinaire mot3 = mot1.additionMod2p32(mot2);
        assertEquals(mot3.asInteger(), 41);
        
        
        MotBinaire mot4 = new MotBinaire(255723774);
        MotBinaire mot5 = new MotBinaire(1448746371);
        
        MotBinaire mot6 = mot4.additionMod2p32(mot5);
        assertEquals(mot6.asInteger(), 1704470145);

    }

    /**
     * Test of scinder method, of class MotBinaire.
     */
    @Test
    public void testScinder() {
        System.out.println("Scinder");
        MotBinaire mot = new MotBinaire("101101");
        int tailleMorceau = 2;
        MotBinaire expResult0 = new MotBinaire("01");
        MotBinaire expResult1 = new MotBinaire("11");
        MotBinaire expResult2 = new MotBinaire("10");
        ArrayList<MotBinaire> results = mot.scinder(tailleMorceau);
        
        System.out.println("Scinder - test des Integer");
        assertEquals(expResult0.asInteger(), results.get(0).asInteger());
        assertEquals(expResult1.asInteger(), results.get(1).asInteger());
        assertEquals(expResult2.asInteger(), results.get(2).asInteger());
        
        System.out.println("Scinder - test des BitSet");
        assertEquals(expResult0.getBitSet(),results.get(0).getBitSet());
        assertEquals(expResult1.getBitSet(),results.get(1).getBitSet());
        assertEquals(expResult2.getBitSet(),results.get(2).getBitSet());
    }

    /**
     * Test of concatenation method, of class MotBinaire.
     */
    @Test
    public void testConcatenation() {
        System.out.println("concatenation");
        MotBinaire motA = new MotBinaire("01000001");
        MotBinaire motE = new MotBinaire("10100010");
        MotBinaire expResult = new MotBinaire("0100000110100010");
        MotBinaire result = motA.concatenation(motE);
        assertEquals(expResult.toString(), result.toString());
    }

}
