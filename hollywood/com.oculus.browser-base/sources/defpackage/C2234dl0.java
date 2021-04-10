package defpackage;

import java.util.Objects;

/* renamed from: dl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2234dl0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2746gl0 f9803a;
    public final UH0 b;

    public C2234dl0(C2746gl0 gl0, UH0 uh0) {
        this.f9803a = gl0;
        this.b = uh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2746gl0 gl0 = this.f9803a;
        UH0 uh0 = this.b;
        Objects.requireNonNull(gl0);
        gl0.b(uh0, ((Integer) obj).intValue());
    }
}
