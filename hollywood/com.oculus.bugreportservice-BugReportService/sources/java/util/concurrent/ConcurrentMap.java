package java.util.concurrent;

import java.util.Map;

public interface ConcurrentMap extends Map {
    @Override // java.util.Map
    Object putIfAbsent(Object obj, Object obj2);

    @Override // java.util.Map
    boolean remove(Object obj, Object obj2);
}
