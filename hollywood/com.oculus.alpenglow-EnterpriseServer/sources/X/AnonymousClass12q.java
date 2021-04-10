package X;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.12q  reason: invalid class name */
public final class AnonymousClass12q extends AnonymousClass139 implements AnonymousClass0Sp {
    @Nullable
    public AbstractC07240oz<AbstractC01590Jn> A00;
    public AnonymousClass15S A01;
    public AnonymousClass15S A02;
    @Nullable
    public final C01720Lc A03;
    public final AnonymousClass0ST A04;

    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[ExcHandler: IndexOutOfBoundsException | BufferUnderflowException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:12:0x0025] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A01(X.AnonymousClass12q r7, int r8, X.AnonymousClass0Sn r9) {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass12q.A01(X.12q, int, X.0Sn):void");
    }

    public AnonymousClass12q(ByteBuffer byteBuffer, AnonymousClass0ST r13, AnonymousClass0Sr r14, AbstractC07240oz<AbstractC01590Jn> r15) {
        super(r14);
        C01720Lc r4;
        AnonymousClass15S r0;
        int i;
        int i2;
        this.A04 = r13;
        this.A00 = r15;
        if (byteBuffer == null || byteBuffer.capacity() <= 0) {
            r4 = null;
        } else {
            C01720Lc r8 = new C01720Lc();
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            ((AnonymousClass07D) r8).A00 = byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position();
            ((AnonymousClass07D) r8).A01 = byteBuffer;
            r4 = null;
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
                        int i3 = A002 + ((AnonymousClass07D) r8).A00;
                        int i4 = ((AnonymousClass07D) r8).A01.getInt(i3 + ((AnonymousClass07D) r8).A01.getInt(i3));
                        if (i4 <= 50) {
                            for (int i5 = 0; i5 < i4; i5++) {
                                C02930b6 r10 = new C02930b6();
                                int A003 = r8.A00(32);
                                if (A003 != 0) {
                                    int A012 = r8.A01(A003) + (i5 << 2);
                                    int i6 = A012 + ((AnonymousClass07D) r8).A01.getInt(A012);
                                    ByteBuffer byteBuffer2 = ((AnonymousClass07D) r8).A01;
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
                    r4 = r8;
                } else {
                    r8.A03();
                    r8.A04();
                }
            } catch (IndexOutOfBoundsException e) {
                if (AnonymousClass0NK.A01.A5V(5)) {
                    AnonymousClass0NK.A01.A8n("FBMobileConfigTableOptimized", "Corrupted file, unexpected fbs offset", e);
                }
            }
        }
        this.A03 = r4;
        int i7 = 0;
        if (r4 != null) {
            try {
                int A006 = r4.A00(26);
                if (A006 != 0) {
                    int i8 = A006 + ((AnonymousClass07D) r4).A00;
                    i7 = ((AnonymousClass07D) r4).A01.getInt(i8 + ((AnonymousClass07D) r4).A01.getInt(i8));
                }
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
                this.A01 = new AnonymousClass15S(0);
                r0 = new AnonymousClass15S(0);
            } catch (Throwable th) {
                this.A01 = new AnonymousClass15S(0);
                this.A02 = new AnonymousClass15S(0);
                throw th;
            }
        }
        this.A01 = new AnonymousClass15S(i7);
        r0 = new AnonymousClass15S(i7);
        this.A02 = r0;
    }

    public static int A00(AnonymousClass12q r5, long j) {
        int A002 = AnonymousClass0T5.A00(j);
        int i = (int) (j & 65535);
        if (A002 == 0) {
            AnonymousClass0NK.A01("MobileConfigContextV2Impl", String.format("Null type specifier is given: %d", Long.valueOf(j)));
            AbstractC07240oz<AbstractC01590Jn> r0 = r5.A00;
            if (r0 != null) {
                r0.get();
            }
        } else if (A002 == 1) {
            C01720Lc r1 = r5.A03;
            int i2 = r1.A00;
            if (i2 != 0) {
                return ((AnonymousClass07D) r1).A01.getInt(r1.A01(i2) + (i << 2));
            }
            return 0;
        } else if (A002 == 2) {
            C01720Lc r12 = r5.A03;
            int i3 = r12.A05;
            if (i3 != 0) {
                return ((AnonymousClass07D) r12).A01.getInt(r12.A01(i3) + (i << 2));
            }
            return 0;
        } else if (A002 == 3) {
            C01720Lc r13 = r5.A03;
            int i4 = r13.A07;
            if (i4 != 0) {
                return ((AnonymousClass07D) r13).A01.getInt(r13.A01(i4) + (i << 2));
            }
            return 0;
        } else if (A002 == 4) {
            try {
                C01720Lc r14 = r5.A03;
                int i5 = r14.A02;
                if (i5 != 0) {
                    return ((AnonymousClass07D) r14).A01.getInt(r14.A01(i5) + (i << 2));
                }
                return 0;
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
            }
        }
        AnonymousClass0NK.A01("MobileConfigContextV2Impl", String.format("Fail to get meta for spec: %d", Long.valueOf(j)));
        AbstractC07240oz<AbstractC01590Jn> r02 = r5.A00;
        if (r02 == null) {
            return -239;
        }
        r02.get();
        return -239;
    }
}
