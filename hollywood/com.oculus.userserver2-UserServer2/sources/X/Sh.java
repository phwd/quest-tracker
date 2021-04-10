package X;

import android.content.Context;
import com.facebook.inject.ApplicationScoped;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public final class Sh implements PC {
    public static final Map<Integer, Object> A03 = Collections.synchronizedMap(new HashMap());
    public final Context A00;
    public final AnonymousClass08 A01 = new AnonymousClass08(this.A02, this);
    public final BZ A02;

    public static final void A00(Op op) {
        List<Context> list = op.A01;
        Preconditions.checkState(!list.isEmpty());
        list.remove(list.size() - 1);
        List<BX> list2 = op.A02;
        Preconditions.checkState(!list2.isEmpty());
        list2.remove(list2.size() - 1);
    }

    public final Op A01() {
        Op injectorThreadStack = this.A02.getInjectorThreadStack();
        injectorThreadStack.A01.add(injectorThreadStack.A00);
        injectorThreadStack.A02.add(this.A01);
        return injectorThreadStack;
    }

    public Sh(BZ bz) {
        this.A02 = bz;
        this.A00 = bz.getInjectorThreadStack().A00();
    }
}
