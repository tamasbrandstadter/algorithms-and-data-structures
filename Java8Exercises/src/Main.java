import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    Runnable runnable = () -> {
      String myString = "Let's split this into array parts.";
      String[] parts = myString.split(" ");
      for (String part : parts) {
        System.out.println(part);
      }
    };

    Function<String, String> myFunction = source -> {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < source.length(); i++) {
        if (i % 2 == 1) {
          sb.append(source.charAt(i));
        }
      }
      return sb.toString();
    };

    System.out.println(everySecondCharacter(myFunction, "121143141"));

    System.out.println(myFunction.apply("121143141"));

    Supplier<String> supplier2 = () -> "I love JAVA";
    String iLoveJava = supplier2.get();
    System.out.println(iLoveJava);

    List<String> names = Arrays.asList("Amelia", "Olivia", "emily", "Isla", "Ava", "oliver", "Jack", "Charlie", "harry", "Jacob");
    List<String> namesWithUpperCase = new ArrayList<>();
    names.forEach((name) ->
        namesWithUpperCase.add(name.substring(0, 1).toUpperCase() + name.substring(1))
    );

    namesWithUpperCase.sort((name1, name2) -> name1.compareTo(name2));
    //same solution with method reference
    namesWithUpperCase.sort(String::compareTo);
    //same with built-in natural order
    namesWithUpperCase.sort(Comparator.naturalOrder());

    namesWithUpperCase.forEach((s -> System.out.println(s)));
    //same solution with method reference
    namesWithUpperCase.forEach(System.out::println);

    List<String> names2 = Arrays.asList("Amelia", "Olivia", "emily", "Isla", "Ava", "oliver", "Jack", "Charlie", "harry", "Jacob");
    names2.stream()
        .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
        .sorted(String::compareTo)
        .forEach(System.out::println);

    long count = names2.stream()
        .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
        .filter(name -> name.startsWith("A"))
        .count();
    System.out.println("Number of names begining with 'A' is: " + count);

    //peek is used for debugging if necessary, it will be evaluated after a terminal operation
    List<String> myList = names2.stream()
        .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
        .peek(System.out::println)
        .sorted(String::compareTo)
        .collect(Collectors.toList());
  }

  private static String everySecondCharacter(Function<String, String> inputFunction, String inputString) {
    return inputFunction.apply(inputString);
  }
}
