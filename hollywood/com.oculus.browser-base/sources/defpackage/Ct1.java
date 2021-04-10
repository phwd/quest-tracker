package defpackage;

import android.graphics.Bitmap;
import org.chromium.base.Callback;

/* renamed from: Ct1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Ct1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f7846a;

    public Ct1(Callback callback) {
        this.f7846a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2901hg hgVar = (C2901hg) obj;
        this.f7846a.onResult(hgVar == null ? null : new RunnableC2388eg(hgVar, Bitmap.Config.ARGB_8888));
    }
}
