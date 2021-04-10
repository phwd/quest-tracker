package X;

import android.content.Context;
import android.content.res.Configuration;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1ru  reason: invalid class name and case insensitive filesystem */
public final class C11391ru {
    public Context A00;

    public final int A00() {
        Configuration configuration = this.A00.getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int i2 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i > 600 || ((i > 960 && i2 > 720) || (i > 720 && i2 > 960))) {
            return 5;
        }
        if (i >= 500) {
            return 4;
        }
        if (i > 640 && i2 > 480) {
            return 4;
        }
        if (i > 480 && i2 > 640) {
            return 4;
        }
        if (i >= 360) {
            return 3;
        }
        return 2;
    }

    public C11391ru(Context context) {
        this.A00 = context;
    }
}
