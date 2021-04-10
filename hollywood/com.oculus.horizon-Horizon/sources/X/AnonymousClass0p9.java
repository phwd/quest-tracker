package X;

import android.content.Context;
import com.facebook.inject.ContextScoped;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Provider;

@ContextScoped
/* renamed from: X.0p9  reason: invalid class name */
public final class AnonymousClass0p9 implements AbstractC01320Qc {
    public static final Map<Context, Map<Integer, Object>> A03 = Collections.synchronizedMap(new HashMap());
    public final AnonymousClass0JL<Context, AnonymousClass005> A00 = new AnonymousClass0JL<>(new AnonymousClass0pJ(this));
    public final AnonymousClass0J2 A01;
    public final Context A02;

    public final AnonymousClass0QF A00() {
        return this.A01.getInjectorThreadStack();
    }

    @Override // X.AbstractC01320Qc
    public final <T> Provider<T> A8U(C09160zY<T> r2, Provider<T> provider) {
        return new AnonymousClass0p8(this, provider);
    }

    public AnonymousClass0p9(AnonymousClass0J2 r3) {
        this.A01 = r3;
        this.A02 = r3.getInjectorThreadStack().A00();
    }
}
