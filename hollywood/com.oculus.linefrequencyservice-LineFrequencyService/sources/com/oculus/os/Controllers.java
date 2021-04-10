package com.oculus.os;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.function.Function;
import oculus.internal.BinderClient;
import oculus.internal.remote.IRemoteService;
import oculus.internal.remote.IRemoteServiceStatusCallback;
import oculus.internal.remote.RemoteService;
import oculus.internal.remote.RemoteStatus;

public class Controllers {
    private static final int MAX_RETRIES = 10;
    private static final int SERVICE_RETRY_DELAY = 1000;
    private static final String TAG = Controllers.class.getSimpleName();
    private BinderClient<IRemoteService> mBinderClient;
    private final ControllerStatusObserver mObserver;
    private final ControllersReadyCallback mReadyCallback;
    private IRemoteServiceStatusCallback mStatusCallback;
    private IRemoteService remoteService;

    public enum ControllerScanAndPairResult {
        SUCCESS,
        TIMED_OUT,
        FAILED_TO_PAIR,
        ALREADY_IN_PROGRESS,
        INTERNAL_ERROR,
        UNKNOWN_ERROR
    }

    public enum ControllerStatus {
        CONNECTED,
        DISABLED,
        SEARCHING,
        UPDATING,
        BLOCKED_BATTERY_DEAD,
        BLOCKED_UPDATE_FAILED,
        BLOCKED_UPDATE_REQUIRED,
        UNKNOWN_ERROR
    }

    public interface ControllerStatusObserver {
        void updateControllerStatus(int i, ControllerInfo controllerInfo);
    }

    public interface ControllersReadyCallback {
        void onControllersReady();
    }

    public enum VerifyConnectableResult {
        CONNECTED,
        RECENTLY_CONNECTED,
        UPDATING,
        UPDATE_PENDING,
        FAIL_ALREADY_IN_PROGRESS,
        FAIL_TIMED_OUT,
        FAIL_SECURITY_ERROR,
        FAIL_NOT_PAIRED,
        FAIL_BATTERY_DEAD,
        FAIL_BLOCKED_BY_UPDATE,
        FAIL_UNKNOWN_ERROR
    }

    public static class ControllerInfo {
        public ControllerStatus status;

        public ControllerInfo(ControllerStatus status2) {
            this.status = status2;
        }
    }

    public Controllers(ControllerStatusObserver observer) {
        this(observer, null);
    }

    public Controllers(ControllerStatusObserver observer, ControllersReadyCallback readyCallback) {
        this.remoteService = null;
        this.mBinderClient = new BinderClient<IRemoteService>(RemoteService.NAME, new Function<IBinder, IRemoteService>() {
            /* class com.oculus.os.Controllers.AnonymousClass1 */

            public IRemoteService apply(IBinder b) {
                return IRemoteService.Stub.asInterface(b);
            }
        }) {
            /* class com.oculus.os.Controllers.AnonymousClass2 */

            /* access modifiers changed from: protected */
            public synchronized void onServiceConnected(IRemoteService service) {
                if (Controllers.this.mReadyCallback != null) {
                    Controllers.this.mReadyCallback.onControllersReady();
                }
                try {
                    service.registerStatusCallback(Controllers.this.mStatusCallback);
                } catch (RemoteException e) {
                    Log.e(Controllers.TAG, "Failed to register with remote service", e);
                }
            }
        };
        this.mStatusCallback = new IRemoteServiceStatusCallback.Stub() {
            /* class com.oculus.os.Controllers.AnonymousClass3 */

            @Override // oculus.internal.remote.IRemoteServiceStatusCallback
            public void onStatus(int deviceType, RemoteStatus status) {
                if (Controllers.this.mObserver != null) {
                    Controllers.this.mObserver.updateControllerStatus(deviceType, new ControllerInfo(Controllers.convertRemoteStatus(status)));
                }
            }
        };
        this.mObserver = observer;
        this.mReadyCallback = readyCallback;
    }

    /* access modifiers changed from: private */
    public static ControllerStatus convertRemoteStatus(RemoteStatus status) {
        if (status == null) {
            return null;
        }
        if (status.isConnected()) {
            return ControllerStatus.CONNECTED;
        }
        if (status.isDisabled()) {
            return ControllerStatus.DISABLED;
        }
        if (status.isSearching()) {
            return ControllerStatus.SEARCHING;
        }
        if (status.isUpdating()) {
            return ControllerStatus.UPDATING;
        }
        if (status.isBatteryDead()) {
            return ControllerStatus.BLOCKED_BATTERY_DEAD;
        }
        if (status.isUpdateRequired()) {
            return ControllerStatus.BLOCKED_UPDATE_REQUIRED;
        }
        if (status.isUpdateFailed()) {
            return ControllerStatus.BLOCKED_UPDATE_FAILED;
        }
        return ControllerStatus.UNKNOWN_ERROR;
    }

    private boolean connectRemoteService() throws InterruptedException {
        for (int retries = 0; retries < 10 && this.remoteService == null; retries++) {
            this.remoteService = this.mBinderClient.getService();
            if (this.remoteService == null) {
                Thread.sleep(1000);
            }
        }
        return this.remoteService != null;
    }

    public int[] getDeviceTypes() throws InterruptedException {
        try {
            if (connectRemoteService()) {
                return this.remoteService.getSupportedDeviceTypes();
            }
            Log.e(TAG, "Remote service not available. Cannot get device types.");
            return null;
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Remote Exception: " + e.getMessage());
            this.remoteService = null;
            return null;
        }
    }

    public String getPairedDevice(int deviceType) throws InterruptedException {
        RemoteStatus status;
        try {
            if (!connectRemoteService() || (status = this.remoteService.getPairedDeviceStatus(deviceType)) == null) {
                return null;
            }
            return status.identifier;
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Remote Exception: " + e.getMessage());
            this.remoteService = null;
        } catch (RuntimeException e2) {
            Log.e(TAG, "Service not ready yet");
            this.remoteService = null;
        }
        return null;
    }

    public int getBatteryLevel(int deviceType) throws InterruptedException {
        RemoteStatus status;
        try {
            if (!connectRemoteService() || (status = this.remoteService.getPairedDeviceStatus(deviceType)) == null) {
                return -1;
            }
            return status.batteryLevel;
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Remote Exception: " + e.getMessage());
            this.remoteService = null;
            return -1;
        } catch (RuntimeException e2) {
            String str2 = TAG;
            Log.e(str2, "Service not ready yet: " + e2.getMessage());
            this.remoteService = null;
            return -1;
        }
    }

    public ControllerStatus getPairedDeviceStatus(int deviceType) throws InterruptedException {
        try {
            if (connectRemoteService()) {
                RemoteStatus status = this.remoteService.getPairedDeviceStatus(deviceType);
                if (status != null) {
                    return convertRemoteStatus(status);
                }
                return null;
            }
            Log.e(TAG, "Remote service not available. Cannot get paired device status.");
            return null;
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Remote Exception: " + e.getMessage());
            this.remoteService = null;
            return null;
        }
    }

    public VerifyConnectableResult[] verifyConnectable(int[] deviceTypes, int timeoutMs) throws InterruptedException {
        return verifyConnectable(deviceTypes, timeoutMs, 0);
    }

    public VerifyConnectableResult[] verifyConnectable(int[] deviceTypes, int timeoutMs, int recentConnectionTimeLimitSeconds) throws InterruptedException {
        try {
            if (connectRemoteService()) {
                int[] verifyResults = this.remoteService.verifyControllersConnectable(deviceTypes, timeoutMs, recentConnectionTimeLimitSeconds);
                VerifyConnectableResult[] results = new VerifyConnectableResult[deviceTypes.length];
                for (int index = 0; index < verifyResults.length; index++) {
                    switch (verifyResults[index]) {
                        case 0:
                            results[index] = VerifyConnectableResult.CONNECTED;
                            break;
                        case 1:
                            results[index] = VerifyConnectableResult.UPDATING;
                            break;
                        case 2:
                            results[index] = VerifyConnectableResult.UPDATE_PENDING;
                            break;
                        case 3:
                            results[index] = VerifyConnectableResult.RECENTLY_CONNECTED;
                            break;
                        case 4:
                            results[index] = VerifyConnectableResult.FAIL_ALREADY_IN_PROGRESS;
                            break;
                        case 5:
                            results[index] = VerifyConnectableResult.FAIL_TIMED_OUT;
                            break;
                        case 6:
                            results[index] = VerifyConnectableResult.FAIL_SECURITY_ERROR;
                            break;
                        case 7:
                            results[index] = VerifyConnectableResult.FAIL_NOT_PAIRED;
                            break;
                        case 8:
                            results[index] = VerifyConnectableResult.FAIL_BATTERY_DEAD;
                            break;
                        case 9:
                            results[index] = VerifyConnectableResult.FAIL_BLOCKED_BY_UPDATE;
                            break;
                        default:
                            results[index] = VerifyConnectableResult.FAIL_UNKNOWN_ERROR;
                            break;
                    }
                }
                return results;
            }
            Log.e(TAG, "Remote service not available. Cannot verify connectable.");
            return null;
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Remote Exception: " + e.getMessage());
            this.remoteService = null;
            return null;
        }
    }

    private static ControllerScanAndPairResult translateResult(int result) {
        if (result == 0) {
            return ControllerScanAndPairResult.SUCCESS;
        }
        if (result == 1) {
            return ControllerScanAndPairResult.TIMED_OUT;
        }
        if (result == 2) {
            return ControllerScanAndPairResult.FAILED_TO_PAIR;
        }
        if (result == 3) {
            return ControllerScanAndPairResult.ALREADY_IN_PROGRESS;
        }
        if (result != 4) {
            return ControllerScanAndPairResult.UNKNOWN_ERROR;
        }
        return ControllerScanAndPairResult.INTERNAL_ERROR;
    }

    public ControllerScanAndPairResult scanAndPairDevice(int deviceType, int timeoutMs) throws InterruptedException {
        try {
            if (connectRemoteService()) {
                return translateResult(this.remoteService.scanAndPairDevice(deviceType, timeoutMs));
            }
            Log.e(TAG, "Remote service not available. Cannot scan and pair device.");
            return null;
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Remote Exception: " + e.getMessage());
            this.remoteService = null;
            return null;
        }
    }
}
