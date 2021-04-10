package com.facebook.acra.sender;

import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.util.HttpConnectionProvider;
import com.facebook.acra.util.SSLConnectionProvider;
import java.net.Proxy;

public class SafeHttpPostSender extends BaseHttpPostSender {
    public SafeHttpPostSender(AcraReportingConfig acraReportingConfig) {
        super(acraReportingConfig);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.acra.sender.BaseHttpPostSender
    public HttpConnectionProvider getConnectionProvider(Proxy proxy) {
        return new SSLConnectionProvider(this.mConfig.socketTimeout(), proxy);
    }
}
