package datacompression.runlength;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RLE {

    public static String encode(String source) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < source.length(); i++) {
            // we have to initialize with 1, because in case 0 the single chars would not be present in compressed state
            int length = 1;

            while (i + 1 < source.length() && source.charAt(i) == source.charAt(i + 1)) {
                length++;
                i++;
            }

            // AAAA -> 4
            sb.append(length);
            // A
            sb.append(source.charAt(i));
        }

        return sb.toString();
    }

    public static String decode(String source) {
        StringBuilder sb = new StringBuilder();

        // i.e: 9A
        Pattern pattern = Pattern.compile("[0-9]+|[a-zA-Z]");
        Matcher matcher = pattern.matcher(source);

        while (matcher.find()) {
            // this is the same length from encode
            int length = Integer.parseInt(matcher.group());

            // keep finding encoded groups
            matcher.find();
            while (length-- != 0) {
                // the group is the character: 4A -> AAAA
                sb.append(matcher.group());
            }
        }

        return sb.toString();
    }


}
