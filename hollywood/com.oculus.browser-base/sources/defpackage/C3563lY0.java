package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: lY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3563lY0 implements AbstractC0899Os0 {

    /* renamed from: a  reason: collision with root package name */
    public final C3905nY0 f10351a;
    public final WindowAndroid b;

    public C3563lY0(C3905nY0 ny0, WindowAndroid windowAndroid) {
        this.f10351a = ny0;
        this.b = windowAndroid;
    }

    @Override // defpackage.AbstractC0899Os0
    public void b(Exception exc) {
        C3905nY0 ny0 = this.f10351a;
        WindowAndroid windowAndroid = this.b;
        Objects.requireNonNull(ny0);
        C3324k7 k7Var = (C3324k7) exc;
        int i = k7Var.F.K;
        int i2 = 1;
        if (i == 17) {
            i2 = 2;
            ny0.f10496a.b();
        } else if (i == 36500) {
            i2 = 3;
            ny0.f10496a.b();
        } else if (i == 36501) {
            ny0.f10496a.b();
            i2 = 4;
        } else if (i == 36502) {
            N.MqHdTL15(ny0.f10496a.f10933a);
        } else if (i != 6) {
            AbstractC1220Ua0.f("SmsVerification", "Unexpected exception", exc);
        } else if (k7Var instanceof C2514fM0) {
            try {
                windowAndroid.E0(((C2514fM0) k7Var).F.M, new C3734mY0(ny0), null);
            } catch (Exception e) {
                AbstractC1220Ua0.a("SmsVerification", "Cannot launch user permission", e);
            }
        }
        AbstractC3364kK0.g("Blink.Sms.BackendAvailability", AbstractC5580xK0.a(i2), 4);
    }
}
