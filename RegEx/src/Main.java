import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    String challenge1 = "I want a bike.";
    String challenge2 = "I want a ball.";

    Pattern pattern = Pattern.compile("I want a \\w+.");
    Matcher matcher = pattern.matcher(challenge1);
    boolean matches = matcher.matches();
    matcher = pattern.matcher(challenge2);
    boolean matches2 = matcher.matches();
    System.out.println(matches + " " + matches2);

    String challenge4 = "Replace all blanks with underscores.";
    System.out.println(challenge4.replaceAll("\\s", "_"));

    String challenge5 = "aaabccccccccdddefffg";
    System.out.println(challenge5.matches("[a-g]+"));
    System.out.println(challenge5.matches("^aaab+c{8}d{3}e+f{3}g$"));

    String challenge7 = "abcd.135";
    String tryOut = "f5.12a";
    System.out.println(challenge7.matches("^[A-z][a-z]+\\.\\d+$"));
    System.out.println(tryOut.matches("^[A-z][a-z]+\\.\\d+$"));

    String challenge8 = "abcd.135uvqz.7tzik.999";
    Pattern myPattern = Pattern.compile("(\\d+)");
    Matcher matcher1 = myPattern.matcher(challenge8);

    while (matcher1.find()) {
      System.out.println("Occurrences: " + matcher1.group(1));
    }

    String challenge9 = "abcd.135\tuvqz.7\ttzik.999\n";
    Pattern myPattern9 = Pattern.compile("[A-Za-z]+\\.(\\d+)\\s");
    Matcher matcher9 = myPattern9.matcher(challenge9);

    while (matcher9.find()) {
      System.out.println("Occurrences: " + matcher9.group(1));
    }

    String challenge10 = "abcd.135\tuvqz.7\ttzik.999\n";
    Pattern myPattern10 = Pattern.compile("[A-Za-z]+\\.(\\d+)\\s");
    Matcher matcher10 = myPattern10.matcher(challenge10);

    while (matcher10.find()) {
      System.out.println("Occurrence starts: " + matcher10.start(1) + " ends: " + (matcher10.end(1) - 1));
    }

    String challenge11 = "{0, 2}, {0, 5}, {1, 3}, {2, 4}";
    Pattern myPatternBetter11 = Pattern.compile("\\{(\\d+, \\d+)}");
    Pattern myPattern11 = Pattern.compile("\\{(.+?)}");
    Matcher matcher11 = myPattern11.matcher(challenge11);

    while (matcher11.find()) {
      System.out.println("Points: " + matcher11.group(1));
    }

    String challenge12 = "11111";
    System.out.println(challenge12.matches("^\\d{5}$"));

    String challenge13 = "11111-1111";
    System.out.println(challenge13.matches("^\\d{5}-\\d{4}$"));

    System.out.println(challenge12.matches("^\\d{5}(-\\d{4})?$"));
    System.out.println(challenge13.matches("^\\d{5}(-\\d{4})?$"));

    int[] myArray = {2,3,4,5,6,7};
    System.out.println(Arrays.toString(reverseArray(myArray)));

  }

  public static int[] reverseArray(int[] myArray) {
    for (int i = 0; i < myArray.length / 2; i++) {
      int temp = myArray[i];
      myArray[i] = myArray[myArray.length - 1 - i];
      myArray[myArray.length - 1 - i] = temp;
    }
    return myArray;
  }

}
