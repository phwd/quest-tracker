package X;

import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: X.1An  reason: invalid class name */
public abstract class AnonymousClass1An {
    public int A00 = -1;
    public View A01;
    public AnonymousClass1Ag A02;
    public RecyclerView A03;
    public boolean A04;
    public boolean A05;
    public boolean A06;
    public final AnonymousClass1Ay A07 = new AnonymousClass1Ay();

    @Nullable
    private final PointF A01(int i) {
        AnonymousClass1Ag r1 = this.A02;
        if (r1 instanceof AnonymousClass1BR) {
            return ((AnonymousClass1BR) r1).computeScrollVectorForPosition(i);
        }
        Log.w(RecyclerView.TAG, AnonymousClass006.A07("You should override computeScrollVectorForPosition when the LayoutManager does not implement ", AnonymousClass1BR.class.getCanonicalName()));
        return null;
    }

    public final void A02() {
        if (this.A05) {
            this.A05 = false;
            AnonymousClass1Ao r0 = (AnonymousClass1Ao) this;
            r0.A02 = 0;
            r0.A01 = 0;
            r0.A03 = null;
            this.A03.mState.A09 = -1;
            this.A01 = null;
            this.A00 = -1;
            this.A04 = false;
            this.A02.onSmoothScrollerStopped(this);
            this.A02 = null;
            this.A03 = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01c5, code lost:
        if (r1 < 0) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01b3, code lost:
        if (r1 < 0) goto L_0x00dc;
     */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x015b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A03(int r12, int r13) {
        /*
        // Method dump skipped, instructions count: 493
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1An.A03(int, int):void");
    }
}
