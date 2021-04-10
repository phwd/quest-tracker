package com.oculus.os.enterprise;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public interface Configuration {

    /* renamed from: com.oculus.os.enterprise.Configuration$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static int $default$getDefaultModeIndex(Configuration configuration) {
            return 0;
        }

        public static String[] $default$getDynamicConfig(Configuration configuration) {
            return new String[0];
        }

        @Nullable
        public static String $default$getOwnerName(Configuration configuration) {
            return null;
        }

        public static boolean $default$getShowUnknownSources(Configuration configuration) {
            return false;
        }

        public static String $default$toMarshalledString(Configuration configuration) throws UnsupportedEncodingException {
            return "";
        }

        public static Date $default$getLastFetchTime(Configuration configuration) {
            return new Date(0);
        }

        public static Date $default$getLicenseExpirationDate(Configuration configuration) {
            return new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
        }

        public static Mode[] $default$getModes(Configuration configuration) {
            return new Mode[]{new Mode() {
                /* class com.oculus.os.enterprise.Configuration.AnonymousClass1 */

                @Override // com.oculus.os.enterprise.Mode
                public /* synthetic */ String[] getApplications() {
                    return new String[0];
                }

                @Override // com.oculus.os.enterprise.Mode
                public /* synthetic */ AUICapability[] getAuiCapabilities() {
                    return new AUICapability[0];
                }

                @Override // com.oculus.os.enterprise.Mode
                public /* synthetic */ String getDefaultApplication() {
                    return "";
                }

                @Override // com.oculus.os.enterprise.Mode
                public /* synthetic */ String getDisplayName() {
                    return "Default";
                }

                @Override // com.oculus.os.enterprise.Mode
                public /* synthetic */ HomeButtonBehaviour getHomeButtonBehaviour() {
                    return HomeButtonBehaviour.UNRESTRICTED;
                }

                @Override // com.oculus.os.enterprise.Mode
                public /* synthetic */ Optional<String> getPin() {
                    return Optional.empty();
                }
            }, new Mode() {
                /* class com.oculus.os.enterprise.Configuration.AnonymousClass2 */

                @Override // com.oculus.os.enterprise.Mode
                public /* synthetic */ String[] getApplications() {
                    return new String[0];
                }

                @Override // com.oculus.os.enterprise.Mode
                public /* synthetic */ AUICapability[] getAuiCapabilities() {
                    return new AUICapability[0];
                }

                @Override // com.oculus.os.enterprise.Mode
                public /* synthetic */ String getDefaultApplication() {
                    return "";
                }

                @Override // com.oculus.os.enterprise.Mode
                public /* synthetic */ String getDisplayName() {
                    return "Default";
                }

                @Override // com.oculus.os.enterprise.Mode
                public /* synthetic */ HomeButtonBehaviour getHomeButtonBehaviour() {
                    return HomeButtonBehaviour.UNRESTRICTED;
                }

                @Override // com.oculus.os.enterprise.Mode
                public /* synthetic */ Optional<String> getPin() {
                    return Optional.empty();
                }
            }};
        }

        public static Date $default$getTimestamp(Configuration configuration) {
            return new Date(0);
        }

        public static ControllerMode $default$getControllerMode(Configuration configuration) {
            return ControllerMode.GAZE_MODE_DISABLED;
        }

        public static GuardianMode $default$getGuardianMode(Configuration configuration) {
            return GuardianMode.ENABLED;
        }

        public static HandTracking $default$getHandTracking(Configuration configuration) {
            return HandTracking.DISABLED;
        }
    }

    ControllerMode getControllerMode();

    int getDefaultModeIndex();

    String[] getDynamicConfig();

    GuardianMode getGuardianMode();

    HandTracking getHandTracking();

    Date getLastFetchTime();

    Date getLicenseExpirationDate();

    Mode[] getModes();

    @Nullable
    String getOwnerName();

    boolean getShowUnknownSources();

    Date getTimestamp();

    String toMarshalledString() throws UnsupportedEncodingException;
}
