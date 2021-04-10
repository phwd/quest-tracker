package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.mobileconfig.MobileConfigCxxChangeListener;
import com.facebook.mobileconfig.MobileConfigEmergencyPushChangeListener;
import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: X.bC  reason: case insensitive filesystem */
public final class C0515bC implements AbstractC0166Fr, MobileConfigCxxChangeListener, MobileConfigEmergencyPushChangeListener {
    public AbstractC0168Ft A00;
    public C0893oF A01;
    public C1238vs A02;
    public File A03;
    public boolean A04;
    public final AbstractC0162Fh A05;
    public final Object A06 = new Object();
    public final Set A07 = new HashSet();
    public final AtomicBoolean A08 = new AtomicBoolean(false);
    public final AtomicBoolean A09 = new AtomicBoolean(true);
    public final AtomicBoolean A0A = new AtomicBoolean(false);
    public final AtomicBoolean A0B = new AtomicBoolean(false);
    public final Random A0C = new Random();
    public final AtomicBoolean A0D = new AtomicBoolean(false);
    public volatile AbstractC0166Fr A0E;
    public volatile AbstractC0463a6 A0F;
    public volatile G6 A0G;
    public volatile AtomicReferenceArray A0H;
    public volatile AbstractC0463a6 A0I;

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        if (r2 != null) goto L_0x0017;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0192  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A07() {
        /*
        // Method dump skipped, instructions count: 481
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0515bC.A07():void");
    }

    @Override // X.AbstractC0166Fr
    public final boolean A2I(long j, boolean z, C0167Fs fs) {
        boolean z2;
        AbstractC0166Fr A052;
        A01(j, 1);
        if (this.A0C.nextInt(200000) == 0) {
            z2 = true;
            fs = C0167Fs.A00(fs);
            fs.A02 = true;
        } else {
            z2 = false;
        }
        int i = (int) ((j >>> 32) & 65535);
        if (fs.A01) {
            A052 = A00(i);
        } else {
            A052 = A05(i);
        }
        AbstractC0162Fh fh = this.A05;
        if (!(fh instanceof C0892oE) || !(((C0892oE) fh).A00() instanceof C0889o9)) {
            z = A052.A2I(j, z, fs);
        } else if (fs.A02) {
            fs.A00 = EnumC0169Fu.DEFAULT__ACCESSED_BEFORE_MC_INIT;
        }
        if (z2) {
            fh.syncFetchReason();
        }
        return z;
    }

    @Override // X.AbstractC0166Fr
    public final String A2y(long j, String str, C0167Fs fs) {
        boolean z;
        AbstractC0166Fr A052;
        int length;
        A01(j, 3);
        if (this.A0C.nextInt(200000) == 0) {
            z = true;
            fs = C0167Fs.A00(fs);
            fs.A02 = true;
        } else {
            z = false;
        }
        int i = (int) ((j >>> 32) & 65535);
        if (fs.A01) {
            A052 = A00(i);
        } else {
            A052 = A05(i);
        }
        AbstractC0162Fh fh = this.A05;
        if (!(fh instanceof C0892oE) || !(((C0892oE) fh).A00() instanceof C0889o9)) {
            str = A052.A2y(j, str, fs);
        } else if (fs.A02) {
            fs.A00 = EnumC0169Fu.DEFAULT__ACCESSED_BEFORE_MC_INIT;
        }
        if (z) {
            fh.syncFetchReason();
            if (str != null && (length = str.length()) > 12) {
                str.substring(0, 5);
                str.substring(length - 5, length);
            }
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        if (r3 == null) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0083, code lost:
        r6 = java.lang.Boolean.valueOf(r7.A07());
        r5 = java.lang.Boolean.valueOf(r10.A04);
        r4 = java.lang.Boolean.valueOf(r7 instanceof X.AnonymousClass2Y);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a2, code lost:
        if (X.C0139Dd.A01.A3Y(4) == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a4, code lost:
        X.C0139Dd.A0B("MobileConfigFactoryImpl", com.facebook.common.stringformat.StringFormatUtil.formatStrLocaleSafe("Updated cached latest contextV2 - isValid: %s, isSessionless: %s withTranslationTable: %s", r6, r5, r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ab, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final X.AbstractC0166Fr A00(int r11) {
        /*
        // Method dump skipped, instructions count: 176
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0515bC.A00(int):X.Fr");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A01(long r9, int r11) {
        /*
            r8 = this;
            boolean r4 = r8.A04
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
            X.C0139Dd.A0D(r6, r0)
        L_0x0031:
            int r0 = X.GJ.A00(r9)
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
            java.lang.String r0 = X.AnonymousClass08.A02(r7, r3, r2, r0)
            X.C0139Dd.A0D(r6, r0)
        L_0x0053:
            return
        L_0x0054:
            java.lang.String r0 = "Session-based factory used for sessionless param"
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0515bC.A01(long, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0010, code lost:
        if (r0 != null) goto L_0x0012;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean A03() {
        /*
            r2 = this;
            boolean r0 = r2.A04
            if (r0 != 0) goto L_0x0019
            X.vs r0 = r2.A02
            java.lang.String r1 = ""
            if (r0 == 0) goto L_0x001e
            com.facebook.auth.viewercontext.ViewerContext r0 = X.W4.A00
            if (r0 == 0) goto L_0x001b
            java.lang.String r0 = r0.mUserId
            if (r0 == 0) goto L_0x001e
        L_0x0012:
            boolean r1 = r1.equals(r0)
            r0 = 1
            if (r1 != 0) goto L_0x001a
        L_0x0019:
            r0 = 0
        L_0x001a:
            return r0
        L_0x001b:
            java.lang.String r0 = "0"
            goto L_0x0012
        L_0x001e:
            r0 = r1
            goto L_0x0012
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0515bC.A03():boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0075  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A04(long r10) {
        /*
        // Method dump skipped, instructions count: 330
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0515bC.A04(long):long");
    }

    public final AbstractC0166Fr A05(int i) {
        Object[] objArr;
        String str;
        AtomicReferenceArray atomicReferenceArray = this.A0H;
        if (i < 0 || i >= atomicReferenceArray.length()) {
            objArr = new Object[]{Integer.valueOf(i), Boolean.valueOf(this.A04)};
            str = "Attempt to read invalid config index(%d) from config caches, isSessionless: %s";
        } else {
            AbstractC0166Fr fr = (AbstractC0166Fr) atomicReferenceArray.get(i);
            if (fr != null) {
                return fr;
            }
            if (!this.A0D.get() || !A03()) {
                AbstractC0166Fr A002 = A00(i);
                if (!atomicReferenceArray.compareAndSet(i, fr, A002)) {
                    return (AbstractC0166Fr) atomicReferenceArray.get(i);
                }
                return A002;
            }
            objArr = new Object[]{Integer.valueOf(i)};
            str = "Attempt to read config (index:%d) after logout, see https://fburl.com/bicj8iz0";
        }
        C0139Dd.A0P("MobileConfigFactoryImpl", str, objArr);
        return C0519bH.A00();
    }

    public final String A06(long j) {
        String str;
        C0167Fs fs = C0167Fs.A05;
        int i = (int) (j & 65535);
        if (i == 10) {
            str = "Lorem Ipsum";
        } else if (i != 12) {
            switch (i) {
                case 1:
                    str = "[code] 190";
                    break;
                case 2:
                    str = "The session has been invalidated because the user changed their password;Session has expired;Session is invalid;The last reauth timestamp for this work user has expired";
                    break;
                case 3:
                    str = "inline";
                    break;
                case 4:
                    str = "MQTT_SUBSCRIPTION";
                    break;
                case 5:
                    str = "Welcome/Privacy/DoublePress/DoublePressFeedback/Time/Photo/Help";
                    break;
                case 6:
                    str = "byte_aware_oculus_v1";
                    break;
                default:
                    str = OacrConstants.AUTO_SPEECH_DOMAIN;
                    break;
            }
        } else {
            str = "MobileConfig is a cross-platform framework for Android and iOS apps to access server-side configurations";
        }
        return A2y(j, str, fs);
    }

    public final boolean A08(long j) {
        C0167Fs fs = C0167Fs.A05;
        boolean z = false;
        if (((j >> 61) & 1) == 1) {
            z = true;
        }
        return A2I(j, z, fs);
    }

    @Override // X.AbstractC0166Fr
    public final long A2c(long j, long j2, C0167Fs fs) {
        boolean z;
        AbstractC0166Fr A052;
        C0167Fs fs2 = fs;
        long j3 = j2;
        A01(j, 2);
        if (this.A0C.nextInt(200000) == 0) {
            z = true;
            fs2 = C0167Fs.A00(fs2);
            fs2.A02 = true;
        } else {
            z = false;
        }
        int i = (int) ((j >>> 32) & 65535);
        if (fs2.A01) {
            A052 = A00(i);
        } else {
            A052 = A05(i);
        }
        AbstractC0162Fh fh = this.A05;
        if (!(fh instanceof C0892oE) || !(((C0892oE) fh).A00() instanceof C0889o9)) {
            j3 = A052.A2c(j, j3, fs2);
        } else if (fs2.A02) {
            fs2.A00 = EnumC0169Fu.DEFAULT__ACCESSED_BEFORE_MC_INIT;
        }
        if (z) {
            fh.syncFetchReason();
        }
        return j3;
    }

    @Override // com.facebook.mobileconfig.MobileConfigCxxChangeListener
    public final void onConfigChanged(String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            synchronized (this) {
                this.A0E = null;
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
        throw new UnsupportedOperationException("Method not decompiled: X.C0515bC.onEpConfigChanged(java.lang.String[], java.lang.String[]):boolean");
    }

    public C0515bC(AbstractC0162Fh fh) {
        this.A05 = fh;
        this.A00 = fh.getNewOverridesTableIfExists();
        this.A0E = null;
        this.A0H = new AtomicReferenceArray((int) AbstractC0447Yr.LOG_RATE_LIMIT);
    }

    public static void A02(C0515bC bCVar, boolean z) {
        boolean A032 = bCVar.A03();
        C0139Dd.A0I("MobileConfigFactoryImpl", "refreshSessionState refreshGK: %s isLoggedOut: %s", Boolean.valueOf(z), Boolean.valueOf(A032));
        synchronized (bCVar) {
            bCVar.A00 = bCVar.A05.getNewOverridesTableIfExists();
            bCVar.A07.clear();
            bCVar.A0H = new AtomicReferenceArray((int) AbstractC0447Yr.LOG_RATE_LIMIT);
            bCVar.A0E = null;
            if (bCVar.A0D.get() && A032) {
                bCVar.A0A.set(false);
                bCVar.A0B.set(false);
                bCVar.A09.set(true);
                bCVar.A08.set(false);
            }
        }
    }
}
