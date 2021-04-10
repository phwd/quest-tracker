package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.library.model.App;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;

public final class SettingsEnvironment extends BaseSettingsItem {
    private final App mEnvironment;

    private SettingsEnvironment(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, App app) {
        super(anytimeUIAndroidPanelAppV2, new Gatekeeper[0], false, null);
        this.mEnvironment = app;
    }

    public App getEnvironment() {
        return this.mEnvironment;
    }

    public static class Builder extends BaseSettingsItem.Builder {
        private App mEnvironment;
        private AnytimeUIAndroidPanelAppV2 mPanelApp;

        public Builder(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
            this.mPanelApp = anytimeUIAndroidPanelAppV2;
        }

        public Builder withEnvironment(App app) {
            this.mEnvironment = app;
            return this;
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem.Builder
        public BaseSettingsItem build() {
            return new SettingsEnvironment(this.mPanelApp, this.mEnvironment);
        }
    }
}
