package X;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.Arrays;
import org.pytorch.Tensor;

/* renamed from: X.qW  reason: case insensitive filesystem */
public final class C1003qW extends Tensor {
    public final FloatBuffer A00;

    public final String toString() {
        return String.format("Tensor(%s, dtype=torch.float32)", Arrays.toString(this.shape));
    }

    @Override // org.pytorch.Tensor
    public final float[] getDataAsFloatArray() {
        FloatBuffer floatBuffer = this.A00;
        floatBuffer.rewind();
        float[] fArr = new float[floatBuffer.remaining()];
        floatBuffer.get(fArr);
        return fArr;
    }

    public C1003qW(FloatBuffer floatBuffer, long[] jArr, EnumC0615d0 d0Var) {
        super(jArr, d0Var);
        this.A00 = floatBuffer;
    }

    @Override // org.pytorch.Tensor
    public final EnumC0612cw dtype() {
        return EnumC0612cw.FLOAT32;
    }

    @Override // org.pytorch.Tensor
    public final Buffer getRawDataBuffer() {
        return this.A00;
    }
}
