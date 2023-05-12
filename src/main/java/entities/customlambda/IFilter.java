package entities.customlambda;

@FunctionalInterface
public interface IFilter<T> {
    boolean filterBy(T t);
}
