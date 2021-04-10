package X;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.soloader.DoNotOptimize;

@DoNotOptimize
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1iD  reason: invalid class name */
public final class AnonymousClass1iD {
    @DoNotOptimize
    @TargetApi(26)
    public static final boolean A00(Bitmap.Config config) {
        if (config == Bitmap.Config.HARDWARE) {
            return true;
        }
        return false;
    }
}
