package com.oculus.os.enterprise;

import java.util.Optional;

public interface Mode {
    String[] getApplications();

    AUICapability[] getAuiCapabilities();

    String getDefaultApplication();

    String getDisplayName();

    HomeButtonBehaviour getHomeButtonBehaviour();

    Optional<String> getPin();

    /* renamed from: com.oculus.os.enterprise.Mode$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static String[] $default$getApplications(Mode mode) {
            return new String[0];
        }

        public static AUICapability[] $default$getAuiCapabilities(Mode mode) {
            return new AUICapability[0];
        }

        public static String $default$getDefaultApplication(Mode mode) {
            return "";
        }

        public static String $default$getDisplayName(Mode mode) {
            return "Default";
        }

        /* JADX WARN: Incorrect args count in method signature: ()Ljava/util/Optional<Ljava/lang/String;>; */
        public static Optional $default$getPin(Mode mode) {
            return Optional.empty();
        }

        public static HomeButtonBehaviour $default$getHomeButtonBehaviour(Mode mode) {
            return HomeButtonBehaviour.UNRESTRICTED;
        }
    }
}
