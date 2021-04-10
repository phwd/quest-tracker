package defpackage;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: FK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FK0 implements Rt1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ IK0 f8011a;

    public FK0(IK0 ik0) {
        this.f8011a = ik0;
    }

    @Override // defpackage.Rt1
    public int a() {
        IK0 ik0 = this.f8011a;
        return ik0.p - ik0.P();
    }

    @Override // defpackage.Rt1
    public int b(View view) {
        return this.f8011a.D(view) - ((ViewGroup.MarginLayoutParams) ((JK0) view.getLayoutParams())).leftMargin;
    }

    @Override // defpackage.Rt1
    public View c(int i) {
        return this.f8011a.y(i);
    }

    @Override // defpackage.Rt1
    public int d() {
        return this.f8011a.O();
    }

    @Override // defpackage.Rt1
    public int e(View view) {
        return this.f8011a.G(view) + ((ViewGroup.MarginLayoutParams) ((JK0) view.getLayoutParams())).rightMargin;
    }
}
