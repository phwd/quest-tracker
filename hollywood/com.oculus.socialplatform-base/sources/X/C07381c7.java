package X;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: X.1c7  reason: invalid class name and case insensitive filesystem */
public final class C07381c7 {
    public static final AbstractC07011bT<Object, Object> A04 = new C07371c6();
    public static final C07411cA A05 = new C07411cA();
    public final AnonymousClass06o<List<Throwable>> A00;
    public final C07411cA A01;
    public final List<C07391c8<?, ?>> A02 = new ArrayList();
    public final Set<C07391c8<?, ?>> A03 = new HashSet();

    @NonNull
    public final synchronized <Model, Data> AbstractC07011bT<Model, Data> A00(@NonNull Class<Model> cls, @NonNull Class<Data> cls2) {
        AbstractC07011bT<Model, Data> r0;
        try {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (C07391c8<?, ?> r5 : this.A02) {
                Set<C07391c8<?, ?>> set = this.A03;
                if (set.contains(r5)) {
                    z = true;
                } else if (r5.A02.isAssignableFrom(cls) && r5.A01.isAssignableFrom(cls2)) {
                    set.add(r5);
                    AbstractC07011bT<? extends Model, ? extends Data> A1o = r5.A00.A1o(this);
                    AnonymousClass1S2.A00(A1o);
                    arrayList.add(A1o);
                    set.remove(r5);
                }
            }
            if (arrayList.size() > 1) {
                r0 = new C07031bV(arrayList, this.A00);
            } else if (arrayList.size() == 1) {
                r0 = (AbstractC07011bT) arrayList.get(0);
            } else if (z) {
                r0 = (AbstractC07011bT<Model, Data>) A04;
            } else {
                throw new C07421cB((Class<?>) cls, (Class<?>) cls2);
            }
        } catch (Throwable th) {
            this.A03.clear();
            throw th;
        }
        return r0;
    }

    public C07381c7(@NonNull AnonymousClass06o<List<Throwable>> r3) {
        C07411cA r1 = A05;
        this.A00 = r3;
        this.A01 = r1;
    }
}
