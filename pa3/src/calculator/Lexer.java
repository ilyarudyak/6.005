package calculator;

import markup.SyntaxErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Calculator lexical analyzer.
 */
public class Lexer {

    private final String s;
    private int i = 0;
    private final Matcher matcher;

    // Regex matching the next token.
    private static final Pattern TOKEN_REGEX
            = Pattern.compile (
            ""                             // anchors to the beginning of the match start
            + "(\\()"                       // LEFTPAREN
            + "|"
            + "(\\))"                       // RIGHTPAREN
            + "|"
            + "(\\d+\\.*\\d*)"              // NUMBER
            + "|"
            + "(in)"                        // INCH
            + "|"
            + "(pt)"                        // POINT
            + "|"
            + "(\\+)"                       // PLUS
            + "|"
            + "(\\-)"                       // MINUS
            + "|"
            + "(\\*)"                       // TIMES
            + "|"
            + "(/)"                         // DIVIDE
            + "|"
            + "(\\s+)"                      // SPACE
    );

    // The token types for each of the parenthesized patterns in TOKEN_REGEX.
    private static final Type[] TOKEN_TYPES
            = {
            Type.LEFTPAREN,
            Type.RIGHTPAREN,
            Type.NUMBER,
            Type.INCH,
            Type.POINT,
            Type.PLUS,
            Type.MINUS,
            Type.TIMES,
            Type.DIVIDE,
            Type.WHITESPACE
    };

    public Lexer(String s) {
        this.s = s;
        this.matcher = TOKEN_REGEX.matcher(s);
    }

    public Token next() throws SyntaxErrorException {


        String value;
        while(true) {
            if (i >= s.length()) {
                return new Token(Type.EOF, "");
            }

            if (!matcher.find(i)) {
                throw new SyntaxErrorException(
                        "syntax error at " + s.substring(i));
            }

            value = matcher.group(0);
            i = matcher.end();

            // skip whitespace
            if (!value.contains(" ")) { break; }

//        System.out.println("value='"+value+"' i="+i);
        }

        Token t = null;
        // here we use k <, not <= as in lectures
        for (int k = 1; k <= matcher.groupCount(); k++) {

            if (matcher.group(k) != null) {
                t = new Token(TOKEN_TYPES[k-1], value);
            }
        }

        return t;
    }

	/**
	 * Token in the stream.
	 */
	static class Token {
		private final Type type;
		private final String text;

        public Type getType() {
            return type;
        }

        public String getText() {
            return text;
        }

        Token(Type type, String text) {
			this.type = type;
			this.text = text;
		}

        @Override
        public String toString() {
            return "Token{" +
                    "type=" + type +
                    ", text='" + text + '\'' +
                    '}';
        }
    }

    List<String> parse() throws SyntaxErrorException {

        List<String> list = new ArrayList<String>();

        Token t = next();
        while (!t.getType().equals(Type.EOF) ) {

            list.add(t.getText());
            t = next();

        }
        return list;
    }

	@SuppressWarnings("serial")
	static class TokenMismatchException extends Exception {
	}


    public static void main(String[] args) throws SyntaxErrorException {

        String s = "4pt + ((3in*2.4))";
        Lexer l = new Lexer(s);

        System.out.println(l.parse());
//        System.out.println(l.next());
//        System.out.println(l.next());

    }
}
