package X;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@TargetApi(26)
/* renamed from: X.1i7  reason: invalid class name */
public final class AnonymousClass1i7 extends AbstractC09481i5 {
    @Override // X.AbstractC09481i5
    public final int A02(int i, int i2, BitmapFactory.Options options) {
        ColorSpace colorSpace = options.outColorSpace;
        if (colorSpace == null || !colorSpace.isWideGamut() || options.inPreferredConfig == Bitmap.Config.RGBA_F16) {
            return i * i2 * C01110Pz.A00(options.inPreferredConfig);
        }
        return (i * i2) << 3;
    }

    public AnonymousClass1i7(AnonymousClass0VL r1, int i, AnonymousClass0WB r3) {
        super(r1, i, r3);
    }
}
