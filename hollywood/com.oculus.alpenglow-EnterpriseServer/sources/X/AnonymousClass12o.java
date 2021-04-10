package X;

import android.content.res.AssetManager;
import com.facebook.mobileconfig.MobileConfigCxxChangeListener;
import com.facebook.mobileconfig.MobileConfigEmergencyPushChangeListener;
import com.facebook.mobileconfig.impl.MobileConfigApi2Logger;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;

/* renamed from: X.12o  reason: invalid class name */
public final class AnonymousClass12o implements MobileConfigCxxChangeListener, MobileConfigEmergencyPushChangeListener, AbstractC02890az {
    @Nullable
    public AssetManager A00;
    @Nullable
    public AnonymousClass0Sj A01;
    public AnonymousClass0Sr A02;
    public AnonymousClass15y A03;
    public File A04;
    public boolean A05;
    public final AnonymousClass0ST A06;
    public final Object A07 = new Object();
    public final AtomicBoolean A08 = new AtomicBoolean(false);
    public final AtomicBoolean A09 = new AtomicBoolean(true);
    public final AtomicBoolean A0A = new AtomicBoolean(false);
    public final AtomicBoolean A0B = new AtomicBoolean(false);
    public final Random A0C = new Random();
    public final Set<AnonymousClass0Sp> A0D = new HashSet();
    public final AtomicBoolean A0E = new AtomicBoolean(false);
    @Nullable
    public volatile AbstractC07240oz<AbstractC01590Jn> A0F;
    public volatile AnonymousClass0Sp A0G;
    @Nullable
    public volatile AnonymousClass15K A0H;
    public volatile AtomicReferenceArray<AnonymousClass0Sp> A0I;
    @Nullable
    public volatile AbstractC07240oz<MobileConfigApi2Logger> A0J;

    /* JADX WARNING: Removed duplicated region for block: B:97:0x0181  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04() {
        /*
        // Method dump skipped, instructions count: 440
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass12o.A04():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009a  */
    @Override // X.AnonymousClass0Sp
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A39(long r11, boolean r13, X.AnonymousClass0Sq r14) {
        /*
        // Method dump skipped, instructions count: 159
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass12o.A39(long, boolean, X.0Sq):boolean");
    }

    private final AnonymousClass0Sp A00(int i) {
        AnonymousClass0SV r5;
        ByteBuffer byteBuffer;
        boolean z = false;
        if (!this.A0E.get() || !A02()) {
            A04();
            AnonymousClass0Sp r1 = this.A0G;
            if (r1 != null) {
                return r1;
            }
            synchronized (this) {
                AnonymousClass0Sp r0 = this.A0G;
                if (r0 != null) {
                    return r0;
                }
                AnonymousClass0ST r7 = this.A06;
                if (r7 != null) {
                    r5 = r7.getLatestHandle();
                } else {
                    r5 = null;
                }
                if (r5 != null) {
                    byteBuffer = r5.getJavaByteBuffer();
                } else {
                    byteBuffer = null;
                }
                if (byteBuffer == null) {
                    Boolean valueOf = Boolean.valueOf(this.A05);
                    if (r5 == null) {
                        z = true;
                    }
                    AnonymousClass0NK.A06("MobileConfigFactoryImpl", "Unable to enable contextV2 due to null buffer - sessionless: %s, handleHolder is null: %b", valueOf, Boolean.valueOf(z));
                }
                if (r7 instanceof AnonymousClass12r) {
                    AnonymousClass12r r12 = (AnonymousClass12r) r7;
                    if (r12.A00() instanceof AnonymousClass12p) {
                        r12.A00();
                    }
                }
                AnonymousClass12q r13 = new AnonymousClass12q(byteBuffer, r7, this.A02, null);
                this.A0G = r13;
                this.A0D.add(r13);
                return r13;
            }
        }
        AnonymousClass0NK.A07("MobileConfigFactoryImpl", "Attempt to read config (index:%d) after logout, see https://fburl.com/bicj8iz0", Integer.valueOf(i));
        return AnonymousClass13u.A00();
    }

    private boolean A02() {
        String str;
        if (!this.A05) {
            AnonymousClass0Sj r0 = this.A01;
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

    public final AnonymousClass0Sp A03(int i) {
        Object[] objArr;
        String str;
        AtomicReferenceArray<AnonymousClass0Sp> atomicReferenceArray = this.A0I;
        if (i < 0 || i >= atomicReferenceArray.length()) {
            objArr = new Object[]{Integer.valueOf(i), Boolean.valueOf(this.A05)};
            str = "Attempt to read invalid config index(%d) from config caches, isSessionless: %s";
        } else {
            AnonymousClass0Sp r0 = atomicReferenceArray.get(i);
            if (r0 != null) {
                return r0;
            }
            if (!this.A0E.get() || !A02()) {
                AnonymousClass0Sp A002 = A00(i);
                if (!atomicReferenceArray.compareAndSet(i, r0, A002)) {
                    return atomicReferenceArray.get(i);
                }
                return A002;
            }
            objArr = new Object[]{Integer.valueOf(i)};
            str = "Attempt to read config (index:%d) after logout, see https://fburl.com/bicj8iz0";
        }
        AnonymousClass0NK.A07("MobileConfigFactoryImpl", str, objArr);
        return AnonymousClass13u.A00();
    }

    @Override // X.AnonymousClass0Sp
    public final boolean A36(long j) {
        return A38(j, AnonymousClass0Sq.A04);
    }

    @Override // X.AnonymousClass0Sp
    public final boolean A38(long j, AnonymousClass0Sq r9) {
        boolean z = false;
        if (((j >> 61) & 1) == 1) {
            z = true;
        }
        return A39(j, z, r9);
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass12o.onEpConfigChanged(java.lang.String[], java.lang.String[]):boolean");
    }

    public AnonymousClass12o(AnonymousClass0ST r4) {
        this.A06 = r4;
        this.A02 = r4.getNewOverridesTableIfExists();
        this.A0G = null;
        this.A0I = new AtomicReferenceArray<>(10000);
    }

    public static void A01(AnonymousClass12o r4) {
        boolean A022 = r4.A02();
        synchronized (r4) {
            r4.A02 = r4.A06.getNewOverridesTableIfExists();
            r4.A0D.clear();
            r4.A0I = new AtomicReferenceArray<>(10000);
            r4.A0G = null;
            if (r4.A0E.get() && A022) {
                r4.A0A.set(false);
                r4.A0B.set(false);
                r4.A09.set(true);
                r4.A08.set(false);
            }
        }
    }
}
