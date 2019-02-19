package sorting;

import java.util.concurrent.CountDownLatch;

public class SleepSort {

    public static void sort(int[] nums) {
        CountDownLatch latch = new CountDownLatch(nums.length);
        for (int num : nums) {
            new Thread(() -> {
                latch.countDown();
                try {
                    latch.await();
                    Thread.sleep(num * 100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }).start();
        }
    }

}
