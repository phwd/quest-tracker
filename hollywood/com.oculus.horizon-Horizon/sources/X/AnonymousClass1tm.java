package X;

import com.facebook.acra.LogCatCollector;
import com.facebook.common.util.TriState;
import com.facebook.privacy.datacollection.DisallowSensitive;
import com.facebook.quicklog.EventLevel;
import com.facebook.quicklog.QuickPerformanceLogger;
import com.oculus.debug.DebugMode;
import com.oculus.perflogs.impl.fbquicklog.PerfLogsHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.inject.Provider;

/* renamed from: X.1tm  reason: invalid class name */
public final class AnonymousClass1tm implements QuickPerformanceLogger {
    @Nullable
    public RunnableC10551tn A00;
    public final AnonymousClass0LA A01;
    public final AnonymousClass0LF A02;
    public final AnonymousClass1uD A03;
    public final C10561tp A04;
    public final AnonymousClass1uY A05 = new AnonymousClass1uY(this);
    public final AnonymousClass0TF A06;
    public final AnonymousClass0VG A07;
    public final PerfLogsHelper.AnonymousClass2 A08;
    public final Random A09 = new Random();
    public final Provider<AnonymousClass1n9> A0A;
    public final AnonymousClass1iH A0B;
    public final AnonymousClass1u6 A0C;
    public final AnonymousClass1hU A0D;
    @GuardedBy("mAlwaysOnSampleRatesLock")
    public final AnonymousClass0VC A0E;
    public final AnonymousClass0VF A0F;
    public final AnonymousClass0VH A0G;
    public volatile TriState A0H;
    public volatile TriState A0I;
    public volatile TriState A0J;
    public volatile AnonymousClass1ty A0K;
    @Nullable
    public final AbstractC10621u9[] mDataProviders;
    @Nullable
    public final AbstractC10701uq[] mEventDecorators;

    private int A00(int i) {
        if (!A0I()) {
            AnonymousClass1u6 r2 = this.A0C;
            r2.A00();
            try {
                int i2 = this.A0E.get(i, Integer.MIN_VALUE);
                if (i2 != Integer.MIN_VALUE) {
                    return i2;
                }
            } finally {
                r2.unlock();
            }
        }
        return 1;
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1tm.A0K(java.lang.String):boolean");
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStartWithCancelPolicy(int i, boolean z, int i2, long j, TimeUnit timeUnit) {
        this.A07.A5Y();
        A03(i, i2, j, timeUnit, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final int sampleRateForMarker(int i) {
        if (A0J(this) || A0I()) {
            return A00(i);
        }
        return 1;
    }

    public static final long A01(AnonymousClass1tm r3, long j, TimeUnit timeUnit) {
        if (j == -1) {
            return r3.A02.nowNanos();
        }
        return timeUnit.toNanos(j);
    }

    @Nullable
    public static String A02(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString().replace("\\", "\\\\").replace("\"", "\\\"").replace("\b", "\\b").replace("\f", "\\f").replace("\n", LogCatCollector.COMPRESS_NEWLINE).replace("\r", "\\r").replace("\t", "\\t");
    }

    public static void A0E(AnonymousClass1tm r7, int i, String str) {
        String str2;
        int length = str.length();
        int i2 = ((length + 1000) - 1) / 1000;
        int i3 = 0;
        while (i3 < i2) {
            if (i3 > 0) {
                str2 = "...";
            } else {
                str2 = "";
            }
            int i4 = i3 * 1000;
            i3++;
            String A052 = AnonymousClass006.A05(str2, str.substring(i4, Math.min(i3 * 1000, length)));
            if (!(i == 2 || i == 3)) {
                if (i == 4) {
                    r7.A0F.A9x("QuickPerformanceLoggerImpl", A052);
                } else if (i == 5) {
                    r7.A0F.A2J("QuickPerformanceLoggerImpl", A052);
                }
            }
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/String;Ljava/lang/StringBuilder;Ljava/util/List<*>;)Ljava/lang/StringBuilder; */
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

    public static void A0H(String str, StringBuilder sb, Map<?, ?> map) {
        sb.append('\"');
        sb.append(str);
        sb.append("\":{");
        boolean z = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
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
        TriState triState;
        if (this.A0J == TriState.UNSET) {
            if (((DebugMode) AnonymousClass0J2.A03(1, 272, PerfLogsHelper.this._UL_mInjectionContext)).A02()) {
                triState = TriState.YES;
            } else {
                triState = TriState.NO;
            }
            this.A0J = triState;
        }
        return this.A0J.asBoolean(false);
    }

    public static boolean A0J(AnonymousClass1tm r3) {
        TriState triState;
        if (r3.A0H == TriState.UNSET) {
            if (((DebugMode) AnonymousClass0J2.A03(1, 272, PerfLogsHelper.this._UL_mInjectionContext)).A02()) {
                triState = TriState.YES;
            } else {
                triState = TriState.NO;
            }
            r3.A0H = triState;
        }
        if (!r3.A0H.asBoolean(false)) {
            if (r3.A0I == TriState.UNSET) {
                r3.A0I = TriState.NO;
            }
            if (r3.A0I.asBoolean(false)) {
                return true;
            }
            return false;
        }
        return true;
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final long currentMonotonicTimestamp() {
        return TimeUnit.NANOSECONDS.toMillis(this.A02.nowNanos());
    }

    private final void A04(int i, int i2, String str, double d) {
        if (A0K(str)) {
            C10561tp r2 = this.A04;
            int i3 = i ^ (i2 * 179426549);
            AnonymousClass1tt r1 = r2.A02;
            if (r1.A03(i3)) {
                AnonymousClass1u6 r4 = r2.A03;
                r4.A00();
                try {
                    RunnableC10551tn A012 = r1.A01(i3);
                    if (AnonymousClass1tt.A00(A012)) {
                        AnonymousClass1tr r6 = A012.A0J;
                        r6.A06 = null;
                        r6.A05 = null;
                        r6.A03.add(str);
                        int i4 = r6.A00;
                        double[] dArr = r6.A08;
                        int length = dArr.length;
                        if (i4 == length) {
                            dArr = Arrays.copyOf(dArr, (int) (((double) length) * 1.4d));
                            r6.A08 = dArr;
                        }
                        int i5 = r6.A00;
                        r6.A00 = i5 + 1;
                        dArr[i5] = d;
                        AnonymousClass1tr.A00(r6, (byte) 6);
                    }
                } finally {
                    r4.unlock();
                }
            }
        }
    }

    private final void A05(int i, int i2, String str, int i3) {
        if (A0K(str)) {
            C10561tp r2 = this.A04;
            int i4 = i ^ (i2 * 179426549);
            AnonymousClass1tt r1 = r2.A02;
            if (r1.A03(i4)) {
                AnonymousClass1u6 r3 = r2.A03;
                r3.A00();
                try {
                    RunnableC10551tn A012 = r1.A01(i4);
                    if (AnonymousClass1tt.A00(A012)) {
                        AnonymousClass1tr r22 = A012.A0J;
                        r22.A06 = null;
                        r22.A05 = null;
                        r22.A03.add(str);
                        AnonymousClass1tr.A01(r22, (long) i3);
                        AnonymousClass1tr.A00(r22, (byte) 2);
                    }
                } finally {
                    r3.unlock();
                }
            }
        }
    }

    private final void A06(int i, int i2, String str, long j) {
        if (A0K(str)) {
            C10561tp r2 = this.A04;
            int i3 = i ^ (i2 * 179426549);
            AnonymousClass1tt r1 = r2.A02;
            if (r1.A03(i3)) {
                AnonymousClass1u6 r22 = r2.A03;
                r22.A00();
                try {
                    RunnableC10551tn A012 = r1.A01(i3);
                    if (AnonymousClass1tt.A00(A012)) {
                        AnonymousClass1tr r12 = A012.A0J;
                        r12.A06 = null;
                        r12.A05 = null;
                        r12.A03.add(str);
                        AnonymousClass1tr.A01(r12, j);
                        AnonymousClass1tr.A00(r12, (byte) 3);
                    }
                } finally {
                    r22.unlock();
                }
            }
        }
    }

    private final void A07(int i, int i2, String str, String str2) {
        if (A0K(str)) {
            C10561tp r2 = this.A04;
            int i3 = i ^ (i2 * 179426549);
            AnonymousClass1tt r1 = r2.A02;
            if (r1.A03(i3)) {
                AnonymousClass1u6 r22 = r2.A03;
                r22.A00();
                try {
                    RunnableC10551tn A012 = r1.A01(i3);
                    if (AnonymousClass1tt.A00(A012)) {
                        AnonymousClass1tr r12 = A012.A0J;
                        r12.A06 = null;
                        r12.A05 = null;
                        r12.A03.add(str);
                        r12.A04.add(str2);
                        AnonymousClass1tr.A00(r12, (byte) 1);
                    }
                } finally {
                    r22.unlock();
                }
            }
        }
    }

    private final void A08(int i, int i2, String str, boolean z) {
        long j;
        if (A0K(str)) {
            C10561tp r2 = this.A04;
            int i3 = i ^ (i2 * 179426549);
            AnonymousClass1tt r1 = r2.A02;
            if (r1.A03(i3)) {
                AnonymousClass1u6 r3 = r2.A03;
                r3.A00();
                try {
                    RunnableC10551tn A012 = r1.A01(i3);
                    if (AnonymousClass1tt.A00(A012)) {
                        AnonymousClass1tr r22 = A012.A0J;
                        r22.A06 = null;
                        r22.A05 = null;
                        r22.A03.add(str);
                        if (z) {
                            j = 1;
                        } else {
                            j = 0;
                        }
                        AnonymousClass1tr.A01(r22, j);
                        AnonymousClass1tr.A00(r22, (byte) 8);
                    }
                } finally {
                    r3.unlock();
                }
            }
        }
    }

    private final void A09(int i, int i2, String str, double[] dArr) {
        if (A0K(str)) {
            C10561tp r2 = this.A04;
            int i3 = i ^ (i2 * 179426549);
            AnonymousClass1tt r1 = r2.A02;
            if (r1.A03(i3)) {
                AnonymousClass1u6 r3 = r2.A03;
                r3.A00();
                try {
                    RunnableC10551tn A012 = r1.A01(i3);
                    if (AnonymousClass1tt.A00(A012)) {
                        AnonymousClass1tr r22 = A012.A0J;
                        r22.A06 = null;
                        r22.A05 = null;
                        r22.A03.add(str);
                        r22.A04.add(Arrays.copyOf(dArr, dArr.length));
                        AnonymousClass1tr.A00(r22, (byte) 7);
                    }
                } finally {
                    r3.unlock();
                }
            }
        }
    }

    private final void A0A(int i, int i2, String str, int[] iArr) {
        if (A0K(str)) {
            C10561tp r2 = this.A04;
            int i3 = i ^ (i2 * 179426549);
            AnonymousClass1tt r1 = r2.A02;
            if (r1.A03(i3)) {
                AnonymousClass1u6 r3 = r2.A03;
                r3.A00();
                try {
                    RunnableC10551tn A012 = r1.A01(i3);
                    if (AnonymousClass1tt.A00(A012)) {
                        AnonymousClass1tr r22 = A012.A0J;
                        r22.A06 = null;
                        r22.A05 = null;
                        r22.A03.add(str);
                        r22.A04.add(Arrays.copyOf(iArr, iArr.length));
                        AnonymousClass1tr.A00(r22, (byte) 5);
                    }
                } finally {
                    r3.unlock();
                }
            }
        }
    }

    private final void A0B(int i, int i2, String str, long[] jArr) {
        if (A0K(str)) {
            C10561tp r2 = this.A04;
            int i3 = i ^ (i2 * 179426549);
            AnonymousClass1tt r1 = r2.A02;
            if (r1.A03(i3)) {
                AnonymousClass1u6 r3 = r2.A03;
                r3.A00();
                try {
                    RunnableC10551tn A012 = r1.A01(i3);
                    if (AnonymousClass1tt.A00(A012)) {
                        AnonymousClass1tr r22 = A012.A0J;
                        r22.A06 = null;
                        r22.A05 = null;
                        r22.A03.add(str);
                        r22.A04.add(Arrays.copyOf(jArr, jArr.length));
                        AnonymousClass1tr.A00(r22, (byte) 10);
                    }
                } finally {
                    r3.unlock();
                }
            }
        }
    }

    private final void A0C(int i, int i2, String str, String[] strArr) {
        if (A0K(str)) {
            C10561tp r2 = this.A04;
            int i3 = i ^ (i2 * 179426549);
            AnonymousClass1tt r1 = r2.A02;
            if (r1.A03(i3)) {
                AnonymousClass1u6 r3 = r2.A03;
                r3.A00();
                try {
                    RunnableC10551tn A012 = r1.A01(i3);
                    if (AnonymousClass1tt.A00(A012)) {
                        AnonymousClass1tr r22 = A012.A0J;
                        r22.A06 = null;
                        r22.A05 = null;
                        r22.A03.add(str);
                        r22.A04.add(Arrays.copyOf(strArr, strArr.length));
                        AnonymousClass1tr.A00(r22, (byte) 4);
                    }
                } finally {
                    r3.unlock();
                }
            }
        }
    }

    private final void A0D(int i, int i2, String str, boolean[] zArr) {
        if (A0K(str)) {
            C10561tp r2 = this.A04;
            int i3 = i ^ (i2 * 179426549);
            AnonymousClass1tt r1 = r2.A02;
            if (r1.A03(i3)) {
                AnonymousClass1u6 r3 = r2.A03;
                r3.A00();
                try {
                    RunnableC10551tn A012 = r1.A01(i3);
                    if (AnonymousClass1tt.A00(A012)) {
                        AnonymousClass1tr r22 = A012.A0J;
                        r22.A06 = null;
                        r22.A05 = null;
                        r22.A03.add(str);
                        r22.A04.add(Arrays.copyOf(zArr, zArr.length));
                        AnonymousClass1tr.A00(r22, (byte) 9);
                    }
                } finally {
                    r3.unlock();
                }
            }
        }
    }

    public static void A0F(AnonymousClass1tm r5, String str, @Nullable int i, @Nullable String str2, String str3) {
        String str4;
        if (A0J(r5)) {
            Object[] objArr = new Object[6];
            objArr[0] = str;
            objArr[1] = r5.A06.A3o(i);
            objArr[2] = Integer.valueOf(i);
            if (str2 == null) {
                str2 = "";
            }
            objArr[3] = str2;
            if (str3 == null) {
                str4 = "";
            } else {
                str4 = ":";
            }
            objArr[4] = str4;
            if (str3 == null) {
                str3 = "";
            }
            objArr[5] = str3;
            A0E(r5, 2, String.format(Locale.US, "%s: %s (%d) %s%s%s", objArr));
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljavax/inject/Provider<LX/1n9;>;Lcom/facebook/quicklog/QPLConfiguration;LX/0LF;LX/0LA;Lcom/facebook/quicklog/DebugAndTestConfig;Lcom/facebook/quicklog/AppStates;Lcom/facebook/quicklog/BackgroundExecution;[Lcom/facebook/quicklog/QuickEventListener;[Lcom/facebook/quicklog/QuickEventVisitor;[LX/1u9;[LX/1uq;LX/0TD;LX/0TF;Lcom/facebook/quicklog/HealthMonitor;Lcom/facebook/quicklog/metadata/QuickEventListenerCounter;Lcom/facebook/quicklog/ReliabilityMarkersObserver;LX/0VH;)V */
    public AnonymousClass1tm(Provider provider, AnonymousClass1uD r5, AnonymousClass0LF r6, AnonymousClass0LA r7, PerfLogsHelper.AnonymousClass2 r8, AnonymousClass1hU r9, AnonymousClass1iH r10, @Nullable AnonymousClass0TF r11, @Nullable AnonymousClass0VH r12) {
        TriState triState = TriState.UNSET;
        this.A0H = triState;
        this.A0I = triState;
        this.A0J = triState;
        this.A0A = provider;
        this.A03 = r5;
        this.A02 = r6;
        this.A01 = r7;
        this.A08 = r8;
        this.A0D = r9;
        this.A0B = r10;
        this.mDataProviders = null;
        this.mEventDecorators = null;
        this.A06 = r11;
        this.A0G = r12;
        this.A07 = r12.A4B();
        this.A0F = r12.A3j();
        this.A0E = r12.A5a();
        this.A0C = new AnonymousClass1u6(r6);
        this.A0K = new AnonymousClass1ty(new AnonymousClass1uB(), this.A02, r12);
        this.A04 = new C10561tp(r9, this.A02, this.A0F, r12);
    }

    /* JADX WARN: Incorrect return type in method signature: (IIJLjava/util/concurrent/TimeUnit;ILcom/facebook/base/lwperf/perfstats/PerfStats;LX/0VE<*>;ZIIZILX/1ty;)LX/1Yh; */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x032f, code lost:
        if (r0 == -1) goto L_0x00a3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x03dd  */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x03e4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A03(int r20, int r21, long r22, java.util.concurrent.TimeUnit r24, int r25) {
        /*
        // Method dump skipped, instructions count: 5220
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1tm.A03(int, int, long, java.util.concurrent.TimeUnit, int):void");
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerEnd(int i, short s) {
        markerEnd(i, 0, s, -1, TimeUnit.NANOSECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, boolean z) {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        this.A07.A5Y();
        A03(i, 0, -1, timeUnit, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, String str, String str2, long j, TimeUnit timeUnit) {
        markerStart(i, 0, j, timeUnit);
        markerAnnotate(i, str, str2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, String str, String str2, long j) {
        markerStart(i, str, str2, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, String str, String str2) {
        markerStart(i);
        markerAnnotate(i, str, str2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, boolean z) {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        this.A07.A5Y();
        A03(i, i2, -1, timeUnit, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, String str, String str2, long j, TimeUnit timeUnit) {
        markerStart(i, i2, j, timeUnit);
        markerAnnotate(i, i2, str, str2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, String str, String str2, long j) {
        markerStart(i, i2, str, str2, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, String str, String str2) {
        markerStart(i, i2);
        markerAnnotate(i, i2, str, str2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, long j, TimeUnit timeUnit, int i3) {
        this.A07.A5Y();
        A03(i, i2, j, timeUnit, i3);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, long j, TimeUnit timeUnit) {
        this.A07.A5Y();
        A03(i, i2, j, timeUnit, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, long j, int i3) {
        markerStart(i, i2, j, TimeUnit.MILLISECONDS, i3);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2, long j) {
        markerStart(i, i2, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i, int i2) {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        this.A07.A5Y();
        A03(i, i2, -1, timeUnit, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerStart(int i) {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        this.A07.A5Y();
        A03(i, 0, -1, timeUnit, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final boolean isMarkerOn(int i) {
        return this.A04.A02.A03(i ^ 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final boolean isMarkerOn(int i, int i2) {
        return this.A04.A02.A03(i ^ (i2 * 179426549));
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, String str, @Nullable String str2, long j, TimeUnit timeUnit) {
        markerPoint(i, 0, str, str2, j, timeUnit);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    @Deprecated
    public final void markerPoint(int i, String str, @Nullable String str2, long j) {
        markerPoint(i, str, str2, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerPoint(int i, String str, @Nullable String str2) {
        markerPoint(i, 0, str, str2, -1, TimeUnit.NANOSECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, String str, long j, TimeUnit timeUnit) {
        markerPoint(i, 0, str, (String) null, j, timeUnit);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @Deprecated
    public final void markerPoint(int i, String str, long j) {
        markerPoint(i, str, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, String str) {
        markerPoint(i, 0, str, (String) null, -1, TimeUnit.NANOSECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, int i2, String str, double d) {
        this.A07.A5Y();
        A04(i, i2, str, d);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, int i2, String str, int i3) {
        this.A07.A5Y();
        A05(i, i2, str, i3);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, int i2, String str, long j) {
        this.A07.A5Y();
        A06(i, i2, str, j);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, int i2, String str, String str2) {
        this.A07.A5Y();
        A07(i, i2, str, str2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, int i2, String str, boolean z) {
        this.A07.A5Y();
        A08(i, i2, str, z);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, int i2, String str, double[] dArr) {
        this.A07.A5Y();
        A09(i, i2, str, dArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, int i2, String str, int[] iArr) {
        this.A07.A5Y();
        A0A(i, i2, str, iArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, int i2, String str, long[] jArr) {
        this.A07.A5Y();
        A0B(i, i2, str, jArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, int i2, String str, String[] strArr) {
        this.A07.A5Y();
        A0C(i, i2, str, strArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, int i2, String str, boolean[] zArr) {
        this.A07.A5Y();
        A0D(i, i2, str, zArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, String str, double d) {
        this.A07.A5Y();
        A04(i, 0, str, d);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, String str, int i2) {
        this.A07.A5Y();
        A05(i, 0, str, i2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, String str, long j) {
        this.A07.A5Y();
        A06(i, 0, str, j);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, String str, String str2) {
        this.A07.A5Y();
        A07(i, 0, str, str2);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, String str, boolean z) {
        this.A07.A5Y();
        A08(i, 0, str, z);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, String str, double[] dArr) {
        this.A07.A5Y();
        A09(i, 0, str, dArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, String str, int[] iArr) {
        this.A07.A5Y();
        A0A(i, 0, str, iArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, String str, long[] jArr) {
        this.A07.A5Y();
        A0B(i, 0, str, jArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerAnnotate(int i, String str, String[] strArr) {
        this.A07.A5Y();
        A0C(i, 0, str, strArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerAnnotate(int i, String str, boolean[] zArr) {
        this.A07.A5Y();
        A0D(i, 0, str, zArr);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, String str, @Nullable String str2, long j, TimeUnit timeUnit, int i3) {
        this.A07.A5Y();
        AnonymousClass0T7 r11 = null;
        if (A0K(str)) {
            A0F(this, "markerPoint", i, str, str2);
            long A012 = A01(this, j, timeUnit);
            C10561tp r3 = this.A04;
            TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
            if (str2 != null) {
                if (r3.A02.A03((179426549 * i2) ^ i)) {
                    r11 = new AnonymousClass0T7();
                    r11.A01("__key", str2, 1);
                    r11.A03 = true;
                } else {
                    return;
                }
            }
            r3.A00(i, i2, 7, A012, timeUnit2, str, r11, i3);
        }
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, String str, @Nullable String str2, long j, TimeUnit timeUnit) {
        markerPoint(i, i2, str, str2, j, timeUnit, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, String str, @Nullable String str2, long j, int i3) {
        markerPoint(i, i2, str, str2, j, TimeUnit.MILLISECONDS, i3);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @Deprecated
    public final void markerPoint(int i, int i2, String str, @Nullable String str2, long j) {
        markerPoint(i, i2, str, str2, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @DisallowSensitive
    public final void markerPoint(int i, int i2, String str, @Nullable String str2) {
        markerPoint(i, i2, str, str2, -1, TimeUnit.NANOSECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, String str, long j, TimeUnit timeUnit) {
        markerPoint(i, i2, str, (String) null, j, timeUnit);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @Deprecated
    public final void markerPoint(int i, int i2, String str, long j) {
        markerPoint(i, i2, str, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, String str) {
        markerPoint(i, i2, str, (String) null, -1, TimeUnit.NANOSECONDS);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, @EventLevel int i3, String str, @Nullable AnonymousClass0T7 r17, long j, TimeUnit timeUnit, int i4) {
        String obj;
        AnonymousClass0T7 r10 = r17;
        if (r17 == null) {
            r10 = null;
        } else {
            r10.A03 = true;
        }
        this.A07.A5Y();
        if (A0K(str)) {
            if (A0J(this)) {
                if (r10 == null) {
                    obj = null;
                } else {
                    obj = r10.toString();
                }
                A0F(this, "markerPoint", i, str, obj);
            }
            this.A04.A00(i, i2, i3, A01(this, j, timeUnit), TimeUnit.NANOSECONDS, str, r10, i4);
        }
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerPoint(int i, int i2, @EventLevel int i3, String str, @Nullable AnonymousClass0T7 r15, long j, int i4) {
        markerPoint(i, i2, i3, str, r15, j, TimeUnit.MILLISECONDS, i4);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @Deprecated
    public final void markerCancel(int i) {
        markerCancel(i, 0);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @Deprecated
    public final void markerCancel(int i, int i2) {
        markerCancel(i, i2, 4);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @Deprecated
    public final void markerCancel(int i, int i2, short s) {
        this.A07.A5Y();
        long nowNanos = this.A02.nowNanos();
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        C10561tp r6 = this.A04;
        AnonymousClass1uY r5 = this.A05;
        int i3 = i ^ (i2 * 179426549);
        AnonymousClass1u6 r3 = r6.A03;
        r3.lock();
        try {
            if (r6.A02.A02(i3) != null) {
                timeUnit.toNanos(nowNanos);
                A0F(r5.A00, "markerDropped", i, null, null);
            }
        } finally {
            r3.unlock();
        }
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    @Deprecated
    public final void markerCancel(int i, short s) {
        markerCancel(i, 0, s);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerEnd(int i, short s, long j, TimeUnit timeUnit) {
        markerEnd(i, 0, s, j, timeUnit);
    }

    @Override // com.facebook.quicklog.QuickPerformanceLogger
    public final void markerEnd(int i, short s, long j) {
        markerEnd(i, s, j, TimeUnit.MILLISECONDS);
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
        short s2;
        ArrayList<String> arrayList;
        this.A07.A5Y();
        boolean z = false;
        if (j == -1) {
            z = true;
        }
        long A012 = A01(this, j, timeUnit);
        C10561tp r9 = this.A04;
        TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
        int i3 = i ^ (i2 * 179426549);
        AnonymousClass1u6 r15 = r9.A03;
        r15.A00();
        try {
            RunnableC10551tn A022 = r9.A02.A02(i3);
            r15.unlock();
            r15.A00();
            if (A022 != null) {
                long nanos = timeUnit2.toNanos(A012) - A022.A09;
                boolean z2 = true;
                if (!A022.A0F) {
                    z2 = false;
                }
                A022.A07 = timeUnit2.toNanos(A012) - A022.A09;
                if (A022.A0F && (s2 = (short) (A022.A02 >> 16)) >= 0 && s2 <= 16348 && (arrayList = r9.A04[s2]) != null && !arrayList.isEmpty()) {
                    A022.A0K.addAll(arrayList);
                }
                A022.A0E = s;
                timeUnit2.toNanos(A012);
                A022.A07 = nanos;
                if (!A022.A0H) {
                    z = false;
                }
                A022.A0H = z;
                if (z2) {
                    r15.unlock();
                    A022.A0B = TriState.valueOf((Boolean) null);
                    A0F(this, "markerEnd", i, null, null);
                    new RunnableC10591tz(this, A022).run();
                    return;
                }
            }
            r15.unlock();
        } catch (Throwable th) {
            r15.unlock();
            throw th;
        }
    }
}
