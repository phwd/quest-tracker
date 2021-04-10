package X;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* renamed from: X.1rS  reason: invalid class name and case insensitive filesystem */
public class DialogC11221rS extends Dialog implements AbstractC11541s9 {
    public AbstractC11211rM A00;
    public final AnonymousClass07B A01;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DialogC11221rS(android.content.Context r6, int r7) {
        /*
            r5 = this;
            r0 = r7
            if (r7 != 0) goto L_0x0015
            android.util.TypedValue r3 = new android.util.TypedValue
            r3.<init>()
            android.content.res.Resources$Theme r2 = r6.getTheme()
            r1 = 2130968723(0x7f040093, float:1.7546108E38)
            r0 = 1
            r2.resolveAttribute(r1, r3, r0)
            int r0 = r3.resourceId
        L_0x0015:
            r5.<init>(r6, r0)
            X.1s7 r0 = new X.1s7
            r0.<init>(r5)
            r5.A01 = r0
            X.1rM r4 = A00(r5)
            if (r7 != 0) goto L_0x0037
            android.util.TypedValue r3 = new android.util.TypedValue
            r3.<init>()
            android.content.res.Resources$Theme r2 = r6.getTheme()
            r1 = 2130968723(0x7f040093, float:1.7546108E38)
            r0 = 1
            r2.resolveAttribute(r1, r3, r0)
            int r7 = r3.resourceId
        L_0x0037:
            boolean r0 = r4 instanceof X.AnonymousClass1rJ
            if (r0 == 0) goto L_0x0040
            r0 = r4
            X.1rJ r0 = (X.AnonymousClass1rJ) r0
            r0.A02 = r7
        L_0x0040:
            r0 = 0
            r4.A0I(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.DialogC11221rS.<init>(android.content.Context, int):void");
    }

    @Override // X.AbstractC11541s9
    @Nullable
    public final AbstractC11301rk onWindowStartingSupportActionMode(AbstractC11461s1 r2) {
        return null;
    }

    public static final AbstractC11211rM A00(DialogC11221rS r3) {
        AbstractC11211rM r0 = r3.A00;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1rJ r02 = new AnonymousClass1rJ(r3.getContext(), r3.getWindow(), r3, r3);
        r3.A00 = r02;
        return r02;
    }

    public final boolean A02(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        AnonymousClass1rJ r2 = (AnonymousClass1rJ) A00(this);
        AnonymousClass1rJ.A05(r2);
        ((ViewGroup) r2.A07.findViewById(16908290)).addView(view, layoutParams);
        ((Window$CallbackC11241rW) r2.A0C).A00.onContentChanged();
    }

    public final void dismiss() {
        super.dismiss();
        A00(this).A0F();
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return AnonymousClass07C.A00(this.A01, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    @Nullable
    public final <T extends View> T findViewById(@IdRes int i) {
        AnonymousClass1rJ r0 = (AnonymousClass1rJ) A00(this);
        AnonymousClass1rJ.A05(r0);
        return (T) r0.A08.findViewById(i);
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final void invalidateOptionsMenu() {
        A00(this).A0E();
    }

    public void onCreate(Bundle bundle) {
        AnonymousClass1rJ r2 = (AnonymousClass1rJ) A00(this);
        LayoutInflater from = LayoutInflater.from(r2.A0j);
        if (from.getFactory() == null) {
            from.setFactory2(r2);
        } else {
            from.getFactory2();
        }
        super.onCreate(bundle);
        A00(this).A0I(bundle);
    }

    public final void onStop() {
        super.onStop();
        A00(this).A0G();
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        A00(this).A0L(charSequence);
    }

    @Override // android.app.Dialog
    public final void setContentView(@LayoutRes int i) {
        A00(this).A0H(i);
    }

    @Override // android.app.Dialog
    public final void setContentView(View view) {
        A00(this).A0J(view);
    }

    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        A00(this).A0K(view, layoutParams);
    }

    @Override // android.app.Dialog
    public final void setTitle(int i) {
        super.setTitle(i);
        A00(this).A0L(getContext().getString(i));
    }
}
