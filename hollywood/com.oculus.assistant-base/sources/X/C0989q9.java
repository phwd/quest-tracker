package X;

import java.nio.Buffer;
import java.nio.LongBuffer;
import java.util.Arrays;
import org.pytorch.Tensor;

/* renamed from: X.q9  reason: case insensitive filesystem */
public final class C0989q9 extends Tensor {
    public final LongBuffer A00;

    public final String toString() {
        return String.format("Tensor(%s, dtype=torch.int64)", Arrays.toString(this.shape));
    }

    public C0989q9(LongBuffer longBuffer, long[] jArr, EnumC0615d0 d0Var) {
        super(jArr, d0Var);
        this.A00 = longBuffer;
    }

    @Override // org.pytorch.Tensor
    public final EnumC0612cw dtype() {
        return EnumC0612cw.INT64;
    }

    @Override // org.pytorch.Tensor
    public final Buffer getRawDataBuffer() {
        return this.A00;
    }
}
