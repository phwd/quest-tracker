package java.util;

public interface Iterator {
    boolean hasNext();

    Object next();

    default void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
