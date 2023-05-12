package entities.customlambda;

import entities.enums.ItemStoringTempAndHumidity;

@FunctionalInterface
public interface Group<T extends ItemStoringTempAndHumidity> {
    void action(T t);
}
