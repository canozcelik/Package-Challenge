package com.mobiquity.service.implementations;

import com.mobiquity.domain.Item;
import com.mobiquity.domain.Pack;
import com.mobiquity.service.PackageOptimizationService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RecursivePackageOptimization implements PackageOptimizationService {

  /**
   * Recursive knapsack problem solving
   * @param weight
   * @param itemList
   * @param optimalChoice
   * @param packageSize
   * @return
   */
  private static double fillPackage(
      double weight, List<Item> itemList, List<Item> optimalChoice, int packageSize) {
    if (packageSize == 0 || weight == 0) {
      return 0;
    }

    if (itemList.get(packageSize - 1).getWeight() > weight) {
      List<Item> subOptimalChoice = new ArrayList<>();
      double optimalCost = fillPackage(weight, itemList, subOptimalChoice, packageSize - 1);
      optimalChoice.addAll(subOptimalChoice);
      return optimalCost;
    } else {
      List<Item> includeOptimalChoice = new ArrayList<>();
      List<Item> excludeOptimalChoice = new ArrayList<>();
      double includeCost =
          itemList.get(packageSize - 1).getCost()
              + fillPackage(
                  (weight - itemList.get(packageSize - 1).getWeight()),
                  itemList,
                  includeOptimalChoice,
                  packageSize - 1);
      double excludeCost = fillPackage(weight, itemList, excludeOptimalChoice, packageSize - 1);
      if (includeCost > excludeCost) {
        optimalChoice.addAll(includeOptimalChoice);
        optimalChoice.add(itemList.get(packageSize - 1));
        return includeCost;
      } else {
        optimalChoice.addAll(excludeOptimalChoice);
        return excludeCost;
      }
    }
  }

  /**
   * Problem solving method
   * @param pack
   * @return
   */
  public String processItemSelection(Pack pack) {
    List<Item> itemList = new ArrayList<>();
    fillPackage(
        pack.getCapacity(),
        pack.getPackageList().stream()
            .sorted(Comparator.comparingDouble(Item::getWeight))
            .collect(Collectors.toList()),
        itemList,
        pack.getPackageList().size());
    // infeasible result
    if (itemList.isEmpty()) {
      return "-";
    }
    var packResult = new StringBuilder();
    for (var i = 0; i < itemList.size(); i++) {
      packResult.append(itemList.get(i).getIndex() + ", ");
    }
    // to remove unnecessary comma
    packResult.deleteCharAt(packResult.length() - 2);
    return packResult.toString();
  }
}
