package defpackage;

import android.os.Bundle;
import java.util.Map;
import java.util.Objects;

/* renamed from: IO0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class IO0 {

    /* renamed from: a  reason: collision with root package name */
    public final JO0 f8223a;
    public final HO0 b = new HO0();

    public IO0(JO0 jo0) {
        this.f8223a = jo0;
    }

    public void a(Bundle bundle) {
        AbstractC3499l80 Q = this.f8223a.Q();
        if (((C4865t80) Q).b == EnumC3328k80.INITIALIZED) {
            Q.a(new C3706mK0(this.f8223a));
            HO0 ho0 = this.b;
            if (!ho0.c) {
                if (bundle != null) {
                    ho0.b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
                }
                Q.a(new EO0(ho0));
                ho0.c = true;
                return;
            }
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
    }

    public void b(Bundle bundle) {
        HO0 ho0 = this.b;
        Objects.requireNonNull(ho0);
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = ho0.b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        C4738sO0 a2 = ho0.f8156a.a();
        while (a2.hasNext()) {
            Map.Entry entry = (Map.Entry) a2.next();
            bundle2.putBundle((String) entry.getKey(), ((GO0) entry.getValue()).a());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}
