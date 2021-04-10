package androidx.lifecycle;

import X.AI;
import X.AJ;
import X.AK;
import X.AN;
import X.AR;
import X.Zx;
import androidx.annotation.NonNull;
import java.util.List;
import java.util.Map;

public class ReflectiveGenericLifecycleObserver implements Zx {
    public final AI A00;
    public final Object A01;

    @Override // X.Zx
    public final void A42(@NonNull AR ar, @NonNull AN an) {
        AI ai = this.A00;
        Object obj = this.A01;
        Map<AN, List<AJ>> map = ai.A01;
        AI.A00(map.get(an), ar, an, obj);
        AI.A00(map.get(AN.ON_ANY), ar, an, obj);
    }

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.A01 = obj;
        AK ak = AK.A02;
        Class<?> cls = obj.getClass();
        AI ai = ak.A00.get(cls);
        this.A00 = ai == null ? AK.A00(ak, cls, null) : ai;
    }
}
