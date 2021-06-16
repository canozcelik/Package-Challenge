package com.mobiquity.domain;

/** With builder pattern */
public class Item {
  private int index;
  private double weight;
  private double cost;

  private Item() {}

  public int getIndex() {
    return index;
  }

  public double getWeight() {
    return weight;
  }

  public double getCost() {
    return cost;
  }

  public static class Builder {
    private int index;
    private double weight;
    private double cost;

    public Builder() {}

    public Builder index(int index) {
      this.index = index;
      return this;
    }

    public Builder weight(double weight) {
      this.weight = weight;
      return this;
    }

    public Builder cost(double cost) {
      this.cost = cost;
      return this;
    }

    public Item build() {
      var item = new Item();
      item.index = this.index;
      item.weight = this.weight;
      item.cost = this.cost;
      return item;
    }
  }
}
