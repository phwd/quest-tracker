package defpackage;

import org.chromium.base.Callback;

/* renamed from: wN0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5416wN0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final GN0 f11543a;

    public C5416wN0(GN0 gn0) {
        this.f11543a = gn0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        GN0 gn0 = this.f11543a;
        Boolean bool = (Boolean) obj;
        Callback callback = gn0.U;
        if (callback != null) {
            callback.onResult(bool);
        }
        gn0.h0.m(bool);
    }
}
