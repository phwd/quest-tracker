package defpackage;

import org.chromium.components.payments.JniPaymentApp;
import org.chromium.components.payments.PayerData;

/* renamed from: F40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class F40 implements Runnable {
    public final JniPaymentApp F;
    public final String G;
    public final String H;
    public final PayerData I;

    public F40(JniPaymentApp jniPaymentApp, String str, String str2, PayerData payerData) {
        this.F = jniPaymentApp;
        this.G = str;
        this.H = str2;
        this.I = payerData;
    }

    public void run() {
        JniPaymentApp jniPaymentApp = this.F;
        String str = this.G;
        String str2 = this.H;
        PayerData payerData = this.I;
        AbstractC1277Uy0 uy0 = jniPaymentApp.p;
        if (uy0 != null) {
            ((EA0) uy0).A(str, str2, payerData);
            jniPaymentApp.p = null;
        }
    }
}
