package com.oculus.companion.server;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import com.oculus.companion.server.ControllerManager;
import com.oculus.os.SettingsManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import oculus.internal.remote.IRemoteService;
import oculus.internal.remote.RemoteStatus;

public class OculusController {
    private static final Set<String> CONTROLLER_TRANSMIT_POWER_BOOST_COUNTRY_ALLOWLIST = Collections.unmodifiableSet(new HashSet(Arrays.asList("CA", "US")));
    private static final String TAG = "OculusController";
    private static IRemoteService remoteService;

    public static boolean connectRemoteService() throws InterruptedException {
        for (int i = 0; i < 10 && remoteService == null; i++) {
            IBinder service = ServiceManager.getService("OVRRemoteService");
            if (service == null) {
                Thread.sleep(1000);
            } else {
                remoteService = IRemoteService.Stub.asInterface(service);
            }
        }
        if (remoteService != null) {
            return true;
        }
        return false;
    }

    private static ControllerManager.ControllerScanAndPairResult translateResult(int i) {
        if (i == 0) {
            return ControllerManager.ControllerScanAndPairResult.SUCCESS;
        }
        if (i == 1) {
            return ControllerManager.ControllerScanAndPairResult.TIMED_OUT;
        }
        if (i == 2) {
            return ControllerManager.ControllerScanAndPairResult.FAILED_TO_PAIR;
        }
        if (i == 3) {
            return ControllerManager.ControllerScanAndPairResult.ALREADY_IN_PROGRESS;
        }
        if (i != 4) {
            return ControllerManager.ControllerScanAndPairResult.UNKNOWN_ERROR;
        }
        return ControllerManager.ControllerScanAndPairResult.INTERNAL_ERROR;
    }

    public static ControllerManager.ControllerScanAndPairResult scanAndPairDevice(int i, int i2) throws InterruptedException {
        try {
            if (connectRemoteService()) {
                return translateResult(remoteService.scanAndPairDevice(i, i2));
            }
            return ControllerManager.ControllerScanAndPairResult.UNKNOWN_ERROR;
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Remote Exception: " + e.getMessage());
            remoteService = null;
            return ControllerManager.ControllerScanAndPairResult.UNKNOWN_ERROR;
        }
    }

    public static int[] verifyConnectable(int[] iArr, int i, int i2) throws InterruptedException {
        try {
            if (connectRemoteService()) {
                return remoteService.verifyControllersConnectable(iArr, i, i2);
            }
            return null;
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Remote Exception: " + e.getMessage());
            return null;
        }
    }

    public static String getPairedDevice(int i) throws InterruptedException {
        try {
            if (connectRemoteService()) {
                if (CompanionServer.DEBUG) {
                    String str = TAG;
                    Log.d(str, "getPairedDevice(" + i + ")");
                }
                RemoteStatus pairedDeviceStatus = remoteService.getPairedDeviceStatus(i);
                if (pairedDeviceStatus != null) {
                    return pairedDeviceStatus.identifier;
                }
                return null;
            }
        } catch (RemoteException e) {
            String str2 = TAG;
            Log.e(str2, "Remote Exception: " + e.getMessage());
            remoteService = null;
        } catch (RuntimeException unused) {
            Log.e(TAG, "Service not ready yet");
            remoteService = null;
        }
        return null;
    }

    public static boolean unpairDevice(int i) throws InterruptedException {
        try {
            if (!connectRemoteService()) {
                return false;
            }
            if (CompanionServer.DEBUG) {
                String str = TAG;
                Log.d(str, "unpairDevice(" + i + ")");
            }
            return remoteService.unpairDevice(i);
        } catch (RemoteException e) {
            String str2 = TAG;
            Log.e(str2, "Remote Exception: " + e.getMessage());
            remoteService = null;
            return false;
        } catch (RuntimeException unused) {
            Log.e(TAG, "Service not ready yet");
            remoteService = null;
            return false;
        }
    }

    public static int[] getDeviceTypes() throws InterruptedException {
        try {
            if (connectRemoteService()) {
                return remoteService.getSupportedDeviceTypes();
            }
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Remote Exception: " + e.getMessage());
            remoteService = null;
        } catch (RuntimeException unused) {
            Log.e(TAG, "Service not ready yet");
            remoteService = null;
        }
        return new int[0];
    }

    public static boolean isConnected(int i) throws InterruptedException {
        try {
            if (connectRemoteService()) {
                RemoteStatus pairedDeviceStatus = remoteService.getPairedDeviceStatus(i);
                boolean z = pairedDeviceStatus != null && pairedDeviceStatus.isConnected();
                if (CompanionServer.DEBUG) {
                    String str = TAG;
                    Log.d(str, "isConnected(" + i + ") = " + z);
                }
                return z;
            }
        } catch (RemoteException e) {
            String str2 = TAG;
            Log.e(str2, "Remote Exception: " + e.getMessage());
            remoteService = null;
        } catch (RuntimeException e2) {
            String str3 = TAG;
            Log.e(str3, "Service not ready yet: " + e2.getMessage());
            remoteService = null;
        }
        return false;
    }

    public static int getBatteryLevel(int i) throws InterruptedException {
        RemoteStatus pairedDeviceStatus;
        try {
            if (!connectRemoteService() || (pairedDeviceStatus = remoteService.getPairedDeviceStatus(i)) == null) {
                return -1;
            }
            return pairedDeviceStatus.batteryLevel;
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Remote Exception: " + e.getMessage());
            remoteService = null;
            return -1;
        } catch (RuntimeException e2) {
            String str2 = TAG;
            Log.e(str2, "Service not ready yet: " + e2.getMessage());
            remoteService = null;
            return -1;
        }
    }

    public static String getFirmwareVersion(int i) throws InterruptedException {
        RemoteStatus pairedDeviceStatus;
        try {
            if (!connectRemoteService() || (pairedDeviceStatus = remoteService.getPairedDeviceStatus(i)) == null) {
                return "";
            }
            return pairedDeviceStatus.firmwareVersion;
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Remote Exception: " + e.getMessage());
            remoteService = null;
            return "";
        } catch (RuntimeException e2) {
            String str2 = TAG;
            Log.e(str2, "Service not ready yet: " + e2.getMessage());
            remoteService = null;
            return "";
        }
    }

    private static void setDetectedControllerTransmitPowerBoostSetting(boolean z) {
        new SettingsManager().setBoolean("detected_controller_transmit_power_boost", z);
    }

    public static void updateControllerTransmitPowerBoost(String str) {
        boolean z = str != null && CONTROLLER_TRANSMIT_POWER_BOOST_COUNTRY_ALLOWLIST.contains(str.toUpperCase());
        if (CompanionServer.DEBUG) {
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Wifi Country: ");
            sb.append(str);
            sb.append(", allows controller tx power boost: ");
            sb.append(z ? "Y" : "N");
            Log.d(str2, sb.toString());
        }
        setDetectedControllerTransmitPowerBoostSetting(z);
    }
}
