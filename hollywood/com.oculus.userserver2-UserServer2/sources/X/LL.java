package X;

import com.google.gson.ExclusionStrategy;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public final class LL implements AbstractC0237hg {
    public final AbstractC0247hs A00;
    public final C0232hV A01;
    public final h7 A02 = h7.A00;
    public final C0086Lh A03;
    public final LU A04;

    private final boolean A00(Field field, boolean z) {
        List<ExclusionStrategy> list;
        C0086Lh lh = this.A03;
        Class<?> type = field.getType();
        if (!Enum.class.isAssignableFrom(type) && (type.isAnonymousClass() || type.isLocalClass())) {
            return false;
        }
        C0086Lh.A00(lh, z);
        if ((136 & field.getModifiers()) != 0 || field.isSynthetic()) {
            return false;
        }
        Class<?> type2 = field.getType();
        if (!Enum.class.isAssignableFrom(type2) && (type2.isAnonymousClass() || type2.isLocalClass())) {
            return false;
        }
        if (z) {
            list = lh.A01;
        } else {
            list = lh.A00;
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
    @Override // X.AbstractC0237hg
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> X.hh<T> A1F(X.C0246hr r33, X.h6<T> r34) {
        /*
        // Method dump skipped, instructions count: 299
        */
        throw new UnsupportedOperationException("Method not decompiled: X.LL.A1F(X.hr, X.h6):X.hh");
    }

    public LL(C0232hV hVVar, AbstractC0247hs hsVar, C0086Lh lh, LU lu) {
        this.A01 = hVVar;
        this.A00 = hsVar;
        this.A03 = lh;
        this.A04 = lu;
    }
}
