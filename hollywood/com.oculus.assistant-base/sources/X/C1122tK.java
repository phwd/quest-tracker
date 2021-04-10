package X;

import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: X.tK  reason: case insensitive filesystem */
public final class C1122tK implements AbstractC0609cs {
    public short A00;
    public byte A01;
    public int A02;
    public int A03;
    public int A04;
    public final t4 A05;

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0609cs
    public final void close() {
    }

    @Override // X.AbstractC0609cs
    public final long A4c(AnonymousClass33 r9, long j) {
        int i;
        int readInt;
        do {
            int i2 = this.A02;
            if (i2 == 0) {
                t4 t4Var = this.A05;
                t4Var.A55((long) this.A00);
                this.A00 = 0;
                if ((this.A01 & 4) == 0) {
                    i = this.A04;
                    int readByte = (t4Var.readByte() & 255) | ((t4Var.readByte() & 255) << 16) | ((t4Var.readByte() & 255) << 8);
                    this.A02 = readByte;
                    this.A03 = readByte;
                    byte readByte2 = (byte) (t4Var.readByte() & 255);
                    this.A01 = (byte) (t4Var.readByte() & 255);
                    Logger logger = C0585cR.A04;
                    if (logger.isLoggable(Level.FINE)) {
                        logger.fine(C0582cN.A00(true, this.A04, this.A03, readByte2, this.A01));
                    }
                    readInt = t4Var.readInt() & Integer.MAX_VALUE;
                    this.A04 = readInt;
                    if (readByte2 != 9) {
                        C0582cN.A01("%s != TYPE_CONTINUATION", Byte.valueOf(readByte2));
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else {
                long A4c = this.A05.A4c(r9, Math.min(j, (long) i2));
                if (A4c != -1) {
                    this.A02 = (int) (((long) this.A02) - A4c);
                    return A4c;
                }
            }
            return -1;
        } while (readInt == i);
        C0582cN.A01("TYPE_CONTINUATION streamId changed", new Object[0]);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC0609cs
    public final C0610ct A5G() {
        return this.A05.A5G();
    }

    public C1122tK(t4 t4Var) {
        this.A05 = t4Var;
    }
}
