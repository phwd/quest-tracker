package com.facebook.acra.sender;

import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.util.HttpConnectionProvider;
import com.facebook.acra.util.SSLConnectionProvider;
import com.facebook.acra.util.UnsafeConnectionProvider;
import java.net.Proxy;

public class HttpPostSender extends BaseHttpPostSender {
    public HttpPostSender(AcraReportingConfig acraConfig) {
        super(acraConfig);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.acra.sender.BaseHttpPostSender
    public HttpConnectionProvider getConnectionProvider(Proxy proxy) {
        if (!this.mSkipSslCertChecks || !this.mConfig.allowUnsafeConnectionsForDebugging()) {
            return new SSLConnectionProvider(this.mConfig.socketTimeout(), proxy);
        }
        return new UnsafeConnectionProvider(this.mConfig.socketTimeout(), proxy);
    }
}
