package defpackage;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: l8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractDialogC3498l8 extends Dialog implements M7 {
    public Q7 F;
    public final P40 G;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AbstractDialogC3498l8(android.content.Context r5, int r6) {
        /*
            r4 = this;
            r0 = 1
            r1 = 2130968828(0x7f0400fc, float:1.754632E38)
            if (r6 != 0) goto L_0x0015
            android.util.TypedValue r2 = new android.util.TypedValue
            r2.<init>()
            android.content.res.Resources$Theme r3 = r5.getTheme()
            r3.resolveAttribute(r1, r2, r0)
            int r2 = r2.resourceId
            goto L_0x0016
        L_0x0015:
            r2 = r6
        L_0x0016:
            r4.<init>(r5, r2)
            k8 r2 = new k8
            r2.<init>(r4)
            r4.G = r2
            Q7 r2 = r4.a()
            if (r6 != 0) goto L_0x0034
            android.util.TypedValue r6 = new android.util.TypedValue
            r6.<init>()
            android.content.res.Resources$Theme r5 = r5.getTheme()
            r5.resolveAttribute(r1, r6, r0)
            int r6 = r6.resourceId
        L_0x0034:
            r5 = r2
            j8 r5 = (defpackage.LayoutInflater$Factory2C3156j8) r5
            r5.x0 = r6
            r5 = 0
            r2.g(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractDialogC3498l8.<init>(android.content.Context, int):void");
    }

    public Q7 a() {
        if (this.F == null) {
            int i = Q7.F;
            this.F = new LayoutInflater$Factory2C3156j8(getContext(), getWindow(), this, this);
        }
        return this.F;
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().c(view, layoutParams);
    }

    public boolean b(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public void dismiss() {
        super.dismiss();
        a().h();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return Q40.b(this.G, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public View findViewById(int i) {
        LayoutInflater$Factory2C3156j8 j8Var = (LayoutInflater$Factory2C3156j8) a();
        j8Var.z();
        return j8Var.O.findViewById(i);
    }

    @Override // defpackage.M7
    public AbstractC5696y2 i(AbstractC5526x2 x2Var) {
        return null;
    }

    public void invalidateOptionsMenu() {
        a().f();
    }

    public void onCreate(Bundle bundle) {
        a().e();
        super.onCreate(bundle);
        a().g(bundle);
    }

    public void onStop() {
        super.onStop();
        a().i();
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        a().l(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        a().p(charSequence);
    }

    @Override // defpackage.M7
    public void t(AbstractC5696y2 y2Var) {
    }

    @Override // defpackage.M7
    public void x(AbstractC5696y2 y2Var) {
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        a().m(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().n(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        a().p(getContext().getString(i));
    }
}
