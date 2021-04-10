package com.oculus.os;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Controllers {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class ControllerInfo {
        public ControllerStatus status;

        public ControllerInfo(ControllerStatus controllerStatus) {
            throw new RuntimeException("Stub!");
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public enum ControllerScanAndPairResult {
        ALREADY_IN_PROGRESS,
        FAILED_TO_PAIR,
        INTERNAL_ERROR,
        SUCCESS,
        TIMED_OUT,
        UNKNOWN_ERROR
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public enum ControllerStatus {
        BLOCKED_BATTERY_DEAD,
        BLOCKED_UPDATE_FAILED,
        BLOCKED_UPDATE_REQUIRED,
        CONNECTED,
        DISABLED,
        SEARCHING,
        UNKNOWN_ERROR,
        UPDATING
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface ControllerStatusObserver {
        void updateControllerStatus(int i, ControllerInfo controllerInfo);
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface ControllersReadyCallback {
        void onControllersReady();
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public enum VerifyConnectableResult {
        CONNECTED,
        FAIL_ALREADY_IN_PROGRESS,
        FAIL_BATTERY_DEAD,
        FAIL_BLOCKED_BY_UPDATE,
        FAIL_NOT_PAIRED,
        FAIL_SECURITY_ERROR,
        FAIL_TIMED_OUT,
        FAIL_UNKNOWN_ERROR,
        UPDATE_PENDING,
        UPDATING
    }

    public Controllers(ControllerStatusObserver controllerStatusObserver) {
        throw new RuntimeException("Stub!");
    }

    public int getBatteryLevel(int i) {
        throw new RuntimeException("Stub!");
    }

    public int[] getDeviceTypes() {
        throw new RuntimeException("Stub!");
    }

    public String getPairedDevice(int i) {
        throw new RuntimeException("Stub!");
    }

    public ControllerStatus getPairedDeviceStatus(int i) {
        throw new RuntimeException("Stub!");
    }

    public ControllerScanAndPairResult scanAndPairDevice(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    public VerifyConnectableResult[] verifyConnectable(int[] iArr, int i) {
        throw new RuntimeException("Stub!");
    }

    public Controllers(ControllerStatusObserver controllerStatusObserver, ControllersReadyCallback controllersReadyCallback) {
        throw new RuntimeException("Stub!");
    }

    public VerifyConnectableResult[] verifyConnectable(int[] iArr, int i, int i2) {
        throw new RuntimeException("Stub!");
    }
}
