package androidx.lifecycle;

import X.AbstractC07290ro;
import X.AnonymousClass0AI;
import X.AnonymousClass0AJ;
import X.AnonymousClass0AK;
import X.AnonymousClass0AN;
import X.AnonymousClass0AR;
import androidx.annotation.NonNull;
import java.util.List;
import java.util.Map;

public class ReflectiveGenericLifecycleObserver implements AbstractC07290ro {
    public final AnonymousClass0AI A00;
    public final Object A01;

    @Override // X.AbstractC07290ro
    public final void A70(@NonNull AnonymousClass0AR r4, @NonNull AnonymousClass0AN r5) {
        AnonymousClass0AI r0 = this.A00;
        Object obj = this.A01;
        Map<AnonymousClass0AN, List<AnonymousClass0AJ>> map = r0.A01;
        AnonymousClass0AI.A00(map.get(r5), r4, r5, obj);
        AnonymousClass0AI.A00(map.get(AnonymousClass0AN.ON_ANY), r4, r5, obj);
    }

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.A01 = obj;
        AnonymousClass0AK r2 = AnonymousClass0AK.A02;
        Class<?> cls = obj.getClass();
        AnonymousClass0AI r0 = r2.A00.get(cls);
        this.A00 = r0 == null ? AnonymousClass0AK.A00(r2, cls, null) : r0;
    }
}
