package defpackage;

import org.chromium.components.payments.PaymentApp;

/* renamed from: Yd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1472Yd extends AbstractC1399Wy0 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f9282a;
    public final /* synthetic */ AbstractC1582Zy0 b;

    public C1472Yd(AbstractC1582Zy0 zy0) {
        this.b = zy0;
    }

    @Override // defpackage.AbstractC1460Xy0
    public AbstractC1582Zy0 d() {
        return this.b;
    }

    @Override // defpackage.AbstractC1460Xy0
    public void j(PaymentApp paymentApp) {
        paymentApp.k = true;
        C1870be beVar = (C1870be) paymentApp;
        if (beVar.H() == 0 && beVar.k) {
            this.f9282a = true;
        }
    }
}
