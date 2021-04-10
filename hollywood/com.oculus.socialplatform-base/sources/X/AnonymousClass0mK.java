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
/* renamed from: X.0mK  reason: invalid class name */
public final class AnonymousClass0mK implements AbstractC01120Rf {
    public static final Map<Integer, Object> A03 = Collections.synchronizedMap(new HashMap());
    public final Context A00;
    public final AnonymousClass00B A01 = new AnonymousClass00B(this.A02, this);
    public final AnonymousClass0VF A02;

    public static final void A00(AnonymousClass0RH r2) {
        List<Context> list = r2.A01;
        Preconditions.checkState(!list.isEmpty());
        list.remove(list.size() - 1);
        r2.A01();
    }

    public final AnonymousClass0RH A01() {
        AnonymousClass0RH injectorThreadStack = this.A02.getInjectorThreadStack();
        injectorThreadStack.A01.add(injectorThreadStack.A00);
        injectorThreadStack.A02.add(this.A01);
        return injectorThreadStack;
    }

    @Override // X.AbstractC01120Rf
    public final <T> Provider<T> A9W(AnonymousClass14P<T> r2, Provider<T> provider) {
        return new C00620Hu(this, provider);
    }

    public AnonymousClass0mK(AnonymousClass0VF r3) {
        this.A02 = r3;
        this.A00 = r3.getInjectorThreadStack().A00();
    }
}
