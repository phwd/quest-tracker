package X;

import android.content.Context;
import com.facebook.infer.annotation.SynchronizedCollection;
import com.facebook.infer.annotation.ThreadSafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
/* renamed from: X.0OS  reason: invalid class name */
public final class AnonymousClass0OS {
    @SynchronizedCollection
    public static final Map<String, Integer> A00 = Collections.synchronizedMap(new HashMap());

    @Deprecated
    public static boolean A01(Context context, String str) {
        if (A00(context, str, 0) != 1) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005d A[Catch:{ all -> 0x00b7, all -> 0x00bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b3 A[SYNTHETIC, Splitter:B:41:0x00b3] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int A00(android.content.Context r11, java.lang.String r12, int r13) {
        /*
        // Method dump skipped, instructions count: 204
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0OS.A00(android.content.Context, java.lang.String, int):int");
    }
}
