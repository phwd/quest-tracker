package com.facebook.acra.sender;

import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.util.HttpConnectionProvider;
import com.facebook.acra.util.SSLConnectionProvider;
import com.facebook.acra.util.UnsafeConnectionProvider;
import com.facebook.infer.annotation.Nullsafe;
import java.net.Proxy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class HttpPostSender extends BaseHttpPostSender {
    @Override // com.facebook.acra.sender.BaseHttpPostSender
    public HttpConnectionProvider getConnectionProvider(Proxy proxy) {
        if (!this.mSkipSslCertChecks || !this.mConfig.allowUnsafeConnectionsForDebugging()) {
            return new SSLConnectionProvider(this.mConfig.socketTimeout(), proxy);
        }
        return new UnsafeConnectionProvider(this.mConfig.socketTimeout(), proxy);
    }

    public HttpPostSender(AcraReportingConfig acraReportingConfig) {
        super(acraReportingConfig);
    }
}
