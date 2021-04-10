package com.facebook.quicklog;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class QplPointNotFoundException extends IllegalArgumentException {
    public final int instanceKey;
    @Nullable
    public final String[] knownPoints;
    public final int markerId;

    public QplPointNotFoundException(String str, @Nullable String[] strArr, int i, int i2) {
        super(str);
        this.knownPoints = strArr;
        this.markerId = i;
        this.instanceKey = i2;
    }
}
