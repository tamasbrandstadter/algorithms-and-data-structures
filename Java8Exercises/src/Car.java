public class Car {
  private int price;

  public Car(int price) {
    this.price = price;
  }

  public int getPrice() {
    return price;
  }

  public int setPrice() {
    this.price = 500;
    return price;
  }

  public void doubleValue() {
    this.price *= 2;
  }
}

