package X;

import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;

/* renamed from: X.1Ae  reason: invalid class name */
public class AnonymousClass1Ae implements Runnable {
    public static final String __redex_internal_original_name = "androidx.recyclerview.widget.RecyclerView$ViewFlinger";
    public int A00;
    public int A01;
    public Interpolator A02;
    public OverScroller A03;
    public boolean A04 = false;
    public boolean A05 = false;
    public final /* synthetic */ RecyclerView A06;

    public AnonymousClass1Ae(RecyclerView recyclerView) {
        this.A06 = recyclerView;
        Interpolator interpolator = RecyclerView.sQuinticInterpolator;
        this.A02 = interpolator;
        this.A03 = new OverScroller(recyclerView.getContext(), interpolator);
    }

    public final void A00() {
        if (this.A04) {
            this.A05 = true;
            return;
        }
        RecyclerView recyclerView = this.A06;
        recyclerView.removeCallbacks(this);
        recyclerView.postOnAnimation(this);
    }

    public final void A01(int i, int i2, int i3, @Nullable Interpolator interpolator) {
        int height;
        int i4;
        int i5 = i3;
        Interpolator interpolator2 = interpolator;
        if (i5 == Integer.MIN_VALUE) {
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            boolean z = false;
            if (abs > abs2) {
                z = true;
            }
            int sqrt = (int) Math.sqrt((double) 0);
            int sqrt2 = (int) Math.sqrt((double) ((i * i) + (i2 * i2)));
            RecyclerView recyclerView = this.A06;
            if (z) {
                height = recyclerView.getWidth();
            } else {
                height = recyclerView.getHeight();
            }
            float f = (float) height;
            float f2 = (float) (height >> 1);
            float sin = f2 + (((float) Math.sin((double) ((Math.min(1.0f, (((float) sqrt2) * 1.0f) / f) - 0.5f) * 0.47123894f))) * f2);
            if (sqrt > 0) {
                i4 = Math.round(Math.abs(sin / ((float) sqrt)) * 1000.0f) << 2;
            } else {
                if (!z) {
                    abs = abs2;
                }
                i4 = (int) (((((float) abs) / f) + 1.0f) * 300.0f);
            }
            i5 = Math.min(i4, 2000);
        }
        if (interpolator == null) {
            interpolator2 = RecyclerView.sQuinticInterpolator;
        }
        if (this.A02 != interpolator2) {
            this.A02 = interpolator2;
            this.A03 = new OverScroller(this.A06.getContext(), interpolator2);
        }
        this.A01 = 0;
        this.A00 = 0;
        this.A06.setScrollState(2);
        this.A03.startScroll(0, 0, i, i2, i5);
        A00();
    }

    public final void run() {
        int i;
        int i2;
        boolean z;
        int i3;
        RecyclerView recyclerView = this.A06;
        if (recyclerView.mLayout == null) {
            recyclerView.removeCallbacks(this);
            this.A03.abortAnimation();
            return;
        }
        this.A05 = false;
        this.A04 = true;
        recyclerView.consumePendingUpdateOperations();
        OverScroller overScroller = this.A03;
        if (overScroller.computeScrollOffset()) {
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int i4 = currX - this.A00;
            int i5 = currY - this.A01;
            this.A00 = currX;
            this.A01 = currY;
            int[] iArr = recyclerView.mReusableIntPair;
            iArr[0] = 0;
            iArr[1] = 0;
            if (recyclerView.dispatchNestedPreScroll(i4, i5, iArr, null, 1)) {
                int[] iArr2 = recyclerView.mReusableIntPair;
                i4 -= iArr2[0];
                i5 -= iArr2[1];
            }
            if (recyclerView.getOverScrollMode() != 2) {
                recyclerView.considerReleasingGlowsOnScroll(i4, i5);
            }
            if (recyclerView.mAdapter != null) {
                int[] iArr3 = recyclerView.mReusableIntPair;
                iArr3[0] = 0;
                iArr3[1] = 0;
                recyclerView.scrollStep(i4, i5, iArr3);
                int[] iArr4 = recyclerView.mReusableIntPair;
                i = iArr4[0];
                i2 = iArr4[1];
                i4 -= i;
                i5 -= i2;
                AnonymousClass1An r6 = recyclerView.mLayout.mSmoothScroller;
                if (r6 != null && !r6.A04 && r6.A05) {
                    int A002 = recyclerView.mState.A00();
                    if (A002 == 0) {
                        r6.A02();
                    } else {
                        if (r6.A00 >= A002) {
                            r6.A00 = A002 - 1;
                        }
                        r6.A03(i, i2);
                    }
                }
            } else {
                i = 0;
                i2 = 0;
            }
            if (!recyclerView.mItemDecorations.isEmpty()) {
                recyclerView.invalidate();
            }
            int[] iArr5 = recyclerView.mReusableIntPair;
            iArr5[0] = 0;
            iArr5[1] = 0;
            recyclerView.dispatchNestedScroll(i, i2, i4, i5, null, 1, iArr5);
            int[] iArr6 = recyclerView.mReusableIntPair;
            int i6 = i4 - iArr6[0];
            int i7 = i5 - iArr6[1];
            if (!(i == 0 && i2 == 0)) {
                recyclerView.dispatchOnScrolled(i, i2);
            }
            if (!(recyclerView.awakenScrollBars())) {
                recyclerView.invalidate();
            }
            boolean z2 = false;
            if (overScroller.getCurrX() == overScroller.getFinalX()) {
                z2 = true;
            }
            boolean z3 = false;
            if (overScroller.getCurrY() == overScroller.getFinalY()) {
                z3 = true;
            }
            if (overScroller.isFinished() || ((z2 || i6 != 0) && (z3 || i7 != 0))) {
                z = true;
            } else {
                z = false;
            }
            AnonymousClass1An r0 = recyclerView.mLayout.mSmoothScroller;
            if ((r0 == null || !r0.A04) && z) {
                if (recyclerView.getOverScrollMode() != 2) {
                    int currVelocity = (int) overScroller.getCurrVelocity();
                    if (i6 < 0) {
                        i3 = -currVelocity;
                    } else {
                        i3 = 0;
                        if (i6 > 0) {
                            i3 = currVelocity;
                        }
                    }
                    if (i7 < 0) {
                        currVelocity = -currVelocity;
                    } else if (i7 <= 0) {
                        currVelocity = 0;
                    }
                    recyclerView.absorbGlows(i3, currVelocity);
                }
                AnonymousClass1Ak r2 = recyclerView.mPrefetchRegistry;
                int[] iArr7 = r2.A03;
                if (iArr7 != null) {
                    Arrays.fill(iArr7, -1);
                }
                r2.A00 = 0;
            } else {
                A00();
                AnonymousClass1Ai r02 = recyclerView.mGapWorker;
                if (r02 != null) {
                    r02.A01(recyclerView, i, i2);
                }
            }
        }
        AnonymousClass1An r1 = recyclerView.mLayout.mSmoothScroller;
        if (r1 != null && r1.A04) {
            r1.A03(0, 0);
        }
        this.A04 = false;
        if (this.A05) {
            recyclerView.removeCallbacks(this);
            recyclerView.postOnAnimation(this);
            return;
        }
        recyclerView.setScrollState(0);
        recyclerView.stopNestedScroll(1);
    }
}
