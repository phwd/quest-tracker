package com.oculus.nux.ota;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.authapi.AuthCredentials;
import com.oculus.authapi.AuthServiceClient;
import com.oculus.os.FirstTimeNuxManager;
import com.oculus.osupdaterapi.OsUpdater;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class StateMachine extends Thread {
    private static final long ENDPOINT_ACTION_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(15);
    private static final long RETRY_DELAY_TO_NOTIFY_ENDPOINT_MS = TimeUnit.SECONDS.toMillis(10);
    private static final String TAG = ("NuxOta" + StateMachine.class.getSimpleName());
    private Context mContext;
    private StateMachineInternalState mInternalState;
    private NuxOta mNuxOta;
    private NuxOtaSettings mSettings;
    private int mSfxUpdateDoneResourceId;
    private Telemetry mTelemetry;
    private final Object mThreadLock = new Object();

    public StateMachine(Context context, NuxOta nuxOta, Telemetry telemetry, NuxOtaSettings nuxOtaSettings, int i) {
        super(TAG);
        this.mContext = context;
        this.mNuxOta = nuxOta;
        this.mTelemetry = telemetry;
        this.mSettings = nuxOtaSettings;
        this.mSfxUpdateDoneResourceId = i;
        this.mInternalState = new StateMachineInternalState(this.mSettings.isInEnterpriseMode(), isHighPriAppsUpdateComplete());
        initialize();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.nux.ota.StateMachine$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$nux$ota$NuxOtaState = new int[NuxOtaState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.oculus.nux.ota.NuxOtaState[] r0 = com.oculus.nux.ota.NuxOtaState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.nux.ota.StateMachine.AnonymousClass2.$SwitchMap$com$oculus$nux$ota$NuxOtaState = r0
                int[] r0 = com.oculus.nux.ota.StateMachine.AnonymousClass2.$SwitchMap$com$oculus$nux$ota$NuxOtaState     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.nux.ota.NuxOtaState r1 = com.oculus.nux.ota.NuxOtaState.OKAY_TO_REBOOT     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.nux.ota.StateMachine.AnonymousClass2.$SwitchMap$com$oculus$nux$ota$NuxOtaState     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.nux.ota.NuxOtaState r1 = com.oculus.nux.ota.NuxOtaState.OTA_READY     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.nux.ota.StateMachine.AnonymousClass2.$SwitchMap$com$oculus$nux$ota$NuxOtaState     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.nux.ota.NuxOtaState r1 = com.oculus.nux.ota.NuxOtaState.NO_OTA     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.nux.ota.StateMachine.AnonymousClass2.$SwitchMap$com$oculus$nux$ota$NuxOtaState     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.nux.ota.NuxOtaState r1 = com.oculus.nux.ota.NuxOtaState.NEW_DEVICE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.oculus.nux.ota.StateMachine.AnonymousClass2.$SwitchMap$com$oculus$nux$ota$NuxOtaState     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.oculus.nux.ota.NuxOtaState r1 = com.oculus.nux.ota.NuxOtaState.WAITING_FOR_REBOOT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = com.oculus.nux.ota.StateMachine.AnonymousClass2.$SwitchMap$com$oculus$nux$ota$NuxOtaState     // Catch:{ NoSuchFieldError -> 0x004b }
                com.oculus.nux.ota.NuxOtaState r1 = com.oculus.nux.ota.NuxOtaState.REBOOTING     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = com.oculus.nux.ota.StateMachine.AnonymousClass2.$SwitchMap$com$oculus$nux$ota$NuxOtaState     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.oculus.nux.ota.NuxOtaState r1 = com.oculus.nux.ota.NuxOtaState.WAITING_FOR_HIGH_PRI_APPS_DOWNLOAD     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = com.oculus.nux.ota.StateMachine.AnonymousClass2.$SwitchMap$com$oculus$nux$ota$NuxOtaState     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.oculus.nux.ota.NuxOtaState r1 = com.oculus.nux.ota.NuxOtaState.NOTIFY_ENDPOINT     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.nux.ota.StateMachine.AnonymousClass2.<clinit>():void");
        }
    }

    private void initialize() {
        int i = AnonymousClass2.$SwitchMap$com$oculus$nux$ota$NuxOtaState[this.mSettings.getState().ordinal()];
        if (i == 1) {
            this.mInternalState.setOkayToReboot(true);
        } else if (i == 2) {
            this.mInternalState.setOtaReady(true);
        } else if (i == 3) {
            this.mInternalState.setNoOta(true);
        }
    }

    public void run() {
        NuxOtaState state = this.mSettings.getState();
        while (state != NuxOtaState.COMPLETE) {
            boolean z = false;
            switch (AnonymousClass2.$SwitchMap$com$oculus$nux$ota$NuxOtaState[state.ordinal()]) {
                case 1:
                    z = onEnterOkayToRebootState();
                    break;
                case 2:
                    z = onEnterOtaReadyState();
                    break;
                case 3:
                    z = onEnterNoOtaState();
                    break;
                case 4:
                    z = onEnterNewDeviceState();
                    break;
                case 5:
                    z = onEnterWaitingForRebootState();
                    break;
                case 6:
                    z = onEnterRebootingState();
                    break;
                case 7:
                    z = onEnterWaitingForHighPriAppsDownloadState();
                    break;
                case 8:
                    z = onEnterNotifyEndpointState();
                    break;
            }
            if (!z) {
                state = this.mSettings.getState();
            } else {
                return;
            }
        }
        this.mNuxOta.maybeEnableWifiSleepMode();
        this.mNuxOta.cleanUp();
    }

    private void waitForEvent() {
        try {
            synchronized (this.mThreadLock) {
                this.mThreadLock.wait();
            }
        } catch (InterruptedException unused) {
            Log.e(TAG, "StateMachine interrupted.");
            interrupt();
        }
    }

    /* access modifiers changed from: protected */
    public void setState(NuxOtaState nuxOtaState) {
        this.mSettings.setState(this.mContext, nuxOtaState);
    }

    public boolean onEnterNewDeviceState() {
        Log.d(TAG, "Entering NEW_DEVICE state.");
        this.mNuxOta.ensureMarauderDeviceId();
        synchronized (this.mThreadLock) {
            while (this.mInternalState.stayInNewDeviceState()) {
                if (this.mNuxOta.isConnectedToNetworkWithInternetAccess()) {
                    this.mNuxOta.checkForUpdates();
                }
                waitForEvent();
            }
        }
        if (this.mInternalState.getOkayToReboot()) {
            setState(NuxOtaState.OKAY_TO_REBOOT);
            return false;
        } else if (this.mInternalState.getNoOta()) {
            setState(NuxOtaState.NO_OTA);
            return false;
        } else if (this.mInternalState.getOtaReady()) {
            setState(NuxOtaState.OTA_READY);
            return false;
        } else {
            Log.e(TAG, "Invalid flags in NEW_DEVICE state!");
            this.mTelemetry.recordError("Invalid flags in NEW_DEVICE state!");
            return false;
        }
    }

    public boolean onEnterNoOtaState() {
        Log.d(TAG, "Entering NO_OTA state.");
        return handleNoOtaOrReady();
    }

    public boolean onEnterOtaReadyState() {
        Log.d(TAG, "Entering OTA_READY state.");
        return handleNoOtaOrReady();
    }

    /* access modifiers changed from: protected */
    public boolean handleNoOtaOrReady() {
        this.mNuxOta.updateOsUpdateProgress(100.0f);
        synchronized (this.mThreadLock) {
            while (this.mInternalState.stayInNoOtaOrOtaReadyState()) {
                waitForEvent();
            }
        }
        setState(NuxOtaState.WAITING_FOR_REBOOT);
        return false;
    }

    public boolean onEnterOkayToRebootState() {
        Log.d(TAG, "Entering OKAY_TO_REBOOT state.");
        synchronized (this.mThreadLock) {
            while (this.mInternalState.stayInOkayToRebootState()) {
                if (this.mNuxOta.isConnectedToNetworkWithInternetAccess()) {
                    this.mNuxOta.checkForUpdates();
                }
                waitForEvent();
            }
        }
        if (this.mInternalState.getIsOtaDownloadStalled()) {
            forceReboot();
            return true;
        }
        setState(NuxOtaState.WAITING_FOR_REBOOT);
        return false;
    }

    public boolean onEnterWaitingForRebootState() {
        Log.d(TAG, "Entering WAITING_FOR_REBOOT state.");
        this.mNuxOta.disableBleAdvertising();
        delayReboot();
        rebootAndApplyUpdate();
        return true;
    }

    public boolean onEnterRebootingState() {
        Log.d(TAG, "Entering REBOOTING state.");
        setState(NuxOtaState.WAITING_FOR_HIGH_PRI_APPS_DOWNLOAD);
        return false;
    }

    public boolean onEnterWaitingForHighPriAppsDownloadState() {
        Log.d(TAG, "Entering WAITING_FOR_HIGH_PRI_APPS_DOWNLOAD state.");
        synchronized (this.mThreadLock) {
            while (this.mInternalState.stayInWaitingForHighPriAppsDownloadState()) {
                waitForEvent();
            }
        }
        setState(NuxOtaState.NOTIFY_ENDPOINT);
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean onEnterNotifyEndpointState() {
        Log.d(TAG, "Entering NOTIFY_ENDPOINT state.");
        this.mNuxOta.waitForControllersFirmwareUpdateComplete();
        playUpdateDoneAudio();
        while (true) {
            if ((!this.mNuxOta.isConnectedToNetworkWithInternetAccess() || !isAccessTokenSet()) && !this.mInternalState.getInEnterpriseMode()) {
                waitForEvent();
            }
        }
        if (!this.mInternalState.getInEnterpriseMode()) {
            int i = 0;
            while (true) {
                if (i >= 3) {
                    break;
                }
                int notifyEndpoint = notifyEndpoint();
                if (notifyEndpoint == 200) {
                    Log.d(TAG, "Notified the endpoint.");
                    this.mTelemetry.recordEvent("nux_ota_notified_endpoint", "Notified the endpoint.");
                    break;
                }
                String str = notifyEndpoint == 400 ? "Server received bad request. Could not notify the endpoint. Skipping the NOTIFY_ENDPOINT step." : (notifyEndpoint == 500 || notifyEndpoint == 503) ? "Error with the server, retrying the endpoint notification." : "Unknown error. Skipping the NOTIFY_ENDPOINT step.";
                i++;
                Log.w(TAG, str);
                this.mTelemetry.recordError(str);
                try {
                    Thread.sleep(RETRY_DELAY_TO_NOTIFY_ENDPOINT_MS);
                } catch (InterruptedException unused) {
                    Log.w(TAG, "Notify endpoint wait interrupted.");
                    interrupt();
                }
            }
        }
        Log.d(TAG, "NUX OTA complete.");
        setState(NuxOtaState.COMPLETE);
        return false;
    }

    private int notifyEndpoint() {
        try {
            return ((Integer) Executors.newSingleThreadExecutor().submit(new Callable<Integer>() {
                /* class com.oculus.nux.ota.StateMachine.AnonymousClass1 */

                /* JADX DEBUG: Failed to insert an additional move for type inference into block B:1:0x000a */
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r0v2, types: [java.io.OutputStream] */
                /* JADX WARN: Type inference failed for: r0v4 */
                /* JADX WARN: Type inference failed for: r0v10 */
                /* JADX WARN: Type inference failed for: r0v12 */
                /* JADX WARN: Type inference failed for: r0v16 */
                /* JADX WARN: Type inference failed for: r0v19 */
                /* JADX WARN: Type inference failed for: r1v20 */
                /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e4, code lost:
                    if (r0 != null) goto L_0x00e6;
                 */
                /* JADX WARNING: Removed duplicated region for block: B:21:0x00ba A[Catch:{ SocketTimeoutException -> 0x00bb, IOException -> 0x008f, all -> 0x008c, all -> 0x00f4 }] */
                /* JADX WARNING: Removed duplicated region for block: B:33:0x00fa  */
                @Override // java.util.concurrent.Callable
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.lang.Integer call() {
                    /*
                    // Method dump skipped, instructions count: 259
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.nux.ota.StateMachine.AnonymousClass1.call():java.lang.Integer");
                }
            }).get(ENDPOINT_ACTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)).intValue();
        } catch (InterruptedException e) {
            String str = TAG;
            Log.e(str, "Interrupted exception while notifying endpoint. " + e.getMessage());
            this.mTelemetry.recordError("Interrupted exception while notifying endpoint.");
        } catch (ExecutionException e2) {
            String str2 = TAG;
            Log.e(str2, " " + e2.getMessage());
            this.mTelemetry.recordError("Execution exception while notifying endpoint.");
        } catch (TimeoutException e3) {
            String str3 = TAG;
            Log.e(str3, "Timeout exception while notifying endpoint. " + e3.getMessage());
            this.mTelemetry.recordError("Timeout exception while notifying endpoint.");
        }
        return -1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void closeOutputStreamQuietly(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    private void forceReboot() {
        Log.w(TAG, "Forcing reboot. Possibly because OTA download stalled.");
        this.mTelemetry.recordError("Forcing reboot. Possibly because OTA download stalled.");
        new OsUpdater(this.mContext).rebootAndApplyUpdate();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0046, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        android.util.Log.w(com.oculus.nux.ota.StateMachine.TAG, "WAITING_FOR_REBOOT 10 second delay was interrupted.");
        interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0066, code lost:
        r2.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0069, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0048 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void delayReboot() {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.nux.ota.StateMachine.delayReboot():void");
    }

    private void rebootAndApplyUpdate() {
        Log.d(TAG, "Requesting OS Updater to reboot and apply the update.");
        setState(NuxOtaState.REBOOTING);
        FirstTimeNuxManager.setFirstTimeNuxPreOtaComplete(true);
        new OsUpdater(this.mContext).rebootAndApplyUpdate();
    }

    private boolean isHighPriAppsUpdateComplete() {
        NuxOtaState state = this.mSettings.getState();
        return state == NuxOtaState.NOTIFY_ENDPOINT || state == NuxOtaState.COMPLETE;
    }

    public Object getLock() {
        return this.mThreadLock;
    }

    private boolean isAccessTokenSet() {
        return getAccessToken() != null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getAccessToken() {
        try {
            AuthCredentials credentials = new AuthServiceClient(this.mContext).getCredentials();
            if (credentials != null) {
                return credentials.getAccessToken();
            }
            return null;
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to get access token.", e);
            return null;
        } catch (InterruptedException unused) {
            Log.w(TAG, "Interrupted while attempting to get access token.");
            interrupt();
            return null;
        }
    }

    private void playUpdateDoneAudio() {
        MediaPlayer.create(this.mContext, this.mSfxUpdateDoneResourceId).start();
    }

    public void setInEnterpriseMode(boolean z) {
        this.mInternalState.setInEnterpriseMode(z);
    }

    public void setHighPriAppsDownloadComplete(boolean z) {
        this.mInternalState.setHighPriAppsDownloadComplete(z);
    }

    public boolean getHighPriAppsDownloadComplete() {
        return this.mInternalState.getHighPriAppsDownloadComplete();
    }

    public void setIsHighPriAppsDownloadStalled(boolean z) {
        this.mInternalState.setIsHighPriAppsDownloadStalled(z);
    }

    public boolean getIsHighPriAppsDownloadStalled() {
        return this.mInternalState.getIsHighPriAppsDownloadStalled();
    }

    public void setIsOtaDownloadStalled(boolean z) {
        this.mInternalState.setIsOtaDownloadStalled(z);
    }

    public boolean getIsOtaDownloadStalled() {
        return this.mInternalState.getIsOtaDownloadStalled();
    }

    public void setNoOta(boolean z) {
        this.mInternalState.setNoOta(z);
    }

    public boolean getNoOta() {
        return this.mInternalState.getNoOta();
    }

    public void setOtaReady(boolean z) {
        this.mInternalState.setOtaReady(z);
    }

    public boolean getOtaReady() {
        return this.mInternalState.getOtaReady();
    }

    public void setOkayToReboot(boolean z) {
        this.mInternalState.setOkayToReboot(z);
    }
}
