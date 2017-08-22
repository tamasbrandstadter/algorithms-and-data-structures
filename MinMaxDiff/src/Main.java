import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    List<Double> numberList = Arrays.asList(4d, 61d, 453d, 2.5d, 6544d);
    System.out.println(minMaxDiff(numberList));
  }

  public static double minMaxDiff(List<Double> numberList) {
    Collections.sort(numberList);
    return numberList.get(numberList.size()-1) - numberList.get(0);
  }
}
