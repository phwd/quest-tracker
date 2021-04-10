package X;

import android.content.Context;
import com.facebook.inject.ApplicationScoped;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public final class Y2 implements AbstractC0133Qc {
    public static final Map<Integer, Object> A03 = Collections.synchronizedMap(new HashMap());
    public final Context A00;
    public final AnonymousClass08 A01 = new AnonymousClass08(this.A02, this);
    public final AbstractC0096Hu A02;

    public static final void A00(QF qf) {
        List<Context> list = qf.A01;
        Preconditions.checkState(!list.isEmpty());
        list.remove(list.size() - 1);
        List<AbstractC0094Hs> list2 = qf.A02;
        Preconditions.checkState(!list2.isEmpty());
        list2.remove(list2.size() - 1);
    }

    public final QF A01() {
        QF injectorThreadStack = this.A02.getInjectorThreadStack();
        injectorThreadStack.A01.add(injectorThreadStack.A00);
        injectorThreadStack.A02.add(this.A01);
        return injectorThreadStack;
    }

    public Y2(AbstractC0096Hu hu) {
        this.A02 = hu;
        this.A00 = hu.getInjectorThreadStack().A00();
    }
}
