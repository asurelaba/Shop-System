package entities.enums;

import entities.shop.Item;

import java.util.List;

public enum ItemType {
    FRUIT(ItemStoringTempAndHumidity.INDOOR_ROOM_TEMP),
    VEGGIE(ItemStoringTempAndHumidity.MIST_AREA),
    CLEANING_PRODUCT(ItemStoringTempAndHumidity.INDOOR_ROOM_TEMP),
    DIARY(ItemStoringTempAndHumidity.REFRIGERATOR),
    FROZEN(ItemStoringTempAndHumidity.FREEZER),
    COOKIE(ItemStoringTempAndHumidity.INDOOR_ROOM_TEMP);

    private ItemStoringTempAndHumidity itemStoringTempAndHumidity;

    ItemType(ItemStoringTempAndHumidity itemStoringTempAndHumidity) {
        this.itemStoringTempAndHumidity = itemStoringTempAndHumidity;
    }

    public ItemStoringTempAndHumidity getItemStoringTempAndHumidity() {
        return itemStoringTempAndHumidity;
    }
}
