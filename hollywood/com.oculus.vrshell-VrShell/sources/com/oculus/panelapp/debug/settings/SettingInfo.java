package com.oculus.panelapp.debug.settings;

import java.util.List;

public class SettingInfo {
    public String name;
    public List<String> presets;
    public SettingType type;

    public SettingInfo() {
    }

    public SettingInfo(String str, SettingType settingType, List<String> list) {
        this.name = str;
        this.type = settingType;
        this.presets = list;
    }
}
