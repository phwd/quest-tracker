package X;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* renamed from: X.gX  reason: case insensitive filesystem */
public final class C0747gX implements AbstractC00588i {
    public final /* synthetic */ RunnableC00578h A00;
    public final /* synthetic */ ByteBuffer A01;
    public final /* synthetic */ byte[] A02;

    public C0747gX(RunnableC00578h r1, byte[] bArr, ByteBuffer byteBuffer) {
        this.A00 = r1;
        this.A02 = bArr;
        this.A01 = byteBuffer;
    }

    @Override // X.AbstractC00588i
    public final boolean A4u() {
        C00598j r1 = this.A00.A02;
        synchronized (r1.A06) {
            try {
                int length = this.A02.length;
                if (length > 0) {
                    if (length % 2 == 0) {
                        DataOutputStream dataOutputStream = r1.A03;
                        if (dataOutputStream == null) {
                            C0139Dd.A0A("LastAudioLogger", "DataOutputStream is null.");
                        } else {
                            dataOutputStream.write(this.A01.array());
                        }
                    } else {
                        throw new IOException("Source data must be even to properly write out pcm data");
                    }
                }
            } catch (IOException e) {
                C0139Dd.A0L("LastAudioLogger", "Failed writing to output stream.", e);
            }
        }
        return false;
    }
}
