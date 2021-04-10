package defpackage;

import org.chromium.components.payments.JniPaymentApp;

/* renamed from: E40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class E40 implements Runnable {
    public final JniPaymentApp F;
    public final boolean G;

    public E40(JniPaymentApp jniPaymentApp, boolean z) {
        this.F = jniPaymentApp;
        this.G = z;
    }

    public void run() {
        JniPaymentApp jniPaymentApp = this.F;
        boolean z = this.G;
        AbstractC1216Ty0 ty0 = jniPaymentApp.o;
        if (ty0 != null) {
            ((EA0) ty0).y(z);
            jniPaymentApp.o = null;
        }
    }
}
