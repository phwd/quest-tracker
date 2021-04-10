package X;

import android.content.Context;
import com.facebook.inject.ContextScoped;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ContextScoped
/* renamed from: X.0bM  reason: invalid class name and case insensitive filesystem */
public final class C03020bM implements AnonymousClass0RX {
    public static final Map<Context, Map<Integer, Object>> A03 = Collections.synchronizedMap(new HashMap());
    public final AnonymousClass0JG<Context, AnonymousClass00A> A00 = new AnonymousClass0JG<>(new C03030bN(this));
    public final AnonymousClass0Lh A01;
    public final Context A02;

    public final AnonymousClass0RA A00() {
        return this.A01.getInjectorThreadStack();
    }

    @Override // X.AnonymousClass0RX
    public final <T> AbstractC07240oz<T> A7Z(C01440Gz<T> r2, AbstractC07240oz<T> r3) {
        return new C03010bL(this, r3);
    }

    public C03020bM(AnonymousClass0Lh r3) {
        this.A01 = r3;
        this.A02 = r3.getInjectorThreadStack().A00();
    }
}
