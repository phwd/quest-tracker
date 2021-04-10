package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DoubleUtil {
    private static final float EPSILON = 1.0E-5f;

    public static boolean doublesEqual(double d, double d2) {
        if (Double.isNaN(d) || Double.isNaN(d2)) {
            if (!Double.isNaN(d) || !Double.isNaN(d2)) {
                return false;
            }
            return true;
        } else if (Math.abs(d2 - d) < 9.999999747378752E-6d) {
            return true;
        } else {
            return false;
        }
    }
}
