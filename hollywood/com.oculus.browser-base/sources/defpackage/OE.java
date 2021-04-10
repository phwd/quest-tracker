package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

/* renamed from: OE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OE extends AbstractComponentCallbacksC3550lS implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    public DialogInterface.OnCancelListener A0 = new ME(this);
    public DialogInterface.OnDismissListener B0 = new NE(this);
    public int C0 = 0;
    public int D0 = 0;
    public boolean E0 = true;
    public boolean F0 = true;
    public int G0 = -1;
    public boolean H0;
    public Dialog I0;
    public boolean J0;
    public boolean K0;
    public boolean L0;
    public Handler y0;
    public Runnable z0 = new LE(this);

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void C0(Bundle bundle) {
        Dialog dialog = this.I0;
        if (dialog != null) {
            bundle.putBundle("android:savedDialogState", dialog.onSaveInstanceState());
        }
        int i = this.C0;
        if (i != 0) {
            bundle.putInt("android:style", i);
        }
        int i2 = this.D0;
        if (i2 != 0) {
            bundle.putInt("android:theme", i2);
        }
        boolean z = this.E0;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.F0;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i3 = this.G0;
        if (i3 != -1) {
            bundle.putInt("android:backStackId", i3);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void D0() {
        this.i0 = true;
        Dialog dialog = this.I0;
        if (dialog != null) {
            this.J0 = false;
            dialog.show();
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void E0() {
        this.i0 = true;
        Dialog dialog = this.I0;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void b0(Bundle bundle) {
        Bundle bundle2;
        this.i0 = true;
        if (this.F0) {
            View view = this.k0;
            if (this.I0 != null) {
                if (view != null) {
                    if (view.getParent() == null) {
                        this.I0.setContentView(view);
                    } else {
                        throw new IllegalStateException("DialogFragment can not be attached to a container view");
                    }
                }
                Context x = x();
                if (x instanceof Activity) {
                    this.I0.setOwnerActivity((Activity) x);
                }
                this.I0.setCancelable(this.E0);
                this.I0.setOnCancelListener(this.A0);
                this.I0.setOnDismissListener(this.B0);
                if (bundle != null && (bundle2 = bundle.getBundle("android:savedDialogState")) != null) {
                    this.I0.onRestoreInstanceState(bundle2);
                }
            }
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void e0(Context context) {
        super.e0(context);
        if (!this.L0) {
            this.K0 = false;
        }
    }

    public void e1() {
        f1(true, false);
    }

    public final void f1(boolean z, boolean z2) {
        if (!this.K0) {
            this.K0 = true;
            this.L0 = false;
            Dialog dialog = this.I0;
            if (dialog != null) {
                dialog.setOnDismissListener(null);
                this.I0.dismiss();
                if (!z2) {
                    if (Looper.myLooper() == this.y0.getLooper()) {
                        onDismiss(this.I0);
                    } else {
                        this.y0.post(this.z0);
                    }
                }
            }
            this.J0 = true;
            if (this.G0 >= 0) {
                KS G = G();
                int i = this.G0;
                if (i >= 0) {
                    G.B(new IS(G, null, i, 1), false);
                    this.G0 = -1;
                    return;
                }
                throw new IllegalArgumentException(AbstractC2531fV.w("Bad id: ", i));
            }
            C0317Fe fe = new C0317Fe(G());
            fe.p(this);
            if (z) {
                fe.f();
            } else {
                fe.e();
            }
        }
    }

    public Dialog g1(Bundle bundle) {
        return new Dialog(P0(), this.D0);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        this.y0 = new Handler();
        this.F0 = this.b0 == 0;
        if (bundle != null) {
            this.C0 = bundle.getInt("android:style", 0);
            this.D0 = bundle.getInt("android:theme", 0);
            this.E0 = bundle.getBoolean("android:cancelable", true);
            this.F0 = bundle.getBoolean("android:showsDialog", this.F0);
            this.G0 = bundle.getInt("android:backStackId", -1);
        }
    }

    public final Dialog h1() {
        Dialog dialog = this.I0;
        if (dialog != null) {
            return dialog;
        }
        throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
    }

    public void i1(boolean z) {
        this.E0 = z;
        Dialog dialog = this.I0;
        if (dialog != null) {
            dialog.setCancelable(z);
        }
    }

    public int j1(C0317Fe fe, String str) {
        this.K0 = false;
        this.L0 = true;
        fe.i(0, this, str, 1);
        this.J0 = false;
        int e = fe.e();
        this.G0 = e;
        return e;
    }

    public void k1(KS ks, String str) {
        this.K0 = false;
        this.L0 = true;
        C0317Fe fe = new C0317Fe(ks);
        fe.i(0, this, str, 1);
        fe.e();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void n0() {
        this.i0 = true;
        Dialog dialog = this.I0;
        if (dialog != null) {
            this.J0 = true;
            dialog.setOnDismissListener(null);
            this.I0.dismiss();
            if (!this.K0) {
                onDismiss(this.I0);
            }
            this.I0 = null;
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void o0() {
        this.i0 = true;
        if (!this.L0 && !this.K0) {
            this.K0 = true;
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.J0) {
            f1(true, true);
        }
    }

    /* JADX INFO: finally extract failed */
    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public LayoutInflater p0(Bundle bundle) {
        LayoutInflater E = E();
        if (!this.F0 || this.H0) {
            return E;
        }
        try {
            this.H0 = true;
            Dialog g1 = g1(bundle);
            this.I0 = g1;
            int i = this.C0;
            if (!(i == 1 || i == 2)) {
                if (i != 3) {
                    this.H0 = false;
                    return E.cloneInContext(h1().getContext());
                }
                Window window = g1.getWindow();
                if (window != null) {
                    window.addFlags(24);
                }
            }
            g1.requestWindowFeature(1);
            this.H0 = false;
            return E.cloneInContext(h1().getContext());
        } catch (Throwable th) {
            this.H0 = false;
            throw th;
        }
    }
}
