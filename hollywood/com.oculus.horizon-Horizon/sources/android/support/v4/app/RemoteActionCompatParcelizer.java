package android.support.v4.app;

import X.AnonymousClass02C;
import X.AnonymousClass0CX;
import androidx.annotation.RestrictTo;
import androidx.core.app.RemoteActionCompat;

@RestrictTo({AnonymousClass02C.LIBRARY})
public final class RemoteActionCompatParcelizer extends androidx.core.app.RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(AnonymousClass0CX r0) {
        return androidx.core.app.RemoteActionCompatParcelizer.read(r0);
    }

    public static void write(RemoteActionCompat remoteActionCompat, AnonymousClass0CX r1) {
        androidx.core.app.RemoteActionCompatParcelizer.write(remoteActionCompat, r1);
    }
}
