package X;

import android.content.Context;
import com.google.common.base.Preconditions;
import java.util.List;

/* renamed from: X.05  reason: invalid class name */
public final class AnonymousClass05 extends AnonymousClass0S implements BX, Ol {
    public final Context A00;

    @Override // X.Sf
    public final void A1W(Object obj) {
        Op op = (Op) obj;
        List<BX> list = op.A02;
        Preconditions.checkState(!list.isEmpty());
        list.remove(list.size() - 1);
        List<Context> list2 = op.A01;
        Preconditions.checkState(!list2.isEmpty());
        list2.remove(list2.size() - 1);
    }

    public AnonymousClass05(BZ bz, Context context) {
        super(bz);
        this.A00 = context;
    }

    @Override // X.Sf
    public final /* bridge */ /* synthetic */ Object A1U() {
        Op injectorThreadStack = getInjectorThreadStack();
        injectorThreadStack.A01.add(A1j());
        injectorThreadStack.A02.add(this);
        return injectorThreadStack;
    }

    @Override // X.BX
    public final Context A1j() {
        return this.A00;
    }
}
