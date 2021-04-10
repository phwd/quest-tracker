package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup$SavedState;
import java.util.ArrayList;
import java.util.List;

/* renamed from: hF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2837hF0 extends Preference {
    public P60 A0 = null;
    public final Runnable B0 = new RunnableC2495fF0(this);
    public final BW0 t0 = new BW0();
    public final Handler u0 = new Handler();
    public List v0 = new ArrayList();
    public boolean w0 = true;
    public int x0 = 0;
    public boolean y0 = false;
    public int z0 = Integer.MAX_VALUE;

    public AbstractC2837hF0(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.q0, i, i2);
        this.w0 = Ko1.b(obtainStyledAttributes, 2, 2, true);
        if (obtainStyledAttributes.hasValue(1)) {
            h0(obtainStyledAttributes.getInt(1, obtainStyledAttributes.getInt(1, Integer.MAX_VALUE)));
        }
        obtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public void A() {
        Z();
        this.y0 = false;
        int d0 = d0();
        for (int i = 0; i < d0; i++) {
            c0(i).A();
        }
    }

    @Override // androidx.preference.Preference
    public void F(Parcelable parcelable) {
        if (!parcelable.getClass().equals(PreferenceGroup$SavedState.class)) {
            super.F(parcelable);
            return;
        }
        PreferenceGroup$SavedState preferenceGroup$SavedState = (PreferenceGroup$SavedState) parcelable;
        this.z0 = preferenceGroup$SavedState.F;
        super.F(preferenceGroup$SavedState.getSuperState());
    }

    @Override // androidx.preference.Preference
    public Parcelable G() {
        return new PreferenceGroup$SavedState(super.G(), this.z0);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a0(androidx.preference.Preference r9) {
        /*
        // Method dump skipped, instructions count: 204
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC2837hF0.a0(androidx.preference.Preference):boolean");
    }

    public Preference b0(CharSequence charSequence) {
        Preference b0;
        if (charSequence == null) {
            throw new IllegalArgumentException("Key cannot be null");
        } else if (TextUtils.equals(this.Q, charSequence)) {
            return this;
        } else {
            int d0 = d0();
            for (int i = 0; i < d0; i++) {
                Preference c0 = c0(i);
                if (TextUtils.equals(c0.Q, charSequence)) {
                    return c0;
                }
                if ((c0 instanceof AbstractC2837hF0) && (b0 = ((AbstractC2837hF0) c0).b0(charSequence)) != null) {
                    return b0;
                }
            }
            return null;
        }
    }

    public Preference c0(int i) {
        return (Preference) this.v0.get(i);
    }

    public int d0() {
        return this.v0.size();
    }

    public void e0() {
        synchronized (this) {
            List list = this.v0;
            for (int size = list.size() - 1; size >= 0; size--) {
                g0((Preference) list.get(0));
            }
        }
        u();
    }

    public boolean f0(Preference preference) {
        boolean g0 = g0(preference);
        u();
        return g0;
    }

    public final boolean g0(Preference preference) {
        boolean remove;
        synchronized (this) {
            preference.Z();
            if (preference.o0 == this) {
                preference.o0 = null;
            }
            remove = this.v0.remove(preference);
            if (remove) {
                String str = preference.Q;
                if (str != null) {
                    this.t0.put(str, Long.valueOf(preference.k()));
                    this.u0.removeCallbacks(this.B0);
                    this.u0.post(this.B0);
                }
                if (this.y0) {
                    preference.A();
                }
            }
        }
        return remove;
    }

    @Override // androidx.preference.Preference
    public void h(Bundle bundle) {
        super.h(bundle);
        int d0 = d0();
        for (int i = 0; i < d0; i++) {
            c0(i).h(bundle);
        }
    }

    public void h0(int i) {
        if (i != Integer.MAX_VALUE && !q()) {
            Log.e("PreferenceGroup", getClass().getSimpleName() + " should have a key defined if it contains an expandable preference");
        }
        this.z0 = i;
    }

    @Override // androidx.preference.Preference
    public void i(Bundle bundle) {
        super.i(bundle);
        int d0 = d0();
        for (int i = 0; i < d0; i++) {
            c0(i).i(bundle);
        }
    }

    @Override // androidx.preference.Preference
    public void t(boolean z) {
        super.t(z);
        int d0 = d0();
        for (int i = 0; i < d0; i++) {
            c0(i).E(z);
        }
    }

    @Override // androidx.preference.Preference
    public void v() {
        super.v();
        this.y0 = true;
        int d0 = d0();
        for (int i = 0; i < d0; i++) {
            c0(i).v();
        }
    }
}
