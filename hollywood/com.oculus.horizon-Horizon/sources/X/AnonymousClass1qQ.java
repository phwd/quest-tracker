package X;

import android.graphics.ColorSpace;
import com.facebook.common.memory.PooledByteBuffer;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
/* renamed from: X.1qQ  reason: invalid class name */
public final class AnonymousClass1qQ implements Closeable {
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public int A04;
    public int A05;
    @Nullable
    public ColorSpace A06;
    public AnonymousClass1tL A07;
    @Nullable
    public AnonymousClass1jz A08;
    public boolean A09;
    @Nullable
    public final AnonymousClass0KW<FileInputStream> A0A;
    @Nullable
    public final AnonymousClass1qa<PooledByteBuffer> A0B;

    public static int A01(InputStream inputStream, int i, boolean z) throws IOException {
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int read = inputStream.read();
            if (read != -1) {
                if (z) {
                    i2 = (read & 255) << (i4 << 3);
                } else {
                    i3 <<= 8;
                    i2 = read & 255;
                }
                i3 |= i2;
            } else {
                throw new IOException("no more bytes");
            }
        }
        return i3;
    }

    @Nullable
    public static AnonymousClass1qQ A02(AnonymousClass1qQ r3) {
        AnonymousClass1qQ r0;
        if (r3 == null) {
            return null;
        }
        AnonymousClass0KW<FileInputStream> r2 = r3.A0A;
        if (r2 != null) {
            r0 = new AnonymousClass1qQ(r2, r3.A04);
        } else {
            AnonymousClass1qa A002 = AnonymousClass1qa.A00(r3.A0B);
            if (A002 == null) {
                return null;
            }
            try {
                r0 = new AnonymousClass1qQ(A002);
            } finally {
                A002.close();
            }
        }
        r0.A09(r3);
        return r0;
    }

    public static void A03(@Nullable AnonymousClass1qQ r0) {
        if (r0 != null) {
            r0.close();
        }
    }

    public static void A05(AnonymousClass1qQ r1) {
        if (r1.A05 < 0 || r1.A01 < 0) {
            A04(r1);
        }
    }

    public static boolean A06(@Nullable AnonymousClass1qQ r2) {
        boolean z;
        if (r2 != null) {
            synchronized (r2) {
                if (!AnonymousClass1qa.A02(r2.A0B)) {
                    z = false;
                    if (r2.A0A != null) {
                    }
                }
                z = true;
            }
            if (!z) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int A07() {
        AnonymousClass1qa<PooledByteBuffer> r0 = this.A0B;
        if (r0 == null) {
            return this.A04;
        }
        r0.A04();
        return r0.A04().A02();
    }

    @Nullable
    public final InputStream A08() {
        AnonymousClass0KW<FileInputStream> r0 = this.A0A;
        if (r0 != null) {
            return r0.get();
        }
        AnonymousClass1qa A002 = AnonymousClass1qa.A00(this.A0B);
        if (A002 == null) {
            return null;
        }
        try {
            return new C09981qk((C10021qp) A002.A04());
        } finally {
            A002.close();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        AnonymousClass1qa<PooledByteBuffer> r0 = this.A0B;
        if (r0 != null) {
            r0.close();
        }
    }

    public static int A00(InputStream inputStream) throws IOException {
        byte read = (byte) (inputStream.read() & 255);
        return ((((byte) (inputStream.read() & 255)) << 16) & 16711680) | ((((byte) (inputStream.read() & 255)) << 8) & 65280) | (read & 255);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 294
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:72)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
        */
    public static void A04(X.AnonymousClass1qQ r9) {
        /*
        // Method dump skipped, instructions count: 768
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1qQ.A04(X.1qQ):void");
    }

    public final void A09(AnonymousClass1qQ r2) {
        A05(r2);
        this.A07 = r2.A07;
        A05(r2);
        this.A05 = r2.A05;
        A05(r2);
        this.A01 = r2.A01;
        A05(r2);
        this.A02 = r2.A02;
        A05(r2);
        this.A00 = r2.A00;
        this.A03 = r2.A03;
        this.A04 = r2.A07();
        this.A08 = r2.A08;
        A05(r2);
        this.A06 = r2.A06;
        this.A09 = r2.A09;
    }

    public AnonymousClass1qQ(AnonymousClass0KW<FileInputStream> r3, int i) {
        this.A07 = AnonymousClass1tL.A01;
        this.A02 = -1;
        this.A00 = 0;
        this.A05 = -1;
        this.A01 = -1;
        this.A03 = 1;
        this.A04 = -1;
        this.A0B = null;
        this.A0A = r3;
        this.A04 = i;
    }

    public AnonymousClass1qQ(AnonymousClass1qa<PooledByteBuffer> r3) {
        this.A07 = AnonymousClass1tL.A01;
        this.A02 = -1;
        this.A00 = 0;
        this.A05 = -1;
        this.A01 = -1;
        this.A03 = 1;
        this.A04 = -1;
        AnonymousClass0KU.A01(Boolean.valueOf(AnonymousClass1qa.A02(r3)));
        this.A0B = r3.clone();
        this.A0A = null;
    }
}
