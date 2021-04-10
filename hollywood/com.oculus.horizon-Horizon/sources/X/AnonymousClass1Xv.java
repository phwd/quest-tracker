package X;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@TargetApi(18)
/* renamed from: X.1Xv  reason: invalid class name */
public final class AnonymousClass1Xv implements AnonymousClass1yB {
    public long A00;
    public AnonymousClass1X9 A01;
    public AnonymousClass1XI A02;
    public DataOutputStream A03;
    public boolean A04;

    private synchronized void A00() throws IOException {
        DataOutputStream dataOutputStream = this.A03;
        if (dataOutputStream != null) {
            dataOutputStream.flush();
            this.A03.close();
        }
    }

    @Override // X.AnonymousClass1yB
    public final synchronized void A1o(String str) throws IOException {
        this.A04 = false;
        this.A00 = 0;
        throw null;
    }

    @Override // X.AnonymousClass1yB
    public final synchronized boolean A9Q() throws IOException, IllegalStateException {
        A00();
        return true;
    }

    @Override // X.AnonymousClass1yB
    public final synchronized void AAB(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws IOException {
        AnonymousClass1Xx.A01(byteBuffer, bufferInfo, false, this.A03);
    }

    @Override // X.AnonymousClass1yB
    public final synchronized void AAJ(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws IOException {
        AnonymousClass1XI r1;
        if ((bufferInfo.flags & 2) == 0) {
            long j = bufferInfo.presentationTimeUs;
            long j2 = this.A00;
            if (j2 == 0) {
                this.A00 = j;
            } else if (j - j2 > ((long) this.A01.A04)) {
                this.A00 = j;
                boolean z = !this.A04;
                this.A04 = z;
                A00();
                if (z) {
                    r1 = new AnonymousClass1XI("tmp_b.mp4");
                } else {
                    r1 = new AnonymousClass1XI("tmp_a.mp4");
                }
                this.A02 = r1;
                if (!r1.exists()) {
                    this.A02.createNewFile();
                }
                this.A03 = new DataOutputStream(new BufferedOutputStream(new AnonymousClass1XT(this.A02)));
            }
            AnonymousClass1Xx.A01(byteBuffer, bufferInfo, true, this.A03);
        }
    }

    @Override // X.AnonymousClass1yB
    public final void start() throws IOException {
    }

    @Override // X.AnonymousClass1yB
    public final void A8b(MediaFormat mediaFormat) {
        this.A01.A01 = mediaFormat;
    }

    @Override // X.AnonymousClass1yB
    public final void A8h(int i) {
        this.A01.A00 = i;
    }

    @Override // X.AnonymousClass1yB
    public final void A8t(MediaFormat mediaFormat) {
        this.A01.A02 = mediaFormat;
    }

    public AnonymousClass1Xv(AnonymousClass1X9 r1) {
        this.A01 = r1;
    }
}
