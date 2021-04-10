package com.facebook.tigon;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import java.io.IOException;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class TigonErrorException extends IOException {
    public final TigonError tigonError;

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.facebook.proguard.annotations.DoNotStrip
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TigonErrorException(com.facebook.tigon.TigonError r4) {
        /*
        // Method dump skipped, instructions count: 120
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.tigon.TigonErrorException.<init>(com.facebook.tigon.TigonError):void");
    }
}
