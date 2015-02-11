package piwords;

import java.util.*;

public class AlphabetGenerator {
    /**
     * Given a numeric base, return a char[] that maps every digit that is
     * representable in that base to a lower-case char.
     * 
     * This method will try to weight each character of the alphabet
     * proportional to their occurrence in words in a training set.
     * 
     * This method should do the following to generate an alphabet:
     *   1. Count the occurrence of each character a-z in trainingData.
     *   2. Compute the probability of each character a-z by taking
     *      (occurrence / total_num_characters).
     *   3. The output generated in step (2) is a PDF of the characters in the
     *      training set. Convert this PDF into a CDF for each character.
     *   4. Multiply the CDF value of each character by the base we are
     *      converting into.
     *   5. For each index 0 <= i < base,
     *      output[i] = (the first character whose CDF * base is > i)
     * 
     * A concrete example:
     * 	 0. Input = {"aaaaa..." (302 "a"s), "bbbbb..." (500 "b"s),
     *               "ccccc..." (198 "c"s)}, base = 93
     *   1. Count(a) = 302, Count(b) = 500, Count(c) = 193
     *   2. Pr(a) = 302 / 1000 = .302, Pr(b) = 500 / 1000 = .5,
     *      Pr(c) = 198 / 1000 = .198
     *   3. CDF(a) = .302, CDF(b) = .802, CDF(c) = 1
     *   4. CDF(a) * base = 28.086, CDF(b) * base = 74.586, CDF(c) * base = 93
     *   5. Output = {"a", "a", ... (28 As, indexes 0-27),
     *                "b", "b", ... (47 Bs, indexes 28-74),
     *                "c", "c", ... (18 Cs, indexes 75-92)}
     * 
     * The letters should occur in lexicographically ascending order in the
     * returned array.
     *   - {"a", "b", "c", "c", "d"} is a valid output.
     *   - {"b", "c", "c", "d", "a"} is not.
     *   
     * If base >= 0, the returned array should have length equal to the size of
     * the base.
     * 
     * If base < 0, return null.
     * 
     * If a String of trainingData has any characters outside the range a-z,
     * ignore those characters and continue.
     * 
     * @param base A numeric base to get an alphabet for.
     * @param trainingData The training data from which to generate frequency
     *                     counts. This array is not mutated.
     * @return A char[] that maps every digit of the base to a char that the
     *         digit should be translated into.
     */

    private static Map<Character, Integer> frequency = new HashMap<>();
    private static Map<Character, Double> pdf = new HashMap<>();
    private static Map<Character, Double> cdf = new HashMap<>();
    public static List<Character> alphabet = new ArrayList<>();
    private static Map<Character, Integer> limits = new HashMap<>();
    private static int letterCount;

    public static char[] generateFrequencyAlphabet(int base,
                                                   String[] trainingData) {
        countLetters(trainingData);
        pdfCalc();
        cdfCalc();

        return generateOutput(base);
    }

    public static void clear() {
        frequency = new HashMap<>();
        pdf = new HashMap<>();
        cdf = new HashMap<>();
        alphabet = new ArrayList<>();
        limits = new HashMap<>();
        letterCount = 0;
    }

    private static char[] generateOutput(int base) {

        limitsCals(base);

        char[] output = new char[base];
        int low = 0;
        int high;
        for(Character letter: alphabet) {

            high = limits.get(letter);
            for (int i=low; i<high; i++)
                output[i] = letter;

            low = high;
        }
        return output;
    }

    private static void limitsCals(int base) {

        for (Character letter: cdf.keySet())
            limits.put(letter, (int) Math.round(cdf.get(letter) * base));
    }

    // calculate pdf
    private static void pdfCalc() {

        for(Character letter: alphabet) {
            pdf.put(letter, (double) frequency.get(letter) / letterCount);
        }
    }

    // calculate cdf
    private static void cdfCalc() {

        double counter = 0;
        for (Character letter: alphabet) {
            counter += pdf.get(letter);
            cdf.put(letter, counter);
        }
    }

    // count letters in trainingData
    private static void countLetters(String[] trainingData) {

        for (String word: trainingData)
            countLettersInWord(word);

        // fill in alphabet
        alphabet.addAll(frequency.keySet());
        Collections.sort(alphabet);
    }

    private static void countLettersInWord(String word) {

        for (Character letter: word.toCharArray()) {

            if (Character.isLowerCase(letter)) {
                if (frequency.containsKey(letter))
                    frequency.put(letter, frequency.get(letter) + 1);
                else frequency.put(letter, 1);
                letterCount++;
            }

        }
    }

    public static void main(String[] args) {

        String[] trainingData = new String[3];
        String s = "";
        for(int i=0; i<302; i++) { s += "a"; } trainingData[0] = s; s = "";
        for(int i=302; i<802; i++) { s += "b"; } trainingData[1] = s; s = "";
        for(int i=802; i<1000; i++) { s += "c"; } trainingData[2] = s;

        char[] expectedOutput = new char[93];
        for(int i=0; i<28; i++) { expectedOutput[i] = 'a'; }
        for(int i=28; i<75; i++) { expectedOutput[i] = 'b'; }
        for(int i=75; i<93; i++) { expectedOutput[i] = 'c'; }

//        System.out.println(Arrays.toString(
//                generateFrequencyAlphabet(93,trainingData)));

        char[] output = generateFrequencyAlphabet(93, trainingData);
        System.out.println(Arrays.toString(output));
        System.out.println(Arrays.toString(expectedOutput));
        System.out.println(Arrays.equals(expectedOutput, output));

        System.out.println("frequency=" + frequency);
        System.out.println("alphabet=" + alphabet);
        System.out.println("letterCount=" + letterCount);
        System.out.println("pdf=" + pdf);
        System.out.println("cdf=" + cdf);
        System.out.println("limits=" + limits);

        String s0 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String s1 = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
        String s2 = "cccccccccccccccccc";

        System.out.println("s0="+s0.length()+" s1="+s1.length()+" s2="+s2.length());
    }
}
