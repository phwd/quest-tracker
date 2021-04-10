package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.components.payments.PaymentRequestUpdateEventListener;

/* renamed from: iz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3129iz0 implements Runnable {
    public final int F;
    public final Bundle G;
    public final AbstractC2710gZ H;

    public RunnableC3129iz0(int i, Bundle bundle, AbstractC2710gZ gZVar) {
        this.F = i;
        this.G = bundle;
        this.H = gZVar;
    }

    public void run() {
        PaymentRequestUpdateEventListener paymentRequestUpdateEventListener;
        int i = this.F;
        Bundle bundle = this.G;
        AbstractC2710gZ gZVar = this.H;
        if (C3813mz0.a().b(i)) {
            C3813mz0 a2 = C3813mz0.a();
            Objects.requireNonNull(a2);
            Object obj = ThreadUtils.f10596a;
            if (bundle == null) {
                a2.e("Method data required.", gZVar);
                return;
            }
            String string = bundle.getString("methodName");
            if (TextUtils.isEmpty(string)) {
                a2.e("Method name required.", gZVar);
                return;
            }
            String string2 = bundle.getString("details", "{}");
            if (a2.c() || (paymentRequestUpdateEventListener = a2.c) == null || !paymentRequestUpdateEventListener.changePaymentMethodFromInvokedApp(string, string2)) {
                a2.e("Invalid state.", gZVar);
            } else {
                a2.b = gZVar;
            }
        }
    }
}
