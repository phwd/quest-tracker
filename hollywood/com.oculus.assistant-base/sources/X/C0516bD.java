package X;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;

/* renamed from: X.bD  reason: case insensitive filesystem */
public class C0516bD extends AbstractC0890oC {
    public AbstractC0463a6 A00;
    public Fz A01;
    public Fz A02;
    public final C0521bK A03;
    public final AbstractC0162Fh A04;

    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[ExcHandler: IndexOutOfBoundsException | BufferUnderflowException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:12:0x0025] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A02(int r8, X.EnumC0165Fq r9) {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0516bD.A02(int, X.Fq):void");
    }

    @Override // X.AbstractC0890oC
    public long A04(long j, long j2, boolean z) {
        int i;
        C0521bK bKVar = this.A03;
        if (bKVar != null && GJ.A00(j) == 2) {
            int i2 = (int) (j & 65535);
            int i3 = bKVar.A05;
            if (i3 != 0) {
                i = ((VA) bKVar).A01.getInt(bKVar.A01(i3) + (i2 << 2));
            } else {
                i = 0;
            }
            if (!z && (i & 6) != 0) {
                A02(i, EnumC0165Fq.AUTO_EXPOSURE);
            }
            if ((i & 1) != 0) {
                return j2;
            }
            try {
                int i4 = bKVar.A06;
                if (i4 != 0) {
                    return ((VA) bKVar).A01.getLong(bKVar.A01(i4) + (i2 << 3));
                }
                return 0;
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
            }
        }
        return j2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:? A[ExcHandler: IndexOutOfBoundsException | BufferUnderflowException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:19:0x003a] */
    @Override // X.AbstractC0890oC
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String A05(long r6, java.lang.String r8, boolean r9) {
        /*
            r5 = this;
            X.bK r3 = r5.A03
            if (r3 == 0) goto L_0x004e
            int r1 = X.GJ.A00(r6)
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
            X.Fq r0 = X.EnumC0165Fq.AUTO_EXPOSURE
            r5.A02(r2, r0)
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
        throw new UnsupportedOperationException("Method not decompiled: X.C0516bD.A05(long, java.lang.String, boolean):java.lang.String");
    }

    @Override // X.AbstractC0890oC
    public void A06(long j, EnumC0165Fq fq) {
        if (this.A03 != null) {
            int A012 = A01(this, j);
            if ((A012 & 6) != 0) {
                A02(A012, fq);
            }
        }
    }

    @Override // X.AbstractC0890oC
    public boolean A08(long j, boolean z, boolean z2) {
        byte b;
        C0521bK bKVar = this.A03;
        if (bKVar == null || GJ.A00(j) != 1) {
            return z;
        }
        int i = (int) (j & 65535);
        try {
            int i2 = bKVar.A01;
            if (i2 != 0) {
                b = ((VA) bKVar).A01.get(bKVar.A01(i2) + i);
            } else {
                b = 0;
            }
            if (!z2 && (b & 6) != 0) {
                int i3 = -239;
                try {
                    int i4 = bKVar.A00;
                    if (i4 != 0) {
                        i3 = ((VA) bKVar).A01.getInt(bKVar.A01(i4) + (i << 2));
                    } else {
                        i3 = 0;
                    }
                } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
                }
                A02(i3, EnumC0165Fq.AUTO_EXPOSURE);
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

    public C0516bD(ByteBuffer byteBuffer, AbstractC0162Fh fh, AbstractC0168Ft ft, AbstractC0463a6 a6Var) {
        super(ft);
        C0521bK bKVar;
        Fz fz;
        int i;
        int i2;
        this.A04 = fh;
        this.A00 = a6Var;
        if (byteBuffer == null || byteBuffer.capacity() <= 0) {
            bKVar = null;
        } else {
            C0521bK bKVar2 = new C0521bK();
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            ((VA) bKVar2).A00 = byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position();
            ((VA) bKVar2).A01 = byteBuffer;
            bKVar = null;
            try {
                if (bKVar2.A03() == 123456 && (bKVar2.A04() == 123456 || bKVar2.A04() == 0)) {
                    bKVar2.A01 = bKVar2.A00(6);
                    bKVar2.A00 = bKVar2.A00(8);
                    bKVar2.A06 = bKVar2.A00(10);
                    bKVar2.A05 = bKVar2.A00(12);
                    bKVar2.A00(14);
                    bKVar2.A00(16);
                    bKVar2.A03 = bKVar2.A00(18);
                    bKVar2.A02 = bKVar2.A00(20);
                    bKVar2.A08 = bKVar2.A00(22);
                    bKVar2.A07 = bKVar2.A00(24);
                    bKVar2.A04 = bKVar2.A00(26);
                    int A002 = bKVar2.A00(32);
                    if (A002 != 0) {
                        int i3 = A002 + ((VA) bKVar2).A00;
                        int i4 = ((VA) bKVar2).A01.getInt(i3 + ((VA) bKVar2).A01.getInt(i3));
                        if (i4 > 50) {
                            C0139Dd.A0G("FBMobileConfigTableOptimized", "Probably corrupted mctable data, epInfoLen:%s", Integer.valueOf(i4));
                        } else {
                            for (int i5 = 0; i5 < i4; i5++) {
                                C0886o5 o5Var = new C0886o5();
                                int A003 = bKVar2.A00(32);
                                if (A003 != 0) {
                                    int A012 = bKVar2.A01(A003) + (i5 << 2);
                                    int i6 = A012 + ((VA) bKVar2).A01.getInt(A012);
                                    ByteBuffer byteBuffer2 = ((VA) bKVar2).A01;
                                    o5Var.A00 = i6;
                                    o5Var.A01 = byteBuffer2;
                                    Map map = bKVar2.A09;
                                    int A004 = o5Var.A00(4);
                                    if (A004 != 0) {
                                        i = o5Var.A01.getInt(A004 + o5Var.A00);
                                    } else {
                                        i = 0;
                                    }
                                    Integer valueOf = Integer.valueOf(i);
                                    int A005 = o5Var.A00(6);
                                    if (A005 != 0) {
                                        i2 = o5Var.A01.getInt(A005 + o5Var.A00);
                                    } else {
                                        i2 = 0;
                                    }
                                    map.put(valueOf, Integer.valueOf(i2));
                                }
                            }
                        }
                    }
                    bKVar = bKVar2;
                } else {
                    C0139Dd.A0I("FBMobileConfigTableOptimized", "Magic number does not match!  Got magic:%s magic2:%s", Integer.valueOf(bKVar2.A03()), Integer.valueOf(bKVar2.A04()));
                }
            } catch (IndexOutOfBoundsException e) {
                C0139Dd.A0M("FBMobileConfigTableOptimized", "Corrupted file, unexpected fbs offset", e);
            }
        }
        this.A03 = bKVar;
        int i7 = 0;
        if (bKVar != null) {
            try {
                int A006 = bKVar.A00(26);
                if (A006 != 0) {
                    int i8 = A006 + ((VA) bKVar).A00;
                    i7 = ((VA) bKVar).A01.getInt(i8 + ((VA) bKVar).A01.getInt(i8));
                }
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
                this.A01 = new Fz(0);
                fz = new Fz(0);
            } catch (Throwable th) {
                this.A01 = new Fz(0);
                this.A02 = new Fz(0);
                throw th;
            }
        }
        this.A01 = new Fz(i7);
        fz = new Fz(i7);
        this.A02 = fz;
    }

    public static int A01(C0516bD bDVar, long j) {
        int A002 = GJ.A00(j);
        int i = (int) (j & 65535);
        if (A002 == 0) {
            C0139Dd.A0A("MobileConfigContextV2Impl", String.format("Null type specifier is given: %d", Long.valueOf(j)));
            AbstractC0463a6 a6Var = bDVar.A00;
            if (a6Var != null) {
                a6Var.get();
            }
        } else if (A002 == 1) {
            C0521bK bKVar = bDVar.A03;
            int i2 = bKVar.A00;
            if (i2 != 0) {
                return ((VA) bKVar).A01.getInt(bKVar.A01(i2) + (i << 2));
            }
            return 0;
        } else if (A002 == 2) {
            C0521bK bKVar2 = bDVar.A03;
            int i3 = bKVar2.A05;
            if (i3 != 0) {
                return ((VA) bKVar2).A01.getInt(bKVar2.A01(i3) + (i << 2));
            }
            return 0;
        } else if (A002 == 3) {
            C0521bK bKVar3 = bDVar.A03;
            int i4 = bKVar3.A07;
            if (i4 != 0) {
                return ((VA) bKVar3).A01.getInt(bKVar3.A01(i4) + (i << 2));
            }
            return 0;
        } else if (A002 == 4) {
            try {
                C0521bK bKVar4 = bDVar.A03;
                int i5 = bKVar4.A02;
                if (i5 != 0) {
                    return ((VA) bKVar4).A01.getInt(bKVar4.A01(i5) + (i << 2));
                }
                return 0;
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
            }
        }
        C0139Dd.A0A("MobileConfigContextV2Impl", String.format("Fail to get meta for spec: %d", Long.valueOf(j)));
        AbstractC0463a6 a6Var2 = bDVar.A00;
        if (a6Var2 == null) {
            return -239;
        }
        a6Var2.get();
        return -239;
    }
}
