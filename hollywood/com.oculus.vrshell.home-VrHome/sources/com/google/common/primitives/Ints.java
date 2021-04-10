package com.google.common.primitives;

import com.oculus.library.database.contract.LibraryDBContract;

public final class Ints {
    public static int saturatedCast(long value) {
        if (value > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (value < LibraryDBContract.VERSION_NOT_INSTALLED) {
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }
}
