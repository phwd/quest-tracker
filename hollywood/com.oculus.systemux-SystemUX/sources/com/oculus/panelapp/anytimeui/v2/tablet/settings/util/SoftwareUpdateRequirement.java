package com.oculus.panelapp.anytimeui.v2.tablet.settings.util;

import androidx.annotation.StringRes;
import com.oculus.panelapp.anytimeui.R;

public enum SoftwareUpdateRequirement {
    BATTERY_PLUGGED_IN("settings_os_update_device_unplugged", R.string.settings_about_section_software_update_unplugged_title, R.string.settings_about_section_software_update_unplugged_body),
    INSUFFICIENT_STORAGE("settings_os_update_device_insufficient_storage", R.string.settings_about_section_software_update_insufficient_storage_title, R.string.settings_about_section_software_update_insufficient_storage_body),
    INSUFFICIENT_BATTERY("settings_os_update_device_battery_insufficient", R.string.settings_about_section_software_update_low_battery_title, R.string.settings_about_section_software_update_low_battery_body);
    
    @StringRes
    private final int mBody;
    private final String mDialogId;
    @StringRes
    private final int mTitle;

    private SoftwareUpdateRequirement(String str, @StringRes int i, @StringRes int i2) {
        this.mDialogId = str;
        this.mTitle = i;
        this.mBody = i2;
    }

    public String getDialogId() {
        return this.mDialogId;
    }

    public int getTitle() {
        return this.mTitle;
    }

    public int getBody() {
        return this.mBody;
    }
}
