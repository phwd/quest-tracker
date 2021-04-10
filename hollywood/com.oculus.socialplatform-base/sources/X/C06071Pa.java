package X;

import android.content.Context;
import javax.annotation.Nullable;

/* renamed from: X.1Pa  reason: invalid class name and case insensitive filesystem */
public final class C06071Pa implements AnonymousClass1YG {
    public final Context A00;

    @Override // X.AnonymousClass1YG
    @Nullable
    public final String A3O() {
        return AnonymousClass1PN.A00(this.A00, AnonymousClass1PL.MQTT_CONFIG).getString("analytics_endpoint", null);
    }

    @Override // X.AnonymousClass1YG
    @Nullable
    public final String A4T() {
        return AnonymousClass1PN.A00(this.A00, AnonymousClass1PL.MQTT_CONFIG).getString("host_name_ipv6", null);
    }

    public C06071Pa(Context context) {
        this.A00 = context;
    }
}
