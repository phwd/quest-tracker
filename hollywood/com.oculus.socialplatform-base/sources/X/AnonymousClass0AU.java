package X;

import androidx.lifecycle.CompositeGeneratedAdaptersObserver;
import androidx.lifecycle.ReflectiveGenericLifecycleObserver;
import androidx.lifecycle.SingleGeneratedAdapterObserver;
import java.lang.reflect.Constructor;
import java.util.List;

/* renamed from: X.0AU  reason: invalid class name */
public class AnonymousClass0AU {
    public AbstractC05230uw A00;
    public AnonymousClass0AP A01;

    public AnonymousClass0AU(AnonymousClass0AR r5, AnonymousClass0AP r6) {
        AbstractC05230uw reflectiveGenericLifecycleObserver;
        if (r5 instanceof AbstractC05230uw) {
            reflectiveGenericLifecycleObserver = (AbstractC05230uw) r5;
        } else {
            Class<?> cls = r5.getClass();
            if (AnonymousClass0AV.A00(cls) == 2) {
                List<Constructor<? extends AnonymousClass0AN>> list = AnonymousClass0AV.A00.get(cls);
                if (list.size() == 1) {
                    AnonymousClass0AV.A01(list.get(0), r5);
                    reflectiveGenericLifecycleObserver = new SingleGeneratedAdapterObserver();
                } else {
                    AnonymousClass0AN[] r1 = new AnonymousClass0AN[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        r1[i] = AnonymousClass0AV.A01(list.get(i), r5);
                    }
                    reflectiveGenericLifecycleObserver = new CompositeGeneratedAdaptersObserver(r1);
                }
            } else {
                reflectiveGenericLifecycleObserver = new ReflectiveGenericLifecycleObserver(r5);
            }
        }
        this.A00 = reflectiveGenericLifecycleObserver;
        this.A01 = r6;
    }

    public final void A00(AnonymousClass0AS r4, AnonymousClass0AO r5) {
        AnonymousClass0AP A012 = AnonymousClass0uv.A01(r5);
        AnonymousClass0AP r1 = this.A01;
        if (A012 != null && A012.compareTo((Enum) r1) < 0) {
            r1 = A012;
        }
        this.A01 = r1;
        this.A00.A87(r4, r5);
        this.A01 = A012;
    }
}
