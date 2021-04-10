package X;

import android.os.StrictMode;
import com.facebook.infer.annotation.Nullsafe;
import java.util.UUID;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0KD  reason: invalid class name */
public final class AnonymousClass0KD {
    public static UUID A00() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return UUID.randomUUID();
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
}
