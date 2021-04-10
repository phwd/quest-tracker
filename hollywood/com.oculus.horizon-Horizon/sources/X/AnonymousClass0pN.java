package X;

import android.content.Context;
import com.facebook.inject.ApplicationScoped;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

@ApplicationScoped
/* renamed from: X.0pN  reason: invalid class name */
public final class AnonymousClass0pN implements AbstractC01320Qc {
    public static final Map<Integer, Object> A03 = Collections.synchronizedMap(new HashMap());
    public final Context A00;
    public final AnonymousClass008 A01 = new AnonymousClass008(this.A02, this);
    public final AnonymousClass0J2 A02;

    public static final void A00(AnonymousClass0QF r2) {
        List<Context> list = r2.A01;
        Preconditions.checkState(!list.isEmpty());
        list.remove(list.size() - 1);
        r2.A01();
    }

    public final AnonymousClass0QF A01() {
        AnonymousClass0QF injectorThreadStack = this.A02.getInjectorThreadStack();
        injectorThreadStack.A01.add(injectorThreadStack.A00);
        injectorThreadStack.A02.add(this.A01);
        return injectorThreadStack;
    }

    @Override // X.AbstractC01320Qc
    public final <T> Provider<T> A8U(C09160zY<T> r2, Provider<T> provider) {
        return new AnonymousClass091(this, provider);
    }

    public AnonymousClass0pN(AnonymousClass0J2 r3) {
        this.A02 = r3;
        this.A00 = r3.getInjectorThreadStack().A00();
    }
}
