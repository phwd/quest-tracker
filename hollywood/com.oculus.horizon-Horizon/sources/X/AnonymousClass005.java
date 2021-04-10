package X;

import android.content.Context;
import com.google.common.base.Preconditions;
import java.util.List;

/* renamed from: X.005  reason: invalid class name */
public final class AnonymousClass005 extends AnonymousClass00W implements AnonymousClass0Iy, AnonymousClass0QB {
    public final Context A00;

    @Override // X.AnonymousClass0pL
    public final void A2b(Object obj) {
        AnonymousClass0QF r3 = (AnonymousClass0QF) obj;
        r3.A01();
        List<Context> list = r3.A01;
        Preconditions.checkState(!list.isEmpty());
        list.remove(list.size() - 1);
    }

    public AnonymousClass005(AnonymousClass0J2 r1, Context context) {
        super(r1);
        this.A00 = context;
    }

    @Override // X.AnonymousClass0pL
    public final /* bridge */ /* synthetic */ Object A2Y() {
        AnonymousClass0QF injectorThreadStack = getInjectorThreadStack();
        injectorThreadStack.A01.add(A3Z());
        injectorThreadStack.A02.add(this);
        return injectorThreadStack;
    }

    @Override // X.AnonymousClass0Iy
    public final Context A3Z() {
        return this.A00;
    }
}
