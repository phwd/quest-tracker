package com.facebook.acra.config;

import android.content.Context;
import com.facebook.acra.sender.FlexibleReportSender;
import com.facebook.acra.sender.HttpPostSender;

public class DefaultAcraConfig extends BaseDefaultAcraConfig {
    @Override // com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.config.BaseDefaultAcraConfig
    public FlexibleReportSender createReportSender() {
        return new HttpPostSender(this);
    }

    public DefaultAcraConfig(Context context, String str, boolean z) {
        super(context, str, z);
    }

    public DefaultAcraConfig(Context context, String str, boolean z, boolean z2, boolean z3) {
        super(context, str, z, z2, z3);
    }

    public DefaultAcraConfig(Context context, String str, boolean z, boolean z2, boolean z3, String str2) {
        super(context, str, z, z2, z3, str2);
    }
}
