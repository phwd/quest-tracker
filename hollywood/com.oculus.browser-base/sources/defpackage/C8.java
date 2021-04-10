package defpackage;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.AppCompatSpinner;

/* renamed from: C8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C8 implements J8, DialogInterface.OnClickListener {
    public DialogC2461f4 F;
    public ListAdapter G;
    public CharSequence H;
    public final /* synthetic */ AppCompatSpinner I;

    public C8(AppCompatSpinner appCompatSpinner) {
        this.I = appCompatSpinner;
    }

    @Override // defpackage.J8
    public boolean b() {
        DialogC2461f4 f4Var = this.F;
        if (f4Var != null) {
            return f4Var.isShowing();
        }
        return false;
    }

    @Override // defpackage.J8
    public int c() {
        return 0;
    }

    @Override // defpackage.J8
    public void dismiss() {
        DialogC2461f4 f4Var = this.F;
        if (f4Var != null) {
            f4Var.dismiss();
            this.F = null;
        }
    }

    @Override // defpackage.J8
    public Drawable e() {
        return null;
    }

    @Override // defpackage.J8
    public void g(CharSequence charSequence) {
        this.H = charSequence;
    }

    @Override // defpackage.J8
    public void h(Drawable drawable) {
        Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.J8
    public void i(int i) {
        Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.J8
    public void j(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.J8
    public void k(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.J8
    public void l(int i, int i2) {
        if (this.G != null) {
            C2290e4 e4Var = new C2290e4(this.I.getPopupContext());
            CharSequence charSequence = this.H;
            if (charSequence != null) {
                e4Var.f9828a.d = charSequence;
            }
            ListAdapter listAdapter = this.G;
            int selectedItemPosition = this.I.getSelectedItemPosition();
            C1598a4 a4Var = e4Var.f9828a;
            a4Var.o = listAdapter;
            a4Var.p = this;
            a4Var.v = selectedItemPosition;
            a4Var.u = true;
            DialogC2461f4 a2 = e4Var.a();
            this.F = a2;
            ListView listView = a2.H.g;
            listView.setTextDirection(i);
            listView.setTextAlignment(i2);
            this.F.show();
        }
    }

    @Override // defpackage.J8
    public int m() {
        return 0;
    }

    @Override // defpackage.J8
    public CharSequence o() {
        return this.H;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.I.setSelection(i);
        if (this.I.getOnItemClickListener() != null) {
            this.I.performItemClick(null, i, this.G.getItemId(i));
        }
        DialogC2461f4 f4Var = this.F;
        if (f4Var != null) {
            f4Var.dismiss();
            this.F = null;
        }
    }

    @Override // defpackage.J8
    public void p(ListAdapter listAdapter) {
        this.G = listAdapter;
    }
}
