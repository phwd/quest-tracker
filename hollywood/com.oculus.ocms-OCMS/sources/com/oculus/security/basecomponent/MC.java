package com.oculus.security.basecomponent;

import com.facebook.mobileconfig.MobileConfigConfigs;
import com.facebook.mobileconfig.MobileConfigParams1;
import com.facebook.mobileconfig.MobileConfigParams2;

public final class MC {

    public static class oculus_security_content_provider_logging {
        public static final int __CONFIG__ = MobileConfigConfigs.oculus_security_content_provider_logging;
        public static final long enabled = MobileConfigParams2.oculus_security_content_provider_logging_enabled;
    }

    public static class oculus_security_intent_logging {
        public static final int __CONFIG__ = MobileConfigConfigs.oculus_security_intent_logging;
        public static final long horizon_enabled = MobileConfigParams1.oculus_security_intent_logging_horizon_enabled;
    }
}
