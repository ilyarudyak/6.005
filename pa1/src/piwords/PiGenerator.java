package piwords;

public class PiGenerator {
    /**
     * Returns precision hexadecimal digits of the fractional part of pi.
     * Returns digits in most significant to least significant order.
     * 
     * If precision < 0, return null.
     * 
     * @param precision The number of digits after the decimal place to
     *                  retrieve.
     * @return precision digits of pi in hexadecimal.
     */
    public static int[] computePiInHex(int precision) {

        int[] pi = new int[precision];
        for (int i=0; i<precision; i++) {
            // piDigit(0) == 3, fractions started from 1
            pi[i] = piDigit(i + 1);
        }
        return pi;
    }

    /**
     * Computes a^b mod n
     * 
     * If a < 0, b < 0, or n < 0, return -1.
     * 
     * @param a
     * @param b
     * @param n
     * @return a^b mod n
     */
    public static int powerMod(int a, int b, int n) {

        if (a < 0 || b < 0 || n < 0) return -1;
        int c = 0, d = 1;

        int[] bb = getReverseBinaryRep(b);
        for (int i=bb.length-1; i>=0; i--) {
            c = 2 * c;
            d = (d * d) % n;
            if (bb[i] == 1) {
                c = c + 1;
                d = (d * a) % n;
            }
        }
        return d;
    }

    private static int[] getReverseBinaryRep(int n) {

        // get binary representation and reverse it
        String bin = new StringBuilder(Integer.toString(n, 2)).reverse().toString();

        int[] binRep = new int[bin.length()];
        for (int i=bin.length()-1; i >=0; i--) {
            binRep[i] = Integer.parseInt(bin.substring(i, i + 1));
        }
        return binRep;
    }
    
    /**
     * Computes the nth digit of Pi in base-16.
     * 
     * If n < 0, return -1.
     * 
     * @param n The digit of Pi to retrieve in base-16.
     * @return The nth digit of Pi in base-16.
     */
    public static int piDigit(int n) {
        if (n < 0) return -1;
        
        n -= 1;
        double x = 4 * piTerm(1, n) - 2 * piTerm(4, n) -
                   piTerm(5, n) - piTerm(6, n);
        x = x - Math.floor(x);
        
        return (int)(x * 16);
    }
    
    private static double piTerm(int j, int n) {
        // Calculate the left sum
        double s = 0;
        for (int k = 0; k <= n; ++k) {
            int r = 8 * k + j;
            s += powerMod(16, n-k, r) / (double) r;
            s = s - Math.floor(s);
        }
        
        // Calculate the right sum
        double t = 0;
        int k = n+1;
        // Keep iterating until t converges (stops changing)
        while (true) {
            int r = 8 * k + j;
            double newt = t + Math.pow(16, n-k) / r;
            if (t == newt) {
                break;
            } else {
                t = newt;
            }
            ++k;
        }
        
        return s+t;
    }
}
