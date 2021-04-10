package com.oculus.panelapp.debug.settings;

import android.util.Pair;
import com.oculus.os.PreferencesManager;
import java.util.List;

public class SystemPreferencesFloatSetting extends SystemPreferencesSetting {
    public SystemPreferencesFloatSetting(PreferencesManager preferencesManager, SettingInfo settingInfo, String str) {
        super(preferencesManager, settingInfo, str);
    }

    public SystemPreferencesFloatSetting(PreferencesManager preferencesManager, String str, String str2, List<String> list) {
        super(preferencesManager, new SettingInfo(str2, SettingType.FLOAT, list), str);
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void getSetting() {
        if (this.mCallback != null) {
            Pair pair = this.mPreferencesManager.getFloat(this.mSystemPreferenceKey);
            this.mCallback.accept(((Boolean) pair.first).booleanValue() ? ((Float) pair.second).toString() : "0.0");
        }
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void setSetting(String str) {
        this.mPreferencesManager.set(this.mSystemPreferenceKey, Float.parseFloat(str));
    }
}
