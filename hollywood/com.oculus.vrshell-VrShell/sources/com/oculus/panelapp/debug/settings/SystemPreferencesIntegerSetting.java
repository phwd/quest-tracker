package com.oculus.panelapp.debug.settings;

import android.util.Pair;
import com.oculus.os.PreferencesManager;
import java.util.List;

public class SystemPreferencesIntegerSetting extends SystemPreferencesSetting {
    public SystemPreferencesIntegerSetting(PreferencesManager preferencesManager, SettingInfo settingInfo, String str) {
        super(preferencesManager, settingInfo, str);
    }

    public SystemPreferencesIntegerSetting(PreferencesManager preferencesManager, String str, String str2, List<String> list) {
        super(preferencesManager, new SettingInfo(str2, SettingType.INTEGER, list), str);
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void getSetting() {
        if (this.mCallback != null) {
            Pair integer = this.mPreferencesManager.getInteger(this.mSystemPreferenceKey);
            this.mCallback.accept(((Boolean) integer.first).booleanValue() ? ((Integer) integer.second).toString() : "0");
        }
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void setSetting(String str) {
        this.mPreferencesManager.set(this.mSystemPreferenceKey, Integer.parseInt(str));
    }
}
