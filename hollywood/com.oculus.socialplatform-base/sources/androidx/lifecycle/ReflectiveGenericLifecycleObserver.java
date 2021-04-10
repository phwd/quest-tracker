package androidx.lifecycle;

import X.AbstractC05230uw;
import X.AnonymousClass0AJ;
import X.AnonymousClass0AK;
import X.AnonymousClass0AL;
import X.AnonymousClass0AO;
import X.AnonymousClass0AS;
import androidx.annotation.NonNull;
import java.util.List;
import java.util.Map;

public class ReflectiveGenericLifecycleObserver implements AbstractC05230uw {
    public final AnonymousClass0AJ A00;
    public final Object A01;

    @Override // X.AbstractC05230uw
    public final void A87(@NonNull AnonymousClass0AS r4, @NonNull AnonymousClass0AO r5) {
        AnonymousClass0AJ r0 = this.A00;
        Object obj = this.A01;
        Map<AnonymousClass0AO, List<AnonymousClass0AK>> map = r0.A01;
        AnonymousClass0AJ.A00(map.get(r5), r4, r5, obj);
        AnonymousClass0AJ.A00(map.get(AnonymousClass0AO.ON_ANY), r4, r5, obj);
    }

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.A01 = obj;
        AnonymousClass0AL r2 = AnonymousClass0AL.A02;
        Class<?> cls = obj.getClass();
        AnonymousClass0AJ r0 = r2.A00.get(cls);
        this.A00 = r0 == null ? AnonymousClass0AL.A00(r2, cls, null) : r0;
    }
}
