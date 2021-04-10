package defpackage;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.SigninFragmentBase;
import org.chromium.chrome.browser.signin.ui.ConfirmImportSyncDataDialog;
import org.chromium.chrome.browser.signin.ui.ConfirmManagedSyncDataDialog;
import org.chromium.chrome.browser.signin.ui.ConfirmSyncDataStateMachineDelegate$ProgressDialogFragment;

/* renamed from: Cx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0177Cx implements AbstractC4490qx, AbstractC5680xx {

    /* renamed from: a  reason: collision with root package name */
    public int f7848a = 0;
    public final TV0 b;
    public final String c;
    public final String d;
    public final C0421Gx e;
    public final Handler f = new Handler();
    public boolean g;
    public Boolean h;
    public Runnable i;

    public C0177Cx(C0421Gx gx, String str, String str2, TV0 tv0) {
        Object obj = ThreadUtils.f10596a;
        this.e = gx;
        this.c = str;
        this.d = str2;
        this.b = tv0;
        d();
        c();
    }

    public void a(boolean z) {
        Object obj = ThreadUtils.f10596a;
        Runnable runnable = this.i;
        if (runnable != null) {
            this.f.removeCallbacks(runnable);
            this.i = null;
        }
        this.f7848a = 4;
        if (!z) {
            SigninFragmentBase signinFragmentBase = this.b.b;
            signinFragmentBase.T0 = null;
            signinFragmentBase.N0 = false;
            this.e.a();
        }
    }

    public final void b() {
        if (this.h.booleanValue()) {
            C0421Gx gx = this.e;
            String J2 = C5949zZ.a().d(Profile.b()).J(this.d);
            gx.a();
            ConfirmManagedSyncDataDialog confirmManagedSyncDataDialog = new ConfirmManagedSyncDataDialog();
            Bundle bundle = new Bundle();
            bundle.putString("domain", J2);
            confirmManagedSyncDataDialog.U0(bundle);
            confirmManagedSyncDataDialog.M0 = this;
            gx.c(confirmManagedSyncDataDialog, "ConfirmManagedSyncDataDialog");
            return;
        }
        this.e.a();
        c();
    }

    public final void c() {
        C5232vH0 vh0;
        int i2 = this.f7848a;
        if (i2 == 0) {
            this.f7848a = 1;
            if (TextUtils.isEmpty(this.c) || this.d.equals(this.c)) {
                c();
                return;
            }
            C0421Gx gx = this.e;
            String str = this.c;
            String str2 = this.d;
            gx.a();
            ConfirmImportSyncDataDialog confirmImportSyncDataDialog = new ConfirmImportSyncDataDialog();
            Bundle bundle = new Bundle();
            bundle.putString("lastAccountName", str);
            bundle.putString("newAccountName", str2);
            confirmImportSyncDataDialog.U0(bundle);
            confirmImportSyncDataDialog.P0 = this;
            gx.c(confirmImportSyncDataDialog, "ConfirmImportSyncDataDialog");
        } else if (i2 == 1) {
            this.f7848a = 2;
            if (this.h != null) {
                b();
                return;
            }
            C0421Gx gx2 = this.e;
            C6020zx zxVar = new C6020zx(this);
            gx2.a();
            ConfirmSyncDataStateMachineDelegate$ProgressDialogFragment confirmSyncDataStateMachineDelegate$ProgressDialogFragment = new ConfirmSyncDataStateMachineDelegate$ProgressDialogFragment();
            confirmSyncDataStateMachineDelegate$ProgressDialogFragment.N0 = zxVar;
            gx2.c(confirmSyncDataStateMachineDelegate$ProgressDialogFragment, "ConfirmSyncTimeoutDialog");
            if (this.i == null) {
                this.i = new RunnableC0055Ax(this);
            }
            this.f.postDelayed(this.i, 30000);
        } else if (i2 == 2) {
            this.f7848a = 4;
            TV0 tv0 = this.b;
            boolean z = this.g;
            SigninFragmentBase signinFragmentBase = tv0.b;
            signinFragmentBase.T0 = null;
            if (!signinFragmentBase.M0) {
                if (z) {
                    vh0 = new C5232vH0();
                    C0695Li li = new C0695Li();
                    li.b(new RunnableC4516r51(li, vh0));
                } else {
                    vh0 = C5232vH0.c(null);
                }
                vh0.g(new RV0(tv0, tv0.f8962a));
            }
        } else if (i2 == 4) {
            throw new IllegalStateException("Can't progress from DONE state!");
        }
    }

    public final void d() {
        C5949zZ.a().d(Profile.b()).j(this.d, new C5850yx(this));
    }
}
