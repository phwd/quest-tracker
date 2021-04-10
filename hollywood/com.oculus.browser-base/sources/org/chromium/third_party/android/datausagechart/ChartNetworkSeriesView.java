package org.chromium.third_party.android.datausagechart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChartNetworkSeriesView extends View {
    public AbstractC0523In F;
    public AbstractC0523In G;
    public Paint H;
    public Paint I;

    /* renamed from: J  reason: collision with root package name */
    public Paint f11012J;
    public Paint K;
    public NetworkStatsHistory L;
    public Path M;
    public Path N;
    public Path O;
    public long P;
    public long Q;
    public long R;
    public long S;
    public long T = Long.MIN_VALUE;
    public boolean U;

    public ChartNetworkSeriesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.w, 0, 0);
        int color = obtainStyledAttributes.getColor(2, -65536);
        int color2 = obtainStyledAttributes.getColor(0, -65536);
        int color3 = obtainStyledAttributes.getColor(1, -65536);
        Paint paint = new Paint();
        this.H = paint;
        paint.setStrokeWidth(getResources().getDisplayMetrics().density * 4.0f);
        this.H.setColor(color);
        this.H.setStyle(Paint.Style.STROKE);
        this.H.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.I = paint2;
        paint2.setColor(color2);
        this.I.setStyle(Paint.Style.FILL);
        this.I.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.f11012J = paint3;
        paint3.setColor(color3);
        this.f11012J.setStyle(Paint.Style.FILL);
        this.f11012J.setAntiAlias(true);
        Paint paint4 = new Paint();
        this.K = paint4;
        paint4.setStrokeWidth(3.0f);
        this.K.setColor(color3);
        this.K.setStyle(Paint.Style.STROKE);
        this.K.setAntiAlias(true);
        this.K.setPathEffect(new DashPathEffect(new float[]{10.0f, 10.0f}, 1.0f));
        setWillNotDraw(false);
        obtainStyledAttributes.recycle();
        this.M = new Path();
        this.N = new Path();
        this.O = new Path();
    }

    public long a() {
        long j;
        long j2;
        NetworkStatsHistory networkStatsHistory = this.L;
        if (networkStatsHistory == null) {
            return 0;
        }
        long j3 = networkStatsHistory.G > 0 ? networkStatsHistory.H[0] : Long.MAX_VALUE;
        long c = networkStatsHistory.c();
        long j4 = -1;
        long j5 = networkStatsHistory.f11013J != null ? 0 : -1;
        if (networkStatsHistory.L != null) {
            j4 = 0;
        }
        for (int h = networkStatsHistory.h(c); h >= 0; h--) {
            long j6 = networkStatsHistory.H[h];
            long j7 = networkStatsHistory.F;
            long j8 = j6 + j7;
            if (j8 <= j3) {
                break;
            }
            if (j6 >= c) {
                j = 0;
            } else {
                if (j6 < Long.MAX_VALUE && j8 > Long.MAX_VALUE) {
                    j2 = j7;
                } else {
                    if (j8 >= c) {
                        j8 = c;
                    }
                    if (j6 <= j3) {
                        j6 = j3;
                    }
                    j2 = j8 - j6;
                }
                j = 0;
                if (j2 > 0) {
                    long[] jArr = networkStatsHistory.I;
                    if (jArr != null) {
                        long j9 = (jArr[h] * j2) / j7;
                    }
                    long[] jArr2 = networkStatsHistory.f11013J;
                    if (jArr2 != null) {
                        j5 = ((jArr2[h] * j2) / j7) + j5;
                    }
                    long[] jArr3 = networkStatsHistory.K;
                    if (jArr3 != null) {
                        long j10 = (jArr3[h] * j2) / j7;
                    }
                    long[] jArr4 = networkStatsHistory.L;
                    if (jArr4 != null) {
                        j4 = ((jArr4[h] * j2) / j7) + j4;
                    }
                    long[] jArr5 = networkStatsHistory.M;
                    if (jArr5 != null) {
                        long j11 = (jArr5[h] * j2) / j7;
                    }
                    long[] jArr6 = networkStatsHistory.N;
                    if (jArr6 != null) {
                        long j12 = (jArr6[h] * j2) / j7;
                    }
                }
            }
        }
        return j5 + j4;
    }

    public void b() {
        this.U = false;
        invalidate();
    }

    public void c() {
        invalidate();
    }

    public void d(long j, long j2) {
        this.R = j;
        this.S = j2;
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        float f;
        long j;
        int i;
        int i2;
        if (!this.U) {
            this.M.reset();
            this.N.reset();
            this.O.reset();
            this.U = true;
            NetworkStatsHistory networkStatsHistory = this.L;
            if (networkStatsHistory != null && networkStatsHistory.G >= 1) {
                float height = (float) getHeight();
                long d = this.F.d(0.0f);
                this.M.moveTo(0.0f, height);
                this.N.moveTo(0.0f, height);
                long j2 = 0;
                C0889On0 on0 = null;
                NetworkStatsHistory networkStatsHistory2 = this.L;
                int binarySearch = Arrays.binarySearch(networkStatsHistory2.H, 0, networkStatsHistory2.G, this.P);
                if (binarySearch < 0) {
                    binarySearch = ~binarySearch;
                }
                int max = Math.max(0, Math.min(networkStatsHistory2.G - 1, binarySearch - 1));
                int h = this.L.h(this.Q);
                float f2 = height;
                float f3 = 0.0f;
                while (max <= h) {
                    NetworkStatsHistory networkStatsHistory3 = this.L;
                    if (on0 == null) {
                        on0 = new C0889On0();
                    }
                    long j3 = d;
                    long j4 = networkStatsHistory3.H[max];
                    on0.b = j4;
                    long j5 = networkStatsHistory3.F;
                    on0.f8649a = j5;
                    long[] jArr = networkStatsHistory3.I;
                    long j6 = -1;
                    if (jArr != null) {
                        long j7 = jArr[max];
                        f = height;
                        j = j7;
                    } else {
                        f = height;
                        j = -1;
                    }
                    on0.c = j;
                    long[] jArr2 = networkStatsHistory3.f11013J;
                    on0.d = jArr2 != null ? jArr2[max] : -1;
                    long[] jArr3 = networkStatsHistory3.K;
                    on0.e = jArr3 != null ? jArr3[max] : -1;
                    long[] jArr4 = networkStatsHistory3.L;
                    on0.f = jArr4 != null ? jArr4[max] : -1;
                    long[] jArr5 = networkStatsHistory3.M;
                    on0.g = jArr5 != null ? jArr5[max] : -1;
                    long[] jArr6 = networkStatsHistory3.N;
                    if (jArr6 != null) {
                        j6 = jArr6[max];
                    }
                    on0.h = j6;
                    long j8 = j4 + j5;
                    float b = this.F.b(j4);
                    float b2 = this.F.b(j8);
                    if (b2 < 0.0f) {
                        i = max;
                        i2 = h;
                    } else {
                        i = max;
                        i2 = h;
                        long j9 = on0.d + on0.f + j2;
                        float b3 = this.G.b(j9);
                        if (j3 != j4) {
                            this.M.lineTo(b, f2);
                            this.N.lineTo(b, f2);
                        }
                        this.M.lineTo(b2, b3);
                        this.N.lineTo(b2, b3);
                        f2 = b3;
                        f3 = b2;
                        j3 = j8;
                        j2 = j9;
                    }
                    max = i + 1;
                    height = f;
                    d = j3;
                    h = i2;
                }
                long j10 = this.T;
                if (d < j10) {
                    f3 = this.F.b(j10);
                    this.M.lineTo(f3, f2);
                    this.N.lineTo(f3, f2);
                }
                this.N.lineTo(f3, height);
                this.N.lineTo(0.0f, height);
                invalidate();
            }
        }
        float b4 = this.F.b(this.R);
        float b5 = this.F.b(this.S);
        int save = canvas.save();
        canvas.clipRect(0.0f, 0.0f, b4, (float) getHeight());
        canvas.drawPath(this.N, this.f11012J);
        canvas.restoreToCount(save);
        int save2 = canvas.save();
        canvas.clipRect(b5, 0.0f, (float) getWidth(), (float) getHeight());
        canvas.drawPath(this.N, this.f11012J);
        canvas.restoreToCount(save2);
        int save3 = canvas.save();
        canvas.clipRect(b4, 0.0f, b5, (float) getHeight());
        canvas.drawPath(this.N, this.I);
        canvas.drawPath(this.M, this.H);
        canvas.restoreToCount(save3);
    }
}
