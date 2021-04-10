package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.os.SystemClock;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: rC0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogC4536rC0 extends DialogC2461f4 implements AbstractC4877tC0, AbstractC4195pC0 {
    public EC0 I;

    /* renamed from: J  reason: collision with root package name */
    public C4366qC0 f11190J;
    public boolean K;

    public DialogC4536rC0(WindowAndroid windowAndroid, ContentResolver contentResolver, AbstractC4707sC0 sc0, boolean z, boolean z2, List list) {
        super((Context) windowAndroid.f11022J.get(), R.style.f72740_resource_name_obfuscated_RES_2132017847);
        this.f11190J = new C4366qC0(sc0);
        EC0 ec0 = new EC0(windowAndroid, contentResolver, z, z2, this);
        this.I = ec0;
        C4366qC0 qc0 = this.f11190J;
        ec0.F = this;
        ec0.L = qc0;
        ec0.k0 = new ArrayList(list);
        GP gp = ec0.h0;
        if (gp != null) {
            gp.b(true);
        }
        ec0.i0 = SystemClock.elapsedRealtime();
        GP gp2 = new GP(ec0.H, ec0, new C0030Ak0(ec0.k0, true), ec0.k0, ec0.I);
        ec0.h0 = gp2;
        Executor executor = AbstractC2032cb.f9616a;
        gp2.f();
        ((ExecutorC1463Ya) executor).execute(gp2.e);
        ec0.F.setOnCancelListener(new AC0(ec0));
        EC0 ec02 = this.I;
        C2120d4 d4Var = this.H;
        d4Var.h = ec02;
        d4Var.i = 0;
        d4Var.n = false;
    }

    @Override // defpackage.AbstractDialogC3498l8
    public void dismiss() {
        if (!this.f11190J.b || this.K) {
            super.dismiss();
            EC0 ec0 = this.I;
            GP gp = ec0.h0;
            if (gp != null) {
                gp.b(true);
                ec0.h0 = null;
            }
            BinderC5899zD zDVar = ec0.M;
            if (zDVar != null) {
                Context context = (Context) ec0.H.f11022J.get();
                if (zDVar.r != null) {
                    context.unbindService(zDVar.s);
                    zDVar.r = null;
                }
                ec0.M = null;
            }
            ec0.F = null;
            this.f11190J.f11123a.c();
        }
    }

    public void onBackPressed() {
        if (!this.I.l0.a()) {
            super.onBackPressed();
        }
    }
}
