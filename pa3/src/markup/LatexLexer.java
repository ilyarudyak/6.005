package markup;

public class LatexLexer {
    private final String s;
    private int i;
    // s is the string of Latex that we're parsing,
    // and s[i] is the start of the next token to return, or
    // i == s.length means we're at the end of parsing.
    
    public LatexLexer(String s) {
        this.s = s;
        this.i = 0;
    }
    
    public Token next() throws SyntaxErrorException {
        if (i >= s.length()) {
            return new Token(Type.EOF, "");
        }
        
        switch (s.charAt(i)) {
        case '{':
            ++i;
            return new Token(Type.OPEN_BRACE, "{");
            
        case '}':
            ++i;
            return new Token(Type.CLOSE_BRACE, "}");

        case '\\':
            ++i;
            if (s.startsWith("em", i)) {
                i += 2;
                return new Token(Type.EM, "}");
            } else {
                throw new SyntaxErrorException("syntax error at " + s.substring(i));
            }
 
        default:
            // it's a Text token.  Find where it ends.
            int start = i;
//            System.out.println(s.charAt(i));
            while (i < s.length() /*&& s.charAt(i) != '{' && s.charAt(i) != '}')*/
                   && "{}\\".indexOf(s.charAt(i)) == -1)
            {
//                System.out.println(s.charAt(i));
                ++i;
            }
            int end = i;

//            System.out.println(start + " " + end);
//            System.out.println(s.substring(start, end));

            return new Token(Type.TEXT, s.substring(start, end));
        } // end of switch
    }

    public static void main(String[] args) throws SyntaxErrorException {

        String s = "In LaTeX, {\\em italics are used to show {\\em emphasis}, unless you’re\n" +
                "nesting emphasized text inside other emphasized text}.";
        LatexLexer ll = new LatexLexer(s);
        Token t = ll.next();
        System.out.println(t);
        while (!t.getType().equals(Type.EOF)) {
            System.out.println(t);
            t = ll.next();
        }
    }
}
