package androidx.lifecycle;

import X.Bj;
import X.Bs;
import X.C0036Bk;
import X.C0037Bl;
import X.EnumC0039Bo;
import X.Td;
import androidx.annotation.NonNull;
import java.util.List;
import java.util.Map;

public class ReflectiveGenericLifecycleObserver implements Td {
    public final Bj A00;
    public final Object A01;

    @Override // X.Td
    public final void A2i(@NonNull Bs bs, @NonNull EnumC0039Bo bo) {
        Bj bj = this.A00;
        Object obj = this.A01;
        Map<EnumC0039Bo, List<C0036Bk>> map = bj.A01;
        Bj.A00(map.get(bo), bs, bo, obj);
        Bj.A00(map.get(EnumC0039Bo.ON_ANY), bs, bo, obj);
    }

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.A01 = obj;
        C0037Bl bl = C0037Bl.A02;
        Class<?> cls = obj.getClass();
        Bj bj = bl.A00.get(cls);
        this.A00 = bj == null ? C0037Bl.A00(bl, cls, null) : bj;
    }
}
