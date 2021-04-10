package X;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import javax.annotation.Nullable;

/* renamed from: X.0bD  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02700bD<EndpointType> {
    public final AbstractC02690bC A00;

    public boolean A00(Context context, EndpointType endpointtype, Intent intent) {
        return A01(context, endpointtype, intent, null);
    }

    public boolean A01(Context context, EndpointType endpointtype, Intent intent, @Nullable C09281ag r12) {
        AbstractC02690bC r1 = this.A00;
        if (!r1.A8y()) {
            return true;
        }
        if (!C09241ac.A00(endpointtype, intent, context, r12, r1.A3G())) {
            C02680bB[] A3g = r1.A3g();
            for (C02680bB r7 : A3g) {
                String str = r7.A02;
                if (str == null || str.equals(endpointtype.getClass().getName())) {
                    IntentFilter intentFilter = r7.A01;
                    if (intentFilter != null) {
                        boolean z = false;
                        if (intentFilter.match(r7.A00, intent, false, "TAG") > 0) {
                            z = true;
                        }
                        if (r7.A03) {
                            if (!z) {
                            }
                        } else if (z) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public AbstractC02700bD(AbstractC02690bC r1) {
        this.A00 = r1;
    }
}
