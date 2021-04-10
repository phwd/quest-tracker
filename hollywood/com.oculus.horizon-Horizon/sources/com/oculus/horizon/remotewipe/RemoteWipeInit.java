package com.oculus.horizon.remotewipe;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.common.init.INeedInit;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.http.core.base.ApiError;
import com.oculus.http.core.base.ApiException;
import com.oculus.remotewipe.Source;
import com.oculus.remotewipe.WipeRequester;
import java.util.concurrent.TimeUnit;

@Dependencies({"_UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_remotewipe_RemoteWipeManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_remotewipe_RemoteWipeMethods_ULSEP_BINDING_ID"})
public class RemoteWipeInit implements INeedInit {
    public static final int RETRIES_ON_BOOT = 4;
    public static final String TAG = "RemoteWipeInit";
    public final long BOOT_RETRY_INTERVAL_MS = TimeUnit.SECONDS.toMillis(5);
    public final long FOREVER_RETRY_INTERVAL_MS = TimeUnit.MINUTES.toMillis(5);
    public AnonymousClass0QC _UL_mInjectionContext;

    public class AggressivePoll implements Runnable {
        public final int mCurrentAttemptNum;

        public final void run() {
            RemoteWipeInit remoteWipeInit;
            long j;
            try {
                if (((RemoteWipeMethods) AnonymousClass0J2.A03(2, 257, RemoteWipeInit.this._UL_mInjectionContext)).A00()) {
                    RemoteWipeManager.A00((RemoteWipeManager) AnonymousClass0J2.A03(1, 285, RemoteWipeInit.this._UL_mInjectionContext), new WipeRequester(Source.COLD_START));
                }
            } catch (ApiException e) {
                if (e.mApiError.type == ApiError.Type.NETWORK_ERROR) {
                    int i = this.mCurrentAttemptNum;
                    if (i <= 4) {
                        remoteWipeInit = RemoteWipeInit.this;
                        j = remoteWipeInit.BOOT_RETRY_INTERVAL_MS;
                    } else {
                        remoteWipeInit = RemoteWipeInit.this;
                        j = remoteWipeInit.FOREVER_RETRY_INTERVAL_MS;
                    }
                    ((OculusThreadExecutor) AnonymousClass0J2.A03(0, 333, remoteWipeInit._UL_mInjectionContext)).mExecutorService.A8S(new AggressivePoll(i + 1), (long) ((int) j), TimeUnit.MILLISECONDS);
                    return;
                }
                AnonymousClass0NO.A0B(RemoteWipeInit.TAG, "Non-network error returned when querying for pending RemoteWipe", e);
            }
        }

        public AggressivePoll(int i) {
            this.mCurrentAttemptNum = i;
        }
    }

    @Override // com.oculus.common.init.INeedInit
    public final void init() {
        ((OculusThreadExecutor) AnonymousClass0J2.A03(0, 333, this._UL_mInjectionContext)).execute(new AggressivePoll(1));
    }

    @Inject
    public RemoteWipeInit(AbstractC06640p5 r5) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r5);
    }
}
