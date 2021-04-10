package defpackage;

import java.util.Objects;

/* renamed from: wr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5493wr0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C6003zr0 f11574a;

    public C5493wr0(C6003zr0 zr0) {
        this.f11574a = zr0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Runnable runnable;
        C6003zr0 zr0 = this.f11574a;
        Objects.requireNonNull(zr0);
        if (!((Boolean) obj).booleanValue() && (runnable = zr0.k) != null) {
            runnable.run();
            zr0.k = null;
        }
    }
}
