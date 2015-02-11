package piwords;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlphabetGeneratorTest {
    @Test
    public void generateFrequencyAlphabetTest1() {

        // Expect in the training data that Pr(a) = 2/5, Pr(b) = 2/5,
        // Pr(c) = 1/5.
        String[] trainingData = {"aa", "bbc"};
        // In the output for base 10, they should be in the same proportion.
        char[] expectedOutput = {'a', 'a', 'a', 'a',
                                 'b', 'b', 'b', 'b',
                                 'c', 'c'};

        assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        10, trainingData));
    }


    // example from description
    @Test
    public void generateFrequencyAlphabetTest2() {

        AlphabetGenerator.clear();

        String[] trainingData = new String[3];
        String s = "";
        for(int i=0; i<302; i++) { s += "a"; } trainingData[0] = s; s = "";
        for(int i=302; i<802; i++) { s += "b"; } trainingData[1] = s; s = "";
        for(int i=802; i<1000; i++) { s += "c"; } trainingData[2] = s;

        char[] expectedOutput = new char[93];
        for(int i=0; i<28; i++) { expectedOutput[i] = 'a'; }
        for(int i=28; i<75; i++) { expectedOutput[i] = 'b'; }
        for(int i=75; i<93; i++) { expectedOutput[i] = 'c'; }


        assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        93, trainingData));
    }

    @Test
    public void generateFrequencyAlphabetTest3() {

        AlphabetGenerator.clear();

        String[] trainingData = {"a single string"};
        char[] expectedOutput = {'a','a','a','a','a',
                'e','e','e','e','e',
                'g','g','g','g','g','g','g','g','g','g',
                'i','i','i','i','i','i','i','i','i','i',
                'l','l','l','l',
                'n','n','n','n','n','n','n','n','n','n',
                'r','r','r','r','r',
                's','s','s','s','s','s','s','s','s','s',
                't','t','t','t','t'};

        // base = 64
        assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        64, trainingData));
    }

    @Test
    public void generateFrequencyAlphabetTest4() {

        AlphabetGenerator.clear();

        // Expect in the training data that Pr(a) = 1/5, Pr(b) = 1/5,
        // Pr(c) = 1/5, Pr(d) = 1/5, Pr(e) = 1/5.
        String[] trainingData = {"1+1=2","aabb","ccdd","e@@e"};

    	/*
    	 * expectedOutput = {"a", "a", ... (13 As, indexes 0-12),
    	 * 					 "b", "b", ... (13 Bs, indexes 13-25),
    	 * 					 "c", "c", ... (12 Cs, indexes 26-37),
    	 * 					 "d", "d", ... (13 Ds, indexes 38-50),
    	 * 					 "e", "e", ... (13 Bs, indexes 51-63)}
    	 * */

        char[] expectedOutput = {'a','a','a','a','a','a','a','a','a','a','a','a','a',
                'b','b','b','b','b','b','b','b','b','b','b','b','b',
                'c','c','c','c','c','c','c','c','c','c','c','c',
                'd','d','d','d','d','d','d','d','d','d','d','d','d',
                'e','e','e','e','e','e','e','e','e','e','e','e','e'};
        // base = 64
        assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        64, trainingData));
    }



}
