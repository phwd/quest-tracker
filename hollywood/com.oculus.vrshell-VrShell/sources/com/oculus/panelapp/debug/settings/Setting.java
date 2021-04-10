package com.oculus.panelapp.debug.settings;

import java.util.List;
import java.util.function.Consumer;

public abstract class Setting {
    protected final SettingInfo mInfo;

    public abstract void bind(Consumer<String> consumer);

    public abstract void getSetting();

    public abstract void setSetting(String str);

    public abstract void unbind();

    public Setting(SettingInfo settingInfo) {
        this.mInfo = settingInfo;
    }

    public String getName() {
        return this.mInfo.name;
    }

    public SettingType getType() {
        return this.mInfo.type;
    }

    public List<String> getPresets() {
        return this.mInfo.presets;
    }
}
