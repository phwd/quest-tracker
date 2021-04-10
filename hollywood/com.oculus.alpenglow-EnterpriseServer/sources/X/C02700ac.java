package X;

import com.facebook.tigon.iface.TigonRequest;
import com.facebook.tigon.iface.TigonRequestBuilder;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.0ac  reason: invalid class name and case insensitive filesystem */
public class C02700ac implements TigonRequest {
    public final C05550ju A00;
    public final String A01;
    public final String A02;
    public final Map<String, String> A03;
    @Nullable
    public final Map<C05590k4<?>, Object> A04;

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

    public C02700ac(TigonRequestBuilder tigonRequestBuilder) {
        Map<C05590k4<?>, Object> map;
        this.A01 = tigonRequestBuilder.mMethod;
        this.A02 = tigonRequestBuilder.mUrl;
        this.A03 = Collections.unmodifiableMap(tigonRequestBuilder.mHeaders);
        this.A00 = tigonRequestBuilder.mHttpPriority;
        Map<C05590k4<?>, Object> map2 = tigonRequestBuilder.mLayerInformation;
        if (map2 != null) {
            map = Collections.unmodifiableMap(map2);
        } else {
            map = null;
        }
        this.A04 = map;
    }
}
