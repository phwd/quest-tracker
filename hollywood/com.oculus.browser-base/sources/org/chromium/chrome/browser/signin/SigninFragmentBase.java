package org.chromium.chrome.browser.signin;

import J.N;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.ui.SigninScrollView;
import org.chromium.chrome.browser.signin.ui.SigninView;
import org.chromium.chrome.browser.signin.ui.account_picker.AccountPickerDialogFragment;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.AccountTrackerService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class SigninFragmentBase extends AbstractComponentCallbacksC3550lS implements AbstractC5523x1 {
    public static final /* synthetic */ int y0 = 0;
    public int A0;
    public SigninView B0;
    public C3296jy C0;
    public boolean D0;
    public String E0;
    public String F0;
    public boolean G0;
    public W1 H0 = new DV0(this);
    public VG0 I0 = new HV0(this);
    public WG0 J0;
    public List K0;
    public boolean L0;
    public boolean M0;
    public boolean N0;
    public boolean O0;
    public boolean P0;
    public Zr1 Q0;
    public DialogC2461f4 R0;
    public long S0;
    public C0177Cx T0;
    public int z0;

    public static Bundle f1(String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("SigninFragmentBase.SigninFlowType", 0);
        bundle.putString("SigninFragmentBase.AccountName", str);
        return bundle;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        boolean z;
        this.i0 = true;
        this.L0 = true;
        AccountManagerFacadeProvider.getInstance().a(this.H0);
        this.J0.U(this.I0);
        y1();
        O6 o6 = this.B0.U;
        Objects.requireNonNull(o6);
        if (Build.VERSION.SDK_INT >= 26) {
            z = ValueAnimator.areAnimatorsEnabled();
        } else {
            z = Settings.Global.getFloat(ContextUtils.getApplicationContext().getContentResolver(), "animator_duration_scale", 1.0f) != 0.0f;
        }
        if (z) {
            L6.c((Drawable) o6.b, o6.c);
            o6.b.start();
            o6.d = true;
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void c0(int i, int i2, Intent intent) {
        String stringExtra;
        if (i == 1 && i2 == -1 && intent != null && (stringExtra = intent.getStringExtra("authAccount")) != null) {
            AccountPickerDialogFragment i1 = i1();
            if (i1 != null) {
                i1.e1();
            }
            AccountManagerFacadeProvider.getInstance().j(new FV0(this, stringExtra));
        }
    }

    public final boolean e1() {
        boolean z;
        if (!Z()) {
            return false;
        }
        KS ks = this.W;
        if (ks == null) {
            z = false;
        } else {
            z = ks.U();
        }
        if (!z && !this.D0 && !this.N0 && !this.O0) {
            return true;
        }
        return false;
    }

    public final void g1() {
        Zr1 zr1 = this.Q0;
        if (zr1 != null) {
            Dialog dialog = zr1.b;
            if (dialog != null) {
                dialog.cancel();
                zr1.b = null;
            }
            this.Q0 = null;
        }
    }

    @Override // defpackage.AbstractC5523x1
    public void h() {
        AbstractC3535lK0.a("Signin_AddAccountToDevice");
        AccountManagerFacadeProvider.getInstance().q(new EV0(this));
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        Bundle k1 = k1();
        this.E0 = k1.getString("SigninFragmentBase.AccountName", null);
        int i = 0;
        this.A0 = k1.getInt("SigninFragmentBase.ChildAccountStatus", 0);
        int i2 = k1.getInt("SigninFragmentBase.SigninFlowType", 0);
        this.z0 = i2;
        this.D0 = true;
        if (bundle == null) {
            if (i2 == 2) {
                x1();
            } else if (i2 == 3) {
                h();
            }
        }
        this.C0 = new C3296jy(I());
        Activity u = u();
        if (AbstractC1254Un.a(this.A0)) {
            i = R.drawable.f29460_resource_name_obfuscated_RES_2131230986;
        }
        this.J0 = WG0.V(u, i);
        this.P0 = true;
    }

    public final void h1() {
        DialogC2461f4 f4Var = this.R0;
        if (f4Var != null) {
            f4Var.dismiss();
            this.R0 = null;
            AbstractC3364kK0.k("Signin.AndroidGmsUpdatingDialogShownTime", SystemClock.elapsedRealtime() - this.S0);
        }
    }

    @Override // defpackage.AbstractC5523x1
    public void i() {
    }

    public final AccountPickerDialogFragment i1() {
        return (AccountPickerDialogFragment) this.W.J("SigninFragmentBase.AccountPickerDialogFragment");
    }

    public abstract int j1();

    public abstract Bundle k1();

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Drawable drawable;
        SigninView signinView = (SigninView) layoutInflater.inflate(R.layout.f41500_resource_name_obfuscated_RES_2131624459, viewGroup, false);
        this.B0 = signinView;
        signinView.I.setOnClickListener(new IV0(this));
        this.B0.R.setOnClickListener(new JV0(this));
        this.B0.Q.setVisibility(8);
        this.B0.S.setVisibility(0);
        this.B0.S.setOnClickListener(new KV0(this));
        this.B0.F.c(new LV0(this));
        this.B0.P.setMovementMethod(LinkMovementMethod.getInstance());
        if (this.z0 == 1) {
            drawable = AbstractC5544x8.a(x(), R.drawable.f29730_resource_name_obfuscated_RES_2131231013);
            this.B0.R.setVisibility(8);
            this.B0.T.setVisibility(4);
        } else {
            drawable = AbstractC2417ep1.f(x(), R.drawable.f30150_resource_name_obfuscated_RES_2131231055, R.color.f11390_resource_name_obfuscated_RES_2131099829);
        }
        this.B0.M.setImageDrawable(drawable);
        this.C0.b(this.B0.H, R.string.f62270_resource_name_obfuscated_RES_2131953544, null);
        this.C0.b(this.B0.N, R.string.f62260_resource_name_obfuscated_RES_2131953543, null);
        this.C0.b(this.B0.O, this.A0 == 1 ? R.string.f62250_resource_name_obfuscated_RES_2131953542 : R.string.f62240_resource_name_obfuscated_RES_2131953541, null);
        this.C0.b(this.B0.R, j1(), null);
        this.C0.b(this.B0.S, R.string.f55090_resource_name_obfuscated_RES_2131952826, null);
        w1(true);
        if (this.F0 != null) {
            m1();
        }
        return this.B0;
    }

    public boolean l1() {
        return this.z0 == 1;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void m0() {
        this.i0 = true;
        g1();
        h1();
        C0177Cx cx = this.T0;
        if (cx != null) {
            cx.a(true);
            this.T0 = null;
        }
        if (this.P0) {
            AbstractC3535lK0.a("Signin_Undo_Signin");
        }
        this.M0 = true;
    }

    public final void n1() {
        if (!l1() && e1()) {
            x1();
        }
    }

    public final void o1() {
        SigninScrollView signinScrollView = this.B0.F;
        signinScrollView.smoothScrollBy(0, signinScrollView.getHeight());
        AbstractC3535lK0.a("Signin_MoreButton_Shown");
    }

    public final void p1() {
        if (e1()) {
            h();
        }
    }

    public final void q1() {
        AbstractC3535lK0.a("Signin_Undo_Signin");
        this.P0 = false;
        s1();
    }

    @Override // defpackage.AbstractC5523x1
    public void r(String str, boolean z) {
        v1(str, z);
        i1().e1();
    }

    public abstract void r1(String str, boolean z, boolean z2, Runnable runnable);

    public abstract void s1();

    public final void t1(boolean z) {
        this.T0 = new C0177Cx(new C0421Gx(w()), N.Ma80fvz5(Wr1.a(Profile.b()).f10883a, "google.services.last_username"), this.F0, new TV0(this, z));
    }

    public final void u1(boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        AccountTrackerService b = C5949zZ.a().b(Profile.b());
        if (b.b()) {
            AbstractC3364kK0.k("Signin.AndroidAccountSigninViewSeedingTime", SystemClock.elapsedRealtime() - elapsedRealtime);
            t1(z);
            return;
        }
        b.a(new QV0(this, b, elapsedRealtime, z));
    }

    public final void v1(String str, boolean z) {
        this.F0 = str;
        this.G0 = z;
        this.J0.Z(Collections.singletonList(str));
        m1();
        AccountPickerDialogFragment i1 = i1();
        if (i1 != null) {
            J1 j1 = i1.M0.f11654a;
            if (!TextUtils.equals(j1.e, str)) {
                j1.e = str;
                Iterator it = j1.f8264a.iterator();
                while (it.hasNext()) {
                    C4765sb0 sb0 = (C4765sb0) it.next();
                    if (sb0.f11283a == 1) {
                        UH0 uh0 = sb0.b;
                        uh0.j(M1.b, TextUtils.equals(j1.e, ((C3522lG) uh0.g(M1.f8455a)).f10337a));
                    }
                }
            }
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void w0() {
        this.i0 = true;
        this.L0 = false;
        this.J0.Y(this.I0);
        AccountManagerFacadeProvider.getInstance().m(this.H0);
        O6 o6 = this.B0.U;
        if (o6.d) {
            L6.d((Drawable) o6.b, o6.c);
            o6.b.stop();
            o6.d = false;
        }
    }

    public final void w1(boolean z) {
        C4467qp0 qp0 = null;
        if (z) {
            this.B0.I.setVisibility(0);
            this.C0.b(this.B0.Q, R.string.f61970_resource_name_obfuscated_RES_2131953514, null);
            this.B0.Q.setOnClickListener(new MV0(this));
        } else {
            this.B0.I.setVisibility(8);
            this.C0.b(this.B0.Q, R.string.f62060_resource_name_obfuscated_RES_2131953523, null);
            this.B0.Q.setOnClickListener(new NV0(this));
        }
        if (z) {
            qp0 = new C4467qp0(I(), new OV0(this));
        }
        EY0 ey0 = new EY0("<LINK1>", "</LINK1>", qp0);
        C3296jy jyVar = this.C0;
        TextView textView = this.B0.P;
        SpannableString a2 = FY0.a(jyVar.f10252a.getText(R.string.f62080_resource_name_obfuscated_RES_2131953525).toString(), ey0);
        textView.setText(a2);
        jyVar.b.put(textView, new C3125iy(a2.toString(), R.string.f62080_resource_name_obfuscated_RES_2131953525));
    }

    public final void x1() {
        if (i1() == null) {
            String str = this.F0;
            AccountPickerDialogFragment accountPickerDialogFragment = new AccountPickerDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("AccountPickerDialogFragment.SelectedAccountName", str);
            accountPickerDialogFragment.U0(bundle);
            accountPickerDialogFragment.b1(this, 2);
            C0317Fe fe = new C0317Fe(this.W);
            fe.i(0, accountPickerDialogFragment, "SigninFragmentBase.AccountPickerDialogFragment", 1);
            fe.f();
        }
    }

    public final void y1() {
        AccountManagerFacadeProvider.getInstance().o(new GV0(this));
    }

    /* renamed from: z1 */
    public final void m1() {
        String str = this.F0;
        if (str != null) {
            C3522lG W = this.J0.W(str);
            this.B0.f10766J.setImageDrawable(W.b);
            String str2 = W.c;
            if (!TextUtils.isEmpty(str2)) {
                this.C0.c(this.B0.K, str2);
                this.C0.c(this.B0.L, W.f10337a);
                this.B0.L.setVisibility(0);
                return;
            }
            this.C0.c(this.B0.K, W.f10337a);
            this.B0.L.setVisibility(8);
        }
    }
}
