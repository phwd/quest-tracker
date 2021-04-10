package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import androidx.databinding.BaseObservable;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import java.util.function.Supplier;

public abstract class BaseSettingsItem extends BaseObservable {
    private boolean mCurrentVisibility = true;
    private final Gatekeeper[] mGatekeepers;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private boolean mShowInEnterprise;
    private Supplier<Boolean> mVisibilityFetcher;

    public static abstract class Builder {
        public abstract BaseSettingsItem build();
    }

    public BaseSettingsItem(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Gatekeeper[] gatekeeperArr, boolean z, Supplier<Boolean> supplier) {
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mGatekeepers = gatekeeperArr;
        this.mShowInEnterprise = z;
        this.mVisibilityFetcher = supplier;
    }

    private boolean passesGatekeepers() {
        for (Gatekeeper gatekeeper : this.mGatekeepers) {
            if (!this.mPanelApp.isGKEnabled(gatekeeper)) {
                return false;
            }
        }
        return true;
    }

    public boolean passesEnterprise() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode || this.mShowInEnterprise;
    }

    public boolean isVisible() {
        Supplier<Boolean> supplier = this.mVisibilityFetcher;
        return supplier != null ? supplier.get().booleanValue() && this.mCurrentVisibility && passesEnterprise() && passesGatekeepers() : this.mCurrentVisibility && passesEnterprise() && passesGatekeepers();
    }

    public void setCurrentVisibility(boolean z) {
        this.mCurrentVisibility = z;
    }
}
