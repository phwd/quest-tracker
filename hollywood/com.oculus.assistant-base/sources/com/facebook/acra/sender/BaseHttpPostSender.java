package com.facebook.acra.sender;

import X.AnonymousClass08;
import X.C0139Dd;
import android.net.Uri;
import com.facebook.acra.ACRA;
import com.facebook.acra.CrashReportData;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.constants.ReportField;
import com.facebook.acra.util.ACRAResponse;
import com.facebook.acra.util.HttpConnectionProvider;
import com.facebook.acra.util.HttpRequest;
import com.facebook.acra.util.HttpRequestMultipart;
import com.facebook.assistant.oacr.OacrConstants;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;

public abstract class BaseHttpPostSender implements FlexibleReportSender {
    public final AcraReportingConfig mConfig;
    public Uri mCrashReportEndpoint;
    public Proxy mProxy;
    public boolean mSkipSslCertChecks;
    public boolean mUseMultipartPost;
    public boolean mUseZstd;

    public abstract HttpConnectionProvider getConnectionProvider(Proxy proxy);

    private void sendInternal(CrashReportData crashReportData) {
        String str;
        URL url = new URL(this.mCrashReportEndpoint.toString());
        C0139Dd.A0F("ACRA", "Connect to %s", url.toString());
        Proxy proxy = null;
        if (this.mConfig.allowProxy()) {
            proxy = this.mProxy;
        }
        HttpConnectionProvider connectionProvider = getConnectionProvider(proxy);
        String userAgent = ACRA.mConfig.getUserAgent();
        HashMap hashMap = new HashMap();
        if (crashReportData.containsKey(ReportField.UID) && (str = (String) crashReportData.get(ReportField.UID)) != null && !str.equals(OacrConstants.AUTO_SPEECH_DOMAIN) && !str.equals("0")) {
            hashMap.put("Cookie", AnonymousClass08.A04("c_user=", str));
        }
        if (this.mUseMultipartPost) {
            HttpRequestMultipart httpRequestMultipart = new HttpRequestMultipart(connectionProvider);
            httpRequestMultipart.mHeaders = hashMap;
            httpRequestMultipart.sendPost(url, crashReportData, crashReportData.mInputStreamFields, new ACRAResponse(), userAgent, this.mUseZstd);
            return;
        }
        HttpRequest httpRequest = new HttpRequest(connectionProvider);
        httpRequest.mHeaders = hashMap;
        httpRequest.sendPost(url, crashReportData, new ACRAResponse(), userAgent);
    }

    @Override // com.facebook.acra.sender.FlexibleReportSender
    public boolean setHost(String str) {
        if (str == null || str.equals(OacrConstants.AUTO_SPEECH_DOMAIN)) {
            return false;
        }
        if (!str.equals(this.mCrashReportEndpoint.getHost())) {
            this.mCrashReportEndpoint = this.mCrashReportEndpoint.buildUpon().authority(str).build();
        }
        return true;
    }

    public BaseHttpPostSender(AcraReportingConfig acraReportingConfig) {
        this.mConfig = acraReportingConfig;
        this.mCrashReportEndpoint = Uri.parse(acraReportingConfig.crashReportUrl());
    }

    @Override // com.facebook.acra.sender.ReportSender
    public void send(CrashReportData crashReportData) {
        try {
            sendInternal(crashReportData);
        } catch (Throwable th) {
            throw new ReportSenderException("Error while sending report to Http Post Form.", th);
        }
    }

    @Override // com.facebook.acra.sender.ReportSender
    public boolean supportsMultipart() {
        return this.mUseMultipartPost;
    }

    @Override // com.facebook.acra.sender.FlexibleReportSender
    public void setProxy(Proxy proxy) {
        this.mProxy = proxy;
    }

    @Override // com.facebook.acra.sender.FlexibleReportSender
    public void setSkipSslCertsChecks(boolean z) {
        this.mSkipSslCertChecks = z;
    }

    public void setUseMultipartPost(boolean z) {
        this.mUseMultipartPost = z;
    }

    public void setUseZstd(boolean z) {
        this.mUseZstd = z;
    }
}
