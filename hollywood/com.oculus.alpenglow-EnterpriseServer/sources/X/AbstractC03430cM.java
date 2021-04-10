package X;

import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0cM  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03430cM<T> extends AbstractC01280Fx {
    public abstract void A03(AbstractC03360cA v, T t);

    public final void A04(T[] tArr) {
        AbstractC03360cA A00 = A00();
        try {
            for (T t : tArr) {
                A03(A00, t);
                A00.A2R();
            }
        } finally {
            A02(A00);
        }
    }

    public AbstractC03430cM(AnonymousClass0Fr r1) {
        super(r1);
    }
}
