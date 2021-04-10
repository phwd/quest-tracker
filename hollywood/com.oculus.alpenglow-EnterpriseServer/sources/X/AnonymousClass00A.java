package X;

import android.content.Context;
import com.google.common.base.Preconditions;
import java.util.List;

/* renamed from: X.00A  reason: invalid class name */
public final class AnonymousClass00A extends AbstractC006205y implements AnonymousClass0Lf, AnonymousClass0R6 {
    public final Context A00;

    @Override // X.AbstractC03040bP
    public final void A2U(Object obj) {
        AnonymousClass0RA r3 = (AnonymousClass0RA) obj;
        r3.A01();
        List<Context> list = r3.A01;
        Preconditions.checkState(!list.isEmpty());
        list.remove(list.size() - 1);
    }

    @Override // X.AnonymousClass0Lf
    public final Context A3m() {
        return this.A00;
    }

    public AnonymousClass00A(AnonymousClass0Lh r1, Context context) {
        super(r1);
        this.A00 = context;
    }

    @Override // X.AbstractC03040bP
    public final /* bridge */ /* synthetic */ Object A2P() {
        AnonymousClass0RA injectorThreadStack = getInjectorThreadStack();
        injectorThreadStack.A01.add(A3m());
        injectorThreadStack.A02.add(this);
        return injectorThreadStack;
    }
}
