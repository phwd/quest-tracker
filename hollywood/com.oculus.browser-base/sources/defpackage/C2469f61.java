package defpackage;

import android.graphics.Bitmap;
import org.chromium.base.Callback;

/* renamed from: f61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2469f61 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f9898a;

    public C2469f61(Callback callback) {
        this.f9898a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Callback callback = this.f9898a;
        Bitmap bitmap = (Bitmap) obj;
        if (bitmap != null) {
            callback.onResult(bitmap);
        }
    }
}
