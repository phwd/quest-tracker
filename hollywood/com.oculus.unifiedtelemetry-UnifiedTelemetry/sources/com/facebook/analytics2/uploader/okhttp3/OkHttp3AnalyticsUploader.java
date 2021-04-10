package com.facebook.analytics2.uploader.okhttp3;

import X.AbstractC0090Hb;
import X.AbstractC0096Hu;
import X.AnonymousClass06;
import X.C0089Ha;
import X.C0359dg;
import X.C0363dk;
import X.C0366dn;
import X.C0369ds;
import X.C0370dt;
import X.C0511sk;
import X.C0513sn;
import X.HI;
import X.LD;
import X.ST;
import X.YW;
import X.Z9;
import android.content.Context;
import com.facebook.acra.util.HttpRequest;
import com.facebook.tigon.iface.TigonRequest;
import com.oculus.base.app.AppInfo;
import com.oculus.http.core.interceptor.GzipInterceptor;
import com.oculus.logging.analytics2.AnalyticsUploaderImpl;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

public abstract class OkHttp3AnalyticsUploader implements AbstractC0090Hb {
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    public static final String DEFAULT_ENDPOINT_URL = "https://graph.facebook.com/logging_client_events";
    public static final String FB_API_CALLER_CLASS_HEADER_NAME = "fb_api_caller_class";
    public static final String FB_API_REQ_FRIENDLY_NAME_HEADER_NAME = "fb_api_req_friendly_name";
    public static final String OAUTH_HEADER_PREFIX = "OAuth ";
    public static final String TAG = "OkHttpAnalyticsUploader";
    public static final String USER_AGENT_HEADER_NAME = "User-Agent";
    public static final C0366dn WWW_FORM_URLENCODED_CONTENT_TYPE = C0366dn.A00(HttpRequest.POST_CONTENT_TYPE_FORM_URLENCODED);
    @Nullable
    public String mCachedClassName;

    @Override // X.AbstractC0090Hb
    public final void A5a(C0089Ha ha, YW yw) {
        ST st = ha.A00;
        C0370dt dtVar = new C0370dt();
        AnalyticsUploaderImpl analyticsUploaderImpl = (AnalyticsUploaderImpl) this;
        String str = (String) AbstractC0096Hu.A03(2, 67, analyticsUploaderImpl._UL_mInjectionContext);
        C0370dt.A00("User-Agent", str);
        dtVar.A02("User-Agent", str);
        C0370dt.A00(FB_API_REQ_FRIENDLY_NAME_HEADER_NAME, "sendAnalyticsLog");
        dtVar.A02(FB_API_REQ_FRIENDLY_NAME_HEADER_NAME, "sendAnalyticsLog");
        String str2 = this.mCachedClassName;
        if (str2 == null) {
            str2 = getClass().getName();
            this.mCachedClassName = str2;
        }
        C0370dt.A00(FB_API_CALLER_CLASS_HEADER_NAME, str2);
        dtVar.A02(FB_API_CALLER_CLASS_HEADER_NAME, str2);
        AppInfo appInfo = (AppInfo) AbstractC0096Hu.A03(1, 111, analyticsUploaderImpl._UL_mInjectionContext);
        C0513sn snVar = new C0513sn(new C0511sk(AnonymousClass06.A05(appInfo.appId, "|", appInfo.appSecret), st));
        C0370dt.A00(GzipInterceptor.HEADER_CONTENT_ENCODING, "gzip");
        dtVar.A02(GzipInterceptor.HEADER_CONTENT_ENCODING, "gzip");
        C0363dk dkVar = new C0363dk();
        dkVar.A01(DEFAULT_ENDPOINT_URL);
        dkVar.A02 = null;
        dkVar.A03 = new C0369ds(dtVar).A01();
        dkVar.A03(TigonRequest.POST, snVar);
        try {
            C0359dg A02 = ((LD) AbstractC0096Hu.A03(0, 112, analyticsUploaderImpl._UL_mInjectionContext)).A00(dkVar.A00()).A02();
            int i = A02.A01;
            InputStream A37 = A02.A0B.A02().A37();
            if (i == 200) {
                try {
                    yw.A00.A3R();
                    yw.A01.A43();
                } catch (IOException e) {
                    yw.A01.A3j(e);
                } catch (Throwable th) {
                    yw.A00.unlock();
                    A37.close();
                    throw th;
                }
                yw.A00.unlock();
                A37.close();
                return;
            }
            throw new HI(i, AnonymousClass06.A01("Unexpected HTTP code ", i));
        } catch (IOException e2) {
            Z9 z9 = yw.A00;
            if (z9.A2z()) {
                z9.unlock();
            }
            yw.A01.A3j(e2);
        }
    }

    public OkHttp3AnalyticsUploader(Context context) {
    }
}
