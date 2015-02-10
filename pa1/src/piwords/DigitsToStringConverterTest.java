package piwords;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class DigitsToStringConverterTest {
    @Test
    public void basicNumberSerializerTest() {
        // Input is a 4 digit number, 0.123 represented in base 4
        int[] input = {0, 1, 2, 3};

        // Want to map 0 -> "d", 1 -> "c", 2 -> "b", 3 -> "a"
        char[] alphabet = {'d', 'c', 'b', 'a'};

        String expectedOutput = "dcba";
        assertEquals(expectedOutput,
                     DigitsToStringConverter.convertDigitsToString(
                             input, 4, alphabet));

        String expectedOutput1 = "drsqlolyrtrodnlhnqtgkudqgtuirxneqbckbszivqqvgdm" +
                "elmuexroiqiyalvuzvebmijpqqxlkplrncfwjpbymggohjmmqismssciekhvdutc" +
                "xtjpsbwhufomqjaosygpowupymlifsfiizrodpl";
        int[] piHexDigits = PiGenerator.computePiInHex(1000);
        int[] translatedPiBase26 =
                BaseTranslator.convertBase(piHexDigits, 16, 26, 1000);
        assertEquals(expectedOutput1,
                DigitsToStringConverter.convertDigitsToString(
                        Arrays.copyOf(translatedPiBase26, 150),
                        Main.BASIC_ALPHABET.length, Main.BASIC_ALPHABET));

    }

    // TODO: Write more tests (Problem 3.a)
}
