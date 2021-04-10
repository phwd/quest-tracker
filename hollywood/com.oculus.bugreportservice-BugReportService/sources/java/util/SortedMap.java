package java.util;

public interface SortedMap extends Map {
    Comparator comparator();

    @Override // java.util.Map
    Set entrySet();

    Object firstKey();

    @Override // java.util.Map
    Set keySet();
}
