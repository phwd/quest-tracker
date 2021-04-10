package X;

import com.facebook.acra.util.HttpRequestMultipart;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.tigon.iface.TigonRequest;
import com.facebook.tigon.javaservice.AbstractRequestToken;
import com.facebook.tigon.javaservice.JavaBackedTigonService;
import com.facebook.tigon.oktigon.OkTigonRequestToken;
import java.io.IOException;
import java.util.Map;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* renamed from: X.0ic  reason: invalid class name and case insensitive filesystem */
public class C02350ic implements JavaBackedTigonService {
    public String A00;
    public final OkHttpClient A01;

    @Override // com.facebook.tigon.javaservice.JavaBackedTigonService
    @DoNotStrip
    public void submitHttpRequest(AbstractRequestToken abstractRequestToken, TigonRequest tigonRequest, byte[] bArr) throws IOException {
        OkTigonRequestToken okTigonRequestToken = (OkTigonRequestToken) abstractRequestToken;
        Request.Builder builder = new Request.Builder();
        builder.url(tigonRequest.url());
        C02340ib r7 = null;
        String str = null;
        boolean z = false;
        for (Map.Entry<String, String> entry : tigonRequest.headers().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (HttpRequestMultipart.CONTENT_TYPE.equalsIgnoreCase(key)) {
                str = value;
            } else {
                builder.header(key, value);
            }
            if ("User-Agent".equalsIgnoreCase(key)) {
                z = true;
            }
        }
        if (bArr != null) {
            r7 = new C02340ib(bArr, str);
        }
        builder.method(tigonRequest.method(), r7);
        if (!z) {
            builder.addHeader("User-Agent", this.A00);
        }
        Call newCall = this.A01.newCall(builder.build());
        okTigonRequestToken.mActiveCall = newCall;
        newCall.enqueue(okTigonRequestToken);
    }

    public C02350ic(OkHttpClient okHttpClient, @Nullable String str) {
        this.A01 = okHttpClient;
        this.A00 = str;
    }

    @DoNotStrip
    public void setUserAgent(String str) {
        this.A00 = str;
    }
}
