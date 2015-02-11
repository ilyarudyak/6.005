package piwords;

import java.util.Arrays;

/**
 * Created by ilyarudyak on 11/02/15.
 */
public class Test1 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString("hello".split("(?!^)")));
    }
}
