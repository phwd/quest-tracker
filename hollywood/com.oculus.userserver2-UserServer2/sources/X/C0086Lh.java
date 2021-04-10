package X;

import com.google.gson.ExclusionStrategy;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.Lh  reason: case insensitive filesystem */
public final class C0086Lh implements AbstractC0237hg, Cloneable {
    public static final C0086Lh A02 = new C0086Lh();
    public List<ExclusionStrategy> A00 = Collections.emptyList();
    public List<ExclusionStrategy> A01 = Collections.emptyList();

    @Override // X.AbstractC0237hg
    public final <T> hh<T> A1F(C0246hr hrVar, h6<T> h6Var) {
        Class<? super T> cls = h6Var.rawType;
        if (!Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass())) {
            return new C0087Li(this, true, true, hrVar, h6Var);
        }
        A00(this, true);
        A00(this, false);
        return null;
    }

    /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/Class<*>;Z)Z */
    public static void A00(C0086Lh lh, boolean z) {
        List<ExclusionStrategy> list;
        if (z) {
            list = lh.A01;
        } else {
            list = lh.A00;
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
