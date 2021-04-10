package defpackage;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* renamed from: aF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractDialogInterface$OnClickListenerC1632aF0 extends OE implements DialogInterface.OnClickListener {
    public WE M0;
    public CharSequence N0;
    public CharSequence O0;
    public CharSequence P0;
    public CharSequence Q0;
    public int R0;
    public BitmapDrawable S0;
    public int T0;

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void C0(Bundle bundle) {
        super.C0(bundle);
        bundle.putCharSequence("PreferenceDialogFragment.title", this.N0);
        bundle.putCharSequence("PreferenceDialogFragment.positiveText", this.O0);
        bundle.putCharSequence("PreferenceDialogFragment.negativeText", this.P0);
        bundle.putCharSequence("PreferenceDialogFragment.message", this.Q0);
        bundle.putInt("PreferenceDialogFragment.layout", this.R0);
        BitmapDrawable bitmapDrawable = this.S0;
        if (bitmapDrawable != null) {
            bundle.putParcelable("PreferenceDialogFragment.icon", bitmapDrawable.getBitmap());
        }
    }

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        this.T0 = -2;
        C2290e4 e4Var = new C2290e4(x());
        CharSequence charSequence = this.N0;
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.d = charSequence;
        a4Var.c = this.S0;
        e4Var.f(this.O0, this);
        CharSequence charSequence2 = this.P0;
        C1598a4 a4Var2 = e4Var.f9828a;
        a4Var2.i = charSequence2;
        a4Var2.j = this;
        x();
        View n1 = n1();
        if (n1 != null) {
            m1(n1);
            C1598a4 a4Var3 = e4Var.f9828a;
            a4Var3.r = n1;
            a4Var3.q = 0;
        } else {
            e4Var.f9828a.f = this.Q0;
        }
        p1(e4Var);
        DialogC2461f4 a2 = e4Var.a();
        if (this instanceof UJ) {
            a2.getWindow().setSoftInputMode(5);
        }
        return a2;
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        AbstractComponentCallbacksC3550lS R = R();
        if (R instanceof VE) {
            VE ve = (VE) R;
            String string = this.K.getString("key");
            if (bundle == null) {
                WE we = (WE) ((AbstractC2324eF0) ve).f1(string);
                this.M0 = we;
                this.N0 = we.t0;
                this.O0 = we.w0;
                this.P0 = we.x0;
                this.Q0 = we.u0;
                this.R0 = we.y0;
                Drawable drawable = we.v0;
                if (drawable == null || (drawable instanceof BitmapDrawable)) {
                    this.S0 = (BitmapDrawable) drawable;
                    return;
                }
                Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
                this.S0 = new BitmapDrawable(I(), createBitmap);
                return;
            }
            this.N0 = bundle.getCharSequence("PreferenceDialogFragment.title");
            this.O0 = bundle.getCharSequence("PreferenceDialogFragment.positiveText");
            this.P0 = bundle.getCharSequence("PreferenceDialogFragment.negativeText");
            this.Q0 = bundle.getCharSequence("PreferenceDialogFragment.message");
            this.R0 = bundle.getInt("PreferenceDialogFragment.layout", 0);
            Bitmap bitmap = (Bitmap) bundle.getParcelable("PreferenceDialogFragment.icon");
            if (bitmap != null) {
                this.S0 = new BitmapDrawable(I(), bitmap);
                return;
            }
            return;
        }
        throw new IllegalStateException("Target fragment must implement TargetFragment interface");
    }

    public WE l1() {
        if (this.M0 == null) {
            this.M0 = (WE) ((AbstractC2324eF0) ((VE) R())).f1(this.K.getString("key"));
        }
        return this.M0;
    }

    public void m1(View view) {
        View findViewById = view.findViewById(16908299);
        if (findViewById != null) {
            CharSequence charSequence = this.Q0;
            int i = 8;
            if (!TextUtils.isEmpty(charSequence)) {
                if (findViewById instanceof TextView) {
                    ((TextView) findViewById).setText(charSequence);
                }
                i = 0;
            }
            if (findViewById.getVisibility() != i) {
                findViewById.setVisibility(i);
            }
        }
    }

    public View n1() {
        int i = this.R0;
        if (i == 0) {
            return null;
        }
        LayoutInflater layoutInflater = this.r0;
        if (layoutInflater == null) {
            layoutInflater = J0(null);
        }
        return layoutInflater.inflate(i, (ViewGroup) null);
    }

    public abstract void o1(boolean z);

    public void onClick(DialogInterface dialogInterface, int i) {
        this.T0 = i;
    }

    @Override // defpackage.OE
    public void onDismiss(DialogInterface dialogInterface) {
        boolean z = true;
        if (!this.J0) {
            f1(true, true);
        }
        if (this.T0 != -1) {
            z = false;
        }
        o1(z);
    }

    public void p1(C2290e4 e4Var) {
    }
}
