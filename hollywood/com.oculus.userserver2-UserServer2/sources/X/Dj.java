package X;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public final class Dj implements WF {
    public int A00;
    public boolean A01;
    public final Inflater A02;
    public final Dp A03;

    public final boolean A00() throws IOException {
        Inflater inflater = this.A02;
        if (!inflater.needsInput()) {
            return false;
        }
        int i = this.A00;
        if (i != 0) {
            int remaining = i - inflater.getRemaining();
            this.A00 -= remaining;
            this.A03.A3i((long) remaining);
        }
        if (inflater.getRemaining() == 0) {
            Dp dp = this.A03;
            if (dp.A1V()) {
                return true;
            }
            WI wi = dp.A16().A01;
            int i2 = wi.A00;
            int i3 = wi.A01;
            int i4 = i2 - i3;
            this.A00 = i4;
            inflater.setInput(wi.A06, i3, i4);
            return false;
        }
        throw new IllegalStateException("?");
    }

    @Override // java.io.Closeable, X.WF, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (!this.A01) {
            this.A02.end();
            this.A01 = true;
            this.A03.close();
        }
    }

    @Override // X.WF
    public final long read(AnonymousClass8k r8, long j) throws IOException {
        boolean A002;
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass06.A02("byteCount < 0: ", j));
        } else if (this.A01) {
            throw new IllegalStateException("closed");
        } else if (j == 0) {
            return 0;
        } else {
            do {
                A002 = A00();
                try {
                    WI A07 = r8.A07(1);
                    int i = A07.A00;
                    int min = (int) Math.min(j, (long) (8192 - i));
                    Inflater inflater = this.A02;
                    int inflate = inflater.inflate(A07.A06, i, min);
                    if (inflate > 0) {
                        A07.A00 += inflate;
                        long j2 = (long) inflate;
                        r8.A00 += j2;
                        return j2;
                    } else if (inflater.finished() || inflater.needsDictionary()) {
                        int i2 = this.A00;
                        if (i2 != 0) {
                            int remaining = i2 - inflater.getRemaining();
                            this.A00 -= remaining;
                            this.A03.A3i((long) remaining);
                        }
                        if (A07.A01 != A07.A00) {
                            return -1;
                        }
                        r8.A01 = A07.A00();
                        WH.A01(A07);
                        return -1;
                    }
                } catch (DataFormatException e) {
                    throw new IOException(e);
                }
            } while (!A002);
            throw new EOFException("source exhausted prematurely");
        }
    }

    @Override // X.WF
    public final WE timeout() {
        return this.A03.timeout();
    }

    public Dj(Dp dp, Inflater inflater) {
        this.A03 = dp;
        this.A02 = inflater;
    }
}
