public class Outcast {
  private final WordNet wordNet;

  public Outcast(WordNet wordnet) {
    this.wordNet = wordnet;
  }

  public String outcast(String[] nouns) {
    String outcast = null;
    int maxDistance = 0;

    for (String noun : nouns) {
      int currentDistance = 0;

      for (String otherNoun : nouns) {
        int distance = wordNet.distance(noun, otherNoun);
        currentDistance += distance;
      }

      if (currentDistance > maxDistance) {
        maxDistance = currentDistance;
        outcast = noun;
      }
    }

    return outcast;
  }

}
