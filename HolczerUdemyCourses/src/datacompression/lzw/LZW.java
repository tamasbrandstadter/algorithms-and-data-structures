package datacompression.lzw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// the probability distribution of words/chars differ from text to text
public class LZW {

    public List<Integer> compress(String text) {
        int dictionarySize = 256;
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < dictionarySize; i++) {
            dictionary.put(String.valueOf((char) i), i);
        }

        String temp = "";
        List<Integer> result = new ArrayList<>();

        for (char c : text.toCharArray()) {
            String temp2 = temp + c;
            if (dictionary.containsKey(temp2)) {
                temp = temp2;
            } else {
                result.add(dictionary.get(temp));
                dictionary.put(temp2, dictionarySize++);
                temp = String.valueOf(c);
            }
        }

        if (!temp.isEmpty()) {
            result.add(dictionary.get(temp));
        }

        return result;
    }

    public String decompress(List<Integer> compressedValues) {
        int dictionarySize = 256;
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < dictionarySize; i++) {
            dictionary.put(i, String.valueOf((char) i));
        }

        String temp = String.valueOf((char) (int) compressedValues.remove(0));
        StringBuilder sb = new StringBuilder(temp);

        for (int value : compressedValues) {
            String s;
            if (dictionary.containsKey(value)) {
                s = dictionary.get(value);
            } else if (value == dictionarySize) {
                s = temp + temp.charAt(0);
            } else {
                throw new IllegalArgumentException("Bad compressed value");
            }

            sb.append(s);
            dictionary.put(dictionarySize++, temp + s.charAt(0));
            temp = s;
        }

        return sb.toString();
    }

}
