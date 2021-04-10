package defpackage;

import android.content.Context;
import android.view.View;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: l2  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3480l2 extends C1488Yi0 {
    public final /* synthetic */ C4676s2 m;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3480l2(C4676s2 s2Var, Context context, SubMenuC4510r31 r31, View view) {
        super(context, r31, view, false, R.attr.f1600_resource_name_obfuscated_RES_2130968606, 0);
        this.m = s2Var;
        if (!r31.A.g()) {
            View view2 = s2Var.N;
            this.f = view2 == null ? (View) s2Var.M : view2;
        }
        d(s2Var.c0);
    }

    @Override // defpackage.C1488Yi0
    public void c() {
        C4676s2 s2Var = this.m;
        s2Var.Z = null;
        Objects.requireNonNull(s2Var);
        super.c();
    }
}
