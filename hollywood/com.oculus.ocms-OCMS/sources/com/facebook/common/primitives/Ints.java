package com.facebook.common.primitives;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Ints {
    private Ints() {
    }

    public static int[] toArray(Collection<Integer> collection) {
        if (collection instanceof List) {
            return toArray((List<Integer>) ((List) collection));
        }
        return toArrayWithIterator(collection);
    }

    public static int[] toArray(List<Integer> list) {
        if (list instanceof RandomAccess) {
            return toArrayWithRandomAccess(list);
        }
        return toArrayWithIterator(list);
    }

    private static int[] toArrayWithIterator(Collection<Integer> collection) {
        int[] iArr = new int[collection.size()];
        int i = 0;
        for (Integer num : collection) {
            iArr[i] = num.intValue();
            i++;
        }
        return iArr;
    }

    private static int[] toArrayWithRandomAccess(List<Integer> list) {
        int[] iArr = new int[list.size()];
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            iArr[i] = list.get(i).intValue();
        }
        return iArr;
    }
}
