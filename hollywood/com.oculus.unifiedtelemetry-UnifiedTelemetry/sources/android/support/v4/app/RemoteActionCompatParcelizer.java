package android.support.v4.app;

import X.AnonymousClass2C;
import X.CW;
import androidx.annotation.RestrictTo;
import androidx.core.app.RemoteActionCompat;

@RestrictTo({AnonymousClass2C.LIBRARY})
public final class RemoteActionCompatParcelizer extends androidx.core.app.RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(CW cw) {
        return androidx.core.app.RemoteActionCompatParcelizer.read(cw);
    }

    public static void write(RemoteActionCompat remoteActionCompat, CW cw) {
        androidx.core.app.RemoteActionCompatParcelizer.write(remoteActionCompat, cw);
    }
}
