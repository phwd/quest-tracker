package com.oculus.auth.e2etestharness;

import X.C02800bY;
import X.C02930bl;
import android.content.Context;
import android.content.Intent;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.authapi.OVRAuth;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class CallerInfoProviderImpl implements OVRAuth.CallerInfoProvider {
    public final Context mContext;

    @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
    public Intent attachCallerInfo(Intent intent) {
        try {
            C02800bY.A02(intent, this.mContext, null);
            return intent;
        } catch (C02930bl e) {
            throw new RuntimeException(e);
        }
    }

    public CallerInfoProviderImpl(Context context) {
        this.mContext = context;
    }
}
