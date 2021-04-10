package com.oculus.oculustestsettings;

public interface OculusTestSettings {

    public enum SettingsKey {
        FAIL_MNUX_UPDATE
    }

    boolean A39(SettingsKey settingsKey);
}
