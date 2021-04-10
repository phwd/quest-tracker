package X;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import org.pytorch.Tensor;

/* renamed from: X.qA  reason: case insensitive filesystem */
public final class C0990qA extends Tensor {
    public final IntBuffer A00;

    public final String toString() {
        return String.format("Tensor(%s, dtype=torch.int32)", Arrays.toString(this.shape));
    }

    public C0990qA(IntBuffer intBuffer, long[] jArr, EnumC0615d0 d0Var) {
        super(jArr, d0Var);
        this.A00 = intBuffer;
    }

    @Override // org.pytorch.Tensor
    public final EnumC0612cw dtype() {
        return EnumC0612cw.INT32;
    }

    @Override // org.pytorch.Tensor
    public final Buffer getRawDataBuffer() {
        return this.A00;
    }
}
