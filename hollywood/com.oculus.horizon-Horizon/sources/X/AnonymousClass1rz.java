package X;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@TargetApi(26)
/* renamed from: X.1rz  reason: invalid class name */
public final class AnonymousClass1rz extends AnonymousClass1qR {
    @Override // X.AnonymousClass1qR
    public final int A02(int i, int i2, BitmapFactory.Options options) {
        ColorSpace colorSpace = options.outColorSpace;
        if (colorSpace == null || !colorSpace.isWideGamut() || options.inPreferredConfig == Bitmap.Config.RGBA_F16) {
            return i * i2 * C09951qe.A00(options.inPreferredConfig);
        }
        return (i * i2) << 3;
    }

    public AnonymousClass1rz(AnonymousClass0Ox r1, int i, AnonymousClass0KB r3) {
        super(r1, i, r3);
    }
}
