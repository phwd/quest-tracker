package defpackage;

import android.os.Bundle;
import android.view.View;
import androidx.preference.Preference;

/* renamed from: rF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4545rF0 extends C5349w {
    public final /* synthetic */ C4716sF0 d;

    public C4545rF0(C4716sF0 sf0) {
        this.d = sf0;
    }

    @Override // defpackage.C5349w
    public void d(View view, D d2) {
        Preference u;
        this.d.g.d(view, d2);
        int K = this.d.f.K(view);
        AbstractC5750yK0 yk0 = this.d.f.T;
        if ((yk0 instanceof C3520lF0) && (u = ((C3520lF0) yk0).u(K)) != null) {
            u.C(d2);
        }
    }

    @Override // defpackage.C5349w
    public boolean g(View view, int i, Bundle bundle) {
        return this.d.g.g(view, i, bundle);
    }
}
