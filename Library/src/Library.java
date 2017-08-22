public class Library {

  public static void main(String[] args) {
    BookShelf myShelf = new BookShelf();
    System.out.println(myShelf.showInfos());

    myShelf.put("Douglas Adams", "The Hitchhiker's Guide to the Galaxy", 2006);
    myShelf.put("Douglas Adams", "Mostly Harmless", 1992);
    myShelf.put("Frank Herbert", "Dune", 1965);
    myShelf.put("Frank Herbert", "The Dragon in the Sea", 20017);
    myShelf.put("bla", "fdsfasdasds", 2000);
    myShelf.put("bla", "daasd", 1955);
    myShelf.put("Frank Herbert", "Title", 20017);

    myShelf.remove("The Dragon in the Sea");

    System.out.println(myShelf.showInfos());
  }
}
