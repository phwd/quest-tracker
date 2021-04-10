package X;

import android.content.Context;
import com.facebook.inject.ContextScoped;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ContextScoped
/* renamed from: X.Sc  reason: case insensitive filesystem */
public final class C0132Sc implements PC {
    public static final Map<Context, Map<Integer, Object>> A03 = Collections.synchronizedMap(new HashMap());
    public final Il<Context, AnonymousClass05> A00 = new Il<>(new C0133Sd(this));
    public final BZ A01;
    public final Context A02;

    public C0132Sc(BZ bz) {
        this.A01 = bz;
        this.A02 = bz.getInjectorThreadStack().A00();
    }
}
