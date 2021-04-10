package com.oculus.alpenglow.config;

import com.facebook.infer.annotation.Nullsafe;
import com.google.gson.annotations.SerializedName;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DynamicConfig {
    @SerializedName("arvr_enterprise_enable_casting")
    public boolean enableCasting;
    @SerializedName("arvr_enterprise_enable_debug_panel")
    public boolean enableDebugPanel;

    public final boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof DynamicConfig)) {
            return false;
        }
        DynamicConfig dynamicConfig = (DynamicConfig) obj;
        return this.enableDebugPanel == dynamicConfig.enableDebugPanel && this.enableCasting == dynamicConfig.enableCasting;
    }
}
