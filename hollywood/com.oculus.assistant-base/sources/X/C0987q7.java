package X;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.pytorch.Tensor;

/* renamed from: X.q7  reason: case insensitive filesystem */
public final class C0987q7 extends Tensor {
    public final ByteBuffer A00;

    public final String toString() {
        return String.format("Tensor(%s, dtype=torch.uint8)", Arrays.toString(this.shape));
    }

    public C0987q7(ByteBuffer byteBuffer, long[] jArr, EnumC0615d0 d0Var) {
        super(jArr, d0Var);
        this.A00 = byteBuffer;
    }

    @Override // org.pytorch.Tensor
    public final EnumC0612cw dtype() {
        return EnumC0612cw.UINT8;
    }

    @Override // org.pytorch.Tensor
    public final Buffer getRawDataBuffer() {
        return this.A00;
    }
}
