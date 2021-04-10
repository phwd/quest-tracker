package X;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.soloader.DoNotOptimize;

@DoNotOptimize
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1sr  reason: invalid class name and case insensitive filesystem */
public final class C10451sr {
    @DoNotOptimize
    @TargetApi(26)
    public static final boolean A00(Bitmap.Config config) {
        if (config == Bitmap.Config.HARDWARE) {
            return true;
        }
        return false;
    }
}
