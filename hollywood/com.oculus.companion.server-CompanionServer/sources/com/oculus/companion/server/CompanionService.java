package com.oculus.companion.server;

import android.app.ActivityManager;
import android.app.ActivityManagerNative;
import android.app.AlarmManager;
import android.app.IActivityManager;
import android.app.IntentService;
import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.hardware.usb.UsbManager;
import android.location.Location;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.policy.IKeyguardService;
import com.android.internal.widget.LockPatternUtils;
import com.oculus.aidl.ILocationCallback;
import com.oculus.aidl.ILocationServiceInterface;
import com.oculus.authapi.OVRAuth;
import com.oculus.companion.server.CompanionServer;
import com.oculus.companion.server.ControllerManager;
import com.oculus.companion.server.WifiModule;
import com.oculus.os.FirstTimeNuxManager;
import com.oculus.os.SettingsManager;
import com.oculus.os.VrApiManager;
import com.oculus.security.CallerInfoHelper;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import oculus.internal.LockPatternUtilsCompat;
import oculus.internal.binder.LocalBinder;

public class CompanionService extends IntentService {
    private static String LOGGED_OUT_USER_ID = "";
    private static final String TAG = "CompanionService";
    private static CompanionServer.LocalBinder binder;
    private static boolean csBound = false;
    private static final Object csLock = new Object();
    private static final CompanionServerConnection csServerConnection = new CompanionServerConnection();
    private static Object kgsLock = new Object();
    private static CompanionServer mCS = null;
    private static IKeyguardService mIks;
    private static KGServiceConnection mKGConnection;
    private LocalBinder<CompanionService> mLocalBinder = null;
    private final Object mLocalBinderLock = new Object();
    private Telemetry mTelemetry = null;

    /* access modifiers changed from: package-private */
    public interface CompanionKeyguardCallback {
        void updateKeyguardStatus(IKeyguardService iKeyguardService);
    }

    public static boolean intToBool(int i) {
        return i == 1;
    }

    public CompanionService() {
        super(TAG);
    }

    public IBinder onBind(Intent intent) {
        Log.i(TAG, "A client is binding to the Companion Service");
        synchronized (this.mLocalBinderLock) {
            if (this.mLocalBinder == null) {
                this.mLocalBinder = new LocalBinder<>(this);
            }
        }
        return this.mLocalBinder;
    }

    /* access modifiers changed from: private */
    public class LocationHelper extends ILocationCallback.Stub implements ServiceConnection {
        private final Context mContext;
        private ILocationServiceInterface mILocationServiceInterface;
        private CountDownLatch mLatch;
        private String mTimeZone;

        private LocationHelper(Context context) {
            this.mContext = context;
            this.mLatch = new CountDownLatch(1);
            this.mTimeZone = null;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private String getTimezone() {
            if (this.mILocationServiceInterface == null) {
                try {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.oculus.ocms", "com.oculus.ocms.locationservices.LocationService"));
                    this.mContext.bindService(intent, this, 1);
                    this.mLatch.await(15, TimeUnit.SECONDS);
                } catch (Exception e) {
                    String str = CompanionService.TAG;
                    Log.e(str, "Error: " + e.getLocalizedMessage());
                    return this.mTimeZone;
                }
            }
            if (this.mILocationServiceInterface == null) {
                if (CompanionServer.DEBUG) {
                    Log.e(CompanionService.TAG, "Failed to connect to LocationService");
                }
                return this.mTimeZone;
            }
            if (CompanionServer.DEBUG) {
                Log.d(CompanionService.TAG, "Connected to LocationService");
            }
            try {
                this.mILocationServiceInterface.getLocationWithTimezoneUsingIPOnly(this);
            } catch (RemoteException e2) {
                String str2 = CompanionService.TAG;
                Log.e(str2, "onServiceConnected: RemoteException = " + e2.getLocalizedMessage());
            }
            try {
                this.mLatch.await(30, TimeUnit.SECONDS);
            } catch (InterruptedException e3) {
                String str3 = CompanionService.TAG;
                Log.e(str3, "Error: " + e3.getLocalizedMessage());
            }
            this.mContext.unbindService(this);
            this.mILocationServiceInterface = null;
            return this.mTimeZone;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (CompanionServer.DEBUG) {
                String str = CompanionService.TAG;
                Log.d(str, "Bound to: " + componentName);
            }
            this.mILocationServiceInterface = ILocationServiceInterface.Stub.asInterface(iBinder);
            this.mLatch.countDown();
            this.mLatch = new CountDownLatch(1);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (CompanionServer.DEBUG) {
                String str = CompanionService.TAG;
                Log.d(str, "Unbound from: " + componentName);
            }
            this.mILocationServiceInterface = null;
        }

        @Override // com.oculus.aidl.ILocationCallback
        public void report(Location location) {
            if (CompanionServer.DEBUG) {
                String str = CompanionService.TAG;
                Log.d(str, "report: location = " + location);
            }
            if (location != null) {
                Bundle extras = location.getExtras();
                if (extras != null) {
                    this.mTimeZone = extras.getString("timezone", null);
                }
                if (CompanionServer.DEBUG) {
                    String str2 = CompanionService.TAG;
                    Log.d(str2, "location returned timeZone = " + this.mTimeZone);
                }
            }
            this.mLatch.countDown();
        }
    }

    public static class OVRAuthHelper extends OVRAuth {
        private final Context context;
        private final UserHandle user;

        public OVRAuthHelper(final Context context2, UserHandle userHandle) {
            super(context2, new OVRAuth.CallerInfoProvider() {
                /* class com.oculus.companion.server.CompanionService.OVRAuthHelper.AnonymousClass1 */

                @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
                public Intent attachCallerInfo(Intent intent) {
                    CallerInfoHelper.attachCallerInfo(intent, context2, null);
                    return intent;
                }
            });
            this.context = context2;
            this.user = userHandle;
        }

        @Override // com.oculus.authapi.OVRAuth
        public void startAuthService(Intent intent) {
            this.context.startServiceAsUser(intent, this.user);
        }
    }

    public static class WifiTuple implements Parcelable {
        public final String capabilities;
        public Integer level;
        public final String ssid;

        public int describeContents() {
            return 0;
        }

        public WifiTuple(String str, String str2, Integer num) {
            this.ssid = str;
            this.capabilities = str2;
            this.level = num;
        }

        public String getSsid() {
            return this.ssid;
        }

        public String getCapabilities() {
            return this.capabilities;
        }

        public int getSignalLevel() {
            return this.level.intValue();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.ssid);
            parcel.writeString(this.capabilities);
            parcel.writeInt(this.level.intValue());
        }
    }

    public static class HorizonLoginReceiver extends ResultReceiver {
        public AtomicBoolean ready = new AtomicBoolean(false);
        private Bundle result;
        private int resultCode;

        public HorizonLoginReceiver() {
            super(null);
        }

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            this.resultCode = i;
            this.result = bundle;
            this.ready.set(true);
            synchronized (this) {
                notifyAll();
            }
        }

        public Bundle getResult() {
            return this.result;
        }

        public boolean wasSuccessful() {
            return this.resultCode == -1;
        }
    }

    public static ResultReceiver getCrossPackageReceiver(ResultReceiver resultReceiver) {
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }

    public static List<WifiTuple> getWifiNetworks(WifiModule wifiModule) throws InterruptedException {
        if (wifiModule == null) {
            return Collections.emptyList();
        }
        List<ScanResult> ScanSSIDs = wifiModule.ScanSSIDs();
        if (ScanSSIDs == null) {
            Log.e(TAG, "ScanSSIDs returned null");
            return Collections.emptyList();
        }
        HashMap hashMap = new HashMap();
        for (ScanResult scanResult : ScanSSIDs) {
            String str = scanResult.SSID;
            if (!hashMap.containsKey(str)) {
                hashMap.put(str, new WifiTuple(str, scanResult.capabilities, Integer.valueOf(scanResult.level)));
            } else if (((WifiTuple) hashMap.get(str)).level.intValue() < scanResult.level) {
                hashMap.remove(str);
                hashMap.put(str, new WifiTuple(str, scanResult.capabilities, Integer.valueOf(scanResult.level)));
            }
        }
        ArrayList arrayList = new ArrayList(hashMap.values());
        Collections.sort(arrayList, new Comparator<WifiTuple>() {
            /* class com.oculus.companion.server.CompanionService.AnonymousClass1 */

            public int compare(WifiTuple wifiTuple, WifiTuple wifiTuple2) {
                return wifiTuple.level.intValue() - wifiTuple2.level.intValue();
            }
        });
        return arrayList;
    }

    /* JADX INFO: finally extract failed */
    public static WifiModule.WifiModuleState provisionWifi(WifiModule wifiModule, String str, String str2, String str3, Protocol$WifiAuthentication protocol$WifiAuthentication, boolean z) throws InterruptedException {
        if (Build.VERSION.SDK_INT > 25) {
            str = "\"" + str + "\"";
        }
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Adding wifi configuration for ssid: " + str);
        }
        WifiModule.WifiModuleState wifiModuleState = WifiModule.WifiModuleState.WIFI_UNKNOWN;
        if (wifiModule == null) {
            return wifiModuleState;
        }
        WifiModule.WifiModuleState newWifiConf = wifiModule.setNewWifiConf(str, str2, str3, protocol$WifiAuthentication, z);
        if (newWifiConf != WifiModule.WifiModuleState.WIFI_CONNECTED) {
            return newWifiConf;
        }
        boolean isConnectedToInternet = wifiModule.isConnectedToInternet();
        if (!isConnectedToInternet) {
            WifiModule.NotifyingReceiver notifyingReceiver = new WifiModule.NotifyingReceiver();
            IntentFilter intentFilter = new IntentFilter("com.oculus.companion.server.WifiModule.INTERNET_CONNECTED");
            LocalBroadcastManager lbm = wifiModule.getLbm();
            if (lbm != null) {
                lbm.registerReceiver(notifyingReceiver, intentFilter);
                try {
                    synchronized (notifyingReceiver) {
                        wifiModule.tryConnectToInternet(2);
                        notifyingReceiver.wait(10000);
                        isConnectedToInternet = wifiModule.isConnectedToInternet();
                    }
                    lbm.unregisterReceiver(notifyingReceiver);
                    if (CompanionServer.DEBUG) {
                        Log.d(TAG, "Check internet connection: " + isConnectedToInternet);
                    }
                } catch (Throwable th) {
                    lbm.unregisterReceiver(notifyingReceiver);
                    throw th;
                }
            }
        }
        return isConnectedToInternet ? WifiModule.WifiModuleState.WIFI_OCULUS_REACHABLE : newWifiConf;
    }

    public static boolean pinSet(Context context, String str, String str2, Protocol$CredentialLockMethod protocol$CredentialLockMethod, int i, boolean z) {
        LockPatternUtilsCompat lockPatternUtilsCompat = new LockPatternUtilsCompat(context);
        if (protocol$CredentialLockMethod == Protocol$CredentialLockMethod.PATTERN) {
            lockPatternUtilsCompat.saveLockPattern(LockPatternUtils.stringToPattern(str), str2, i);
            lockPatternUtilsCompat.setVisiblePatternEnabled(true, i);
        } else if (protocol$CredentialLockMethod == Protocol$CredentialLockMethod.PASSWORD) {
            lockPatternUtilsCompat.saveLockPassword(str, str2, 131072, i);
            lockPatternUtilsCompat.setVisiblePasswordEnabled(true, i);
        }
        if (!z) {
            return true;
        }
        return updateKeyguardStatus(context, new CompanionKeyguardCallback() {
            /* class com.oculus.companion.server.CompanionService.AnonymousClass2 */

            @Override // com.oculus.companion.server.CompanionService.CompanionKeyguardCallback
            public void updateKeyguardStatus(IKeyguardService iKeyguardService) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("COMPANION_SERVER_PIN_SET", true);
                    iKeyguardService.doKeyguardTimeout(bundle);
                } catch (RemoteException e) {
                    Log.e(CompanionService.TAG, "updateKeyguardStatus: ", e);
                }
            }
        });
    }

    public static Bundle pinVerify(Context context, String str) {
        Bundle bundle = new Bundle();
        LockPatternUtils lockPatternUtils = new LockPatternUtils(context);
        boolean z = false;
        try {
            if (lockPatternUtils.isLockPatternEnabled(0)) {
                z = lockPatternUtils.checkPattern(LockPatternUtils.stringToPattern(str), 0);
            } else if (lockPatternUtils.isLockPasswordEnabled(0)) {
                z = lockPatternUtils.checkPassword(str, 0);
            }
        } catch (LockPatternUtils.RequestThrottledException e) {
            bundle.putInt("PIN_VERIFY_TIMEOUT", e.getTimeoutMs());
        } catch (Exception e2) {
            String str2 = TAG;
            Log.e(str2, "Exception: " + e2.getMessage());
        }
        bundle.putBoolean("PIN_VERIFY_RESULT", z);
        return bundle;
    }

    public static boolean pinReset(Context context, int i, String str) {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "pinReset() called");
        }
        final LockPatternUtilsCompat lockPatternUtilsCompat = new LockPatternUtilsCompat(context);
        if (str == null) {
            str = "";
        }
        lockPatternUtilsCompat.clearPattern(i, str);
        lockPatternUtilsCompat.setLockScreenDisabled(true, i);
        return updateKeyguardStatus(context, new CompanionKeyguardCallback() {
            /* class com.oculus.companion.server.CompanionService.AnonymousClass3 */

            @Override // com.oculus.companion.server.CompanionService.CompanionKeyguardCallback
            public void updateKeyguardStatus(IKeyguardService iKeyguardService) {
                try {
                    LockPatternUtilsCompat.this.dismissKeyguard(iKeyguardService);
                } catch (RemoteException e) {
                    Log.e(CompanionService.TAG, "updateKeyguardStatus: ", e);
                }
            }
        });
    }

    public static int pinStatus(Context context, int i) {
        int i2;
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        if (keyguardManager.isDeviceLocked(i)) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "KeyGuardManager: Device is locked");
            }
            i2 = 2;
        } else {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "KeyGuardManager: Device is not locked");
            }
            i2 = 0;
        }
        if (keyguardManager.isDeviceSecure(i)) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "KeyGuardManager: Device is secure");
            }
            i2 |= 1;
        } else if (CompanionServer.DEBUG) {
            Log.d(TAG, "KeyGuardManager: Device is not secure");
        }
        if (CompanionServer.DEBUG) {
            if (keyguardManager.isKeyguardLocked()) {
                Log.d(TAG, "KeyGuardManager: Keyguard is locked");
            } else {
                Log.d(TAG, "KeyGuardManager: Keyguard is not locked");
            }
            if (keyguardManager.isKeyguardSecure()) {
                Log.d(TAG, "KeyGuardManager: Keyguard is secure");
            } else {
                Log.d(TAG, "KeyGuardManager: Keyguard is not secure");
            }
        }
        LockPatternUtils lockPatternUtils = new LockPatternUtils(context);
        if (lockPatternUtils.isLockPasswordEnabled(i)) {
            return i2 | 4;
        }
        if (!CompanionServer.DEBUG || !lockPatternUtils.isLockPatternEnabled(i)) {
            return i2;
        }
        Log.d(TAG, "Saved pattern exists");
        return i2;
    }

    /* access modifiers changed from: private */
    public static class KGServiceConnection implements ServiceConnection {
        private KGServiceConnection() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (CompanionServer.DEBUG) {
                Log.d(CompanionService.TAG, "Bound to KG service");
            }
            IKeyguardService unused = CompanionService.mIks = IKeyguardService.Stub.asInterface(iBinder);
            synchronized (CompanionService.kgsLock) {
                CompanionService.kgsLock.notify();
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (CompanionServer.DEBUG) {
                Log.d(CompanionService.TAG, "Disconnected from KG service");
            }
            KGServiceConnection unused = CompanionService.mKGConnection = null;
        }
    }

    private static void sendStringResult(Intent intent, String str) {
        ResultReceiver resultReceiver;
        Bundle extras = intent.getExtras();
        if (extras != null && (resultReceiver = (ResultReceiver) extras.getParcelable("RESULT_RECEIVER")) != null) {
            Bundle bundle = new Bundle();
            bundle.putString("CS_RESULT", str);
            resultReceiver.send(0, bundle);
        }
    }

    private static void sendIntResult(Intent intent, int i) {
        ResultReceiver resultReceiver;
        Bundle extras = intent.getExtras();
        if (extras != null && (resultReceiver = (ResultReceiver) extras.getParcelable("RESULT_RECEIVER")) != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("CS_RESULT", i);
            resultReceiver.send(0, bundle);
        }
    }

    private static void sendBooleanResult(Intent intent, boolean z) {
        ResultReceiver resultReceiver;
        Bundle extras = intent.getExtras();
        if (extras != null && (resultReceiver = (ResultReceiver) extras.getParcelable("RESULT_RECEIVER")) != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("CS_RESULT", z);
            resultReceiver.send(0, bundle);
        }
    }

    public static Bundle pinUnlock(Context context, String str) throws InterruptedException {
        new LockPatternUtils(context);
        Bundle bundle = new Bundle();
        final LockPatternUtilsCompat lockPatternUtilsCompat = new LockPatternUtilsCompat(context);
        boolean z = false;
        try {
            boolean checkPatternOrPassword = lockPatternUtilsCompat.checkPatternOrPassword(str, 0);
            if (checkPatternOrPassword) {
                updateKeyguardStatus(context, new CompanionKeyguardCallback() {
                    /* class com.oculus.companion.server.CompanionService.AnonymousClass4 */

                    @Override // com.oculus.companion.server.CompanionService.CompanionKeyguardCallback
                    public void updateKeyguardStatus(IKeyguardService iKeyguardService) {
                        try {
                            LockPatternUtilsCompat.this.dismissKeyguard(iKeyguardService);
                        } catch (RemoteException e) {
                            Log.e(CompanionService.TAG, "updateKeyguardStatus: ", e);
                        }
                    }
                });
            }
            z = checkPatternOrPassword;
        } catch (LockPatternUtils.RequestThrottledException e) {
            bundle.putInt("PIN_VERIFY_TIMEOUT", e.getTimeoutMs());
        } catch (Exception e2) {
            String str2 = TAG;
            Log.e(str2, "some other exception: " + e2.getMessage());
        }
        bundle.putBoolean("PIN_VERIFY_RESULT", z);
        return bundle;
    }

    public static int horizonLogin(Context context, String str, String str2, UserHandle userHandle) throws InterruptedException {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Oculus Login");
        }
        return horizonAuth(context, "companion.OCULUS_AUTH_LOGIN", str, str2, userHandle, 120000);
    }

    public static int horizonLogout(Context context, UserHandle userHandle) throws InterruptedException {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Oculus Logout");
        }
        return horizonAuth(context, "companion.OCULUS_AUTH_LOGOUT", "", null, userHandle, 120000);
    }

    public static int horizonStatus(Context context, UserHandle userHandle) throws InterruptedException {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Oculus Status");
        }
        return horizonAuth(context, "companion.OCULUS_AUTH_STATUS", "", null, userHandle, 120000);
    }

    public static String getUserIdFromHorizonLoggedInStatus(Context context, UserHandle userHandle) throws InterruptedException {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Horizon logged in status for user id");
        }
        return getUserIdFromHorizonAuthStatus(context, "companion.OCULUS_AUTH_LOGGED_IN_USER_ID", "", userHandle, 120000);
    }

    private static int horizonAuth(Context context, String str, String str2, String str3, UserHandle userHandle, long j) throws InterruptedException {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Oculus Horizon Auth Handler");
        }
        HorizonLoginReceiver makeOVRAuthCall = makeOVRAuthCall(context, str, str2, str3, userHandle, j);
        if (!makeOVRAuthCall.ready.get()) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Not ready - Timed Out?");
            }
            return Protocol$ErrorCode.TIMED_OUT.getNumber();
        }
        Bundle result = makeOVRAuthCall.getResult();
        if (makeOVRAuthCall.wasSuccessful()) {
            updateHorizonLoginStatus(context, !"companion.OCULUS_AUTH_LOGOUT".equals(str));
            if (!CompanionServer.DEBUG) {
                return 1;
            }
            Log.d(TAG, "SUCCESS");
            return 1;
        }
        if ("companion.OCULUS_AUTH_STATUS".equals(str)) {
            updateHorizonLoginStatus(context, false);
        }
        return result.getInt("error_code");
    }

    private static String getUserIdFromHorizonAuthStatus(Context context, String str, String str2, UserHandle userHandle, long j) throws InterruptedException {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Get user id from Horizon auth status");
        }
        HorizonLoginReceiver makeOVRAuthCall = makeOVRAuthCall(context, str, str2, null, userHandle, j);
        if (!makeOVRAuthCall.ready.get()) {
            return null;
        }
        Bundle result = makeOVRAuthCall.getResult();
        if (!makeOVRAuthCall.wasSuccessful() || result == null) {
            return null;
        }
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Received successful auth status response from Horizon.");
        }
        return result.getString("user_id", LOGGED_OUT_USER_ID);
    }

    private static HorizonLoginReceiver makeOVRAuthCall(Context context, String str, String str2, String str3, UserHandle userHandle, long j) throws InterruptedException {
        OVRAuthHelper oVRAuthHelper = new OVRAuthHelper(context, userHandle);
        HorizonLoginReceiver horizonLoginReceiver = new HorizonLoginReceiver();
        ResultReceiver crossPackageReceiver = getCrossPackageReceiver(horizonLoginReceiver);
        if ("companion.OCULUS_AUTH_LOGIN".equals(str)) {
            oVRAuthHelper.loginWithCredentials(str3, str2, crossPackageReceiver);
        } else if ("companion.OCULUS_AUTH_LOGOUT".equals(str)) {
            oVRAuthHelper.logout(crossPackageReceiver);
        } else if ("companion.OCULUS_AUTH_STATUS".equals(str) || "companion.OCULUS_AUTH_LOGGED_IN_USER_ID".equals(str)) {
            oVRAuthHelper.status(crossPackageReceiver);
        }
        long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) + j;
        long millis2 = millis - TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
        synchronized (horizonLoginReceiver) {
            while (!horizonLoginReceiver.ready.get() && millis2 > 0) {
                horizonLoginReceiver.wait(millis2);
                millis2 = millis - TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
            }
        }
        return horizonLoginReceiver;
    }

    private static void updateHorizonLoginStatus(Context context, boolean z) {
        new SecureStorage(context).storeValue("login_status", z);
    }

    public static void wipeData(Context context) {
        Intent intent = new Intent(context, CompanionService.class);
        intent.setAction("companion.WIPE_DATA");
        context.startServiceAsUser(intent, UserHandle.SYSTEM);
    }

    public static boolean setMtp(Context context, boolean z) {
        UsbManager usbManager = (UsbManager) context.getSystemService(UsbManager.class);
        boolean isFunctionEnabled = usbManager.isFunctionEnabled("mtp");
        String str = TAG;
        Log.i(str, "Current MTP setting:" + isFunctionEnabled + ", mode requested: " + z);
        if (z) {
            usbManager.setCurrentFunction("mtp", true);
        } else {
            usbManager.setCurrentFunction("none", false);
        }
        return usbManager.isFunctionEnabled("mtp");
    }

    public static boolean getMtp(Context context) {
        boolean isFunctionEnabled = ((UsbManager) context.getSystemService(UsbManager.class)).isFunctionEnabled("mtp");
        if (CompanionServer.DEBUG) {
            String str = TAG;
            Log.d(str, "Current MTP setting:" + isFunctionEnabled);
        }
        return isFunctionEnabled;
    }

    public static boolean setControllerHandedness(Context context, String str) {
        if (CompanionServer.DEBUG) {
            String str2 = TAG;
            Log.d(str2, "Trying to set the value of controller handedness to: " + str);
        }
        VrApiManager instance = VrApiManager.getInstance();
        instance.setDeviceProperty("handedness", str);
        String deviceProperty = instance.getDeviceProperty("handedness");
        if (deviceProperty == null) {
            return false;
        }
        if (CompanionServer.DEBUG) {
            String str3 = TAG;
            Log.d(str3, "Reading the value of controller handedness: " + deviceProperty);
        }
        return str.equals(deviceProperty);
    }

    public static String getControllerHandedness() {
        String deviceProperty = VrApiManager.getInstance().getDeviceProperty("handedness");
        if (deviceProperty == null) {
            Log.e(TAG, "Internal Error: Could not read the handedness. Returning default value");
            return "UNKNOWN";
        }
        if (CompanionServer.DEBUG) {
            String str = TAG;
            Log.d(str, "Read the value of controller handedness: " + deviceProperty);
        }
        return deviceProperty;
    }

    public static boolean isAdbEnabled(Context context) {
        boolean intToBool = intToBool(Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0));
        if (CompanionServer.DEBUG) {
            String str = TAG;
            Log.d(str, "Adb Mode is: " + intToBool);
        }
        return intToBool;
    }

    public static boolean setAdbEnabled(Context context, boolean z) {
        if (CompanionServer.DEBUG) {
            String str = TAG;
            Log.d(str, "Setting Adb Mode: " + z);
        }
        return Settings.Global.putInt(context.getContentResolver(), "adb_enabled", z ? 1 : 0);
    }

    public static boolean setLocale(Context context, String str, String str2) {
        return setLocale(context, str, str2, 0);
    }

    public static boolean setLocale(Context context, String str, String str2, int i) {
        Locale locale;
        if (str != null) {
            if (str2 != null) {
                locale = new Locale(str, str2);
            }
            if (CompanionServer.DEBUG) {
                String str3 = TAG;
                Log.d(str3, "Setting Locale to " + locale.toString());
            }
            Locale.setDefault(locale);
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            try {
                IActivityManager iActivityManager = ActivityManagerNative.getDefault();
                Configuration configuration = iActivityManager.getConfiguration();
                configuration.setLocales(localeList);
                configuration.userSetLocale = true;
                iActivityManager.updateUserPersistentConfiguration(configuration, i);
                if (!FirstTimeNuxManager.getFirstTimeNuxComplete(ActivityManager.getCurrentUser())) {
                    Intent intent = new Intent("com.oculus.companion.server.LOCALE_UPDATED");
                    intent.putExtra("LANGUAGE", str);
                    intent.putExtra("COUNTRY", str2);
                    context.sendBroadcastAsUser(intent, UserHandle.SYSTEM);
                }
                return true;
            } catch (RemoteException e) {
                String str4 = TAG;
                Log.e(str4, "update persitent config failed" + e.getMessage());
                return false;
            }
        } else {
            Log.e(TAG, "Invalid locale - empty language");
            return false;
        }
    }

    static void rebootIfTrueAndNuxCompleted(Context context, boolean z) {
        if (!z) {
            return;
        }
        if (FirstTimeNuxManager.getFirstTimeNuxComplete(ActivityManager.getCurrentUser())) {
            Log.d(TAG, "NUX OTA completed, rebooting");
            ((PowerManager) context.getSystemService("power")).reboot(null);
            return;
        }
        Log.d(TAG, "NUX not completed, not rebooting");
    }

    public static void insertLinkedAccount(Context context, String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("token", str2);
        contentValues.put("user_id", str);
        contentValues.put("service_provider", str3);
        context.getContentResolver().insert(Uri.parse("content://com.oculus.horizon.linkedaccounts/accounts"), contentValues);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
        r8 = com.oculus.companion.server.CompanionService.mKGConnection;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0042, code lost:
        if (r8 == null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
        r7.unbindService(r8);
        com.oculus.companion.server.CompanionService.mKGConnection = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean updateKeyguardStatus(android.content.Context r7, com.oculus.companion.server.CompanionService.CompanionKeyguardCallback r8) {
        /*
        // Method dump skipped, instructions count: 175
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionService.updateKeyguardStatus(android.content.Context, com.oculus.companion.server.CompanionService$CompanionKeyguardCallback):boolean");
    }

    public static Bundle enableCasting(Context context, boolean z, long j) throws InterruptedException {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "VrCasting");
        }
        Bundle bundle = new Bundle();
        HorizonLoginReceiver horizonLoginReceiver = new HorizonLoginReceiver();
        ResultReceiver crossPackageReceiver = getCrossPackageReceiver(horizonLoginReceiver);
        String str = z ? "com.oculus.horizon.START_CAST_SERVER" : "com.oculus.horizon.STOP_CAST_SERVER";
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.horizon.service_media.OVRMediaService"));
        intent.putExtra("message_type", str);
        intent.putExtra("RESULT_RECEIVER", crossPackageReceiver);
        CallerInfoHelper.attachCallerInfo(intent, context, "com.oculus.companion.server");
        context.startService(intent);
        if (!z) {
            return bundle;
        }
        long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) + j;
        long millis2 = millis - TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
        synchronized (horizonLoginReceiver) {
            while (!horizonLoginReceiver.ready.get() && millis2 > 0) {
                horizonLoginReceiver.wait(millis2);
                millis2 = millis - TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
            }
        }
        if (!horizonLoginReceiver.ready.get()) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Not ready - Timed Out?");
            }
            bundle.putBoolean("SUCCESS", false);
            return bundle;
        }
        Bundle result = horizonLoginReceiver.getResult();
        result.putBoolean("SUCCESS", true);
        return result;
    }

    public static boolean sendText(Context context, String str) {
        if (str == null) {
            Log.e(TAG, "Invalid text - empty");
            return false;
        }
        ComponentName componentName = new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.ShellControlBroadcastReceiver");
        Intent intent = new Intent();
        intent.setAction("com.oculus.vrshell.intent.action.SEND_KEYS");
        intent.setComponent(componentName);
        intent.putExtra("input_type", "text");
        intent.putExtra("input_keys", str);
        context.sendBroadcast(intent);
        return true;
    }

    public static boolean sendRenderKeyboardStart(Context context, String str) {
        ComponentName componentName = new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.ShellControlBroadcastReceiver");
        Intent intent = new Intent();
        intent.setAction("com.oculus.vrshell.intent.action.RENDER_KEYBOARD_START");
        intent.setComponent(componentName);
        intent.putExtra("hardwareId", str);
        context.sendBroadcast(intent);
        return true;
    }

    public static boolean sendRenderKeyboardStop(Context context, String str) {
        ComponentName componentName = new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.ShellControlBroadcastReceiver");
        Intent intent = new Intent();
        intent.setAction("com.oculus.vrshell.intent.action.RENDER_KEYBOARD_STOP");
        intent.setComponent(componentName);
        intent.putExtra("hardwareId", str);
        context.sendBroadcast(intent);
        return true;
    }

    public static boolean setLineFrequency(Context context, int i) {
        String str = TAG;
        Log.d(str, "Setting Electrical Line Frequency to: " + i);
        new SettingsManager().setInt("electric_grid_line_frequency", i);
        return true;
    }

    public static int getLineFrequency(Context context) {
        int i = new SettingsManager().getInt("electric_grid_line_frequency", -1);
        String str = TAG;
        Log.d(str, "Current Electrical Line Frequency mode is: " + i);
        return i;
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        long currentTimeMillis = System.currentTimeMillis();
        String str = "";
        if (intent != null) {
            try {
                str = intent.getAction();
                if (CompanionServer.DEBUG) {
                    String str2 = TAG;
                    Log.d(str2, "Received intent action: " + str);
                }
                if ("companion.GET_WIFI_LIST".equals(str)) {
                    handleGetWifiList(intent);
                } else if ("companion.SET_WIFI".equals(str)) {
                    handleProvisionWifi(intent);
                } else {
                    if (!"companion.OCULUS_AUTH_LOGIN".equals(str) && !"companion.OCULUS_AUTH_LOGOUT".equals(str)) {
                        if (!"companion.OCULUS_AUTH_STATUS".equals(str)) {
                            if ("companion.WIPE_DATA".equals(str)) {
                                handleWipeData();
                            } else if ("companion.ADB_MODE".equals(str)) {
                                handleAdbMode(intent);
                            } else if ("companion.MTP_MODE".equals(str)) {
                                handleMtpMode(intent);
                            } else if (!"companion.DEV_MODE".equals(str)) {
                                if ("companion.PIN_SET".equals(str)) {
                                    handlePinSet(intent);
                                } else if ("companion.PIN_RESET".equals(str)) {
                                    handlePinReset(intent);
                                } else if ("companion.PIN_STATUS".equals(str)) {
                                    handlePinStatus(intent);
                                } else if ("companion.PIN_VERIFY".equals(str)) {
                                    handlePinVerify(intent);
                                } else if ("companion.PIN_UNLOCK".equals(str)) {
                                    handlePinUnlock(intent);
                                } else if ("companion.CONTROLLER_STATUS".equals(str)) {
                                    handleControllerStatus(intent);
                                } else if ("companion.CONTROLLER_UNPAIR".equals(str)) {
                                    handleControllerUnpair(intent);
                                } else if ("companion.CONTROLLER_SCAN_AND_PAIR".equals(str)) {
                                    handleControllerScanAndPair(intent);
                                } else if ("companion.CONTROLLER_VERIFY_CONNECTABLE".equals(str)) {
                                    handleControllerVerifyConnectable(intent);
                                } else if ("companion.VERIFY_ALL_CONTROLLERS_CONNECTABLE".equals(str)) {
                                    handleVerifyAllControllersConnectable(intent);
                                } else if ("companion.CONTROLLER_SET_HANDEDNESS".equals(str)) {
                                    handleControllerSetHandedness(intent);
                                } else if ("companion.BT_SCAN".equals(str)) {
                                    handleBluetoothDeviceScan(intent);
                                } else if ("companion.BT_BOND".equals(str)) {
                                    handleBluetoothBondDevice(intent);
                                } else if ("companion.BT_CONNECT".equals(str)) {
                                    handleBluetoothConnectDevice(intent);
                                } else if ("companion.BT_UNPAIR".equals(str)) {
                                    handleBluetoothUnpairDevice(intent);
                                } else {
                                    if (!"companion.START_MONITOR".equals(str) && !"companion.STOP_MONITOR".equals(str)) {
                                        if (!"companion.STATE_MONITOR".equals(str)) {
                                            if ("companion.LOCALE".equals(str)) {
                                                handleLocale(intent);
                                            } else if ("companion.OTA_CHECK_AVAILABILITY".equals(str)) {
                                                handleCheckAvailability(intent);
                                            } else if ("companion.OTA_UPDATE".equals(str)) {
                                                handleAttemptUpdate(intent);
                                            } else if ("companion.ENABLE_CAST".equals(str)) {
                                                handleCasting(intent);
                                            } else if ("companion.MANAGED_DEVICE".equals(str)) {
                                                handleManagedMode(intent);
                                            } else if ("companion.DESTROY_USER_KEY".equals(str)) {
                                                handleDestroyUserKey(intent);
                                            } else if ("companion.NAME_SET".equals(str)) {
                                                handleNameSet(intent);
                                            } else if ("companion.APP_LAUNCH".equals(str)) {
                                                handleAppLaunch(intent);
                                            } else if ("companion.LINE_FREQUENCY_SET".equals(str)) {
                                                handleLineFrequencySet(intent);
                                            } else if ("companion.LINE_FREQUENCY_STATUS".equals(str)) {
                                                handleLineFrequencyStatus(intent);
                                            } else if ("companion.OTA_ENABLE".equals(str)) {
                                                handleSetOTAEnableStatus(intent);
                                            } else if ("companion.UPDATE_HORIZON_STATE".equals(str)) {
                                                handleUpdateHorizonState(intent);
                                            } else if ("companion.SET_TIME_ZONE".equals(str)) {
                                                handleSetTimeZone(intent);
                                            } else if ("companion.DISABLE_BLE_ADV".equals(str)) {
                                                handleDisableBleAdv(intent);
                                            } else if ("companion.INJECT_BLE".equals(str)) {
                                                handleInjectBleMsg(intent);
                                            }
                                        }
                                    }
                                    handleMonitor(intent);
                                }
                            }
                        }
                    }
                    handleHorizonAuth(intent);
                }
            } catch (RuntimeException e) {
                e.toString();
                Log.getStackTraceString(e);
                throw e;
            } catch (Throwable th) {
                if (this.mTelemetry == null) {
                    this.mTelemetry = new Telemetry(getApplicationContext());
                }
                this.mTelemetry.logIntentInvoke(str, System.currentTimeMillis() - currentTimeMillis, "", "");
                throw th;
            }
        }
        if (this.mTelemetry == null) {
            this.mTelemetry = new Telemetry(getApplicationContext());
        }
        this.mTelemetry.logIntentInvoke(str, System.currentTimeMillis() - currentTimeMillis, "", "");
    }

    private void handlePinSet(Intent intent) {
        Protocol$CredentialLockMethod protocol$CredentialLockMethod;
        Protocol$CredentialLockMethod protocol$CredentialLockMethod2;
        String stringExtra = intent.getStringExtra("PIN");
        String stringExtra2 = intent.getStringExtra("PIN2");
        int intExtra = intent.getIntExtra("USER_ID", 0);
        boolean booleanExtra = intent.getBooleanExtra("LOCK_AFTER_PIN_SET", true);
        Protocol$CredentialLockMethod protocol$CredentialLockMethod3 = Protocol$CredentialLockMethod.PATTERN;
        String stringExtra3 = intent.getStringExtra("LOCK_METHOD");
        if ("PATTERN".equals(stringExtra3)) {
            protocol$CredentialLockMethod2 = Protocol$CredentialLockMethod.PATTERN;
        } else if ("PASSWORD".equals(stringExtra3)) {
            protocol$CredentialLockMethod2 = Protocol$CredentialLockMethod.PASSWORD;
        } else if (stringExtra3 != null) {
            String str = TAG;
            Log.e(str, "Set pin invalid argument: " + stringExtra3);
            return;
        } else {
            protocol$CredentialLockMethod = protocol$CredentialLockMethod3;
            pinSet(this, stringExtra, stringExtra2, protocol$CredentialLockMethod, intExtra, booleanExtra);
        }
        protocol$CredentialLockMethod = protocol$CredentialLockMethod2;
        pinSet(this, stringExtra, stringExtra2, protocol$CredentialLockMethod, intExtra, booleanExtra);
    }

    private void handlePinReset(Intent intent) {
        pinReset(this, intent.getIntExtra("USER_ID", 0), intent.getStringExtra("PIN"));
    }

    private void handlePinVerify(Intent intent) {
        pinVerify(this, intent.getStringExtra("PIN"));
    }

    private void handlePinStatus(Intent intent) {
        ResultReceiver resultReceiver;
        int pinStatus = pinStatus(this, getUserFromIntent(intent, 0));
        Bundle extras = intent.getExtras();
        if (extras != null && (resultReceiver = (ResultReceiver) extras.getParcelable("RESULT_RECEIVER")) != null) {
            Bundle bundle = new Bundle();
            bundle.putByte("CS_RESULT", (byte) pinStatus);
            resultReceiver.send(0, bundle);
        }
    }

    private int getUserFromIntent(Intent intent, int i) {
        UserHandle userHandle = (UserHandle) intent.getParcelableExtra("USER_HANDLE");
        if (userHandle != null) {
            return userHandle.getIdentifier();
        }
        return intent.getIntExtra("USER_ID", i);
    }

    private void handlePinUnlock(Intent intent) {
        try {
            pinUnlock(this, intent.getStringExtra("PIN"));
        } catch (InterruptedException e) {
            Log.e(TAG, "Operation Interrupted: ", e);
        }
    }

    private void handleControllerStatus(Intent intent) {
        try {
            String pairedDevice = ControllerManager.getPairedDevice(intent.getIntExtra("TYPE", 0));
            String controllerHandedness = getControllerHandedness();
            if (CompanionServer.DEBUG) {
                String str = TAG;
                Log.d(str, "Address " + pairedDevice);
            }
            if (CompanionServer.DEBUG) {
                String str2 = TAG;
                Log.d(str2, "Handedness " + controllerHandedness);
            }
            sendStringResult(intent, pairedDevice + "-" + controllerHandedness);
        } catch (InterruptedException e) {
            Log.e(TAG, "Controller Status Interrupted: ", e);
        }
    }

    private void handleControllerUnpair(Intent intent) {
        try {
            sendBooleanResult(intent, ControllerManager.unpairDevice(intent.getIntExtra("TYPE", 0)));
        } catch (InterruptedException e) {
            Log.e(TAG, "Controller Unpair Interrupted: ", e);
        }
    }

    private void handleControllerScanAndPair(Intent intent) {
        boolean z = false;
        try {
            ControllerManager.ControllerScanAndPairResult scanAndPairDevice = ControllerManager.scanAndPairDevice(intent.getIntExtra("TYPE", 0), (int) intent.getLongExtra("TIMEOUT", 15000));
            if (CompanionServer.DEBUG) {
                String str = TAG;
                Log.d(str, "ControllerScanAndPair: " + scanAndPairDevice);
            }
            if (scanAndPairDevice == ControllerManager.ControllerScanAndPairResult.SUCCESS) {
                z = true;
            }
            sendBooleanResult(intent, z);
        } catch (InterruptedException e) {
            Log.e(TAG, "Operation Interrupted: ", e);
        }
    }

    private void handleControllerSetHandedness(Intent intent) {
        boolean z;
        if (intent.hasExtra("SET")) {
            z = setControllerHandedness(this, intent.getStringExtra("SET"));
        } else {
            Log.e(TAG, "Handedness Value is not provided");
            z = false;
        }
        sendBooleanResult(intent, z);
    }

    private void handleControllerVerifyConnectable(Intent intent) {
        int intExtra = intent.getIntExtra("TYPE", 0);
        try {
            int[] verifyConnectable = ControllerManager.verifyConnectable(new int[]{intExtra}, (int) intent.getLongExtra("TIMEOUT", 0), (int) intent.getLongExtra("RECENT_TIME_LIMIT", 0));
            if (CompanionServer.DEBUG) {
                String str = TAG;
                Log.d(str, "ControllerVerifyConnectable: " + verifyConnectable[0]);
            }
            sendIntResult(intent, verifyConnectable[0]);
        } catch (InterruptedException e) {
            Log.e(TAG, "Operation Interrupted: ", e);
        }
    }

    private void handleVerifyAllControllersConnectable(Intent intent) {
        try {
            String str = "";
            for (int i : ControllerManager.verifyConnectable(ControllerManager.getDeviceTypes(), (int) intent.getLongExtra("TIMEOUT", 0), (int) intent.getLongExtra("RECENT_TIME_LIMIT", 0))) {
                str = str + i + "\n";
            }
            sendStringResult(intent, str);
        } catch (InterruptedException e) {
            Log.e(TAG, "Operation Interrupted: ", e);
        }
    }

    private void handleBluetoothDeviceScan(Intent intent) {
        try {
            ControllerManager.bluetoothBondedThirdPartyDevices();
            ControllerManager.bluetoothDeviceScan(this);
        } catch (InterruptedException e) {
            Log.e(TAG, "Operation Interrupted: ", e);
        }
    }

    private void handleBluetoothBondDevice(Intent intent) {
        String stringExtra = intent.getStringExtra("BT_ADDRESS");
        if (stringExtra != null) {
            try {
                ControllerManager.bluetoothBondDevice(this, stringExtra, false);
            } catch (InterruptedException e) {
                Log.e(TAG, "Operation Interrupted: ", e);
            }
        } else if (CompanionServer.DEBUG) {
            Log.d(TAG, "Empty address");
        }
    }

    private void handleBluetoothConnectDevice(Intent intent) {
        String stringExtra = intent.getStringExtra("BT_ADDRESS");
        if (stringExtra != null) {
            try {
                ControllerManager.bluetoothConnectDevice(this, stringExtra, false);
            } catch (InterruptedException e) {
                Log.e(TAG, "Operation Interrupted: ", e);
            }
        } else if (CompanionServer.DEBUG) {
            Log.d(TAG, "Empty address");
        }
    }

    private void handleBluetoothUnpairDevice(Intent intent) {
        String stringExtra = intent.getStringExtra("BT_ADDRESS");
        if (stringExtra != null) {
            ControllerManager.bluetoothUnpairDevice(stringExtra);
        } else if (CompanionServer.DEBUG) {
            Log.d(TAG, "Empty address");
        }
    }

    private void handleLineFrequencySet(Intent intent) {
        sendBooleanResult(intent, setLineFrequency(this, intent.getIntExtra("LINE_FREQUENCY", -1)));
    }

    private void handleLineFrequencyStatus(Intent intent) {
        sendIntResult(intent, getLineFrequency(this));
    }

    private void handleSetOTAEnableStatus(Intent intent) {
        try {
            SystemProperties.set("persist.ovr.ota.enabled", intent.getBooleanExtra("SET", true) ? "true" : "false");
        } catch (IllegalArgumentException e) {
            String str = TAG;
            Log.e(str, "Could not set the OTA_ENABLE state. Failed with message: " + e.getMessage());
        }
    }

    private void handleMtpMode(Intent intent) {
        sendBooleanResult(intent, setMtp(this, intent.getBooleanExtra("MODE", false)));
    }

    private void handleAdbMode(Intent intent) {
        int intExtra = intent.getIntExtra("MODE", -1);
        if (intExtra >= 0) {
            setAdbEnabled(this, intToBool(intExtra));
        }
        sendIntResult(intent, isAdbEnabled(this) ? 1 : 0);
    }

    private void handleWipeData() {
        CompanionDeviceAdmin.wipeData(this);
    }

    private void handleHorizonAuth(Intent intent) {
        Log.w(TAG, "Oculus Horizon Auth Handler");
        String action = intent.getAction();
        long longExtra = intent.getLongExtra("TIMEOUT", 120000);
        try {
            if ("companion.OCULUS_AUTH_LOGIN".equals(action)) {
                horizonAuth(this, action, intent.getStringExtra("AUTH_TOKEN"), intent.getStringExtra("USER_ID"), UserHandle.SYSTEM, longExtra);
            } else if ("companion.OCULUS_AUTH_LOGOUT".equals(action)) {
                horizonAuth(this, action, "", null, UserHandle.SYSTEM, longExtra);
            } else if ("companion.OCULUS_AUTH_STATUS".equals(action)) {
                horizonAuth(this, action, "", null, UserHandle.SYSTEM, longExtra);
            }
        } catch (InterruptedException e) {
            String str = TAG;
            Log.e(str, "Horizon auth action was interrupted!!" + e.getMessage());
        }
    }

    private void handleGetWifiList(Intent intent) {
        ResultReceiver resultReceiver;
        bindToCompanionServer();
        synchronized (csLock) {
            if (csBound && mCS != null) {
                try {
                    List<WifiTuple> wifiNetworks = getWifiNetworks(mCS.getWifiModule());
                    for (WifiTuple wifiTuple : wifiNetworks) {
                        if (CompanionServer.DEBUG) {
                            Log.d(TAG, wifiTuple.getSsid());
                        }
                        if (CompanionServer.DEBUG) {
                            Log.d(TAG, wifiTuple.getCapabilities());
                        }
                        if (CompanionServer.DEBUG) {
                            String str = TAG;
                            Log.d(str, "" + wifiTuple.getSignalLevel());
                        }
                    }
                    Bundle extras = intent.getExtras();
                    if (!(extras == null || (resultReceiver = (ResultReceiver) extras.getParcelable("RESULT_RECEIVER")) == null)) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("CS_RESULT", (ArrayList) wifiNetworks);
                        resultReceiver.send(0, bundle);
                    }
                } catch (InterruptedException e) {
                    Log.e(TAG, "Operation Interrupted: ", e);
                } catch (Throwable th) {
                    unbindFromCompanionServer();
                    throw th;
                }
                unbindFromCompanionServer();
            }
        }
    }

    private void handleNameSet(Intent intent) {
        sendBooleanResult(intent, nameSet(this, intent.getStringExtra("SET")));
    }

    public static boolean nameSet(Context context, String str) {
        boolean z;
        if (str == null) {
            return false;
        }
        int length = str.getBytes(StandardCharsets.UTF_8).length;
        if (length > 248 || length == 0) {
            Log.d(TAG, "nameSet() was called with an empty or too-long name");
            return false;
        }
        if (!Settings.Global.putString(context.getContentResolver(), "device_name", str)) {
            Log.w(TAG, "Failed to set device name");
            z = false;
        } else {
            z = true;
        }
        if (!Settings.Global.putString(context.getContentResolver(), "wifi_p2p_device_name", str)) {
            Log.w(TAG, "Failed to set wifi p2p device name");
            z = false;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.setName(str)) {
            return z;
        }
        Log.w(TAG, "Failed to set Bluetooth name");
        return false;
    }

    private void handleAppLaunch(Intent intent) {
        sendBooleanResult(intent, launchApplication(this, intent.getStringExtra("APP_ID"), intent.getStringExtra("LAUNCH_PARAMS_JSON"), false, 0, null));
    }

    static boolean launchApplication(Context context, final String str, String str2, final boolean z, final int i, final CompanionServer companionServer) {
        if (str == null || "".equals(str)) {
            Log.e(TAG, "LaunchApplication: Received invalid appId");
            return false;
        }
        ResultReceiver crossPackageReceiver = getCrossPackageReceiver(new ResultReceiver(null) {
            /* class com.oculus.companion.server.CompanionService.AnonymousClass5 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                CompanionServer companionServer;
                boolean z = i == -1;
                String string = bundle.containsKey("error_string") ? bundle.getString("error_string") : "";
                if (z) {
                    String str = CompanionService.TAG;
                    Log.d(str, "App Launch success for the app: " + str);
                } else {
                    Log.e(CompanionService.TAG, String.format("App Launch for id:%s failed with error: %s", str, string));
                }
                if (z && (companionServer = companionServer) != null) {
                    companionServer.response_APP_LAUNCH(i, z, string);
                }
            }
        });
        Intent intent = new Intent();
        intent.setAction("com.oculus.horizon.REMOTE_LAUNCH_APP");
        intent.putExtra("app_id", str);
        if (str2 != null) {
            intent.putExtra("launch_params_json", str2);
        }
        intent.putExtra("result_receiver", crossPackageReceiver);
        CallerInfoHelper.attachCallerInfo(intent, context, "com.oculus.companion.server");
        String str3 = TAG;
        Log.i(str3, "Sending Intent to launch Application Id: " + str);
        context.sendBroadcastAsUser(intent, UserHandle.SYSTEM);
        return true;
    }

    private void handleProvisionWifi(Intent intent) {
        String stringExtra = intent.getStringExtra("WIFI_SSID");
        String stringExtra2 = intent.getStringExtra("WIFI_PWD");
        boolean z = false;
        WifiModule.WifiModuleState tryProvisionWifi = tryProvisionWifi(stringExtra, intent.getStringExtra("WIFI_USERNAME"), stringExtra2, intent.getStringExtra("AUTH_LEVEL"), intent.getBooleanExtra("WIFI_HIDDEN", false));
        if (tryProvisionWifi == WifiModule.WifiModuleState.WIFI_OCULUS_REACHABLE || tryProvisionWifi == WifiModule.WifiModuleState.WIFI_CONNECTED) {
            z = true;
        }
        sendBooleanResult(intent, z);
    }

    private Protocol$WifiAuthentication wifiAuthLevelFromString(String str) {
        Protocol$WifiAuthentication protocol$WifiAuthentication = Protocol$WifiAuthentication.WPA;
        if (str != null) {
            try {
                return Protocol$WifiAuthentication.valueOf(str);
            } catch (IllegalArgumentException e) {
                String str2 = TAG;
                Log.e(str2, "Auth value invalid argument: " + e.getMessage());
                return protocol$WifiAuthentication;
            }
        } else {
            Log.w(TAG, "Invalid auth level. Assuming WPA");
            return protocol$WifiAuthentication;
        }
    }

    private WifiModule.WifiModuleState tryProvisionWifi(String str, String str2, String str3, String str4, boolean z) {
        Protocol$WifiAuthentication wifiAuthLevelFromString = wifiAuthLevelFromString(str4);
        WifiModule.WifiModuleState wifiModuleState = WifiModule.WifiModuleState.WIFI_UNKNOWN;
        bindToCompanionServer();
        synchronized (csLock) {
            if (csBound && mCS != null) {
                try {
                    wifiModuleState = provisionWifi(mCS.getWifiModule(), str, str2, str3, wifiAuthLevelFromString, z);
                } catch (InterruptedException e) {
                    Log.e(TAG, "Operation Interrupted: ", e);
                } catch (Throwable th) {
                    unbindFromCompanionServer();
                    throw th;
                }
                unbindFromCompanionServer();
            }
        }
        return wifiModuleState;
    }

    /* access modifiers changed from: private */
    public static class CompanionServerConnection implements ServiceConnection {
        private CompanionServerConnection() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (CompanionServer.DEBUG) {
                Log.d(CompanionService.TAG, "Bound to CS service");
            }
            CompanionServer.LocalBinder unused = CompanionService.binder = (CompanionServer.LocalBinder) iBinder;
            synchronized (CompanionService.csLock) {
                CompanionServer unused2 = CompanionService.mCS = CompanionService.binder.getService();
                CompanionService.csLock.notify();
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (CompanionServer.DEBUG) {
                Log.d(CompanionService.TAG, "Disconnected from CS service");
            }
            synchronized (CompanionService.csLock) {
                CompanionServer unused = CompanionService.mCS = null;
                CompanionServer.LocalBinder unused2 = CompanionService.binder = null;
                boolean unused3 = CompanionService.csBound = false;
            }
        }
    }

    private void bindToCompanionServer() {
        if (!csBound) {
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Connecting to CS");
            }
            Intent intent = new Intent(this, CompanionServer.class);
            try {
                synchronized (csLock) {
                    while (mCS == null) {
                        csBound = bindServiceAsUser(intent, csServerConnection, 1, UserHandle.SYSTEM);
                        csLock.wait(3000);
                    }
                }
            } catch (InterruptedException unused) {
                Log.e(TAG, "Could not bind to Companion Server");
            }
            if (!csBound) {
                Log.e(TAG, "Binding unsuccessful.");
            }
        }
    }

    private void unbindFromCompanionServer() {
        if (csBound) {
            unbindService(csServerConnection);
            csBound = false;
            mCS = null;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:40|41) */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0091, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        android.util.Log.e(com.oculus.companion.server.CompanionService.TAG, "handleMonitor Interrupted");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009e, code lost:
        unbindFromCompanionServer();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a1, code lost:
        throw r8;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x0093 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleMonitor(android.content.Intent r8) {
        /*
        // Method dump skipped, instructions count: 162
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionService.handleMonitor(android.content.Intent):void");
    }

    private void handleLocale(Intent intent) {
        String stringExtra = intent.getStringExtra("LANGUAGE");
        String stringExtra2 = intent.getStringExtra("COUNTRY");
        boolean z = true;
        boolean booleanExtra = intent.getBooleanExtra("SHOULD_REBOOT", true);
        boolean locale = setLocale(this, stringExtra, stringExtra2, getUserFromIntent(intent, 0));
        sendBooleanResult(intent, locale);
        Context applicationContext = getApplicationContext();
        if (!booleanExtra || !locale) {
            z = false;
        }
        rebootIfTrueAndNuxCompleted(applicationContext, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        android.util.Log.e(com.oculus.companion.server.CompanionService.TAG, "handleUpdateHorizonState Interrupted");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        unbindFromCompanionServer();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0021 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleUpdateHorizonState(android.content.Intent r4) {
        /*
            r3 = this;
            r3.bindToCompanionServer()
            r0 = 0
            boolean r1 = com.oculus.companion.server.CompanionService.csBound     // Catch:{ InterruptedException -> 0x0021 }
            if (r1 == 0) goto L_0x0017
            com.oculus.companion.server.CompanionServer r1 = com.oculus.companion.server.CompanionService.mCS     // Catch:{ InterruptedException -> 0x0021 }
            if (r1 == 0) goto L_0x0017
            com.oculus.companion.server.CompanionServer r1 = com.oculus.companion.server.CompanionService.mCS     // Catch:{ InterruptedException -> 0x0021 }
            com.oculus.companion.server.CompanionState r1 = r1.getCompanionState()     // Catch:{ InterruptedException -> 0x0021 }
            r1.updateHorizonState()     // Catch:{ InterruptedException -> 0x0021 }
            r0 = 1
            goto L_0x0028
        L_0x0017:
            java.lang.String r1 = com.oculus.companion.server.CompanionService.TAG     // Catch:{ InterruptedException -> 0x0021 }
            java.lang.String r2 = "Could not bind to Companion Server"
            android.util.Log.e(r1, r2)     // Catch:{ InterruptedException -> 0x0021 }
            goto L_0x0028
        L_0x001f:
            r4 = move-exception
            goto L_0x002f
        L_0x0021:
            java.lang.String r1 = com.oculus.companion.server.CompanionService.TAG     // Catch:{ all -> 0x001f }
            java.lang.String r2 = "handleUpdateHorizonState Interrupted"
            android.util.Log.e(r1, r2)     // Catch:{ all -> 0x001f }
        L_0x0028:
            r3.unbindFromCompanionServer()
            sendBooleanResult(r4, r0)
            return
        L_0x002f:
            r3.unbindFromCompanionServer()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionService.handleUpdateHorizonState(android.content.Intent):void");
    }

    private void handleSetTimeZone(Intent intent) {
        String str;
        boolean z;
        if (intent.hasExtra("TIME_ZONE_ID")) {
            str = intent.getStringExtra("TIME_ZONE_ID");
        } else {
            str = new LocationHelper(this).getTimezone();
        }
        if (!TextUtils.isEmpty(str)) {
            AlarmManager alarmManager = (AlarmManager) getSystemService("alarm");
            if (Arrays.asList(TimeZone.getAvailableIDs()).contains(str)) {
                alarmManager.setTimeZone(str);
                z = true;
                sendBooleanResult(intent, z);
            }
        }
        z = false;
        sendBooleanResult(intent, z);
    }

    private void handleDisableBleAdv(Intent intent) {
        Log.d(TAG, "Disabling BLE advertisement.");
        bindToCompanionServer();
        boolean z = false;
        try {
            if (!csBound || mCS == null) {
                Log.e(TAG, "Could not bind to Companion Server");
            } else {
                z = mCS.getBleModule().stopAdvertising();
            }
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Error: " + e.getLocalizedMessage());
        } catch (Throwable th) {
            unbindFromCompanionServer();
            throw th;
        }
        unbindFromCompanionServer();
        sendBooleanResult(intent, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0072, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0077, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0078, code lost:
        r3.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007b, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleInjectBleMsg(android.content.Intent r4) {
        /*
        // Method dump skipped, instructions count: 151
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionService.handleInjectBleMsg(android.content.Intent):void");
    }

    private void handleCheckAvailability(Intent intent) {
        UpdaterManager.checkUpdateAvailability(this, intent.getBooleanExtra("FULL_UPDATE", false), null, 0);
    }

    private void handleAttemptUpdate(Intent intent) {
        UpdaterManager.attemptUpdate(this, intent.getBooleanExtra("FULL_UPDATE", false));
    }

    private void handleCasting(Intent intent) {
        Log.d(TAG, "handleCasting");
        try {
            Bundle enableCasting = enableCasting(this, true, 20000);
            if (enableCasting.getBoolean("SUCCESS")) {
                Log.i(TAG, "Successfully started casting");
                String str = TAG;
                Log.i(str, "URL=" + enableCasting.getString("cast_server_url"));
                String str2 = TAG;
                Log.i(str2, "Session Id=" + enableCasting.getString("cast_session_id"));
                return;
            }
            String str3 = TAG;
            Log.e(str3, "Error Starting Casting = " + enableCasting.getString("cast_server_error"));
        } catch (InterruptedException e) {
            Log.e(TAG, "Operation interrupted", e);
        }
    }

    private void handleManagedMode(Intent intent) {
        SecureStorage secureStorage = new SecureStorage(this);
        int intValue = secureStorage.getIntValue("managed_device");
        int i = SystemProperties.getInt("com.oculus.managed", -1);
        String str = TAG;
        Log.d(str, "Managed Device(SettingsManager) = " + intValue);
        String str2 = TAG;
        Log.d(str2, "Managed Device Property Level(SystemProperties) = " + i);
        if (intent.hasExtra("SET")) {
            int intExtra = intent.getIntExtra("SET", 0);
            if (CompanionServer.DEBUG) {
                String str3 = TAG;
                Log.d(str3, "Setting managed level (value in intent) to = " + intExtra);
            }
            if (i == -1 || intExtra == i) {
                SettingsManager settingsManager = new SettingsManager();
                secureStorage.storeValue("managed_device", intExtra);
                settingsManager.setInt("managed_device", intExtra);
            } else {
                Log.e(TAG, "New setting and com.oculus.managed(SystemProperties) do not match. Ignoring.");
            }
        }
        sendIntResult(intent, intValue);
    }

    private void handleDestroyUserKey(Intent intent) {
        int intExtra = intent.getIntExtra("USER_ID", 0);
        String str = TAG;
        Log.d(str, "Destroy key for user " + intExtra);
        ((StorageManager) getApplicationContext().getSystemService(StorageManager.class)).destroyUserKey(intExtra);
    }
}
