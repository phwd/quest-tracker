package android.support.v4.os;

import android.os.CancellationSignal;

/* access modifiers changed from: package-private */
public class CancellationSignalCompatJellybean {
    CancellationSignalCompatJellybean() {
    }

    public static Object create() {
        return new CancellationSignal();
    }

    public static void cancel(Object obj) {
        ((CancellationSignal) obj).cancel();
    }
}
