package java.util;

public interface Map {

    public interface Entry {
        Object getKey();

        Object getValue();

        int hashCode();
    }

    void clear();

    boolean containsKey(Object obj);

    Set entrySet();

    boolean equals(Object obj);

    Object get(Object obj);

    int hashCode();

    boolean isEmpty();

    Set keySet();

    Object put(Object obj, Object obj2);

    void putAll(Map map);

    Object remove(Object obj);

    int size();

    Collection values();

    default Object putIfAbsent(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 == null ? put(obj, obj2) : obj3;
    }

    default boolean remove(Object obj, Object obj2) {
        Object obj3 = get(obj);
        if (!Objects.equals(obj3, obj2)) {
            return false;
        }
        if (obj3 == null && !containsKey(obj)) {
            return false;
        }
        remove(obj);
        return true;
    }
}
