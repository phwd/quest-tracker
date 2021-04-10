package X;

import com.google.gson.ExclusionStrategy;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public final class T5 implements AbstractC0132Os {
    public final HX A00;
    public final TW A01;
    public final AbstractC0448kW A02 = AbstractC0448kW.A00;
    public final Tj A03;
    public final TP A04;

    private final boolean A00(Field field, boolean z) {
        List<ExclusionStrategy> list;
        Tj tj = this.A03;
        Class<?> type = field.getType();
        if (!Enum.class.isAssignableFrom(type) && (type.isAnonymousClass() || type.isLocalClass())) {
            return false;
        }
        Tj.A00(tj, z);
        if ((136 & field.getModifiers()) != 0 || field.isSynthetic()) {
            return false;
        }
        Class<?> type2 = field.getType();
        if (!Enum.class.isAssignableFrom(type2) && (type2.isAnonymousClass() || type2.isLocalClass())) {
            return false;
        }
        if (z) {
            list = tj.A01;
        } else {
            list = tj.A00;
        }
        if (list.isEmpty()) {
            return true;
        }
        Iterator<ExclusionStrategy> it = list.iterator();
        if (!it.hasNext()) {
            return true;
        }
        it.next();
        throw null;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:52:0x006f */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.AbstractCollection, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.List] */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a6, code lost:
        if (r26 == null) goto L_0x00a8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ed A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0043 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // X.AbstractC0132Os
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> X.AbstractC0131Ob<T> A1e(X.HY r33, X.lQ<T> r34) {
        /*
        // Method dump skipped, instructions count: 299
        */
        throw new UnsupportedOperationException("Method not decompiled: X.T5.A1e(X.HY, X.lQ):X.Ob");
    }

    public T5(TW tw, HX hx, Tj tj, TP tp) {
        this.A01 = tw;
        this.A00 = hx;
        this.A03 = tj;
        this.A04 = tp;
    }
}
