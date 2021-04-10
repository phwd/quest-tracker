package com.oculus.os.enterprise;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public interface Configuration {
    default int getDefaultModeIndex() {
        return 0;
    }

    @Nullable
    default String getOwnerName() {
        return null;
    }

    default boolean getShowUnknownSources() {
        return false;
    }

    default String toMarshalledString() throws UnsupportedEncodingException {
        return "";
    }

    default Date getLastFetchTime() {
        return new Date(0);
    }

    default Date getTimestamp() {
        return new Date(0);
    }

    default Mode[] getModes() {
        return new Mode[]{new Mode() {
            /* class com.oculus.os.enterprise.Configuration.AnonymousClass1 */
        }, new Mode() {
            /* class com.oculus.os.enterprise.Configuration.AnonymousClass2 */
        }};
    }

    default Date getLicenseExpirationDate() {
        return new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
    }

    default ControllerMode getControllerMode() {
        return ControllerMode.GAZE_MODE_DISABLED;
    }

    default GuardianMode getGuardianMode() {
        return GuardianMode.ENABLED;
    }

    default String[] getDynamicConfig() {
        return new String[0];
    }

    default HandTracking getHandTracking() {
        return HandTracking.DISABLED;
    }
}
