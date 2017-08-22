import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    if (args.length == 0) {
      showUsageInfos();
    } else if (args.length == 1 && args[0].equals("mostCommonWord")) {
      showErrorMessage();
    } else {
      mostCommonWord(args[1]);
    }
  }

  private static void showUsageInfos() {
    System.out.println("No arguments provided. Use the program as follows: java mostCommonWord [source]");
  }

  private static void showErrorMessage() {
    System.out.println("No source provided.");
  }

  private static void mostCommonWord(String sourcePath) throws FileNotFoundException {
    Scanner file = new Scanner(new File(sourcePath)).useDelimiter("[^a-zA-Z]+");
    HashMap<String, Integer> map = new HashMap<>();
    while (file.hasNext()) {
      String word = file.next();
      map.put(word, map.containsKey(word) ? map.get(word) + 1 : 1);
    }
    ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
    entries.sort(Comparator.comparing(Map.Entry::getValue));
    System.out.println(entries.get(entries.size() - 1));
  }
}
