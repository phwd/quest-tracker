package X;

import android.content.Context;
import com.facebook.infer.annotation.SynchronizedCollection;
import com.facebook.infer.annotation.ThreadSafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
/* renamed from: X.0Nf  reason: invalid class name */
public final class AnonymousClass0Nf {
    @SynchronizedCollection
    public static final Map<String, Integer> A00 = Collections.synchronizedMap(new HashMap());

    @Deprecated
    public static boolean A01(Context context, String str) {
        if (A00(context, str, 0) != 1) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005d A[SYNTHETIC, Splitter:B:18:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b1 A[SYNTHETIC, Splitter:B:40:0x00b1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int A00(android.content.Context r9, java.lang.String r10, int r11) {
        /*
        // Method dump skipped, instructions count: 202
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Nf.A00(android.content.Context, java.lang.String, int):int");
    }
}
