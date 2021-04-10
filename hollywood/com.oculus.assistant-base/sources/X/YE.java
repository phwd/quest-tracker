package X;

import android.os.Process;
import com.facebook.acra.LogCatCollector;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.common.perftest.base.PerfTestConfigBase;
import com.facebook.common.util.TriState;
import com.facebook.quicklog.QuickPerformanceLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public final class YE implements QuickPerformanceLogger {
    public RunnableC0929p4 A00;
    public final Cb A01;
    public final C0914op A02;
    public final C0916or A03;
    public final IZ A04;
    public final C0925p0 A05;
    public final C0931p6 A06 = new C0931p6(this);
    public final Random A07 = new Random();
    public final AbstractC0463a6 A08;
    public final C0786hL A09;
    public final C0832jW A0A;
    public final C0919ou A0B;
    public final IU A0C;
    public final C0924oz A0D;
    public final AbstractC0185Iv A0E;
    public final C0938pF A0F;
    public final C0939pG A0G;
    public final C0940pH A0H;
    public volatile TriState A0I;
    public volatile TriState A0J;
    public volatile TriState A0K;
    public volatile Ig A0L;
    public final IP[] mDataProviders;
    public final IQ[] mEventDecorators;

    private final void A03(int i, int i2, long j, TimeUnit timeUnit, int i3) {
        boolean z;
        int A002;
        long A003;
        boolean z2 = false;
        if (j == -1) {
            z2 = true;
        }
        long A012 = A01(this, j, timeUnit);
        TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
        if (isMarkerOn(i, i2)) {
            IZ iz = this.A04;
            long currentTimeMillis = System.currentTimeMillis();
            int i4 = i ^ (i2 * 179426549);
            IL il = iz.A03;
            if (il.A03(i4)) {
                IU iu = iz.A04;
                iu.A00();
                try {
                    RunnableC0929p4 A013 = il.A01(i4);
                    if (IL.A00(A013)) {
                        A013.A09 = timeUnit2.toNanos(A012);
                        A013.A0J = z2;
                        timeUnit2.toNanos(A012);
                        A013.A0A = currentTimeMillis;
                        IN in = A013.A0L;
                        in.A02 = 0;
                        in.A03.clear();
                        in.A04.clear();
                        in.A01 = 0;
                        in.A00 = 0;
                        A013.A0M.clear();
                        A013.A03 = 0 | A013.A03;
                        Ix ix = A013.A0E;
                        if (ix != null) {
                            IZ.A02(iz, ix, A013.A08);
                        }
                        A013.A0E = IZ.A00(iz, A013.A08);
                        A013.A04++;
                        return;
                    }
                    iu.unlock();
                } finally {
                    iu.unlock();
                }
            }
        }
        if (A0J(this) || A0I() || CR.A00) {
            z = true;
            A002 = A00(i);
        } else {
            z = false;
            A002 = 1;
        }
        if (A002 != Integer.MAX_VALUE) {
            if ((i3 & 8) == 8) {
                A003 = 0;
            } else {
                A003 = this.A02.A00.A00(i);
            }
            int nextInt = this.A07.nextInt(Integer.MAX_VALUE);
            long currentTimeMillis2 = System.currentTimeMillis();
            RunnableC0929p4 p4Var = new RunnableC0929p4();
            p4Var.A02 = i;
            p4Var.A05 = A002;
            p4Var.A08 = A003;
            p4Var.A0I = z;
            p4Var.A09 = timeUnit2.toNanos(A012);
            p4Var.A0J = z2;
            timeUnit2.toNanos(A012);
            p4Var.A0A = currentTimeMillis2;
            p4Var.A01 = i2;
            p4Var.A06 = nextInt;
            p4Var.A00 = i3;
            p4Var.A0H = true;
            p4Var.A07 = 0;
            A0F(this, "onMarkerStart", i, null, null);
            IZ iz2 = this.A04;
            int i5 = p4Var.A02 ^ (p4Var.A01 * 179426549);
            Ix A004 = IZ.A00(iz2, p4Var.A08);
            IU iu2 = iz2.A04;
            iu2.A00();
            try {
                p4Var.A0E = A004;
                p4Var.A03 = 0;
                iz2.A00 = p4Var;
                IL il2 = iz2.A03;
                iu2 = il2.A00;
                iu2.lock();
                try {
                    Ix ix2 = il2.A01;
                    int indexOfKey = ix2.indexOfKey(i5);
                    if (indexOfKey >= 0) {
                        iu2.lock();
                        try {
                            ix2.setValueAt(indexOfKey, p4Var);
                        } catch (Throwable th) {
                            iu2.unlock();
                            throw th;
                        }
                    } else {
                        iu2.lock();
                        try {
                            ix2.put(i5, p4Var);
                        } catch (Throwable th2) {
                            iu2.unlock();
                            throw th2;
                        }
                    }
                    iu2.unlock();
                    iu2.unlock();
                } finally {
                    iu2.unlock();
                }
            } finally {
                iu2.unlock();
            }
        } else {
            A0F(this, "markerNotStarted", i, null, null);
            this.A07.nextInt(Integer.MAX_VALUE);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        if (r3.isEmpty() != false) goto L_0x000a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A0K(java.lang.String r3) {
        /*
            r2 = 1
            if (r3 == 0) goto L_0x000a
            boolean r1 = r3.isEmpty()
            r0 = 0
            if (r1 == 0) goto L_0x000b
        L_0x000a:
            r0 = 1
        L_0x000b:
            r0 = r0 ^ r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.YE.A0K(java.lang.String):boolean");
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final int sampleRateForMarker(int i) {
        if (A0J(this) || A0I() || CR.A00) {
            return A00(i);
        }
        return 1;
    }

    private int A00(int i) {
        if ((!CR.A00 || PerfTestConfigBase.A01) && !A0I()) {
            IU iu = this.A0C;
            iu.A00();
            try {
                int i2 = this.A0E.get(i, Integer.MIN_VALUE);
                if (i2 != Integer.MIN_VALUE) {
                    return i2;
                }
            } finally {
                iu.unlock();
            }
        }
        return 1;
    }

    public static final long A01(YE ye, long j, TimeUnit timeUnit) {
        if (j == -1) {
            return ye.A01.nowNanos();
        }
        return timeUnit.toNanos(j);
    }

    public static String A02(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString().replace("\\", "\\\\").replace("\"", "\\\"").replace("\b", "\\b").replace("\f", "\\f").replace("\n", LogCatCollector.COMPRESS_NEWLINE).replace("\r", "\\r").replace("\t", "\\t");
    }

    public static void A0E(YE ye, int i, String str) {
        String str2;
        int length = str.length();
        int i2 = ((length + 1000) - 1) / 1000;
        int i3 = 0;
        while (i3 < i2) {
            if (i3 > 0) {
                str2 = "...";
            } else {
                str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
            }
            int i4 = i3 * 1000;
            i3++;
            String A042 = AnonymousClass08.A04(str2, str.substring(i4, Math.min(i3 * 1000, length)));
            if (i != 2) {
                if (i == 3) {
                    C0139Dd.A0B("QuickPerformanceLoggerImpl", A042);
                } else if (i == 4) {
                    C0139Dd.A0D("QuickPerformanceLoggerImpl", A042);
                } else if (i == 5) {
                    C0139Dd.A0A("QuickPerformanceLoggerImpl", A042);
                }
            }
            C0139Dd.A09("QuickPerformanceLoggerImpl", A042);
        }
    }

    public static void A0G(String str, StringBuilder sb, List list) {
        sb.append('\"');
        sb.append(str);
        sb.append("\":[");
        boolean z = true;
        for (Object obj : list) {
            if (!z) {
                sb.append(',');
            }
            sb.append('\"');
            sb.append(A02(obj));
            sb.append('\"');
            z = false;
        }
        sb.append("]");
    }

    public static void A0H(String str, StringBuilder sb, Map map) {
        sb.append('\"');
        sb.append(str);
        sb.append("\":{");
        boolean z = true;
        for (Map.Entry entry : map.entrySet()) {
            if (!z) {
                sb.append(",");
            }
            if (entry.getValue() instanceof Map) {
                A0H(entry.getKey().toString(), sb, (Map) entry.getValue());
            } else {
                sb.append('\"');
                sb.append(entry.getKey());
                sb.append("\":\"");
                sb.append(A02(entry.getValue()));
                sb.append('\"');
            }
            z = false;
        }
        sb.append("}");
    }

    private boolean A0I() {
        if (this.A0K == TriState.UNSET) {
            this.A0K = TriState.NO;
        }
        return this.A0K.asBoolean(false);
    }

    public static boolean A0J(YE ye) {
        if (ye.A0I == TriState.UNSET) {
            ye.A0I = TriState.NO;
        }
        if (!ye.A0I.asBoolean(false)) {
            if (ye.A0J == TriState.UNSET) {
                ye.A0J = TriState.NO;
            }
            if (ye.A0J.asBoolean(false)) {
                return true;
            }
            return false;
        }
        return true;
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final long currentMonotonicTimestamp() {
        return TimeUnit.NANOSECONDS.toMillis(this.A01.nowNanos());
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final IY withMarker(int i, int i2) {
        IZ iz = this.A04;
        IU iu = iz.A04;
        iu.lock();
        try {
            RunnableC0929p4 A012 = iz.A03.A01(i ^ (i2 * 179426549));
            if (!IL.A00(A012)) {
                iu.unlock();
            } else if (A012 != null) {
                return new C0917os(this, A012);
            }
            return C0918ot.A00;
        } finally {
            iu.unlock();
        }
    }

    private final void A04(int i, int i2, String str, double d) {
        if (A0K(str)) {
            IZ iz = this.A04;
            int i3 = i ^ (i2 * 179426549);
            IL il = iz.A03;
            if (il.A03(i3)) {
                IU iu = iz.A04;
                iu.A00();
                try {
                    RunnableC0929p4 A012 = il.A01(i3);
                    if (IL.A00(A012)) {
                        IN in = A012.A0L;
                        in.A06 = null;
                        in.A05 = null;
                        in.A03.add(str);
                        int i4 = in.A00;
                        double[] dArr = in.A08;
                        int length = dArr.length;
                        if (i4 == length) {
                            dArr = Arrays.copyOf(dArr, (int) (((double) length) * 1.4d));
                            in.A08 = dArr;
                        }
                        int i5 = in.A00;
                        in.A00 = i5 + 1;
                        dArr[i5] = d;
                        IN.A00(in, (byte) 6);
                    }
                } finally {
                    iu.unlock();
                }
            }
        }
    }

    private final void A05(int i, int i2, String str, int i3) {
        if (A0K(str)) {
            IZ iz = this.A04;
            int i4 = i ^ (i2 * 179426549);
            IL il = iz.A03;
            if (il.A03(i4)) {
                IU iu = iz.A04;
                iu.A00();
                try {
                    RunnableC0929p4 A012 = il.A01(i4);
                    if (IL.A00(A012)) {
                        IN in = A012.A0L;
                        in.A06 = null;
                        in.A05 = null;
                        in.A03.add(str);
                        IN.A01(in, (long) i3);
                        IN.A00(in, (byte) 2);
                    }
                } finally {
                    iu.unlock();
                }
            }
        }
    }

    private final void A06(int i, int i2, String str, long j) {
        if (A0K(str)) {
            IZ iz = this.A04;
            int i3 = i ^ (i2 * 179426549);
            IL il = iz.A03;
            if (il.A03(i3)) {
                IU iu = iz.A04;
                iu.A00();
                try {
                    RunnableC0929p4 A012 = il.A01(i3);
                    if (IL.A00(A012)) {
                        IN in = A012.A0L;
                        in.A06 = null;
                        in.A05 = null;
                        in.A03.add(str);
                        IN.A01(in, j);
                        IN.A00(in, (byte) 3);
                    }
                } finally {
                    iu.unlock();
                }
            }
        }
    }

    private final void A07(int i, int i2, String str, String str2) {
        if (A0K(str)) {
            IZ iz = this.A04;
            int i3 = i ^ (i2 * 179426549);
            IL il = iz.A03;
            if (il.A03(i3)) {
                IU iu = iz.A04;
                iu.A00();
                try {
                    RunnableC0929p4 A012 = il.A01(i3);
                    if (IL.A00(A012)) {
                        IN in = A012.A0L;
                        in.A06 = null;
                        in.A05 = null;
                        in.A03.add(str);
                        in.A04.add(str2);
                        IN.A00(in, (byte) 1);
                    }
                } finally {
                    iu.unlock();
                }
            }
        }
    }

    private final void A08(int i, int i2, String str, boolean z) {
        long j;
        if (A0K(str)) {
            IZ iz = this.A04;
            int i3 = i ^ (i2 * 179426549);
            IL il = iz.A03;
            if (il.A03(i3)) {
                IU iu = iz.A04;
                iu.A00();
                try {
                    RunnableC0929p4 A012 = il.A01(i3);
                    if (IL.A00(A012)) {
                        IN in = A012.A0L;
                        in.A06 = null;
                        in.A05 = null;
                        in.A03.add(str);
                        if (z) {
                            j = 1;
                        } else {
                            j = 0;
                        }
                        IN.A01(in, j);
                        IN.A00(in, (byte) 8);
                    }
                } finally {
                    iu.unlock();
                }
            }
        }
    }

    private final void A09(int i, int i2, String str, double[] dArr) {
        if (A0K(str)) {
            IZ iz = this.A04;
            int i3 = i ^ (i2 * 179426549);
            IL il = iz.A03;
            if (il.A03(i3)) {
                IU iu = iz.A04;
                iu.A00();
                try {
                    RunnableC0929p4 A012 = il.A01(i3);
                    if (IL.A00(A012)) {
                        IN in = A012.A0L;
                        in.A06 = null;
                        in.A05 = null;
                        in.A03.add(str);
                        in.A04.add(Arrays.copyOf(dArr, dArr.length));
                        IN.A00(in, (byte) 7);
                    }
                } finally {
                    iu.unlock();
                }
            }
        }
    }

    private final void A0A(int i, int i2, String str, int[] iArr) {
        if (A0K(str)) {
            IZ iz = this.A04;
            int i3 = i ^ (i2 * 179426549);
            IL il = iz.A03;
            if (il.A03(i3)) {
                IU iu = iz.A04;
                iu.A00();
                try {
                    RunnableC0929p4 A012 = il.A01(i3);
                    if (IL.A00(A012)) {
                        IN in = A012.A0L;
                        in.A06 = null;
                        in.A05 = null;
                        in.A03.add(str);
                        in.A04.add(Arrays.copyOf(iArr, iArr.length));
                        IN.A00(in, (byte) 5);
                    }
                } finally {
                    iu.unlock();
                }
            }
        }
    }

    private final void A0B(int i, int i2, String str, long[] jArr) {
        if (A0K(str)) {
            IZ iz = this.A04;
            int i3 = i ^ (i2 * 179426549);
            IL il = iz.A03;
            if (il.A03(i3)) {
                IU iu = iz.A04;
                iu.A00();
                try {
                    RunnableC0929p4 A012 = il.A01(i3);
                    if (IL.A00(A012)) {
                        IN in = A012.A0L;
                        in.A06 = null;
                        in.A05 = null;
                        in.A03.add(str);
                        in.A04.add(Arrays.copyOf(jArr, jArr.length));
                        IN.A00(in, (byte) 10);
                    }
                } finally {
                    iu.unlock();
                }
            }
        }
    }

    private final void A0C(int i, int i2, String str, String[] strArr) {
        if (A0K(str)) {
            IZ iz = this.A04;
            int i3 = i ^ (i2 * 179426549);
            IL il = iz.A03;
            if (il.A03(i3)) {
                IU iu = iz.A04;
                iu.A00();
                try {
                    RunnableC0929p4 A012 = il.A01(i3);
                    if (IL.A00(A012)) {
                        IN in = A012.A0L;
                        in.A06 = null;
                        in.A05 = null;
                        in.A03.add(str);
                        in.A04.add(Arrays.copyOf(strArr, strArr.length));
                        IN.A00(in, (byte) 4);
                    }
                } finally {
                    iu.unlock();
                }
            }
        }
    }

    private final void A0D(int i, int i2, String str, boolean[] zArr) {
        if (A0K(str)) {
            IZ iz = this.A04;
            int i3 = i ^ (i2 * 179426549);
            IL il = iz.A03;
            if (il.A03(i3)) {
                IU iu = iz.A04;
                iu.A00();
                try {
                    RunnableC0929p4 A012 = il.A01(i3);
                    if (IL.A00(A012)) {
                        IN in = A012.A0L;
                        in.A06 = null;
                        in.A05 = null;
                        in.A03.add(str);
                        in.A04.add(Arrays.copyOf(zArr, zArr.length));
                        IN.A00(in, (byte) 9);
                    }
                } finally {
                    iu.unlock();
                }
            }
        }
    }

    public static void A0F(YE ye, String str, int i, String str2, String str3) {
        if (A0J(ye)) {
            Object[] objArr = new Object[6];
            objArr[0] = str;
            objArr[1] = ye.A05.A01(i);
            objArr[2] = Integer.valueOf(i);
            if (str2 == null) {
                str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
            }
            objArr[3] = str2;
            objArr[4] = str3 == null ? OacrConstants.AUTO_SPEECH_DOMAIN : ":";
            if (str3 == null) {
                str3 = OacrConstants.AUTO_SPEECH_DOMAIN;
            }
            objArr[5] = str3;
            A0E(ye, 2, String.format(Locale.US, "%s: %s (%d) %s%s%s", objArr));
        }
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStartWithCancelPolicy(int i, boolean z, int i2, long j, TimeUnit timeUnit) {
        Process.myTid();
        A03(i, i2, j, timeUnit, 0);
    }

    public YE(AbstractC0463a6 a6Var, C0914op opVar, Cb cb, C0832jW jWVar, C0786hL hLVar, C0924oz ozVar, C0919ou ouVar, IP[] ipArr, C0916or orVar, C0925p0 p0Var, C0940pH pHVar) {
        TriState triState = TriState.UNSET;
        this.A0I = triState;
        this.A0J = triState;
        this.A0K = triState;
        this.A08 = a6Var;
        this.A02 = opVar;
        this.A01 = cb;
        this.A0A = jWVar;
        this.A09 = hLVar;
        this.A0D = ozVar;
        this.A0B = ouVar;
        this.mDataProviders = ipArr;
        this.mEventDecorators = null;
        this.A03 = orVar;
        this.A05 = p0Var;
        this.A0H = pHVar;
        this.A0G = C0939pG.A00;
        this.A0F = C0938pF.A00;
        this.A0E = new C0936pC();
        this.A0C = new IU(cb);
        if (this.A03 == null) {
            new C0916or();
        }
        this.A0L = new Ig(this.A01, pHVar);
        this.A04 = new IZ(ozVar, ipArr, orVar, this.A01, this.A0F, pHVar);
        C0139Dd.A09("QuickPerformanceLoggerImpl", "Created!");
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final boolean isMarkerOn(int i) {
        return this.A04.A03.A03(i ^ 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final boolean isMarkerOn(int i, int i2) {
        return this.A04.A03.A03(i ^ (i2 * 179426549));
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, int i2, String str, double d) {
        Process.myTid();
        A04(i, i2, str, d);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, int i2, String str, int i3) {
        Process.myTid();
        A05(i, i2, str, i3);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, int i2, String str, long j) {
        Process.myTid();
        A06(i, i2, str, j);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, int i2, String str, String str2) {
        Process.myTid();
        A07(i, i2, str, str2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, int i2, String str, boolean z) {
        Process.myTid();
        A08(i, i2, str, z);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, int i2, String str, double[] dArr) {
        Process.myTid();
        A09(i, i2, str, dArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, int i2, String str, int[] iArr) {
        Process.myTid();
        A0A(i, i2, str, iArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, int i2, String str, long[] jArr) {
        Process.myTid();
        A0B(i, i2, str, jArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, int i2, String str, String[] strArr) {
        Process.myTid();
        A0C(i, i2, str, strArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, int i2, String str, boolean[] zArr) {
        Process.myTid();
        A0D(i, i2, str, zArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, String str, double d) {
        Process.myTid();
        A04(i, 0, str, d);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, String str, int i2) {
        Process.myTid();
        A05(i, 0, str, i2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, String str, long j) {
        Process.myTid();
        A06(i, 0, str, j);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, String str, String str2) {
        Process.myTid();
        A07(i, 0, str, str2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, String str, boolean z) {
        Process.myTid();
        A08(i, 0, str, z);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, String str, double[] dArr) {
        Process.myTid();
        A09(i, 0, str, dArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, String str, int[] iArr) {
        Process.myTid();
        A0A(i, 0, str, iArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, String str, long[] jArr) {
        Process.myTid();
        A0B(i, 0, str, jArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, String str, String[] strArr) {
        Process.myTid();
        A0C(i, 0, str, strArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, String str, boolean[] zArr) {
        Process.myTid();
        A0D(i, 0, str, zArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerCancel(int i) {
        markerCancel(i, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerCancel(int i, int i2) {
        markerCancel(i, i2, 4);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerCancel(int i, int i2, short s) {
        Process.myTid();
        long nowNanos = this.A01.nowNanos();
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        IZ iz = this.A04;
        C0931p6 p6Var = this.A06;
        int i3 = i ^ (i2 * 179426549);
        IU iu = iz.A04;
        iu.lock();
        try {
            RunnableC0929p4 A022 = iz.A03.A02(i3);
            if (A022 != null) {
                Ix ix = A022.A0E;
                if (ix != null) {
                    IZ.A02(iz, ix, A022.A08);
                }
                timeUnit.toNanos(nowNanos);
                A0F(p6Var.A00, "markerDropped", i, null, null);
            }
        } finally {
            iu.unlock();
        }
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerCancel(int i, short s) {
        markerCancel(i, 0, s);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerEnd(int i, int i2, short s) {
        markerEnd(i, i2, s, -1, TimeUnit.NANOSECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerEnd(int i, int i2, short s, long j) {
        markerEnd(i, i2, s, j, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: finally extract failed */
    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerEnd(int i, int i2, short s, long j, TimeUnit timeUnit) {
        Ix ix;
        long j2;
        short s2;
        ArrayList arrayList;
        Process.myTid();
        boolean z = false;
        if (j == -1) {
            z = true;
        }
        long A012 = A01(this, j, timeUnit);
        IZ iz = this.A04;
        TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
        int i3 = i ^ (i2 * 179426549);
        IU iu = iz.A04;
        iu.A00();
        try {
            RunnableC0929p4 A022 = iz.A03.A02(i3);
            if (A022 != null) {
                ix = A022.A0E;
                j2 = A022.A08;
            } else {
                j2 = 0;
                ix = null;
            }
            iu.unlock();
            Ix A013 = IZ.A01(iz, j2);
            IZ.A02(iz, ix, j2);
            iu.A00();
            if (A022 != null) {
                try {
                    long nanos = timeUnit2.toNanos(A012) - A022.A09;
                    A022.A0F = A013;
                    boolean z2 = true;
                    if (!A022.A0H) {
                        z2 = false;
                    }
                    A022.A07 = timeUnit2.toNanos(A012) - A022.A09;
                    if (A022.A0H && (s2 = (short) (A022.A02 >> 16)) >= 0 && s2 <= 16348 && (arrayList = iz.A05[s2]) != null && !arrayList.isEmpty()) {
                        A022.A0M.addAll(arrayList);
                    }
                    A022.A0G = s;
                    timeUnit2.toNanos(A012);
                    A022.A07 = nanos;
                    if (!A022.A0J) {
                        z = false;
                    }
                    A022.A0J = z;
                    if (z2) {
                        iu.unlock();
                        A022.A0B = TriState.NO;
                        A0F(this, "markerEnd", i, null, null);
                        new RunnableC0184Ir(this, A022).run();
                        return;
                    }
                } catch (Throwable th) {
                    iu.unlock();
                    throw th;
                }
            }
            iu.unlock();
        } catch (Throwable th2) {
            iu.unlock();
            throw th2;
        }
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerEnd(int i, short s) {
        markerEnd(i, 0, s, -1, TimeUnit.NANOSECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerEnd(int i, short s, long j) {
        markerEnd(i, s, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerEnd(int i, short s, long j, TimeUnit timeUnit) {
        markerEnd(i, 0, s, j, timeUnit);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, int i3, String str, Ie ie, long j, int i4) {
        markerPoint(i, i2, i3, str, ie, j, TimeUnit.MILLISECONDS, i4);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, int i3, String str, Ie ie, long j, TimeUnit timeUnit, int i4) {
        String obj;
        Ie ie2 = ie;
        if (ie == null) {
            ie2 = null;
        } else {
            ie2.A03 = true;
        }
        Process.myTid();
        if (A0K(str)) {
            if (A0J(this)) {
                if (ie2 == null) {
                    obj = null;
                } else {
                    obj = ie2.toString();
                }
                A0F(this, "markerPoint", i, str, obj);
            }
            this.A04.A03(i, i2, i3, A01(this, j, timeUnit), TimeUnit.NANOSECONDS, str, ie2, i4);
        }
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, String str) {
        markerPoint(i, i2, str, (String) null, -1, TimeUnit.NANOSECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, String str, long j) {
        markerPoint(i, i2, str, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, String str, long j, TimeUnit timeUnit) {
        markerPoint(i, i2, str, (String) null, j, timeUnit);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, String str, String str2) {
        markerPoint(i, i2, str, str2, -1, TimeUnit.NANOSECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, String str, String str2, long j) {
        markerPoint(i, i2, str, str2, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, String str, String str2, long j, int i3) {
        markerPoint(i, i2, str, str2, j, TimeUnit.MILLISECONDS, i3);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, String str, String str2, long j, TimeUnit timeUnit) {
        markerPoint(i, i2, str, str2, j, timeUnit, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, String str, String str2, long j, TimeUnit timeUnit, int i3) {
        Process.myTid();
        Ie ie = null;
        if (A0K(str)) {
            A0F(this, "markerPoint", i, str, str2);
            long A012 = A01(this, j, timeUnit);
            IZ iz = this.A04;
            TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
            if (str2 != null) {
                if (iz.A03.A03((179426549 * i2) ^ i)) {
                    ie = new Ie();
                    ie.A00("__key", str2, 1);
                    ie.A03 = true;
                } else {
                    return;
                }
            }
            iz.A03(i, i2, 7, A012, timeUnit2, str, ie, i3);
        }
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, String str) {
        markerPoint(i, 0, str, (String) null, -1, TimeUnit.NANOSECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, String str, long j) {
        markerPoint(i, str, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, String str, long j, TimeUnit timeUnit) {
        markerPoint(i, 0, str, (String) null, j, timeUnit);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, String str, String str2) {
        markerPoint(i, 0, str, str2, -1, TimeUnit.NANOSECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, String str, String str2, long j) {
        markerPoint(i, str, str2, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, String str, String str2, long j, TimeUnit timeUnit) {
        markerPoint(i, 0, str, str2, j, timeUnit);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i) {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        Process.myTid();
        A03(i, 0, -1, timeUnit, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2) {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        Process.myTid();
        A03(i, i2, -1, timeUnit, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, long j) {
        markerStart(i, i2, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, long j, int i3) {
        markerStart(i, i2, j, TimeUnit.MILLISECONDS, i3);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, long j, TimeUnit timeUnit) {
        Process.myTid();
        A03(i, i2, j, timeUnit, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, long j, TimeUnit timeUnit, int i3) {
        Process.myTid();
        A03(i, i2, j, timeUnit, i3);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, String str, String str2) {
        markerStart(i, i2);
        markerAnnotate(i, i2, str, str2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, String str, String str2, long j) {
        markerStart(i, i2, str, str2, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, String str, String str2, long j, TimeUnit timeUnit) {
        markerStart(i, i2, j, timeUnit);
        markerAnnotate(i, i2, str, str2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, boolean z) {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        Process.myTid();
        A03(i, i2, -1, timeUnit, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, String str, String str2) {
        markerStart(i);
        markerAnnotate(i, str, str2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, String str, String str2, long j) {
        markerStart(i, str, str2, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, String str, String str2, long j, TimeUnit timeUnit) {
        markerStart(i, 0, j, timeUnit);
        markerAnnotate(i, str, str2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, boolean z) {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        Process.myTid();
        A03(i, 0, -1, timeUnit, 0);
    }
}
