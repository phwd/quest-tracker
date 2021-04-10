package X;

import com.facebook.tigon.iface.TigonRequest;
import com.facebook.tigon.iface.TigonRequestBuilder;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.0id  reason: invalid class name and case insensitive filesystem */
public class C02360id implements TigonRequest {
    public final AnonymousClass0mQ A00;
    public final String A01;
    public final String A02;
    public final Map<String, String> A03;
    @Nullable
    public final Map<C03420mb<?>, Object> A04;

    public C02360id(TigonRequestBuilder tigonRequestBuilder) {
        Map<C03420mb<?>, Object> map;
        this.A01 = tigonRequestBuilder.mMethod;
        this.A02 = tigonRequestBuilder.mUrl;
        this.A03 = Collections.unmodifiableMap(tigonRequestBuilder.mHeaders);
        this.A00 = tigonRequestBuilder.mHttpPriority;
        Map<C03420mb<?>, Object> map2 = tigonRequestBuilder.mLayerInformation;
        if (map2 != null) {
            map = Collections.unmodifiableMap(map2);
        } else {
            map = null;
        }
        this.A04 = map;
    }

    @Override // com.facebook.tigon.iface.TigonRequest
    public final Map<String, String> headers() {
        return this.A03;
    }

    @Override // com.facebook.tigon.iface.TigonRequest
    public final String method() {
        return this.A01;
    }

    @Override // com.facebook.tigon.iface.TigonRequest
    public final String url() {
        return this.A02;
    }
}
