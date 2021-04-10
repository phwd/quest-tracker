package X;

import android.content.Context;
import com.facebook.inject.ContextScoped;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Provider;

@ContextScoped
/* renamed from: X.0lj  reason: invalid class name and case insensitive filesystem */
public final class C03210lj implements AbstractC01120Rf {
    public static final Map<Context, Map<Integer, Object>> A03 = Collections.synchronizedMap(new HashMap());
    public final AnonymousClass0HO<Context, AnonymousClass00A> A00 = new AnonymousClass0HO<>(new C03250lr(this));
    public final AnonymousClass0VF A01;
    public final Context A02;

    public final AnonymousClass0RH A00() {
        return this.A01.getInjectorThreadStack();
    }

    @Override // X.AbstractC01120Rf
    public final <T> Provider<T> A9W(AnonymousClass14P<T> r2, Provider<T> provider) {
        return new C03200li(this, provider);
    }

    public C03210lj(AnonymousClass0VF r3) {
        this.A01 = r3;
        this.A02 = r3.getInjectorThreadStack().A00();
    }
}
