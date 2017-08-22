import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MainTest {

  @Test
  public void minMaxDiff() throws Exception {
    List<Double> numberList = Arrays.asList(4d, 61d, 453d, 2.5d, 6544d);
    assertEquals(6541.5d, Main.minMaxDiff(numberList), 0.01);
  }

  @Test
  public void minMaxDiffWith0() throws Exception {
    List<Double> numberList = Arrays.asList(4d, 0d, 453d, 2d, 6544d);
    assertEquals(6544.0d, Main.minMaxDiff(numberList), 0.01);
  }

  @Test
  public void minMaxDiffWithNegative() throws Exception {
    List<Double> numberList = Arrays.asList(54d, 0d, -1d, 2d, 6544d);
    assertEquals(6545.0d, Main.minMaxDiff(numberList), 0.01);
  }
}