package X;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;

/* renamed from: X.2aA  reason: invalid class name */
public class AnonymousClass2aA implements AnonymousClass2aK {
    public ConstraintLayout A00;
    public int A01;
    public int A02;
    public int A03;
    public int A04;
    public int A05;
    public int A06;
    public final /* synthetic */ ConstraintLayout A07;

    public static boolean A00(int i, int i2, int i3) {
        if (i != i2) {
            int mode = View.MeasureSpec.getMode(i);
            View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (mode2 == 1073741824) {
                return (mode == Integer.MIN_VALUE || mode == 0) && i3 == size;
            }
            return false;
        }
    }

    public AnonymousClass2aA(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2) {
        this.A07 = constraintLayout;
        this.A00 = constraintLayout2;
    }

    @Override // X.AnonymousClass2aK
    public final void A2c() {
        int childCount = this.A00.getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            this.A00.getChildAt(i2);
        }
        ArrayList<AnonymousClass2a3> arrayList = this.A00.mConstraintHelpers;
        int size = arrayList.size();
        if (size > 0) {
            do {
                arrayList.get(i);
                i++;
            } while (i < size);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0190, code lost:
        if (r7 == X.AnonymousClass007.A00) goto L_0x0192;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x019d, code lost:
        if (r20.A01 <= com.oculus.vrshell.panels.AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) goto L_0x019f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x01a8, code lost:
        if (r20.A01 <= com.oculus.vrshell.panels.AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) goto L_0x01aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x01d0, code lost:
        if (r4 != r21.A05) goto L_0x01d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x01ed, code lost:
        if (r2 != false) goto L_0x01da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0262, code lost:
        if (r3 != -1) goto L_0x01c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0186, code lost:
        if (r8 == X.AnonymousClass007.A00) goto L_0x0188;
     */
    @Override // X.AnonymousClass2aK
    @android.annotation.SuppressLint({"WrongCall"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A6N(X.AnonymousClass2ac r20, X.AnonymousClass2aH r21) {
        /*
        // Method dump skipped, instructions count: 642
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass2aA.A6N(X.2ac, X.2aH):void");
    }
}
