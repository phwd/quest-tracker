package X;

import android.util.SparseIntArray;
import com.facebook.imagepipeline.memory.NativeMemoryChunk;
import com.facebook.imagepipeline.memory.NativeMemoryChunkPool;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1rK  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC10131rK extends AnonymousClass1rI<AbstractC10321rv> {
    public final int[] A00;

    public AbstractC10321rv A07(int i) {
        if (!(this instanceof NativeMemoryChunkPool)) {
            return new C10011qo(i);
        }
        return new NativeMemoryChunk(i);
    }

    public AbstractC10131rK(AbstractC10671uj r5, C10471su r6, AbstractC10691uo r7) {
        super(r5, r6, r7);
        SparseIntArray sparseIntArray = r6.A03;
        this.A00 = new int[sparseIntArray.size()];
        int i = 0;
        while (true) {
            int[] iArr = this.A00;
            if (i < iArr.length) {
                iArr[i] = sparseIntArray.keyAt(i);
                i++;
            } else {
                return;
            }
        }
    }
}
