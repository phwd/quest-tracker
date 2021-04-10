package X;

import android.content.res.AssetManager;
import com.facebook.mobileconfig.MobileConfigCxxChangeListener;
import com.facebook.mobileconfig.MobileConfigEmergencyPushChangeListener;
import com.facebook.mobileconfig.impl.MobileConfigApi2Logger;
import com.oculus.auth.credentials.Credentials;
import com.oculus.mobileconfig.init.MobileConfigInitModule;
import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;

public final class GA implements MobileConfigCxxChangeListener, MobileConfigEmergencyPushChangeListener, AbstractC0234Xa {
    @Nullable
    public AssetManager A00;
    public Re A01;
    public XW A02;
    @Nullable
    public MobileConfigInitModule.AnonymousClass1 A03;
    public File A04;
    public boolean A05;
    public final RU A06;
    public final Object A07 = new Object();
    public final AtomicBoolean A08 = new AtomicBoolean(false);
    public final AtomicBoolean A09 = new AtomicBoolean(true);
    public final AtomicBoolean A0A = new AtomicBoolean(false);
    public final AtomicBoolean A0B = new AtomicBoolean(false);
    public final Random A0C = new Random();
    public final Set<Rc> A0D = new HashSet();
    public final AtomicBoolean A0E = new AtomicBoolean(false);
    @Nullable
    public volatile eJ<Ix> A0F;
    public volatile Rc A0G;
    @Nullable
    public volatile AbstractC0141Rx A0H;
    public volatile AtomicReferenceArray<Rc> A0I;
    @Nullable
    public volatile eJ<MobileConfigApi2Logger> A0J;

    /* JADX WARNING: Removed duplicated region for block: B:94:0x017c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A06() {
        /*
        // Method dump skipped, instructions count: 439
        */
        throw new UnsupportedOperationException("Method not decompiled: X.GA.A06():void");
    }

    @Override // X.Rc
    public final boolean A2N(long j, boolean z, Rd rd) {
        boolean z2;
        Rc A052;
        A02(j, 1);
        if (this.A0C.nextInt(200000) == 0) {
            z2 = true;
            rd = Rd.A00(rd);
            rd.A01 = true;
        } else {
            z2 = false;
        }
        int i = (int) ((j >>> 32) & 65535);
        if (rd.A00) {
            A052 = A00(i);
        } else {
            A052 = A05(i);
        }
        RU ru = this.A06;
        if (!(ru instanceof XX) || !(((XX) ru).A00() instanceof C0235Xd)) {
            z = A052.A2N(j, z, rd);
        }
        if (z2) {
            ru.syncFetchReason();
        }
        return z;
    }

    @Override // X.Rc
    public final long A2c(long j, Rd rd) {
        return A2b(j, C0478qP.A00(j), rd);
    }

    @Override // X.Rc
    public final String A2v(long j, String str, Rd rd) {
        boolean z;
        Rc A052;
        int length;
        A02(j, 3);
        if (this.A0C.nextInt(200000) == 0) {
            z = true;
            rd = Rd.A00(rd);
            rd.A01 = true;
        } else {
            z = false;
        }
        int i = (int) ((j >>> 32) & 65535);
        if (rd.A00) {
            A052 = A00(i);
        } else {
            A052 = A05(i);
        }
        RU ru = this.A06;
        if (!(ru instanceof XX) || !(((XX) ru).A00() instanceof C0235Xd)) {
            str = A052.A2v(j, str, rd);
        }
        if (z) {
            ru.syncFetchReason();
            if (str != null && (length = str.length()) > 12) {
                str.substring(0, 5);
                str.substring(length - 5, length);
            }
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0040, code lost:
        if (r4 == null) goto L_0x0042;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final X.Rc A00(int r10) {
        /*
        // Method dump skipped, instructions count: 132
        */
        throw new UnsupportedOperationException("Method not decompiled: X.GA.A00(int):X.Rc");
    }

    private String A01() {
        String str;
        MobileConfigInitModule.AnonymousClass1 r2 = this.A03;
        if (r2 == null) {
            return "";
        }
        if (AbstractC0246Xt.this.get() != null) {
            str = ((Credentials) AbstractC0246Xt.this.get()).mUserId;
        } else {
            str = "";
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A02(long r7, int r9) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: X.GA.A02(long, int):void");
    }

    private boolean A04() {
        if (this.A05 || !"".equals(A01())) {
            return false;
        }
        return true;
    }

    public final Rc A05(int i) {
        Object[] objArr;
        String str;
        AtomicReferenceArray<Rc> atomicReferenceArray = this.A0I;
        if (i < 0 || i >= atomicReferenceArray.length()) {
            objArr = new Object[]{Integer.valueOf(i), Boolean.valueOf(this.A05)};
            str = "Attempt to read invalid config index(%d) from config caches, isSessionless: %s";
        } else {
            Rc rc = atomicReferenceArray.get(i);
            if (rc != null) {
                return rc;
            }
            if (!this.A0E.get() || !A04()) {
                Rc A002 = A00(i);
                if (!atomicReferenceArray.compareAndSet(i, rc, A002)) {
                    return atomicReferenceArray.get(i);
                }
                return A002;
            }
            objArr = new Object[]{Integer.valueOf(i)};
            str = "Attempt to read config (index:%d) after logout, see https://fburl.com/bicj8iz0";
        }
        Mu.A06("MobileConfigFactoryImpl", str, objArr);
        return GD.A00();
    }

    @Override // X.Rc
    public final boolean A2L(long j) {
        return A2M(j, Rd.A04);
    }

    @Override // X.Rc
    public final long A2b(long j, long j2, Rd rd) {
        boolean z;
        Rc A052;
        Rd rd2 = rd;
        long j3 = j2;
        A02(j, 2);
        if (this.A0C.nextInt(200000) == 0) {
            z = true;
            rd2 = Rd.A00(rd2);
            rd2.A01 = true;
        } else {
            z = false;
        }
        int i = (int) ((j >>> 32) & 65535);
        if (rd2.A00) {
            A052 = A00(i);
        } else {
            A052 = A05(i);
        }
        RU ru = this.A06;
        if (!(ru instanceof XX) || !(((XX) ru).A00() instanceof C0235Xd)) {
            j3 = A052.A2b(j, j3, rd2);
        }
        if (z) {
            ru.syncFetchReason();
        }
        return j3;
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
        throw new UnsupportedOperationException("Method not decompiled: X.GA.onEpConfigChanged(java.lang.String[], java.lang.String[]):boolean");
    }

    public GA(RU ru) {
        this.A06 = ru;
        this.A01 = ru.getNewOverridesTableIfExists();
        this.A0G = null;
        this.A0I = new AtomicReferenceArray<>(10000);
    }

    public static void A03(GA ga) {
        boolean A042 = ga.A04();
        synchronized (ga) {
            ga.A01 = ga.A06.getNewOverridesTableIfExists();
            ga.A0D.clear();
            ga.A0I = new AtomicReferenceArray<>(10000);
            ga.A0G = null;
            if (ga.A0E.get() && A042) {
                ga.A0A.set(false);
                ga.A0B.set(false);
                ga.A09.set(true);
                ga.A08.set(false);
            }
        }
    }

    @Override // X.Rc
    public final boolean A2M(long j, Rd rd) {
        return A2N(j, SL.A01(j), rd);
    }

    @Override // X.Rc
    public final String A2u(long j, Rd rd) {
        return A2v(j, C0478qP.A01(j), rd);
    }
}
