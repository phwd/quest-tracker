package com.oculus.panelapp.anytimeui.config;

public abstract class SystemUXConfiguration {
    public final boolean areGuardianPillButtonsEnabled;
    public final boolean canDefaultApplicationBeRestarted;
    public final boolean hasAuiNavigateTab;
    public final boolean hasAuiNotificationsTab;
    public final boolean hasAuiPeopleTab;
    public final boolean hasAuiShareTab;
    public final boolean hasAuiSocialIcons;
    public final boolean isAuiTabButtonBarReconfigured;
    public final boolean isAuiTabButtonBarVisible;
    public final boolean isCameraRollEnabled;
    public final boolean isCastingEnabled;
    public final boolean isDefaultApplicationSet;
    public final boolean isEnterpriseAdminModeEnabled;
    public final boolean isEnterpriseHandTrackingEnabled;
    public final boolean isEnterpriseMode;
    public final boolean isLiveStreamingEnabled;
    public final boolean isWifiEditable;

    SystemUXConfiguration(Builder builder) {
        this.isEnterpriseMode = builder.isEnterpriseMode;
        this.hasAuiNavigateTab = builder.hasAuiNavigateTab;
        this.hasAuiSocialIcons = builder.hasAuiSocialIcons;
        this.hasAuiPeopleTab = builder.hasAuiPeopleTab;
        this.hasAuiShareTab = builder.hasAuiShareTab;
        this.isCastingEnabled = builder.isCastingEnabled;
        this.isLiveStreamingEnabled = builder.isLiveStreamingEnabled;
        this.isCameraRollEnabled = builder.isCameraRollEnabled;
        this.hasAuiNotificationsTab = builder.hasAuiNotificationsTab;
        this.isAuiTabButtonBarVisible = builder.isAuiTabButtonBarVisible;
        this.isWifiEditable = builder.isWifiEditable;
        this.isDefaultApplicationSet = builder.isDefaultApplicationSet;
        this.canDefaultApplicationBeRestarted = builder.canDefaultApplicationBeRestarted;
        this.isEnterpriseAdminModeEnabled = builder.isEnterpriseAdminModeEnabled;
        this.isEnterpriseHandTrackingEnabled = builder.isEnterpriseHandTrackingEnabled;
        this.areGuardianPillButtonsEnabled = builder.areGuardianPillButtonsEnabled;
        this.isAuiTabButtonBarReconfigured = builder.isAuiTabButtonBarReconfigured;
    }

    static Builder sysUxBuilder() {
        return new Builder();
    }

    static class Builder {
        private boolean areGuardianPillButtonsEnabled = true;
        private boolean canDefaultApplicationBeRestarted = true;
        private boolean hasAuiNavigateTab;
        private boolean hasAuiNotificationsTab;
        private boolean hasAuiPeopleTab;
        private boolean hasAuiShareTab;
        private boolean hasAuiSocialIcons;
        private boolean isAuiTabButtonBarReconfigured;
        private boolean isAuiTabButtonBarVisible = true;
        private boolean isCameraRollEnabled = true;
        private boolean isCastingEnabled;
        private boolean isDefaultApplicationSet;
        private boolean isEnterpriseAdminModeEnabled;
        private boolean isEnterpriseHandTrackingEnabled;
        private boolean isEnterpriseMode;
        private boolean isLiveStreamingEnabled = true;
        private boolean isWifiEditable;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public Builder setEnterpriseMode(boolean z) {
            this.isEnterpriseMode = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAuiNavigateTabVisible(boolean z) {
            this.hasAuiNavigateTab = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAuiSocialIconsVisible(boolean z) {
            this.hasAuiSocialIcons = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAuiPeopleTabVisible(boolean z) {
            this.hasAuiPeopleTab = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAuiShareTabVisible(boolean z) {
            this.hasAuiShareTab = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setCastingEnabled(boolean z) {
            this.isCastingEnabled = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setLiveStreamingEnabled(boolean z) {
            this.isLiveStreamingEnabled = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setCameraRollEnabled(boolean z) {
            this.isCameraRollEnabled = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAuiNotificationsTabVisible(boolean z) {
            this.hasAuiNotificationsTab = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAuiTabBarVisible(boolean z) {
            this.isAuiTabButtonBarVisible = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setWifiEditable(boolean z) {
            this.isWifiEditable = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setDefaultApplicationCanBeRestarted(boolean z) {
            this.canDefaultApplicationBeRestarted = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setDefaultApplicationSet(boolean z) {
            this.isDefaultApplicationSet = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setEnterpriseAdminModeEnabled(boolean z) {
            this.isEnterpriseAdminModeEnabled = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setEnterpriseHandTrackingEnabled(boolean z) {
            this.isEnterpriseHandTrackingEnabled = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setGuardianPillButtonsEnabled(boolean z) {
            this.areGuardianPillButtonsEnabled = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAuiTabButtonBarReconfigured(boolean z) {
            this.isAuiTabButtonBarReconfigured = z;
            return this;
        }
    }
}
