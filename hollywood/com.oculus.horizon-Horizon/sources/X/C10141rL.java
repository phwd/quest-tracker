package X;

import android.util.SparseIntArray;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1rL  reason: invalid class name and case insensitive filesystem */
public class C10141rL extends AnonymousClass1rI<byte[]> implements AnonymousClass0Km {
    public final int[] A00;

    public C10141rL(AbstractC10671uj r5, C10471su r6, AbstractC10691uo r7) {
        super(r5, r6, r7);
        SparseIntArray sparseIntArray = r6.A03;
        this.A00 = new int[sparseIntArray.size()];
        for (int i = 0; i < sparseIntArray.size(); i++) {
            this.A00[i] = sparseIntArray.keyAt(i);
        }
    }
}
