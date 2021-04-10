package com.facebook.mobileconfig.factory;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public interface MobileConfig extends MobileConfigContext {
    @Deprecated
    MobileConfigValueState getCurrentMobileConfigState(boolean z);

    @Deprecated
    boolean isLoaded(boolean z);

    public enum MobileConfigValueState {
        DefaultValue("DefaultValue"),
        MobileConfigValue("MobileConfigValue");
        
        public final String loggingValue;

        private MobileConfigValueState(String str) {
            this.loggingValue = str;
        }
    }
}
