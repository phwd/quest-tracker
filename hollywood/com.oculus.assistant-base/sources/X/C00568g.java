package X;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.8g  reason: invalid class name and case insensitive filesystem */
public final class C00568g extends InputStream {
    public double A00;
    public AbstractC00548e A01;
    public AbstractC00558f A02;
    public C00598j A03;
    public C0781hE A04;
    public InputStream A05;
    public final AtomicBoolean A06 = new AtomicBoolean(false);

    public final synchronized void A00() {
        if (this.A05 != null) {
            try {
                close();
            } catch (IOException unused) {
            }
        }
        this.A05 = null;
        this.A06.set(false);
        this.A02 = null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() {
        InputStream inputStream = this.A05;
        if (inputStream != null) {
            inputStream.close();
        }
        C00598j r3 = this.A03;
        if (r3 != null) {
            synchronized (r3) {
                RunnableC00578h r2 = r3.A00;
                if (r2 != null) {
                    r2.A01.set(false);
                    r2.A00.add(new C0746gW(r2.A02));
                    r3.A00 = null;
                }
            }
        }
    }

    @Override // java.io.InputStream
    public final int read() {
        throw new IOException("Not implemented");
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        RunnableC00578h r0;
        RunnableC00578h r3;
        synchronized (this) {
            if (this.A05 == null) {
                return 0;
            }
            AtomicBoolean atomicBoolean = this.A06;
            if (atomicBoolean.get()) {
                AbstractC00548e r02 = this.A01;
                if (r02 != null) {
                    r02.A4A();
                }
                atomicBoolean.set(false);
            }
            int read = this.A05.read(bArr, i, i2);
            C00598j r1 = this.A03;
            if (!(r1 == null || read <= 0 || (r0 = r1.A00) == null || !r0.A01.get() || (r3 = r1.A00) == null)) {
                C00598j r12 = r3.A02;
                if (r12.A00 == null) {
                    C0139Dd.A0A("LastAudioLogger", "DataQueueThread is null");
                } else {
                    ByteBuffer allocate = ByteBuffer.allocate(read);
                    allocate.put(bArr, i, read);
                    r12.A00.A00.add(new C0747gX(r3, bArr, allocate));
                }
            }
            int i3 = (read >> 1) << 1;
            double d = 0.0d;
            for (int i4 = i; i4 < i + i3; i4 += 2) {
                short s = (short) (((bArr[i4 + 1] & 255) << 8) | (bArr[i4] & 255));
                d += (double) (s * s);
            }
            double max = Math.max(Math.min(((Math.log10(Math.sqrt(d / ((double) (i3 >> 1))) / 20.0d) * 20.0d) - 0.0d) / 40.0d, 1.0d), 0.0d);
            if (max != this.A00) {
                this.A00 = max;
                AbstractC00558f r03 = this.A02;
                if (r03 != null) {
                    r03.A4Q(max);
                }
            }
            C0781hE hEVar = this.A04;
            if (hEVar != null) {
                C00849o r4 = hEVar.A00;
                r4.A02 = hEVar.A01.A00.currentMonotonicTimestamp();
                r4.A01 += (long) read;
            }
            return read;
        }
    }
}
