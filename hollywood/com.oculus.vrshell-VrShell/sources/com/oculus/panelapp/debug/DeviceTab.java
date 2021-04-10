package com.oculus.panelapp.debug;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.PreferencesManager;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.vrshell.util.AndroidSystemProperties;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public final class DeviceTab extends LinearLayout implements DebugTabHost.DebugTab {
    private static final InfoItem[] BASIC_INFO = {new InfoItem(Category.BUILD, "Build.BOARD", Build.BOARD), new InfoItem(Category.BUILD, "Build.BOOTLOADER", Build.BOOTLOADER), new InfoItem(Category.BUILD, "Build.BRANCH", AndroidSystemProperties.getSystemPropertyString("ro.build.branch", "UNKNOWN")), new InfoItem(Category.BUILD, "Build.BRAND", Build.BRAND), new InfoItem(Category.BUILD, "Build.DATE", AndroidSystemProperties.getSystemPropertyString("ro.build.date", "UNKNOWN")), new InfoItem(Category.BUILD, "Build.DEVICE", Build.DEVICE), new InfoItem(Category.BUILD, "Build.DISPLAY", Build.DISPLAY), new InfoItem(Category.BUILD, "Build.FINGERPRINT", Build.FINGERPRINT), new InfoItem(Category.BUILD, "Build.HARDWARE", Build.HARDWARE), new InfoItem(Category.BUILD, "Build.MANUFACTURER", Build.MANUFACTURER), new InfoItem(Category.BUILD, "Build.MODEL", Build.MODEL), new InfoItem(Category.BUILD, "Build.PRODUCT", Build.PRODUCT), new InfoItem(Category.BUILD, "Build.SERIAL", Build.SERIAL), new InfoItem(Category.BUILD, "Build.TYPE", Build.TYPE)};
    private static final String[] PACKAGE_VERSIONS = {"com.oculus.horizon", "com.oculus.ocms", "com.oculus.vrshell", "com.oculus.shellenv", "com.oculus.vrshell.home", "com.oculus.socialplatform", "com.oculus.systemdriver", "com.oculus.systemutilities", "com.oculus.browser", "com.oculus.tv", "com.oculus.systemux"};
    private static final String PAIRING_CODE_PREF_KEY = "pairing_code";
    private static final String TAG = LoggingUtil.tag(DeviceTab.class);
    private List<InfoItem> mItems;
    private ViewGroup mListItemHost;

    /* access modifiers changed from: private */
    public enum Category {
        SETTINGS,
        BUILD,
        APP_VERSIONS,
        NETWORK
    }

    public DeviceTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        debugTabHost.addTab("deviceinfo", "Device Info", R.id.debug_tab_device);
        this.mListItemHost = (ViewGroup) findViewById(R.id.list_item_host);
        this.mItems = new ArrayList();
        refresh();
    }

    private void refresh() {
        new Thread(new Runnable() {
            /* class com.oculus.panelapp.debug.DeviceTab.AnonymousClass1 */

            public void run() {
                ArrayList arrayList = new ArrayList();
                SettingsManager settingsManager = new SettingsManager();
                arrayList.add(new InfoItem(Category.SETTINGS, "Analytics Device ID", settingsManager.getString("marauder_device_id", "<unknown>")));
                arrayList.add(new InfoItem(Category.SETTINGS, "Analytics State", settingsManager.getString("telemetry_state", "<unknown>")));
                arrayList.add(new InfoItem(Category.SETTINGS, "Hands (opt-in)", Boolean.toString(settingsManager.getBoolean("hand_tracking_opt_in", false))));
                arrayList.add(new InfoItem(Category.SETTINGS, "Hands (enabled)", Boolean.toString(settingsManager.getBoolean("hand_tracking_enabled", false))));
                arrayList.add(new InfoItem(Category.SETTINGS, "Crash Reports Enabled", Boolean.toString(settingsManager.getBoolean("crash_reports_enabled", false))));
                arrayList.add(new InfoItem(Category.SETTINGS, "Pairing Code", DeviceTab.this.getPairingCode()));
                arrayList.addAll(Arrays.asList(DeviceTab.BASIC_INFO));
                String[] strArr = DeviceTab.PACKAGE_VERSIONS;
                for (String str : strArr) {
                    DeviceTab deviceTab = DeviceTab.this;
                    deviceTab.addAppVersionInfo(deviceTab.getContext().getPackageManager(), str, arrayList);
                }
                DeviceTab.this.addNetworkInfo(arrayList);
                Collections.sort(arrayList);
                DeviceTab.this.mItems.clear();
                DeviceTab.this.mItems.addAll(arrayList);
                DeviceTab.this.injectItems();
            }
        }).start();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void injectItems() {
        ThreadUtil.runOnUiThread(getContext(), new Runnable() {
            /* class com.oculus.panelapp.debug.DeviceTab.AnonymousClass2 */

            public void run() {
                DeviceTab.this.mListItemHost.removeAllViews();
                for (int i = 0; i < DeviceTab.this.mItems.size(); i++) {
                    InfoItem infoItem = (InfoItem) DeviceTab.this.mItems.get(i);
                    if (i == 0 || infoItem.category != ((InfoItem) DeviceTab.this.mItems.get(i - 1)).category) {
                        DeviceTab.this.injectHeader(infoItem.category.name());
                    }
                    View inflate = View.inflate(DeviceTab.this.getContext(), R.layout.device_info_item, null);
                    TextView textView = (TextView) inflate.findViewById(R.id.device_info_item_header);
                    ((TextView) inflate.findViewById(R.id.device_info_item_label)).setText(infoItem.label);
                    ((TextView) inflate.findViewById(R.id.device_info_item_value)).setText(infoItem.value);
                    DeviceTab.this.mListItemHost.addView(inflate);
                }
                DeviceTab.this.invalidate();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void injectHeader(String str) {
        View inflate = inflate(getContext(), R.layout.device_info_item, null);
        TextView textView = (TextView) inflate.findViewById(R.id.device_info_item_header);
        textView.setVisibility(0);
        ((TextView) inflate.findViewById(R.id.device_info_item_label)).setVisibility(8);
        ((TextView) inflate.findViewById(R.id.device_info_item_value)).setVisibility(8);
        textView.setText(str);
        this.mListItemHost.addView(inflate);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAppVersionInfo(PackageManager packageManager, String str, List<InfoItem> list) {
        String str2;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            str2 = String.format("%s (%s)", packageInfo.versionName, Integer.valueOf(packageInfo.versionCode));
        } catch (PackageManager.NameNotFoundException unused) {
            str2 = "<unknown>";
        }
        list.add(new InfoItem(Category.APP_VERSIONS, str, str2));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addNetworkInfo(List<InfoItem> list) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement2 = inetAddresses.nextElement();
                    if (!nextElement2.isLoopbackAddress()) {
                        String canonicalHostName = nextElement2.getCanonicalHostName();
                        int indexOf = canonicalHostName.indexOf("%");
                        if (indexOf != -1) {
                            canonicalHostName = canonicalHostName.substring(0, indexOf);
                        }
                        list.add(new InfoItem(Category.NETWORK, nextElement.getDisplayName(), canonicalHostName));
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getPairingCode() {
        PreferencesManager preferencesManager = new PreferencesManager();
        return ((Boolean) preferencesManager.getString(PAIRING_CODE_PREF_KEY).first).booleanValue() ? (String) preferencesManager.getString(PAIRING_CODE_PREF_KEY).second : "";
    }

    /* access modifiers changed from: private */
    public static class InfoItem implements Comparable<InfoItem> {
        public final Category category;
        public final String label;
        public final String value;

        InfoItem(Category category2, String str, String str2) {
            this.category = category2;
            this.label = str;
            this.value = str2;
        }

        public int compareTo(@NonNull InfoItem infoItem) {
            Category category2 = this.category;
            Category category3 = infoItem.category;
            if (category2 == category3) {
                return this.label.compareTo(infoItem.label);
            }
            return category2.compareTo((Enum) category3);
        }
    }
}
