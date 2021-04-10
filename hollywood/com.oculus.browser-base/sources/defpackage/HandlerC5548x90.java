package defpackage;

import android.os.Looper;
import android.os.Message;
import java.util.Objects;
import org.chromium.device.geolocation.LocationProviderAdapter;

/* renamed from: x90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HandlerC5548x90 extends ZB1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5718y90 f11595a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC5548x90(C5718y90 y90, Looper looper) {
        super(looper);
        this.f11595a = y90;
    }

    public final void handleMessage(Message message) {
        boolean z = true;
        if (message.what != 1) {
            z = false;
        }
        SE0.a(z);
        C2835hE1 he1 = (C2835hE1) message.obj;
        Object obj = this.f11595a.b;
        if (obj == null) {
            Objects.requireNonNull(he1);
            return;
        }
        try {
            Objects.requireNonNull(he1);
            C0976Qa0 qa0 = (C0976Qa0) ((AbstractC0489Ia0) obj);
            LocationProviderAdapter.b(he1.f10059a);
        } catch (RuntimeException e) {
            Objects.requireNonNull(he1);
            throw e;
        }
    }
}
