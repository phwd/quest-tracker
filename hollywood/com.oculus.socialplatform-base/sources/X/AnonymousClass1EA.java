package X;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.localmedia.MediaProviderUtils;
import com.oculus.vrshell.panels.AndroidPanelLayer;

@VisibleForTesting
/* renamed from: X.1EA  reason: invalid class name */
public final class AnonymousClass1EA extends AnonymousClass1B2 implements AnonymousClass1BM {
    public static final int[] A0T = new int[0];
    public static final int[] A0U = {16842919};
    @VisibleForTesting
    public float A00;
    @VisibleForTesting
    public float A01;
    public int A02 = 0;
    @VisibleForTesting
    public int A03;
    @VisibleForTesting
    public int A04;
    public int A05 = 0;
    public int A06 = 0;
    public int A07 = 0;
    @VisibleForTesting
    public int A08;
    @VisibleForTesting
    public int A09;
    public RecyclerView A0A;
    public boolean A0B = false;
    public boolean A0C = false;
    public int A0D = 0;
    public final int A0E;
    public final int A0F;
    public final int A0G;
    public final int A0H;
    public final int A0I;
    public final int A0J;
    public final ValueAnimator A0K = ValueAnimator.ofFloat(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, 1.0f);
    public final Drawable A0L;
    public final Drawable A0M;
    public final StateListDrawable A0N;
    public final StateListDrawable A0O;
    public final AnonymousClass1CG A0P = new AnonymousClass1EB(this);
    public final Runnable A0Q = new AnonymousClass1ED(this);
    public final int[] A0R = new int[2];
    public final int[] A0S = new int[2];

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A02(int r5) {
        /*
            r4 = this;
            r2 = 2
            if (r5 != r2) goto L_0x003d
            int r0 = r4.A07
            if (r0 == r2) goto L_0x0015
            android.graphics.drawable.StateListDrawable r1 = r4.A0O
            int[] r0 = X.AnonymousClass1EA.A0U
            r1.setState(r0)
            androidx.recyclerview.widget.RecyclerView r1 = r4.A0A
            java.lang.Runnable r0 = r4.A0Q
            r1.removeCallbacks(r0)
        L_0x0015:
            r4.A00()
        L_0x0018:
            int r0 = r4.A07
            if (r0 != r2) goto L_0x0037
            if (r5 == r2) goto L_0x0034
            android.graphics.drawable.StateListDrawable r1 = r4.A0O
            int[] r0 = X.AnonymousClass1EA.A0T
            r1.setState(r0)
            r1 = 1200(0x4b0, float:1.682E-42)
        L_0x0027:
            androidx.recyclerview.widget.RecyclerView r0 = r4.A0A
            java.lang.Runnable r3 = r4.A0Q
            r0.removeCallbacks(r3)
            androidx.recyclerview.widget.RecyclerView r2 = r4.A0A
            long r0 = (long) r1
            r2.postDelayed(r3, r0)
        L_0x0034:
            r4.A07 = r5
            return
        L_0x0037:
            r0 = 1
            if (r5 != r0) goto L_0x0034
            r1 = 1500(0x5dc, float:2.102E-42)
            goto L_0x0027
        L_0x003d:
            if (r5 != 0) goto L_0x0015
            androidx.recyclerview.widget.RecyclerView r0 = r4.A0A
            r0.invalidate()
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1EA.A02(int):void");
    }

    private final void A00() {
        int i = this.A02;
        if (i != 0) {
            if (i == 3) {
                this.A0K.cancel();
            } else {
                return;
            }
        }
        this.A02 = 1;
        ValueAnimator valueAnimator = this.A0K;
        valueAnimator.setFloatValues(((Number) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        valueAnimator.setDuration(500L);
        valueAnimator.setStartDelay(0);
        valueAnimator.start();
    }

    @VisibleForTesting
    private final boolean A01(float f, float f2) {
        if (this.A0A.getLayoutDirection() != 1 ? f >= ((float) (this.A06 - this.A0I)) : f <= ((float) this.A0I)) {
            int i = this.A08;
            int i2 = this.A09 >> 1;
            if (f2 < ((float) (i - i2)) || f2 > ((float) (i + i2))) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0037, code lost:
        if (r0 > 0) goto L_0x0039;
     */
    @Override // X.AnonymousClass1BM
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A7B(@androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView r9, @androidx.annotation.NonNull android.view.MotionEvent r10) {
        /*
            r8 = this;
            int r0 = r8.A07
            r7 = 0
            r2 = 2
            r4 = 1
            if (r0 != r4) goto L_0x0060
            float r1 = r10.getX()
            float r0 = r10.getY()
            boolean r6 = r8.A01(r1, r0)
            float r5 = r10.getX()
            float r3 = r10.getY()
            int r1 = r8.A05
            int r0 = r8.A0E
            int r1 = r1 - r0
            float r0 = (float) r1
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x0039
            int r3 = r8.A03
            int r0 = r8.A04
            int r1 = r0 >> 1
            int r0 = r3 - r1
            float r0 = (float) r0
            int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x0039
            int r3 = r3 + r1
            float r0 = (float) r3
            int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            r1 = 1
            if (r0 <= 0) goto L_0x003a
        L_0x0039:
            r1 = 0
        L_0x003a:
            int r0 = r10.getAction()
            if (r0 != 0) goto L_0x0052
            if (r6 != 0) goto L_0x0053
            if (r1 == 0) goto L_0x0052
        L_0x0044:
            r8.A0D = r4
            float r0 = r10.getX()
            int r0 = (int) r0
            float r0 = (float) r0
            r8.A00 = r0
        L_0x004e:
            r8.A02(r2)
        L_0x0051:
            r7 = 1
        L_0x0052:
            return r7
        L_0x0053:
            if (r1 != 0) goto L_0x0044
            r8.A0D = r2
            float r0 = r10.getY()
            int r0 = (int) r0
            float r0 = (float) r0
            r8.A01 = r0
            goto L_0x004e
        L_0x0060:
            if (r0 != r2) goto L_0x0052
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1EA.A7B(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003c, code lost:
        if (r4 > ((float) (r3 + r1))) goto L_0x003e;
     */
    @Override // X.AnonymousClass1BM
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A8D(@androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView r12, @androidx.annotation.NonNull android.view.MotionEvent r13) {
        /*
        // Method dump skipped, instructions count: 314
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1EA.A8D(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent):void");
    }

    @Override // X.AnonymousClass1B2
    public final void onDrawOver(Canvas canvas, RecyclerView recyclerView, AnonymousClass1As r11) {
        int i;
        if (this.A06 != this.A0A.getWidth() || this.A05 != this.A0A.getHeight()) {
            this.A06 = this.A0A.getWidth();
            this.A05 = this.A0A.getHeight();
            A02(0);
        } else if (this.A02 != 0) {
            if (this.A0C) {
                int i2 = this.A06;
                int i3 = this.A0I;
                int i4 = i2 - i3;
                int i5 = this.A08;
                int i6 = this.A09;
                int i7 = i5 - (i6 >> 1);
                StateListDrawable stateListDrawable = this.A0O;
                stateListDrawable.setBounds(0, 0, i3, i6);
                Drawable drawable = this.A0M;
                drawable.setBounds(0, 0, this.A0J, this.A05);
                if (this.A0A.getLayoutDirection() == 1) {
                    drawable.draw(canvas);
                    canvas.translate((float) i3, (float) i7);
                    canvas.scale(-1.0f, 1.0f);
                    stateListDrawable.draw(canvas);
                    canvas.scale(-1.0f, 1.0f);
                    i = -i3;
                } else {
                    canvas.translate((float) i4, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                    drawable.draw(canvas);
                    canvas.translate(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, (float) i7);
                    stateListDrawable.draw(canvas);
                    i = -i4;
                }
                canvas.translate((float) i, (float) (-i7));
            }
            if (this.A0B) {
                int i8 = this.A05;
                int i9 = this.A0E;
                int i10 = i8 - i9;
                int i11 = this.A03;
                int i12 = this.A04;
                int i13 = i11 - (i12 >> 1);
                StateListDrawable stateListDrawable2 = this.A0N;
                stateListDrawable2.setBounds(0, 0, i12, i9);
                Drawable drawable2 = this.A0L;
                drawable2.setBounds(0, 0, this.A06, this.A0F);
                canvas.translate(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, (float) i10);
                drawable2.draw(canvas);
                canvas.translate((float) i13, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                stateListDrawable2.draw(canvas);
                canvas.translate((float) (-i13), (float) (-i10));
            }
        }
    }

    public AnonymousClass1EA(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
        this.A0O = stateListDrawable;
        this.A0M = drawable;
        this.A0N = stateListDrawable2;
        this.A0L = drawable2;
        this.A0I = Math.max(i, stateListDrawable.getIntrinsicWidth());
        this.A0J = Math.max(i, drawable.getIntrinsicWidth());
        this.A0E = Math.max(i, stateListDrawable2.getIntrinsicWidth());
        this.A0F = Math.max(i, drawable2.getIntrinsicWidth());
        this.A0H = i2;
        this.A0G = i3;
        this.A0O.setAlpha(MediaProviderUtils.JPEG_HEADER);
        this.A0M.setAlpha(MediaProviderUtils.JPEG_HEADER);
        this.A0K.addListener(new AnonymousClass1EE(this));
        this.A0K.addUpdateListener(new AnonymousClass1EC(this));
        RecyclerView recyclerView2 = this.A0A;
        if (recyclerView2 != recyclerView) {
            if (recyclerView2 != null) {
                recyclerView2.removeItemDecoration(this);
                this.A0A.removeOnItemTouchListener(this);
                this.A0A.removeOnScrollListener(this.A0P);
                this.A0A.removeCallbacks(this.A0Q);
            }
            this.A0A = recyclerView;
            recyclerView.addItemDecoration(this, -1);
            RecyclerView recyclerView3 = this.A0A;
            recyclerView3.addOnItemTouchListener(this);
            recyclerView3.addOnScrollListener(this.A0P);
        }
    }
}
