package defpackage;

import android.view.View;

/* renamed from: gO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2689gO0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f9994a;
    public final Runnable b;

    public C2689gO0(boolean z, Runnable runnable) {
        this.f9994a = z;
        this.b = runnable;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        boolean z = this.f9994a;
        Runnable runnable = this.b;
        View view = (View) obj;
        C3372kO0.X(z, 2);
        runnable.run();
    }
}
