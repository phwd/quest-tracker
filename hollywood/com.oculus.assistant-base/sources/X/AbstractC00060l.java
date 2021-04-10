package X;

import java.lang.reflect.Array;

/* renamed from: X.0l  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC00060l {
    public C00030h A00;
    public C00040i A01;
    public C00050k A02;

    public final int A00() {
        if (!(this instanceof C0627dR)) {
            return ((AnonymousClass0m) ((C0625dP) this).A00).A00;
        }
        return ((C0627dR) this).A00.A00;
    }

    public final int A01(Object obj) {
        if (!(this instanceof C0627dR)) {
            return ((C0625dP) this).A00.A04(obj);
        }
        C00010d r1 = ((C0627dR) this).A00;
        if (obj == null) {
            return C00010d.A00(r1);
        }
        return C00010d.A01(r1, obj, obj.hashCode());
    }

    public final int A02(Object obj) {
        if (!(this instanceof C0627dR)) {
            return ((C0625dP) this).A00.A05(obj);
        }
        C00010d r1 = ((C0627dR) this).A00;
        if (obj == null) {
            return C00010d.A00(r1);
        }
        return C00010d.A01(r1, obj, obj.hashCode());
    }

    public final Object A03(int i, int i2) {
        if (!(this instanceof C0627dR)) {
            return ((C0625dP) this).A00.A02[(i << 1) + i2];
        }
        return ((C0627dR) this).A00.A03[i];
    }

    public final void A04() {
        if (!(this instanceof C0627dR)) {
            ((C0625dP) this).A00.clear();
        } else {
            ((C0627dR) this).A00.clear();
        }
    }

    public final void A05(int i) {
        if (!(this instanceof C0627dR)) {
            ((C0625dP) this).A00.A06(i);
        } else {
            ((C0627dR) this).A00.A04(i);
        }
    }

    public final Object[] A06(Object[] objArr, int i) {
        int A002 = A00();
        if (objArr.length < A002) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), A002);
        }
        for (int i2 = 0; i2 < A002; i2++) {
            objArr[i2] = A03(i2, i);
        }
        if (objArr.length > A002) {
            objArr[A002] = null;
        }
        return objArr;
    }
}
