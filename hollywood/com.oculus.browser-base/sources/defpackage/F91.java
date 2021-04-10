package defpackage;

import android.graphics.Bitmap;
import org.chromium.base.Callback;

/* renamed from: F91  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class F91 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f7996a;

    public F91(Callback callback) {
        this.f7996a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f7996a.onResult((Bitmap) obj);
    }
}
