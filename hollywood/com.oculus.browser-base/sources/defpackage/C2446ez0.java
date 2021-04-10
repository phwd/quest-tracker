package defpackage;

import J.N;
import org.chromium.chrome.browser.payments.PaymentAppServiceBridge$PaymentAppServiceCallback;

/* renamed from: ez0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2446ez0 implements AbstractC1521Yy0 {
    @Override // defpackage.AbstractC1521Yy0
    public void a(AbstractC1460Xy0 xy0) {
        C1934bz0 bz0 = (C1934bz0) xy0;
        if (!bz0.d().e() && bz0.d().p().i() != null && bz0.d().p().d() != null && !bz0.d().t().g()) {
            N.MYEzy9ak(bz0.d().p(), bz0.d().r(), bz0.d().q(), bz0.d().s(), bz0.d().m(), new PaymentAppServiceBridge$PaymentAppServiceCallback(this, xy0, null));
        }
    }
}
