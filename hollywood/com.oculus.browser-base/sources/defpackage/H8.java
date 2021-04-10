package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import androidx.appcompat.widget.AppCompatSpinner;

/* renamed from: H8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H8 extends AbstractC4186p90 implements J8 {
    public CharSequence h0;
    public ListAdapter i0;
    public final Rect j0 = new Rect();
    public int k0;
    public final /* synthetic */ AppCompatSpinner l0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public H8(AppCompatSpinner appCompatSpinner, Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0);
        this.l0 = appCompatSpinner;
        this.W = appCompatSpinner;
        s(true);
        this.U = 0;
        this.X = new E8(this, appCompatSpinner);
    }

    @Override // defpackage.J8
    public void g(CharSequence charSequence) {
        this.h0 = charSequence;
    }

    @Override // defpackage.J8
    public void j(int i) {
        this.k0 = i;
    }

    @Override // defpackage.J8
    public void l(int i, int i2) {
        ViewTreeObserver viewTreeObserver;
        boolean b = b();
        t();
        this.g0.setInputMethodMode(2);
        d();
        C1823bJ bJVar = this.f11052J;
        bJVar.setChoiceMode(1);
        bJVar.setTextDirection(i);
        bJVar.setTextAlignment(i2);
        int selectedItemPosition = this.l0.getSelectedItemPosition();
        C1823bJ bJVar2 = this.f11052J;
        if (b() && bJVar2 != null) {
            bJVar2.N = false;
            bJVar2.setSelection(selectedItemPosition);
            if (bJVar2.getChoiceMode() != 0) {
                bJVar2.setItemChecked(selectedItemPosition, true);
            }
        }
        if (!b && (viewTreeObserver = this.l0.getViewTreeObserver()) != null) {
            F8 f8 = new F8(this);
            viewTreeObserver.addOnGlobalLayoutListener(f8);
            this.g0.setOnDismissListener(new G8(this, f8));
        }
    }

    @Override // defpackage.J8
    public CharSequence o() {
        return this.h0;
    }

    @Override // defpackage.AbstractC4186p90, defpackage.J8
    public void p(ListAdapter listAdapter) {
        super.p(listAdapter);
        this.i0 = listAdapter;
    }

    public void t() {
        int i;
        int i2;
        Drawable e = e();
        int i3 = 0;
        if (e != null) {
            e.getPadding(this.l0.N);
            if (AbstractC4826sv1.a(this.l0)) {
                i2 = this.l0.N.right;
            } else {
                i2 = -this.l0.N.left;
            }
            i3 = i2;
        } else {
            Rect rect = this.l0.N;
            rect.right = 0;
            rect.left = 0;
        }
        int paddingLeft = this.l0.getPaddingLeft();
        int paddingRight = this.l0.getPaddingRight();
        int width = this.l0.getWidth();
        AppCompatSpinner appCompatSpinner = this.l0;
        int i4 = appCompatSpinner.M;
        if (i4 == -2) {
            int a2 = appCompatSpinner.a((SpinnerAdapter) this.i0, e());
            int i5 = this.l0.getContext().getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.l0.N;
            int i6 = (i5 - rect2.left) - rect2.right;
            if (a2 > i6) {
                a2 = i6;
            }
            r(Math.max(a2, (width - paddingLeft) - paddingRight));
        } else if (i4 == -1) {
            r((width - paddingLeft) - paddingRight);
        } else {
            r(i4);
        }
        if (AbstractC4826sv1.a(this.l0)) {
            i = (((width - paddingRight) - this.L) - this.k0) + i3;
        } else {
            i = paddingLeft + this.k0 + i3;
        }
        this.M = i;
    }
}
