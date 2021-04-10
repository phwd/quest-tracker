package X;

import android.os.Handler;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1z7  reason: invalid class name */
public final class AnonymousClass1z7 {
    public static void A00(AnonymousClass1zF r1, Handler handler) {
        String str;
        if (r1 == null) {
            str = "stateCallback cannot be null";
        } else if (handler != null) {
            handler.post(new AnonymousClass1zA(r1));
            return;
        } else {
            str = "handler cannot be null";
        }
        throw new IllegalArgumentException(str);
    }

    public static void A01(AnonymousClass1zF r1, Handler handler, AnonymousClass1lF r3) {
        String str;
        if (r1 == null) {
            str = "stateCallback cannot be null";
        } else if (handler != null) {
            handler.post(new AnonymousClass1z8(r1, r3));
            return;
        } else {
            str = "handler cannot be null";
        }
        throw new IllegalArgumentException(str);
    }
}
