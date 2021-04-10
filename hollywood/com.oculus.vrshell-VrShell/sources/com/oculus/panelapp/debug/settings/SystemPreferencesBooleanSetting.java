package com.oculus.panelapp.debug.settings;

import android.util.Pair;
import com.oculus.os.PreferencesManager;

public class SystemPreferencesBooleanSetting extends SystemPreferencesSetting {
    public SystemPreferencesBooleanSetting(PreferencesManager preferencesManager, SettingInfo settingInfo, String str) {
        super(preferencesManager, settingInfo, str);
    }

    public SystemPreferencesBooleanSetting(PreferencesManager preferencesManager, String str, String str2) {
        super(preferencesManager, new SettingInfo(str2, SettingType.BOOLEAN, null), str);
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void getSetting() {
        if (this.mCallback != null) {
            Pair pair = this.mPreferencesManager.getBoolean(this.mSystemPreferenceKey);
            this.mCallback.accept((!((Boolean) pair.first).booleanValue() || !((Boolean) pair.second).booleanValue()) ? "false" : "true");
        }
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void setSetting(String str) {
        this.mPreferencesManager.set(this.mSystemPreferenceKey, Boolean.parseBoolean(str));
    }
}
