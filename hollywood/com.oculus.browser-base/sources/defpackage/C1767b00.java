package defpackage;

import android.graphics.Bitmap;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: b00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1767b00 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1938c00 f9506a;
    public final LZ b;
    public final Callback c;

    public C1767b00(C1938c00 c00, LZ lz, Callback callback) {
        this.f9506a = c00;
        this.b = lz;
        this.c = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1938c00 c00 = this.f9506a;
        LZ lz = this.b;
        Callback callback = this.c;
        Bitmap bitmap = (Bitmap) obj;
        Objects.requireNonNull(c00);
        String str = lz.f8424a;
        int i = lz.c;
        int i2 = lz.d;
        if (!(bitmap == null || c00.c == null)) {
            c00.c.d(c00.h(str, i, i2), bitmap);
        }
        callback.onResult(bitmap);
    }
}
