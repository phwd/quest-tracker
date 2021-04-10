package com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.Controllers;

public final class ControllerPairer {
    private static final String TAG = LoggingUtil.tag(ControllerPairer.class);
    private Controllers mControllers;
    private boolean mIsPairingCanceled = false;
    private ControllerPairerListener mListener;
    private ControllerTypeProvider mProvider;

    public interface ControllerPairerListener {
        void onPairingFailure();

        void onPairingStart(long j);

        void onPairingSuccess(int i, PairedControllerFirmwareStatus pairedControllerFirmwareStatus);
    }

    public interface ControllerTypeProvider {
        int getNextTypeToPair();
    }

    public enum PairedControllerFirmwareStatus {
        UPDATE_REQUIRED,
        UP_TO_DATE,
        UNKNOWN
    }

    public ControllerPairer(ControllerTypeProvider controllerTypeProvider, ControllerPairerListener controllerPairerListener) {
        this.mProvider = controllerTypeProvider;
        this.mListener = controllerPairerListener;
    }

    public void stopPairing() {
        this.mIsPairingCanceled = true;
    }

    public void startPairing() {
        if (this.mControllers == null) {
            this.mControllers = new Controllers(null, new Controllers.ControllersReadyCallback() {
                /* class com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.AnonymousClass1 */

                @Override // com.oculus.os.Controllers.ControllersReadyCallback
                public void onControllersReady() {
                    if (ControllerPairer.this.mIsPairingCanceled) {
                        return;
                    }
                    if (ControllerPairer.this.mControllers != null) {
                        Log.d(ControllerPairer.TAG, "Starting thread to pair either controller.");
                        new Thread(new Runnable() {
                            /* class com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.AnonymousClass1.AnonymousClass1 */

                            public void run() {
                                ControllerPairer.this.scanAndPair();
                                Log.d(ControllerPairer.TAG, "Scanning completed");
                            }
                        }).start();
                        return;
                    }
                    Log.e(ControllerPairer.TAG, "Failed to begin controller pairing because controllers was null.");
                }
            });
        }
    }

    private boolean pairingIsCanceledOrIsTimedOut(long j) {
        return this.mIsPairingCanceled || (((System.currentTimeMillis() - j) > 30000 ? 1 : ((System.currentTimeMillis() - j) == 30000 ? 0 : -1)) >= 0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void scanAndPair() {
        long currentTimeMillis = System.currentTimeMillis();
        String str = TAG;
        Log.d(str, "Began pairing controller: " + currentTimeMillis);
        Controllers.ControllerScanAndPairResult controllerScanAndPairResult = Controllers.ControllerScanAndPairResult.UNKNOWN_ERROR;
        int i = -1;
        try {
            if (this.mListener != null) {
                this.mListener.onPairingStart(currentTimeMillis);
            }
            while (!pairingIsCanceledOrIsTimedOut(currentTimeMillis) && controllerScanAndPairResult != Controllers.ControllerScanAndPairResult.SUCCESS) {
                i = this.mProvider.getNextTypeToPair();
                controllerScanAndPairResult = this.mControllers.scanAndPairDevice(i, 500);
            }
        } catch (InterruptedException e) {
            String str2 = TAG;
            Log.e(str2, "Error while scanning for controller: " + -1, e);
        }
        if (controllerScanAndPairResult == Controllers.ControllerScanAndPairResult.SUCCESS) {
            String str3 = TAG;
            Log.d(str3, "Scanning for controllers completed, found controller type: " + i);
            if (this.mListener != null) {
                PairedControllerFirmwareStatus controllerFirmwareStatus = getControllerFirmwareStatus(i, currentTimeMillis);
                Log.d(TAG, String.format("Paired controller firmware status for device: %d is: %s", Integer.valueOf(i), controllerFirmwareStatus.name()));
                this.mListener.onPairingSuccess(i, controllerFirmwareStatus);
                return;
            }
            return;
        }
        Log.d(TAG, "Scanning for controllers failed");
        ControllerPairerListener controllerPairerListener = this.mListener;
        if (controllerPairerListener != null) {
            controllerPairerListener.onPairingFailure();
        }
    }

    private PairedControllerFirmwareStatus getControllerFirmwareStatus(int i, long j) {
        Log.d(TAG, String.format("Retrieve paired device status for device: %d", Integer.valueOf(i)));
        Controllers.ControllerStatus controllerStatus = Controllers.ControllerStatus.UNKNOWN_ERROR;
        while (!pairingIsCanceledOrIsTimedOut(j)) {
            try {
                controllerStatus = this.mControllers.getPairedDeviceStatus(i);
                if (Controllers.ControllerStatus.SEARCHING != controllerStatus) {
                    break;
                }
            } catch (InterruptedException e) {
                Log.e(TAG, String.format("Error while getting paired status for controller: %s", Integer.valueOf(i)), e);
            }
        }
        Log.d(TAG, String.format("Retrieved controller status: %s for device: %d", controllerStatus.name(), Integer.valueOf(i)));
        int i2 = AnonymousClass2.$SwitchMap$com$oculus$os$Controllers$ControllerStatus[controllerStatus.ordinal()];
        if (i2 == 1 || i2 == 2) {
            return PairedControllerFirmwareStatus.UPDATE_REQUIRED;
        }
        if (i2 == 3) {
            return PairedControllerFirmwareStatus.UP_TO_DATE;
        }
        Log.w(TAG, String.format("Mapping status: %s for device: %d to UNKNOWN", controllerStatus.name(), Integer.valueOf(i)));
        return PairedControllerFirmwareStatus.UNKNOWN;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$Controllers$ControllerStatus = new int[Controllers.ControllerStatus.values().length];

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
                com.oculus.os.Controllers$ControllerStatus[] r0 = com.oculus.os.Controllers.ControllerStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.AnonymousClass2.$SwitchMap$com$oculus$os$Controllers$ControllerStatus = r0
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.AnonymousClass2.$SwitchMap$com$oculus$os$Controllers$ControllerStatus     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.os.Controllers$ControllerStatus r1 = com.oculus.os.Controllers.ControllerStatus.BLOCKED_UPDATE_REQUIRED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.AnonymousClass2.$SwitchMap$com$oculus$os$Controllers$ControllerStatus     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.os.Controllers$ControllerStatus r1 = com.oculus.os.Controllers.ControllerStatus.UPDATING     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.AnonymousClass2.$SwitchMap$com$oculus$os$Controllers$ControllerStatus     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.os.Controllers$ControllerStatus r1 = com.oculus.os.Controllers.ControllerStatus.CONNECTED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.AnonymousClass2.$SwitchMap$com$oculus$os$Controllers$ControllerStatus     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.os.Controllers$ControllerStatus r1 = com.oculus.os.Controllers.ControllerStatus.BLOCKED_BATTERY_DEAD     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.AnonymousClass2.$SwitchMap$com$oculus$os$Controllers$ControllerStatus     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.oculus.os.Controllers$ControllerStatus r1 = com.oculus.os.Controllers.ControllerStatus.BLOCKED_UPDATE_FAILED     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.AnonymousClass2.$SwitchMap$com$oculus$os$Controllers$ControllerStatus     // Catch:{ NoSuchFieldError -> 0x004b }
                com.oculus.os.Controllers$ControllerStatus r1 = com.oculus.os.Controllers.ControllerStatus.DISABLED     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.AnonymousClass2.$SwitchMap$com$oculus$os$Controllers$ControllerStatus     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.oculus.os.Controllers$ControllerStatus r1 = com.oculus.os.Controllers.ControllerStatus.SEARCHING     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.AnonymousClass2.$SwitchMap$com$oculus$os$Controllers$ControllerStatus     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.oculus.os.Controllers$ControllerStatus r1 = com.oculus.os.Controllers.ControllerStatus.UNKNOWN_ERROR     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.AnonymousClass2.<clinit>():void");
        }
    }
}
