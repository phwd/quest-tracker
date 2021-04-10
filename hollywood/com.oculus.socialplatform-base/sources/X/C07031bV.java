package X;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: X.1bV  reason: invalid class name and case insensitive filesystem */
public final class C07031bV<Model, Data> implements AbstractC07011bT<Model, Data> {
    public final AnonymousClass06o<List<Throwable>> A00;
    public final List<AbstractC07011bT<Model, Data>> A01;

    @Override // X.AbstractC07011bT
    public final C07091bb<Data> A1r(@NonNull Model model, int i, int i2, @NonNull AnonymousClass1cO r12) {
        C07091bb<Data> A1r;
        List<AbstractC07011bT<Model, Data>> list = this.A01;
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        AbstractC06491aL r2 = null;
        for (int i3 = 0; i3 < size; i3++) {
            AbstractC07011bT<Model, Data> r1 = list.get(i3);
            if (r1.A5N(model) && (A1r = r1.A1r(model, i, i2, r12)) != null) {
                r2 = A1r.A00;
                arrayList.add(A1r.A01);
            }
        }
        if (arrayList.isEmpty() || r2 == null) {
            return null;
        }
        return new C07091bb<>(r2, new C07041bW(arrayList, this.A00));
    }

    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull Model model) {
        for (AbstractC07011bT<Model, Data> r0 : this.A01) {
            if (r0.A5N(model)) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MultiModelLoader{modelLoaders=");
        sb.append(Arrays.toString(this.A01.toArray()));
        sb.append('}');
        return sb.toString();
    }

    public C07031bV(@NonNull List<AbstractC07011bT<Model, Data>> list, @NonNull AnonymousClass06o<List<Throwable>> r2) {
        this.A01 = list;
        this.A00 = r2;
    }
}
