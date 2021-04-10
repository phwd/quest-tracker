package androidx.lifecycle;

import X.AbstractC01030Da;
import X.AbstractC03550cd;
import X.AnonymousClass0DR;
import X.AnonymousClass0DS;
import X.AnonymousClass0DT;
import X.AnonymousClass0DW;
import androidx.annotation.NonNull;
import java.util.List;
import java.util.Map;

public class ReflectiveGenericLifecycleObserver implements AbstractC03550cd {
    public final AnonymousClass0DR A00;
    public final Object A01;

    @Override // X.AbstractC03550cd
    public final void A6c(@NonNull AbstractC01030Da r4, @NonNull AnonymousClass0DW r5) {
        AnonymousClass0DR r0 = this.A00;
        Object obj = this.A01;
        Map<AnonymousClass0DW, List<AnonymousClass0DS>> map = r0.A01;
        AnonymousClass0DR.A00(map.get(r5), r4, r5, obj);
        AnonymousClass0DR.A00(map.get(AnonymousClass0DW.ON_ANY), r4, r5, obj);
    }

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.A01 = obj;
        AnonymousClass0DT r2 = AnonymousClass0DT.A02;
        Class<?> cls = obj.getClass();
        AnonymousClass0DR r0 = r2.A00.get(cls);
        this.A00 = r0 == null ? AnonymousClass0DT.A00(r2, cls, null) : r0;
    }
}
