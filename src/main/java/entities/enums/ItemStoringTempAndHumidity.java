package entities.enums;

import entities.customlambda.IGroup;
import entities.shop.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public enum ItemStoringTempAndHumidity {
    INDOOR_ROOM_TEMP(70.0f, 40),
    OUTSIDE(200.0f, 80),
    REFRIGERATOR(40.0f, 20),
    FREEZER(30.0f, 10),
    MIST_AREA(60.0f, 90);

    private static final Logger LOGGER = LogManager.getLogger(ItemStoringTempAndHumidity.class);
    private final float temp;
    private final int humidity;

    ItemStoringTempAndHumidity(float temp, int humidity) {
        this.temp = temp;
        this.humidity = humidity;
    }

    public float getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    @Override
    public String toString() {
        return "ItemStoringTempAndHumidity{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                '}';
    }

    public static void groupby(Collection<Item> items) {
        IGroup group = (ItemStoringTempAndHumidity tempAndHumidity) -> {
            List<Item> groupedItems = new ArrayList<>();
            items.forEach((Item item) -> {
                if (item.getItemType().getItemStoringTempAndHumidity() == tempAndHumidity) {
                    groupedItems.add(item);
                }
            });
            LOGGER.info("Items in " + tempAndHumidity.name() + groupedItems);
        };

        List<ItemStoringTempAndHumidity> tempAndHumidities = Arrays.asList(ItemStoringTempAndHumidity.values());
        for (ItemStoringTempAndHumidity tempAndHumidity : tempAndHumidities) {
            group.action(tempAndHumidity);
        }
    }
}
