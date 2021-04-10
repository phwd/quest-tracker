package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: MZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class MZ {

    /* renamed from: a  reason: collision with root package name */
    public PZ f8483a;

    public MZ(MZ mz) {
        this.f8483a = mz.f8483a;
    }

    public static Bitmap g(Bitmap bitmap, int i, int i2) {
        return (bitmap == null || i <= 0 || i2 <= 0 || bitmap.getWidth() == i || bitmap.getHeight() == i2) ? bitmap : ThumbnailUtils.extractThumbnail(bitmap, i, i2, 2);
    }

    public abstract void a();

    public abstract void b();

    public abstract void c(LZ lz, Callback callback);

    public abstract void d(LZ lz, Callback callback);

    public abstract int e();

    public void f(String str, int i) {
        Objects.requireNonNull(this.f8483a);
        N.M1k4kLxJ(str, i);
    }

    public MZ(PZ pz) {
        this.f8483a = pz;
    }
}
