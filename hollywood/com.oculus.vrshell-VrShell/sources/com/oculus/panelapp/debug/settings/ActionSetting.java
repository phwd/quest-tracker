package com.oculus.panelapp.debug.settings;

import java.util.function.Consumer;

public class ActionSetting extends Setting {
    private final Runnable mCallback;

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void bind(Consumer<String> consumer) {
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void getSetting() {
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void unbind() {
    }

    public ActionSetting(String str, Runnable runnable) {
        super(new SettingInfo(str, SettingType.ACTION, null));
        this.mCallback = runnable;
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void setSetting(String str) {
        Runnable runnable = this.mCallback;
        if (runnable != null) {
            runnable.run();
        }
    }
}
