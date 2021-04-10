package X;

import android.content.res.AssetManager;
import com.facebook.mobileconfig.MobileConfigCxxChangeListener;
import com.facebook.mobileconfig.MobileConfigEmergencyPushChangeListener;
import com.facebook.mobileconfig.impl.MobileConfigApi2Logger;
import com.oculus.horizon.notifications.core.NotificationBuilder;
import com.oculus.horizon.service_media.OVRMediaServiceManager;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;
import javax.inject.Provider;

/* renamed from: X.1ar  reason: invalid class name */
public final class AnonymousClass1ar implements MobileConfigCxxChangeListener, MobileConfigEmergencyPushChangeListener, AbstractC06600ny {
    @Nullable
    public AssetManager A00;
    @Nullable
    public AnonymousClass0Ra A01;
    public AnonymousClass0Ri A02;
    public AnonymousClass1Af A03;
    public File A04;
    public boolean A05;
    public final AnonymousClass0RX A06;
    public final Object A07 = new Object();
    public final AtomicBoolean A08 = new AtomicBoolean(false);
    public final AtomicBoolean A09 = new AtomicBoolean(true);
    public final AtomicBoolean A0A = new AtomicBoolean(false);
    public final AtomicBoolean A0B = new AtomicBoolean(false);
    public final Random A0C = new Random();
    public final Set<AnonymousClass0Rg> A0D = new HashSet();
    public final AtomicBoolean A0E = new AtomicBoolean(false);
    @Nullable
    public volatile Provider<AbstractC01060Jr> A0F;
    public volatile AnonymousClass0Rg A0G;
    @Nullable
    public volatile AnonymousClass1bb A0H;
    public volatile AtomicReferenceArray<AnonymousClass0Rg> A0I;
    @Nullable
    public volatile Provider<MobileConfigApi2Logger> A0J;

    /* JADX WARNING: Removed duplicated region for block: B:97:0x0181  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A07() {
        /*
        // Method dump skipped, instructions count: 440
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1ar.A07():void");
    }

    @Override // X.AnonymousClass0Rg
    public final boolean A37(long j, boolean z) {
        A02(j, 1);
        return A01(j).A37(j, z);
    }

    @Override // X.AnonymousClass0Rg
    public final boolean A3B(long j, boolean z, AnonymousClass0Rh r8) {
        boolean z2;
        AnonymousClass0Rg A012;
        A02(j, 1);
        if (this.A0C.nextInt(200000) == 0) {
            z2 = true;
            r8 = r8.A01();
        } else {
            z2 = false;
        }
        if (r8.A01) {
            A012 = A06(j);
        } else {
            A012 = A01(j);
        }
        AnonymousClass0RX r1 = this.A06;
        if (!(r1 instanceof C09311at) || !(((C09311at) r1).A00() instanceof AnonymousClass0o1)) {
            z = A012.A3B(j, z, r8);
        } else if (r8.A02) {
            r8.A00 = AnonymousClass0Rj.DEFAULT__ACCESSED_BEFORE_MC_INIT;
        }
        if (z2) {
            r1.syncFetchReason();
        }
        return z;
    }

    @Override // X.AnonymousClass0Rg
    public final double A3J(long j, double d, AnonymousClass0Rh r14) {
        boolean z;
        AnonymousClass0Rg A012;
        AnonymousClass0Rh r8 = r14;
        double d2 = d;
        A02(j, 4);
        if (this.A0C.nextInt(200000) == 0) {
            z = true;
            r8 = r14.A01();
        } else {
            z = false;
        }
        if (r8.A01) {
            A012 = A06(j);
        } else {
            A012 = A01(j);
        }
        AnonymousClass0RX r1 = this.A06;
        if (!(r1 instanceof C09311at) || !(((C09311at) r1).A00() instanceof AnonymousClass0o1)) {
            d2 = A012.A3J(j, d2, r8);
        } else if (r8.A02) {
            r8.A00 = AnonymousClass0Rj.DEFAULT__ACCESSED_BEFORE_MC_INIT;
        }
        if (z) {
            r1.syncFetchReason();
        }
        return d2;
    }

    @Override // X.AnonymousClass0Rg
    public final int A3d(long j, int i) {
        A02(j, 2);
        return A01(j).A3d(j, i);
    }

    @Override // X.AnonymousClass0Rg
    public final long A3m(long j, long j2, AnonymousClass0Rh r14) {
        boolean z;
        AnonymousClass0Rg A012;
        AnonymousClass0Rh r8 = r14;
        long j3 = j2;
        A02(j, 2);
        if (this.A0C.nextInt(200000) == 0) {
            z = true;
            r8 = r14.A01();
        } else {
            z = false;
        }
        if (r8.A01) {
            A012 = A06(j);
        } else {
            A012 = A01(j);
        }
        AnonymousClass0RX r1 = this.A06;
        if (!(r1 instanceof C09311at) || !(((C09311at) r1).A00() instanceof AnonymousClass0o1)) {
            j3 = A012.A3m(j, j3, r8);
        } else if (r8.A02) {
            r8.A00 = AnonymousClass0Rj.DEFAULT__ACCESSED_BEFORE_MC_INIT;
        }
        if (z) {
            r1.syncFetchReason();
        }
        return j3;
    }

    @Override // X.AnonymousClass0Rg
    public final long A3n(long j, AnonymousClass0Rh r9) {
        return A3m(j, AnonymousClass1bY.A00(j), r9);
    }

    @Override // X.AnonymousClass0Rg
    public final String A4T(long j, String str, AnonymousClass0Rh r8) {
        boolean z;
        AnonymousClass0Rg A012;
        int length;
        A02(j, 3);
        if (this.A0C.nextInt(200000) == 0) {
            z = true;
            r8 = r8.A01();
        } else {
            z = false;
        }
        if (r8.A01) {
            A012 = A06(j);
        } else {
            A012 = A01(j);
        }
        AnonymousClass0RX r1 = this.A06;
        if (!(r1 instanceof C09311at) || !(((C09311at) r1).A00() instanceof AnonymousClass0o1)) {
            str = A012.A4T(j, str, r8);
        } else if (r8.A02) {
            r8.A00 = AnonymousClass0Rj.DEFAULT__ACCESSED_BEFORE_MC_INIT;
        }
        if (z) {
            r1.syncFetchReason();
            if (str != null && (length = str.length()) > 12) {
                str.substring(0, 5);
                str.substring(length - 5, length);
            }
        }
        return str;
    }

    private final AnonymousClass0Rg A00(int i) {
        AnonymousClass0RZ r7;
        ByteBuffer byteBuffer;
        boolean z = false;
        if (!this.A0E.get() || !A04()) {
            A07();
            AnonymousClass0Rg r1 = this.A0G;
            if (r1 != null) {
                return r1;
            }
            synchronized (this) {
                AnonymousClass0Rg r0 = this.A0G;
                if (r0 != null) {
                    return r0;
                }
                AnonymousClass0RX r6 = this.A06;
                if (r6 != null) {
                    r7 = r6.getLatestHandle();
                } else {
                    r7 = null;
                }
                if (r7 != null) {
                    byteBuffer = r7.getJavaByteBuffer();
                } else {
                    byteBuffer = null;
                }
                if (byteBuffer == null) {
                    Object[] objArr = new Object[2];
                    objArr[0] = Boolean.valueOf(this.A05);
                    if (r7 == null) {
                        z = true;
                    }
                    objArr[1] = Boolean.valueOf(z);
                    AnonymousClass0NO.A0E("MobileConfigFactoryImpl", "Unable to enable contextV2 due to null buffer - sessionless: %s, handleHolder is null: %b", objArr);
                }
                if (r6 instanceof C09311at) {
                    C09311at r12 = (C09311at) r6;
                    if (r12.A00() instanceof C09321au) {
                        r12.A00();
                    }
                }
                C09331av r13 = new C09331av(byteBuffer, r6, this.A02, null);
                this.A0G = r13;
                this.A0D.add(r13);
                return r13;
            }
        }
        AnonymousClass0NO.A0F("MobileConfigFactoryImpl", "Attempt to read config (index:%d) after logout, see https://fburl.com/bicj8iz0", Integer.valueOf(i));
        return C09341bP.A00();
    }

    private AnonymousClass0Rg A01(long j) {
        return A05((int) ((j >>> 32) & 65535));
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A02(long r9, int r11) {
        /*
            r8 = this;
            boolean r4 = r8.A05
            r0 = 54
            long r2 = r9 >>> r0
            r0 = 63
            long r2 = r2 & r0
            int r1 = (int) r2
            if (r1 == 0) goto L_0x0025
            r0 = 1
            if (r1 == r0) goto L_0x0026
            r0 = 2
            if (r1 == r0) goto L_0x0025
            r0 = 3
            if (r1 == r0) goto L_0x0025
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r0 = "%d is not a MobileConfigUnitType"
            java.lang.String r1 = com.facebook.common.stringformat.StringFormatUtil.formatStrLocaleSafe(r0, r1)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r1)
            throw r0
        L_0x0025:
            r0 = 0
        L_0x0026:
            java.lang.String r6 = "MobileConfigFactoryImpl"
            if (r4 == r0) goto L_0x0031
            if (r4 == 0) goto L_0x0054
            java.lang.String r0 = "Sessionless factory used for session-based param"
        L_0x002e:
            X.AnonymousClass0NO.A09(r6, r0)
        L_0x0031:
            int r0 = X.C01340Rx.A00(r9)
            if (r11 == r0) goto L_0x0053
            java.lang.String r7 = "Invalid param type used for config: "
            r0 = 32
            long r4 = r9 >>> r0
            r0 = 65535(0xffff, double:3.23786E-319)
            long r4 = r4 & r0
            int r3 = (int) r4
            java.lang.String r2 = ", param: "
            r0 = 16
            long r9 = r9 >>> r0
            r0 = 65535(0xffff, double:3.23786E-319)
            long r9 = r9 & r0
            int r0 = (int) r9
            java.lang.String r0 = X.AnonymousClass006.A03(r7, r3, r2, r0)
            X.AnonymousClass0NO.A09(r6, r0)
        L_0x0053:
            return
        L_0x0054:
            java.lang.String r0 = "Session-based factory used for sessionless param"
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1ar.A02(long, int):void");
    }

    private boolean A04() {
        String str;
        if (!this.A05) {
            AnonymousClass0Ra r0 = this.A01;
            if (r0 == null || (str = r0.getUserId()) == null) {
                str = "";
            }
            if (!"".equals(str)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final AnonymousClass0Rg A05(int i) {
        Object[] objArr;
        String str;
        AtomicReferenceArray<AnonymousClass0Rg> atomicReferenceArray = this.A0I;
        if (i < 0 || i >= atomicReferenceArray.length()) {
            objArr = new Object[]{Integer.valueOf(i), Boolean.valueOf(this.A05)};
            str = "Attempt to read invalid config index(%d) from config caches, isSessionless: %s";
        } else {
            AnonymousClass0Rg r0 = atomicReferenceArray.get(i);
            if (r0 != null) {
                return r0;
            }
            if (!this.A0E.get() || !A04()) {
                AnonymousClass0Rg A002 = A00(i);
                if (!atomicReferenceArray.compareAndSet(i, r0, A002)) {
                    return atomicReferenceArray.get(i);
                }
                return A002;
            }
            objArr = new Object[]{Integer.valueOf(i)};
            str = "Attempt to read config (index:%d) after logout, see https://fburl.com/bicj8iz0";
        }
        AnonymousClass0NO.A0F("MobileConfigFactoryImpl", str, objArr);
        return C09341bP.A00();
    }

    public final AnonymousClass0Rg A06(long j) {
        return A00((int) ((j >>> 32) & 65535));
    }

    @Override // X.AnonymousClass0Rg
    public final boolean A36(long j) {
        return A3A(j, AnonymousClass0Rh.A05);
    }

    @Override // X.AnonymousClass0Rg
    public final boolean A3A(long j, AnonymousClass0Rh r9) {
        boolean z = false;
        if (((j >> 61) & 1) == 1) {
            z = true;
        }
        return A3B(j, z, r9);
    }

    @Override // X.AnonymousClass0Rg
    public final long A3k(long j) {
        return A3n(j, AnonymousClass0Rh.A05);
    }

    @Override // com.facebook.mobileconfig.MobileConfigCxxChangeListener
    public final void onConfigChanged(String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            synchronized (this) {
                this.A0G = null;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0059, code lost:
        if (java.lang.Integer.parseInt(r1) == 0) goto L_0x005b;
     */
    @Override // com.facebook.mobileconfig.MobileConfigEmergencyPushChangeListener
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onEpConfigChanged(java.lang.String[] r18, java.lang.String[] r19) {
        /*
        // Method dump skipped, instructions count: 219
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1ar.onEpConfigChanged(java.lang.String[], java.lang.String[]):boolean");
    }

    public AnonymousClass1ar(AnonymousClass0RX r4) {
        this.A06 = r4;
        this.A02 = r4.getNewOverridesTableIfExists();
        this.A0G = null;
        this.A0I = new AtomicReferenceArray<>((int) NotificationBuilder.CANCELLABLE_NOTIFICATION_FIRST_ID);
    }

    public static void A03(AnonymousClass1ar r4) {
        boolean A042 = r4.A04();
        synchronized (r4) {
            r4.A02 = r4.A06.getNewOverridesTableIfExists();
            r4.A0D.clear();
            r4.A0I = new AtomicReferenceArray<>((int) NotificationBuilder.CANCELLABLE_NOTIFICATION_FIRST_ID);
            r4.A0G = null;
            if (r4.A0E.get() && A042) {
                r4.A0A.set(false);
                r4.A0B.set(false);
                r4.A09.set(true);
                r4.A08.set(false);
            }
        }
    }

    @Override // X.AnonymousClass0Rg
    public final double A3K(long j, AnonymousClass0Rh r12) {
        double d;
        int i = (int) (j & 65535);
        if (i == 0) {
            d = -142.5d;
        } else if (i == 1 || i != 2) {
            d = OVRMediaServiceManager.SCREENSHOT_SHORTCUT_DELAY;
        } else {
            d = 9.876543210125E9d;
        }
        return A3J(j, d, r12);
    }

    @Override // X.AnonymousClass0Rg
    public final String A4S(long j, AnonymousClass0Rh r4) {
        return A4T(j, AnonymousClass1bY.A01(j), r4);
    }
}
