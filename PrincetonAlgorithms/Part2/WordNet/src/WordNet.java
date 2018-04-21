import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WordNet {
  private final Map<String, LinkedList<Integer>> nouns;
  private final List<String> synonymList;
  private final SAP sap;

  public WordNet(String synsets, String hypernyms) {
    this.nouns = new HashMap<>();
    this.synonymList = new ArrayList<>();
    int id = 0;

    In in = new In(synsets);
    while (in.hasNextLine()) {
      String line = in.readLine();
      String[] lineParts = line.split(",");

      id = Integer.parseInt(lineParts[0]);

      String synonymSet = lineParts[1];
      synonymList.add(synonymSet);
      String[] synonyms = synonymSet.split(" ");

      for (String noun : synonyms) {
        if (nouns.containsKey(noun)) {
          LinkedList<Integer> idsOfSynonymNouns = nouns.get(noun);
          idsOfSynonymNouns.add(id);
          nouns.put(noun, idsOfSynonymNouns);
        } else {
          LinkedList<Integer> ids = new LinkedList<>();
          ids.add(id);
          nouns.put(noun, ids);
        }
      }
    }

    Digraph digraph = new Digraph(id + 1);

    in = new In(hypernyms);
    int synsetId;
    while (in.hasNextLine()) {
      String line = in.readLine();
      String[] parts = line.split(",");

      synsetId = Integer.parseInt(parts[0]);
      for (int i = 1; i < parts.length; i++) {
        digraph.addEdge(synsetId, Integer.parseInt(parts[i]));
      }
    }

    this.sap = new SAP(digraph);

    DirectedCycle directedCycle = new DirectedCycle(digraph);
    if (directedCycle.hasCycle()) {
      throw new IllegalArgumentException();
    }
  }

  public Iterable<String> nouns() {
    return nouns.keySet();
  }

  public boolean isNoun(String word) {
    if (word == null) {
      throw new IllegalArgumentException();
    }
    return nouns.containsKey(word);
  }

  public int distance(String nounA, String nounB) {
    if (nounA == null || nounB == null || !nouns.containsKey(nounA) || !nouns.containsKey(nounB)) {
      throw new IllegalArgumentException();
    }

    return sap.length(nouns.get(nounA), nouns.get(nounB));
  }

  public String sap(String nounA, String nounB) {
    int ancestor = sap.ancestor(nouns.get(nounA), nouns.get(nounB));
    return synonymList.get(ancestor);
  }

}
