package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import java.util.ArrayList;
import java.util.List;

public class SettingsList {
    private List<BaseSettingsItem> mSettingsItems = new ArrayList();

    public void addSettingsItem(BaseSettingsItem.Builder builder) {
        this.mSettingsItems.add(builder.build());
    }

    public List<BaseSettingsItem> getSettingsItems() {
        return this.mSettingsItems;
    }

    public void clear() {
        this.mSettingsItems.clear();
    }
}
