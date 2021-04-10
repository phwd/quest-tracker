package com.facebook.acra.sender;

import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.util.HttpConnectionProvider;
import com.facebook.acra.util.SSLConnectionProvider;
import com.facebook.acra.util.UnsafeConnectionProvider;
import com.facebook.infer.annotation.Nullsafe;
import java.net.Proxy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class HttpPostSender extends BaseHttpPostSender {
    public HttpPostSender(AcraReportingConfig acraReportingConfig) {
        super(acraReportingConfig);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.acra.sender.BaseHttpPostSender
    public final HttpConnectionProvider getConnectionProvider(Proxy proxy) {
        if (this.mSkipSslCertChecks) {
            return new UnsafeConnectionProvider(3000, proxy);
        }
        return new SSLConnectionProvider(3000, proxy);
    }
}
