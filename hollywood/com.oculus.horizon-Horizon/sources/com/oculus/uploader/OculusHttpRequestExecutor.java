package com.oculus.uploader;

import X.AbstractC06640p5;
import X.AnonymousClass0N1;
import X.AnonymousClass0p1;
import X.AnonymousClass1Rj;
import X.AnonymousClass1Rk;
import X.AnonymousClass1Rl;
import X.AnonymousClass1Rm;
import X.C003008y;
import X.C08340wO;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.http.core.interceptor.OculusAuthorizationInterceptor;
import com.oculus.http.defaultclient.DefaultHttpClient;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import okhttp3.Call;

@Dependencies({"_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_BINDING_ID"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class OculusHttpRequestExecutor {
    public static final String FACEBOOK_DOMAIN = "facebook.com";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final Class<?> TAG = OculusHttpRequestExecutor.class;
    @Inject
    @Eager
    public final AnonymousClass0p1<OculusAuthorizationInterceptor> mOculusAuthorizationInterceptor;
    @Inject
    @Eager
    @DefaultHttpClient
    public final AnonymousClass0p1<AnonymousClass0N1> mOkHttpClient;
    public final Map<AnonymousClass1Rm, Call> mRequests = Collections.synchronizedMap(new HashMap());

    public final AnonymousClass1Rm A00(AnonymousClass1Rk r7, Map<String, String> map, URI uri, @Nullable AnonymousClass1Rl r10, AnonymousClass1Rj r11) {
        OkHttp3RequestBody okHttp3RequestBody;
        AnonymousClass1Rm r3 = new AnonymousClass1Rm();
        if (r10 == null) {
            okHttp3RequestBody = null;
        } else {
            okHttp3RequestBody = new OkHttp3RequestBody(r10, r11);
        }
        if (!map.containsKey("Authorization")) {
            try {
                String A05 = this.mOculusAuthorizationInterceptor.get().A05(null);
                if (A05 != null) {
                    map.put("Authorization", StringFormatUtil.formatStrLocaleSafe("OAuth %s", A05));
                }
            } catch (IOException unused) {
            }
        }
        C08340wO r4 = new C08340wO();
        r4.A03(r7.toString(), okHttp3RequestBody);
        r4.A01(uri.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            r4.A02(entry.getKey(), entry.getValue());
        }
        Call A00 = this.mOkHttpClient.get().A00(r4.A00());
        this.mRequests.put(r3, A00);
        uri.toString();
        A00.A03(new OkHttp3Callback(r11));
        return r3;
    }

    @Inject
    public OculusHttpRequestExecutor(AbstractC06640p5 r3) {
        this.mOkHttpClient = new C003008y(394, r3);
        this.mOculusAuthorizationInterceptor = new C003008y(35, r3);
    }
}
