import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
  private double balance;
  private String accountNumber;
  private Lock lock;

  public BankAccount(String accountNumber, double balance) {
    this.balance = balance;
    this.accountNumber = accountNumber;
    this.lock = new ReentrantLock();
  }

  private boolean withdraw(double amount) {
    if (lock.tryLock()) {
      try {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        balance -= amount;
        System.out.printf("%s: Withdrew %f\n", Thread.currentThread().getName(), amount);
        return true;
      } finally {
        lock.unlock();
      }
    }
    return false;
  }

  private boolean deposit(double amount) {
    if (lock.tryLock()) {
      try {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        balance += amount;
        System.out.printf("%s: Deposited %f\n", Thread.currentThread().getName(), amount);
        return true;
      } finally {
        lock.unlock();
      }
    }
    return false;
  }

  public boolean transfer(BankAccount destinationAccount, double amount) {
    if (withdraw(amount)) {
      if (destinationAccount.deposit(amount)) {
        return true;
      } else {
        System.out.printf("%s: Destination account busy. Refunding money\n",
            Thread.currentThread().getName());
        deposit(amount);
      }
    }
    return false;
  }
}

class Transfer implements Runnable {
  private BankAccount sourceAccount, destinationAccount;
  private double amount;

  Transfer(BankAccount sourceAccount, BankAccount destinationAccount, double amount) {
    this.sourceAccount = sourceAccount;
    this.destinationAccount = destinationAccount;
    this.amount = amount;
  }

  public void run() {
    while (!sourceAccount.transfer(destinationAccount, amount))
      continue;
    System.out.printf("%s completed\n", Thread.currentThread().getName());
  }
}
