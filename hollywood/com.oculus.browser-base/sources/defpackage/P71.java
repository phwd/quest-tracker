package defpackage;

import java.util.Objects;

/* renamed from: P71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class P71 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2475f81 f8671a;

    public P71(C2475f81 f81) {
        this.f8671a = f81;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2475f81 f81 = this.f8671a;
        Boolean bool = (Boolean) obj;
        Objects.requireNonNull(f81);
        f81.g((bool == null || !bool.booleanValue()) ? ((AbstractC0246Ea1) f81.e).k() : -1);
    }
}
