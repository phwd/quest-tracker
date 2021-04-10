package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
public final class Platform {
    static <T> T[] newArray(T[] reference, int length) {
        return (T[]) ((Object[]) Array.newInstance(reference.getClass().getComponentType(), length));
    }

    static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        return Collections.newSetFromMap(map);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.common.collect.MapMaker] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.common.collect.MapMaker tryWeakKeys(com.google.common.collect.MapMaker r1) {
        /*
            com.google.common.collect.MapMaker r0 = r1.weakKeys()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Platform.tryWeakKeys(com.google.common.collect.MapMaker):com.google.common.collect.MapMaker");
    }

    static <K, V1, V2> SortedMap<K, V2> mapsTransformEntriesSortedMap(SortedMap<K, V1> fromMap, Maps.EntryTransformer<? super K, ? super V1, V2> transformer) {
        if (fromMap instanceof NavigableMap) {
            return Maps.transformEntries((NavigableMap) fromMap, (Maps.EntryTransformer) transformer);
        }
        return Maps.transformEntriesIgnoreNavigable(fromMap, transformer);
    }

    static <K, V> SortedMap<K, V> mapsAsMapSortedSet(SortedSet<K> set, Function<? super K, V> function) {
        if (set instanceof NavigableSet) {
            return Maps.asMap((NavigableSet) set, (Function) function);
        }
        return Maps.asMapSortedIgnoreNavigable(set, function);
    }

    static <E> SortedSet<E> setsFilterSortedSet(SortedSet<E> set, Predicate<? super E> predicate) {
        if (set instanceof NavigableSet) {
            return Sets.filter((NavigableSet) set, (Predicate) predicate);
        }
        return Sets.filterSortedIgnoreNavigable(set, predicate);
    }

    static <K, V> SortedMap<K, V> mapsFilterSortedMap(SortedMap<K, V> map, Predicate<? super Map.Entry<K, V>> predicate) {
        if (map instanceof NavigableMap) {
            return Maps.filterEntries((NavigableMap) map, (Predicate) predicate);
        }
        return Maps.filterSortedIgnoreNavigable(map, predicate);
    }

    private Platform() {
    }
}
