package org.pytorch;

import X.AnonymousClass08;
import X.EnumC0612cw;
import X.EnumC0615d0;
import com.facebook.jni.HybridData;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Locale;

public abstract class Tensor {
    public HybridData mHybridData;
    public final EnumC0615d0 memoryFormat;
    public final long[] shape;

    public abstract EnumC0612cw dtype();

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
        if (r1.jniCode == r6) goto L_0x000e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.pytorch.Tensor nativeNewTensor(java.nio.ByteBuffer r3, long[] r4, int r5, int r6, com.facebook.jni.HybridData r7) {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: org.pytorch.Tensor.nativeNewTensor(java.nio.ByteBuffer, long[], int, int, com.facebook.jni.HybridData):org.pytorch.Tensor");
    }

    public float[] getDataAsFloatArray() {
        throw new IllegalStateException(AnonymousClass08.A05("Tensor of type ", getClass().getSimpleName(), " cannot return data as float array."));
    }

    public Buffer getRawDataBuffer() {
        throw new IllegalStateException(AnonymousClass08.A05("Tensor of type ", getClass().getSimpleName(), " cannot return raw data buffer."));
    }

    public int memoryFormatJniCode() {
        return this.memoryFormat.jniCode;
    }

    public Tensor(long[] jArr, EnumC0615d0 d0Var) {
        Object[] objArr = new Object[0];
        if (jArr != null) {
            int i = 0;
            while (true) {
                int length = jArr.length;
                if (i < length) {
                    Object[] objArr2 = new Object[0];
                    if (jArr[i] >= 0) {
                        i++;
                    } else {
                        throw new IllegalArgumentException(String.format(Locale.US, "Shape elements must be non negative", objArr2));
                    }
                } else {
                    this.shape = Arrays.copyOf(jArr, length);
                    this.memoryFormat = d0Var;
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "Shape must be not null", objArr));
        }
    }

    public int dtypeJniCode() {
        return dtype().jniCode;
    }
}
