package X;

import android.os.Build;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.08K  reason: invalid class name */
public interface AnonymousClass08K {
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public static final boolean A00;

    static {
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 27) {
            z = true;
        }
        A00 = z;
    }
}
