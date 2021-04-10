package com.oculus.os.enterprise;

import java.util.Optional;

public interface Mode {
    default String getDefaultApplication() {
        return "";
    }

    default String getDisplayName() {
        return "Default";
    }

    default String[] getApplications() {
        return new String[0];
    }

    default AUICapability[] getAuiCapabilities() {
        return new AUICapability[0];
    }

    default HomeButtonBehaviour getHomeButtonBehaviour() {
        return HomeButtonBehaviour.UNRESTRICTED;
    }

    default Optional<String> getPin() {
        return Optional.empty();
    }
}
