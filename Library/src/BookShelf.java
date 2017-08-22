import java.util.*;

public class BookShelf {
  private List<Book> books;

  public BookShelf() {
    this.books = new ArrayList<>();
  }

  public void put(String author, String title, int releaseYear) {
    books.add(new Book(author, title, releaseYear));
  }

  public void remove(String title) {
    books.removeIf(book -> book.getTitle().equals(title));
  }

  public String getFavouriteAuthor() {
    Map<String, Integer> map = new HashMap<>();
    for (Book book : books) {
      map.put(book.getAuthor(), map.containsKey(book.getAuthor()) ? map.get(book.getAuthor()) + 1 : 1);
    }
    ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
    entries.sort(Comparator.comparing(Map.Entry::getValue));
    return entries.get(entries.size() - 1).getKey();
  }

  public List<Book> queryForPublish() {
    books.sort((book1, book2) -> {
      if (book1.getReleaseYear() == book2.getReleaseYear()) {
        return 0;
      } else {
        return book1.getReleaseYear() < book2.getReleaseYear() ? 1 : -1;
      }
    });
    return books;
  }

  public String showInfos() {
    if (books.isEmpty()) {
      return "You have no books.";
    } else {
      return "You have " + books.size() + " books. \n " +
          "Earliest released: " + queryForPublish().get(books.size() - 1) + " \n " +
          "Latest released: " + queryForPublish().get(0) + " \n " +
          "Favourite author: " + getFavouriteAuthor();
    }
  }
}
