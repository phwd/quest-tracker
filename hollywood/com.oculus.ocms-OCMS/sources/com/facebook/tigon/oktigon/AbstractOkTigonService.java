package com.facebook.tigon.oktigon;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.tigon.iface.TigonRequest;
import com.facebook.tigon.javaservice.AbstractRequestToken;
import com.facebook.tigon.javaservice.JavaBackedTigonService;
import java.io.IOException;
import java.util.Map;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AbstractOkTigonService implements JavaBackedTigonService {
    private static final String ACCESS_TOKEN_PARAM = "access_token";
    private final String mAccessToken;
    private final OkHttpClient mOkHttpClient;
    private final String mUserAgent;

    public AbstractOkTigonService(OkHttpClient okHttpClient, @Nullable String str, String str2) {
        this.mOkHttpClient = okHttpClient;
        this.mAccessToken = str;
        this.mUserAgent = str2;
    }

    @Override // com.facebook.tigon.javaservice.JavaBackedTigonService
    @DoNotStrip
    public void submitHttpRequest(AbstractRequestToken abstractRequestToken, TigonRequest tigonRequest, byte[] bArr) throws IOException {
        OkTigonRequestToken okTigonRequestToken = (OkTigonRequestToken) abstractRequestToken;
        Call newCall = this.mOkHttpClient.newCall(buildRequest(tigonRequest, bArr));
        okTigonRequestToken.attachActiveCall(newCall);
        newCall.enqueue(okTigonRequestToken);
    }

    private Request buildRequest(TigonRequest tigonRequest, byte[] bArr) {
        Request.Builder builder = new Request.Builder();
        String url = tigonRequest.url();
        if (this.mAccessToken != null) {
            url = url + "?" + "access_token" + "=" + this.mAccessToken;
        }
        builder.url(url);
        OkTigonRequestBody okTigonRequestBody = null;
        String str = null;
        boolean z = false;
        for (Map.Entry<String, String> entry : tigonRequest.headers().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if ("Content-Type".equalsIgnoreCase(key)) {
                str = value;
            } else {
                builder.header(key, value);
            }
            if ("User-Agent".equalsIgnoreCase(key)) {
                z = true;
            }
        }
        if (bArr != null) {
            okTigonRequestBody = new OkTigonRequestBody(bArr, str);
        }
        builder.method(tigonRequest.method(), okTigonRequestBody);
        if (!z) {
            builder.addHeader("User-Agent", this.mUserAgent);
        }
        return builder.build();
    }
}
