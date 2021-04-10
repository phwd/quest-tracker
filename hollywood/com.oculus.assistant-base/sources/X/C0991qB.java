package X;

import java.nio.Buffer;
import java.nio.DoubleBuffer;
import java.util.Arrays;
import org.pytorch.Tensor;

/* renamed from: X.qB  reason: case insensitive filesystem */
public final class C0991qB extends Tensor {
    public final DoubleBuffer A00;

    public final String toString() {
        return String.format("Tensor(%s, dtype=torch.float64)", Arrays.toString(this.shape));
    }

    public C0991qB(DoubleBuffer doubleBuffer, long[] jArr, EnumC0615d0 d0Var) {
        super(jArr, d0Var);
        this.A00 = doubleBuffer;
    }

    @Override // org.pytorch.Tensor
    public final EnumC0612cw dtype() {
        return EnumC0612cw.FLOAT64;
    }

    @Override // org.pytorch.Tensor
    public final Buffer getRawDataBuffer() {
        return this.A00;
    }
}
