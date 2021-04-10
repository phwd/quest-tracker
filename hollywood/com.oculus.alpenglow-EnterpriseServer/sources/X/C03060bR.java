package X;

import android.content.Context;
import com.facebook.inject.ApplicationScoped;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
/* renamed from: X.0bR  reason: invalid class name and case insensitive filesystem */
public final class C03060bR implements AnonymousClass0RX {
    public static final Map<Integer, Object> A03 = Collections.synchronizedMap(new HashMap());
    public final Context A00;
    public final AnonymousClass00B A01 = new AnonymousClass00B(this.A02, this);
    public final AnonymousClass0Lh A02;

    public static final void A00(AnonymousClass0RA r2) {
        List<Context> list = r2.A01;
        Preconditions.checkState(!list.isEmpty());
        list.remove(list.size() - 1);
        r2.A01();
    }

    public final AnonymousClass0RA A01() {
        AnonymousClass0RA injectorThreadStack = this.A02.getInjectorThreadStack();
        injectorThreadStack.A01.add(injectorThreadStack.A00);
        injectorThreadStack.A02.add(this.A01);
        return injectorThreadStack;
    }

    @Override // X.AnonymousClass0RX
    public final <T> AbstractC07240oz<T> A7Z(C01440Gz<T> r2, AbstractC07240oz<T> r3) {
        return new C01360Gj(this, r3);
    }

    public C03060bR(AnonymousClass0Lh r3) {
        this.A02 = r3;
        this.A00 = r3.getInjectorThreadStack().A00();
    }
}
