import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PackerTest {

  private static String successFilePath;
  private static String invalidPackageWeightPath;
  private static String invalidPackageSizePath;
  private static String invalidItemWeightPath;
  private static String invalidItemCostPath;

  @BeforeAll
  public static void init() {
    successFilePath = "src/test/resources/validInput";
    invalidPackageWeightPath = "src/test/resources/invalid_package_weight_input";
    invalidPackageSizePath = "src/test/resources/invalid_package_size_input";
    invalidItemWeightPath = "src/test/resources/invalid_item_weight_input";
    invalidItemCostPath = "src/test/resources/invalid_item_cost_input";
  }

  @Test
  public void returnValidResultWhenSuccess() throws APIException, IOException {

    Assertions.assertEquals(Packer.pack(successFilePath).trim(), "4");
  }

  @Test
  public void throwExceptionWhenInvalidInputs() {
    APIException apiExceptionInvalidPackageWeight =
        Assertions.assertThrows(
            APIException.class,
            () -> {
              Packer.pack(invalidPackageWeightPath);
            });

    APIException apiExceptionInvalidPackageSize =
        Assertions.assertThrows(
            APIException.class,
            () -> {
              Packer.pack(invalidPackageSizePath);
            });
    APIException apiExceptionInvalidItemWeight =
        Assertions.assertThrows(
            APIException.class,
            () -> {
              Packer.pack(invalidItemWeightPath);
            });
    APIException apiExceptionInvalidItemCost =
        Assertions.assertThrows(
            APIException.class,
            () -> {
              Packer.pack(invalidItemCostPath);
            });
    Assertions.assertEquals(
        apiExceptionInvalidPackageWeight.getMessage(),
        "At line 3 : Package weight can not be greater than 100.0");

    Assertions.assertEquals(
        apiExceptionInvalidPackageSize.getMessage(),
        "At line 1 : Package size can not be greater than 15");

    Assertions.assertEquals(
        apiExceptionInvalidItemWeight.getMessage(),
        "At line 4 : Item weight can not be greater than 100.0");

    Assertions.assertEquals(
        apiExceptionInvalidItemCost.getMessage(),
        "At line 2 : Item cost can not be greater than 100.0");
  }
}
