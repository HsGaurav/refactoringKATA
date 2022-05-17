package com.gildedrose;

import java.util.HashMap;
import java.util.Map;

public class CustomisedItemFactory {

  private static final Map<String, CustomisedItem> ITEM_TYPES_LIST = new HashMap<>();
  public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
  public static final String BRIE = "Aged Brie";
  public static final String BACKSTAGE_PASSES_ITEM = "Backstage passes to a TAFKAL80ETC concert";
  public static final String CONJURED_ITEM = "Conjured";

  public CustomisedItemFactory(Item item) {
    ITEM_TYPES_LIST.put(SULFURAS, new Sulfuras());
    ITEM_TYPES_LIST.put(BRIE, new AgedBrie(item));
    ITEM_TYPES_LIST.put(BACKSTAGE_PASSES_ITEM, new BackstagePassesItem(item));
    ITEM_TYPES_LIST.put(CONJURED_ITEM, new ConjuredItem(item));
  }

  public CustomisedItem customiseItem(Item item) {
    if (isStandardItem(item)) {
      return new StandardItem(item);
    }
    return ITEM_TYPES_LIST.get(item.name);
  }

  private boolean isStandardItem(Item item) {
    return !ITEM_TYPES_LIST.containsKey(item.name);
  }
}
