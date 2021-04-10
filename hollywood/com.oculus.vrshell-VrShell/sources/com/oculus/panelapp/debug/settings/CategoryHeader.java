package com.oculus.panelapp.debug.settings;

import java.util.function.Consumer;

public class CategoryHeader extends Setting {
    @Override // com.oculus.panelapp.debug.settings.Setting
    public void bind(Consumer<String> consumer) {
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void getSetting() {
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void setSetting(String str) {
    }

    @Override // com.oculus.panelapp.debug.settings.Setting
    public void unbind() {
    }

    public CategoryHeader(String str) {
        super(new SettingInfo(str, SettingType.CATEGORY_HEADER, null));
    }
}
