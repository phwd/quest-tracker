package X;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* renamed from: X.0er  reason: invalid class name and case insensitive filesystem */
public class DialogC04410er extends Dialog implements AnonymousClass02k {
    public AnonymousClass02m A00;
    public final AnonymousClass0AR A01;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DialogC04410er(android.content.Context r6, int r7) {
        /*
            r5 = this;
            r0 = r7
            if (r7 != 0) goto L_0x0015
            android.util.TypedValue r3 = new android.util.TypedValue
            r3.<init>()
            android.content.res.Resources$Theme r2 = r6.getTheme()
            r1 = 2130837603(0x7f020063, float:1.7280165E38)
            r0 = 1
            r2.resolveAttribute(r1, r3, r0)
            int r0 = r3.resourceId
        L_0x0015:
            r5.<init>(r6, r0)
            X.0es r0 = new X.0es
            r0.<init>(r5)
            r5.A01 = r0
            X.02m r4 = A01(r5)
            if (r7 != 0) goto L_0x0037
            android.util.TypedValue r3 = new android.util.TypedValue
            r3.<init>()
            android.content.res.Resources$Theme r2 = r6.getTheme()
            r1 = 2130837603(0x7f020063, float:1.7280165E38)
            r0 = 1
            r2.resolveAttribute(r1, r3, r0)
            int r7 = r3.resourceId
        L_0x0037:
            r4.A0S(r7)
            r0 = 0
            r4.A0V(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.DialogC04410er.<init>(android.content.Context, int):void");
    }

    @Override // X.AnonymousClass02k
    @Nullable
    public final AnonymousClass03D onWindowStartingSupportActionMode(AnonymousClass03C r2) {
        return null;
    }

    public static final AnonymousClass02m A01(DialogC04410er r3) {
        AnonymousClass02m r0 = r3.A00;
        if (r0 != null) {
            return r0;
        }
        LayoutInflater$Factory2C04430et r02 = new LayoutInflater$Factory2C04430et(r3.getContext(), r3.getWindow(), r3, r3);
        r3.A00 = r02;
        return r02;
    }

    public final boolean A02(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        A01(this).A0Y(view, layoutParams);
    }

    public final void dismiss() {
        super.dismiss();
        A01(this).A0O();
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return AnonymousClass0AS.A00(this.A01, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    @Nullable
    public final <T extends View> T findViewById(@IdRes int i) {
        return (T) A01(this).A0I(i);
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final void invalidateOptionsMenu() {
        A01(this).A0N();
    }

    public void onCreate(Bundle bundle) {
        A01(this).A0M();
        super.onCreate(bundle);
        A01(this).A0V(bundle);
    }

    public final void onStop() {
        super.onStop();
        A01(this).A0R();
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        A01(this).A0b(charSequence);
    }

    @Override // android.app.Dialog
    public final void setContentView(@LayoutRes int i) {
        A01(this).A0T(i);
    }

    @Override // android.app.Dialog
    public final void setContentView(View view) {
        A01(this).A0X(view);
    }

    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        A01(this).A0Z(view, layoutParams);
    }

    @Override // android.app.Dialog
    public final void setTitle(int i) {
        super.setTitle(i);
        A01(this).A0b(getContext().getString(i));
    }
}
