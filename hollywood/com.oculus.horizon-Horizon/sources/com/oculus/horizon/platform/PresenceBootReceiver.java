package com.oculus.horizon.platform;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0b9;
import android.content.Context;
import android.content.Intent;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PresenceBootReceiver extends OculusPublicBroadcastReceiver {
    public AnonymousClass0QC _UL_mInjectionContext;

    public PresenceBootReceiver() {
        super("android.intent.action.BOOT_COMPLETED");
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, PresenceBootReceiver presenceBootReceiver) {
        presenceBootReceiver._UL_mInjectionContext = new AnonymousClass0QC(1, r2);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public void onReceive(Context context, Intent intent, AnonymousClass0b9 r6) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), this);
        ((PresenceManager) AnonymousClass0J2.A03(0, 91, this._UL_mInjectionContext)).onLockStateChange();
    }
}
