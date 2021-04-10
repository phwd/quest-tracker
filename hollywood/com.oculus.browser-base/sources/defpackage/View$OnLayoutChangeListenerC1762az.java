package defpackage;

import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import java.util.Objects;

/* renamed from: az  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC1762az implements View.OnLayoutChangeListener {
    public final /* synthetic */ DialogC2104cz F;

    public View$OnLayoutChangeListenerC1762az(DialogC2104cz czVar) {
        this.F = czVar;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.F.K) {
            if (view.getMeasuredHeight() != 0) {
                DialogC2104cz czVar = this.F;
                int i9 = (int) czVar.H;
                int i10 = (int) (czVar.I + czVar.f9735J);
                Rect rect = new Rect(i9, i10, i9, i10);
                DialogC2104cz czVar2 = this.F;
                czVar2.O = new O4(czVar2.F, czVar2.P, new ColorDrawable(0), this.F.G, new C4390qK0(rect));
                this.F.O.K.setOutsideTouchable(false);
                this.F.O.d();
            } else {
                return;
            }
        } else if (view.getMeasuredHeight() != 0) {
            DialogC2104cz czVar3 = this.F;
            Objects.requireNonNull(czVar3);
            Rect rect2 = new Rect();
            czVar3.F.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect2);
            float f = ((float) rect2.top) + czVar3.f9735J;
            int[] iArr = new int[2];
            czVar3.G.getLocationOnScreen(iArr);
            czVar3.N = iArr[1];
            float f2 = (czVar3.H - ((float) iArr[0])) + ((float) rect2.left);
            czVar3.L = f2;
            float f3 = (czVar3.I - ((float) iArr[1])) + f;
            czVar3.M = f3;
            czVar3.G.startAnimation(czVar3.b(true, f2, f3));
        } else {
            return;
        }
        view.removeOnLayoutChangeListener(this);
    }
}
