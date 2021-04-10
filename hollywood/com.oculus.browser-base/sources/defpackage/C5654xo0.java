package defpackage;

import android.app.Activity;

/* renamed from: xo0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5654xo0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0038Ao0 f11636a;

    public C5654xo0(C0038Ao0 ao0) {
        this.f11636a = ao0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0038Ao0 ao0 = this.f11636a;
        ao0.i0();
        ao0.K = (Activity) obj;
        ao0.j0();
    }
}
