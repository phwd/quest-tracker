package com.oculus.panelapp.anytimeui.config;

public final class ConsumerConfiguration extends SystemUXConfiguration {
    private ConsumerConfiguration(Builder builder) {
        super(SystemUXConfiguration.sysUxBuilder().setEnterpriseMode(false).setAuiNavigateTabVisible(true).setAuiSocialIconsVisible(builder.auiSocialIconsEnabled).setAuiPeopleTabVisible(true).setAuiShareTabVisible(true).setCastingEnabled(true).setAuiNotificationsTabVisible(true).setWifiEditable(true).setGuardianPillButtonsEnabled(builder.areGuardianPillButtonsEnabled));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private boolean areGuardianPillButtonsEnabled;
        private boolean auiSocialIconsEnabled;

        public Builder setAuiSocialIconsEnabled(boolean z) {
            this.auiSocialIconsEnabled = z;
            return this;
        }

        public Builder setGuardianPillButtonsEnabled(boolean z) {
            this.areGuardianPillButtonsEnabled = z;
            return this;
        }

        public ConsumerConfiguration build() {
            return new ConsumerConfiguration(this);
        }
    }
}
