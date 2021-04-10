package com.facebook.acra.sender;

import android.net.Uri;
import com.facebook.acra.ACRA;
import com.facebook.acra.CrashReportData;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.util.ACRAResponse;
import com.facebook.acra.util.HttpConnectionProvider;
import com.facebook.acra.util.HttpRequest;
import com.facebook.acra.util.HttpRequestMultipart;
import com.facebook.debug.log.BLog;
import com.oculus.common.build.BuildConfig;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;

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

    @Override // com.facebook.acra.sender.FlexibleReportSender
    public final boolean setHost(String str) {
        if (str == null || str.equals(BuildConfig.PROVIDER_SUFFIX)) {
            return false;
        }
        if (str.equals(this.mCrashReportEndpoint.getHost())) {
            return true;
        }
        this.mCrashReportEndpoint = this.mCrashReportEndpoint.buildUpon().authority(str).build();
        return true;
    }

    @Override // com.facebook.acra.sender.FlexibleReportSender
    public final void setSkipSslCertsChecks(boolean z) {
        this.mSkipSslCertChecks = z;
    }

    @Override // com.facebook.acra.sender.ReportSender
    public final boolean supportsMultipart() {
        return this.mUseMultipartPost;
    }

    @Override // com.facebook.acra.sender.ReportSender
    public final void send(CrashReportData crashReportData) throws ReportSenderException {
        try {
            URL url = new URL(this.mCrashReportEndpoint.toString());
            BLog.d(ACRA.LOG_TAG, "Connect to %s", url.toString());
            HttpConnectionProvider connectionProvider = getConnectionProvider(this.mProxy);
            String userAgent = ACRA.getConfig().getUserAgent();
            HashMap hashMap = new HashMap();
            String str = null;
            if (crashReportData.containsKey("UID")) {
                str = (String) crashReportData.get("UID");
            }
            if (str != null && !str.equals(BuildConfig.PROVIDER_SUFFIX) && !str.equals("0")) {
                hashMap.put("Cookie", "c_user=" + str);
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
        } catch (Throwable th) {
            throw new ReportSenderException("Error while sending report to Http Post Form.", th);
        }
    }
}
