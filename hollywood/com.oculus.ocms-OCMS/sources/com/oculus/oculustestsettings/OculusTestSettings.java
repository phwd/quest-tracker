package com.oculus.oculustestsettings;

import java.util.List;

public interface OculusTestSettings {

    public enum SettingsKey {
        FAIL_MNUX_UPDATE
    }

    List<String> getAllTestSettings();

    boolean getBooleanTestSetting(SettingsKey settingsKey);

    void setBooleanTestSetting(SettingsKey settingsKey, boolean z);
}
