package X;

import android.graphics.ColorSpace;
import android.media.ExifInterface;
import androidx.annotation.RequiresApi;
import com.adobe.xmp.impl.Base64;
import com.oculus.localmedia.MediaProviderUtils;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
/* renamed from: X.0PZ  reason: invalid class name */
public final class AnonymousClass0PZ implements Closeable {
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public int A04;
    public int A05;
    @Nullable
    public ColorSpace A06;
    public AnonymousClass0Oj A07;
    @Nullable
    public AnonymousClass0PH A08;
    public boolean A09;
    @Nullable
    public final AbstractC00750Ik<FileInputStream> A0A;
    @Nullable
    public final AbstractC00820Ju<AnonymousClass0JV> A0B;

    public static int A02(InputStream inputStream, int i, boolean z) throws IOException {
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int read = inputStream.read();
            if (read != -1) {
                if (z) {
                    i2 = (read & MediaProviderUtils.JPEG_HEADER) << (i4 << 3);
                } else {
                    i3 <<= 8;
                    i2 = read & MediaProviderUtils.JPEG_HEADER;
                }
                i3 |= i2;
            } else {
                throw new IOException("no more bytes");
            }
        }
        return i3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
        if (r2.A0A != null) goto L_0x000e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean A0C() {
        /*
            r2 = this;
            monitor-enter(r2)
            X.0Ju<X.0JV> r0 = r2.A0B     // Catch:{ all -> 0x0011 }
            boolean r0 = X.AbstractC00820Ju.A04(r0)     // Catch:{ all -> 0x0011 }
            if (r0 != 0) goto L_0x000e
            X.0Ik<java.io.FileInputStream> r1 = r2.A0A     // Catch:{ all -> 0x0011 }
            r0 = 0
            if (r1 == 0) goto L_0x000f
        L_0x000e:
            r0 = 1
        L_0x000f:
            monitor-exit(r2)
            return r0
        L_0x0011:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0PZ.A0C():boolean");
    }

    @RequiresApi(api = 24)
    public static int A00(InputStream inputStream) {
        try {
            return new ExifInterface(inputStream).getAttributeInt("Orientation", 1);
        } catch (IOException unused) {
            return 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (r0 != null) goto L_0x000d;
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static X.AnonymousClass0PZ A03(X.AnonymousClass0PZ r3) {
        /*
            if (r3 == 0) goto L_0x002b
            X.0Ik<java.io.FileInputStream> r2 = r3.A0A
            if (r2 == 0) goto L_0x0011
            int r1 = r3.A04
            X.0PZ r0 = new X.0PZ
            r0.<init>(r2, r1)
        L_0x000d:
            r0.A0B(r3)
        L_0x0010:
            return r0
        L_0x0011:
            X.0Ju<X.0JV> r0 = r3.A0B
            X.0Ju r1 = X.AbstractC00820Ju.A00(r0)
            if (r1 != 0) goto L_0x0020
            r0 = 0
        L_0x001a:
            X.AbstractC00820Ju.A03(r1)
            if (r0 == 0) goto L_0x0010
            goto L_0x000d
        L_0x0020:
            X.0PZ r0 = new X.0PZ     // Catch:{ all -> 0x0026 }
            r0.<init>(r1)     // Catch:{ all -> 0x0026 }
            goto L_0x001a
        L_0x0026:
            r0 = move-exception
            X.AbstractC00820Ju.A03(r1)
            throw r0
        L_0x002b:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0PZ.A03(X.0PZ):X.0PZ");
    }

    public static void A04(@Nullable AnonymousClass0PZ r0) {
        if (r0 != null) {
            r0.close();
        }
    }

    public static void A06(AnonymousClass0PZ r1) {
        if (r1.A05 < 0 || r1.A01 < 0) {
            A05(r1);
        }
    }

    public static boolean A07(AnonymousClass0PZ r1) {
        if (r1.A02 < 0 || r1.A05 < 0 || r1.A01 < 0) {
            return false;
        }
        return true;
    }

    public static boolean A08(@Nullable AnonymousClass0PZ r1) {
        if (r1 == null || !r1.A0C()) {
            return false;
        }
        return true;
    }

    public final int A09() {
        AbstractC00820Ju<AnonymousClass0JV> r0 = this.A0B;
        if (r0 == null) {
            return this.A04;
        }
        r0.A06();
        return r0.A06().size();
    }

    @Nullable
    public final InputStream A0A() {
        AbstractC00750Ik<FileInputStream> r0 = this.A0A;
        if (r0 != null) {
            return r0.get();
        }
        AbstractC00820Ju A002 = AbstractC00820Ju.A00(this.A0B);
        if (A002 == null) {
            return null;
        }
        try {
            return new AnonymousClass0JX((AnonymousClass0JV) A002.A06());
        } finally {
            AbstractC00820Ju.A03(A002);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        AbstractC00820Ju.A03(this.A0B);
    }

    public static int A01(InputStream inputStream) throws IOException {
        byte read = (byte) (inputStream.read() & MediaProviderUtils.JPEG_HEADER);
        return ((((byte) (inputStream.read() & MediaProviderUtils.JPEG_HEADER)) << 16) & 16711680) | ((((byte) (inputStream.read() & MediaProviderUtils.JPEG_HEADER)) << 8) & 65280) | (read & Base64.INVALID);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 274
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:72)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
        */
    public static void A05(X.AnonymousClass0PZ r9) {
        /*
        // Method dump skipped, instructions count: 733
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0PZ.A05(X.0PZ):void");
    }

    public final void A0B(AnonymousClass0PZ r2) {
        A06(r2);
        this.A07 = r2.A07;
        A06(r2);
        this.A05 = r2.A05;
        A06(r2);
        this.A01 = r2.A01;
        A06(r2);
        this.A02 = r2.A02;
        A06(r2);
        this.A00 = r2.A00;
        this.A03 = r2.A03;
        this.A04 = r2.A09();
        this.A08 = r2.A08;
        A06(r2);
        this.A06 = r2.A06;
        this.A09 = r2.A09;
    }

    public AnonymousClass0PZ(AbstractC00750Ik<FileInputStream> r3, int i) {
        this.A07 = AnonymousClass0Oj.A01;
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

    public AnonymousClass0PZ(AbstractC00820Ju<AnonymousClass0JV> r3) {
        this.A07 = AnonymousClass0Oj.A01;
        this.A02 = -1;
        this.A00 = 0;
        this.A05 = -1;
        this.A01 = -1;
        this.A03 = 1;
        this.A04 = -1;
        C00740Ii.A01(Boolean.valueOf(AbstractC00820Ju.A04(r3)));
        this.A0B = r3.clone();
        this.A0A = null;
    }
}
