package X;

import android.content.Context;
import com.google.common.base.Preconditions;
import java.util.List;

/* renamed from: X.00A  reason: invalid class name */
public final class AnonymousClass00A extends AbstractC000300x implements AnonymousClass0VD, AnonymousClass0RD {
    public final Context A00;

    @Override // X.AbstractC03270lw
    public final void A2u(Object obj) {
        AnonymousClass0RH r3 = (AnonymousClass0RH) obj;
        r3.A01();
        List<Context> list = r3.A01;
        Preconditions.checkState(!list.isEmpty());
        list.remove(list.size() - 1);
    }

    public AnonymousClass00A(AnonymousClass0VF r1, Context context) {
        super(r1);
        this.A00 = context;
    }

    @Override // X.AbstractC03270lw
    public final /* bridge */ /* synthetic */ Object A2s() {
        AnonymousClass0RH injectorThreadStack = getInjectorThreadStack();
        injectorThreadStack.A01.add(A4B());
        injectorThreadStack.A02.add(this);
        return injectorThreadStack;
    }

    @Override // X.AnonymousClass0VD
    public final Context A4B() {
        return this.A00;
    }
}
