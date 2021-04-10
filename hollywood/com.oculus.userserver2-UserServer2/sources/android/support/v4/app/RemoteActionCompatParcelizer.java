package android.support.v4.app;

import X.AbstractC0056El;
import X.AnonymousClass2O;
import androidx.annotation.RestrictTo;
import androidx.core.app.RemoteActionCompat;

@RestrictTo({AnonymousClass2O.LIBRARY})
public final class RemoteActionCompatParcelizer extends androidx.core.app.RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(AbstractC0056El el) {
        return androidx.core.app.RemoteActionCompatParcelizer.read(el);
    }

    public static void write(RemoteActionCompat remoteActionCompat, AbstractC0056El el) {
        androidx.core.app.RemoteActionCompatParcelizer.write(remoteActionCompat, el);
    }
}
