package com.oculus.panelapp.debug.settings;

import com.oculus.os.PreferencesManager;
import java.util.function.Consumer;

public abstract class SystemPreferencesSetting extends Setting {
    protected Consumer<String> mCallback;
    protected final PreferencesManager mPreferencesManager;
    protected final String mSystemPreferenceKey;

    public SystemPreferencesSetting(PreferencesManager preferencesManager, SettingInfo settingInfo, String str) {
        super(settingInfo);
        this.mPreferencesManager = preferencesManager;
        this.mSystemPreferenceKey = str;
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void bind(Consumer<String> consumer) {
        this.mCallback = consumer;
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void unbind() {
        this.mCallback = null;
    }
}
