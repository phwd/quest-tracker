package com.oculus.panelapp.anytimeui.v2.tablet.settings.util;

import com.oculus.os.SettingsManager;

public final class DoNotDisturbUtil {
    private static final boolean DO_NOT_DISTURB_DEFAULT_VALUE = false;

    public static boolean getDoNotDisturbEnabled() {
        return new SettingsManager().getBoolean(SettingsManager.DO_NOT_DISTURB, false);
    }

    public static void setDoNotDisturbEnabled(boolean z) {
        new SettingsManager().setBoolean(SettingsManager.DO_NOT_DISTURB, z);
    }
}
