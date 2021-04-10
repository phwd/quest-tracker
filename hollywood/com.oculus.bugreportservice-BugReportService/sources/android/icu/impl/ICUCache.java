package android.icu.impl;

public interface ICUCache {
    public static final Object NULL = new Object();

    Object get(Object obj);

    void put(Object obj, Object obj2);
}
