package com.oculus.alpenglow.remotewipe;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
import X.C04910hv;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.facebook.infer.annotation.Nullsafe;
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
@Nullsafe(Nullsafe.Mode.LOCAL)
public class RemoteWipeManager implements IRemoteWipeManager {
    public static final String REMOTE_WIPE_SERVICE = "com.oculus.alpenglow.remotewipe.RemoteWipeService";
    public static final long RETRY_INTERVAL = TimeUnit.SECONDS.toMillis(30);
    public static final String TAG = "RemoteWipeManager";
    public AnonymousClass0R7 _UL_mInjectionContext;

    public static void A00(RemoteWipeManager remoteWipeManager, WipeRequester wipeRequester) {
        ((WipeTelemetry) AnonymousClass0Lh.A03(1, 36, remoteWipeManager._UL_mInjectionContext)).A02(WipeTelemetry.EVENT_WIPE_REQUESTED, wipeRequester);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.alpenglow", REMOTE_WIPE_SERVICE));
        intent.putExtra(RemoteWipeService.KEY_WIPE_REQUESTER, wipeRequester);
        if (C04910hv.A00().A02().A00(intent, (Context) AnonymousClass0Lh.A03(0, 4, remoteWipeManager._UL_mInjectionContext)) == null) {
            WipeTelemetry.A01(TAG, wipeRequester, "Failed to send intent to RemoteWipeService.", null, false);
            WipeTelemetry.A00((WipeTelemetry) AnonymousClass0Lh.A03(1, 36, remoteWipeManager._UL_mInjectionContext), WipeTelemetry.EVENT_WIPE_FAIL, wipeRequester, "Failed to send intent to RemoteWipeService.", null);
        }
    }

    @Override // com.oculus.remotewipe.IRemoteWipeManager
    public final void A7f(final WipeRequester wipeRequester) {
        wipeRequester.mAttemptNum++;
        OculusThreadExecutor.A00().mExecutorService.A7Y(new Runnable() {
            /* class com.oculus.alpenglow.remotewipe.RemoteWipeManager.AnonymousClass1 */

            public final void run() {
                RemoteWipeManager.A00(RemoteWipeManager.this, wipeRequester);
            }
        }, (long) ((int) RETRY_INTERVAL), TimeUnit.MILLISECONDS);
    }

    @Inject
    public RemoteWipeManager(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(2, r3);
    }
}
