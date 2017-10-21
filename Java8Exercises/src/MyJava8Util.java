import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyJava8Util {
  public static void main(String[] args) {
    String[] myArray = new String[] {"beta", "gamma", "ypsilonaaaa", "alf"};
    Arrays.sort(myArray, (o1, o2) -> {
      if (o1.length() > o2.length()) {
        return -1;
      } else if (o1.length() < o2.length()) {
        return 1;
      } else {
        return 0;
      }
    });
    Arrays.stream(myArray).forEach(System.out::println);

    String[] myArray2 = new String[] {"beta", "gamma", "ypsilonaaaa", "alf"};
    Arrays.sort(myArray2, (o1, o2) -> {
      if (o1.charAt(0) < o2.charAt(0)) {
        return -1;
      } else if (o1.charAt(0) < o2.charAt(0)) {
        return 1;
      } else {
        return 0;
      }
    });
    Arrays.stream(myArray2).forEach(System.out::println);

    String[] myArray3 = new String[] {"beta", "gamma", "ypsilona", "alf", "eperke"};
    Arrays.sort(myArray3, ((o1, o2) -> o2.startsWith("e") ? 1 : -1));
    Arrays.stream(myArray3).forEach(System.out::println);

    String s1 = "test string";
    String s2 = "test string longer";
    Car car = new Car(100);
    Car car2 = new Car(200);

    String longer = betterEntry(s1, s2, (s11, s21) -> s11.length() > s21.length());
    Car bigger = betterEntry(car, car2, (car11, car21) -> car11.getPrice() > car21.getPrice());
    System.out.println(longer);
    System.out.println(bigger.getPrice());

    List<String> words = Arrays.asList("hi", "hello", "hii", "blabla");
    List<Integer> nums = Arrays.asList(10, 11, 145, 1000, 10000);

    allMatches(words, s -> s.length() < 4).forEach(System.out::println);
    allMatches(nums, i -> i < 5000).forEach(System.out::println);

    transformedList(words, s -> s + "!!").forEach(System.out::println);
    transformedList(words, String::toUpperCase).forEach(System.out::println);

    List<Car> carList = Arrays.asList(new Car(100), new Car(200), new Car(300));
    List<Integer> priceList = transformedList(carList, Car::getPrice);
    System.out.println(priceList);
  }

  private static <T> T betterEntry(T s1, T s2, TwoElementPredicate<T> twoElementPredicate) {
    return twoElementPredicate.execute(s1, s2) ? s1 : s2;
  }

  private static <T> List<T> allMatches(List<T> inputList, Predicate<T> inputPredicate) {
    List<T> myList = new ArrayList<>();
    for (T s : inputList) {
      if (inputPredicate.test(s)) {
        myList.add(s);
      }
    }
    return myList;

    //other solution would be with stream:
    //return inputList.stream().filter(inputPredicate).collect(Collectors.toList());
  }

  private static <T, R> List<R> transformedList(List<T> input, Function<T, R> myFunction) {
    List<R> returnList = new ArrayList<>();
    for (T s : input) {
      returnList.add(myFunction.apply(s));
    }
    return returnList;
  }

  private static double getAverage(List<Double> list) {
    return list.stream().mapToDouble(n -> n).average().orElseThrow(RuntimeException::new);
  }

  private static List<Double> generateList(int size) {
    return Stream.generate(Math::random).limit(size).collect(Collectors.toList());
  }

  private static List<Double> cutOff(List<Double> list, double cutOff) {
    list.removeIf(n -> n < cutOff);
    return list;
  }

  private static <T, S> List<S> genericMethod(List<T> tList, Function<T, S> function) {
    return tList.stream().map(function).collect(Collectors.toList());
  }
}

