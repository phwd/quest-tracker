package android.support.v4.app;

import X.AnonymousClass02D;
import X.AnonymousClass0HI;
import androidx.annotation.RestrictTo;
import androidx.core.app.RemoteActionCompat;

@RestrictTo({AnonymousClass02D.LIBRARY})
public final class RemoteActionCompatParcelizer extends androidx.core.app.RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(AnonymousClass0HI r0) {
        return androidx.core.app.RemoteActionCompatParcelizer.read(r0);
    }

    public static void write(RemoteActionCompat remoteActionCompat, AnonymousClass0HI r1) {
        androidx.core.app.RemoteActionCompatParcelizer.write(remoteActionCompat, r1);
    }
}
