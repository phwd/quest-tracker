package X;

import com.facebook.tigon.iface.TigonRequest;
import com.facebook.tigon.iface.TigonRequestBuilder;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.Ru  reason: case insensitive filesystem */
public class C0127Ru implements TigonRequest {
    public final Ot A00;
    public final Map<String, String> A01;
    @Nullable
    public final Map<C0111Ng<?>, Object> A02;

    public C0127Ru(TigonRequestBuilder tigonRequestBuilder) {
        Map<C0111Ng<?>, Object> map;
        this.A01 = Collections.unmodifiableMap(tigonRequestBuilder.A01);
        this.A00 = tigonRequestBuilder.A00;
        Map<C0111Ng<?>, Object> map2 = tigonRequestBuilder.A02;
        if (map2 != null) {
            map = Collections.unmodifiableMap(map2);
        } else {
            map = null;
        }
        this.A02 = map;
    }
}
