package com.oculus.mediaupload.api;

import X.AbstractC08380wS;
import X.AnonymousClass0J3;
import X.AnonymousClass0N1;
import X.AnonymousClass0S3;
import X.C003108z;
import X.C003809k;
import X.C08360wQ;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.facebook.annotations.Generated;
import com.oculus.http.common.HttpModule;
import com.oculus.http.core.interceptor.ApiInterceptor;
import com.oculus.http.core.interceptor.GzipInterceptor;
import com.oculus.http.useragent.UserAgentModule;
import com.oculus.locale.LocaleModule;
import java.util.List;
import javax.inject.Provider;

@Generated({"By: InjectorProcessor"})
public class OkHttpClient_com_oculus_mediaupload_api_FacebookHttpClientMethodAutoProvider extends AnonymousClass0J3<AnonymousClass0N1> {
    public final Object get() {
        AnonymousClass0N1 A05 = HttpModule.A05(this);
        Context A00 = C003108z.A00(this);
        PackageInfo A02 = C003809k.A02(this);
        String A01 = UserAgentModule.A01(this);
        Provider A012 = LocaleModule.A01(this);
        C08360wQ r2 = new C08360wQ(A05);
        ApiInterceptor A002 = ApiInterceptor.A00(A00, A02, A01, A012);
        List<AbstractC08380wS> list = r2.A0N;
        list.add(A002);
        list.add(new GzipInterceptor());
        r2.A0K = AnonymousClass0S3.A00(Build.TIME);
        return new AnonymousClass0N1(r2);
    }
}
