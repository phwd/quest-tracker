package defpackage;

import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import java.util.Objects;

/* renamed from: x6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5538x6 extends PhoneStateListener {

    /* renamed from: a  reason: collision with root package name */
    public ServiceState f11587a;
    public final /* synthetic */ C5708y6 b;

    public C5538x6(C5708y6 y6Var, AbstractC5368w6 w6Var) {
        this.b = y6Var;
    }

    public void onServiceStateChanged(ServiceState serviceState) {
        ServiceState serviceState2 = this.f11587a;
        if (serviceState2 == null || !serviceState2.equals(serviceState)) {
            this.f11587a = serviceState;
            C5708y6 y6Var = this.b;
            TelephonyManager b2 = C5708y6.b();
            Objects.requireNonNull(y6Var);
            if (b2 != null) {
                b2.getNetworkCountryIso();
                y6Var.b = b2.getNetworkOperator();
                y6Var.c = b2.getSimOperator();
            }
        }
    }
}
