package com.facebook.acra.sender;

import android.net.Uri;
import com.facebook.acra.ACRA;
import com.facebook.acra.CrashReportData;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.util.ACRAResponse;
import com.facebook.acra.util.HttpConnectionProvider;
import com.facebook.acra.util.HttpRequest;
import com.facebook.acra.util.HttpRequestMultipart;
import com.facebook.acra.util.SSLConnectionProvider;
import com.facebook.acra.util.UnsafeConnectionProvider;
import com.facebook.debug.log.BLog;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpPostSender implements FlexibleReportSender {
    protected final AcraReportingConfig mConfig;
    private Uri mCrashReportEndpoint = Uri.parse(this.mConfig.crashReportUrl());
    private Proxy mProxy;
    protected boolean mSkipSslCertChecks;
    private boolean mUseMultipartPost;
    private boolean mUseZstd;

    public HttpPostSender(AcraReportingConfig acraConfig) {
        this.mConfig = acraConfig;
    }

    @Override // com.facebook.acra.sender.ReportSender
    public void send(CrashReportData report) throws ReportSenderException {
        try {
            sendInternal(report);
        } catch (Throwable e) {
            throw new ReportSenderException("Error while sending report to Http Post Form.", e);
        }
    }

    private void sendInternal(CrashReportData report) throws IOException {
        URL url = new URL(this.mCrashReportEndpoint.toString());
        BLog.d(ACRA.LOG_TAG, "Connect to %s", url.toString());
        Proxy proxy = null;
        if (this.mConfig.allowProxy()) {
            proxy = this.mProxy;
        }
        HttpConnectionProvider provider = getConnectionProvider(proxy);
        String userAgent = ACRA.getConfig().getUserAgent();
        Map<String, String> extraHeaders = new HashMap<>();
        String userId = null;
        if (report.containsKey("UID")) {
            userId = (String) report.get("UID");
        }
        if (userId != null && !userId.equals("") && !userId.equals("0")) {
            extraHeaders.put("Cookie", "c_user=" + userId);
        }
        if (this.mUseMultipartPost) {
            HttpRequestMultipart req = new HttpRequestMultipart(provider);
            req.setHeaders(extraHeaders);
            req.sendPost(url, report, report.getInputStreamFields(), new ACRAResponse(), userAgent, this.mUseZstd);
            return;
        }
        HttpRequest req2 = new HttpRequest(provider);
        req2.setHeaders(extraHeaders);
        req2.sendPost(url, report, new ACRAResponse(), userAgent);
    }

    /* access modifiers changed from: protected */
    public HttpConnectionProvider getConnectionProvider(Proxy proxy) {
        if (!this.mSkipSslCertChecks || !this.mConfig.allowUnsafeConnectionsForDebugging()) {
            return new SSLConnectionProvider(this.mConfig.socketTimeout(), proxy);
        }
        return new UnsafeConnectionProvider(this.mConfig.socketTimeout(), proxy);
    }

    @Override // com.facebook.acra.sender.FlexibleReportSender
    public boolean setHost(String host) {
        if (host == null || host.equals("")) {
            return false;
        }
        if (host.equals(this.mCrashReportEndpoint.getHost())) {
            return true;
        }
        this.mCrashReportEndpoint = this.mCrashReportEndpoint.buildUpon().authority(host).build();
        return true;
    }

    @Override // com.facebook.acra.sender.FlexibleReportSender
    public void setSkipSslCertsChecks(boolean skipChecks) {
        this.mSkipSslCertChecks = skipChecks;
    }

    @Override // com.facebook.acra.sender.ReportSender
    public boolean supportsMultipart() {
        return this.mUseMultipartPost;
    }
}
