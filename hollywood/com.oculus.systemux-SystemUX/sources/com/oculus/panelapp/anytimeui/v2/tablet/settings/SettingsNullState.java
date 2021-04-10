package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import androidx.annotation.StringRes;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import java.util.function.Supplier;

public final class SettingsNullState extends BaseSettingsItem {
    private final String mSubtitle;
    private final String mTitle;

    private SettingsNullState(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Gatekeeper[] gatekeeperArr, boolean z, Supplier<Boolean> supplier, String str, String str2) {
        super(anytimeUIAndroidPanelAppV2, gatekeeperArr, z, supplier);
        this.mTitle = str;
        this.mSubtitle = str2;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public static class Builder extends BaseSettingsItem.Builder {
        private final Context mContext;
        private Gatekeeper[] mGatekeepers = new Gatekeeper[0];
        private final AnytimeUIAndroidPanelAppV2 mPanelApp;
        private boolean mShowInEnterprise = false;
        private String mSubtitle;
        private String mTitle;
        private Supplier<Boolean> mVisibilityFetcher;

        public Builder(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
            this.mContext = context;
            this.mPanelApp = anytimeUIAndroidPanelAppV2;
        }

        public Builder withTitle(@StringRes int i) {
            this.mTitle = this.mContext.getResources().getString(i);
            return this;
        }

        public Builder withSubtitle(@StringRes int i) {
            this.mSubtitle = this.mContext.getResources().getString(i);
            return this;
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

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem.Builder
        public BaseSettingsItem build() {
            return new SettingsNullState(this.mPanelApp, this.mGatekeepers, this.mShowInEnterprise, this.mVisibilityFetcher, this.mTitle, this.mSubtitle);
        }
    }
}
