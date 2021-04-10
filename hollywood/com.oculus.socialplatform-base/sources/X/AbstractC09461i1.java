package X;

import android.util.SparseIntArray;
import com.facebook.imagepipeline.memory.NativeMemoryChunk;
import com.facebook.imagepipeline.memory.NativeMemoryChunkPool;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1i1  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09461i1 extends AbstractC09401hs<AnonymousClass1iY> {
    public final int[] A00;

    public AnonymousClass1iY A07(int i) {
        if (!(this instanceof NativeMemoryChunkPool)) {
            return new AnonymousClass1ib(i);
        }
        return new NativeMemoryChunk(i);
    }

    public AbstractC09461i1(AnonymousClass0JS r5, AnonymousClass1i0 r6, AnonymousClass1i3 r7) {
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
