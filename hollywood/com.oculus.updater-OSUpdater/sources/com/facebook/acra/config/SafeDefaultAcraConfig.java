package com.facebook.acra.config;

import android.content.Context;
import com.facebook.acra.sender.FlexibleReportSender;
import com.facebook.acra.sender.SafeHttpPostSender;
import javax.annotation.Nullable;

public class SafeDefaultAcraConfig extends BaseDefaultAcraConfig {
    public SafeDefaultAcraConfig(Context context, String str, boolean z) {
        super(context, str, z);
    }

    public SafeDefaultAcraConfig(Context context, String str, boolean z, boolean z2, boolean z3, @Nullable String str2) {
        super(context, str, z, z2, z3, str2);
    }

    public SafeDefaultAcraConfig(Context context, String str, boolean z, boolean z2, boolean z3) {
        super(context, str, z, z2, z3);
    }

    @Override // com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.config.BaseDefaultAcraConfig
    public FlexibleReportSender createReportSender() {
        return new SafeHttpPostSender(this);
    }
}
