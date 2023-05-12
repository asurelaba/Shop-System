package entities.customlambda;

import java.util.List;

@FunctionalInterface
public interface Filter<T> {
    boolean filterBy(T t);
}
