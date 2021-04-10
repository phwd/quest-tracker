package defpackage;

import org.chromium.components.payments.JniPaymentApp;

/* renamed from: G40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class G40 implements Runnable {
    public final JniPaymentApp F;
    public final String G;

    public G40(JniPaymentApp jniPaymentApp, String str) {
        this.F = jniPaymentApp;
        this.G = str;
    }

    public void run() {
        JniPaymentApp jniPaymentApp = this.F;
        String str = this.G;
        AbstractC1277Uy0 uy0 = jniPaymentApp.p;
        if (uy0 != null) {
            ((EA0) uy0).z(str);
            jniPaymentApp.p = null;
        }
    }
}
