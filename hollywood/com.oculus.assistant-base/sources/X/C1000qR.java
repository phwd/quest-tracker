package X;

import com.facebook.tigon.iface.TigonRequest;
import com.facebook.tigon.iface.TigonRequestBuilder;
import java.util.Collections;
import java.util.Map;

/* renamed from: X.qR  reason: case insensitive filesystem */
public final class C1000qR implements TigonRequest {
    public final Lx A00;
    public final String A01;
    public final String A02;
    public final Map A03;
    public final Map A04;

    public C1000qR(TigonRequestBuilder tigonRequestBuilder) {
        Map map;
        this.A01 = tigonRequestBuilder.mMethod;
        this.A02 = tigonRequestBuilder.mUrl;
        this.A03 = Collections.unmodifiableMap(tigonRequestBuilder.mHeaders);
        this.A00 = tigonRequestBuilder.mHttpPriority;
        Map map2 = tigonRequestBuilder.mLayerInformation;
        if (map2 != null) {
            map = Collections.unmodifiableMap(map2);
        } else {
            map = null;
        }
        this.A04 = map;
    }

    @Override // com.facebook.tigon.iface.TigonRequest
    public final Map headers() {
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
