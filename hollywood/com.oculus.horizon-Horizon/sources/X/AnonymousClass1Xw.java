package X;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

@TargetApi(18)
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1Xw  reason: invalid class name */
public final class AnonymousClass1Xw implements AnonymousClass1yB {
    public int A00 = 0;
    public int A01 = 0;
    public AnonymousClass1X9 A02;
    public boolean A03 = true;
    public boolean A04 = false;
    public final ByteBuffer A05;

    private final synchronized void A03(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo, boolean z) {
        int i;
        if (this.A03) {
            A02(this.A00, this.A05, byteBuffer, bufferInfo, z);
            this.A03 = false;
        } else {
            ByteBuffer byteBuffer2 = this.A05;
            int capacity = byteBuffer2.capacity();
            int A002 = A00(this.A00, byteBuffer2) + 24;
            int i2 = this.A00;
            int i3 = i2 + A002;
            int i4 = bufferInfo.size + 24;
            if (this.A04 || i3 + i4 >= capacity) {
                this.A04 = true;
                int i5 = this.A01;
                int i6 = i5 - i3;
                if (i2 >= i5) {
                    i6 = i5 + (capacity - i3);
                }
                int i7 = i4 - i6;
                while (i7 > 0) {
                    int A003 = A00(i5, byteBuffer2) + 24;
                    i5 = (this.A01 + A003) % capacity;
                    this.A01 = i5;
                    i7 -= A003;
                }
                i = (this.A00 + A002) % capacity;
                this.A00 = i;
            } else {
                i = i3 % capacity;
                this.A00 = i;
            }
            A02(i, byteBuffer2, byteBuffer, bufferInfo, z);
        }
    }

    @Override // X.AnonymousClass1yB
    public final synchronized void A1o(String str) throws IOException {
    }

    @Override // X.AnonymousClass1yB
    public final synchronized boolean A9Q() throws IOException, IllegalStateException {
        AnonymousClass1XI r3;
        byte[] array;
        int i;
        if (this.A03) {
            AnonymousClass0NO.A08("InMemoryUnmuxedRollingBufferMuxerImpl", "buffer is empty");
        } else {
            File file = this.A02.A03;
            if (file == null) {
                AnonymousClass0NO.A08("InMemoryUnmuxedRollingBufferMuxerImpl", "no output file");
            } else {
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        AnonymousClass0NO.A0B("InMemoryUnmuxedRollingBufferMuxerImpl", "couldn't create output file", e);
                    }
                }
                int i2 = this.A00;
                ByteBuffer byteBuffer = this.A05;
                int A002 = A00(i2, byteBuffer) + 24 + this.A00;
                try {
                    if (file instanceof AnonymousClass1XI) {
                        r3 = (AnonymousClass1XI) file;
                    } else {
                        r3 = new AnonymousClass1XI(file);
                    }
                    DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new AnonymousClass1XT(r3)));
                    try {
                        if (this.A01 > this.A00) {
                            dataOutputStream.write(byteBuffer.array(), this.A01, byteBuffer.limit() - this.A01);
                            array = byteBuffer.array();
                            i = 0;
                        } else {
                            array = byteBuffer.array();
                            i = this.A01;
                        }
                        dataOutputStream.write(array, i, A002);
                        dataOutputStream.close();
                    } catch (Throwable unused) {
                    }
                } catch (IOException e2) {
                    AnonymousClass0NO.A0B("InMemoryUnmuxedRollingBufferMuxerImpl", "couldn't write buffer to disk", e2);
                }
            }
        }
        return true;
        throw th;
    }

    @Override // X.AnonymousClass1yB
    public final synchronized void AAB(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws IOException {
        A03(byteBuffer, bufferInfo, false);
    }

    @Override // X.AnonymousClass1yB
    public final synchronized void AAJ(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws IOException {
        if ((bufferInfo.flags & 2) == 0) {
            A03(byteBuffer, bufferInfo, true);
        }
    }

    @Override // X.AnonymousClass1yB
    public final void start() throws IOException {
    }

    @Override // X.AnonymousClass1yB
    public final void A8b(MediaFormat mediaFormat) {
        this.A02.A01 = mediaFormat;
    }

    @Override // X.AnonymousClass1yB
    public final void A8h(int i) {
        this.A02.A00 = i;
    }

    @Override // X.AnonymousClass1yB
    public final void A8t(MediaFormat mediaFormat) {
        this.A02.A02 = mediaFormat;
    }

    public AnonymousClass1Xw(AnonymousClass1X9 r5) {
        this.A02 = r5;
        this.A05 = ByteBuffer.allocate((int) ((r5.A05 * ((long) ((r5.A04 / 1000) / 1000))) / 8));
    }

    public static int A00(int i, ByteBuffer byteBuffer) {
        if (byteBuffer.limit() < 4) {
            return 0;
        }
        byteBuffer.position(i);
        int remaining = byteBuffer.remaining();
        if (byteBuffer.remaining() > 4) {
            return byteBuffer.getInt(i);
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        byteBuffer.get(allocate.array(), 0, remaining);
        byteBuffer.position(0);
        byteBuffer.get(allocate.array(), remaining - 1, 4 - remaining);
        return allocate.getInt(0);
    }

    @VisibleForTesting
    public static void A01(int i, ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        if (remaining >= 4) {
            byteBuffer.putInt(i);
            return;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        byte[] array = allocate.array();
        byteBuffer.put(array, 0, remaining);
        byteBuffer.position(0);
        byteBuffer.put(array, remaining, 4 - remaining);
    }

    public static void A02(int i, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, MediaCodec.BufferInfo bufferInfo, boolean z) {
        byteBuffer.position(i);
        A01(bufferInfo.size, byteBuffer);
        A01(bufferInfo.offset, byteBuffer);
        long j = bufferInfo.presentationTimeUs;
        int remaining = byteBuffer.remaining();
        if (remaining >= 8) {
            byteBuffer.putLong(j);
        } else {
            ByteBuffer allocate = ByteBuffer.allocate(8);
            allocate.putLong(j);
            byte[] array = allocate.array();
            byteBuffer.put(array, 0, remaining);
            byteBuffer.position(0);
            byteBuffer.put(array, remaining, 8 - remaining);
        }
        A01(bufferInfo.flags, byteBuffer);
        A01(z ? 1 : 0, byteBuffer);
        int remaining2 = byteBuffer.remaining();
        int remaining3 = byteBuffer2.remaining();
        if (remaining2 >= remaining3) {
            byteBuffer.put(byteBuffer2);
            return;
        }
        byte[] bArr = new byte[remaining3];
        byteBuffer2.get(bArr);
        byteBuffer.put(bArr, 0, remaining2);
        byteBuffer.position(0);
        byteBuffer.put(bArr, remaining2, remaining3 - remaining2);
    }
}
