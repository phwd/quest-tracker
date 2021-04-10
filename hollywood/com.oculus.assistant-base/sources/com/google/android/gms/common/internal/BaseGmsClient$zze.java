package com.google.android.gms.common.internal;

import X.Gy;
import X.RO;
import X.RZ;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import com.google.android.gms.internal.common.zza;

public final class BaseGmsClient$zze extends zza implements IGmsCallbacks {
    public RO A00;
    public final int A01;

    public final void A02(int i, IBinder iBinder, Bundle bundle) {
        RZ.A02(this.A00, "onPostInitComplete can be called only once per call to getRemoteService");
        RO ro = this.A00;
        int i2 = this.A01;
        Handler handler = ro.A0A;
        handler.sendMessage(handler.obtainMessage(1, i2, -1, new Gy(ro, i, iBinder, bundle)));
        this.A00 = null;
    }

    public BaseGmsClient$zze() {
        super("com.google.android.gms.common.internal.IGmsCallbacks");
    }

    public BaseGmsClient$zze(RO ro, int i) {
        this();
        this.A00 = ro;
        this.A01 = i;
    }
}
