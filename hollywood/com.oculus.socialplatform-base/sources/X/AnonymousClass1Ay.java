package X;

import android.util.Log;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: X.1Ay  reason: invalid class name */
public class AnonymousClass1Ay {
    public int A00 = 0;
    public int A01 = Integer.MIN_VALUE;
    public int A02 = 0;
    public int A03 = 0;
    public int A04 = -1;
    public Interpolator A05 = null;
    public boolean A06 = false;

    public final void A00(RecyclerView recyclerView) {
        int i = this.A04;
        if (i >= 0) {
            this.A04 = -1;
            recyclerView.jumpToPositionForSmoothScroller(i);
        } else if (this.A06) {
            Interpolator interpolator = this.A05;
            if (interpolator == null || this.A01 >= 1) {
                int i2 = this.A01;
                if (i2 >= 1) {
                    recyclerView.mViewFlinger.A01(this.A02, this.A03, i2, interpolator);
                    int i3 = this.A00 + 1;
                    this.A00 = i3;
                    if (i3 > 10) {
                        Log.e(RecyclerView.TAG, "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                } else {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            } else {
                throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
            }
        } else {
            this.A00 = 0;
            return;
        }
        this.A06 = false;
    }
}
