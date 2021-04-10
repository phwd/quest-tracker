package com.facebook.common.util;

import android.util.SparseArray;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class SparseArrayUtil {
    private SparseArrayUtil() {
    }

    public static int[] getKeys(SparseArray sparseArray) {
        int size = sparseArray.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = sparseArray.keyAt(i);
        }
        return iArr;
    }
}
