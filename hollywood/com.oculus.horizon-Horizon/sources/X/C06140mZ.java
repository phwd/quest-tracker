package X;

import android.content.Context;
import javax.annotation.Nullable;

/* renamed from: X.0mZ  reason: invalid class name and case insensitive filesystem */
public final class C06140mZ implements AnonymousClass0X4 {
    public final Context A00;

    @Override // X.AnonymousClass0X4
    @Nullable
    public final String A2y() {
        return AnonymousClass0WJ.A00(this.A00, AnonymousClass0WE.MQTT_CONFIG).getString("analytics_endpoint", null);
    }

    @Override // X.AnonymousClass0X4
    @Nullable
    public final String A3v() {
        return AnonymousClass0WJ.A00(this.A00, AnonymousClass0WE.MQTT_CONFIG).getString("host_name_ipv6", null);
    }

    public C06140mZ(Context context) {
        this.A00 = context;
    }
}
