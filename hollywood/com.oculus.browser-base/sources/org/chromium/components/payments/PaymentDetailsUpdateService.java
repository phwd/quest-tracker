package org.chromium.components.payments;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PaymentDetailsUpdateService extends Service {
    public final BinderC3642lz0 F = new BinderC3642lz0(this);

    public IBinder onBind(Intent intent) {
        if (!AbstractC3984nz0.a("AndroidAppPaymentUpdateEvents")) {
            return null;
        }
        return this.F;
    }
}
