package com.facebook.acra.sender;

import android.net.Uri;
import com.facebook.acra.ACRA;
import com.facebook.acra.CrashReportData;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.constants.ReportField;
import com.facebook.acra.util.ACRAResponse;
import com.facebook.acra.util.HttpConnectionProvider;
import com.facebook.acra.util.HttpRequest;
import com.facebook.acra.util.HttpRequestMultipart;
import com.facebook.debug.log.BLog;
import com.oculus.util.constants.OculusConstants;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import javax.annotation.Nullable;

public abstract class BaseHttpPostSender implements FlexibleReportSender {
    protected final AcraReportingConfig mConfig;
    private Uri mCrashReportEndpoint = Uri.parse(this.mConfig.crashReportUrl());
    private Proxy mProxy;
    protected boolean mSkipSslCertChecks;
    private boolean mUseMultipartPost;
    private boolean mUseZstd;

    /* access modifiers changed from: protected */
    public abstract HttpConnectionProvider getConnectionProvider(Proxy proxy);

    public BaseHttpPostSender(AcraReportingConfig acraReportingConfig) {
        this.mConfig = acraReportingConfig;
    }

    @Override // com.facebook.acra.sender.ReportSender
    public void send(CrashReportData crashReportData) throws ReportSenderException {
        try {
            sendInternal(crashReportData);
        } catch (Throwable th) {
            throw new ReportSenderException("Error while sending report to Http Post Form.", th);
        }
    }

    private void sendInternal(CrashReportData crashReportData) throws IOException {
        URL url = new URL(this.mCrashReportEndpoint.toString());
        BLog.d(ACRA.LOG_TAG, "Connect to %s", url.toString());
        String str = null;
        HttpConnectionProvider connectionProvider = getConnectionProvider(this.mConfig.allowProxy() ? this.mProxy : null);
        String userAgent = ACRA.getConfig().getUserAgent();
        HashMap hashMap = new HashMap();
        if (crashReportData.containsKey(ReportField.UID)) {
            str = (String) crashReportData.get(ReportField.UID);
        }
        if (str != null && !str.equals("") && !str.equals(OculusConstants.DEFAULT_ENTERPRISE_USER_ID)) {
            hashMap.put("Cookie", "c_user=" + str);
        }
        if (this.mUseMultipartPost) {
            HttpRequestMultipart httpRequestMultipart = new HttpRequestMultipart(connectionProvider);
            httpRequestMultipart.setHeaders(hashMap);
            httpRequestMultipart.sendPost(url, crashReportData, crashReportData.getInputStreamFields(), new ACRAResponse(), userAgent, this.mUseZstd);
            return;
        }
        HttpRequest httpRequest = new HttpRequest(connectionProvider);
        httpRequest.setHeaders(hashMap);
        httpRequest.sendPost(url, crashReportData, new ACRAResponse(), userAgent);
    }

    @Override // com.facebook.acra.sender.FlexibleReportSender
    public boolean setHost(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        if (str.equals(this.mCrashReportEndpoint.getHost())) {
            return true;
        }
        this.mCrashReportEndpoint = this.mCrashReportEndpoint.buildUpon().authority(str).build();
        return true;
    }

    @Override // com.facebook.acra.sender.FlexibleReportSender
    public void setSkipSslCertsChecks(boolean z) {
        this.mSkipSslCertChecks = z;
    }

    @Override // com.facebook.acra.sender.FlexibleReportSender
    public void setProxy(@Nullable Proxy proxy) {
        this.mProxy = proxy;
    }

    @Override // com.facebook.acra.sender.ReportSender
    public boolean supportsMultipart() {
        return this.mUseMultipartPost;
    }

    public void setUseMultipartPost(boolean z) {
        this.mUseMultipartPost = z;
    }

    public void setUseZstd(boolean z) {
        this.mUseZstd = z;
    }
}
