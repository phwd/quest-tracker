package com.oculus.panelapp.anytimeui.v2.bar.status;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationSender;
import com.oculus.os.MolokiniParams;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.tablet.view.ViewModelLifecycle;
import com.oculus.vrshell.panels.InputFrame;
import com.oculus.vrshell.sharedprefs.PrefKey;
import com.oculus.vrshell.sharedprefs.SharedKeys;
import com.oculus.vrshell.sharedprefs.SharedPreferencesHelper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class StatusViewModel extends BaseObservable implements ViewModelLifecycle {
    private static final String ANTE_MERIDIEM_SUFFIX = " AM";
    private static final long BATTERY_UPDATE_INTERVAL_MILLIS = 1500;
    private static final PrefKey IS_EXTENDED_BATTERY_CONNECTED = SharedKeys.FLAGS.extend("IS_EXTENDED_BATTERY_CONNECTED");
    private static final int NUM_WIFI_LEVELS = 3;
    private static final String POST_MERIDIEM_SUFFIX = " PM";
    private static final String TAG = LoggingUtil.tag(StatusViewModel.class);
    private static final long WIFI_UPDATE_INTERVAL_MILLIS = 1000;
    private BroadcastReceiver mBatteryBroadcastReceiver;
    private int mBatteryLevel;
    private int mBatteryScale;
    private int mCombinedBatteryLevel = 0;
    private int mCombinedBatteryScale = 100;
    private String mConnectedNetworkSSID;
    private ConnectivityManager mConnectivityManager;
    private Context mContext;
    private Date mCurrentDate = null;
    private Set<DateChangeListener> mDateChangeListeners = new HashSet();
    private MolokiniParams mExtendedBattery;
    private int mExtendedBatteryLevel = 0;
    private int mExtendedBatteryScale = 100;
    private MolokiniParams.ChargingStatus mExtendedBatteryStatus = MolokiniParams.ChargingStatus.UNKNOWN;
    private boolean mIsDeviceBatterySaverOn;
    private boolean mIsDeviceCharging;
    private boolean mIsExtendedBatteryConnected = false;
    private long mLastDeviceBatteryUpdateTimeMillis;
    private long mLastWifiUpdateTimeMillis;
    private boolean mNetworkConnectedWithInternet;
    private PowerManager mPowerManager;
    private boolean mShouldUpdateBatteryUI = false;
    private boolean mShouldUpdateWifiUI = false;
    private int mSignalLevelThreeScale = 0;
    private String mTime;
    private DateFormat mTimeFormat;
    private BroadcastReceiver mTimeFormatUpdateBroadcastReceiver;
    private BroadcastReceiver mTimeTickUpdateBroadcastReceiver;
    private WifiManager mWifiManager;
    private WifiState mWifiState;
    private Set<WifiStateListener> mWifiStateListeners = new HashSet();

    public interface DateChangeListener {
        void onDateChanged();
    }

    public interface WifiStateListener {
        void onWifiStateChanged();
    }

    public StatusViewModel(Context context) {
        Log.d(TAG, "Constructing ViewModel");
        this.mContext = context;
        this.mConnectivityManager = (ConnectivityManager) this.mContext.getApplicationContext().getSystemService("connectivity");
        this.mWifiManager = (WifiManager) this.mContext.getApplicationContext().getSystemService("wifi");
        this.mPowerManager = (PowerManager) this.mContext.getSystemService("power");
        this.mExtendedBattery = new MolokiniParams(this.mContext);
        initializeTimeUpdateBroadcastReceiver();
        initializeBatteryBroadcastReceiver();
        updateTimeFormat();
        updateDateTime();
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        Log.d(TAG, "Destroying ViewModel");
        this.mContext.unregisterReceiver(this.mBatteryBroadcastReceiver);
        this.mContext.unregisterReceiver(this.mTimeTickUpdateBroadcastReceiver);
        this.mContext.unregisterReceiver(this.mTimeFormatUpdateBroadcastReceiver);
    }

    private void updateWifiStatus() {
        ConnectivityManager connectivityManager;
        String str;
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager == null || !wifiManager.isWifiEnabled() || (connectivityManager = this.mConnectivityManager) == null) {
            setNewWifiStatus(WifiState.OFF, null, 0, false);
            return;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
            setNewWifiStatus(WifiState.NOT_CONNECTED, null, 0, false);
            return;
        }
        WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            str = null;
        } else {
            str = connectionInfo.getSSID();
        }
        if (str == null) {
            setNewWifiStatus(WifiState.NOT_CONNECTED, null, WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 3), false);
        } else {
            setNewWifiStatus(WifiState.CONNECTED, str.substring(1, str.length() - 1), WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 3), isNetworkOnline());
        }
    }

    public void updateWifi(InputFrame inputFrame) {
        long timeMillis = inputFrame.getTimeMillis();
        if (timeMillis - this.mLastWifiUpdateTimeMillis > WIFI_UPDATE_INTERVAL_MILLIS) {
            this.mLastWifiUpdateTimeMillis = timeMillis;
            updateWifiStatus();
        }
    }

    public String getConnectedNetworkSSID() {
        return this.mConnectedNetworkSSID;
    }

    public void setConnectedNetworkSSID(String str) {
        this.mConnectedNetworkSSID = str;
    }

    public void setWifiState(WifiState wifiState) {
        this.mWifiState = wifiState;
        notifyPropertyChanged(BR.wifiButtonCTA);
        notifyPropertyChanged(BR.wifiButtonIcon);
        notifyWifiStateListeners();
    }

    public WifiState getWifiState() {
        return this.mWifiState;
    }

    public boolean isNetworkConnectedWithInternet() {
        return this.mNetworkConnectedWithInternet;
    }

    private boolean isNetworkOnline() {
        NetworkCapabilities networkCapabilities;
        Network activeNetwork = this.mConnectivityManager.getActiveNetwork();
        if (activeNetwork == null || (networkCapabilities = this.mConnectivityManager.getNetworkCapabilities(activeNetwork)) == null || !networkCapabilities.hasCapability(16)) {
            return false;
        }
        return true;
    }

    private void notifyWifiStateListeners() {
        for (WifiStateListener wifiStateListener : this.mWifiStateListeners) {
            wifiStateListener.onWifiStateChanged();
        }
    }

    public void addWifiStateListener(WifiStateListener wifiStateListener) {
        this.mWifiStateListeners.add(wifiStateListener);
    }

    public void removeWifiStateListener(WifiStateListener wifiStateListener) {
        this.mWifiStateListeners.remove(wifiStateListener);
    }

    @Bindable
    public Drawable getWifiButtonIcon() {
        Resources resources = this.mContext.getResources();
        if (getWifiState() == WifiState.OFF) {
            return resources.getDrawable(R.drawable.oc_icon_wifi_off_filled_24_d2d2d2, null);
        }
        return resources.getDrawable(R.drawable.anytime_tablet_settings_icon_wifi_on_v2, null);
    }

    /* renamed from: com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$bar$status$WifiState = new int[WifiState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.panelapp.anytimeui.v2.bar.status.WifiState[] r0 = com.oculus.panelapp.anytimeui.v2.bar.status.WifiState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel.AnonymousClass4.$SwitchMap$com$oculus$panelapp$anytimeui$v2$bar$status$WifiState = r0
                int[] r0 = com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel.AnonymousClass4.$SwitchMap$com$oculus$panelapp$anytimeui$v2$bar$status$WifiState     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.anytimeui.v2.bar.status.WifiState r1 = com.oculus.panelapp.anytimeui.v2.bar.status.WifiState.CONNECTED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel.AnonymousClass4.$SwitchMap$com$oculus$panelapp$anytimeui$v2$bar$status$WifiState     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.anytimeui.v2.bar.status.WifiState r1 = com.oculus.panelapp.anytimeui.v2.bar.status.WifiState.NOT_CONNECTED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel.AnonymousClass4.$SwitchMap$com$oculus$panelapp$anytimeui$v2$bar$status$WifiState     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.panelapp.anytimeui.v2.bar.status.WifiState r1 = com.oculus.panelapp.anytimeui.v2.bar.status.WifiState.OFF     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel.AnonymousClass4.<clinit>():void");
        }
    }

    @Bindable
    public String getWifiButtonCTA() {
        Resources resources = this.mContext.getResources();
        int i = AnonymousClass4.$SwitchMap$com$oculus$panelapp$anytimeui$v2$bar$status$WifiState[getWifiState().ordinal()];
        if (i == 1) {
            String str = this.mConnectedNetworkSSID;
            if (str != null) {
                return str;
            }
            Log.e(TAG, "Connected network SSID should not be null");
            return "";
        } else if (i == 2) {
            return resources.getString(R.string.anytime_tablet_settings_wifi_button_cta_not_connected);
        } else {
            if (i == 3) {
                return resources.getString(R.string.anytime_tablet_settings_wifi_button_cta_off);
            }
            Log.e(TAG, "Unknown wifi state");
            return "";
        }
    }

    private void setNewWifiStatus(WifiState wifiState, String str, int i, boolean z) {
        if (getWifiState() != wifiState) {
            this.mShouldUpdateWifiUI = true;
            setWifiState(wifiState);
        }
        if ((str == null && getConnectedNetworkSSID() != null) || (str != null && (getConnectedNetworkSSID() == null || !str.equals(getConnectedNetworkSSID())))) {
            this.mShouldUpdateWifiUI = true;
            setConnectedNetworkSSID(str);
        }
        if (this.mSignalLevelThreeScale != i) {
            this.mShouldUpdateWifiUI = true;
            this.mSignalLevelThreeScale = i;
        }
        if (this.mNetworkConnectedWithInternet != z) {
            this.mShouldUpdateWifiUI = true;
            this.mNetworkConnectedWithInternet = z;
        }
    }

    private void initializeBatteryBroadcastReceiver() {
        this.mBatteryBroadcastReceiver = new BroadcastReceiver() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel.AnonymousClass1 */

            public void onReceive(Context context, Intent intent) {
                int intExtra = intent.getIntExtra("level", -1);
                int intExtra2 = intent.getIntExtra("scale", -1);
                boolean z = false;
                if (intent.getIntExtra("plugged", 0) != 0) {
                    z = true;
                }
                if (StatusViewModel.this.mBatteryLevel != intExtra) {
                    StatusViewModel.this.mShouldUpdateBatteryUI = true;
                    StatusViewModel.this.mBatteryLevel = intExtra;
                }
                if (StatusViewModel.this.mBatteryScale != intExtra2) {
                    StatusViewModel.this.mShouldUpdateBatteryUI = true;
                    StatusViewModel.this.mBatteryScale = intExtra2;
                }
                if (StatusViewModel.this.mIsDeviceCharging != z) {
                    StatusViewModel.this.mShouldUpdateBatteryUI = true;
                    StatusViewModel.this.mIsDeviceCharging = z;
                }
            }
        };
        this.mContext.registerReceiver(this.mBatteryBroadcastReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    public void updateDeviceBattery(InputFrame inputFrame) {
        long timeMillis = inputFrame.getTimeMillis();
        if (timeMillis - this.mLastDeviceBatteryUpdateTimeMillis > BATTERY_UPDATE_INTERVAL_MILLIS) {
            this.mLastDeviceBatteryUpdateTimeMillis = timeMillis;
            if (this.mIsDeviceBatterySaverOn != this.mPowerManager.isPowerSaveMode()) {
                this.mShouldUpdateBatteryUI = true;
                this.mIsDeviceBatterySaverOn = this.mPowerManager.isPowerSaveMode();
            }
            updateExtendedBattery();
        }
    }

    private void updateExtendedBattery() {
        this.mIsExtendedBatteryConnected = this.mExtendedBattery.getConnectionStatus();
        boolean loadExtendedBatteryPreviousConnectionState = loadExtendedBatteryPreviousConnectionState();
        if (this.mIsExtendedBatteryConnected != loadExtendedBatteryPreviousConnectionState) {
            this.mShouldUpdateBatteryUI = true;
            saveExtendedBatteryPreviousConnectionState();
        }
        boolean z = this.mIsExtendedBatteryConnected && !loadExtendedBatteryPreviousConnectionState;
        if (this.mIsExtendedBatteryConnected) {
            setNewExtendedBatteryStatus(this.mExtendedBattery.getBatteryLevel(), this.mExtendedBattery.getCombinedBatteryLevel(), this.mExtendedBattery.getChargingStatus());
        } else {
            setNewExtendedBatteryStatus(0, 0, MolokiniParams.ChargingStatus.UNKNOWN);
        }
        if (z) {
            NotificationSender.build("oculus_mobile_system_ux_extended_battery_notification", this.mContext.getResources().getString(R.string.anytime_extended_battery_dialog_connected_notification_title_v2), this.mContext.getResources().getString(R.string.anytime_extended_battery_dialog_connected_notification_text_v2), R.drawable.oc_icon_molokini_filled_24_a5a5a5).setIsPersistent(true).send(this.mContext);
        }
    }

    private void setNewExtendedBatteryStatus(int i, int i2, MolokiniParams.ChargingStatus chargingStatus) {
        if (i2 == -1) {
            i2 = this.mBatteryLevel;
        }
        if (this.mExtendedBatteryLevel != i) {
            this.mShouldUpdateBatteryUI = true;
            this.mExtendedBatteryLevel = i;
        }
        if (this.mCombinedBatteryLevel != i2) {
            this.mShouldUpdateBatteryUI = true;
            this.mCombinedBatteryLevel = i2;
        }
        if (this.mExtendedBatteryStatus != chargingStatus) {
            this.mShouldUpdateBatteryUI = true;
            this.mExtendedBatteryStatus = chargingStatus;
        }
    }

    private void initializeTimeUpdateBroadcastReceiver() {
        this.mTimeTickUpdateBroadcastReceiver = new BroadcastReceiver() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel.AnonymousClass2 */

            public void onReceive(Context context, Intent intent) {
                StatusViewModel.this.updateDateTime();
            }
        };
        this.mContext.registerReceiver(this.mTimeTickUpdateBroadcastReceiver, new IntentFilter("android.intent.action.TIME_TICK"));
        this.mTimeFormatUpdateBroadcastReceiver = new BroadcastReceiver() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel.AnonymousClass3 */

            public void onReceive(Context context, Intent intent) {
                StatusViewModel.this.updateTimeFormat();
                StatusViewModel.this.updateDateTime();
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        this.mContext.registerReceiver(this.mTimeFormatUpdateBroadcastReceiver, intentFilter);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateTimeFormat() {
        Locale locale = this.mContext.getResources().getConfiguration().locale;
        this.mTimeFormat = new SimpleDateFormat(android.text.format.DateFormat.getBestDateTimePattern(locale, android.text.format.DateFormat.is24HourFormat(this.mContext) ? "Hm" : "hm"), locale);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateDateTime() {
        DateFormat dateFormat = this.mTimeFormat;
        if (dateFormat != null) {
            this.mTime = formatDisplayTime(dateFormat.format(Long.valueOf(System.currentTimeMillis())));
            notifyPropertyChanged(BR.time);
            Date date = new Date();
            Date date2 = this.mCurrentDate;
            if (date2 == null || date2.getDay() != date.getDay() || this.mCurrentDate.getMonth() != date.getMonth() || this.mCurrentDate.getYear() != date.getYear()) {
                this.mCurrentDate = date;
                notifyDateChangeListeners();
            }
        }
    }

    public Date getCurrentDate() {
        return this.mCurrentDate;
    }

    @VisibleForTesting
    public static String formatDisplayTime(String str) {
        if (str.endsWith(ANTE_MERIDIEM_SUFFIX)) {
            return str.substring(0, str.length() - 3);
        }
        return str.endsWith(POST_MERIDIEM_SUFFIX) ? str.substring(0, str.length() - 3) : str;
    }

    public int getSignalLevelThreeScale() {
        return this.mSignalLevelThreeScale;
    }

    public boolean isDeviceBatteryCharging() {
        if (this.mIsExtendedBatteryConnected) {
            return this.mExtendedBattery.isChargerPlugged();
        }
        return this.mIsDeviceCharging;
    }

    public int getBatteryLevel() {
        return this.mBatteryLevel;
    }

    public int getBatteryScale() {
        return this.mBatteryScale;
    }

    public boolean isDeviceBatterySaverOn() {
        return this.mIsDeviceBatterySaverOn;
    }

    public boolean isExtendedBatteryConnected() {
        return this.mIsExtendedBatteryConnected;
    }

    public int getCombinedBatteryLevel() {
        return this.mCombinedBatteryLevel;
    }

    public int getCombinedBatteryScale() {
        return this.mCombinedBatteryScale;
    }

    public int getExtendedBatteryLevel() {
        return this.mExtendedBatteryLevel;
    }

    public int getExtendedBatteryScale() {
        return this.mExtendedBatteryScale;
    }

    public MolokiniParams.ChargingStatus getExtendedBatteryStatus() {
        return this.mExtendedBatteryStatus;
    }

    @Bindable
    public String getTime() {
        return this.mTime;
    }

    public boolean shouldUpdateWifiUI() {
        if (!this.mShouldUpdateWifiUI) {
            return false;
        }
        this.mShouldUpdateWifiUI = false;
        return true;
    }

    public boolean shouldUpdateBatteryUI() {
        if (!this.mShouldUpdateBatteryUI) {
            return false;
        }
        this.mShouldUpdateBatteryUI = false;
        return true;
    }

    private void saveExtendedBatteryPreviousConnectionState() {
        SharedPreferencesHelper.putBoolean(this.mContext, IS_EXTENDED_BATTERY_CONNECTED, this.mIsExtendedBatteryConnected);
    }

    private boolean loadExtendedBatteryPreviousConnectionState() {
        return SharedPreferencesHelper.getBoolean(this.mContext, IS_EXTENDED_BATTERY_CONNECTED, false);
    }

    public void addDateChangeListener(DateChangeListener dateChangeListener) {
        this.mDateChangeListeners.add(dateChangeListener);
    }

    public void removeDateChangeListener(DateChangeListener dateChangeListener) {
        this.mDateChangeListeners.remove(dateChangeListener);
    }

    private void notifyDateChangeListeners() {
        for (DateChangeListener dateChangeListener : this.mDateChangeListeners) {
            dateChangeListener.onDateChanged();
        }
    }
}
