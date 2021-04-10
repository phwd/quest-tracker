package com.facebook.acra.pinnedcertsender;

import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.sender.HttpPostSender;
import com.facebook.acra.util.HttpConnectionProvider;
import com.facebook.acra.util.SSLConnectionProvider;
import com.facebook.acra.util.UnsafeConnectionProvider;
import com.facebook.infer.annotation.Nullsafe;
import java.net.Proxy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class HttpPinnedCertPostSender extends HttpPostSender {
    @Override // com.facebook.acra.sender.BaseHttpPostSender, com.facebook.acra.sender.HttpPostSender
    public HttpConnectionProvider getConnectionProvider(Proxy proxy) {
        if (this.mSkipSslCertChecks && this.mConfig.allowUnsafeConnectionsForDebugging()) {
            return new UnsafeConnectionProvider(this.mConfig.socketTimeout(), proxy);
        }
        boolean usePinningSSLProvider = this.mConfig.usePinningSSLProvider();
        int socketTimeout = this.mConfig.socketTimeout();
        if (usePinningSSLProvider) {
            return new PinnedSSLConnectionProvider(socketTimeout, this.mConfig.getAppBuildTimestamp(), proxy);
        }
        return new SSLConnectionProvider(socketTimeout, proxy);
    }

    public HttpPinnedCertPostSender(AcraReportingConfig acraReportingConfig) {
        super(acraReportingConfig);
    }
}
