package com.oculus.util.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public class CollectionUtil {

    public interface Filter<I> {
        boolean function(I i);
    }

    public interface Folder<I, O> {
        O function(I i, O o);
    }

    public interface Predicate<I, O> {
        O function(I i);
    }

    public static <T> boolean isNullOrEmpty(@Nullable Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isNullOrEmpty(@Nullable HashMap hashMap) {
        return hashMap == null || hashMap.isEmpty();
    }

    public static <T> boolean isNotNullOrEmpty(@Nullable Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static <T> boolean isNotNullOrEmpty(@Nullable HashMap hashMap) {
        return hashMap != null && !hashMap.isEmpty();
    }

    public static <T, U, V> Map<T, U> mapValue(@Nullable Map<T, V> map, Predicate<V, U> predicate) {
        if (map == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (T t : map.keySet()) {
            hashMap.put(t, predicate.function(map.get(t)));
        }
        return hashMap;
    }

    public static <T, U> List<U> mapList(@Nullable List<T> list, Predicate<T, U> predicate) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (T t : list) {
            U function = predicate.function(t);
            if (function != null) {
                arrayList.add(function);
            }
        }
        return arrayList;
    }

    public static <T> T findFirst(@Nullable List<T> list, Filter<T> filter) {
        if (isNullOrEmpty(list)) {
            return null;
        }
        for (T t : list) {
            if (filter.function(t)) {
                return t;
            }
        }
        return null;
    }

    public static <T> List<T> filter(@Nullable List<T> list, Filter<T> filter) {
        if (isNullOrEmpty(list)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (T t : list) {
            if (filter.function(t)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static <T, U> U foldL(U u, List<T> list, Folder<T, U> folder) {
        if (isNullOrEmpty(list)) {
            return u;
        }
        return list.size() == 1 ? folder.function(list.get(0), u) : (U) foldL(folder.function(list.get(0), u), list.subList(1, list.size()), folder);
    }

    @Nullable
    public static <T, U> U[] mapArray(Class<U> cls, T[] tArr, Predicate<T, U> predicate) {
        U[] uArr;
        if (tArr == null || (uArr = (U[]) ((Object[]) Array.newInstance((Class<?>) cls, tArr.length))) == null) {
            return null;
        }
        for (int i = 0; i < tArr.length; i++) {
            uArr[i] = predicate.function(tArr[i]);
        }
        return uArr;
    }
}
