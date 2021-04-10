package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
public enum ExifOrientation {
    UNDEFINED(0),
    NORMAL(1),
    FLIP_HORIZONTAL(2),
    ROTATE_180(3),
    FLIP_VERTICAL(4),
    TRANSPOSE(5),
    ROTATE_90(6),
    TRANSVERSE(7),
    ROTATE_270(8);
    
    public final int exifValue;

    public static ExifOrientation fromExifInterfaceOrientation(int i) {
        ExifOrientation[] values = values();
        for (ExifOrientation exifOrientation : values) {
            if (i == exifOrientation.exifValue) {
                return exifOrientation;
            }
        }
        throw new IllegalArgumentException("Invalid ExifInterface Orientation: " + i);
    }

    private ExifOrientation(int i) {
        this.exifValue = i;
    }
}
