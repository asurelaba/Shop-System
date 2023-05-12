package entities.customlambda;

import entities.enums.ItemStoringTempAndHumidity;

@FunctionalInterface
public interface IGroup<T extends ItemStoringTempAndHumidity> {
    void action(T t);
}
