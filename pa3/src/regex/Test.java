package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ilyarudyak on 01/03/15.
 */
public class Test {


    public static void main(String[] args) {


        // convenience method of Pattern
        // useful when we need only one test
        // string must match *completely*
        String myText = "42.0f";
        Boolean match = Pattern.matches("\\d+\\.\\d+f?", myText);
        System.out.println(match);

        // ---------- Matcher ----------
        // 1) complete match
        Pattern p = Pattern.compile("\\d+\\.\\d+f?");
        Matcher m = p.matcher(myText);
        System.out.println(m.matches());

        String myText2 = "42.0f.....";
        Matcher m2 = p.matcher(myText2);
        System.out.println(m2.matches());
        // the string *starts* with the pattern
        System.out.println(m2.lookingAt());

        // 2) find all matches
        String text="A horse is a horse, of course of course...";
        String pattern="horse|course";
        Matcher matcher = Pattern.compile( pattern ).matcher( text );

        while ( matcher.find() )
            System.out.println(
                    "Matched: '"+matcher.group(0)+"' at position "+matcher.start() );


    }
}
