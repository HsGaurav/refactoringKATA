package com.gildedrose;

class GildedRose {
  Item[] items;
  public static final String AGED_BRIE = "Aged Brie";
  public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
  public static final String SULFURAS_HAND = "Sulfuras, Hand of Ragnaros";
  public static final String CONJURED = "Conjured Mana Cake";

  public GildedRose(Item... items) {
    this.items = items;
  }

  public void updateQuality() {
    for (Item item : items) {
      updateItemQuality(item);
    }
  }

  private void updateItemQuality(Item item) {
    boolean isOutOfStock = item.sellIn < 1;
    int decrement = getDecrement(item, isOutOfStock);
    boolean nameMatch =
        !item.name.equals(AGED_BRIE)
            && !item.name.equals(BACKSTAGE_PASSES)
            && !item.name.equals(SULFURAS_HAND);

    if (nameMatch) {
      adjustQuality(item, decrement);
    }
    if (item.name.equals(AGED_BRIE)) {
      int adjustment = isOutOfStock ? 2 : 1;
      adjustQuality(item, adjustment);
    }
    if (item.name.equals(BACKSTAGE_PASSES)) {
      backstagePassAdjustment(item, isOutOfStock);
    }
    if (!item.name.equals(SULFURAS_HAND)) {
      item.sellIn = item.sellIn - 1;
    }
  }

  private void backstagePassAdjustment(Item item, boolean isOutOfStock) {
    adjustQuality(item, 1);
    if (item.sellIn < 11) {
      adjustQuality(item, 1);
    }
    if (item.sellIn < 6) {
      adjustQuality(item, 1);
    }
    if (isOutOfStock) {
      item.quality = 0;
    }
  }

  private int getDecrement(Item item, boolean isOutOfStock) {
    int decrement = item.name.equals(CONJURED) ? -2 : -1;
    return isOutOfStock ? decrement * 2 : decrement;
  }

  private void adjustQuality(Item item, int adjustment) {
    if (item.quality < 50 && item.quality > 0) {
      item.quality = item.quality + adjustment;
    }
  }

  public Item[] getItems() {
    return items;
  }
}
