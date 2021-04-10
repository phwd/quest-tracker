package com.oculus.companion.server;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.UserHandle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import com.oculus.companion.server.utils.LineFrequencyServiceContract;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class WifiModule {
    private static final String TAG = "WifiModule";
    static final long WIFI_COUNTRY_CODE_UPDATE_DELAY_MS = TimeUnit.SECONDS.toMillis(30);
    private static Thread connectionThread = null;
    private static NetworkInfo.DetailedState networkDetailedState = NetworkInfo.DetailedState.DISCONNECTED;
    private static NetworkInfo networkInfo = null;
    private static NetworkInfo.State networkState = NetworkInfo.State.UNKNOWN;
    private static boolean stateUpdated = false;
    private static int supplicantError = -1;
    private static SupplicantState supplicantState = SupplicantState.UNINITIALIZED;
    private static final Object wifiLock = new Object();
    private BroadcastReceiver broadcastReceiver = null;
    private Context context;
    private Handler countryCodeHandler = new Handler() {
        /* class com.oculus.companion.server.WifiModule.AnonymousClass3 */

        public void handleMessage(Message message) {
            if (CompanionUtil.isHollywoodDevice(WifiModule.this.context)) {
                int i = message.what;
                if (i == 1) {
                    String countryCode = WifiModule.this.wifiManager.getCountryCode();
                    if (countryCode == null) {
                        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_aloge");
                        analyticsEvent.setExtra("aloge_msg", "WifiManager returned null countryCode");
                        analyticsEvent.setExtra("log_tag", WifiModule.TAG);
                        UnifiedTelemetryLogger.getInstance(WifiModule.this.context).reportEvent(analyticsEvent, true);
                        Log.e(WifiModule.TAG, "WifiManager returned null countryCode");
                        WifiModule.this.countryCodeHandler.sendEmptyMessageDelayed(1, WifiModule.WIFI_COUNTRY_CODE_UPDATE_DELAY_MS);
                    }
                    OculusController.updateControllerTransmitPowerBoost(countryCode);
                } else if (i != 2) {
                    String str = WifiModule.TAG;
                    Log.e(str, "Country code handler: Invalid message received: " + message.what);
                } else {
                    removeMessages(1);
                    OculusController.updateControllerTransmitPowerBoost(null);
                }
            } else if (CompanionServer.DEBUG) {
                Log.d(WifiModule.TAG, "WiFi country code logic not supported on this device.");
            }
        }
    };
    private volatile boolean isOculusReachable = false;
    List<ScanResult> latestScanSSIDs;
    private LocalBroadcastManager localBroadcastManager = null;
    private WifiManager wifiManager = null;

    public enum WifiModuleState {
        WIFI_DISABLED,
        WIFI_BAD_PASSWORD,
        WIFI_UNSUPPORTED,
        WIFI_NO_CONFIGURED_NETWORK,
        WIFI_AUTH_FAILURE,
        WIFI_AUTH_TIMEOUT,
        WIFI_IP_CONFIG_FAILURE,
        WIFI_CONNECTED,
        WIFI_DISCONNECTED,
        WIFI_OCULUS_REACHABLE,
        WIFI_UNKNOWN
    }

    /* access modifiers changed from: package-private */
    public String wifiStateToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "WIFI_STATE_????" : "WIFI_STATE_UNKNOWN" : "WIFI_STATE_ENABLED" : "WIFI_STATE_ENABLING" : "WIFI_STATE_DISABLED" : "WIFI_STATE_DISABLING";
    }

    private static void interruptibleSleep(int i) throws InterruptedException {
        Thread.sleep((long) i);
    }

    private final BroadcastReceiver getWifiReceiver() {
        return new BroadcastReceiver() {
            /* class com.oculus.companion.server.WifiModule.AnonymousClass1 */

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            public void onReceive(Context context, Intent intent) {
                boolean z;
                String action = intent.getAction();
                if (CompanionServer.DEBUG) {
                    Log.d(WifiModule.TAG, "WifiModule Receiver: " + action);
                }
                switch (action.hashCode()) {
                    case -1172645946:
                        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                            z = true;
                            break;
                        }
                        z = true;
                        break;
                    case -343630553:
                        if (action.equals("android.net.wifi.STATE_CHANGE")) {
                            z = false;
                            break;
                        }
                        z = true;
                        break;
                    case 68995823:
                        if (action.equals("android.net.wifi.supplicant.CONNECTION_CHANGE")) {
                            z = true;
                            break;
                        }
                        z = true;
                        break;
                    case 233521600:
                        if (action.equals("android.net.wifi.supplicant.STATE_CHANGE")) {
                            z = true;
                            break;
                        }
                        z = true;
                        break;
                    case 1878357501:
                        if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                            z = true;
                            break;
                        }
                        z = true;
                        break;
                    default:
                        z = true;
                        break;
                }
                if (!z) {
                    synchronized (WifiModule.wifiLock) {
                        NetworkInfo unused = WifiModule.networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                        if (WifiModule.networkInfo != null) {
                            NetworkInfo.State unused2 = WifiModule.networkState = WifiModule.networkInfo.getState();
                            if (CompanionServer.DEBUG) {
                                Log.d(WifiModule.TAG, "Network State = " + WifiModule.networkState);
                            }
                            NetworkInfo.DetailedState unused3 = WifiModule.networkDetailedState = WifiModule.networkInfo.getDetailedState();
                            if (CompanionServer.DEBUG) {
                                Log.d(WifiModule.TAG, "DetailedState = " + WifiModule.networkDetailedState.toString());
                            }
                            if (CompanionServer.DEBUG) {
                                Log.d(WifiModule.TAG, "Extra info = " + WifiModule.networkInfo.getExtraInfo());
                            }
                            if (CompanionServer.DEBUG) {
                                Log.d(WifiModule.TAG, "Reason = " + WifiModule.networkInfo.getReason());
                            }
                            boolean unused4 = WifiModule.stateUpdated = true;
                            int i = AnonymousClass4.$SwitchMap$android$net$NetworkInfo$State[WifiModule.networkState.ordinal()];
                            if (i != 1) {
                                if (i == 2) {
                                    if (CompanionServer.DEBUG) {
                                        Log.v(WifiModule.TAG, "sendBroadcast: com.oculus.companion.server.WifiModule.WIFI_DISCONNECTED");
                                    }
                                    WifiModule.this.isOculusReachable = false;
                                    WifiModule.this.localBroadcastManager.sendBroadcast(new Intent("com.oculus.companion.server.WifiModule.WIFI_DISCONNECTED"));
                                } else if (i == 3) {
                                    if (CompanionServer.DEBUG) {
                                        Log.v(WifiModule.TAG, "sendBroadcast: com.oculus.companion.server.WifiModule.WIFI_CONNECTING");
                                    }
                                    WifiModule.this.localBroadcastManager.sendBroadcast(new Intent("com.oculus.companion.server.WifiModule.WIFI_CONNECTING"));
                                } else if (CompanionServer.DEBUG) {
                                    Log.v(WifiModule.TAG, "Skipping state update: " + WifiModule.networkState);
                                }
                            }
                        }
                    }
                } else if (z) {
                    boolean booleanExtra = intent.getBooleanExtra("resultsUpdated", false);
                    if (CompanionServer.DEBUG) {
                        Log.d(WifiModule.TAG, "Scan results updated?: " + booleanExtra);
                    }
                    if (CompanionServer.DEBUG) {
                        Log.v(WifiModule.TAG, "sendBroadcast: com.oculus.companion.server.WifiModule.WIFI_SCAN_RESULTS");
                    }
                    WifiModule.this.localBroadcastManager.sendBroadcast(new Intent("com.oculus.companion.server.WifiModule.WIFI_SCAN_RESULTS"));
                } else if (z) {
                    boolean booleanExtra2 = intent.getBooleanExtra("connected", false);
                    if (CompanionServer.DEBUG) {
                        Log.d(WifiModule.TAG, "SUPPLICANT_CONNECTION_CHANGE_ACTION: " + booleanExtra2);
                    }
                } else if (z) {
                    synchronized (WifiModule.wifiLock) {
                        SupplicantState unused5 = WifiModule.supplicantState = (SupplicantState) intent.getParcelableExtra("newState");
                        int unused6 = WifiModule.supplicantError = intent.getIntExtra("supplicantError", -1);
                        if (CompanionServer.DEBUG) {
                            Log.d(WifiModule.TAG, "SUPPLICANT_STATE_CHANGED_ACTION(State):" + WifiModule.supplicantState.toString());
                        }
                        if (CompanionServer.DEBUG) {
                            Log.d(WifiModule.TAG, "SUPPLICANT_STATE_CHANGED_ACTION(Error):" + WifiModule.supplicantError);
                        }
                        boolean unused7 = WifiModule.stateUpdated = true;
                        if (CompanionServer.DEBUG) {
                            Log.v(WifiModule.TAG, "sendBroadcast: com.oculus.companion.server.WifiModule.WIFI_CONNECTING");
                        }
                        WifiModule.this.localBroadcastManager.sendBroadcast(new Intent("com.oculus.companion.server.WifiModule.WIFI_CONNECTING"));
                    }
                } else if (!z) {
                    Log.w(WifiModule.TAG, "Unhandled action: " + action);
                } else {
                    if (CompanionServer.DEBUG) {
                        Log.v(WifiModule.TAG, "Got ConnectivityManager.CONNECTIVITY_ACTION intent");
                    }
                    NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                    if (networkInfo != null && networkInfo.getType() == 1) {
                        NetworkInfo unused8 = WifiModule.networkInfo = networkInfo;
                        NetworkInfo.State unused9 = WifiModule.networkState = WifiModule.networkInfo.getState();
                        NetworkInfo.DetailedState unused10 = WifiModule.networkDetailedState = WifiModule.networkInfo.getDetailedState();
                        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                        if (CompanionServer.DEBUG) {
                            Log.v(WifiModule.TAG, "ActiveNetworkInfo on intent: " + activeNetworkInfo);
                        }
                        if (WifiModule.networkState != NetworkInfo.State.CONNECTED || activeNetworkInfo == null) {
                            WifiModule.this.countryCodeHandler.sendEmptyMessage(2);
                            return;
                        }
                        WifiModule.this.localBroadcastManager.sendBroadcast(new Intent("com.oculus.companion.server.WifiModule.WIFI_CONNECTED"));
                        new LineFrequencyServiceContract().startLineFrequencyService(context, "OC_LOCATION_UPDATE_LINE_FREQUENCY");
                        WifiModule.this.countryCodeHandler.sendEmptyMessageDelayed(1, WifiModule.WIFI_COUNTRY_CODE_UPDATE_DELAY_MS);
                        Intent intent2 = new Intent();
                        intent2.setComponent(new ComponentName(context, BatteryCycleService.class.getCanonicalName()));
                        intent2.setAction("OC_BATTERY_PERSIST_BATTERY_CYCLE_COUNT");
                        context.startServiceAsUser(intent2, UserHandle.SYSTEM);
                    }
                }
            }
        };
    }

    public WifiModule initialize(Context context2) {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Initializing wifiModule");
        }
        BroadcastReceiver broadcastReceiver2 = this.broadcastReceiver;
        if (broadcastReceiver2 != null) {
            this.context.unregisterReceiver(broadcastReceiver2);
        }
        this.context = context2;
        if (this.wifiManager == null) {
            this.wifiManager = (WifiManager) context2.getSystemService(WifiManager.class);
            if (this.wifiManager == null) {
                Log.e(TAG, "Could not connect to Wifi service");
                throw new RuntimeException("WIFI Service not available");
            }
        }
        this.localBroadcastManager = LocalBroadcastManager.getInstance(context2);
        this.broadcastReceiver = getWifiReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.supplicant.CONNECTION_CHANGE");
        intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.context.registerReceiver(this.broadcastReceiver, intentFilter);
        return this;
    }

    public boolean isWifiEnabled() {
        return this.wifiManager.isWifiEnabled();
    }

    public boolean enableWifi() throws InterruptedException {
        long uptimeMillis = SystemClock.uptimeMillis() + 45000;
        while (!this.wifiManager.isWifiEnabled() && SystemClock.uptimeMillis() < uptimeMillis) {
            this.wifiManager.setWifiEnabled(true);
            interruptibleSleep(1000);
            if (CompanionServer.DEBUG) {
                String str = TAG;
                Log.d(str, "Wifi State: " + wifiStateToString(this.wifiManager.getWifiState()));
            }
        }
        return this.wifiManager.isWifiEnabled();
    }

    public void disableWifi() {
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "Disabling Wifi");
        }
        this.wifiManager.setWifiEnabled(false);
    }

    public void tryConnectToInternet(final int i) {
        if (!isConnectedToInternet()) {
            AnonymousClass2 r0 = new Runnable() {
                /* class com.oculus.companion.server.WifiModule.AnonymousClass2 */

                /* JADX WARNING: Removed duplicated region for block: B:55:0x010c A[Catch:{ SocketTimeoutException -> 0x0127, IOException -> 0x011a, NetworkOnMainThreadException -> 0x010d, Exception -> 0x0100, all -> 0x00fe }] */
                /* JADX WARNING: Removed duplicated region for block: B:59:0x0119 A[Catch:{ SocketTimeoutException -> 0x0127, IOException -> 0x011a, NetworkOnMainThreadException -> 0x010d, Exception -> 0x0100, all -> 0x00fe }] */
                /* JADX WARNING: Removed duplicated region for block: B:63:0x0126 A[Catch:{ SocketTimeoutException -> 0x0127, IOException -> 0x011a, NetworkOnMainThreadException -> 0x010d, Exception -> 0x0100, all -> 0x00fe }] */
                /* JADX WARNING: Removed duplicated region for block: B:70:0x013a  */
                /* JADX WARNING: Removed duplicated region for block: B:76:0x0135 A[SYNTHETIC] */
                /* JADX WARNING: Removed duplicated region for block: B:77:0x0135 A[SYNTHETIC] */
                /* JADX WARNING: Removed duplicated region for block: B:78:0x0135 A[SYNTHETIC] */
                /* JADX WARNING: Removed duplicated region for block: B:79:0x0135 A[SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                    // Method dump skipped, instructions count: 319
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.WifiModule.AnonymousClass2.run():void");
                }
            };
            synchronized (this) {
                try {
                    if (CompanionServer.DEBUG) {
                        Log.v(TAG, "checkConnectivityFunction - trying connection thread");
                    }
                    if (connectionThread == null || !connectionThread.isAlive()) {
                        if (CompanionServer.DEBUG) {
                            Log.v(TAG, "checkConnectivityFunction - connection thread dead");
                        }
                        connectionThread = new Thread(r0);
                        connectionThread.start();
                    }
                } catch (IllegalThreadStateException e) {
                    String str = TAG;
                    Log.e(str, "Exception caught when starting connection thread: " + e);
                }
            }
            if (CompanionServer.DEBUG) {
                Log.v(TAG, "checkConnectivityFunction - end");
            }
        }
    }

    public boolean reconnectToWifi(String str) throws InterruptedException {
        int i;
        List<WifiConfiguration> configuredNetworks = this.wifiManager.getConfiguredNetworks();
        if (configuredNetworks == null || str == null) {
            Log.e(TAG, "List of configured networks or specified network to reconnect to was null");
            return false;
        }
        Iterator<WifiConfiguration> it = configuredNetworks.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            WifiConfiguration next = it.next();
            if (stripQuotes(next.SSID).equals(str)) {
                i = next.networkId;
                break;
            }
        }
        if (i != -1) {
            resetConnectedToInternet();
            if (!this.wifiManager.enableNetwork(i, true) || !this.wifiManager.reconnect()) {
                return false;
            }
            if (CompanionServer.DEBUG) {
                Log.d(TAG, "Reconnecting to network " + str);
            }
            if (!isConnectedToInternet()) {
                NotifyingReceiver notifyingReceiver = new NotifyingReceiver();
                this.localBroadcastManager.registerReceiver(notifyingReceiver, new IntentFilter("com.oculus.companion.server.WifiModule.INTERNET_CONNECTED"));
                try {
                    synchronized (notifyingReceiver) {
                        tryConnectToInternet(2);
                        notifyingReceiver.wait(45000);
                    }
                } finally {
                    this.localBroadcastManager.unregisterReceiver(notifyingReceiver);
                }
            }
            return isConnectedToInternet();
        }
        Log.e(TAG, "Wifi ssid " + str + " for reconnect was not found");
        return false;
    }

    public boolean isConnectedToInternet() {
        return this.isOculusReachable;
    }

    public void resetConnectedToInternet() {
        this.isOculusReachable = false;
    }

    private boolean validatePassword(Protocol$WifiAuthentication protocol$WifiAuthentication, String str) {
        if (str == null) {
            return protocol$WifiAuthentication == Protocol$WifiAuthentication.NONE;
        }
        int length = str.length();
        if (protocol$WifiAuthentication == Protocol$WifiAuthentication.WPA) {
            return length >= 8 && length <= 63;
        }
        if (protocol$WifiAuthentication != Protocol$WifiAuthentication.WEP) {
            return true;
        }
        if (length == 5 || length == 13) {
            return str.matches("\\A\\p{ASCII}*\\z");
        }
        if (length == 10 || length == 26) {
            return str.matches("[0-9a-fA-F]+");
        }
        if (CompanionServer.DEBUG) {
            Log.d(TAG, "bad length");
        }
        return false;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x02c0, code lost:
        r9 = r17;
        r11 = 10000;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.oculus.companion.server.WifiModule.WifiModuleState setNewWifiConf(java.lang.String r22, java.lang.String r23, java.lang.String r24, com.oculus.companion.server.Protocol$WifiAuthentication r25, boolean r26) throws java.lang.InterruptedException {
        /*
        // Method dump skipped, instructions count: 854
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.WifiModule.setNewWifiConf(java.lang.String, java.lang.String, java.lang.String, com.oculus.companion.server.Protocol$WifiAuthentication, boolean):com.oculus.companion.server.WifiModule$WifiModuleState");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.companion.server.WifiModule$4  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$android$net$NetworkInfo$State = new int[NetworkInfo.State.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$companion$server$Protocol$WifiAuthentication = new int[Protocol$WifiAuthentication.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|5|6|7|9|10|(2:11|12)|13|15|16|17|18|19|20|22) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|9|10|11|12|13|15|16|17|18|19|20|22) */
        /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0052 */
        static {
            /*
                com.oculus.companion.server.Protocol$WifiAuthentication[] r0 = com.oculus.companion.server.Protocol$WifiAuthentication.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.companion.server.WifiModule.AnonymousClass4.$SwitchMap$com$oculus$companion$server$Protocol$WifiAuthentication = r0
                r0 = 1
                int[] r1 = com.oculus.companion.server.WifiModule.AnonymousClass4.$SwitchMap$com$oculus$companion$server$Protocol$WifiAuthentication     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.companion.server.Protocol$WifiAuthentication r2 = com.oculus.companion.server.Protocol$WifiAuthentication.NONE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = com.oculus.companion.server.WifiModule.AnonymousClass4.$SwitchMap$com$oculus$companion$server$Protocol$WifiAuthentication     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.companion.server.Protocol$WifiAuthentication r3 = com.oculus.companion.server.Protocol$WifiAuthentication.WEP     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = com.oculus.companion.server.WifiModule.AnonymousClass4.$SwitchMap$com$oculus$companion$server$Protocol$WifiAuthentication     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.companion.server.Protocol$WifiAuthentication r4 = com.oculus.companion.server.Protocol$WifiAuthentication.WPA     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r3 = com.oculus.companion.server.WifiModule.AnonymousClass4.$SwitchMap$com$oculus$companion$server$Protocol$WifiAuthentication     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.companion.server.Protocol$WifiAuthentication r4 = com.oculus.companion.server.Protocol$WifiAuthentication.EAP     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                android.net.NetworkInfo$State[] r3 = android.net.NetworkInfo.State.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                com.oculus.companion.server.WifiModule.AnonymousClass4.$SwitchMap$android$net$NetworkInfo$State = r3
                int[] r3 = com.oculus.companion.server.WifiModule.AnonymousClass4.$SwitchMap$android$net$NetworkInfo$State     // Catch:{ NoSuchFieldError -> 0x0048 }
                android.net.NetworkInfo$State r4 = android.net.NetworkInfo.State.CONNECTED     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                int[] r0 = com.oculus.companion.server.WifiModule.AnonymousClass4.$SwitchMap$android$net$NetworkInfo$State     // Catch:{ NoSuchFieldError -> 0x0052 }
                android.net.NetworkInfo$State r3 = android.net.NetworkInfo.State.DISCONNECTED     // Catch:{ NoSuchFieldError -> 0x0052 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0052 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0052 }
            L_0x0052:
                int[] r0 = com.oculus.companion.server.WifiModule.AnonymousClass4.$SwitchMap$android$net$NetworkInfo$State     // Catch:{ NoSuchFieldError -> 0x005c }
                android.net.NetworkInfo$State r1 = android.net.NetworkInfo.State.CONNECTING     // Catch:{ NoSuchFieldError -> 0x005c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.WifiModule.AnonymousClass4.<clinit>():void");
        }
    }

    private boolean isActiveNetwork(String str) {
        String str2 = "";
        if (Build.VERSION.SDK_INT > 25) {
            WifiInfo connectionInfo = ((WifiManager) this.context.getSystemService("wifi")).getConnectionInfo();
            if (!(connectionInfo == null || connectionInfo.getSSID() == null)) {
                str2 = connectionInfo.getSSID();
            }
        } else {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                str2 = stripQuotes(activeNetworkInfo.getExtraInfo());
            }
        }
        if (TextUtils.isEmpty(str2) || !TextUtils.equals(str2, str)) {
            return false;
        }
        return true;
    }

    /* JADX INFO: finally extract failed */
    public List<ScanResult> ScanSSIDs() throws InterruptedException {
        if (!enableWifi()) {
            return Collections.emptyList();
        }
        IntentFilter intentFilter = new IntentFilter("com.oculus.companion.server.WifiModule.WIFI_SCAN_RESULTS");
        NotifyingReceiver notifyingReceiver = new NotifyingReceiver();
        this.localBroadcastManager.registerReceiver(notifyingReceiver, intentFilter);
        try {
            synchronized (notifyingReceiver) {
                if (this.wifiManager.startScan()) {
                    notifyingReceiver.wait(10000);
                }
            }
            this.localBroadcastManager.unregisterReceiver(notifyingReceiver);
            List<ScanResult> scanResults = this.wifiManager.getScanResults();
            if (scanResults == null) {
                return Collections.emptyList();
            }
            ListIterator<ScanResult> listIterator = scanResults.listIterator();
            while (listIterator.hasNext()) {
                if (!isSsidOk(listIterator.next().SSID)) {
                    listIterator.remove();
                }
            }
            this.latestScanSSIDs = scanResults;
            return scanResults;
        } catch (Throwable th) {
            this.localBroadcastManager.unregisterReceiver(notifyingReceiver);
            throw th;
        }
    }

    public List<WifiConfiguration> getConfiguredNetworks() {
        List<WifiConfiguration> configuredNetworks = this.wifiManager.getConfiguredNetworks();
        if (configuredNetworks == null) {
            return Collections.emptyList();
        }
        ListIterator<WifiConfiguration> listIterator = configuredNetworks.listIterator();
        while (listIterator.hasNext()) {
            if (!isSsidOk(listIterator.next().SSID)) {
                listIterator.remove();
            }
        }
        return configuredNetworks;
    }

    public WifiInfo getConnectionInfo() {
        WifiInfo connectionInfo = this.wifiManager.getConnectionInfo();
        if (connectionInfo == null || !isSsidOk(connectionInfo.getSSID())) {
            return null;
        }
        return connectionInfo;
    }

    public String getIpAddress() {
        WifiInfo connectionInfo = getConnectionInfo();
        return connectionInfo != null ? Formatter.formatIpAddress(connectionInfo.getIpAddress()) : "";
    }

    public String getMacAddress() {
        WifiInfo connectionInfo = this.wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        return connectionInfo.getMacAddress();
    }

    public boolean forgetNetwork(String str) {
        int i;
        List<WifiConfiguration> configuredNetworks = this.wifiManager.getConfiguredNetworks();
        if (configuredNetworks == null) {
            return false;
        }
        Iterator<WifiConfiguration> it = configuredNetworks.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            WifiConfiguration next = it.next();
            if (stripQuotes(next.SSID).equals(stripQuotes(str))) {
                String str2 = TAG;
                Log.i(str2, "Removing network " + str);
                i = next.networkId;
                break;
            }
        }
        return this.wifiManager.removeNetwork(i);
    }

    public static String stripQuotes(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.replaceAll("^\"|\"$", "");
        }
        return "";
    }

    private boolean isSsidOk(String str) {
        return str != null && !str.isEmpty() && !str.equals("<unknown ssid>");
    }

    public void destroy() {
        BroadcastReceiver broadcastReceiver2 = this.broadcastReceiver;
        if (broadcastReceiver2 != null) {
            this.context.unregisterReceiver(broadcastReceiver2);
            this.broadcastReceiver = null;
        }
    }

    public LocalBroadcastManager getLbm() {
        return this.localBroadcastManager;
    }

    /* access modifiers changed from: package-private */
    public static final class NotifyingReceiver extends BroadcastReceiver {
        NotifyingReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            synchronized (this) {
                notify();
            }
        }
    }
}
