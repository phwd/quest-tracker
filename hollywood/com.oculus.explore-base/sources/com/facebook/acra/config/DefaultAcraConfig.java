package com.facebook.acra.config;

import android.content.Context;
import com.facebook.acra.sender.FlexibleReportSender;
import com.facebook.acra.sender.HttpPostSender;

public class DefaultAcraConfig extends BaseDefaultAcraConfig {
    public DefaultAcraConfig(Context applicationContext, String crashReportUrl, boolean isInternalBuild) {
        super(applicationContext, crashReportUrl, isInternalBuild);
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public FlexibleReportSender createReportSender() {
        return new HttpPostSender(this);
    }
}
