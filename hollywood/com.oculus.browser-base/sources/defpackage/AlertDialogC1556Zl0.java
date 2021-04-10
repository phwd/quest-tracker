package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.NumberPicker;

/* renamed from: Zl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AlertDialogC1556Zl0 extends AlertDialog implements DialogInterface.OnClickListener {
    public final NumberPicker F;
    public final NumberPicker G;
    public final NumberPicker H;
    public final NumberPicker I;

    /* renamed from: J  reason: collision with root package name */
    public final NumberPicker f9366J;
    public final M10 K;
    public final int L;
    public final int M;
    public final boolean N;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00da, code lost:
        if (r7 == 0) goto L_0x00dc;
     */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x013c  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x019d  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01bb A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0218 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x023b  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0244  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AlertDialogC1556Zl0(android.content.Context r20, int r21, int r22, int r23, int r24, int r25, int r26, int r27, int r28, boolean r29, defpackage.M10 r30) {
        /*
        // Method dump skipped, instructions count: 613
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AlertDialogC1556Zl0.<init>(android.content.Context, int, int, int, int, int, int, int, int, boolean, M10):void");
    }

    public final int a(NumberPicker numberPicker) {
        numberPicker.clearFocus();
        return numberPicker.getValue();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        int a2 = a(this.F);
        int a3 = a(this.G);
        int a4 = a(this.H);
        int a5 = (a(this.I) * this.L) + this.M;
        if (!this.N) {
            int a6 = a(this.f9366J);
            if (a2 == 12) {
                a2 = 0;
            }
            a2 += a6 * 12;
        }
        M10 m10 = this.K;
        m10.b.b(m10.f8456a, 0, 0, 0, a2, a3, a4, a5, 0);
    }
}
