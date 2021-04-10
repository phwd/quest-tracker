package X;

import android.content.Context;
import com.facebook.inject.ContextScoped;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ContextScoped
/* renamed from: X.Xx  reason: case insensitive filesystem */
public final class C0250Xx implements AbstractC0133Qc {
    public static final Map<Context, Map<Integer, Object>> A03 = Collections.synchronizedMap(new HashMap());
    public final IP<Context, AnonymousClass05> A00 = new IP<>(new C0251Xy(this));
    public final AbstractC0096Hu A01;
    public final Context A02;

    public C0250Xx(AbstractC0096Hu hu) {
        this.A01 = hu;
        this.A02 = hu.getInjectorThreadStack().A00();
    }
}
