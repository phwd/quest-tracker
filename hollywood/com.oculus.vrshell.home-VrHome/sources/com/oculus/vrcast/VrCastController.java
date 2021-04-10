package com.oculus.vrcast;

import android.content.Context;
import android.os.Bundle;
import java.util.List;

public class VrCastController {

    public enum DeviceType {
        Miracast,
        Chromecast
    }

    @Deprecated
    public interface VrShellCastCallback {
        void onBindServiceFailed();

        void onBindServiceSucceeded();

        void onDeviceStateUpdated(VrShellCastDevice vrShellCastDevice);

        void onDevicesFound(List<VrShellCastDevice> list);

        void onRemoteServiceDied();
    }

    @Deprecated
    public VrCastController(Context context, VrShellCastCallback callback) {
        throw new RuntimeException("Stub!");
    }

    public VrCastController(Context context, VrCastCallback callback) {
        throw new RuntimeException("Stub!");
    }

    public void startDiscovery() {
        throw new RuntimeException("Stub!");
    }

    public void startDiscovery(String callerContext) {
        throw new RuntimeException("Stub!");
    }

    public void stopDiscovery() {
        throw new RuntimeException("Stub!");
    }

    public void stopDiscovery(String callerContext) {
        throw new RuntimeException("Stub!");
    }

    public void startCast(String id) {
        throw new RuntimeException("Stub!");
    }

    public void startCast(String id, String callerContext) {
        throw new RuntimeException("Stub!");
    }

    public void startCast(String id, boolean isDeviceAudioDest) {
        throw new RuntimeException("Stub!");
    }

    public void startCast(String id, String callerContext, boolean isDeviceAudioDest) {
        throw new RuntimeException("Stub!");
    }

    public void stopCast(String id) {
        throw new RuntimeException("Stub!");
    }

    public void stopCast(String id, String callerContext) {
        throw new RuntimeException("Stub!");
    }

    public void stopCastWithError(String id, String errorMessage) {
        throw new RuntimeException("Stub!");
    }

    public void stopCastWithError(String id, String errorMessage, String callerContext) {
        throw new RuntimeException("Stub!");
    }

    public boolean isServiceBound() {
        throw new RuntimeException("Stub!");
    }

    public void unbindVrCastService() {
        throw new RuntimeException("Stub!");
    }

    public static abstract class VrCastCallback {
        public abstract void onBindServiceFailed();

        public abstract void onBindServiceSucceeded(VrShellCastDevice vrShellCastDevice);

        public abstract void onDeviceStateUpdated(VrShellCastDevice vrShellCastDevice);

        public abstract void onDevicesFound(List<VrShellCastDevice> list);

        public abstract void onRemoteServiceDied();

        public VrCastCallback() {
            throw new RuntimeException("Stub!");
        }

        public void onError(int errorCode, Bundle extras) {
            throw new RuntimeException("Stub!");
        }
    }

    public static class VrShellCastDevice {
        public String id;
        public String name;
        public State state;
        public DeviceType type;

        public VrShellCastDevice(String name2, String id2, State state2, DeviceType type2) {
            throw new RuntimeException("Stub!");
        }

        public String toString() {
            throw new RuntimeException("Stub!");
        }

        public enum State {
            FOUND,
            CONNECTING_TO_PEER,
            CONNECTION_INITIATED,
            CONNECTION_SUCCESS,
            STARTING_SESSION,
            CASTING,
            DISCONNECTING,
            INVALID;

            public static State fromOrdinal(int val) {
                throw new RuntimeException("Stub!");
            }
        }
    }
}
