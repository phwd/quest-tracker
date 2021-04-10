package com.oculus.companion.server;

import android.app.ActivityManager;
import android.app.Instrumentation;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.PowerManager;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.preference.PreferenceManager;
import android.support.coordinatorlayout.R$styleable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.oculus.companion.authentication.Authentication;
import com.oculus.companion.gattble.bluetooth.BluetoothService;
import com.oculus.companion.server.CompanionService;
import com.oculus.companion.server.ControllerManager;
import com.oculus.companion.server.Protocol$AdbModeResponse;
import com.oculus.companion.server.Protocol$AppLaunchResponse;
import com.oculus.companion.server.Protocol$AutosleepTimeResponse;
import com.oculus.companion.server.Protocol$AutowakeResponse;
import com.oculus.companion.server.Protocol$CastingStatusResponse;
import com.oculus.companion.server.Protocol$Controller;
import com.oculus.companion.server.Protocol$ControllerDetectionState;
import com.oculus.companion.server.Protocol$ControllerScanResponse;
import com.oculus.companion.server.Protocol$ControllerStatusResponse;
import com.oculus.companion.server.Protocol$CrashReportsEnabledResponse;
import com.oculus.companion.server.Protocol$DevModeResponse;
import com.oculus.companion.server.Protocol$DevelopmentLicenseDetailsResponse;
import com.oculus.companion.server.Protocol$DiscoverCastingDevicesResponse;
import com.oculus.companion.server.Protocol$ErrorDetails;
import com.oculus.companion.server.Protocol$HmdExternalBatteryStatusResponse;
import com.oculus.companion.server.Protocol$HmdVersionResponse;
import com.oculus.companion.server.Protocol$LineFrequencyResponse;
import com.oculus.companion.server.Protocol$ManagedModeResponse;
import com.oculus.companion.server.Protocol$MirrorResponse;
import com.oculus.companion.server.Protocol$MtpModeResponse;
import com.oculus.companion.server.Protocol$OtaCheckAvailabilityResponse;
import com.oculus.companion.server.Protocol$OtaEnabledResponse;
import com.oculus.companion.server.Protocol$PhoneNotificationResponse;
import com.oculus.companion.server.Protocol$PinStatusResponse;
import com.oculus.companion.server.Protocol$PinUnlockResponse;
import com.oculus.companion.server.Protocol$PinVerifyResponse;
import com.oculus.companion.server.Protocol$Response;
import com.oculus.companion.server.Protocol$StartCastingResponse;
import com.oculus.companion.server.Protocol$VerifyMultipleControllersConnectableResponse;
import com.oculus.companion.server.Protocol$WifiNetwork;
import com.oculus.companion.server.Protocol$WifiScanResponse;
import com.oculus.companion.server.Protocol$WifiStatusResponse;
import com.oculus.companion.server.WifiModule;
import com.oculus.companion.server.casting.CastController;
import com.oculus.companion.server.casting.CastDialog;
import com.oculus.companion.server.connection.CertificateModule;
import com.oculus.companion.server.connection.ConnectionState;
import com.oculus.companion.server.connection.SecureConnection;
import com.oculus.companion.server.utils.LicenseManagementConnector;
import com.oculus.companion.server.utils.LineFrequencyServiceContract;
import com.oculus.os.FirstTimeNuxManager;
import com.oculus.os.ICompanionServer;
import com.oculus.os.IRemoteWipeCallback;
import com.oculus.os.MolokiniParams;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import com.oculus.trustedcallerverifier.SignatureHelper;
import com.oculus.trustedcallerverifier.TrustedCallerVerifier;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import oculus.internal.BinderClient;
import oculus.internal.BlockingResultReceiver;
import oculus.internal.BuildCompat;
import oculus.internal.Gatekeeper;
import oculus.internal.power.IVrPowerManager;
import oculus.internal.power.IVrPowerManagerClient;

public class CompanionServer extends Service implements SecureConnection.Logger, SecureConnection.StateProvider {
    private static final ComponentName ANTI_PIRACY_SERVICE_COMPONENT = new ComponentName("com.oculus.horizon", "com.oculus.antipiracy.AntiPiracyIntentService");
    public static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final boolean IS_DEBUG_BUILD = (!Build.TYPE.equals("user"));
    static int MAX_POOL_THREADS = 20;
    private static final Set<Protocol$Method> METHODS_REQUIRING_OWNER_USER_AS_FOREGROUND = ImmutableSet.of(Protocol$Method.MIRROR_REQUEST, Protocol$Method.DISCOVER_CASTING_DEVICES, Protocol$Method.START_CASTING);
    static int POOL_THREADS = 2;
    private static final String TAG = "CompanionServer";
    static int THREAD_KEEPALIVE_SEC = 60;
    private static final long TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(5);
    private static final List<String> availableTimeZones = Arrays.asList(TimeZone.getAvailableIDs());
    public static final Object initLock = new Object();
    private static final Gatekeeper sPhoneNotificationsGK = new Gatekeeper(18);
    private BleModule bleModule = null;
    private BluetoothService bluetoothService = null;
    private CastController castController = null;
    private CompanionState companionState;
    private ControllerManager controllerManager = null;
    private IBinder csBinder = null;
    private boolean devicePinLocked = false;
    private boolean devicePinSet = false;
    private String deviceRenderingKeyboard = null;
    private Instrumentation instrumentation = null;
    private boolean invalidDevice = false;
    private BinderClient<IVrPowerManager> mBinderClient = new BinderClient<IVrPowerManager>("vrpowermanager", $$Lambda$CompanionServer$rEDtNznqyTfnwltvoHFTPf8C4I.INSTANCE) {
        /* class com.oculus.companion.server.CompanionServer.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public void onServiceConnected(IVrPowerManager iVrPowerManager) {
            try {
                iVrPowerManager.registerClient(CompanionServer.this.mPowerManagerClient);
            } catch (RemoteException e) {
                Log.e(CompanionServer.TAG, "Failed to register with VrPowerManager", e);
            }
        }
    };
    private CastDialog mCastDialog;
    private final ConnectionState mConnectionState = new ConnectionState();
    private HandlerThread mHandshakeHandlerThread;
    private Handler mHandshakeStateHandler;
    private LicenseManagementConnector mLicenseManagementConnector = null;
    private MolokiniParams mMolokiniParams = null;
    private final VrPowerManagerClient mPowerManagerClient = new VrPowerManagerClient();
    private SecureConnection mSecureConnection;
    public MCSState mState = MCSState.MCS_STATE_INIT;
    private ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(POOL_THREADS, MAX_POOL_THREADS, (long) THREAD_KEEPALIVE_SEC, TimeUnit.SECONDS, this.mWorkQueue);
    private AtomicInteger mTimeout = new AtomicInteger(0);
    private final BlockingQueue<Runnable> mWorkQueue = new ArrayBlockingQueue(100);
    private SettingsManager osSDKSettingsManager;
    private PowerManager powerManager = null;
    private CompanionBLEReceiver receiveHandler;
    private HandlerThread receiverThread;
    private SecureStorage secureStorage;
    private Telemetry telemetry = null;
    private WifiModule wifiModule = null;

    /* access modifiers changed from: package-private */
    public enum DisconnectKey {
        ALL,
        ALL_KEEP_LAST,
        MOST_RECENT
    }

    /* access modifiers changed from: private */
    public final class VrPowerManagerClient extends IVrPowerManagerClient.Stub {
        private VrPowerManagerClient() {
        }

        public void onStateChange(int i) {
            if (CompanionServer.DEBUG) {
                String str = CompanionServer.TAG;
                Log.d(str, "Headset state changed to " + i + " from PowerManager");
            }
            if (CompanionServer.this.companionState != null) {
                CompanionServer.this.companionState.updateHeadsetState(i);
            }
            CompanionServer.this.reconnectPhoneNotifications(i);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void reconnectPhoneNotifications(int i) {
        String string;
        if (sPhoneNotificationsGK.isEnabled() && i == 1 && this.osSDKSettingsManager.getBoolean("phone_notification_enabled", false) && this.bluetoothService != null && (string = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("phone_notifications_session_ble_address", "")) != "") {
            Log.d(TAG, "Reconnecting to previously connected phone via BLE.");
            this.bluetoothService.connect(string);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void logEvent(java.lang.String r19, java.lang.String r20, int r21, boolean r22, java.lang.String r23, com.oculus.companion.server.Protocol$ErrorCode r24) {
        /*
        // Method dump skipped, instructions count: 146
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionServer.logEvent(java.lang.String, java.lang.String, int, boolean, java.lang.String, com.oculus.companion.server.Protocol$ErrorCode):void");
    }

    @Override // com.oculus.companion.server.connection.SecureConnection.Logger
    public void logEvent(String str, String str2, int i, boolean z) {
        logEvent(str, str2, i, z, null, null);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendErrorResponse(Protocol$Request protocol$Request, Protocol$ErrorCode protocol$ErrorCode, String str) {
        sendErrorResponseInternal(protocol$Request, protocol$ErrorCode, str, null, true);
    }

    private void sendErrorResponsePlainText(Protocol$Request protocol$Request, Protocol$ErrorCode protocol$ErrorCode, String str) {
        sendErrorResponseInternal(protocol$Request, protocol$ErrorCode, str, null, false);
    }

    private void sendErrorResponseWithPairingKey(Protocol$Request protocol$Request, String str, Protocol$BlePairingKey protocol$BlePairingKey) {
        sendErrorResponseInternal(protocol$Request, Protocol$ErrorCode.BLE_PAIRING_KEY_REQUIRED, str, protocol$BlePairingKey, true);
    }

    private void sendErrorResponseInternal(Protocol$Request protocol$Request, Protocol$ErrorCode protocol$ErrorCode, String str, Protocol$BlePairingKey protocol$BlePairingKey, boolean z) {
        logEvent("generate_error_message", str, 6, true, protocol$Request != null ? protocol$Request.getMethod().toString() : "null", protocol$ErrorCode);
        Protocol$ErrorDetails.Builder newBuilder = Protocol$ErrorDetails.newBuilder();
        newBuilder.setCode(protocol$ErrorCode);
        if (str != null) {
            newBuilder.setDebugDetails(str);
        }
        if (protocol$BlePairingKey != null) {
            newBuilder.setBlePairingKey(protocol$BlePairingKey);
        }
        sendResponseInternal(protocol$Request, Protocol$ResponseCode.FAIL, newBuilder.build(), z, protocol$ErrorCode);
    }

    private void logSuccessEvent(Protocol$Request protocol$Request) {
        logEvent("companion_server_request_success", "", -1, true, protocol$Request.getMethod().toString(), null);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendSuccessResponseAndLogEvent(Protocol$Request protocol$Request) {
        logSuccessEvent(protocol$Request);
        sendSuccessResponse(protocol$Request);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendSuccessResponseAndLogEvent(Protocol$Request protocol$Request, MessageLite messageLite) {
        logSuccessEvent(protocol$Request);
        sendSuccessResponse(protocol$Request, messageLite);
    }

    private void sendSuccessResponse(Protocol$Request protocol$Request) {
        sendResponseInternal(protocol$Request, Protocol$ResponseCode.SUCCESS, null, true, null);
    }

    private void sendSuccessResponse(Protocol$Request protocol$Request, MessageLite messageLite) {
        sendResponseInternal(protocol$Request, Protocol$ResponseCode.SUCCESS, messageLite, true, null);
    }

    private void sendResponse(Protocol$Request protocol$Request, Protocol$ResponseCode protocol$ResponseCode, MessageLite messageLite) {
        sendResponseInternal(protocol$Request, protocol$ResponseCode, messageLite, true, null);
    }

    private void sendResponsePlainText(Protocol$Request protocol$Request, Protocol$ResponseCode protocol$ResponseCode, MessageLite messageLite) {
        sendResponseInternal(protocol$Request, protocol$ResponseCode, messageLite, false, null);
    }

    private void sendResponseInternal(Protocol$Request protocol$Request, Protocol$ResponseCode protocol$ResponseCode, MessageLite messageLite, boolean z, Protocol$ErrorCode protocol$ErrorCode) {
        this.telemetry.logBLEInvoke(protocol$Request, protocol$ResponseCode, protocol$ErrorCode);
        sendSeqResponseInternal(protocol$Request != null ? Integer.valueOf(protocol$Request.getSeq()) : null, protocol$ResponseCode, messageLite, z);
    }

    private void sendSeqResponse(int i, Protocol$ResponseCode protocol$ResponseCode, MessageLite messageLite) {
        sendSeqResponseInternal(Integer.valueOf(i), protocol$ResponseCode, messageLite, true);
    }

    private void sendSeqResponseInternal(Integer num, Protocol$ResponseCode protocol$ResponseCode, MessageLite messageLite, boolean z) {
        Protocol$Response.Builder newBuilder = Protocol$Response.newBuilder();
        newBuilder.setCode(protocol$ResponseCode);
        if (num != null) {
            newBuilder.setSeq(num.intValue());
        }
        if (messageLite != null) {
            newBuilder.setBody(messageLite.toByteString());
        }
        sendResponseBle(newBuilder, z);
    }

    public final class CompanionBLEReceiver extends Handler {
        private final String TAG = CompanionBLEReceiver.class.getSimpleName();

        public CompanionBLEReceiver(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            try {
                dispatch(CompanionServer.this.mSecureConnection.processRequest(message.getData().getByteArray("BLE_MESSAGE_KEY")));
            } catch (SecureConnection.SecureConnectionException e) {
                handleSecureConnectionException(e);
            } catch (IllegalArgumentException e2) {
                CompanionServer.this.logEvent("decryptls", e2.getMessage(), 6, true);
                CompanionServer.this.invalidProtocolBuffer(null, e2.getMessage());
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void dispatch(final Protocol$Request protocol$Request) {
            final Protocol$Method method = protocol$Request.getMethod();
            CompanionServer.this.mThreadPoolExecutor.execute(new Runnable() {
                /* class com.oculus.companion.server.CompanionServer.CompanionBLEReceiver.AnonymousClass1 */

                public void run() {
                    String str = "Received " + method + " Request";
                    Log.d(CompanionBLEReceiver.this.TAG, str);
                    if (CompanionServer.this.companionState != null) {
                        CompanionServer.this.companionState.maybeExitDoze("Received BT message from Twilight");
                    }
                    if (!CompanionServer.METHODS_REQUIRING_OWNER_USER_AS_FOREGROUND.contains(method) || CompanionServer.this.isCurrentUserTheOwner()) {
                        CompanionServer.this.telemetry.registerStartTime(protocol$Request);
                        try {
                            switch (AnonymousClass7.$SwitchMap$com$oculus$companion$server$Protocol$Method[method.ordinal()]) {
                                case 1:
                                    CompanionServer.this.handle_PING(protocol$Request);
                                    return;
                                case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                                    CompanionServer.this.handle_HELLO(protocol$Request);
                                    return;
                                case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                                    CompanionServer.this.handle_AUTHENTICATE(protocol$Request);
                                    return;
                                case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                                    CompanionServer.this.handle_WIFI_SCAN(protocol$Request);
                                    return;
                                case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                                    CompanionServer.this.handle_WIFI_CONNECT(protocol$Request);
                                    return;
                                case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                                    CompanionServer.this.handle_WIFI_RECONNECT(protocol$Request);
                                    return;
                                case 7:
                                    CompanionServer.this.handle_WIFI_ENABLE(protocol$Request);
                                    return;
                                case 8:
                                    CompanionServer.this.handle_WIFI_DISABLE(protocol$Request);
                                    return;
                                case 9:
                                    CompanionServer.this.handle_WIFI_FORGET(protocol$Request);
                                    return;
                                case 10:
                                    CompanionServer.this.handle_WIFI_STATUS(protocol$Request);
                                    return;
                                case 11:
                                    CompanionServer.this.handle_DISCOVER_CASTING_DEVICES(protocol$Request);
                                    return;
                                case 12:
                                    CompanionServer.this.handle_START_CASTING(protocol$Request);
                                    return;
                                case 13:
                                    CompanionServer.this.handle_STOP_CASTING(protocol$Request);
                                    return;
                                case 14:
                                    CompanionServer.this.handle_CASTING_STATUS(protocol$Request);
                                    return;
                                case 15:
                                    CompanionServer.this.logEvent("set_user_secret", str, -1, true);
                                    CompanionServer.this.handle_OCULUS_SET_USER_SECRET(protocol$Request);
                                    return;
                                case 16:
                                    CompanionServer.this.logEvent("set_access_token", str, -1, true);
                                    CompanionServer.this.handle_OCULUS_SET_ACCESS_TOKEN(protocol$Request);
                                    CompanionServer.this.requestLineFrequency("OC_LOCATION_UPDATE_LINE_FREQUENCY");
                                    return;
                                case 17:
                                    CompanionServer.this.logEvent("insert_linked_account", str, -1, true);
                                    CompanionServer.this.handle_OCULUS_INSERT_LINKED_ACCOUNT(protocol$Request);
                                    return;
                                case 18:
                                    CompanionServer.this.logEvent("logout", str, -1, true);
                                    CompanionServer.this.handle_OCULUS_LOGOUT(protocol$Request);
                                    return;
                                case 19:
                                    CompanionServer.this.logEvent("wipe_data", str, -1, true);
                                    CompanionServer.this.handle_WIPE_DATA(protocol$Request);
                                    return;
                                case 20:
                                    CompanionServer.this.handle_OTA_CHECK_AVAILABILITY(protocol$Request);
                                    return;
                                case 21:
                                    CompanionServer.this.handle_OTA_MANUAL_UPDATE(protocol$Request);
                                    return;
                                case 22:
                                    CompanionServer.this.logEvent("dev_mode_set", str, -1, true);
                                    CompanionServer.this.handle_DEV_MODE_SET(protocol$Request);
                                    return;
                                case 23:
                                    CompanionServer.this.handle_DEV_MODE_STATUS(protocol$Request);
                                    return;
                                case 24:
                                    CompanionServer.this.handle_ADB_MODE_SET(protocol$Request);
                                    return;
                                case 25:
                                    CompanionServer.this.handle_ADB_MODE_STATUS(protocol$Request);
                                    return;
                                case 26:
                                    CompanionServer.this.handle_MTP_MODE_SET(protocol$Request);
                                    return;
                                case 27:
                                    CompanionServer.this.handle_MTP_MODE_STATUS(protocol$Request);
                                    return;
                                case 28:
                                    CompanionServer.this.logEvent("pin_set", str, -1, false);
                                    CompanionServer.this.handle_PIN_SET(protocol$Request);
                                    return;
                                case 29:
                                    CompanionServer.this.handle_PIN_STATUS(protocol$Request);
                                    return;
                                case 30:
                                    CompanionServer.this.handle_NotImplemented(protocol$Request);
                                    return;
                                case 31:
                                    CompanionServer.this.handle_PIN_UNLOCK(protocol$Request);
                                    return;
                                case 32:
                                    CompanionServer.this.handle_PIN_VERIFY(protocol$Request);
                                    return;
                                case 33:
                                    CompanionServer.this.handle_PIN_RESET(protocol$Request);
                                    return;
                                case 34:
                                    CompanionServer.this.handle_CONTROLLER_SCAN(protocol$Request);
                                    return;
                                case 35:
                                    CompanionServer.this.logEvent("controller_pair", str, -1, true);
                                    CompanionServer.this.handle_CONTROLLER_PAIR(protocol$Request);
                                    return;
                                case 36:
                                    CompanionServer.this.handle_CONTROLLER_STATUS(protocol$Request);
                                    return;
                                case 37:
                                    CompanionServer.this.logEvent("controller_unpair", str, -1, true);
                                    CompanionServer.this.handle_CONTROLLER_UNPAIR(protocol$Request);
                                    return;
                                case 38:
                                    CompanionServer.this.handle_CONTROLLER_SET_HANDEDNESS(protocol$Request);
                                    return;
                                case 39:
                                    CompanionServer.this.handle_CONTROLLER_SCAN_AND_PAIR(protocol$Request);
                                    return;
                                case 40:
                                    CompanionServer.this.handle_CONTROLLER_VERIFY_CONNECTABLE(protocol$Request);
                                    return;
                                case 41:
                                    CompanionServer.this.handle_VERIFY_MULTIPLE_CONTROLLERS_CONNECTABLE(protocol$Request);
                                    return;
                                case 42:
                                    CompanionServer.this.handle_HMD_STATUS(protocol$Request);
                                    return;
                                case 43:
                                    CompanionServer.this.handle_HMD_VERSION(protocol$Request);
                                    return;
                                case 44:
                                    CompanionServer.this.handle_HMD_CAPABILITIES(protocol$Request);
                                    return;
                                case 45:
                                    CompanionServer.this.handle_HMD_EXTERNAL_BATTERY_STATUS(protocol$Request);
                                    return;
                                case 46:
                                    CompanionServer.this.handle_APP_LAUNCH(protocol$Request);
                                    return;
                                case 47:
                                    CompanionServer.this.handle_LOCALE_SET(protocol$Request);
                                    return;
                                case 48:
                                    CompanionServer.this.handle_TIME_SET(protocol$Request);
                                    return;
                                case 49:
                                    CompanionServer.this.handle_HEALTH_AND_SAFETY_WARNING_SET(protocol$Request);
                                    return;
                                case 50:
                                    CompanionServer.this.handle_NUX_COMPLETED(protocol$Request);
                                    return;
                                case 51:
                                    CompanionServer.this.handle_SKIP_HIGH_PRI_APPS_DOWNLOAD(protocol$Request);
                                    return;
                                case 52:
                                    CompanionServer.this.handle_OTA_ENABLED_STATUS(protocol$Request);
                                    return;
                                case 53:
                                    CompanionServer.this.handle_OTA_ENABLED_SET(protocol$Request);
                                    return;
                                case 54:
                                    CompanionServer.this.handle_MIRROR_REQUEST(protocol$Request);
                                    return;
                                case 55:
                                    CompanionServer.this.handle_CRASH_REPORTS_ENABLED_SET(protocol$Request);
                                    return;
                                case 56:
                                    CompanionServer.this.handle_CRASH_REPORTS_ENABLED_STATUS(protocol$Request);
                                    return;
                                case 57:
                                    CompanionServer.this.handle_AUTOWAKE_SET(protocol$Request);
                                    return;
                                case 58:
                                    CompanionServer.this.handle_AUTOWAKE_STATUS(protocol$Request);
                                    return;
                                case 59:
                                    CompanionServer.this.handle_MANAGED_MODE_SET(protocol$Request);
                                    return;
                                case 60:
                                    CompanionServer.this.handle_MANAGED_MODE_STATUS(protocol$Request);
                                    return;
                                case 61:
                                    CompanionServer.this.handle_AUTOSLEEP_TIME_SET(protocol$Request);
                                    return;
                                case 62:
                                    CompanionServer.this.handle_AUTOSLEEP_TIME_STATUS(protocol$Request);
                                    return;
                                case 63:
                                    CompanionServer.this.handle_NAME_SET(protocol$Request);
                                    return;
                                case 64:
                                    CompanionServer.this.handle_TEXT_SEND(protocol$Request);
                                    return;
                                case 65:
                                    CompanionServer.this.handle_LINE_FREQUENCY_SET(protocol$Request);
                                    return;
                                case 66:
                                    CompanionServer.this.handle_LINE_FREQUENCY_STATUS(protocol$Request);
                                    return;
                                case 67:
                                    CompanionServer.this.handle_RESET_GUARDIAN(protocol$Request);
                                    return;
                                case 68:
                                    CompanionServer.this.handle_RESET_HEADSET_VIEW(protocol$Request);
                                    return;
                                case 69:
                                    CompanionServer.this.handle_TOGGLE_PASSTHROUGH(protocol$Request);
                                    return;
                                case 70:
                                    CompanionServer.this.handle_DEVELOPMENT_LICENSE_DETAILS(protocol$Request);
                                    return;
                                case 71:
                                    CompanionServer.this.handle_DEVELOPMENT_LICENSE_REFRESH(protocol$Request);
                                    return;
                                case 72:
                                    CompanionServer.this.handle_PHONE_NOTIFICATION_SET(protocol$Request);
                                    return;
                                case 73:
                                    CompanionServer.this.handle_PHONE_NOTIFICATION_STATUS(protocol$Request);
                                    return;
                                case 74:
                                    CompanionServer.this.handle_NotImplemented(protocol$Request);
                                    return;
                                case 75:
                                    CompanionServer.this.handle_RENDER_KEYBOARD_START(protocol$Request);
                                    return;
                                case 76:
                                    CompanionServer.this.handle_RENDER_KEYBOARD_STOP(protocol$Request);
                                    return;
                                default:
                                    CompanionServer.this.logEvent("unknown_request", "Undefined message", 5, true);
                                    CompanionServer.this.handle_NotImplemented(protocol$Request);
                                    return;
                            }
                        } catch (InterruptedException e) {
                            Log.e(CompanionBLEReceiver.this.TAG, "Operation " + method.toString() + " Interrupted: ", e);
                        } catch (RuntimeException e2) {
                            String str2 = "Operation " + method.toString() + " RuntimeException: " + e2.getMessage();
                            Log.e(CompanionBLEReceiver.this.TAG, str2, e2);
                            CompanionServer.this.logEvent("runtime_exception", str2, -1, true);
                            CompanionServer.this.sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, str2);
                        }
                    } else {
                        CompanionServer.this.sendErrorResponse(protocol$Request, Protocol$ErrorCode.WRONG_USER_LOGGED_IN, "The primary user is not the foreground user.");
                    }
                }
            });
        }

        private void handleSecureConnectionException(SecureConnection.SecureConnectionException secureConnectionException) {
            if (secureConnectionException.shouldDropConnection()) {
                CompanionServer.this.logEvent("decryptls", secureConnectionException.getMessage(), 6, true);
                CompanionServer.this.invalidDevice = true;
                CompanionServer.this.invalidProtocol(secureConnectionException.getRequest());
                CompanionServer.this.dropConnection(DisconnectKey.ALL);
                return;
            }
            CompanionServer.this.invalidProtocol(secureConnectionException.getRequest());
        }
    }

    private void sendResponseBle(Protocol$Response.Builder builder, boolean z) {
        Protocol$Response protocol$Response = (Protocol$Response) builder.build();
        if (DEBUG) {
            if (protocol$Response.hasBody()) {
                if (DEBUG) {
                    Log.d(TAG, "Response code: " + protocol$Response.getCode().name() + " size: " + protocol$Response.getBody().size() + " seq: " + protocol$Response.getSeq());
                }
            } else if (DEBUG) {
                Log.d(TAG, "Response code: " + protocol$Response.getCode().name() + " seq: " + protocol$Response.getSeq());
            }
        }
        if (!Thread.interrupted()) {
            if (z && !this.mSecureConnection.isCurrentConnectionSecure()) {
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                String str = "secure=" + z + ", secureConnection=" + this.mSecureConnection.isCurrentConnectionSecure();
                for (int i = 0; i < stackTrace.length; i++) {
                    str = str + stackTrace[i].toString();
                }
                logEvent("do_nothing", str, 6, true);
            }
            if (z) {
                try {
                    this.bleModule.send(this.mSecureConnection.getAuthenticationServer().encryptLS(protocol$Response.toByteArray()));
                } catch (InterruptedException e) {
                    if (DEBUG) {
                        Log.d(TAG, "Send operation interrupted:" + e.getMessage());
                    }
                    this.bleModule.dropFirst();
                } catch (IllegalArgumentException e2) {
                    logEvent("decryptls", e2.getMessage(), 6, true);
                    this.bleModule.dropFirst();
                }
            } else {
                this.bleModule.send(protocol$Response.toByteArray());
            }
        } else if (DEBUG) {
            Log.d(TAG, "Thread was interrupted - not sending over BLE");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_NotImplemented(Protocol$Request protocol$Request) {
        logEvent("handle_notimplemented", "Received Unimplemented Protocol Request: " + protocol$Request.getMethod(), 6, true);
        sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNSUPPORTED_METHOD, "Not implemented");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void invalidProtocol(Protocol$Request protocol$Request) {
        StringBuilder sb = new StringBuilder();
        sb.append("Protocol message not expected: ");
        sb.append(protocol$Request != null ? protocol$Request.getMethod().toString() : "null");
        sendErrorResponse(protocol$Request, Protocol$ErrorCode.BAD_REQUEST, sb.toString());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void invalidProtocolBuffer(Protocol$Request protocol$Request, String str) {
        String str2;
        if (protocol$Request != null) {
            str2 = ". Received invalid Protocol Request: " + protocol$Request.getMethod().toString() + ". Sequence number=" + protocol$Request.getSeq();
        } else {
            str2 = "" + "null";
        }
        String str3 = str2 + str;
        sendErrorResponse(protocol$Request, Protocol$ErrorCode.BAD_REQUEST, str3);
        logEvent("invalid_protocol_buffer", str3, 6, true);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void internalError(Protocol$Request protocol$Request, String str, boolean z) {
        String str2 = "Internal Error: " + protocol$Request.getMethod().toString() + ": " + str;
        Log.e(TAG, str2);
        logEvent("companion_internal_error", str2, 6, true);
        if (z) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.INTERNAL_ERROR, str2);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_PING(Protocol$Request protocol$Request) {
        sendSuccessResponse(protocol$Request);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_HELLO(Protocol$Request protocol$Request) {
        synchronized (ConnectionState.secureLock) {
            try {
                Protocol$HelloRequest parseFrom = Protocol$HelloRequest.parseFrom(protocol$Request.getBody());
                sendResponsePlainText(protocol$Request, Protocol$ResponseCode.SUCCESS, this.mSecureConnection.handleHello(parseFrom.getClientPublicKey().toByteArray(), parseFrom.getClientChallenge().toByteArray(), parseFrom.hasKnownCertFingerprint() ? parseFrom.getKnownCertFingerprint().toByteArray() : null).build());
                discardPreviousConnectionState();
                this.mHandshakeStateHandler.sendEmptyMessage(MCSState.MCS_FRESH_HELLO.value);
            } catch (InvalidProtocolBufferException e) {
                invalidProtocolBuffer(protocol$Request, e.getMessage());
            } catch (InvalidAlgorithmParameterException e2) {
                logEvent("decryptls", e2.getMessage(), 6, true);
                Protocol$ErrorCode protocol$ErrorCode = Protocol$ErrorCode.UNKNOWN_ERROR;
                sendErrorResponsePlainText(protocol$Request, protocol$ErrorCode, "Internal error generating keys [" + protocol$Request.getMethod().toString() + "] " + e2.getMessage());
            } catch (IllegalArgumentException e3) {
                logEvent("decryptls", e3.getMessage(), 6, true);
                Protocol$ErrorCode protocol$ErrorCode2 = Protocol$ErrorCode.UNKNOWN_ERROR;
                sendErrorResponsePlainText(protocol$Request, protocol$ErrorCode2, "Internal error exchanging keys [" + protocol$Request.getMethod().toString() + "] " + e3.getMessage());
            } catch (IOException e4) {
                Protocol$ErrorCode protocol$ErrorCode3 = Protocol$ErrorCode.UNKNOWN_ERROR;
                sendErrorResponsePlainText(protocol$Request, protocol$ErrorCode3, "Internal error[" + protocol$Request.getMethod().toString() + "] " + e4.getMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_AUTHENTICATE(Protocol$Request protocol$Request) {
        if (!this.mConnectionState.isClaimedByUser || !this.mSecureConnection.getAuthenticationServer().authenticationSessionInProgress()) {
            invalidProtocol(protocol$Request);
        } else if (requireUserPin(this)) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.USER_PIN_REQUIRED, "Enter lock pattern to authenticate!");
        } else {
            try {
                if (this.mSecureConnection.handleAuthenticate(Protocol$AuthenticateRequest.parseFrom(protocol$Request.getBody()).getSignedAuthenticationChallenge().toByteArray(), this.secureStorage.getValue("secret_key"))) {
                    if (this.mConnectionState.isClaimedByUser) {
                        this.mHandshakeStateHandler.sendEmptyMessage(MCSState.MCS_CHALLENGE_RESPONSE.value);
                    } else {
                        this.mHandshakeStateHandler.sendEmptyMessage(MCSState.MCS_WAIT_FOR_COMMAND.value);
                    }
                    sendSuccessResponse(protocol$Request);
                    return;
                }
                logEvent("decryptls", "AuthFailure", 6, true);
                this.invalidDevice = true;
                dropConnection(DisconnectKey.ALL);
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.AUTHENTICATION_FAILURE, "Authentication failure");
            } catch (InvalidProtocolBufferException e) {
                invalidProtocolBuffer(protocol$Request, e.getMessage());
            } catch (IllegalArgumentException e2) {
                logEvent("decryptls", e2.getMessage(), 6, true);
                this.invalidDevice = true;
                dropConnection(DisconnectKey.ALL);
                Protocol$ErrorCode protocol$ErrorCode = Protocol$ErrorCode.AUTHENTICATION_FAILURE;
                sendErrorResponse(protocol$Request, protocol$ErrorCode, "Authentication Verification failure: " + e2.getMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_WIFI_SCAN(Protocol$Request protocol$Request) throws InterruptedException {
        Protocol$WifiScanResponse.Builder newBuilder = Protocol$WifiScanResponse.newBuilder();
        List<CompanionService.WifiTuple> wifiNetworks = CompanionService.getWifiNetworks(this.wifiModule);
        if (wifiNetworks != null) {
            for (CompanionService.WifiTuple wifiTuple : wifiNetworks) {
                String ssid = wifiTuple.getSsid();
                String capabilities = wifiTuple.getCapabilities();
                int signalLevel = wifiTuple.getSignalLevel();
                if (DEBUG) {
                    Log.d(TAG, ssid);
                }
                if (DEBUG) {
                    Log.d(TAG, capabilities);
                }
                boolean z = false;
                Protocol$WifiNetwork.Builder newBuilder2 = Protocol$WifiNetwork.newBuilder();
                newBuilder2.setSsid(ssid);
                newBuilder2.setSignalLevel(signalLevel);
                boolean z2 = true;
                if (capabilities.contains("EAP")) {
                    newBuilder2.addAuth(Protocol$WifiAuthentication.EAP);
                    z = true;
                }
                if (capabilities.contains("WPA")) {
                    newBuilder2.addAuth(Protocol$WifiAuthentication.WPA);
                    z = true;
                }
                if (capabilities.contains("WEP")) {
                    newBuilder2.addAuth(Protocol$WifiAuthentication.WEP);
                } else {
                    z2 = z;
                }
                if (!z2) {
                    newBuilder2.addAuth(Protocol$WifiAuthentication.NONE);
                }
                newBuilder.addNetworks((Protocol$WifiNetwork) newBuilder2.build());
            }
            String macAddress = this.wifiModule.getMacAddress();
            if (macAddress != null) {
                newBuilder.setDeviceMacAddress(macAddress);
                if (DEBUG) {
                    Log.d(TAG, macAddress);
                }
            }
            sendSuccessResponse(protocol$Request, newBuilder.build());
            return;
        }
        sendErrorResponse(protocol$Request, Protocol$ErrorCode.WIFI_NO_NETWORK, "Wifi Scan List Empty");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_WIFI_CONNECT(Protocol$Request protocol$Request) throws InterruptedException {
        try {
            Protocol$WifiConnectRequest parseFrom = Protocol$WifiConnectRequest.parseFrom(protocol$Request.getBody());
            String password = parseFrom.getPassword();
            Protocol$WifiAuthentication auth = parseFrom.hasAuth() ? parseFrom.getAuth() : Protocol$WifiAuthentication.NONE;
            WifiModule.WifiModuleState provisionWifi = CompanionService.provisionWifi(this.wifiModule, parseFrom.getSsid(), parseFrom.getUsername(), password, auth, parseFrom.getHidden());
            switch (AnonymousClass7.$SwitchMap$com$oculus$companion$server$WifiModule$WifiModuleState[provisionWifi.ordinal()]) {
                case 1:
                    String str = "Invalid password. ";
                    if (auth == Protocol$WifiAuthentication.WPA) {
                        str = str + "WPA password must be between 8 and 63 characters";
                    } else if (auth == Protocol$WifiAuthentication.WEP) {
                        str = str + "WEP password must be either 5 ASCII (10 hex) or 13 ASCII (26 hex) characters";
                    }
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.BAD_ARGUEMENT, str);
                    return;
                case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                    sendSuccessResponse(protocol$Request);
                    return;
                case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.WIFI_INVALID_AUTH, "Cannot connect to network - Failed authentication");
                    return;
                case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.WIFI_AUTH_TIMEOUT, "Cannot connect to network - Timed out during authentication");
                    return;
                case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.WIFI_NO_INTERNET, "Connected to network but can't reach Oculus servers");
                    return;
                case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.WIFI_IP_CONFIG_FAIL, "Connected to AP but can't acquire an IP address");
                    return;
                case 7:
                case 8:
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.WIFI_NO_NETWORK, "Disconnected from network: " + provisionWifi.toString());
                    return;
                default:
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.DEVICE_WIFI_ERROR, "Cannot connect to network: " + provisionWifi.toString());
                    return;
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_WIFI_RECONNECT(Protocol$Request protocol$Request) throws InterruptedException {
        try {
            if (this.wifiModule.reconnectToWifi(Protocol$WifiReconnectRequest.parseFrom(protocol$Request.getBody()).getSsid())) {
                sendSuccessResponse(protocol$Request);
            } else {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.WIFI_NO_NETWORK, "Cannot reconnect to network");
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_WIFI_ENABLE(Protocol$Request protocol$Request) throws InterruptedException {
        if (this.wifiModule.enableWifi()) {
            sendSuccessResponse(protocol$Request);
        } else {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.DEVICE_WIFI_ERROR, "Could not enable wifi");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_WIFI_DISABLE(Protocol$Request protocol$Request) throws InterruptedException {
        this.wifiModule.disableWifi();
        sendSuccessResponse(protocol$Request);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_WIFI_STATUS(Protocol$Request protocol$Request) throws InterruptedException {
        boolean isWifiEnabled = this.wifiModule.isWifiEnabled();
        Protocol$WifiStatusResponse.Builder newBuilder = Protocol$WifiStatusResponse.newBuilder();
        newBuilder.setEnabled(isWifiEnabled);
        if (isWifiEnabled) {
            if (this.wifiModule.isConnectedToInternet()) {
                newBuilder.setReachability(Protocol$ReachabilityStatus.OK);
            } else {
                newBuilder.setReachability(Protocol$ReachabilityStatus.OCULUS_SERVER_UNREACHABLE);
            }
        }
        WifiInfo connectionInfo = this.wifiModule.getConnectionInfo();
        if (connectionInfo != null) {
            String stripQuotes = WifiModule.stripQuotes(connectionInfo.getSSID());
            Protocol$WifiNetwork.Builder newBuilder2 = Protocol$WifiNetwork.newBuilder();
            newBuilder2.setSsid(stripQuotes);
            newBuilder.setNetwork((Protocol$WifiNetwork) newBuilder2.build());
        }
        List<WifiConfiguration> configuredNetworks = this.wifiModule.getConfiguredNetworks();
        if (configuredNetworks == null) {
            Log.e(TAG, "getConfiguredNetworks returned null!");
        } else {
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                String str = wifiConfiguration.SSID;
                if (str == null) {
                    Log.e(TAG, "null ssid returned from getConfiguredNetworks!");
                } else {
                    String stripQuotes2 = WifiModule.stripQuotes(str);
                    Protocol$WifiNetwork.Builder newBuilder3 = Protocol$WifiNetwork.newBuilder();
                    newBuilder3.setSsid(stripQuotes2);
                    newBuilder.addKnownNetworks((Protocol$WifiNetwork) newBuilder3.build());
                }
            }
        }
        sendSuccessResponse(protocol$Request, newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_WIFI_FORGET(Protocol$Request protocol$Request) {
        try {
            if (this.wifiModule.forgetNetwork(Protocol$WifiForgetRequest.parseFrom(protocol$Request.getBody()).getNetwork().getSsid())) {
                sendSuccessResponse(protocol$Request);
            } else {
                sendResponse(protocol$Request, Protocol$ResponseCode.FAIL, null);
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_DISCOVER_CASTING_DEVICES(Protocol$Request protocol$Request) {
        List<Protocol$CastDevice> advertisingDevices = this.castController.getAdvertisingDevices();
        if (advertisingDevices == null) {
            sendResponse(protocol$Request, Protocol$ResponseCode.FAIL, null);
            return;
        }
        Protocol$DiscoverCastingDevicesResponse.Builder newBuilder = Protocol$DiscoverCastingDevicesResponse.newBuilder();
        newBuilder.addAllDevice(advertisingDevices);
        sendSuccessResponse(protocol$Request, (Protocol$DiscoverCastingDevicesResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_START_CASTING(Protocol$Request protocol$Request) {
        try {
            Protocol$StartCastingRequest parseFrom = Protocol$StartCastingRequest.parseFrom(protocol$Request.getBody());
            String deviceId = parseFrom.getDeviceId();
            if (TextUtils.isEmpty(deviceId)) {
                invalidProtocolBuffer(protocol$Request, "DeviceId is empty");
            } else if (!parseFrom.hasShowDialog() || parseFrom.getShowDialog()) {
                getCastDialog().showCastDialog(new Runnable(deviceId, protocol$Request) {
                    /* class com.oculus.companion.server.$$Lambda$CompanionServer$BIjOfdjEg1MCz9orHXaUNY */
                    private final /* synthetic */ String f$1;
                    private final /* synthetic */ Protocol$Request f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        CompanionServer.this.lambda$handle_START_CASTING$1$CompanionServer(this.f$1, this.f$2);
                    }
                }, new Runnable(protocol$Request) {
                    /* class com.oculus.companion.server.$$Lambda$CompanionServer$chNGtwHAIAjI3TY5IlA5XVUZOCo */
                    private final /* synthetic */ Protocol$Request f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        CompanionServer.this.lambda$handle_START_CASTING$2$CompanionServer(this.f$1);
                    }
                }, new Runnable(protocol$Request) {
                    /* class com.oculus.companion.server.$$Lambda$CompanionServer$PMdwNrbZN0GCL0Ge1GHOrEp97pY */
                    private final /* synthetic */ Protocol$Request f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        CompanionServer.this.lambda$handle_START_CASTING$3$CompanionServer(this.f$1);
                    }
                });
            } else {
                Log.d(TAG, "Skip privacy dialog inside START_CASTING");
                int startCasting = this.castController.startCasting(deviceId);
                Protocol$StartCastingResponse.Builder newBuilder = Protocol$StartCastingResponse.newBuilder();
                newBuilder.setResultCode(startCasting);
                sendSuccessResponse(protocol$Request, (Protocol$StartCastingResponse) newBuilder.build());
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, e.getMessage());
        }
    }

    public /* synthetic */ void lambda$handle_START_CASTING$1$CompanionServer(String str, Protocol$Request protocol$Request) {
        int startCasting = this.castController.startCasting(str);
        Protocol$StartCastingResponse.Builder newBuilder = Protocol$StartCastingResponse.newBuilder();
        newBuilder.setResultCode(startCasting);
        sendSuccessResponse(protocol$Request, (Protocol$StartCastingResponse) newBuilder.build());
    }

    public /* synthetic */ void lambda$handle_START_CASTING$2$CompanionServer(Protocol$Request protocol$Request) {
        Protocol$StartCastingResponse.Builder newBuilder = Protocol$StartCastingResponse.newBuilder();
        newBuilder.setResultCode(-1);
        sendSuccessResponse(protocol$Request, (Protocol$StartCastingResponse) newBuilder.build());
    }

    public /* synthetic */ void lambda$handle_START_CASTING$3$CompanionServer(Protocol$Request protocol$Request) {
        sendErrorResponse(protocol$Request, Protocol$ErrorCode.TIMED_OUT, "No User response to privacy dialog.");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_STOP_CASTING(Protocol$Request protocol$Request) {
        this.castController.stopCasting();
        sendSuccessResponse(protocol$Request);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_CASTING_STATUS(Protocol$Request protocol$Request) {
        Protocol$CastDevice castingStatus = this.castController.getCastingStatus();
        if (castingStatus == null) {
            sendResponse(protocol$Request, Protocol$ResponseCode.FAIL, null);
            return;
        }
        Protocol$CastingStatusResponse.Builder newBuilder = Protocol$CastingStatusResponse.newBuilder();
        newBuilder.setDevice(castingStatus);
        sendSuccessResponse(protocol$Request, (Protocol$CastingStatusResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_OCULUS_SET_USER_SECRET(Protocol$Request protocol$Request) {
        try {
            Protocol$OculusSetUserSecretRequest parseFrom = Protocol$OculusSetUserSecretRequest.parseFrom(protocol$Request.getBody());
            if (!parseFrom.hasUserSecretKey()) {
                logEvent("set_user_secret", "No user secret key - expecting that!", 6, true);
                invalidProtocol(protocol$Request);
            } else if (setUserSecret(parseFrom.getUserSecretKey().toByteArray(), false, "set_user_secret")) {
                sendSuccessResponse(protocol$Request);
            } else {
                invalidProtocol(protocol$Request);
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean setUserSecret(byte[] bArr, boolean z, String str) {
        boolean z2;
        String str2;
        synchronized (ConnectionState.secureLock) {
            if (!this.mConnectionState.isClaimedByUser) {
                this.secureStorage.storeValue("secret_key", bArr);
                this.secureStorage.storeValue("headset_claimed_by_user", true);
                this.mConnectionState.isClaimedByUser = true;
                str2 = "Succeeded";
            } else if (!z || !Arrays.equals(bArr, this.secureStorage.getValue("secret_key"))) {
                str2 = "Failed, device already claimed";
                z2 = false;
            } else {
                str2 = "Succeeded, identical secret";
            }
            z2 = true;
        }
        logEvent(str, str2, z2 ? 3 : 6, true);
        return z2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_OCULUS_SET_ACCESS_TOKEN(Protocol$Request protocol$Request) throws InterruptedException {
        if (requireUserPin(this)) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.USER_PIN_REQUIRED, "Enter lock pattern to set access token!");
            return;
        }
        try {
            Protocol$OculusSetAccessTokenRequest parseFrom = Protocol$OculusSetAccessTokenRequest.parseFrom(protocol$Request.getBody());
            String accessToken = parseFrom.getAccessToken();
            String userId = parseFrom.hasUserId() ? parseFrom.getUserId() : null;
            if (this.secureStorage.getValue("access_token").length != 0) {
                logEvent("set_access_token", "Horizon re-login", 3, true);
            }
            this.secureStorage.storeValue("access_token", accessToken);
            int horizonLogin = CompanionService.horizonLogin(getApplicationContext(), accessToken, userId, UserHandle.SYSTEM);
            if (DEBUG) {
                String str = TAG;
                Log.d(str, "Result " + horizonLogin);
            }
            if (horizonLogin == Protocol$ErrorCode.TIMED_OUT.getNumber()) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.TIMED_OUT, "Oculus Authentication Failed (Timed Out)");
            } else if (horizonLogin == -101) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Oculus Authentication Failed (Interrupted)");
            }
            if (horizonLogin == -7) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Oculus Authentication Failed (Unknown Error)");
            } else if (horizonLogin == -6 || horizonLogin == -5 || horizonLogin == -4) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.WIFI_NO_NETWORK, "Oculus Authentication Failed (Network Error)");
            } else if (horizonLogin == -1) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.BAD_ACCESS_TOKEN, "Oculus Authentication Failed (Invalid Credentials)");
            } else if (horizonLogin != 1) {
                Protocol$ErrorCode protocol$ErrorCode = Protocol$ErrorCode.UNKNOWN_ERROR;
                sendErrorResponse(protocol$Request, protocol$ErrorCode, "Oculus Authentication Failed (Default Error (" + horizonLogin + "))");
            } else {
                sendSuccessResponse(protocol$Request);
            }
            this.companionState.updateHorizonState();
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "OculusLoginRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_OCULUS_INSERT_LINKED_ACCOUNT(Protocol$Request protocol$Request) throws InterruptedException {
        try {
            Protocol$OculusInsertLinkedAccountRequest parseFrom = Protocol$OculusInsertLinkedAccountRequest.parseFrom(protocol$Request.getBody());
            String token = parseFrom.getToken();
            CompanionService.insertLinkedAccount(getApplicationContext(), parseFrom.getUserId(), token, parseFrom.getServiceProvider());
            sendSuccessResponse(protocol$Request);
        } catch (IllegalArgumentException unused) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.BAD_ARGUEMENT, "Oculus Insert Linked Account Failed (Illegal Argument)");
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "OculusInsertLinkedAccountRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_OCULUS_LOGOUT(Protocol$Request protocol$Request) throws InterruptedException {
        int horizonLogout = CompanionService.horizonLogout(getApplicationContext(), UserHandle.SYSTEM);
        if (DEBUG) {
            String str = TAG;
            Log.d(str, "Result " + horizonLogout);
        }
        if (horizonLogout == -6 || horizonLogout == -5 || horizonLogout == -4) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Oculus Authentication Failed (Network Error)");
        } else if (horizonLogout != 1) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Oculus Authentication Failed (Unknown Error)");
        } else {
            sendSuccessResponse(protocol$Request);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_WIPE_DATA(Protocol$Request protocol$Request) {
        try {
            Protocol$WipeDataRequest.parseFrom(protocol$Request.getBody());
            sendSuccessResponse(protocol$Request);
            CompanionService.wipeData(getApplicationContext());
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "WipeDataRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_OTA_CHECK_AVAILABILITY(Protocol$Request protocol$Request) {
        try {
            if (!UpdaterManager.checkUpdateAvailability(getApplicationContext(), Protocol$OtaCheckAvailabilityRequest.parseFrom(protocol$Request.getBody()).getFullOta(), this, protocol$Request.getSeq())) {
                internalError(protocol$Request, "CompanionService failed to send Updater an availability intent", true);
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "OtaCheckAvailabilityRequest corrupt: " + e.getMessage());
        }
    }

    public void response_OTA_CHECK_AVAILABILITY(int i, boolean z, String str) {
        Protocol$ResponseCode protocol$ResponseCode = z ? Protocol$ResponseCode.SUCCESS : Protocol$ResponseCode.FAIL;
        Protocol$OtaCheckAvailabilityResponse.Builder newBuilder = Protocol$OtaCheckAvailabilityResponse.newBuilder();
        newBuilder.setDebugMessage(str);
        sendSeqResponse(i, protocol$ResponseCode, (Protocol$OtaCheckAvailabilityResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_OTA_MANUAL_UPDATE(Protocol$Request protocol$Request) {
        try {
            if (!UpdaterManager.attemptUpdate(getApplicationContext(), Protocol$OtaManualUpdateRequest.parseFrom(protocol$Request.getBody()).getFullOta())) {
                internalError(protocol$Request, "CompanionService failed to send Updater an update intent", true);
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "OtaManualUpdateRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_PIN_SET(Protocol$Request protocol$Request) {
        try {
            Protocol$PinSetRequest parseFrom = Protocol$PinSetRequest.parseFrom(protocol$Request.getBody());
            if (parseFrom.getNewPin() == null || parseFrom.getNewPin().length() == 0) {
                logEvent("pin_set", "Remove pin", 3, true);
            } else {
                logEvent("pin_set", "Update pin", 3, true);
            }
            if (CompanionService.pinSet(getApplicationContext(), parseFrom.getNewPin(), parseFrom.getOldPin(), parseFrom.hasMethod() ? parseFrom.getMethod() : Protocol$CredentialLockMethod.PATTERN, 0, true)) {
                sendSuccessResponse(protocol$Request);
            } else {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.BAD_LOCK_PIN, "Incorrect pin");
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "PinSetRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_PIN_STATUS(Protocol$Request protocol$Request) {
        Protocol$CredentialLockMethod protocol$CredentialLockMethod;
        int pinStatus = CompanionService.pinStatus(getApplicationContext(), 0);
        Protocol$PinStatusResponse.Builder newBuilder = Protocol$PinStatusResponse.newBuilder();
        if ((pinStatus & 1) > 0) {
            newBuilder.setPinIsSet(true);
        }
        if ((pinStatus & 2) > 0) {
            newBuilder.setDeviceIsLocked(true);
        }
        if ((pinStatus & 4) > 0) {
            protocol$CredentialLockMethod = Protocol$CredentialLockMethod.PASSWORD;
        } else {
            protocol$CredentialLockMethod = Protocol$CredentialLockMethod.PATTERN;
        }
        newBuilder.setMethod(protocol$CredentialLockMethod);
        sendSuccessResponse(protocol$Request, newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_PIN_UNLOCK(Protocol$Request protocol$Request) throws InterruptedException {
        if (this.mTimeout.get() > 0) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Cannot Verify your pin now, try again later.");
            return;
        }
        Protocol$PinUnlockResponse.Builder newBuilder = Protocol$PinUnlockResponse.newBuilder();
        int i = 0;
        try {
            Bundle pinUnlock = CompanionService.pinUnlock(getApplicationContext(), Protocol$PinUnlockRequest.parseFrom(protocol$Request.getBody()).getPin());
            boolean z = pinUnlock.getBoolean("PIN_VERIFY_RESULT", false);
            i = pinUnlock.getInt("PIN_VERIFY_TIMEOUT", 0);
            if (i > 0) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Cannot Verify your pin now, try again later.");
                if (this.mTimeout.get() == 0) {
                    logEvent("pin_unlock", "lockscreen timeout", 3, true);
                }
            } else {
                newBuilder.setCorrect(z);
                if (z) {
                    this.companionState.updateHorizonState();
                }
                sendSuccessResponse(protocol$Request, newBuilder.build());
            }
        } catch (InvalidProtocolBufferException e) {
            Protocol$ErrorCode protocol$ErrorCode = Protocol$ErrorCode.BAD_REQUEST;
            sendErrorResponse(protocol$Request, protocol$ErrorCode, "PinUnlockRequest Corrupt:: " + e.getMessage());
        }
        if (i > 0) {
            updatePinVerifyTimeout(i);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_PIN_VERIFY(Protocol$Request protocol$Request) {
        if (this.mTimeout.get() > 0) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Cannot Verify your pin now, try again later.");
            return;
        }
        Protocol$PinVerifyResponse.Builder newBuilder = Protocol$PinVerifyResponse.newBuilder();
        int i = 0;
        try {
            Bundle pinVerify = CompanionService.pinVerify(getApplicationContext(), Protocol$PinVerifyRequest.parseFrom(protocol$Request.getBody()).getPin());
            boolean z = pinVerify.getBoolean("PIN_VERIFY_RESULT", false);
            i = pinVerify.getInt("PIN_VERIFY_TIMEOUT", 0);
            if (i > 0) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Cannot Verify your pin now, try again later.");
                if (this.mTimeout.get() == 0) {
                    logEvent("pin_unlock", "lockscreen timeout", 3, true);
                }
            } else {
                newBuilder.setCorrect(z);
                sendSuccessResponse(protocol$Request, newBuilder.build());
            }
        } catch (InvalidProtocolBufferException e) {
            Protocol$ErrorCode protocol$ErrorCode = Protocol$ErrorCode.BAD_REQUEST;
            sendErrorResponse(protocol$Request, protocol$ErrorCode, "PinVerifyRequest corrupt: " + e.getMessage());
        }
        if (i > 0) {
            updatePinVerifyTimeout(i);
        }
    }

    private void updatePinVerifyTimeout(int i) {
        if (this.mTimeout.compareAndSet(0, i)) {
            this.receiveHandler.postDelayed(new Runnable() {
                /* class com.oculus.companion.server.CompanionServer.AnonymousClass2 */

                public void run() {
                    CompanionServer.this.mTimeout.set(0);
                }
            }, (long) i);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_PIN_RESET(Protocol$Request protocol$Request) {
        if (CompanionService.pinReset(getApplicationContext(), UserHandle.getCallingUserId(), "")) {
            sendSuccessResponse(protocol$Request);
        } else {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "PinResetRequest Could not set reset pin");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_DEV_MODE_SET(Protocol$Request protocol$Request) {
        try {
            Protocol$DevModeRequest parseFrom = Protocol$DevModeRequest.parseFrom(protocol$Request.getBody());
            if (parseFrom.getMode() < 0) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.BAD_ARGUEMENT, "DevModeRequest Invalid Mode (must be >= 0)");
            } else if (!checkIfDevModeCanBeEnabled()) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.OPERATION_NOT_ALLOWED, "Developer mode is blocked for this account");
            } else {
                if (CompanionService.setAdbEnabled(getApplicationContext(), CompanionService.intToBool(parseFrom.getMode()))) {
                    sendSuccessResponse(protocol$Request);
                } else {
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "DevModeRequest Could not set adb mode");
                }
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "DevModeRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_DEV_MODE_STATUS(Protocol$Request protocol$Request) {
        boolean isAdbEnabled = CompanionService.isAdbEnabled(getApplicationContext());
        Protocol$DevModeResponse.Builder newBuilder = Protocol$DevModeResponse.newBuilder();
        newBuilder.setStatus(isAdbEnabled ? 1 : 0);
        sendSuccessResponse(protocol$Request, (Protocol$DevModeResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_ADB_MODE_SET(Protocol$Request protocol$Request) {
        try {
            if (CompanionService.setAdbEnabled(getApplicationContext(), Protocol$AdbModeRequest.parseFrom(protocol$Request.getBody()).getEnable())) {
                sendSuccessResponse(protocol$Request);
            } else {
                internalError(protocol$Request, "Could not set adb mode.", true);
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "AdbModeRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_ADB_MODE_STATUS(Protocol$Request protocol$Request) {
        boolean isAdbEnabled = CompanionService.isAdbEnabled(getApplicationContext());
        Protocol$AdbModeResponse.Builder newBuilder = Protocol$AdbModeResponse.newBuilder();
        newBuilder.setStatus(isAdbEnabled);
        sendSuccessResponse(protocol$Request, (Protocol$AdbModeResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_MTP_MODE_SET(Protocol$Request protocol$Request) {
        try {
            boolean enable = Protocol$MtpModeRequest.parseFrom(protocol$Request.getBody()).getEnable();
            if (DEBUG) {
                String str = TAG;
                Log.d(str, "Setting MTP mode to " + enable);
            }
            boolean mtp = CompanionService.setMtp(getApplicationContext(), enable);
            if (DEBUG) {
                String str2 = TAG;
                Log.d(str2, "MTP Mode is now " + mtp);
            }
            if (enable == mtp) {
                sendSuccessResponse(protocol$Request);
            } else {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "MtpModeRequest Could not set mtp mode");
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "MtpModeRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_MTP_MODE_STATUS(Protocol$Request protocol$Request) {
        boolean mtp = CompanionService.getMtp(getApplicationContext());
        Protocol$MtpModeResponse.Builder newBuilder = Protocol$MtpModeResponse.newBuilder();
        newBuilder.setStatus(mtp);
        sendSuccessResponse(protocol$Request, (Protocol$MtpModeResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_CONTROLLER_SCAN(Protocol$Request protocol$Request) throws InterruptedException {
        String str;
        Protocol$ControllerScanResponse.Builder newBuilder = Protocol$ControllerScanResponse.newBuilder();
        try {
            List<Protocol$ControllerType> typesList = Protocol$ControllerScanRequest.parseFrom(protocol$Request.getBody()).getTypesList();
            int[] deviceTypes = ControllerManager.getDeviceTypes();
            if (typesList != null) {
                for (Protocol$ControllerType protocol$ControllerType : typesList) {
                    if (deviceTypes != null && (protocol$ControllerType == Protocol$ControllerType.PRIMARY || protocol$ControllerType == Protocol$ControllerType.SECONDARY)) {
                        sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNSUPPORTED_METHOD, "CONTROLLER_SCAN not supported for oculus controllers, use CONTROLLER_SCAN_AND_PAIR");
                    } else if (protocol$ControllerType == Protocol$ControllerType.THIRD_PARTY) {
                        for (Map.Entry<String, BluetoothDevice> entry : ControllerManager.bluetoothDeviceScan(getApplicationContext()).entrySet()) {
                            BluetoothDevice value = entry.getValue();
                            Protocol$Controller.Builder newBuilder2 = Protocol$Controller.newBuilder();
                            newBuilder2.setId(entry.getKey());
                            if (value.getName() != null) {
                                str = value.getName();
                            } else {
                                str = getApplicationContext().getString(R.string.third_party_controller_name);
                            }
                            newBuilder2.setDisplayName(str);
                            newBuilder2.setType(protocol$ControllerType);
                            if (value.getBondState() != 12) {
                                newBuilder2.setState(Protocol$ControllerState.NOT_PAIRED);
                            } else if (this.controllerManager.isBluetoothDeviceConnected(entry.getKey())) {
                                newBuilder2.setState(Protocol$ControllerState.PAIRED_AND_ACTIVE);
                            } else {
                                newBuilder2.setState(Protocol$ControllerState.PAIRED_BUT_INACTIVE);
                            }
                            newBuilder.addControllers((Protocol$Controller) newBuilder2.build());
                        }
                    }
                }
            }
            sendSuccessResponse(protocol$Request, newBuilder.build());
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "ControllerScanRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_CONTROLLER_SCAN_AND_PAIR(Protocol$Request protocol$Request) throws InterruptedException {
        try {
            Protocol$ControllerScanAndPairRequest parseFrom = Protocol$ControllerScanAndPairRequest.parseFrom(protocol$Request.getBody());
            ControllerManager.ControllerScanAndPairResult scanAndPairDevice = ControllerManager.scanAndPairDevice(controllerTypeToInt(parseFrom.getType()), parseFrom.hasTimeoutMs() ? parseFrom.getTimeoutMs() : 15000);
            if (DEBUG) {
                String str = TAG;
                Log.d(str, "scanAndPair result: " + scanAndPairDevice);
            }
            int i = AnonymousClass7.$SwitchMap$com$oculus$companion$server$ControllerManager$ControllerScanAndPairResult[scanAndPairDevice.ordinal()];
            if (i == 1) {
                sendSuccessResponse(protocol$Request);
            } else if (i == 2) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.ALREADY_IN_PROGRESS, "Operation is already in progress");
            } else if (i == 3) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.TIMED_OUT, "Timed out searching for controller");
            } else if (i == 4) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.CONTROLLER_PAIR_FAILED, "Found controller, but failed to pair");
            } else if (i != 5) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Unknown error");
            } else {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.CONTROLLER_INTERNAL_ERROR, "Operation failed with an Internal Error");
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "ControllerScanAndPairRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_CONTROLLER_VERIFY_CONNECTABLE(Protocol$Request protocol$Request) throws InterruptedException {
        try {
            Protocol$ControllerVerifyConnectableRequest parseFrom = Protocol$ControllerVerifyConnectableRequest.parseFrom(protocol$Request.getBody());
            int[] verifyConnectable = ControllerManager.verifyConnectable(new int[]{controllerTypeToInt(parseFrom.getType())}, parseFrom.hasTimeoutMs() ? parseFrom.getTimeoutMs() : 0, parseFrom.hasRecentConnectionTimeLimitS() ? parseFrom.getRecentConnectionTimeLimitS() : 0);
            int i = -1;
            if (verifyConnectable != null) {
                i = verifyConnectable[0];
            }
            if (DEBUG) {
                String str = TAG;
                Log.d(str, "verifyConnectable result: " + i);
            }
            switch (i) {
                case 0:
                case 1:
                case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                    sendSuccessResponse(protocol$Request);
                    return;
                case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.ALREADY_IN_PROGRESS, "Operation is already in progress");
                    return;
                case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.TIMED_OUT, "Timed out searching for controller");
                    return;
                case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                case 7:
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.CONTROLLER_PAIR_REQUIRED, "Either no controller is paired, or the pairing is invalid");
                    return;
                case 8:
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.BATTERY_TOO_LOW, "Found controller, but battery must be replaced");
                    return;
                default:
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Unknown error");
                    return;
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "ControllerVerifyConnectableRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_VERIFY_MULTIPLE_CONTROLLERS_CONNECTABLE(Protocol$Request protocol$Request) throws InterruptedException {
        Protocol$VerifyMultipleControllersConnectableResponse.Builder newBuilder = Protocol$VerifyMultipleControllersConnectableResponse.newBuilder();
        try {
            Protocol$VerifyMultipleControllersConnectableRequest parseFrom = Protocol$VerifyMultipleControllersConnectableRequest.parseFrom(protocol$Request.getBody());
            List<Protocol$ControllerType> typesList = parseFrom.getTypesList();
            int i = 0;
            int timeoutMs = parseFrom.hasTimeoutMs() ? parseFrom.getTimeoutMs() : 0;
            int recentConnectionTimeLimitS = parseFrom.hasRecentConnectionTimeLimitS() ? parseFrom.getRecentConnectionTimeLimitS() : 0;
            int[] iArr = new int[typesList.size()];
            int i2 = 0;
            for (Protocol$ControllerType protocol$ControllerType : typesList) {
                iArr[i2] = controllerTypeToInt(protocol$ControllerType);
                i2++;
            }
            int[] verifyConnectable = ControllerManager.verifyConnectable(iArr, timeoutMs, recentConnectionTimeLimitS);
            if (verifyConnectable != null) {
                if (verifyConnectable.length == typesList.size()) {
                    for (Protocol$ControllerType protocol$ControllerType2 : typesList) {
                        Protocol$ControllerDetectionState.Builder newBuilder2 = Protocol$ControllerDetectionState.newBuilder();
                        newBuilder2.setType(protocol$ControllerType2);
                        switch (verifyConnectable[i]) {
                            case 0:
                                newBuilder2.setSuccessReason(Protocol$VerifySuccessReason.CONNECTED);
                                break;
                            case 1:
                                newBuilder2.setSuccessReason(Protocol$VerifySuccessReason.UPDATING);
                                break;
                            case R$styleable.CoordinatorLayout_Layout_layout_anchorGravity:
                                newBuilder2.setSuccessReason(Protocol$VerifySuccessReason.UPDATE_PENDING);
                                break;
                            case R$styleable.CoordinatorLayout_Layout_layout_behavior:
                                newBuilder2.setSuccessReason(Protocol$VerifySuccessReason.RECENTLY_CONNECTED);
                                break;
                            case R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges:
                                newBuilder2.setErrorCode(Protocol$ErrorCode.ALREADY_IN_PROGRESS);
                                break;
                            case R$styleable.CoordinatorLayout_Layout_layout_insetEdge:
                                newBuilder2.setErrorCode(Protocol$ErrorCode.TIMED_OUT);
                                break;
                            case R$styleable.CoordinatorLayout_Layout_layout_keyline:
                            case 7:
                                newBuilder2.setErrorCode(Protocol$ErrorCode.CONTROLLER_PAIR_REQUIRED);
                                break;
                            case 8:
                                newBuilder2.setErrorCode(Protocol$ErrorCode.BATTERY_TOO_LOW);
                                break;
                            case 9:
                                newBuilder2.setErrorCode(Protocol$ErrorCode.CONTROLLER_BLOCKED_BY_UPDATE);
                                break;
                            default:
                                newBuilder2.setErrorCode(Protocol$ErrorCode.UNKNOWN_ERROR);
                                break;
                        }
                        newBuilder.addStates((Protocol$ControllerDetectionState) newBuilder2.build());
                        i++;
                    }
                    sendSuccessResponse(protocol$Request, newBuilder.build());
                    return;
                }
            }
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.CONTROLLER_INTERNAL_ERROR, "Cannot verify controller connectable");
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "VerifyMultipleControllersConnectableRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_CONTROLLER_SET_HANDEDNESS(Protocol$Request protocol$Request) {
        try {
            if (CompanionService.setControllerHandedness(getApplicationContext(), Protocol$ControllerSetHandednessRequest.parseFrom(protocol$Request.getBody()).getHandedness().name())) {
                sendSuccessResponse(protocol$Request);
            } else {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Unable to set the Controller Handedness");
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "setControllerHandedness Request Corrupt: " + e.getMessage());
        }
    }

    private static Protocol$ControllerType intToControllerType(int i) {
        if (i == 1) {
            return Protocol$ControllerType.SECONDARY;
        }
        if (i != 2) {
            return Protocol$ControllerType.PRIMARY;
        }
        return Protocol$ControllerType.THIRD_PARTY;
    }

    private static int controllerTypeToInt(Protocol$ControllerType protocol$ControllerType) {
        int i = AnonymousClass7.$SwitchMap$com$oculus$companion$server$Protocol$ControllerType[protocol$ControllerType.ordinal()];
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                return 0;
            }
        }
        return i2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_CONTROLLER_STATUS(Protocol$Request protocol$Request) throws InterruptedException {
        Protocol$ControllerStatusResponse.Builder newBuilder = Protocol$ControllerStatusResponse.newBuilder();
        PowerManager.WakeLock wakeLock = null;
        try {
            Protocol$ControllerStatusRequest parseFrom = Protocol$ControllerStatusRequest.parseFrom(protocol$Request.getBody());
            int timeoutMs = parseFrom.hasTimeoutMs() ? parseFrom.getTimeoutMs() : 0;
            int[] deviceTypes = ControllerManager.getDeviceTypes();
            if (deviceTypes != null) {
                if (timeoutMs > 0) {
                    wakeLock = wakeUpHeadsetAndAcquireWakeLock("controller-status");
                    Thread.sleep((long) timeoutMs);
                }
                for (int i : deviceTypes) {
                    String pairedDevice = ControllerManager.getPairedDevice(i);
                    if (pairedDevice != null) {
                        Protocol$Controller.Builder newBuilder2 = Protocol$Controller.newBuilder();
                        newBuilder2.setId(pairedDevice);
                        int batteryLevel = ControllerManager.getBatteryLevel(i);
                        if (batteryLevel >= 0 && batteryLevel <= 100) {
                            newBuilder2.setBatteryLevel(batteryLevel);
                        }
                        newBuilder2.setType(intToControllerType(i));
                        newBuilder2.setFirmwareVersion(ControllerManager.getFirmwareVersion(i));
                        if (this.controllerManager.isBluetoothDeviceConnected(pairedDevice)) {
                            newBuilder2.setState(Protocol$ControllerState.PAIRED_AND_ACTIVE);
                        } else {
                            newBuilder2.setState(Protocol$ControllerState.PAIRED_BUT_INACTIVE);
                        }
                        newBuilder.addPairedControllers((Protocol$Controller) newBuilder2.build());
                    }
                }
            }
            Set<BluetoothDevice> bluetoothBondedThirdPartyDevices = ControllerManager.bluetoothBondedThirdPartyDevices();
            if (bluetoothBondedThirdPartyDevices != null) {
                for (BluetoothDevice bluetoothDevice : bluetoothBondedThirdPartyDevices) {
                    Protocol$Controller.Builder newBuilder3 = Protocol$Controller.newBuilder();
                    newBuilder3.setId(bluetoothDevice.getAddress());
                    newBuilder3.setDisplayName(bluetoothDevice.getName());
                    newBuilder3.setType(Protocol$ControllerType.THIRD_PARTY);
                    if (this.controllerManager.isBluetoothDeviceConnected(bluetoothDevice.getAddress())) {
                        newBuilder3.setState(Protocol$ControllerState.PAIRED_AND_ACTIVE);
                    } else {
                        newBuilder3.setState(Protocol$ControllerState.PAIRED_BUT_INACTIVE);
                    }
                    newBuilder.addPairedControllers((Protocol$Controller) newBuilder3.build());
                }
            }
            String controllerHandedness = CompanionService.getControllerHandedness();
            if (controllerHandedness.equals("LEFT")) {
                newBuilder.setHandedness(Protocol$ControllerHandedness.LEFT);
            } else if (controllerHandedness.equals("RIGHT")) {
                newBuilder.setHandedness(Protocol$ControllerHandedness.RIGHT);
            } else {
                newBuilder.setHandedness(Protocol$ControllerHandedness.UNSPECIFIED);
            }
            sendSuccessResponse(protocol$Request, newBuilder.build());
            if (wakeLock != null && wakeLock.isHeld()) {
                wakeLock.release();
                if (!DEBUG) {
                    return;
                }
                Log.d(TAG, "Released the wake lock");
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "CONTROLLER STATUS Request Corrupt: " + e.getMessage());
            if (0 != 0 && wakeLock.isHeld()) {
                wakeLock.release();
                if (!DEBUG) {
                }
            }
        } catch (Throwable th) {
            if (0 != 0 && wakeLock.isHeld()) {
                wakeLock.release();
                if (DEBUG) {
                    Log.d(TAG, "Released the wake lock");
                }
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003c A[Catch:{ InvalidProtocolBufferException -> 0x0065 }] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0040 A[Catch:{ InvalidProtocolBufferException -> 0x0065 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handle_CONTROLLER_PAIR(com.oculus.companion.server.Protocol$Request r5) throws java.lang.InterruptedException {
        /*
        // Method dump skipped, instructions count: 127
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionServer.handle_CONTROLLER_PAIR(com.oculus.companion.server.Protocol$Request):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036 A[Catch:{ InvalidProtocolBufferException -> 0x0042 }] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003a A[Catch:{ InvalidProtocolBufferException -> 0x0042 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handle_CONTROLLER_UNPAIR(com.oculus.companion.server.Protocol$Request r4) throws java.lang.InterruptedException {
        /*
            r3 = this;
            com.google.protobuf.ByteString r0 = r4.getBody()     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            com.oculus.companion.server.Protocol$ControllerPairRequest r0 = com.oculus.companion.server.Protocol$ControllerPairRequest.parseFrom(r0)     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            com.oculus.companion.server.Protocol$Controller r1 = r0.getController()     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            com.oculus.companion.server.Protocol$ControllerType r1 = r1.getType()     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            com.oculus.companion.server.Protocol$Controller r0 = r0.getController()     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            java.lang.String r0 = r0.getId()     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            com.oculus.companion.server.Protocol$ControllerType r2 = com.oculus.companion.server.Protocol$ControllerType.PRIMARY     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            if (r1 == r2) goto L_0x002c
            com.oculus.companion.server.Protocol$ControllerType r2 = com.oculus.companion.server.Protocol$ControllerType.SECONDARY     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            if (r1 != r2) goto L_0x0021
            goto L_0x002c
        L_0x0021:
            com.oculus.companion.server.Protocol$ControllerType r2 = com.oculus.companion.server.Protocol$ControllerType.THIRD_PARTY     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            if (r1 != r2) goto L_0x002a
            boolean r0 = com.oculus.companion.server.ControllerManager.bluetoothUnpairDevice(r0)     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            goto L_0x0034
        L_0x002a:
            r0 = 0
            goto L_0x0034
        L_0x002c:
            int r0 = r1.getNumber()     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            boolean r0 = com.oculus.companion.server.ControllerManager.unpairDevice(r0)     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
        L_0x0034:
            if (r0 == 0) goto L_0x003a
            r3.sendSuccessResponse(r4)     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            goto L_0x005b
        L_0x003a:
            com.oculus.companion.server.Protocol$ErrorCode r0 = com.oculus.companion.server.Protocol$ErrorCode.BAD_PERIPHERAL_ADDRESS     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            java.lang.String r1 = "Could not unpair controller"
            r3.sendErrorResponse(r4, r0, r1)     // Catch:{ InvalidProtocolBufferException -> 0x0042 }
            goto L_0x005b
        L_0x0042:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "ControllerUnpairRequest corrupt: "
            r1.append(r2)
            java.lang.String r0 = r0.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r3.invalidProtocolBuffer(r4, r0)
        L_0x005b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionServer.handle_CONTROLLER_UNPAIR(com.oculus.companion.server.Protocol$Request):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_HMD_STATUS(Protocol$Request protocol$Request) {
        sendSuccessResponse(protocol$Request, this.companionState.getUpdateMessage());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_HMD_VERSION(Protocol$Request protocol$Request) {
        Protocol$HmdVersionResponse.Builder newBuilder = Protocol$HmdVersionResponse.newBuilder();
        newBuilder.setBoard(Build.BOARD);
        newBuilder.setBootloader(Build.BOOTLOADER);
        newBuilder.setBrand(Build.BRAND);
        newBuilder.setDevice(Build.DEVICE);
        newBuilder.setDisplay(Build.DISPLAY);
        newBuilder.setFingerprint(Build.FINGERPRINT);
        newBuilder.setHardware(Build.HARDWARE);
        newBuilder.setHost(Build.HOST);
        newBuilder.setId(Build.ID);
        newBuilder.setManufacturer(Build.MANUFACTURER);
        newBuilder.setModel(Build.MODEL);
        newBuilder.setProduct(Build.PRODUCT);
        newBuilder.setSerial(new BuildCompat().getSerial());
        newBuilder.setTags(Build.TAGS);
        newBuilder.setTime(Build.TIME);
        newBuilder.setType(Build.TYPE);
        newBuilder.setUser(Build.USER);
        newBuilder.setVersionBaseOs(Build.VERSION.BASE_OS);
        newBuilder.setVersionCodename(Build.VERSION.CODENAME);
        newBuilder.setVersionIncremental(Build.VERSION.INCREMENTAL);
        newBuilder.setVersionRelease(Build.VERSION.RELEASE);
        newBuilder.setVersionSecurityPatch(Build.VERSION.SECURITY_PATCH);
        sendSuccessResponse(protocol$Request, (Protocol$HmdVersionResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_HMD_CAPABILITIES(Protocol$Request protocol$Request) {
        HmdCapabilities hmdCapabilities = new HmdCapabilities(getApplicationContext());
        Log.i(TAG, hmdCapabilities.toString());
        sendSuccessResponse(protocol$Request, hmdCapabilities.getMessage());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_HMD_EXTERNAL_BATTERY_STATUS(Protocol$Request protocol$Request) {
        Protocol$HmdExternalBatteryStatusResponse.Builder newBuilder = Protocol$HmdExternalBatteryStatusResponse.newBuilder();
        newBuilder.setIsChargerPlugged(this.mMolokiniParams.isChargerPlugged());
        newBuilder.setBatteryLevel((long) this.mMolokiniParams.getBatteryLevel());
        newBuilder.setCombinedBatteryLevel((long) this.mMolokiniParams.getCombinedBatteryLevel());
        int i = AnonymousClass7.$SwitchMap$com$oculus$os$MolokiniParams$ChargingStatus[this.mMolokiniParams.getChargingStatus().ordinal()];
        if (i == 1) {
            newBuilder.setChargingStatus(Protocol$HmdExternalBatteryStatusResponse.ChargingStatus.CHARGING);
        } else if (i != 2) {
            newBuilder.setChargingStatus(Protocol$HmdExternalBatteryStatusResponse.ChargingStatus.UNKNOWN);
        } else {
            newBuilder.setChargingStatus(Protocol$HmdExternalBatteryStatusResponse.ChargingStatus.NOT_CHARGING);
        }
        sendSuccessResponse(protocol$Request, newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_APP_LAUNCH(Protocol$Request protocol$Request) {
        if (!FirstTimeNuxManager.getFirstTimeNuxComplete(ActivityManager.getCurrentUser())) {
            internalError(protocol$Request, "App launch is not allowed during NUX", true);
            return;
        }
        try {
            Protocol$AppLaunchRequest parseFrom = Protocol$AppLaunchRequest.parseFrom(protocol$Request.getBody());
            if (!CompanionService.launchApplication(getApplicationContext(), parseFrom.getAppId(), parseFrom.getLaunchParametersJson(), true, protocol$Request.getSeq(), this)) {
                internalError(protocol$Request, "CompanionService failed to send App Launch Intent", true);
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "AppLaunchRequest Corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public void response_APP_LAUNCH(int i, boolean z, String str) {
        Protocol$ResponseCode protocol$ResponseCode = z ? Protocol$ResponseCode.SUCCESS : Protocol$ResponseCode.FAIL;
        Protocol$AppLaunchResponse.Builder newBuilder = Protocol$AppLaunchResponse.newBuilder();
        if (str != null) {
            newBuilder.setErrorMessage(str);
        }
        sendSeqResponse(i, protocol$ResponseCode, newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_LOCALE_SET(Protocol$Request protocol$Request) {
        try {
            Protocol$LocaleSet parseFrom = Protocol$LocaleSet.parseFrom(protocol$Request.getBody());
            String language = parseFrom.getLanguage();
            String country = parseFrom.getCountry();
            if (language != null) {
                if (DEBUG) {
                    String str = TAG;
                    Log.d(str, "Setting language = " + language + ", country = " + country);
                }
                boolean locale = CompanionService.setLocale(getApplicationContext(), language, country);
                if (locale) {
                    sendSuccessResponse(protocol$Request);
                    CompanionService.rebootIfTrueAndNuxCompleted(getApplicationContext(), locale);
                    return;
                }
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Could not set language");
                return;
            }
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.BAD_REQUEST, "Invalid language (null)");
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "LocaleSet Corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002f A[Catch:{ InvalidProtocolBufferException -> 0x0050 }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0041 A[Catch:{ InvalidProtocolBufferException -> 0x0050 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handle_TIME_SET(com.oculus.companion.server.Protocol$Request r10) {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionServer.handle_TIME_SET(com.oculus.companion.server.Protocol$Request):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_HEALTH_AND_SAFETY_WARNING_SET(Protocol$Request protocol$Request) {
        try {
            if (this.osSDKSettingsManager.setBoolean("first_time_nux_health_safety_complete", Protocol$HealthAndSafetyWarningRequest.parseFrom(protocol$Request.getBody()).getHswOptOut(), 0)) {
                sendSuccessResponse(protocol$Request);
            } else {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Internal Error: Unable to set first_time_nux_health_safety_complete");
            }
            this.companionState.updateMiscState();
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "AppLaunchRequest Corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_NUX_COMPLETED(Protocol$Request protocol$Request) {
        if (FirstTimeNuxManager.isOtaComplete()) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.BAD_REQUEST, "NUX OTA already completed");
            return;
        }
        Context applicationContext = getApplicationContext();
        Intent intent = new Intent();
        intent.setAction("nux.ota.SET_OKAY_TO_REBOOT");
        intent.setClassName("com.oculus.nux.ota", "com.oculus.nux.ota.NuxOtaIntentService");
        applicationContext.startServiceAsUser(intent, UserHandle.SYSTEM);
        sendSuccessResponse(protocol$Request);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_SKIP_HIGH_PRI_APPS_DOWNLOAD(Protocol$Request protocol$Request) {
        Context applicationContext = getApplicationContext();
        Intent intent = new Intent();
        intent.setAction("nux.ota.SKIP_HIGH_PRI_APPS");
        intent.setClassName("com.oculus.nux.ota", "com.oculus.nux.ota.NuxOtaIntentService");
        applicationContext.startServiceAsUser(intent, UserHandle.SYSTEM);
        sendSuccessResponse(protocol$Request);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_OTA_ENABLED_STATUS(Protocol$Request protocol$Request) {
        boolean z = SystemProperties.getBoolean("persist.ovr.ota.enabled", true);
        Protocol$OtaEnabledResponse.Builder newBuilder = Protocol$OtaEnabledResponse.newBuilder();
        newBuilder.setEnabled(z);
        sendSuccessResponse(protocol$Request, (Protocol$OtaEnabledResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_OTA_ENABLED_SET(Protocol$Request protocol$Request) {
        try {
            SystemProperties.set("persist.ovr.ota.enabled", Protocol$OtaEnabledRequest.parseFrom(protocol$Request.getBody()).getEnable() ? "true" : "false");
            sendSuccessResponse(protocol$Request);
        } catch (Exception unused) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Could not set OTA enable flag");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_MIRROR_REQUEST(Protocol$Request protocol$Request) {
        boolean isHeld;
        boolean z;
        Log.d(TAG, "handle_MIRROR_REQUEST: called.");
        PowerManager.WakeLock wakeLock = null;
        try {
            wakeLock = wakeUpHeadsetAndAcquireWakeLock("mirror-request");
            try {
                Protocol$MirrorRequest parseFrom = Protocol$MirrorRequest.parseFrom(protocol$Request.getBody());
                Protocol$MirrorResponse.Builder newBuilder = Protocol$MirrorResponse.newBuilder();
                if (!parseFrom.hasStart()) {
                    sendErrorResponse(protocol$Request, Protocol$ErrorCode.BAD_REQUEST, "Mirror request missing required start.");
                    if (wakeLock != null && isHeld) {
                        if (!z) {
                            return;
                        }
                        return;
                    }
                    return;
                }
                boolean start = parseFrom.getStart();
                if (!start) {
                    lambda$handle_MIRROR_REQUEST$4$CompanionServer(protocol$Request, newBuilder, false);
                    if (wakeLock != null && wakeLock.isHeld()) {
                        wakeLock.release();
                        if (DEBUG) {
                            Log.d(TAG, "Released the wake lock");
                        }
                    }
                } else if (!parseFrom.hasShowDialog() || parseFrom.getShowDialog()) {
                    getCastDialog().showCastDialog(new Runnable(protocol$Request, newBuilder, start) {
                        /* class com.oculus.companion.server.$$Lambda$CompanionServer$DUDntzBTgXZI8YAdHAajKLGeJBg */
                        private final /* synthetic */ Protocol$Request f$1;
                        private final /* synthetic */ Protocol$MirrorResponse.Builder f$2;
                        private final /* synthetic */ boolean f$3;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                            this.f$3 = r4;
                        }

                        public final void run() {
                            CompanionServer.this.lambda$handle_MIRROR_REQUEST$4$CompanionServer(this.f$1, this.f$2, this.f$3);
                        }
                    }, new Runnable(newBuilder, protocol$Request) {
                        /* class com.oculus.companion.server.$$Lambda$CompanionServer$Kl17jtmqaEJnPd_XT5g59V7_oRQ */
                        private final /* synthetic */ Protocol$MirrorResponse.Builder f$1;
                        private final /* synthetic */ Protocol$Request f$2;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                        }

                        public final void run() {
                            CompanionServer.this.lambda$handle_MIRROR_REQUEST$5$CompanionServer(this.f$1, this.f$2);
                        }
                    }, new Runnable(protocol$Request) {
                        /* class com.oculus.companion.server.$$Lambda$CompanionServer$YT8dv6pw0X9Jwmt2UDo1JQwVbBI */
                        private final /* synthetic */ Protocol$Request f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            CompanionServer.this.lambda$handle_MIRROR_REQUEST$6$CompanionServer(this.f$1);
                        }
                    });
                    if (wakeLock != null && wakeLock.isHeld()) {
                        wakeLock.release();
                        if (DEBUG) {
                            Log.d(TAG, "Released the wake lock");
                        }
                    }
                } else {
                    Log.d(TAG, "Skip privacy dialog inside MIRROR_REQUEST");
                    lambda$handle_MIRROR_REQUEST$4$CompanionServer(protocol$Request, newBuilder, start);
                    if (wakeLock != null && wakeLock.isHeld()) {
                        wakeLock.release();
                        if (DEBUG) {
                            Log.d(TAG, "Released the wake lock");
                        }
                    }
                }
            } catch (InvalidProtocolBufferException unused) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.BAD_REQUEST, "Could not respond to MIRROR request");
                if (wakeLock != null && wakeLock.isHeld()) {
                    wakeLock.release();
                    if (DEBUG) {
                        Log.d(TAG, "Released the wake lock");
                    }
                }
            }
        } finally {
            if (wakeLock != null && wakeLock.isHeld()) {
                wakeLock.release();
                if (DEBUG) {
                    Log.d(TAG, "Released the wake lock");
                }
            }
        }
    }

    public /* synthetic */ void lambda$handle_MIRROR_REQUEST$5$CompanionServer(Protocol$MirrorResponse.Builder builder, Protocol$Request protocol$Request) {
        builder.setRunning(false);
        sendSuccessResponse(protocol$Request, builder.build());
    }

    public /* synthetic */ void lambda$handle_MIRROR_REQUEST$6$CompanionServer(Protocol$Request protocol$Request) {
        sendErrorResponse(protocol$Request, Protocol$ErrorCode.TIMED_OUT, "No User response to privacy dialog.");
    }

    private synchronized CastDialog getCastDialog() {
        if (this.mCastDialog == null) {
            this.mCastDialog = new CastDialog(this, this.secureStorage);
        }
        return this.mCastDialog;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_CRASH_REPORTS_ENABLED_STATUS(Protocol$Request protocol$Request) {
        boolean z = this.osSDKSettingsManager.getBoolean("crash_reports_enabled", false);
        Protocol$CrashReportsEnabledResponse.Builder newBuilder = Protocol$CrashReportsEnabledResponse.newBuilder();
        newBuilder.setEnabled(z);
        sendSuccessResponse(protocol$Request, (Protocol$CrashReportsEnabledResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_CRASH_REPORTS_ENABLED_SET(Protocol$Request protocol$Request) {
        try {
            this.osSDKSettingsManager.setBoolean("crash_reports_enabled", Protocol$CrashReportsEnabledRequest.parseFrom(protocol$Request.getBody()).getEnable());
            sendSuccessResponse(protocol$Request);
            recordCrashReportsEnabledEvent();
        } catch (Exception unused) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Could not set the crash reports enable flag");
        }
    }

    private void recordCrashReportsEnabledEvent() {
        Context applicationContext = getApplicationContext();
        if (this.telemetry == null) {
            this.telemetry = new Telemetry(applicationContext);
        }
        this.telemetry.recordCrashReportsEnabledEvent(applicationContext);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_AUTOWAKE_STATUS(Protocol$Request protocol$Request) {
        boolean z = this.osSDKSettingsManager.getBoolean("autowake", true, 0);
        Protocol$AutowakeResponse.Builder newBuilder = Protocol$AutowakeResponse.newBuilder();
        newBuilder.setEnabled(z);
        sendSuccessResponse(protocol$Request, (Protocol$AutowakeResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_AUTOWAKE_SET(Protocol$Request protocol$Request) {
        try {
            this.osSDKSettingsManager.setBoolean("autowake", Protocol$AutowakeRequest.parseFrom(protocol$Request.getBody()).getEnable(), 0);
            sendSuccessResponse(protocol$Request);
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "AutowakeRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_LINE_FREQUENCY_SET(Protocol$Request protocol$Request) {
        try {
            CompanionService.setLineFrequency(getApplicationContext(), Protocol$LineFrequencyRequest.parseFrom(protocol$Request.getBody()).getLineFrequency());
            sendSuccessResponse(protocol$Request);
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "LineFreqRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_LINE_FREQUENCY_STATUS(Protocol$Request protocol$Request) {
        int lineFrequency = CompanionService.getLineFrequency(getApplicationContext());
        Protocol$LineFrequencyResponse.Builder newBuilder = Protocol$LineFrequencyResponse.newBuilder();
        newBuilder.setLineFrequency(lineFrequency);
        sendSuccessResponse(protocol$Request, (Protocol$LineFrequencyResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_PHONE_NOTIFICATION_SET(Protocol$Request protocol$Request) {
        if (!sPhoneNotificationsGK.isEnabled()) {
            Log.e(TAG, "Failed oculus_mobile_enable_phone_notifications GK while handling PHONE_NOTIFICATION_SET request.");
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.GK_CHECK_FAILED, "Failed oculus_mobile_enabled_phone_notifications GK");
            return;
        }
        try {
            Protocol$PhoneNotificationRequest parseFrom = Protocol$PhoneNotificationRequest.parseFrom(protocol$Request.getBody());
            boolean enable = parseFrom.getEnable();
            boolean z = this.osSDKSettingsManager.getBoolean("phone_notification_enabled", false);
            if (enable != z) {
                this.osSDKSettingsManager.setBoolean("phone_notification_enabled", enable);
            }
            boolean allowAllApps = parseFrom.getAllowAllApps();
            if (allowAllApps != this.osSDKSettingsManager.getBoolean("phone_notification_allow_all_apps", false)) {
                this.osSDKSettingsManager.setBoolean("phone_notification_allow_all_apps", allowAllApps);
            }
            String appFilters = parseFrom.getAppFilters();
            if (!(appFilters == null || appFilters == this.osSDKSettingsManager.getString("phone_notification_apps", ""))) {
                this.osSDKSettingsManager.setString("phone_notification_apps", appFilters);
            }
            if (enable != z) {
                handlePhoneNotificationsEnabledChanged(enable);
            }
            sendSuccessResponse(protocol$Request);
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "PhoneNotificationRequest corrupt: " + e.getMessage());
        }
    }

    private void handlePhoneNotificationsEnabledChanged(boolean z) {
        if (z) {
            String mostRecentBluetoothDeviceAddress = this.bleModule.getMostRecentBluetoothDeviceAddress();
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
            edit.putString("phone_notifications_session_ble_address", mostRecentBluetoothDeviceAddress);
            edit.apply();
            String str = TAG;
            Log.d(str, "Caching connected BLE device address: " + mostRecentBluetoothDeviceAddress);
            if (mostRecentBluetoothDeviceAddress != null) {
                String str2 = TAG;
                Log.d(str2, "Attempting to connect to: " + mostRecentBluetoothDeviceAddress);
                this.bluetoothService.connect(mostRecentBluetoothDeviceAddress);
                return;
            }
            Log.d(TAG, "Failed to connect to null device address");
            return;
        }
        SharedPreferences.Editor edit2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
        edit2.putString("phone_notifications_session_ble_address", "");
        edit2.apply();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_PHONE_NOTIFICATION_STATUS(Protocol$Request protocol$Request) {
        if (!sPhoneNotificationsGK.isEnabled()) {
            Log.e(TAG, "Failed oculus_mobile_enable_phone_notifications GK while handling PHONE_NOTIFICATION_STATUS request.");
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.GK_CHECK_FAILED, "Failed oculus_mobile_enabled_phone_notifications GK");
            return;
        }
        boolean z = this.osSDKSettingsManager.getBoolean("phone_notification_enabled", false);
        boolean z2 = this.osSDKSettingsManager.getBoolean("phone_notification_allow_all_apps", false);
        String string = this.osSDKSettingsManager.getString("phone_notification_apps", "");
        Protocol$PhoneNotificationResponse.Builder newBuilder = Protocol$PhoneNotificationResponse.newBuilder();
        newBuilder.setEnabled(z);
        newBuilder.setAllowAllApps(z2);
        newBuilder.setAppFilters(string);
        sendSuccessResponse(protocol$Request, (Protocol$PhoneNotificationResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_MANAGED_MODE_SET(Protocol$Request protocol$Request) {
        try {
            int level = Protocol$ManagedModeSet.parseFrom(protocol$Request.getBody()).getLevel();
            this.secureStorage.storeValue("managed_device", level);
            this.osSDKSettingsManager.setInt("managed_device", level);
            sendSuccessResponse(protocol$Request);
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "ManagedModeSet corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_MANAGED_MODE_STATUS(Protocol$Request protocol$Request) {
        int intValue = this.secureStorage.getIntValue("managed_device");
        Protocol$ManagedModeResponse.Builder newBuilder = Protocol$ManagedModeResponse.newBuilder();
        newBuilder.setLevel(intValue);
        sendSuccessResponse(protocol$Request, (Protocol$ManagedModeResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_AUTOSLEEP_TIME_STATUS(Protocol$Request protocol$Request) {
        int i = this.osSDKSettingsManager.getInt("autosleep_time", 15, 0);
        Protocol$AutosleepTimeResponse.Builder newBuilder = Protocol$AutosleepTimeResponse.newBuilder();
        newBuilder.setInterval(i);
        sendSuccessResponse(protocol$Request, (Protocol$AutosleepTimeResponse) newBuilder.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_AUTOSLEEP_TIME_SET(Protocol$Request protocol$Request) {
        try {
            this.osSDKSettingsManager.setInt("autosleep_time", Protocol$AutosleepTimeRequest.parseFrom(protocol$Request.getBody()).getInterval(), 0);
            sendSuccessResponse(protocol$Request);
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "AutosleepTimeRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_NAME_SET(Protocol$Request protocol$Request) {
        try {
            sendResponse(protocol$Request, CompanionService.nameSet(getApplicationContext(), Protocol$NameSetRequest.parseFrom(protocol$Request.getBody()).getName()) ? Protocol$ResponseCode.SUCCESS : Protocol$ResponseCode.FAIL, null);
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "NameSetRequest corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_TEXT_SEND(Protocol$Request protocol$Request) {
        try {
            String text = Protocol$TextSend.parseFrom(protocol$Request.getBody()).getText();
            if (text == null) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.BAD_REQUEST, "Invalid text (null)");
            } else if (CompanionService.sendText(getApplicationContext(), text)) {
                sendSuccessResponse(protocol$Request);
            } else {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Could not set text");
            }
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "TextSend Corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_RENDER_KEYBOARD_START(Protocol$Request protocol$Request) {
        try {
            CompanionService.sendRenderKeyboardStart(getApplicationContext(), Protocol$RenderKeyboardStart.parseFrom(protocol$Request.getBody()).getHardwareId());
            this.deviceRenderingKeyboard = this.bleModule.getMostRecentBluetoothDeviceAddress();
            sendSuccessResponse(protocol$Request);
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "RenderKeyboardStart Corrupt: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_RENDER_KEYBOARD_STOP(Protocol$Request protocol$Request) {
        try {
            stopRenderingKeyboard(Protocol$RenderKeyboardStop.parseFrom(protocol$Request.getBody()).getHardwareId());
            sendSuccessResponse(protocol$Request);
        } catch (InvalidProtocolBufferException e) {
            invalidProtocolBuffer(protocol$Request, "RenderKeyboardStop Corrupt: " + e.getMessage());
        }
    }

    public void stopIfRenderingKeyboard(String str) {
        String str2 = this.deviceRenderingKeyboard;
        if (str2 != null && str2.equals(str)) {
            stopRenderingKeyboard(str);
        }
    }

    private void stopRenderingKeyboard(String str) {
        CompanionService.sendRenderKeyboardStop(getApplicationContext(), str);
        this.deviceRenderingKeyboard = null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_RESET_GUARDIAN(Protocol$Request protocol$Request) {
        if (DEBUG) {
            Log.d(TAG, "handle_RESET_GUARDIAN: Handled reset guardian");
        }
        Context applicationContext = getApplicationContext();
        if (new TourGuideAPI(applicationContext).isGuardianSetupActiveCall()) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.ALREADY_IN_PROGRESS, "Already in guardian setup");
            return;
        }
        String runningAppPackageName = getRunningAppPackageName();
        if (runningAppPackageName != null) {
            Intent intent = new Intent();
            intent.setClassName("com.oculus.vrshell", "com.oculus.vrshell.MainActivity");
            intent.setData(Uri.parse("systemux://guardian/setup"));
            intent.putExtra("intent_pkg", runningAppPackageName);
            intent.addFlags(268435456);
            applicationContext.startActivity(intent);
            logEvent("tour_guide_success", "RESET_GUARDIAN", -1, true);
            sendSuccessResponse(protocol$Request);
            return;
        }
        logEvent("tour_guide_failure", "RESET_GUARDIAN", 6, true);
        sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Could not get package name");
    }

    private String getRunningAppPackageName() {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) getApplicationContext().getSystemService("activity")).getRunningTasks(1);
        if (runningTasks != null) {
            runningTasks.isEmpty();
        }
        try {
            return runningTasks.get(0).topActivity.getPackageName();
        } catch (Exception e) {
            Log.e(TAG, "Error getting top activity package name", e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_RESET_HEADSET_VIEW(Protocol$Request protocol$Request) {
        if (DEBUG) {
            Log.d(TAG, "handle_RESET_HEADSET_VIEW: Handled reset headset view");
        }
        if (new TourGuideAPI(getApplicationContext()).resetHeadsetViewCall()) {
            logEvent("tour_guide_success", "RESET_HEADSET_VIEW", -1, true);
            sendSuccessResponse(protocol$Request);
            return;
        }
        logEvent("tour_guide_failure", "RESET_HEADSET_VIEW", 6, true);
        sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Could not reset headset view");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_TOGGLE_PASSTHROUGH(Protocol$Request protocol$Request) {
        if (DEBUG) {
            Log.d(TAG, "handle_TOGGLE_PASSTHROUGH: Toggled passthrough mode.");
        }
        Context applicationContext = getApplicationContext();
        Intent intent = new Intent();
        intent.setClassName("com.oculus.vrshell", "com.oculus.vrshell.MainActivity");
        intent.setData(Uri.parse("systemux://guardian/passthrough-on-demand?state=toggle"));
        intent.addFlags(268435456);
        String runningAppPackageName = getRunningAppPackageName();
        if (runningAppPackageName != null) {
            intent.putExtra("intent_pkg", runningAppPackageName);
        }
        applicationContext.startActivity(intent);
        sendSuccessResponse(protocol$Request);
        logEvent("tour_guide_success", "TOGGLE_PASSTHROUGH", -1, true);
    }

    private boolean requireUserPin(Context context) {
        return !((UserManager) context.getSystemService("user")).isUserUnlocked();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isCurrentUserTheOwner() {
        return ActivityManager.getCurrentUser() == 0;
    }

    private PowerManager.WakeLock wakeUpHeadsetAndAcquireWakeLock(String str) {
        initializePowerManager();
        PowerManager powerManager2 = this.powerManager;
        long uptimeMillis = SystemClock.uptimeMillis();
        powerManager2.wakeUp(uptimeMillis, "Companion:" + str);
        PowerManager.WakeLock newWakeLock = this.powerManager.newWakeLock(1, TAG);
        if (DEBUG) {
            Log.d(TAG, "Acquiring wakelock");
        }
        newWakeLock.acquire();
        return newWakeLock;
    }

    private void initializePowerManager() {
        if (this.powerManager == null) {
            this.powerManager = (PowerManager) getSystemService("power");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: handleCastingRequest */
    public void lambda$handle_MIRROR_REQUEST$4$CompanionServer(Protocol$Request protocol$Request, Protocol$MirrorResponse.Builder builder, boolean z) {
        if (DEBUG) {
            String str = TAG;
            Log.d(str, "handleCastingRequest: start = " + z);
        }
        try {
            Bundle enableCasting = CompanionService.enableCasting(getApplicationContext(), z, 20000);
            if (enableCasting != null && enableCasting.getBoolean("SUCCESS")) {
                String string = enableCasting.getString("cast_server_url");
                String string2 = enableCasting.getString("cast_session_id");
                builder.setUri(string);
                builder.setPort(string2);
                builder.setHostname("");
                builder.setIpAddress(this.wifiModule.getIpAddress());
                builder.setRunning(z);
                sendSuccessResponse(protocol$Request, builder.build());
            } else if (enableCasting != null) {
                sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, enableCasting.getString("cast_server_error", "Undefined"));
            }
        } catch (InterruptedException unused) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Could not respond to MIRROR request");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: sendGetDevelopmentLicenseDetailMessage */
    public void lambda$handle_DEVELOPMENT_LICENSE_DETAILS$7$CompanionServer(Messenger messenger, final Protocol$Request protocol$Request) {
        if (messenger == null) {
            internalError(protocol$Request, "Failed to bind with LicenseManagementService", true);
            return;
        }
        HandlerThread handlerThread = new HandlerThread("GetDevelopmentLicenseDetailsResponseHandler");
        handlerThread.start();
        AnonymousClass3 r2 = new Handler(handlerThread.getLooper()) {
            /* class com.oculus.companion.server.CompanionServer.AnonymousClass3 */

            public void handleMessage(Message message) {
                if (message.what == 102) {
                    Bundle peekData = message.peekData();
                    if (peekData == null) {
                        CompanionServer.this.internalError(protocol$Request, "Response does not setData correctly", true);
                    }
                    if (!peekData.getBoolean("success")) {
                        CompanionServer.this.internalError(protocol$Request, String.format("Error getting development license: %s", peekData.getString("error_message")), true);
                    } else if (peekData.getInt("status") == 1) {
                        CompanionServer.this.sendErrorResponse(protocol$Request, Protocol$ErrorCode.DEVELOPMENT_LICENSE_DOES_NOT_EXIST, "Development license does not exist.");
                    } else {
                        Protocol$DevelopmentLicenseDetailsResponse.Builder newBuilder = Protocol$DevelopmentLicenseDetailsResponse.newBuilder();
                        newBuilder.setExpiryTimeS(peekData.getLong("expiry_time_sec"));
                        newBuilder.setIssuedTimeS(peekData.getLong("issue_time_sec"));
                        newBuilder.setRevoked(peekData.getInt("status") == 2);
                        Protocol$DevelopmentLicenseDetailsResponse protocol$DevelopmentLicenseDetailsResponse = (Protocol$DevelopmentLicenseDetailsResponse) newBuilder.build();
                        Log.d(CompanionServer.TAG, String.format("Got Development License details: expiry_time_s: %d, issued_time_s: %d, revoked: %b", Long.valueOf(protocol$DevelopmentLicenseDetailsResponse.getExpiryTimeS()), Long.valueOf(protocol$DevelopmentLicenseDetailsResponse.getIssuedTimeS()), Boolean.valueOf(protocol$DevelopmentLicenseDetailsResponse.getRevoked())));
                        CompanionServer.this.sendSuccessResponseAndLogEvent(protocol$Request, protocol$DevelopmentLicenseDetailsResponse);
                    }
                }
                getLooper().quitSafely();
            }
        };
        try {
            Message obtain = Message.obtain((Handler) null, 2);
            obtain.replyTo = new Messenger(r2);
            messenger.send(obtain);
        } catch (RemoteException e) {
            Log.e(TAG, "Exception when sending message to LicenseManagementService", e);
            internalError(protocol$Request, "Failed to send message LicenseManagementService", true);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_DEVELOPMENT_LICENSE_DETAILS(Protocol$Request protocol$Request) {
        try {
            getPackageManager().getPackageInfo("com.oculus.license", 0);
            this.mLicenseManagementConnector.getFutureMessenger().thenAccept((Consumer<? super Messenger>) new Consumer(protocol$Request) {
                /* class com.oculus.companion.server.$$Lambda$CompanionServer$59mcaaH6enR6EnHBZN0mYRB6qh4 */
                private final /* synthetic */ Protocol$Request f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CompanionServer.this.lambda$handle_DEVELOPMENT_LICENSE_DETAILS$7$CompanionServer(this.f$1, (Messenger) obj);
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function(protocol$Request) {
                /* class com.oculus.companion.server.$$Lambda$CompanionServer$06D03FvlmBUM3YBhHO8kFBvrbQ */
                private final /* synthetic */ Protocol$Request f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CompanionServer.this.lambda$handle_DEVELOPMENT_LICENSE_DETAILS$8$CompanionServer(this.f$1, (Throwable) obj);
                }
            });
        } catch (PackageManager.NameNotFoundException unused) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNSUPPORTED_METHOD, "This device does not support this feature.");
        }
    }

    public /* synthetic */ Void lambda$handle_DEVELOPMENT_LICENSE_DETAILS$8$CompanionServer(Protocol$Request protocol$Request, Throwable th) {
        internalError(protocol$Request, th.getMessage(), true);
        return null;
    }

    /* access modifiers changed from: private */
    /* renamed from: sendDevelopmentLicenseRefreshMessage */
    public void lambda$handle_DEVELOPMENT_LICENSE_REFRESH$9$CompanionServer(Messenger messenger, final Protocol$Request protocol$Request) {
        if (messenger == null) {
            internalError(protocol$Request, "Failed to bind with LicenseManagementService", true);
            return;
        }
        HandlerThread handlerThread = new HandlerThread("LicenseManagementReplyHandler");
        handlerThread.start();
        AnonymousClass4 r2 = new Handler(handlerThread.getLooper()) {
            /* class com.oculus.companion.server.CompanionServer.AnonymousClass4 */

            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 100) {
                    CompanionServer.this.sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNKNOWN_ERROR, "Development license refresh request failed");
                } else if (i == 101) {
                    CompanionServer.this.sendSuccessResponseAndLogEvent(protocol$Request);
                }
                getLooper().quitSafely();
            }
        };
        try {
            Message obtain = Message.obtain((Handler) null, 1);
            obtain.replyTo = new Messenger(r2);
            messenger.send(obtain);
        } catch (RemoteException e) {
            Log.e(TAG, "Exception when sending message to LicenseManagementService", e);
            internalError(protocol$Request, "Failed to send message LicenseManagementService", true);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handle_DEVELOPMENT_LICENSE_REFRESH(Protocol$Request protocol$Request) {
        if (!this.wifiModule.isConnectedToInternet()) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.WIFI_NO_INTERNET, "HMD must be connected to internet to refresh development license");
            return;
        }
        try {
            getPackageManager().getPackageInfo("com.oculus.license", 0);
            this.mLicenseManagementConnector.getFutureMessenger().thenAccept((Consumer<? super Messenger>) new Consumer(protocol$Request) {
                /* class com.oculus.companion.server.$$Lambda$CompanionServer$O_dtvoUpUhO7vc5Ua4ESy7cTE3g */
                private final /* synthetic */ Protocol$Request f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CompanionServer.this.lambda$handle_DEVELOPMENT_LICENSE_REFRESH$9$CompanionServer(this.f$1, (Messenger) obj);
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function(protocol$Request) {
                /* class com.oculus.companion.server.$$Lambda$CompanionServer$YNdSNXLqIfPqIcWss2H1S4ZuzBk */
                private final /* synthetic */ Protocol$Request f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CompanionServer.this.lambda$handle_DEVELOPMENT_LICENSE_REFRESH$10$CompanionServer(this.f$1, (Throwable) obj);
                }
            });
        } catch (PackageManager.NameNotFoundException unused) {
            sendErrorResponse(protocol$Request, Protocol$ErrorCode.UNSUPPORTED_METHOD, "This device does not support this feature.");
        }
    }

    public /* synthetic */ Void lambda$handle_DEVELOPMENT_LICENSE_REFRESH$10$CompanionServer(Protocol$Request protocol$Request, Throwable th) {
        internalError(protocol$Request, th.getMessage(), true);
        return null;
    }

    public enum MCSState {
        MCS_STATE_INIT(0),
        MCS_FRESH_HELLO(4),
        MCS_EXCHANGE_HELLO(1),
        MCS_CHALLENGE_RESPONSE(2),
        MCS_WAIT_FOR_COMMAND(3);
        
        final int value;

        private MCSState(int i) {
            this.value = i;
        }

        public static MCSState getMCSState(int i) {
            MCSState[] values = values();
            for (MCSState mCSState : values) {
                if (mCSState.value == i) {
                    return mCSState;
                }
            }
            throw new UnsupportedOperationException("Unknown State");
        }
    }

    public void dropConnection(DisconnectKey disconnectKey) {
        if (DEBUG) {
            String str = TAG;
            Log.d(str, "dropConnection. key = " + disconnectKey);
        }
        int i = AnonymousClass7.$SwitchMap$com$oculus$companion$server$CompanionServer$DisconnectKey[disconnectKey.ordinal()];
        if (i == 1) {
            this.bleModule.disconnectAllKeepLast();
        } else if (i == 2) {
            this.bleModule.disconnectLastDevices();
        } else if (i != 3) {
            Log.e(TAG, "Invalid DisonnectKey");
        } else {
            this.bleModule.disconnectAll();
        }
        if (disconnectKey == DisconnectKey.ALL) {
            if (DEBUG) {
                Log.d(TAG, "dropConnection Clean up!");
            }
            this.mThreadPoolExecutor.shutdownNow();
            this.mThreadPoolExecutor = new ThreadPoolExecutor(POOL_THREADS, MAX_POOL_THREADS, (long) THREAD_KEEPALIVE_SEC, TimeUnit.SECONDS, this.mWorkQueue);
            if (DEBUG) {
                Log.d(TAG, "Interrupting receiverThread");
            }
            this.receiverThread.interrupt();
            this.bleModule.resetReceiver();
        }
        this.mConnectionState.isAuthenticated.set(false);
        this.mSecureConnection.resetCurrentSecureConnection();
        this.mSecureConnection.resetSequences();
        this.invalidDevice = false;
    }

    public void handleHandshakeState() {
        if (this.bleModule.isConnected()) {
            MCSState mCSState = null;
            if (this.invalidDevice) {
                dropConnection(DisconnectKey.MOST_RECENT);
                mCSState = MCSState.MCS_EXCHANGE_HELLO;
                this.mSecureConnection.resetSequences();
            } else if (!this.mSecureConnection.isCurrentConnectionSecure()) {
                mCSState = MCSState.MCS_EXCHANGE_HELLO;
            } else if (this.mConnectionState.isClaimedByUser && this.mConnectionState.isAuthenticated.get()) {
                mCSState = MCSState.MCS_WAIT_FOR_COMMAND;
            } else if (this.mConnectionState.isClaimedByUser) {
                mCSState = MCSState.MCS_CHALLENGE_RESPONSE;
            }
            if (mCSState != null) {
                synchronized (this.mState) {
                    this.mState = mCSState;
                }
            }
        } else {
            Log.w(TAG, "No connection");
            dropConnection(DisconnectKey.ALL);
        }
        if (this.companionState == null) {
            return;
        }
        if (this.bleModule.hasNotify()) {
            this.companionState.startMonitor();
        } else {
            this.companionState.stopMonitor();
        }
    }

    public void discardPreviousConnectionState() {
        dropConnection(DisconnectKey.ALL_KEEP_LAST);
        this.mConnectionState.secureConnection.set(true);
    }

    public void doStateInit() {
        try {
            if (DEBUG) {
                Log.d(TAG, "Initializing Companion Server");
            }
            synchronized (initLock) {
                this.telemetry = new Telemetry(getApplicationContext());
                this.mSecureConnection = new SecureConnection(new Authentication(), this, getApplicationContext(), new CertificateModule(this), this.mConnectionState);
                this.mLicenseManagementConnector = new LicenseManagementConnector(this);
                WifiModule wifiModule2 = new WifiModule();
                wifiModule2.initialize(this);
                this.wifiModule = wifiModule2;
                BleModule bleModule2 = new BleModule();
                bleModule2.initialize(this);
                this.bleModule = bleModule2;
                this.bluetoothService = new BluetoothService(this);
                this.secureStorage = new SecureStorage(this);
                this.controllerManager = new ControllerManager(this, this.telemetry);
                this.mMolokiniParams = new MolokiniParams(getApplicationContext());
                this.osSDKSettingsManager = new SettingsManager();
                this.instrumentation = new Instrumentation();
                this.bleModule.setControllerManager(this.controllerManager);
                CompanionState companionState2 = new CompanionState();
                companionState2.initialize(this, this.bleModule, this.wifiModule, this.controllerManager, this.secureStorage, this.telemetry, this.osSDKSettingsManager);
                this.companionState = companionState2;
                this.controllerManager.setCompanionState(this.companionState);
                this.mThreadPoolExecutor.execute(new Runnable() {
                    /* class com.oculus.companion.server.$$Lambda$CompanionServer$bIoZcfDdW4SZ5AcItV3sDDctcys */

                    public final void run() {
                        CompanionServer.this.lambda$doStateInit$11$CompanionServer();
                    }
                });
                CompanionDeviceAdmin.setActive(this);
                initLock.notifyAll();
            }
            this.mConnectionState.isClaimedByUser = this.secureStorage.getBooleanValue("headset_claimed_by_user");
            this.mThreadPoolExecutor.execute(new Runnable() {
                /* class com.oculus.companion.server.$$Lambda$CompanionServer$Yv3Nq32FfZokQ_tSK7k4LB070 */

                public final void run() {
                    CompanionServer.this.lambda$doStateInit$12$CompanionServer();
                }
            });
            int pinStatus = CompanionService.pinStatus(getApplicationContext(), 0);
            this.devicePinSet = (pinStatus & 1) > 0;
            this.devicePinLocked = (pinStatus & 2) > 0;
            if (DEBUG) {
                Log.d(TAG, String.format("isClaimedByUser = %b\nisNuxOtaCompleted = %b\ndevicePinSet = %b\ndevicePinLocked = %b", Boolean.valueOf(this.mConnectionState.isClaimedByUser), Boolean.valueOf(FirstTimeNuxManager.isOtaComplete()), Boolean.valueOf(this.devicePinSet), Boolean.valueOf(this.devicePinLocked)));
            }
            logEvent("bt_addr", BluetoothAdapter.getDefaultAdapter().getAddress(), 3, true);
        } catch (Exception e) {
            logEvent("do_state_init", "Could not start - exiting: " + e.getMessage(), 6, true);
            throw new RuntimeException("Could not start CompanionServer", e);
        }
    }

    public /* synthetic */ void lambda$doStateInit$11$CompanionServer() {
        this.controllerManager.asyncInit();
    }

    /* access modifiers changed from: private */
    /* renamed from: startBluetooth */
    public void lambda$doStateInit$12$CompanionServer() {
        this.bleModule.asyncInit();
        int intValue = this.secureStorage.getIntValue("managed_device");
        int i = SystemProperties.getInt("com.oculus.managed", -1);
        if (!(i == -1 || i == intValue)) {
            this.secureStorage.storeValue("managed_device", i);
            this.osSDKSettingsManager.setInt("managed_device", i);
            intValue = i;
        }
        boolean z = true;
        if (intValue <= 1) {
            z = false;
        }
        this.bleModule.setClaimed(this.mConnectionState.isClaimedByUser);
        this.bleModule.setManaged(z);
        if (this.receiveHandler == null) {
            this.receiverThread = new HandlerThread("CS Receiver");
            this.receiverThread.start();
            this.receiveHandler = new CompanionBLEReceiver(this.receiverThread.getLooper());
        }
        this.bleModule.startReceiver(this.receiveHandler);
        this.receiveHandler.post(new Runnable() {
            /* class com.oculus.companion.server.CompanionServer.AnonymousClass5 */

            public void run() {
                tryStartBluetooth(0);
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void tryStartBluetooth(final int i) {
                if (i > 3) {
                    Log.e(CompanionServer.TAG, "Could not start BLE Advertising! Giving up");
                } else if (!CompanionServer.this.bleModule.startAdvertising()) {
                    CompanionServer.this.receiveHandler.postDelayed(new Runnable() {
                        /* class com.oculus.companion.server.CompanionServer.AnonymousClass5.AnonymousClass1 */

                        public void run() {
                            AnonymousClass5.this.tryStartBluetooth(i + 1);
                        }
                    }, 5000);
                }
            }
        });
    }

    public void InjectBLE(Protocol$Request protocol$Request) {
        if (Build.IS_USERDEBUG) {
            this.receiveHandler.dispatch(protocol$Request);
        }
    }

    /* JADX WARN: Type inference failed for: r1v8, types: [com.oculus.companion.server.CompanionServer$LocalBinder, android.os.IBinder] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate() {
        /*
        // Method dump skipped, instructions count: 178
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionServer.onCreate():void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.companion.server.CompanionServer$7  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$companion$server$CompanionServer$DisconnectKey = new int[DisconnectKey.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$companion$server$CompanionServer$MCSState = new int[MCSState.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$companion$server$ControllerManager$ControllerScanAndPairResult = new int[ControllerManager.ControllerScanAndPairResult.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$companion$server$Protocol$ControllerType = new int[Protocol$ControllerType.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$companion$server$Protocol$Method = new int[Protocol$Method.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$companion$server$WifiModule$WifiModuleState = new int[WifiModule.WifiModuleState.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$MolokiniParams$ChargingStatus = new int[MolokiniParams.ChargingStatus.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(210:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|(2:25|26)|27|29|30|(2:31|32)|33|35|36|(2:37|38)|39|41|42|43|44|45|46|47|48|(2:49|50)|51|(2:53|54)|55|57|58|59|60|61|62|63|64|65|66|(2:67|68)|69|(2:71|72)|73|(2:75|76)|77|(2:79|80)|81|(2:83|84)|85|(2:87|88)|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|(3:241|242|244)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(212:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|(2:25|26)|27|29|30|(2:31|32)|33|35|36|(2:37|38)|39|41|42|43|44|45|46|47|48|(2:49|50)|51|(2:53|54)|55|57|58|59|60|61|62|63|64|65|66|(2:67|68)|69|(2:71|72)|73|(2:75|76)|77|(2:79|80)|81|(2:83|84)|85|(2:87|88)|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|241|242|244) */
        /* JADX WARNING: Can't wrap try/catch for region: R(213:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|(2:25|26)|27|29|30|(2:31|32)|33|35|36|(2:37|38)|39|41|42|43|44|45|46|47|48|(2:49|50)|51|53|54|55|57|58|59|60|61|62|63|64|65|66|(2:67|68)|69|(2:71|72)|73|(2:75|76)|77|(2:79|80)|81|(2:83|84)|85|(2:87|88)|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|241|242|244) */
        /* JADX WARNING: Can't wrap try/catch for region: R(214:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|(2:25|26)|27|29|30|(2:31|32)|33|35|36|(2:37|38)|39|41|42|43|44|45|46|47|48|49|50|51|53|54|55|57|58|59|60|61|62|63|64|65|66|(2:67|68)|69|(2:71|72)|73|(2:75|76)|77|(2:79|80)|81|(2:83|84)|85|(2:87|88)|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|241|242|244) */
        /* JADX WARNING: Can't wrap try/catch for region: R(215:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|(2:25|26)|27|29|30|(2:31|32)|33|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|51|53|54|55|57|58|59|60|61|62|63|64|65|66|(2:67|68)|69|(2:71|72)|73|(2:75|76)|77|(2:79|80)|81|(2:83|84)|85|(2:87|88)|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|241|242|244) */
        /* JADX WARNING: Can't wrap try/catch for region: R(216:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|(2:25|26)|27|29|30|31|32|33|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|51|53|54|55|57|58|59|60|61|62|63|64|65|66|(2:67|68)|69|(2:71|72)|73|(2:75|76)|77|(2:79|80)|81|(2:83|84)|85|(2:87|88)|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|241|242|244) */
        /* JADX WARNING: Can't wrap try/catch for region: R(218:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|26|27|29|30|31|32|33|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|51|53|54|55|57|58|59|60|61|62|63|64|65|66|(2:67|68)|69|(2:71|72)|73|(2:75|76)|77|(2:79|80)|81|(2:83|84)|85|87|88|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|241|242|244) */
        /* JADX WARNING: Can't wrap try/catch for region: R(219:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|26|27|29|30|31|32|33|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|51|53|54|55|57|58|59|60|61|62|63|64|65|66|(2:67|68)|69|(2:71|72)|73|(2:75|76)|77|(2:79|80)|81|83|84|85|87|88|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|241|242|244) */
        /* JADX WARNING: Can't wrap try/catch for region: R(220:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|26|27|29|30|31|32|33|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|51|53|54|55|57|58|59|60|61|62|63|64|65|66|(2:67|68)|69|(2:71|72)|73|(2:75|76)|77|79|80|81|83|84|85|87|88|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|241|242|244) */
        /* JADX WARNING: Can't wrap try/catch for region: R(222:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|24|25|26|27|29|30|31|32|33|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|51|53|54|55|57|58|59|60|61|62|63|64|65|66|(2:67|68)|69|(2:71|72)|73|75|76|77|79|80|81|83|84|85|87|88|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|241|242|244) */
        /* JADX WARNING: Can't wrap try/catch for region: R(224:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|24|25|26|27|29|30|31|32|33|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|51|53|54|55|57|58|59|60|61|62|63|64|65|66|(2:67|68)|69|71|72|73|75|76|77|79|80|81|83|84|85|87|88|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|241|242|244) */
        /* JADX WARNING: Can't wrap try/catch for region: R(226:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|24|25|26|27|29|30|31|32|33|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|51|53|54|55|57|58|59|60|61|62|63|64|65|66|67|68|69|71|72|73|75|76|77|79|80|81|83|84|85|87|88|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|241|242|244) */
        /* JADX WARNING: Can't wrap try/catch for region: R(227:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|24|25|26|27|29|30|31|32|33|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|51|53|54|55|57|58|59|60|61|62|63|64|65|66|67|68|69|71|72|73|75|76|77|79|80|81|83|84|85|87|88|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|241|242|244) */
        /* JADX WARNING: Can't wrap try/catch for region: R(228:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|24|25|26|27|29|30|31|32|33|35|36|37|38|39|41|42|43|44|45|46|47|48|49|50|51|53|54|55|57|58|59|60|61|62|63|64|65|66|67|68|69|71|72|73|75|76|77|79|80|81|83|84|85|87|88|89|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|175|176|177|178|179|180|181|182|183|184|185|186|187|188|189|190|191|192|193|194|195|196|197|198|199|200|201|202|203|204|205|206|207|208|209|210|211|212|213|214|215|216|217|218|219|220|221|222|223|224|225|226|227|228|229|230|231|232|233|234|235|236|237|238|239|240|241|242|244) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:101:0x01a2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:103:0x01ac */
        /* JADX WARNING: Missing exception handler attribute for start block: B:105:0x01b6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:107:0x01c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:109:0x01ca */
        /* JADX WARNING: Missing exception handler attribute for start block: B:111:0x01d4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:113:0x01de */
        /* JADX WARNING: Missing exception handler attribute for start block: B:115:0x01ea */
        /* JADX WARNING: Missing exception handler attribute for start block: B:117:0x01f6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:119:0x0202 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:121:0x020e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:123:0x021a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:125:0x0226 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:127:0x0232 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:129:0x023e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:131:0x024a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:133:0x0256 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:135:0x0262 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:137:0x026e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:139:0x027a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:141:0x0286 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:143:0x0292 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:145:0x029e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:147:0x02aa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:149:0x02b6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:151:0x02c2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:153:0x02ce */
        /* JADX WARNING: Missing exception handler attribute for start block: B:155:0x02da */
        /* JADX WARNING: Missing exception handler attribute for start block: B:157:0x02e6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:159:0x02f2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:161:0x02fe */
        /* JADX WARNING: Missing exception handler attribute for start block: B:163:0x030a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:165:0x0316 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:167:0x0322 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:169:0x032e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:171:0x033a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:173:0x0346 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:175:0x0352 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:177:0x035e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:179:0x036a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:181:0x0376 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:183:0x0382 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:185:0x038e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:187:0x039a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:189:0x03a6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:191:0x03b2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:193:0x03be */
        /* JADX WARNING: Missing exception handler attribute for start block: B:195:0x03ca */
        /* JADX WARNING: Missing exception handler attribute for start block: B:197:0x03d6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:199:0x03e2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:201:0x03ee */
        /* JADX WARNING: Missing exception handler attribute for start block: B:203:0x03fa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:205:0x0406 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:207:0x0412 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:209:0x041e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:211:0x042a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:213:0x0436 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:215:0x0442 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:217:0x044e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:219:0x045a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:221:0x0466 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:223:0x0472 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:225:0x047e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:227:0x048a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:229:0x0496 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:231:0x04a2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:233:0x04ae */
        /* JADX WARNING: Missing exception handler attribute for start block: B:235:0x04ba */
        /* JADX WARNING: Missing exception handler attribute for start block: B:237:0x04c6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:239:0x04d2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0053 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:241:0x04de */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x005d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0097 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00be */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00c8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00d2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x00fa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x0104 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x010e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x0118 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x0122 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:93:0x017a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:95:0x0184 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:97:0x018e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:99:0x0198 */
        static {
            /*
            // Method dump skipped, instructions count: 1259
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionServer.AnonymousClass7.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getLoggedInUserId(Context context, UserHandle userHandle) {
        try {
            return CompanionService.getUserIdFromHorizonLoggedInStatus(context, userHandle);
        } catch (InterruptedException e) {
            String str = TAG;
            Log.e(str, "Failed to get Horizon logged in userId for user " + userHandle, e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void persistKillSwitchToken() throws java.lang.SecurityException, java.io.IOException {
        /*
            r2 = this;
            r2 = 0
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x0026 }
            java.lang.String r1 = "/persist/anti_piracy/killswitch_token"
            r0.<init>(r1)     // Catch:{ all -> 0x0026 }
            boolean r1 = r0.exists()     // Catch:{ all -> 0x0026 }
            if (r1 == 0) goto L_0x0016
            java.lang.String r0 = com.oculus.companion.server.CompanionServer.TAG     // Catch:{ all -> 0x0026 }
            java.lang.String r1 = "Anti-Piracy KillSwitch file already exists"
            android.util.Log.w(r0, r1)     // Catch:{ all -> 0x0026 }
            return
        L_0x0016:
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0026 }
            r1.<init>(r0)     // Catch:{ all -> 0x0026 }
            r2 = 1
            r1.write(r2)     // Catch:{ all -> 0x0023 }
            r1.close()
            return
        L_0x0023:
            r0 = move-exception
            r2 = r1
            goto L_0x0027
        L_0x0026:
            r0 = move-exception
        L_0x0027:
            if (r2 == 0) goto L_0x002c
            r2.close()
        L_0x002c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionServer.persistKillSwitchToken():void");
    }

    private boolean checkIfDevModeCanBeEnabled() {
        File file = new File("/persist/anti_piracy/killswitch_token");
        if (!file.exists()) {
            return true;
        }
        Intent intent = new Intent();
        intent.setComponent(ANTI_PIRACY_SERVICE_COMPONENT);
        intent.setAction("get_backend_developer_status");
        Pair result = new BlockingResultReceiver(getApplicationContext(), (Handler) null, intent, "result_receiver").getResult(TIMEOUT_MILLIS);
        if (result == null || ((Integer) result.first).intValue() != 0 || ((Bundle) result.second).getBoolean("is_developer_mode_blocked", true)) {
            return false;
        }
        if (!file.delete()) {
            Log.w(TAG, "Failed to delete the Anti-Piracy KillSwitch File");
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void requestLineFrequency(String str) {
        if (DEBUG) {
            Log.d(TAG, "requestLineFrequency called");
        }
        new LineFrequencyServiceContract().startLineFrequencyService(getApplicationContext(), str);
    }

    class LineFrequencyChangeObserver implements SettingsObserverCallback {
        LineFrequencyChangeObserver() {
        }

        public void onSettingChange(String str) {
            if (CompanionServer.DEBUG) {
                String str2 = CompanionServer.TAG;
                Log.d(str2, "CompanionServer onSettingChange called for setting : " + str);
            }
            if ("electric_grid_line_frequency".equals(str)) {
                CompanionServer.this.requestLineFrequency("OC_LOCATION_USER_SET_LINE_FREQUENCY");
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.oculus.companion.server.CompanionServer$LocalBinder, android.os.IBinder] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onStartCommand(android.content.Intent r1, int r2, int r3) {
        /*
            r0 = this;
            java.lang.String r1 = "CompanionService"
            android.os.IBinder r2 = android.os.ServiceManager.checkService(r1)
            if (r2 != 0) goto L_0x0020
            boolean r2 = com.oculus.companion.server.CompanionServer.DEBUG
            if (r2 == 0) goto L_0x0013
            java.lang.String r2 = com.oculus.companion.server.CompanionServer.TAG
            java.lang.String r3 = "Starting ICompanionServer"
            android.util.Log.d(r2, r3)
        L_0x0013:
            com.oculus.companion.server.CompanionServer$LocalBinder r2 = new com.oculus.companion.server.CompanionServer$LocalBinder
            r2.<init>(r0)
            r0.csBinder = r2
            android.os.IBinder r0 = r0.csBinder
            android.os.ServiceManager.addService(r1, r0)
            goto L_0x002b
        L_0x0020:
            boolean r0 = com.oculus.companion.server.CompanionServer.DEBUG
            if (r0 == 0) goto L_0x002b
            java.lang.String r0 = com.oculus.companion.server.CompanionServer.TAG
            java.lang.String r1 = "ICompanionServer already running"
            android.util.Log.d(r0, r1)
        L_0x002b:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.CompanionServer.onStartCommand(android.content.Intent, int, int):int");
    }

    public IBinder onBind(Intent intent) {
        return this.csBinder;
    }

    public void onDestroy() {
        Log.e(TAG, "Companion Server onDestroy()");
        WifiModule wifiModule2 = this.wifiModule;
        if (wifiModule2 != null) {
            wifiModule2.destroy();
            this.wifiModule = null;
        }
        Telemetry telemetry2 = this.telemetry;
        if (telemetry2 != null) {
            telemetry2.destroy();
            this.telemetry = null;
        }
        BleModule bleModule2 = this.bleModule;
        if (bleModule2 != null) {
            bleModule2.destroy();
            this.bleModule = null;
        }
        BluetoothService bluetoothService2 = this.bluetoothService;
        if (bluetoothService2 != null) {
            bluetoothService2.destroy();
            this.bluetoothService = null;
        }
        LicenseManagementConnector licenseManagementConnector = this.mLicenseManagementConnector;
        if (licenseManagementConnector != null) {
            licenseManagementConnector.release();
            this.mLicenseManagementConnector = null;
        }
        this.csBinder = null;
    }

    public CompanionState getCompanionState() throws InterruptedException {
        synchronized (initLock) {
            while (this.companionState == null) {
                initLock.wait(1000);
            }
        }
        return this.companionState;
    }

    public WifiModule getWifiModule() {
        return this.wifiModule;
    }

    public BleModule getBleModule() {
        return this.bleModule;
    }

    public class LocalBinder extends ICompanionServer.Stub {
        private final Context context;
        private final TrustedCallerVerifier trustedCallerVerifier;

        public LocalBinder(Context context2) {
            this.context = context2;
            HashMultimap create = HashMultimap.create();
            create.put(SignatureHelper.OCULUS_PRIV_APPS_RELEASE_SIG, "com.oculus.alpenglow");
            create.put(SignatureHelper.OCULUS_CORE_APPS_RELEASE_SIGNATURE, "com.oculus.horizon");
            if (CompanionServer.IS_DEBUG_BUILD) {
                create.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.alpenglow");
                create.put(SignatureHelper.FBANDROID_FIRST_PARTY_DEBUG_SIG, "com.oculus.horizon");
            }
            this.trustedCallerVerifier = new TrustedCallerVerifier(create, context2, context2.getPackageManager());
        }

        public CompanionServer getService() {
            return CompanionServer.this;
        }

        public String getUserId() {
            if (CompanionServer.DEBUG) {
                Log.d(CompanionServer.TAG, "getUserId called");
            }
            CompanionServer companionServer = CompanionServer.this;
            return companionServer.getLoggedInUserId(companionServer.getApplicationContext(), Process.myUserHandle());
        }

        public void performRemoteWipe(IRemoteWipeCallback iRemoteWipeCallback) {
            if (CompanionServer.DEBUG) {
                Log.d(CompanionServer.TAG, "performRemoteWipe called");
            }
            this.trustedCallerVerifier.enforceTrustedCaller();
            try {
                ((StorageManager) this.context.getSystemService(StorageManager.class)).destroyUserKey(UserHandle.getCallingUserId());
                Log.w(CompanionServer.TAG, "wiped user encryption keys");
            } catch (Exception e) {
                try {
                    Log.e(CompanionServer.TAG, "failed to wipe encryption keys, reporting failure");
                    iRemoteWipeCallback.onFailure(e.getMessage());
                } catch (RemoteException e2) {
                    Log.e(CompanionServer.TAG, "failed to invoke success callback", e2);
                } catch (Throwable th) {
                    CompanionDeviceAdmin.wipeData(this.context);
                    Log.w(CompanionServer.TAG, "initiated factory reset");
                    throw th;
                }
            }
            iRemoteWipeCallback.onSuccess();
            Log.w(CompanionServer.TAG, "reported remote wipe success");
            CompanionDeviceAdmin.wipeData(this.context);
            Log.w(CompanionServer.TAG, "initiated factory reset");
        }

        public void claimDevice(String str) {
            this.trustedCallerVerifier.enforceTrustedCaller();
            CompanionServer.this.logEvent("claim_device", "Started", 3, true);
            if (CompanionServer.this.setUserSecret(CompanionUtil.hexToBytes(str), true, "claim_device")) {
                CompanionServer.this.dropConnection(DisconnectKey.ALL);
                CompanionServer.this.bleModule.setClaimed(true);
                return;
            }
            throw new IllegalStateException("Device already claimed");
        }

        public void performAntiPiracyKillSwitchAction() {
            if (!this.context.getPackageManager().hasSystemFeature("oculus.software.anti_piracy")) {
                Log.w(CompanionServer.TAG, "Anti-Piracy Feature is not supported");
                return;
            }
            try {
                CompanionServer.this.persistKillSwitchToken();
                CompanionService.setAdbEnabled(this.context, false);
            } catch (IOException | SecurityException e) {
                Log.e(CompanionServer.TAG, "Failed to perform Anti-Piracy Kill Switch Action", e);
            }
        }

        public boolean getLegacyPreOtaComplete() {
            SecureStorage secureStorage = new SecureStorage(this.context);
            if (secureStorage.hasKey("vr_ready")) {
                return secureStorage.getBooleanValue("vr_ready");
            }
            return false;
        }

        public String getLegacyNuxOtaState() {
            SecureStorage secureStorage = new SecureStorage(this.context);
            if (secureStorage.hasKey("nux_status")) {
                return secureStorage.getStringValue("nux_status");
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.write("------------------------ BEGIN SERVER STATE ------------------------\n");
            if (CompanionServer.this.companionState == null) {
                printWriter.write("companionState = null\n");
            } else {
                CompanionServer.this.companionState.dumpInfo(printWriter);
            }
            printWriter.write("------------------------- END SERVER STATE -------------------------\n");
        }
    }
}
