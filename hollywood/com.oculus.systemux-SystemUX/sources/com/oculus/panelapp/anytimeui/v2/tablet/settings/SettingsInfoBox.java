package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import androidx.annotation.StringRes;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import java.util.function.Supplier;

public final class SettingsInfoBox extends BaseSettingsItem {
    private final int mInfo;

    private SettingsInfoBox(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Gatekeeper[] gatekeeperArr, boolean z, Supplier<Boolean> supplier, @StringRes int i) {
        super(anytimeUIAndroidPanelAppV2, gatekeeperArr, z, supplier);
        this.mInfo = i;
    }

    public int getInfo() {
        return this.mInfo;
    }

    public static class Builder extends BaseSettingsItem.Builder {
        private Gatekeeper[] mGatekeepers = new Gatekeeper[0];
        @StringRes
        private int mInfo;
        private AnytimeUIAndroidPanelAppV2 mPanelApp;
        private boolean mShowInEnterprise = false;
        private Supplier<Boolean> mVisibilityFetcher;

        public Builder(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
            this.mPanelApp = anytimeUIAndroidPanelAppV2;
        }

        public Builder withGatekeepers(Gatekeeper... gatekeeperArr) {
            this.mGatekeepers = gatekeeperArr;
            return this;
        }

        public Builder withShowInEnterprise(boolean z) {
            this.mShowInEnterprise = z;
            return this;
        }

        public Builder visibilityFetcher(Supplier<Boolean> supplier) {
            this.mVisibilityFetcher = supplier;
            return this;
        }

        public Builder withInfo(@StringRes int i) {
            this.mInfo = i;
            return this;
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem.Builder
        public SettingsInfoBox build() {
            return new SettingsInfoBox(this.mPanelApp, this.mGatekeepers, this.mShowInEnterprise, this.mVisibilityFetcher, this.mInfo);
        }
    }
}
