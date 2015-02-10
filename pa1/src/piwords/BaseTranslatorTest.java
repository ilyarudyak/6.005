package piwords;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class BaseTranslatorTest {
    @Test
    public void basicBaseTranslatorTest() {
        // Expect that .01 in base-2 is .25 in base-10
        // (0 * 1/2^1 + 1 * 1/2^2 = .25)
        int[] input = {0, 1};
        int[] expectedOutput = {2, 5};
        assertArrayEquals(expectedOutput,
                        BaseTranslator.convertBase(input, 2, 10, 2));

        // 3.1415926535897932384626 -> 3.243F6A8885A
        int[] input1 = {1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6,2,6};
        int[] expectedOutput1 = {2,4,3,15,6,10,8,8,8,5,10};
        assertArrayEquals(expectedOutput1,
                        BaseTranslator.convertBase(input1,10,16,11));

    }

}
