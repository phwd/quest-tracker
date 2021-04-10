package com.oculus.auth.deviceownership;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QB;
import X.AnonymousClass0QC;
import X.AnonymousClass0b9;
import android.content.Context;
import android.content.Intent;
import com.oculus.auth.components.DeviceOwnershipHelper;
import com.oculus.security.basecomponent.OculusSystemSecureBroadcastReceiver;

public final class EnsureDeviceOwnershipBootReceiver extends OculusSystemSecureBroadcastReceiver implements AnonymousClass0QB {
    public static final String TAG = "EnsureDeviceOwnershipBootReceiver";
    public AnonymousClass0QC _UL_mInjectionContext;

    public EnsureDeviceOwnershipBootReceiver() {
        super("android.intent.action.BOOT_COMPLETED");
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, EnsureDeviceOwnershipBootReceiver ensureDeviceOwnershipBootReceiver) {
        ensureDeviceOwnershipBootReceiver._UL_mInjectionContext = new AnonymousClass0QC(2, r2);
    }

    public static final void _UL_injectMe(Context context, EnsureDeviceOwnershipBootReceiver ensureDeviceOwnershipBootReceiver) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), ensureDeviceOwnershipBootReceiver);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public void onReceive(Context context, Intent intent, AnonymousClass0b9 r6) {
        _UL_injectMe(context, this);
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            ((DeviceOwnershipHelper) AnonymousClass0J2.A03(0, 23, this._UL_mInjectionContext)).resetIsOwnershipSet();
            ((EnsureDeviceOwnershipJobScheduler) AnonymousClass0J2.A03(1, 395, this._UL_mInjectionContext)).scheduleJobIfNecessary();
        }
    }
}
