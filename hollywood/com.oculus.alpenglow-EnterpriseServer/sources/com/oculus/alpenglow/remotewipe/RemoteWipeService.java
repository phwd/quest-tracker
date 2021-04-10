package com.oculus.alpenglow.remotewipe;

import X.AnonymousClass08h;
import X.AnonymousClass0Cg;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0P6;
import X.AnonymousClass0P8;
import X.AnonymousClass0R7;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.alpenglow.graphql.calls.HWMEntPersistedConfigMutationRequest;
import com.oculus.alpenglow.graphql.calls.RemoteWipeStatus;
import com.oculus.alpenglow.graphql.enums.GraphQLRemoteWipeStatus;
import com.oculus.alpenglow.remotewipe.UpdateRemoteWipeStatusMutationImpl;
import com.oculus.alpenglow.remotewipe.UpdateRemoteWipeStatusResponse;
import com.oculus.os.IRemoteWipeCallback;
import com.oculus.remotewipe.WipeRequester;
import com.oculus.remotewipe.WipeTelemetry;
import com.oculus.security.basecomponent.OculusPublicIntentService;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class RemoteWipeService extends OculusPublicIntentService {
    public static final String FAIL_MAX_TRIES = "Failed all 3 retries";
    public static final String FAIL_NO_REQUESTER = "RemoteWipeService called with no valid requester";
    public static final String FAIL_NULL_INTENT = "RemoteWipeService called with null intent";
    public static final String KEY_WIPE_REQUESTER = "enterprise_remote_wipe_requester";
    public static final int MAX_ATTEMPT_NUM = 3;
    public static final String TAG = "RemoteWipeService";
    public AnonymousClass0R7 _UL_mInjectionContext;
    public final IRemoteWipeCallback mCallback = new IRemoteWipeCallback.Stub() {
        /* class com.oculus.alpenglow.remotewipe.RemoteWipeService.AnonymousClass1 */

        @Override // com.oculus.os.IRemoteWipeCallback
        public final void onFailure(String str) {
            AnonymousClass0NK.A06(RemoteWipeService.TAG, "remote wipe failed: %s", str);
            RemoteWipeService.A01(RemoteWipeService.this, RemoteWipeStatus.FAILURE);
            RemoteWipeService.A00(RemoteWipeService.this, null, str);
        }

        @Override // com.oculus.os.IRemoteWipeCallback
        public final void onSuccess() {
            RemoteWipeService.A01(RemoteWipeService.this, RemoteWipeStatus.SUCCESS);
            ((WipeTelemetry) AnonymousClass0Lh.A03(1, 36, RemoteWipeService.this._UL_mInjectionContext)).A02(WipeTelemetry.EVENT_WIPE_SUCCESS, null);
        }
    };

    public static void A00(@Nullable RemoteWipeService remoteWipeService, WipeRequester wipeRequester, @Nullable String str) {
        WipeTelemetry.A01(TAG, wipeRequester, str, null, false);
        WipeTelemetry.A00((WipeTelemetry) AnonymousClass0Lh.A03(1, 36, remoteWipeService._UL_mInjectionContext), WipeTelemetry.EVENT_WIPE_FAIL, wipeRequester, str, null);
    }

    public static void A01(RemoteWipeService remoteWipeService, String str) {
        HWMEntPersistedConfigMutationRequest hWMEntPersistedConfigMutationRequest = new HWMEntPersistedConfigMutationRequest();
        hWMEntPersistedConfigMutationRequest.A05("remote_wipe_status", str);
        ((AnonymousClass0P6) AnonymousClass0Lh.A03(2, 51, remoteWipeService._UL_mInjectionContext)).A2X(new UpdateRemoteWipeStatusMutationImpl.Builder().A87(hWMEntPersistedConfigMutationRequest).A1a(), new AnonymousClass0Cg<AnonymousClass0P8<UpdateRemoteWipeStatusResponse>>() {
            /* class com.oculus.alpenglow.remotewipe.RemoteWipeService.AnonymousClass2 */

            @Override // X.AnonymousClass0Cg
            public final void onFailure(Throwable th) {
                AnonymousClass0NK.A04(RemoteWipeService.TAG, "error updating remote wipe status", th);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // X.AnonymousClass0Cg
            public final void onSuccess(AnonymousClass0P8<UpdateRemoteWipeStatusResponse> r6) {
                UpdateRemoteWipeStatusResponse.UpdateRemoteWipeStatus A4n;
                UpdateRemoteWipeStatusResponse.UpdateRemoteWipeStatus.PersistedConfig A4G;
                UpdateRemoteWipeStatusResponse A4R = r6.A4R();
                if (A4R == null || (A4n = A4R.A4n()) == null || (A4G = A4n.A4G()) == null) {
                    AnonymousClass0NK.A01(RemoteWipeService.TAG, "Unknown error happened when mutate remote wipe status");
                    RemoteWipeService.A00(RemoteWipeService.this, null, "Unknown error from graphql response");
                    return;
                }
                GraphQLRemoteWipeStatus A4O = A4G.A4O();
                String A3V = A4n.A3V();
                if (A4O == null && A3V != null) {
                    AnonymousClass0NK.A06(RemoteWipeService.TAG, "Mutation of remote wipe status failed with error=%s", A3V);
                    RemoteWipeService.A00(RemoteWipeService.this, null, A3V);
                }
            }
        }, AnonymousClass08h.INSTANCE);
    }

    @Override // X.AnonymousClass133, com.oculus.security.basecomponent.OculusPublicIntentService
    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new AnonymousClass0R7(3, AnonymousClass0Lh.get(this));
    }
}
