package X;

import java.nio.ByteBuffer;

/* renamed from: X.fj  reason: case insensitive filesystem */
public final class C0703fj implements AbstractC0105Aj {
    public final /* synthetic */ C0740gP A00;

    public C0703fj(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C00929w.A00.A00("OacrTtsAudioDataMessage", ai.A00);
        C0818iN iNVar = (C0818iN) ak.A2L();
        ByteBuffer byteBuffer = iNVar.A01;
        int limit = byteBuffer.limit();
        byte[] bArr = new byte[limit];
        byteBuffer.get(bArr);
        l0 l0Var = (l0) iNVar.A00;
        if (l0Var != null) {
            l0Var.A06(bArr, 0, limit);
        }
    }
}
