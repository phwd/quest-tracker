package X;

import androidx.annotation.Nullable;
import java.lang.reflect.Array;

/* renamed from: X.3B  reason: invalid class name */
public abstract class AnonymousClass3B<K, V> {
    @Nullable
    public AnonymousClass3B<K, V>.EntrySet A00;
    @Nullable
    public AnonymousClass3B<K, V>.KeySet A01;
    @Nullable
    public AnonymousClass3B<K, V>.ValuesCollection A02;

    public final int A00() {
        if (!(this instanceof UA)) {
            return ((AnonymousClass3C) ((UC) this).A00).A00;
        }
        return ((UA) this).A00.A00;
    }

    public final int A01(Object obj) {
        if (!(this instanceof UA)) {
            return ((UC) this).A00.A04(obj);
        }
        AnonymousClass30 r1 = ((UA) this).A00;
        if (obj == null) {
            return AnonymousClass30.A00(r1);
        }
        return AnonymousClass30.A01(r1, obj, obj.hashCode());
    }

    public final int A02(Object obj) {
        if (!(this instanceof UA)) {
            return ((UC) this).A00.A05(obj);
        }
        AnonymousClass30 r1 = ((UA) this).A00;
        if (obj == null) {
            return AnonymousClass30.A00(r1);
        }
        return AnonymousClass30.A01(r1, obj, obj.hashCode());
    }

    public final Object A03(int i, int i2) {
        if (!(this instanceof UA)) {
            return ((UC) this).A00.A02[(i << 1) + i2];
        }
        return ((UA) this).A00.A03[i];
    }

    public final void A04() {
        if (!(this instanceof UA)) {
            ((UC) this).A00.clear();
        } else {
            ((UA) this).A00.clear();
        }
    }

    public final void A05(int i) {
        if (!(this instanceof UA)) {
            ((UC) this).A00.A06(i);
        } else {
            ((UA) this).A00.A04(i);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v7, resolved type: T[] */
    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T[] A06(T[] tArr, int i) {
        int A002 = A00();
        if (tArr.length < A002) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), A002));
        }
        for (int i2 = 0; i2 < A002; i2++) {
            tArr[i2] = A03(i2, i);
        }
        if (tArr.length > A002) {
            tArr[A002] = null;
        }
        return tArr;
    }
}
