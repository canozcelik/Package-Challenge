import com.mobiquity.util.GeneralUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneralUtilTest {

  @Test
  public void returnDefaultValueWhenThereIsNoMainMethodParameter() {
    Assertions.assertEquals(
        GeneralUtil.getPathToTargetFile(new String[] {}), "src/main/test/resources/example_input");
  }

  @Test
  public void returnSelectedParameter() {
    String parameter = "main/method/parameter.txt";
    Assertions.assertEquals(GeneralUtil.getPathToTargetFile(new String[] {parameter}), parameter);
  }
}
