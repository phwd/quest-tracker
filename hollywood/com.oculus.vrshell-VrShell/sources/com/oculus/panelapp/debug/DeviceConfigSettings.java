package com.oculus.panelapp.debug;

import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.deviceconfigclient.DebugOnlyValueInfo;
import com.oculus.deviceconfigclient.DeviceConfigClient;
import java.util.HashMap;

/* access modifiers changed from: package-private */
public class DeviceConfigSettings {
    public static final String NONE = "<NONE>";
    private static final String TAG = LoggingUtil.tag(DeviceConfigSettings.class);
    private static DeviceConfigClient mDeviceConfigClient;

    public enum SettingType {
        BOOLEAN,
        STRING,
        LONG,
        DOUBLE,
        SECTION_HEADER,
        INVALID
    }

    DeviceConfigSettings() {
    }

    public static class Setting {
        private Object mCachedValue = null;
        private String mConfigName;
        private Object mOverriddenValue = null;
        private String mParamName;
        private Object mServiceValue = null;
        private SettingType mType;

        Setting(String str, SettingType settingType) {
            String[] parseConfigParamName = parseConfigParamName(str);
            this.mConfigName = parseConfigParamName[0];
            this.mParamName = parseConfigParamName[1];
            this.mType = settingType;
            validate();
        }

        Setting(String str, String str2, SettingType settingType) {
            this.mConfigName = str;
            this.mParamName = str2;
            this.mType = settingType;
            validate();
        }

        public void setCachedValue(@Nullable Object obj) {
            this.mCachedValue = obj;
        }

        public void setServiceValue(@Nullable Object obj) {
            this.mServiceValue = obj;
        }

        public void setOverriddenValue(@Nullable Object obj) {
            Object obj2 = this.mOverriddenValue;
            if (obj2 != null && obj2.equals(obj)) {
                return;
            }
            if (this.mOverriddenValue != null || obj != null) {
                this.mOverriddenValue = obj;
            }
        }

        public void setAndSyncOverriddenValue(@Nullable Object obj) {
            setOverriddenValue(obj);
            syncOverriddenValue();
        }

        public void resetAndSyncOverriddenValue() {
            setAndSyncOverriddenValue(null);
        }

        public boolean isOverridden() {
            return this.mOverriddenValue != null;
        }

        public void setOverriddenValueByStringInput(String str) {
            setAndSyncOverriddenValue(DeviceConfigSettings.parseValueForSetting(this, str));
        }

        public String getConfigName() {
            return this.mConfigName;
        }

        public String getParamName() {
            return this.mParamName;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private String getConfigParamName() {
            return this.mConfigName + ":" + this.mParamName;
        }

        public String getLabel() {
            return getConfigParamName();
        }

        public String getParamLabel() {
            return this.mParamName;
        }

        public SettingType getType() {
            return this.mType;
        }

        public Object getCurrentValue() {
            Object obj = this.mCachedValue;
            return obj != null ? obj : this.mServiceValue;
        }

        public Object getOverriddenValue() {
            return this.mOverriddenValue;
        }

        public String getServiceValueLabel() {
            return getValueLabel(this.mServiceValue);
        }

        public String getCurrentValueLabel() {
            Object obj = this.mOverriddenValue;
            if (obj != null) {
                return getValueLabel(obj);
            }
            Object obj2 = this.mCachedValue;
            if (obj2 != null) {
                return getValueLabel(obj2);
            }
            return "From MC: " + getValueLabel(this.mServiceValue);
        }

        private void validate() {
            if (this.mConfigName.isEmpty() || (this.mParamName.isEmpty() && this.mType != SettingType.SECTION_HEADER)) {
                Log.e(DeviceConfigSettings.TAG, "build: DeviceConfig parameter name cannot be empty string");
                throw new IllegalArgumentException();
            } else if (this.mType == SettingType.INVALID) {
                Log.e(DeviceConfigSettings.TAG, "build: DeviceConfig parameter type is invalid");
                throw new IllegalArgumentException();
            }
        }

        private String[] parseConfigParamName(String str) {
            String[] split = str.split(":", 2);
            if (split.length == 2) {
                return split;
            }
            throw new IllegalStateException();
        }

        private String getValueLabel(Object obj) {
            if (obj == null) {
                return DeviceConfigSettings.NONE;
            }
            return obj.toString().isEmpty() ? "(Empty string)" : obj.toString();
        }

        private void syncOverriddenValue() {
            DeviceConfigSettings.syncOverriddenValues(new Setting[]{this});
        }
    }

    public static void setDeviceConfigClient(DeviceConfigClient deviceConfigClient) {
        mDeviceConfigClient = deviceConfigClient;
    }

    public static DeviceConfigClient getDeviceConfigClient() {
        return mDeviceConfigClient;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.debug.DeviceConfigSettings$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType = new int[SettingType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.oculus.panelapp.debug.DeviceConfigSettings$SettingType[] r0 = com.oculus.panelapp.debug.DeviceConfigSettings.SettingType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.debug.DeviceConfigSettings.AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType = r0
                int[] r0 = com.oculus.panelapp.debug.DeviceConfigSettings.AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.debug.DeviceConfigSettings$SettingType r1 = com.oculus.panelapp.debug.DeviceConfigSettings.SettingType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.debug.DeviceConfigSettings.AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.debug.DeviceConfigSettings$SettingType r1 = com.oculus.panelapp.debug.DeviceConfigSettings.SettingType.LONG     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.debug.DeviceConfigSettings.AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.panelapp.debug.DeviceConfigSettings$SettingType r1 = com.oculus.panelapp.debug.DeviceConfigSettings.SettingType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.panelapp.debug.DeviceConfigSettings.AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.panelapp.debug.DeviceConfigSettings$SettingType r1 = com.oculus.panelapp.debug.DeviceConfigSettings.SettingType.STRING     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.debug.DeviceConfigSettings.AnonymousClass1.<clinit>():void");
        }
    }

    public static Object parseValueForSetting(Setting setting, String str) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType[setting.getType().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return str;
                    }
                    Log.e(TAG, "parseValueForSetting: Cannot parse value for invalid setting");
                    throw new IllegalArgumentException();
                } else if (str.isEmpty()) {
                    return Double.valueOf(0.0d);
                } else {
                    return Double.valueOf(Double.parseDouble(str));
                }
            } else if (str.isEmpty()) {
                return 0L;
            } else {
                return Long.valueOf(Long.parseLong(str));
            }
        } else if (str.isEmpty()) {
            return false;
        } else {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        }
    }

    public static void resetAndSyncOverriddenValues(Setting[] settingArr) {
        for (Setting setting : settingArr) {
            if (setting.isOverridden()) {
                setting.setOverriddenValue(null);
            }
        }
        syncOverriddenValues(settingArr);
    }

    /* access modifiers changed from: private */
    public static void syncOverriddenValues(Setting[] settingArr) {
        HashMap hashMap = new HashMap();
        for (Setting setting : settingArr) {
            String configParamName = setting.getConfigParamName();
            SettingType type = setting.getType();
            Object overriddenValue = setting.getOverriddenValue();
            int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType[type.ordinal()];
            int i2 = 4;
            if (i == 1) {
                i2 = 1;
            } else if (i == 2) {
                i2 = 2;
            } else if (i == 3) {
                continue;
            } else if (i == 4) {
                i2 = 3;
            } else {
                Log.e(TAG, "resetOverriddenValues: Invalid DeviceConfig setting type: " + type.toString());
                throw new IllegalArgumentException();
            }
            hashMap.put(configParamName, DebugOnlyValueInfo.Builder.createDebugOnlyValueInfo().setConfigParamName(configParamName).setOverriddenValue(overriddenValue).setValueType(i2).build());
            setting.setOverriddenValue(overriddenValue);
        }
        if (!hashMap.isEmpty()) {
            mDeviceConfigClient.debugOnlySetOverriddenParams(hashMap);
        }
    }
}
