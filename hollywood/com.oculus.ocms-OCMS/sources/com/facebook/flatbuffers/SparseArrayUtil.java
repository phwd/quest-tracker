package com.facebook.flatbuffers;

import android.util.SparseArray;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SparseArrayUtil {
    public static int indexOfKeyOrNextLowest(SparseArray sparseArray, int i) {
        int indexOfKey = sparseArray.indexOfKey(i);
        return indexOfKey >= 0 ? indexOfKey : (-indexOfKey) - 2;
    }

    public static int indexOfKeyOrNextHighest(SparseArray sparseArray, int i) {
        int indexOfKey = sparseArray.indexOfKey(i);
        return indexOfKey >= 0 ? indexOfKey : (-indexOfKey) - 1;
    }
}
