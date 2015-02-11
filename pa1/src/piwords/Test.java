package piwords;


import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by ilyarudyak on 09/02/15.
 */
public class Test {

    public static void main(String[] args) {

//        System.out.println(Arrays.toString(fromBase10(
//                new BigDecimal("0.141592653589793238462643383279502884197169399375"), 16, 20)));

//        int[] pi = {0x2,0x4,0x3,0xF,0x6,0xA,0x8,0x8,0x8,0x5,0xA,0x3,0x0,0x8};
//        System.out.println(toBase10(pi, 16).toString().substring(0, pi.length));
//        System.out.println(Arrays.toString(convertBase(pi, 16, 10, pi.length)));

        int[] a = {3,7,5};
        System.out.println(Arrays.toString(convertBase(a, 10, 2, a.length)));
    }

    /**
     * for (i < precisionB) {
     *   1. Keep a carry, initialize to 0.
     *   2. From RIGHT to LEFT
     *   	a. x = multiply the ith digit by baseB and add the carry
     *      b. the new ith digit is x % baseA
     *      c. carry = x / baseA
     *   3. output[i] = carry */
    // convert baseA -> baseB
    public static int[] convertBase(int[] digits, int baseA,
                                    int baseB, int precisionB) {

        int[] output = new int[precisionB];
        int x;
        int carry;
        for (int i=0; i<precisionB; i++) {
            carry = 0;
            for (int j=digits.length-1; j>=0; j--) {
                x = digits[j] * baseB + carry;
                digits[j] = x % baseA;
                carry = x / baseA;
            }
            System.out.println(Arrays.toString(digits));
            output[i] = carry;
        }
        return output;
    }

    private static BigDecimal toBase10(int[] digits, int base) {

        BigDecimal x = new BigDecimal(0);
        for (int i=0; i<digits.length; i++) {
            x = new BigDecimal(digits[i]).multiply(
                    new BigDecimal(1).divide(
                    new BigDecimal(base).pow((i+1)))).add(x);
        }
        return x;
    }

    private static int[] fromBase10(BigDecimal x, int base, int precision) {

        int[] output = new int[precision];
        BigDecimal c;
        for (int i=0; i<precision; i++) {

            c = x.multiply(new BigDecimal(base));
            output[i] = c.intValue();
            x = c.subtract(new BigDecimal(c.intValue()));
        }
        return output;
    }
}
