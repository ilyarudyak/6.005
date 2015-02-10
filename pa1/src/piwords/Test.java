package piwords;


import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by ilyarudyak on 09/02/15.
 */
public class Test {

    public static void main(String[] args) {

//        System.out.println(Arrays.toString(fromBase10(.375, 2, 3)));

    }

    private static double toBase10(int[] digits, int base) {

        double x = 0;
        for (int i=0; i<digits.length; i++) {
            x += digits[i] * Math.pow(base, -(i+1));
        }
        return x;
    }

//    private static int[] fromBase10(double x, int base, int precision) {
//
//        int[] output = new int[precision];
//        BigDecimal c;
//        BigDecimal xbig = new BigDecimal()
//        for (int i=0; i<precision; i++) {
//
//            c = x * base;
//            output[i] = (int) c;
//            x = (x * base) - (int) c;
//        }
//        return output;
//    }




}
