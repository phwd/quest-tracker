package X;

import android.os.Process;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.quicklog.PointEditor;
import java.util.concurrent.TimeUnit;

/* renamed from: X.os  reason: case insensitive filesystem */
public final class C0917os extends IY implements PointEditor {
    public int A00 = 7;
    public int A01;
    public long A02;
    public Ie A03;
    public String A04;
    public final RunnableC0929p4 A05;
    public final YE A06;
    public final C0939pG A07;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        if (r4.isEmpty() != false) goto L_0x000a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean A01(java.lang.String r4) {
        /*
            r3 = this;
            r2 = 1
            if (r4 == 0) goto L_0x000a
            boolean r1 = r4.isEmpty()
            r0 = 0
            if (r1 == 0) goto L_0x000b
        L_0x000a:
            r0 = 1
        L_0x000b:
            r0 = r0 ^ r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0917os.A01(java.lang.String):boolean");
    }

    private Ie A00() {
        Ie ie = this.A03;
        if (ie != null) {
            return ie;
        }
        Ie ie2 = new Ie();
        this.A03 = ie2;
        return ie2;
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor pointCustomTimestamp(long j) {
        if (this.A01 != 1 || j == -1) {
            this.A02 = j;
            return this;
        }
        throw new IllegalStateException("You can't collect metadata with custom timestamp, as point appeared in the past but metadata is to be collected in the present");
    }

    @Override // com.facebook.quicklog.PointEditor
    public final IY pointEditingCompleted() {
        String obj;
        if (this.A04 != null) {
            Ie ie = this.A03;
            if (ie != null) {
                ie.A03 = true;
            }
            YE ye = this.A06;
            RunnableC0929p4 p4Var = this.A05;
            int i = this.A00;
            String str = this.A04;
            Ie ie2 = this.A03;
            long j = this.A02;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            int i2 = this.A01;
            Process.myTid();
            if (YE.A0K(str)) {
                long A012 = YE.A01(ye, j, timeUnit);
                IZ iz = ye.A04;
                TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
                IU iu = iz.A04;
                iu.A00();
                try {
                    long nanos = timeUnit2.toNanos(A012) - p4Var.A09;
                    long j2 = p4Var.A08;
                    boolean z = false;
                    if (IL.A00(p4Var)) {
                        if (j2 == 0 || i2 == 0) {
                            p4Var.A00(nanos, timeUnit2, i, str, ie2, null);
                            timeUnit2.toMillis(A012);
                            timeUnit2.toNanos(A012);
                        } else {
                            z = true;
                        }
                    }
                    if (z) {
                        Ix A013 = IZ.A01(iz, j2);
                        iu.A00();
                        try {
                            p4Var.A00(nanos, timeUnit2, i, str, ie2, A013);
                            timeUnit2.toMillis(A012);
                            timeUnit2.toNanos(A012);
                        } finally {
                            iu.unlock();
                        }
                    }
                    int i3 = p4Var.A02;
                    if (YE.A0J(ye)) {
                        if (ie2 == null) {
                            obj = null;
                        } else {
                            obj = ie2.toString();
                        }
                        YE.A0F(ye, "markerPoint", i3, str, obj);
                    }
                } finally {
                    iu.unlock();
                }
            }
            this.A04 = null;
            this.A03 = null;
            this.A02 = -1;
            this.A01 = 0;
            return this;
        }
        throw new IllegalStateException("You should not use PointEditor after point was completed");
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor pointShouldIncludeMetadata(boolean z) {
        int i;
        if (!z) {
            i = 0;
        } else if (this.A02 == -1) {
            i = 1;
        } else {
            throw new IllegalStateException("You can't collect metadata with custom timestamp, as point appeared in the past but metadata is to be collected in the present");
        }
        this.A01 = i;
        return this;
    }

    public C0917os(YE ye, RunnableC0929p4 p4Var) {
        this.A06 = ye;
        this.A05 = p4Var;
        this.A07 = C0939pG.A00;
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor addPointData(String str, double d) {
        if (A01(str)) {
            A00().A00(str, String.valueOf(d), 6);
        }
        return this;
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor addPointData(String str, float f) {
        if (A01(str)) {
            A00().A00(str, String.valueOf(f), 6);
        }
        return this;
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor addPointData(String str, int i) {
        if (A01(str)) {
            A00().A00(str, String.valueOf(i), 2);
        }
        return this;
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor addPointData(String str, long j) {
        if (A01(str)) {
            A00().A00(str, String.valueOf(j), 3);
        }
        return this;
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor addPointData(String str, String str2) {
        if (A01(str) && str2 != null) {
            A00().A00(str, str2, 1);
        }
        return this;
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor addPointData(String str, boolean z) {
        if (A01(str)) {
            A00().A00(str, String.valueOf(z), 8);
        }
        return this;
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor addPointData(String str, double[] dArr) {
        if (A01(str)) {
            A00().A00(str, IM.A00(dArr), 7);
        }
        return this;
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor addPointData(String str, float[] fArr) {
        String obj;
        if (A01(str)) {
            Ie A002 = A00();
            int length = fArr.length;
            if (length == 0) {
                obj = OacrConstants.AUTO_SPEECH_DOMAIN;
            } else {
                StringBuilder sb = new StringBuilder();
                int i = 0;
                for (float f : fArr) {
                    sb.append(f);
                    sb.append(",,,");
                }
                int length2 = sb.length();
                if (length > 0) {
                    i = 3;
                }
                sb.setLength(length2 - i);
                obj = sb.toString();
            }
            A002.A00(str, obj, 7);
        }
        return this;
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor addPointData(String str, int[] iArr) {
        if (A01(str)) {
            A00().A00(str, IM.A01(iArr), 5);
        }
        return this;
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor addPointData(String str, long[] jArr) {
        if (A01(str)) {
            A00().A00(str, IM.A02(jArr), 10);
        }
        return this;
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor addPointData(String str, String[] strArr) {
        if (strArr != null && A01(str)) {
            A00().A00(str, IM.A03(strArr), 4);
        }
        return this;
    }

    @Override // com.facebook.quicklog.PointEditor
    public final PointEditor addPointData(String str, boolean[] zArr) {
        if (A01(str)) {
            A00().A00(str, IM.A04(zArr), 9);
        }
        return this;
    }
}
