package com.facebook.acra.config;

import android.content.Context;
import com.facebook.acra.sender.FlexibleReportSender;
import com.facebook.acra.sender.HttpPostSender;

public final class DefaultAcraConfig extends BaseDefaultAcraConfig {
    public DefaultAcraConfig(Context context, String str, boolean z) {
        super(context, str, false);
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public final FlexibleReportSender createReportSender() {
        return new HttpPostSender(this);
    }
}
