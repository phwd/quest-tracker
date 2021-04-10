package X;

import android.util.SparseIntArray;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1hz  reason: invalid class name and case insensitive filesystem */
public class C09451hz extends AbstractC09401hs<byte[]> implements AnonymousClass0VT {
    public final int[] A00;

    public C09451hz(AnonymousClass0JS r5, AnonymousClass1i0 r6, AnonymousClass1i3 r7) {
        super(r5, r6, r7);
        SparseIntArray sparseIntArray = r6.A03;
        this.A00 = new int[sparseIntArray.size()];
        for (int i = 0; i < sparseIntArray.size(); i++) {
            this.A00[i] = sparseIntArray.keyAt(i);
        }
    }
}
