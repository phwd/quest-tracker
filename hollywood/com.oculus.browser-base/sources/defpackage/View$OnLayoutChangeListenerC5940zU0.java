package defpackage;

import android.app.Activity;
import android.content.res.Configuration;
import android.view.View;
import com.oculus.browser.R;
import java.util.Set;
import org.chromium.base.Callback;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: zU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC5940zU0 implements Jy1, AbstractC3464kx, View.OnLayoutChangeListener {
    public final AbstractC4448qj F;
    public final Q31 G;
    public final CU0 H;
    public final Callback I;

    /* renamed from: J  reason: collision with root package name */
    public final C2528fT0 f11747J;
    public final boolean K;
    public final HZ L = null;
    public long M;
    public boolean N;
    public boolean O;
    public Set P;
    public Activity Q;
    public M2 R;
    public C1569Zs S;
    public C2189dU0 T;
    public C5090uU0 U;
    public WindowAndroid V;
    public final AbstractC0576Jj W;
    public final X60 X;
    public final Tm1 Y;

    public View$OnLayoutChangeListenerC5940zU0(AbstractC4448qj qjVar, M2 m2, Q31 q31, CU0 cu0, Callback callback, X60 x60, C2528fT0 ft0, boolean z, HZ hz, Tm1 tm1) {
        this.F = qjVar;
        this.R = m2;
        m2.a(this);
        this.G = q31;
        this.H = cu0;
        this.I = callback;
        this.f11747J = ft0;
        this.K = z;
        C5770yU0 yu0 = new C5770yU0(this);
        this.W = yu0;
        ((C5638xj) qjVar).j(yu0);
        this.X = x60;
        this.Y = tm1;
    }

    @Override // defpackage.Jy1
    public void f() {
        C5090uU0 uu0 = this.U;
        if (uu0 != null) {
            ((C5638xj) this.F).p(uu0, true, 0);
        }
    }

    @Override // defpackage.Jy1
    public void g() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:116:0x03fb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void h(defpackage.C2189dU0 r32, defpackage.C1915bt r33, long r34) {
        /*
        // Method dump skipped, instructions count: 1107
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.View$OnLayoutChangeListenerC5940zU0.h(dU0, bt, long):void");
    }

    @Override // defpackage.AbstractC3464kx
    public void onConfigurationChanged(Configuration configuration) {
        boolean isInMultiWindowMode;
        Set set;
        Activity activity = this.Q;
        if (activity != null && this.O != (isInMultiWindowMode = activity.isInMultiWindowMode()) && (set = this.P) != null) {
            this.O = isInMultiWindowMode;
            this.U.x(this.S.a(set, isInMultiWindowMode));
            ((C5638xj) this.F).u(this.U, false);
        }
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i7 - i5 != i3 - i) {
            this.U.I.findViewById(R.id.share_sheet_chrome_apps).invalidate();
            this.U.I.findViewById(R.id.share_sheet_chrome_apps).requestLayout();
            this.U.I.findViewById(R.id.share_sheet_other_apps).invalidate();
            this.U.I.findViewById(R.id.share_sheet_other_apps).requestLayout();
        }
    }
}
