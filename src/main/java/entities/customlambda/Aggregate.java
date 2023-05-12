package entities.customlambda;

@FunctionalInterface
public interface Aggregate <T extends Number> {
    T operate(T t, T u);
}
