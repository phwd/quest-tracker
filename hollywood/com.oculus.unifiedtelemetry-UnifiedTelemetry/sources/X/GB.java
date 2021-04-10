package X;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
import javax.annotation.Nullable;

public final class GB extends XZ implements Rc {
    @Nullable
    public eJ<Ix> A00;
    public C0138Rl A01;
    public C0138Rl A02;
    @Nullable
    public final C0085Gf A03;
    public final RU A04;

    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[ExcHandler: IndexOutOfBoundsException | BufferUnderflowException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:12:0x0025] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A01(int r8, X.Ra r9) {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: X.GB.A01(int, X.Ra):void");
    }

    @Override // X.XZ
    public final long A03(long j, long j2, boolean z) {
        int i;
        C0085Gf gf = this.A03;
        if (gf != null && SL.A00(j) == 2) {
            int i2 = (int) (j & 65535);
            int i3 = gf.A05;
            if (i3 != 0) {
                i = ((HP) gf).A01.getInt(gf.A01(i3) + (i2 << 2));
            } else {
                i = 0;
            }
            if (!z && (i & 6) != 0) {
                A01(i, Ra.AUTO_EXPOSURE);
            }
            if ((i & 1) != 0) {
                return j2;
            }
            try {
                int i4 = gf.A06;
                if (i4 != 0) {
                    return ((HP) gf).A01.getLong(gf.A01(i4) + (i2 << 3));
                }
                return 0;
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
            }
        }
        return j2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:? A[ExcHandler: IndexOutOfBoundsException | BufferUnderflowException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:19:0x003a] */
    @Override // X.XZ
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String A04(long r6, java.lang.String r8, boolean r9) {
        /*
            r5 = this;
            X.Gf r3 = r5.A03
            if (r3 == 0) goto L_0x004e
            int r1 = X.SL.A00(r6)
            r0 = 3
            if (r1 != r0) goto L_0x004e
            r0 = 65535(0xffff, double:3.23786E-319)
            long r6 = r6 & r0
            int r4 = (int) r6
            int r0 = r3.A07
            if (r0 == 0) goto L_0x0022
            java.nio.ByteBuffer r2 = r3.A01
            int r1 = r3.A01(r0)
            int r0 = r4 << 2
            int r1 = r1 + r0
            int r2 = r2.getInt(r1)
            goto L_0x0023
        L_0x0022:
            r2 = 0
        L_0x0023:
            if (r9 != 0) goto L_0x0032
            r1 = r2 & 6
            r0 = 0
            if (r1 == 0) goto L_0x002b
            r0 = 1
        L_0x002b:
            if (r0 == 0) goto L_0x0032
            X.Ra r0 = X.Ra.AUTO_EXPOSURE
            r5.A01(r2, r0)
        L_0x0032:
            r0 = 1
            r2 = r2 & r0
            if (r2 != 0) goto L_0x0037
            r0 = 0
        L_0x0037:
            if (r0 == 0) goto L_0x003a
            return r8
        L_0x003a:
            int r0 = r3.A08     // Catch:{ IndexOutOfBoundsException | BufferUnderflowException -> 0x004e, IndexOutOfBoundsException | BufferUnderflowException -> 0x004e, IndexOutOfBoundsException | BufferUnderflowException -> 0x004e }
            if (r0 == 0) goto L_0x004a
            int r1 = r3.A01(r0)     // Catch:{ IndexOutOfBoundsException | BufferUnderflowException -> 0x004e, IndexOutOfBoundsException | BufferUnderflowException -> 0x004e, IndexOutOfBoundsException | BufferUnderflowException -> 0x004e }
            int r0 = r4 << 2
            int r1 = r1 + r0
            java.lang.String r0 = r3.A02(r1)     // Catch:{ IndexOutOfBoundsException | BufferUnderflowException -> 0x004e, IndexOutOfBoundsException | BufferUnderflowException -> 0x004e, IndexOutOfBoundsException | BufferUnderflowException -> 0x004e }
            goto L_0x004b
        L_0x004a:
            r0 = 0
        L_0x004b:
            if (r0 == 0) goto L_0x004e
            return r0
        L_0x004e:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: X.GB.A04(long, java.lang.String, boolean):java.lang.String");
    }

    @Override // X.XZ
    public final void A05(long j, Ra ra) {
        if (this.A03 != null) {
            int A002 = A00(j);
            if ((A002 & 6) != 0) {
                A01(A002, ra);
            }
        }
    }

    @Override // X.XZ
    public final boolean A06() {
        if (this.A03 != null) {
            return true;
        }
        return false;
    }

    @Override // X.XZ
    public final boolean A09(long j, boolean z, boolean z2) {
        byte b;
        C0085Gf gf = this.A03;
        if (gf == null || SL.A00(j) != 1) {
            return z;
        }
        int i = (int) (j & 65535);
        try {
            int i2 = gf.A01;
            if (i2 != 0) {
                b = ((HP) gf).A01.get(gf.A01(i2) + i);
            } else {
                b = 0;
            }
            if (!z2 && (b & 6) != 0) {
                int i3 = -239;
                try {
                    int i4 = gf.A00;
                    if (i4 != 0) {
                        i3 = ((HP) gf).A01.getInt(gf.A01(i4) + (i << 2));
                    } else {
                        i3 = 0;
                    }
                } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
                }
                A01(i3, Ra.AUTO_EXPOSURE);
            }
            if ((b & 1) != 0) {
                return z;
            }
            if ((b >>> 7) != 0) {
                return true;
            }
            return false;
        } catch (IndexOutOfBoundsException | BufferUnderflowException unused2) {
            return z;
        }
    }

    public GB(ByteBuffer byteBuffer, RU ru, Re re, eJ<Ix> eJVar) {
        super(re);
        C0085Gf gf;
        C0138Rl rl;
        int i;
        int i2;
        this.A04 = ru;
        this.A00 = eJVar;
        if (byteBuffer == null || byteBuffer.capacity() <= 0) {
            gf = null;
        } else {
            C0085Gf gf2 = new C0085Gf();
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            ((HP) gf2).A00 = byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position();
            ((HP) gf2).A01 = byteBuffer;
            gf = null;
            try {
                if (gf2.A03() == 123456 && (gf2.A04() == 123456 || gf2.A04() == 0)) {
                    gf2.A01 = gf2.A00(6);
                    gf2.A00 = gf2.A00(8);
                    gf2.A06 = gf2.A00(10);
                    gf2.A05 = gf2.A00(12);
                    gf2.A00(14);
                    gf2.A00(16);
                    gf2.A03 = gf2.A00(18);
                    gf2.A02 = gf2.A00(20);
                    gf2.A08 = gf2.A00(22);
                    gf2.A07 = gf2.A00(24);
                    gf2.A04 = gf2.A00(26);
                    int A002 = gf2.A00(32);
                    if (A002 != 0) {
                        int i3 = A002 + ((HP) gf2).A00;
                        int i4 = ((HP) gf2).A01.getInt(i3 + ((HP) gf2).A01.getInt(i3));
                        if (i4 <= 50) {
                            for (int i5 = 0; i5 < i4; i5++) {
                                C0238Xh xh = new C0238Xh();
                                int A003 = gf2.A00(32);
                                if (A003 != 0) {
                                    int A012 = gf2.A01(A003) + (i5 << 2);
                                    int i6 = A012 + ((HP) gf2).A01.getInt(A012);
                                    ByteBuffer byteBuffer2 = ((HP) gf2).A01;
                                    xh.A00 = i6;
                                    xh.A01 = byteBuffer2;
                                    Map<Integer, Integer> map = gf2.A09;
                                    int A004 = xh.A00(4);
                                    if (A004 != 0) {
                                        i = xh.A01.getInt(A004 + xh.A00);
                                    } else {
                                        i = 0;
                                    }
                                    Integer valueOf = Integer.valueOf(i);
                                    int A005 = xh.A00(6);
                                    if (A005 != 0) {
                                        i2 = xh.A01.getInt(A005 + xh.A00);
                                    } else {
                                        i2 = 0;
                                    }
                                    map.put(valueOf, Integer.valueOf(i2));
                                }
                            }
                        }
                    }
                    gf = gf2;
                } else {
                    gf2.A03();
                    gf2.A04();
                }
            } catch (IndexOutOfBoundsException e) {
                Mu.A03("FBMobileConfigTableOptimized", "Corrupted file, unexpected fbs offset", e);
            }
        }
        this.A03 = gf;
        int i7 = 0;
        if (gf != null) {
            try {
                int A006 = gf.A00(26);
                if (A006 != 0) {
                    int i8 = A006 + ((HP) gf).A00;
                    i7 = ((HP) gf).A01.getInt(i8 + ((HP) gf).A01.getInt(i8));
                }
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
                this.A01 = new C0138Rl(0);
                rl = new C0138Rl(0);
            } catch (Throwable th) {
                this.A01 = new C0138Rl(0);
                this.A02 = new C0138Rl(0);
                throw th;
            }
        }
        this.A01 = new C0138Rl(i7);
        rl = new C0138Rl(i7);
        this.A02 = rl;
    }

    private int A00(long j) {
        Ix ix;
        Ix ix2;
        int A002 = SL.A00(j);
        int i = (int) (j & 65535);
        if (A002 == 0) {
            String format = String.format("Null type specifier is given: %d", Long.valueOf(j));
            Mu.A00("MobileConfigContextV2Impl", format);
            eJ<Ix> eJVar = this.A00;
            if (!(eJVar == null || (ix2 = eJVar.get()) == null)) {
                ix2.A5L("MobileConfigContextV2Impl", format, 100000);
            }
        } else if (A002 == 1) {
            C0085Gf gf = this.A03;
            int i2 = gf.A00;
            if (i2 != 0) {
                return ((HP) gf).A01.getInt(gf.A01(i2) + (i << 2));
            }
            return 0;
        } else if (A002 == 2) {
            C0085Gf gf2 = this.A03;
            int i3 = gf2.A05;
            if (i3 != 0) {
                return ((HP) gf2).A01.getInt(gf2.A01(i3) + (i << 2));
            }
            return 0;
        } else if (A002 == 3) {
            C0085Gf gf3 = this.A03;
            int i4 = gf3.A07;
            if (i4 != 0) {
                return ((HP) gf3).A01.getInt(gf3.A01(i4) + (i << 2));
            }
            return 0;
        } else if (A002 == 4) {
            try {
                C0085Gf gf4 = this.A03;
                int i5 = gf4.A02;
                if (i5 != 0) {
                    return ((HP) gf4).A01.getInt(gf4.A01(i5) + (i << 2));
                }
                return 0;
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
            }
        }
        String format2 = String.format("Fail to get meta for spec: %d", Long.valueOf(j));
        Mu.A00("MobileConfigContextV2Impl", format2);
        eJ<Ix> eJVar2 = this.A00;
        if (eJVar2 == null || (ix = eJVar2.get()) == null) {
            return -239;
        }
        ix.A5L("MobileConfigContextV2Impl", format2, 100000);
        return -239;
    }

    @Override // X.XZ
    public final boolean A07(long j) {
        if (!A06()) {
            return false;
        }
        boolean z = false;
        if ((A00(j) & 16) != 0) {
            z = true;
        }
        return !z;
    }

    @Override // X.XZ
    public final boolean A08(long j) {
        if (!A06()) {
            return false;
        }
        boolean z = true;
        if ((A00(j) & 1) == 0) {
            z = false;
        }
        return !z;
    }
}
