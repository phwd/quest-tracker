package X;

import com.facebook.FacebookSdk;
import com.oculus.horizon.service_media.OVRMediaServiceManager;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
import javax.annotation.Nullable;
import javax.inject.Provider;

/* renamed from: X.1av  reason: invalid class name and case insensitive filesystem */
public class C09331av extends AnonymousClass1b2 implements AnonymousClass0Rg {
    @Nullable
    public Provider<AbstractC01060Jr> A00;
    public AnonymousClass1bU A01;
    public AnonymousClass1bU A02;
    @Nullable
    public final C01000Ig A03;
    public final AnonymousClass0RX A04;

    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[ExcHandler: IndexOutOfBoundsException | BufferUnderflowException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:12:0x0025] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A01(int r8, X.AnonymousClass0Re r9) {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09331av.A01(int, X.0Re):void");
    }

    @Override // X.AnonymousClass1b2
    public double A03(long j, double d, boolean z) {
        int i;
        C01000Ig r4 = this.A03;
        if (r4 != null && C01340Rx.A00(j) == 4) {
            int i2 = (int) (j & 65535);
            int i3 = r4.A02;
            if (i3 != 0) {
                i = ((C08760yY) r4).A01.getInt(r4.A01(i3) + (i2 << 2));
            } else {
                i = 0;
            }
            if (!z && (i & 6) != 0) {
                A01(i, AnonymousClass0Re.AUTO_EXPOSURE);
            }
            if ((i & 1) != 0) {
                return d;
            }
            try {
                int i4 = r4.A03;
                if (i4 != 0) {
                    return ((C08760yY) r4).A01.getDouble(r4.A01(i4) + (i2 << 3));
                }
                return OVRMediaServiceManager.SCREENSHOT_SHORTCUT_DELAY;
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
            }
        }
        return d;
    }

    @Override // X.AnonymousClass1b2
    public int A04(long j) {
        if (this.A03 == null) {
            return 0;
        }
        int A002 = A00(this, j);
        int i = (A002 & 6) >>> 1;
        if ((A002 & 8) != 0) {
            return i | FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE;
        }
        return i;
    }

    @Override // X.AnonymousClass1b2
    public long A05(long j, long j2, boolean z) {
        int i;
        C01000Ig r4 = this.A03;
        if (r4 != null && C01340Rx.A00(j) == 2) {
            int i2 = (int) (j & 65535);
            int i3 = r4.A05;
            if (i3 != 0) {
                i = ((C08760yY) r4).A01.getInt(r4.A01(i3) + (i2 << 2));
            } else {
                i = 0;
            }
            if (!z && (i & 6) != 0) {
                A01(i, AnonymousClass0Re.AUTO_EXPOSURE);
            }
            if ((i & 1) != 0) {
                return j2;
            }
            try {
                int i4 = r4.A06;
                if (i4 != 0) {
                    return ((C08760yY) r4).A01.getLong(r4.A01(i4) + (i2 << 3));
                }
                return 0;
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
            }
        }
        return j2;
    }

    @Override // X.AnonymousClass1b2
    @Nullable
    public String A06(long j) {
        int i;
        C01000Ig r4 = this.A03;
        if (r4 == null) {
            return null;
        }
        int A002 = A00(this, j);
        boolean z = false;
        if ((A002 & 6) != 0) {
            z = true;
        }
        if (z) {
            i = (A002 >>> 8) & 16777215;
        } else {
            i = -1;
        }
        if (i < 0) {
            return null;
        }
        try {
            int i2 = r4.A04;
            if (i2 != 0) {
                return r4.A02(r4.A01(i2) + (i << 2));
            }
            return null;
        } catch (IndexOutOfBoundsException | NegativeArraySizeException | OutOfMemoryError | BufferUnderflowException unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:? A[ExcHandler: IndexOutOfBoundsException | BufferUnderflowException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:19:0x003a] */
    @Override // X.AnonymousClass1b2
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String A07(long r6, java.lang.String r8, boolean r9) {
        /*
            r5 = this;
            X.0Ig r3 = r5.A03
            if (r3 == 0) goto L_0x004e
            int r1 = X.C01340Rx.A00(r6)
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
            X.0Re r0 = X.AnonymousClass0Re.AUTO_EXPOSURE
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
        throw new UnsupportedOperationException("Method not decompiled: X.C09331av.A07(long, java.lang.String, boolean):java.lang.String");
    }

    @Override // X.AnonymousClass1b2
    public void A08(long j, AnonymousClass0Re r5) {
        if (this.A03 != null) {
            int A002 = A00(this, j);
            if ((A002 & 6) != 0) {
                A01(A002, r5);
            }
        }
    }

    @Override // X.AnonymousClass1b2
    public boolean A09(long j, boolean z, boolean z2) {
        byte b;
        C01000Ig r2 = this.A03;
        if (r2 == null || C01340Rx.A00(j) != 1) {
            return z;
        }
        int i = (int) (j & 65535);
        try {
            int i2 = r2.A01;
            if (i2 != 0) {
                b = ((C08760yY) r2).A01.get(r2.A01(i2) + i);
            } else {
                b = 0;
            }
            if (!z2 && (b & 6) != 0) {
                int i3 = -239;
                try {
                    int i4 = r2.A00;
                    if (i4 != 0) {
                        i3 = ((C08760yY) r2).A01.getInt(r2.A01(i4) + (i << 2));
                    } else {
                        i3 = 0;
                    }
                } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
                }
                A01(i3, AnonymousClass0Re.AUTO_EXPOSURE);
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

    public C09331av(ByteBuffer byteBuffer, AnonymousClass0RX r13, AnonymousClass0Ri r14, Provider<AbstractC01060Jr> provider) {
        super(r14);
        C01000Ig r3;
        AnonymousClass1bU r0;
        int i;
        int i2;
        this.A04 = r13;
        this.A00 = provider;
        if (byteBuffer == null || byteBuffer.capacity() <= 0) {
            r3 = null;
        } else {
            C01000Ig r8 = new C01000Ig();
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            ((C08760yY) r8).A00 = byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position();
            ((C08760yY) r8).A01 = byteBuffer;
            r3 = null;
            try {
                if (r8.A03() == 123456 && (r8.A04() == 123456 || r8.A04() == 0)) {
                    r8.A01 = r8.A00(6);
                    r8.A00 = r8.A00(8);
                    r8.A06 = r8.A00(10);
                    r8.A05 = r8.A00(12);
                    r8.A00(14);
                    r8.A00(16);
                    r8.A03 = r8.A00(18);
                    r8.A02 = r8.A00(20);
                    r8.A08 = r8.A00(22);
                    r8.A07 = r8.A00(24);
                    r8.A04 = r8.A00(26);
                    int A002 = r8.A00(32);
                    if (A002 != 0) {
                        int i3 = A002 + ((C08760yY) r8).A00;
                        int i4 = ((C08760yY) r8).A01.getInt(i3 + ((C08760yY) r8).A01.getInt(i3));
                        if (i4 <= 50) {
                            for (int i5 = 0; i5 < i4; i5++) {
                                AnonymousClass0oC r10 = new AnonymousClass0oC();
                                int A003 = r8.A00(32);
                                if (A003 != 0) {
                                    int A012 = r8.A01(A003) + (i5 << 2);
                                    int i6 = A012 + ((C08760yY) r8).A01.getInt(A012);
                                    ByteBuffer byteBuffer2 = ((C08760yY) r8).A01;
                                    r10.A00 = i6;
                                    r10.A01 = byteBuffer2;
                                    Map<Integer, Integer> map = r8.A09;
                                    int A004 = r10.A00(4);
                                    if (A004 != 0) {
                                        i = r10.A01.getInt(A004 + r10.A00);
                                    } else {
                                        i = 0;
                                    }
                                    Integer valueOf = Integer.valueOf(i);
                                    int A005 = r10.A00(6);
                                    if (A005 != 0) {
                                        i2 = r10.A01.getInt(A005 + r10.A00);
                                    } else {
                                        i2 = 0;
                                    }
                                    map.put(valueOf, Integer.valueOf(i2));
                                }
                            }
                        }
                    }
                    r3 = r8;
                } else {
                    r8.A03();
                    r8.A04();
                }
            } catch (IndexOutOfBoundsException e) {
                AnonymousClass0NO.A0C("FBMobileConfigTableOptimized", "Corrupted file, unexpected fbs offset", e);
            }
        }
        this.A03 = r3;
        int i7 = 0;
        if (r3 != null) {
            try {
                int A006 = r3.A00(26);
                if (A006 != 0) {
                    int i8 = A006 + ((C08760yY) r3).A00;
                    i7 = ((C08760yY) r3).A01.getInt(i8 + ((C08760yY) r3).A01.getInt(i8));
                }
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
                this.A01 = new AnonymousClass1bU(0);
                r0 = new AnonymousClass1bU(0);
            } catch (Throwable th) {
                this.A01 = new AnonymousClass1bU(0);
                this.A02 = new AnonymousClass1bU(0);
                throw th;
            }
        }
        this.A01 = new AnonymousClass1bU(i7);
        r0 = new AnonymousClass1bU(i7);
        this.A02 = r0;
    }

    public static int A00(C09331av r7, long j) {
        AbstractC01060Jr r1;
        AbstractC01060Jr r12;
        int A002 = C01340Rx.A00(j);
        int i = (int) (j & 65535);
        if (A002 == 0) {
            String format = String.format("Null type specifier is given: %d", Long.valueOf(j));
            AnonymousClass0NO.A08("MobileConfigContextV2Impl", format);
            Provider<AbstractC01060Jr> provider = r7.A00;
            if (!(provider == null || (r12 = provider.get()) == null)) {
                r12.A9B("MobileConfigContextV2Impl", format, 100000);
            }
        } else if (A002 == 1) {
            C01000Ig r13 = r7.A03;
            int i2 = r13.A00;
            if (i2 != 0) {
                return ((C08760yY) r13).A01.getInt(r13.A01(i2) + (i << 2));
            }
            return 0;
        } else if (A002 == 2) {
            C01000Ig r14 = r7.A03;
            int i3 = r14.A05;
            if (i3 != 0) {
                return ((C08760yY) r14).A01.getInt(r14.A01(i3) + (i << 2));
            }
            return 0;
        } else if (A002 == 3) {
            C01000Ig r15 = r7.A03;
            int i4 = r15.A07;
            if (i4 != 0) {
                return ((C08760yY) r15).A01.getInt(r15.A01(i4) + (i << 2));
            }
            return 0;
        } else if (A002 == 4) {
            try {
                C01000Ig r16 = r7.A03;
                int i5 = r16.A02;
                if (i5 != 0) {
                    return ((C08760yY) r16).A01.getInt(r16.A01(i5) + (i << 2));
                }
                return 0;
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
            }
        }
        String format2 = String.format("Fail to get meta for spec: %d", Long.valueOf(j));
        AnonymousClass0NO.A08("MobileConfigContextV2Impl", format2);
        Provider<AbstractC01060Jr> provider2 = r7.A00;
        if (provider2 == null || (r1 = provider2.get()) == null) {
            return -239;
        }
        r1.A9B("MobileConfigContextV2Impl", format2, 100000);
        return -239;
    }
}
