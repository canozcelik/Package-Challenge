import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PackerTest {

  private static String successFilePath;
  private static String invalidPackageWeightPath;
  @BeforeAll
  public static void init() {
    successFilePath = "src/test/resources/validInput";
    invalidPackageWeightPath = "src/test/resources/invalid_package_weight_input";
  }

  @Test
  public void returnValidResultWhenSuccess() throws APIException, IOException {

    Assertions.assertEquals(Packer.pack(successFilePath).trim(), "4");
  }

  public void isInvalidInput() {

  }
}
