package defpackage;

import android.graphics.Bitmap;
import org.chromium.base.Callback;

/* renamed from: OZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class OZ extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f8631a;
    public final LZ b;

    public OZ(Callback callback, LZ lz) {
        this.f8631a = callback;
        this.b = lz;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Callback callback = this.f8631a;
        LZ lz = this.b;
        callback.onResult(MZ.g((Bitmap) obj, lz.c, lz.d));
    }
}
