package defpackage;

import android.os.Bundle;
import java.util.Objects;
import java.util.regex.Pattern;
import org.chromium.base.ThreadUtils;
import org.chromium.components.payments.Address;
import org.chromium.components.payments.PaymentRequestUpdateEventListener;

/* renamed from: kz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3471kz0 implements Runnable {
    public final int F;
    public final Bundle G;
    public final AbstractC2710gZ H;

    public RunnableC3471kz0(int i, Bundle bundle, AbstractC2710gZ gZVar) {
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
            if (bundle == null || bundle.isEmpty()) {
                a2.e("Payment app returned invalid shipping address in response.", gZVar);
                return;
            }
            Address a3 = Address.a(bundle);
            Objects.requireNonNull(a3);
            if (Address.f10871a == null) {
                Address.f10871a = Pattern.compile("^[A-Z]{2}$");
            }
            if (!Address.f10871a.matcher(a3.b).matches()) {
                a2.e("Payment app returned invalid shipping address in response.", gZVar);
            } else if (a2.c() || (paymentRequestUpdateEventListener = a2.c) == null || !paymentRequestUpdateEventListener.c(AbstractC1094Ry0.a(a3))) {
                a2.e("Invalid state.", gZVar);
            } else {
                a2.b = gZVar;
            }
        }
    }
}
