package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import androidx.annotation.StringRes;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;
import java.util.function.Supplier;

public class SettingsDescriptiveText extends BaseSettingsItem {
    private final String mBody;
    private final String mBodyUri;
    private final SettingsButtonActionType mButton;
    private final String mHeader;
    private final String mName;
    private boolean mShowInEnterprise;

    public SettingsDescriptiveText(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, String str, String str2, String str3, String str4, Gatekeeper[] gatekeeperArr, boolean z, Supplier<Boolean> supplier, SettingsButtonActionType settingsButtonActionType) {
        super(anytimeUIAndroidPanelAppV2, gatekeeperArr, z, supplier);
        this.mHeader = str;
        this.mBody = str2;
        this.mBodyUri = str3;
        this.mButton = settingsButtonActionType;
        this.mName = str4;
    }

    public String getHeader() {
        String str = this.mHeader;
        return str != null ? str : "";
    }

    public String getBody() {
        String str = this.mBody;
        return str != null ? str : "";
    }

    public String getBodyUri() {
        return this.mBodyUri;
    }

    public boolean isHeaderVisible() {
        return this.mHeader != null;
    }

    public boolean isBodyVisible() {
        return this.mBody != null;
    }

    public boolean isButtonVisible() {
        return this.mButton != null;
    }

    public String getButtonText() {
        if (isButtonVisible()) {
            return this.mButton.getTitle();
        }
        return null;
    }

    public boolean isButtonEnabled() {
        return isButtonVisible() && this.mButton.getEnabled();
    }

    public void clickButton(SettingsLogger settingsLogger) {
        SettingsButtonActionType settingsButtonActionType = this.mButton;
        if (settingsButtonActionType != null) {
            settingsButtonActionType.onClick();
            settingsLogger.logButtonClick(this.mName);
        }
    }

    public static class Builder extends BaseSettingsItem.Builder {
        private String mBody;
        private String mBodyUri;
        private SettingsButtonActionType mButton;
        private Context mContext;
        private Gatekeeper[] mGatekeepers = new Gatekeeper[0];
        private String mHeader;
        private String mName = "";
        private AnytimeUIAndroidPanelAppV2 mPanelApp;
        private boolean mShowInEnterprise = false;
        private Supplier<Boolean> mVisibilityFetcher;

        public Builder(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
            this.mContext = context;
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

        public Builder withHeader(@StringRes int i) {
            this.mHeader = this.mContext.getResources().getString(i);
            return this;
        }

        public Builder withHeader(String str) {
            this.mHeader = str;
            return this;
        }

        public Builder withBody(@StringRes int i) {
            this.mBody = this.mContext.getResources().getString(i);
            return this;
        }

        public Builder withBody(String str) {
            this.mBody = str;
            return this;
        }

        public Builder withBodyUri(String str) {
            this.mBodyUri = str;
            return this;
        }

        public Builder withButton(SettingsButtonActionType.Builder builder) {
            this.mButton = builder.build();
            return this;
        }

        public Builder withSettingName(String str) {
            this.mName = str;
            return this;
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem.Builder
        public BaseSettingsItem build() {
            return new SettingsDescriptiveText(this.mPanelApp, this.mHeader, this.mBody, this.mBodyUri, this.mName, this.mGatekeepers, this.mShowInEnterprise, this.mVisibilityFetcher, this.mButton);
        }
    }
}
