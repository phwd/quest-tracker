package java.util;

public interface SortedSet extends Set {
    Comparator comparator();

    Object first();
}
