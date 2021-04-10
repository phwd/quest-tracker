package androidx.core.os;

import android.os.Build;
import android.os.Trace;
import androidx.annotation.NonNull;

public final class TraceCompat {
    public static void beginSection(@NonNull String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.beginSection(str);
        }
    }

    public static void endSection() {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }

    private TraceCompat() {
    }
}
