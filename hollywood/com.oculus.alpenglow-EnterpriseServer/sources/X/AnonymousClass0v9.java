package X;

import android.content.Context;
import javax.annotation.Nullable;

/* renamed from: X.0v9  reason: invalid class name */
public final class AnonymousClass0v9 implements AnonymousClass0vW {
    public final Context A00;

    @Override // X.AnonymousClass0vW
    @Nullable
    public final String A2v() {
        return C07680vm.A00(this.A00, EnumC07690vn.MQTT_CONFIG).getString("analytics_endpoint", null);
    }

    @Override // X.AnonymousClass0vW
    @Nullable
    public final String A45() {
        return C07680vm.A00(this.A00, EnumC07690vn.MQTT_CONFIG).getString("host_name_ipv6", null);
    }

    public AnonymousClass0v9(Context context) {
        this.A00 = context;
    }
}
