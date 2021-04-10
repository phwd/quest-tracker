package com.oculus.horizon.remotewipe;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.C02600ao;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.facebook.inject.BindAs;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.remotewipe.IRemoteWipeManager;
import com.oculus.remotewipe.WipeRequester;
import com.oculus.remotewipe.WipeTelemetry;
import java.util.concurrent.TimeUnit;

@BindAs(IRemoteWipeManager.class)
@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_remotewipe_WipeTelemetry_ULSEP_BINDING_ID"})
public class RemoteWipeManager implements IRemoteWipeManager {
    public static final long RETRY_INTERVAL = TimeUnit.SECONDS.toMillis(30);
    public static final String TAG = "RemoteWipeManager";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static void A00(RemoteWipeManager remoteWipeManager, WipeRequester wipeRequester) {
        ((WipeTelemetry) AnonymousClass0J2.A03(1, 191, remoteWipeManager._UL_mInjectionContext)).A02(WipeTelemetry.EVENT_WIPE_REQUESTED, wipeRequester);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", RemoteWipeService.class.getCanonicalName()));
        intent.putExtra(RemoteWipeService.KEY_WIPE_REQUESTER, wipeRequester);
        if (C02600ao.A00().A06().A00(intent, (Context) AnonymousClass0J2.A03(0, 294, remoteWipeManager._UL_mInjectionContext)) == null) {
            WipeTelemetry.A01(TAG, wipeRequester, "Failed to send intent to RemoteWipeService.", null, false);
            WipeTelemetry.A00((WipeTelemetry) AnonymousClass0J2.A03(1, 191, remoteWipeManager._UL_mInjectionContext), WipeTelemetry.EVENT_WIPE_FAIL, wipeRequester, "Failed to send intent to RemoteWipeService.", null);
        }
    }

    @Override // com.oculus.remotewipe.IRemoteWipeManager
    public final void A8a(final WipeRequester wipeRequester) {
        wipeRequester.mAttemptNum++;
        OculusThreadExecutor.A00().mExecutorService.A8S(new Runnable() {
            /* class com.oculus.horizon.remotewipe.RemoteWipeManager.AnonymousClass1 */

            public final void run() {
                RemoteWipeManager.A00(RemoteWipeManager.this, wipeRequester);
            }
        }, (long) ((int) RETRY_INTERVAL), TimeUnit.MILLISECONDS);
    }

    @Inject
    public RemoteWipeManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }
}
