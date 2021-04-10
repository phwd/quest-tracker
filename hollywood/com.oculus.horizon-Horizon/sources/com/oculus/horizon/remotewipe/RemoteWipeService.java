package com.oculus.horizon.remotewipe;

import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import com.oculus.common.serial.BuildSerialUtil;
import com.oculus.horizon.remotewipe.RemoteWipeMethods;
import com.oculus.os.IRemoteWipeCallback;
import com.oculus.remotewipe.WipeRequester;
import com.oculus.remotewipe.WipeTelemetry;
import com.oculus.security.basecomponent.OculusPublicIntentService;
import javax.annotation.Nullable;

public class RemoteWipeService extends OculusPublicIntentService {
    public static final String FAIL_API_ERROR = "Error verifying pending remote wipe: ";
    public static final String FAIL_MAX_TRIES = "Failed all 3 retries";
    public static final String FAIL_NO_PENDING = "Strangely, backend claims no wipe pending.";
    public static final String FAIL_NO_REQUESTER = "RemoteWipeService called with no valid requester";
    public static final String FAIL_NULL_INTENT = "RemoteWipeService called with null intent";
    public static final String KEY_WIPE_REQUESTER = "remote_wipe_requester";
    public static final int MAX_ATTEMPT_NUM = 3;
    public static final String TAG = "RemoteWipeService";
    public AnonymousClass0QC _UL_mInjectionContext;
    public final IRemoteWipeCallback mCallback = new IRemoteWipeCallback.Stub() {
        /* class com.oculus.horizon.remotewipe.RemoteWipeService.AnonymousClass1 */

        @Override // com.oculus.os.IRemoteWipeCallback
        public final void onFailure(String str) {
            AnonymousClass0NO.A0E(RemoteWipeService.TAG, "remote wipe failed: %s", str);
            RemoteWipeService.A00(RemoteWipeService.this, null, str);
            RemoteWipeMethods.Methods methods = ((RemoteWipeMethods) AnonymousClass0J2.A03(1, 257, RemoteWipeService.this._UL_mInjectionContext)).mMethods;
            String A00 = BuildSerialUtil.A00();
            boolean z = false;
            if (str != null) {
                z = true;
            }
            methods.sendCompleted(A00, Boolean.valueOf(z), str);
        }

        @Override // com.oculus.os.IRemoteWipeCallback
        public final void onSuccess() {
            ((WipeTelemetry) AnonymousClass0J2.A03(3, 191, RemoteWipeService.this._UL_mInjectionContext)).A02(WipeTelemetry.EVENT_WIPE_SUCCESS, null);
            ((RemoteWipeMethods) AnonymousClass0J2.A03(1, 257, RemoteWipeService.this._UL_mInjectionContext)).mMethods.sendCompleted(BuildSerialUtil.A00(), false, null);
        }
    };

    public static void A00(@Nullable RemoteWipeService remoteWipeService, WipeRequester wipeRequester, @Nullable String str) {
        WipeTelemetry.A01(TAG, wipeRequester, str, null, false);
        WipeTelemetry.A00((WipeTelemetry) AnonymousClass0J2.A03(3, 191, remoteWipeService._UL_mInjectionContext), WipeTelemetry.EVENT_WIPE_FAIL, wipeRequester, str, null);
    }

    public RemoteWipeService() {
        super(TAG);
    }

    public static void A01(RemoteWipeService remoteWipeService, WipeRequester wipeRequester, @Nullable String str, Exception exc) {
        WipeTelemetry.A01(TAG, wipeRequester, str, exc, true);
        WipeTelemetry.A00((WipeTelemetry) AnonymousClass0J2.A03(3, 191, remoteWipeService._UL_mInjectionContext), WipeTelemetry.EVENT_WIPE_RETRY, wipeRequester, str, exc);
        ((RemoteWipeManager) AnonymousClass0J2.A03(2, 285, remoteWipeService._UL_mInjectionContext)).A8a(wipeRequester);
    }

    @Override // X.AnonymousClass1U8, com.oculus.security.basecomponent.OculusPublicIntentService
    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new AnonymousClass0QC(4, AnonymousClass0J2.get(this));
    }
}
