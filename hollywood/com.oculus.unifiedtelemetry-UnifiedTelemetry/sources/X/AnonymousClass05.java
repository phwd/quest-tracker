package X;

import android.content.Context;
import com.google.common.base.Preconditions;
import java.util.List;

/* renamed from: X.05  reason: invalid class name */
public final class AnonymousClass05 extends AnonymousClass0U implements AbstractC0094Hs, QB {
    public final Context A00;

    @Override // X.Y0
    public final void A20(Object obj) {
        QF qf = (QF) obj;
        List<AbstractC0094Hs> list = qf.A02;
        Preconditions.checkState(!list.isEmpty());
        list.remove(list.size() - 1);
        List<Context> list2 = qf.A01;
        Preconditions.checkState(!list2.isEmpty());
        list2.remove(list2.size() - 1);
    }

    public AnonymousClass05(AbstractC0096Hu hu, Context context) {
        super(hu);
        this.A00 = context;
    }

    @Override // X.Y0
    public final /* bridge */ /* synthetic */ Object A1y() {
        QF injectorThreadStack = getInjectorThreadStack();
        injectorThreadStack.A01.add(A2W());
        injectorThreadStack.A02.add(this);
        return injectorThreadStack;
    }

    @Override // X.AbstractC0094Hs
    public final Context A2W() {
        return this.A00;
    }
}
