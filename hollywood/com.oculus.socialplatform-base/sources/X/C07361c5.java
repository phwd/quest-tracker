package X;

import java.nio.ByteBuffer;

/* renamed from: X.1c5  reason: invalid class name and case insensitive filesystem */
public class C07361c5 implements AbstractC07341c1<ByteBuffer> {
    public final /* synthetic */ C07351c4 A00;

    public C07361c5(C07351c4 r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC07341c1
    public final Class<ByteBuffer> A3h() {
        return ByteBuffer.class;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC07341c1
    public final ByteBuffer A2H(byte[] bArr) {
        return ByteBuffer.wrap(bArr);
    }
}
