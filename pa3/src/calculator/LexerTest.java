package calculator;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LexerTest {


    @Test
    public void testNext1() throws Exception {

        String s = "3pt * 2.4in";
        Lexer l = new Lexer(s);
        assertEquals(Arrays.asList("3","pt","*","2.4","in"), l.parse() );

    }

    @Test
    public void testNext2() throws Exception {

        String s = "4pt+((3in*2.4))";
        Lexer l = new Lexer(s);
        assertEquals(Arrays.asList("4","pt","+","(","(","3","in","*","2.4",")",")"), l.parse() );

    }

    @Test
    public void testNext3() throws Exception {

        String s = " ( 3 in * 2.4 )  pt  ";
        Lexer l = new Lexer(s);
        assertEquals(Arrays.asList("(","3","in","*","2.4",")","pt"), l.parse() );

    }
}