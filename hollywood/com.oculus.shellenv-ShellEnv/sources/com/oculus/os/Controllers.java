package com.oculus.os;

public class Controllers {

    public static class ControllerInfo {
        public ControllerStatus status;

        public ControllerInfo(ControllerStatus controllerStatus) {
            throw new RuntimeException("Stub!");
        }
    }

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

    public Controllers(ControllerStatusObserver controllerStatusObserver) {
        throw new RuntimeException("Stub!");
    }

    public Controllers(ControllerStatusObserver controllerStatusObserver, ControllersReadyCallback controllersReadyCallback) {
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

    public VerifyConnectableResult[] verifyConnectable(int[] iArr, int i, int i2) {
        throw new RuntimeException("Stub!");
    }
}
