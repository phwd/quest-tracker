package defpackage;

import java.util.Objects;

/* renamed from: ZB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class ZB0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC2145dC0 f9327a;

    public ZB0(AbstractC2145dC0 dc0) {
        this.f9327a = dc0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AbstractC2145dC0 dc0 = this.f9327a;
        Objects.requireNonNull(dc0);
        if (((Boolean) obj).booleanValue()) {
            dc0.n();
            dc0.M = true;
        } else if (dc0.M) {
            dc0.c();
        }
    }
}
