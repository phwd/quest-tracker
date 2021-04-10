package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference$SavedState;

/* renamed from: Jo1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Jo1 extends Preference {
    public boolean t0;
    public CharSequence u0;
    public CharSequence v0;
    public boolean w0;
    public boolean x0;

    public Jo1(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // androidx.preference.Preference
    public Object B(TypedArray typedArray, int i) {
        return Boolean.valueOf(typedArray.getBoolean(i, false));
    }

    @Override // androidx.preference.Preference
    public void F(Parcelable parcelable) {
        if (!parcelable.getClass().equals(TwoStatePreference$SavedState.class)) {
            super.F(parcelable);
            return;
        }
        TwoStatePreference$SavedState twoStatePreference$SavedState = (TwoStatePreference$SavedState) parcelable;
        super.F(twoStatePreference$SavedState.getSuperState());
        a0(twoStatePreference$SavedState.F);
    }

    @Override // androidx.preference.Preference
    public Parcelable G() {
        Parcelable G = super.G();
        if (this.X) {
            return G;
        }
        TwoStatePreference$SavedState twoStatePreference$SavedState = new TwoStatePreference$SavedState(G);
        twoStatePreference$SavedState.F = this.t0;
        return twoStatePreference$SavedState;
    }

    @Override // androidx.preference.Preference
    public void H(Object obj) {
        if (obj == null) {
            obj = Boolean.FALSE;
        }
        a0(l(((Boolean) obj).booleanValue()));
    }

    @Override // androidx.preference.Preference
    public boolean X() {
        return (this.x0 ? this.t0 : !this.t0) || super.X();
    }

    public void a0(boolean z) {
        boolean z2 = this.t0 != z;
        if (z2 || !this.w0) {
            this.t0 = z;
            this.w0 = true;
            if (Y() && z != l(!z)) {
                o();
                SharedPreferences.Editor b = this.G.b();
                b.putBoolean(this.Q, z);
                if (!this.G.e) {
                    b.apply();
                }
            }
            if (z2) {
                t(X());
                s();
            }
        }
    }

    public void b0(int i) {
        c0(this.F.getString(i));
    }

    public void c0(CharSequence charSequence) {
        this.v0 = charSequence;
        if (!this.t0) {
            s();
        }
    }

    public void d0(int i) {
        e0(this.F.getString(i));
    }

    public void e0(CharSequence charSequence) {
        this.u0 = charSequence;
        if (this.t0) {
            s();
        }
    }

    public void f0(C4886tF0 tf0) {
        g0(tf0.x(16908304));
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void g0(android.view.View r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof android.widget.TextView
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            android.widget.TextView r5 = (android.widget.TextView) r5
            r0 = 1
            boolean r1 = r4.t0
            r2 = 0
            if (r1 == 0) goto L_0x001c
            java.lang.CharSequence r1 = r4.u0
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x001c
            java.lang.CharSequence r0 = r4.u0
            r5.setText(r0)
        L_0x001a:
            r0 = r2
            goto L_0x002e
        L_0x001c:
            boolean r1 = r4.t0
            if (r1 != 0) goto L_0x002e
            java.lang.CharSequence r1 = r4.v0
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x002e
            java.lang.CharSequence r0 = r4.v0
            r5.setText(r0)
            goto L_0x001a
        L_0x002e:
            if (r0 == 0) goto L_0x003e
            java.lang.CharSequence r1 = r4.p()
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L_0x003e
            r5.setText(r1)
            r0 = r2
        L_0x003e:
            r1 = 8
            if (r0 != 0) goto L_0x0043
            goto L_0x0044
        L_0x0043:
            r2 = r1
        L_0x0044:
            int r0 = r5.getVisibility()
            if (r2 == r0) goto L_0x004d
            r5.setVisibility(r2)
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Jo1.g0(android.view.View):void");
    }

    @Override // androidx.preference.Preference
    public void y() {
        boolean z = !this.t0;
        if (f(Boolean.valueOf(z))) {
            a0(z);
        }
    }
}
