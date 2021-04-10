package X;

import android.os.Handler;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1z6  reason: invalid class name */
public final class AnonymousClass1z6 {
    public static void A00(AbstractC11131xk r1, Handler handler) {
        String str;
        if (r1 == null) {
            str = "stateCallback cannot be null";
        } else if (handler != null) {
            handler.post(new AnonymousClass1z9(r1));
            return;
        } else {
            str = "handler cannot be null";
        }
        throw new IllegalArgumentException(str);
    }

    public static void A01(@Nullable AbstractC11131xk r1, Handler handler, Throwable th) {
        String str;
        if (r1 == null) {
            str = "stateCallback cannot be null";
        } else if (handler != null) {
            handler.post(new AnonymousClass1z5(r1, th));
            return;
        } else {
            str = "handler cannot be null";
        }
        throw new IllegalArgumentException(str);
    }
}
