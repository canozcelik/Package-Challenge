package com.mobiquity.service.implementations;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Pack;
import com.mobiquity.exception.APIException;
import com.mobiquity.service.FileReaderService;
import com.mobiquity.service.PackageExtractionService;
import com.mobiquity.service.PackageOptimizationService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PackageExtractionServiceImpl implements PackageExtractionService {

  private static final String PACK_SPLITTER = ":";
  private static final String ITEM_SPLITTER = ",";
  private static final String ITEM_REGEX = "\\((.*?)\\)";
  private static final String CURRENCY_REGEX = "\\p{Sc}";
  private static final double MAX_PACKAGE_WEIGHT = 100;
  private static final double MAX_ITEM_WEIGHT = 100;
  private static final double MAX_ITEM_COST = 100;
  private static final int MAX_ITEM_SIZE = 15;

  /**
   * Creating packs from the file
   *
   * @param filePath
   * @return
   * @throws APIException
   * @throws IOException
   */
  public String evaluatePackageInput(String filePath) throws APIException, IOException {
    FileReaderService fileReaderService = new FileReaderServiceImpl();
    PackageOptimizationService packageOptimizationService = new RecursivePackageOptimization();
    var result = new StringBuilder();
    try {

      List<String> lineList = fileReaderService.readLineByLine(filePath);
      for (int i = 0, lineListSize = lineList.size(); i < lineListSize; i++) {
        String line = lineList.get(i);
        var pack = createPack(line);
        validatePack(pack, i + 1);
        result
            .append(packageOptimizationService.processItemSelection(pack))
            .append(System.lineSeparator());
      }

    } catch (IOException | APIException e) {
      throw new APIException(e.getMessage(), e);
    }
    return result.toString();
  }

  private Pack createPack(String line) {
    String[] linePack = StringUtils.split(line, PACK_SPLITTER);
    return new Pack(NumberUtils.toDouble(linePack[0].trim()), getItems(linePack[1].trim()));
  }

  private List<Item> getItems(String packageLine) {
    var matcher = Pattern.compile(ITEM_REGEX).matcher(packageLine);
    List<Item> itemList = new ArrayList<>();
    while (matcher.find()) {
      itemList.add(createItem(matcher.group(1)));
    }
    return itemList;
  }

  private Item createItem(String packageString) {
    String[] itemContent = StringUtils.split(packageString, ITEM_SPLITTER);
    return new Item.Builder()
        .index(NumberUtils.toInt(itemContent[0].trim()))
        .weight(NumberUtils.toDouble(itemContent[1].trim()))
        .cost(
            NumberUtils.toDouble(
                itemContent[2].trim().replaceAll(CURRENCY_REGEX, StringUtils.EMPTY)))
        .build();
  }

  private void validatePack(Pack pack, int lineNumber) throws APIException {

    if (pack.getCapacity() > MAX_PACKAGE_WEIGHT) {
      throw new APIException(
          String.format(
              "At line %s : Package weight can not be greater than " + MAX_PACKAGE_WEIGHT,
              lineNumber));
    }

    if (pack.getPackageList().size() > MAX_ITEM_SIZE) {
      throw new APIException(
          String.format(
              "At line %s : Package size can not be greater than " + MAX_ITEM_SIZE, lineNumber));
    }

    for (Item item : pack.getPackageList()) {
      if (item.getWeight() > MAX_ITEM_WEIGHT) {
        throw new APIException(
            String.format(
                "At line %s : Item weight can not be greater than " + MAX_ITEM_WEIGHT, lineNumber));
      }

      if (item.getCost() > MAX_ITEM_COST) {
        throw new APIException(
            String.format(
                "At line %s : Item cost can not be greater than " + MAX_ITEM_COST, lineNumber));
      }
    }
  }
}
