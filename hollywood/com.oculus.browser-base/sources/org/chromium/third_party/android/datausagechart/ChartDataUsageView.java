package org.chromium.third_party.android.datausagechart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewDebug;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChartDataUsageView extends FrameLayout {
    public AbstractC0523In F;
    public AbstractC0523In G;
    @ViewDebug.ExportedProperty
    public int H = -1;
    public float I;

    /* renamed from: J  reason: collision with root package name */
    public Rect f11011J = new Rect();
    public Rect K = new Rect();
    public ChartNetworkSeriesView L;
    public ChartNetworkSeriesView M;
    public NetworkStatsHistory N;
    public long O;
    public long P;
    public long Q;

    public ChartDataUsageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.x, 0, 0);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        float f = obtainStyledAttributes.getFloat(1, 0.0f);
        this.H = dimensionPixelSize;
        this.I = f;
        requestLayout();
        obtainStyledAttributes.recycle();
        setClipToPadding(false);
        setClipChildren(false);
        C0645Kn kn = new C0645Kn();
        O30 o30 = new O30(new C0584Jn());
        this.F = kn;
        this.G = o30;
    }

    public final void a() {
        long j = this.O;
        long j2 = this.P;
        if (this.M.getVisibility() == 0) {
            this.M.d(j, j2);
            this.L.d(j, j2);
            return;
        }
        this.L.d(j, j2);
    }

    public final void b() {
        long max = Math.max(Math.max((Math.max(Math.max(this.L.a(), this.M.a()), 0L) * 12) / 10, 1048576L), 0L);
        if (max != this.Q) {
            this.Q = max;
            if (this.G.a(0, max)) {
                this.L.b();
                this.M.b();
            }
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.L = (ChartNetworkSeriesView) findViewById(R.id.original_series);
        this.M = (ChartNetworkSeriesView) findViewById(R.id.compressed_series);
        ChartNetworkSeriesView chartNetworkSeriesView = this.L;
        AbstractC0523In in = this.F;
        AbstractC0523In in2 = this.G;
        Objects.requireNonNull(chartNetworkSeriesView);
        Objects.requireNonNull(in, "missing horiz");
        Objects.requireNonNull(in2, "missing vert");
        chartNetworkSeriesView.F = in;
        chartNetworkSeriesView.G = in2;
        ChartNetworkSeriesView chartNetworkSeriesView2 = this.M;
        AbstractC0523In in3 = this.F;
        AbstractC0523In in4 = this.G;
        Objects.requireNonNull(chartNetworkSeriesView2);
        Objects.requireNonNull(in3, "missing horiz");
        Objects.requireNonNull(in4, "missing vert");
        chartNetworkSeriesView2.F = in3;
        chartNetworkSeriesView2.G = in4;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f11011J.set(getPaddingLeft(), getPaddingTop(), (i3 - i) - getPaddingRight(), (i4 - i2) - getPaddingBottom());
        int width = this.f11011J.width();
        int height = this.f11011J.height();
        this.F.c((float) width);
        this.G.c((float) height);
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            if (childAt instanceof ChartNetworkSeriesView) {
                Gravity.apply(layoutParams.gravity, width, height, this.f11011J, this.K);
                Rect rect = this.K;
                childAt.layout(rect.left, rect.top, rect.right, rect.bottom);
            }
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int i3 = this.H;
        int i4 = measuredWidth - i3;
        if (i3 > 0 && i4 > 0) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec((int) ((((float) i4) * this.I) + ((float) i3)), 1073741824), i2);
        }
    }
}
