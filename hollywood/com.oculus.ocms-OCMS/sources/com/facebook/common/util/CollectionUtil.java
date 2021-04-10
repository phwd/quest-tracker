package com.facebook.common.util;

import androidx.core.util.Pair;
import com.facebook.infer.annotation.FalseOnNull;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.TrueOnNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class CollectionUtil {
    @TrueOnNull
    public static <T> boolean isNullOrEmpty(@Nullable Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    @FalseOnNull
    public static <T> boolean isNotNullOrEmpty(@Nullable Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static <T> void addAll(Collection<? super T> collection, @Nullable Collection<? extends T> collection2) {
        if (collection2 != null) {
            collection.addAll(collection2);
        }
    }

    public static <T> int size(@Nullable Collection<T> collection) {
        if (isNullOrEmpty(collection)) {
            return 0;
        }
        return collection.size();
    }

    public static <T> List<Pair<T, T>> pairsOf(List<T> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < list.size(); i3++) {
                arrayList.add(Pair.create(list.get(i), list.get(i3)));
            }
            i = i2;
        }
        return arrayList;
    }
}
