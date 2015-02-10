package piwords;

import static org.junit.Assert.*;

import org.junit.Test;

import java.math.BigInteger;

public class PiGeneratorTest {

    @Test
    public void powerModTest() {

        // 5^7 mod 23 = 17
        assertEquals(17, PiGenerator.powerMod(5, 7, 23));
        assertEquals(1, PiGenerator.powerMod(7, 560, 561));

        BigInteger n = new BigInteger("1234567");
        assertEquals(n.modPow(new BigInteger("100"), new BigInteger("345")).intValue(),
                PiGenerator.powerMod(1234567, 100, 345));
    }

    @Test
    public void computePiInHexTest() {

        // pi in hex = 3.243F6A8885A308D3...
        assertEquals(0x02, PiGenerator.piDigit(1));
        assertEquals(0x0F, PiGenerator.piDigit(4));

        int[] pi = new int[]{0x2,0x4,0x3,0xF,0x6,0xA,0x8,0x8,0x8,0x5,0xA,0x3,0x0,0x8,0xD,0x3};
        assertArrayEquals(pi, PiGenerator.computePiInHex(pi.length));

    }

}
