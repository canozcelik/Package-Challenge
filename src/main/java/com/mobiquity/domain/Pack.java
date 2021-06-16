package com.mobiquity.domain;

import java.util.List;

public class Pack {
  private double capacity;
  private List<Item> itemList;

  public Pack(double capacity, List<Item> itemList) {
    this.capacity = capacity;
    this.itemList = itemList;
  }

  public double getCapacity() {
    return capacity;
  }

  public List<Item> getPackageList() {
    return itemList;
  }
}
