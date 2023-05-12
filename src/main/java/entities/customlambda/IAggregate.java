package entities.customlambda;

@FunctionalInterface
public interface IAggregate<T extends Number> {
    T operate(T t, T u);
}
