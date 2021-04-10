package oculus.internal;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import oculus.internal.IGatekeeperService;

public class Gatekeeper implements IBinder.DeathRecipient {
    public static final int COLLECT_BAD_DISCHARGE_STATS = 0;
    private static final boolean DEBUG = false;
    public static final int ENABLE_APP_SAFETY_BINARY_CHECK = 15;
    public static final int ENABLE_ASSISTANT_WAKEWORD = 27;
    public static final int ENABLE_BATTERY_DRAIN_SERVICE = 35;
    public static final int ENABLE_BUGREPORTER = 1;
    public static final int ENABLE_BUGREPORTER_V2 = 10;
    public static final int ENABLE_BUGREPORTSERVICE = 22;
    public static final int ENABLE_CHROMECASTING = 4;
    public static final int ENABLE_CHROMECAST_101_ERROR_KEEP_CONNECTED = 30;
    public static final int ENABLE_CHROMECAST_FORCE_H264_FOR_SMART_TVS = 34;
    public static final int ENABLE_CHROMECAST_H264_OVERRIDE = 32;
    public static final int ENABLE_CHROMECAST_INCREASE_PING_FAILURE_TOLERANCE = 28;
    public static final int ENABLE_CHROMECAST_INIT_NSD_ON_DISCOVERY = 33;
    public static final int ENABLE_CHROMECAST_LONGER_PING_INTERVAL = 19;
    public static final int ENABLE_CHROMECAST_NATIVE_RECEIVER = 8;
    public static final int ENABLE_CHROMECAST_REMOVE_PING = 29;
    public static final int ENABLE_CONFIGURABLE_MTP_DIALOG = 23;
    public static final int ENABLE_CRASH_REPORT_LOG_COLLECTION = 37;
    public static final int ENABLE_HANDS_OPT_IN_MIGRATION = 38;
    public static final int ENABLE_LICENSE_ENFORCEMENT = 17;
    public static final int ENABLE_LICENSE_MANAGER = 13;
    public static final int ENABLE_LINK_NO_MODAL = 21;
    public static final int ENABLE_LOCATION_PROVIDER = 2;
    public static final int ENABLE_MIRACAST = 9;
    public static final int ENABLE_MONTEREY_N_10BPP_TRACKING = 25;
    public static final int ENABLE_MR_DATA_PERMISSION = 11;
    public static final int ENABLE_MULTI_USER = 3;
    public static final int ENABLE_NATIVE_VOICE_SERVICE = 36;
    public static final int ENABLE_OCULUS_MEDIA_SHARED_MIC = 40;
    public static final int ENABLE_PERMISSION_DIALOG_TELEMETRY = 16;
    public static final int ENABLE_PHONE_NOTIFICATIONS = 18;
    public static final int ENABLE_RETAIL_DEMO = 20;
    public static final int ENABLE_SAFETY_SIGNAL_COLLECTION = 26;
    public static final int ENABLE_SWAP_SYSTEM_BTN_HANDEDNESS = 12;
    public static final int ENABLE_TOUR_GUIDE = 14;
    public static final int ENABLE_UNKNOWN_SOURCES = 6;
    public static final int ENABLE_VISION_BUGREPORTS = 5;
    public static final int ENABLE_VR_KEYBOARD = 7;
    public static final int ENABLE_WIFI_TELEMETRY = 39;
    private static final String SERVICE_NAME = "GatekeeperService";
    private static final String TAG = "Gatekeeper";
    public static final int USE_PACKAGE_PARTS_UPLOADER = 24;
    private IGatekeeperService mService;
    private final int mType;

    public Gatekeeper(int type) {
        this.mType = type;
        ensureServiceConnected();
    }

    public boolean isEnabled() {
        return isEnabled(DEBUG);
    }

    public boolean isEnabled(boolean defaultValue) {
        try {
            ensureServiceConnected();
            return this.mService.getGatekeeperDef(this.mType, defaultValue);
        } catch (Exception e) {
            Log.e(TAG, "Failed to register gatekeeper: " + this.mType);
            return defaultValue;
        }
    }

    public void binderDied() {
        Log.d(TAG, "Remote service died, resetting mService");
        this.mService = null;
    }

    private void ensureServiceConnected() {
        if (this.mService == null) {
            IBinder b = ServiceManager.getService(SERVICE_NAME);
            this.mService = IGatekeeperService.Stub.asInterface(b);
            if (this.mService == null) {
                Log.wtf(TAG, "Failed to get GatekeeperService");
                return;
            }
            try {
                b.linkToDeath(this, 0);
            } catch (RemoteException e) {
                Log.e(TAG, "linkToDeath failed", e);
            }
        }
    }
}
