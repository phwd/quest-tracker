package X;

import com.google.gson.ExclusionStrategy;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Tj implements AbstractC0132Os, Cloneable {
    public static final Tj A02 = new Tj();
    public List<ExclusionStrategy> A00 = Collections.emptyList();
    public List<ExclusionStrategy> A01 = Collections.emptyList();

    @Override // X.AbstractC0132Os
    public final <T> AbstractC0131Ob<T> A1e(HY hy, lQ<T> lQVar) {
        Class<? super T> cls = lQVar.rawType;
        if (!Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass())) {
            return new Tk(this, true, true, hy, lQVar);
        }
        A00(this, true);
        A00(this, false);
        return null;
    }

    /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/Class<*>;Z)Z */
    public static void A00(Tj tj, boolean z) {
        List<ExclusionStrategy> list;
        if (z) {
            list = tj.A01;
        } else {
            list = tj.A00;
        }
        Iterator<ExclusionStrategy> it = list.iterator();
        if (it.hasNext()) {
            it.next();
            throw null;
        }
    }

    @Override // java.lang.Object
    public final Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
