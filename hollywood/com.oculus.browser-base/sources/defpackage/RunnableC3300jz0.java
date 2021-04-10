package defpackage;

import android.text.TextUtils;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.components.payments.PaymentRequestUpdateEventListener;

/* renamed from: jz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3300jz0 implements Runnable {
    public final int F;
    public final String G;
    public final AbstractC2710gZ H;

    public RunnableC3300jz0(int i, String str, AbstractC2710gZ gZVar) {
        this.F = i;
        this.G = str;
        this.H = gZVar;
    }

    public void run() {
        PaymentRequestUpdateEventListener paymentRequestUpdateEventListener;
        int i = this.F;
        String str = this.G;
        AbstractC2710gZ gZVar = this.H;
        if (C3813mz0.a().b(i)) {
            C3813mz0 a2 = C3813mz0.a();
            Objects.requireNonNull(a2);
            Object obj = ThreadUtils.f10596a;
            if (TextUtils.isEmpty(str)) {
                a2.e("Shipping option identifier required.", gZVar);
            } else if (a2.c() || (paymentRequestUpdateEventListener = a2.c) == null || !paymentRequestUpdateEventListener.changeShippingOptionFromInvokedApp(str)) {
                a2.e("Invalid state.", gZVar);
            } else {
                a2.b = gZVar;
            }
        }
    }
}
