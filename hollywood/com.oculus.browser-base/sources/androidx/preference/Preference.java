package androidx.preference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Preference implements Comparable {
    public Context F;
    public C4375qF0 G;
    public long H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public XE0 f9480J;
    public YE0 K;
    public int L;
    public CharSequence M;
    public CharSequence N;
    public int O;
    public Drawable P;
    public String Q;
    public Intent R;
    public String S;
    public Bundle T;
    public boolean U;
    public boolean V;
    public boolean W;
    public boolean X;
    public String Y;
    public Object Z;
    public boolean a0;
    public boolean b0;
    public boolean c0;
    public boolean d0;
    public boolean e0;
    public boolean f0;
    public boolean g0;
    public boolean h0;
    public boolean i0;
    public boolean j0;
    public int k0;
    public int l0;
    public C3520lF0 m0;
    public List n0;
    public AbstractC2837hF0 o0;
    public boolean p0;
    public ZE0 q0;
    public C4527r90 r0;
    public final View.OnClickListener s0;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class BaseSavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new WE0();

        public BaseSavedState(Parcel parcel) {
            super(parcel);
        }

        public BaseSavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public Preference(Context context, AttributeSet attributeSet, int i, int i2) {
        this.L = Integer.MAX_VALUE;
        this.U = true;
        this.V = true;
        this.X = true;
        this.a0 = true;
        this.b0 = true;
        this.c0 = true;
        this.d0 = true;
        this.e0 = true;
        this.g0 = true;
        this.j0 = true;
        this.k0 = R.layout.f40680_resource_name_obfuscated_RES_2131624377;
        this.s0 = new VE0(this);
        this.F = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.o0, i, i2);
        this.O = Ko1.d(obtainStyledAttributes, 23, 0, 0);
        String string = obtainStyledAttributes.getString(26);
        this.Q = string == null ? obtainStyledAttributes.getString(6) : string;
        CharSequence text = obtainStyledAttributes.getText(34);
        this.M = text == null ? obtainStyledAttributes.getText(4) : text;
        CharSequence text2 = obtainStyledAttributes.getText(33);
        this.N = text2 == null ? obtainStyledAttributes.getText(7) : text2;
        this.L = obtainStyledAttributes.getInt(28, obtainStyledAttributes.getInt(8, Integer.MAX_VALUE));
        String string2 = obtainStyledAttributes.getString(22);
        this.S = string2 == null ? obtainStyledAttributes.getString(13) : string2;
        this.k0 = obtainStyledAttributes.getResourceId(27, obtainStyledAttributes.getResourceId(3, R.layout.f40680_resource_name_obfuscated_RES_2131624377));
        this.l0 = obtainStyledAttributes.getResourceId(35, obtainStyledAttributes.getResourceId(9, 0));
        this.U = obtainStyledAttributes.getBoolean(21, obtainStyledAttributes.getBoolean(2, true));
        this.V = obtainStyledAttributes.getBoolean(30, obtainStyledAttributes.getBoolean(5, true));
        this.X = obtainStyledAttributes.getBoolean(29, obtainStyledAttributes.getBoolean(1, true));
        String string3 = obtainStyledAttributes.getString(19);
        this.Y = string3 == null ? obtainStyledAttributes.getString(10) : string3;
        this.d0 = obtainStyledAttributes.getBoolean(16, obtainStyledAttributes.getBoolean(16, this.V));
        this.e0 = obtainStyledAttributes.getBoolean(17, obtainStyledAttributes.getBoolean(17, this.V));
        if (obtainStyledAttributes.hasValue(18)) {
            this.Z = B(obtainStyledAttributes, 18);
        } else if (obtainStyledAttributes.hasValue(11)) {
            this.Z = B(obtainStyledAttributes, 11);
        }
        this.j0 = obtainStyledAttributes.getBoolean(31, obtainStyledAttributes.getBoolean(12, true));
        boolean hasValue = obtainStyledAttributes.hasValue(32);
        this.f0 = hasValue;
        if (hasValue) {
            this.g0 = obtainStyledAttributes.getBoolean(32, obtainStyledAttributes.getBoolean(14, true));
        }
        this.h0 = obtainStyledAttributes.getBoolean(24, obtainStyledAttributes.getBoolean(15, false));
        this.c0 = obtainStyledAttributes.getBoolean(25, obtainStyledAttributes.getBoolean(25, true));
        this.i0 = obtainStyledAttributes.getBoolean(20, obtainStyledAttributes.getBoolean(20, false));
        obtainStyledAttributes.recycle();
    }

    public void A() {
        Z();
    }

    public Object B(TypedArray typedArray, int i) {
        return null;
    }

    @Deprecated
    public void C(D d) {
    }

    public void E(boolean z) {
        if (this.b0 == z) {
            this.b0 = !z;
            t(X());
            s();
        }
    }

    public void F(Parcelable parcelable) {
        this.p0 = true;
        if (parcelable != AbsSavedState.EMPTY_STATE && parcelable != null) {
            throw new IllegalArgumentException("Wrong state class -- expecting Preference State");
        }
    }

    public Parcelable G() {
        this.p0 = true;
        return AbsSavedState.EMPTY_STATE;
    }

    public void H(Object obj) {
    }

    @Deprecated
    public void I(Object obj) {
        H(obj);
    }

    public void J(View view) {
        Intent intent;
        AbstractC4204pF0 pf0;
        if (r() && this.V) {
            y();
            YE0 ye0 = this.K;
            if (ye0 == null || !ye0.d(this)) {
                C4375qF0 qf0 = this.G;
                if ((qf0 == null || (pf0 = qf0.h) == null || !pf0.q(this)) && (intent = this.R) != null) {
                    this.F.startActivity(intent);
                }
            }
        }
    }

    public void K(boolean z) {
        if (this.U != z) {
            this.U = z;
            t(X());
            s();
        }
    }

    public final void L(View view, boolean z) {
        view.setEnabled(z);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                L(viewGroup.getChildAt(childCount), z);
            }
        }
    }

    public void M(int i) {
        N(AbstractC5544x8.a(this.F, i));
        this.O = i;
    }

    public void N(Drawable drawable) {
        if (this.P != drawable) {
            this.P = drawable;
            this.O = 0;
            s();
        }
    }

    public void O(String str) {
        this.Q = str;
        if (this.W && !q()) {
            if (!TextUtils.isEmpty(this.Q)) {
                this.W = true;
                return;
            }
            throw new IllegalStateException("Preference does not have a key assigned.");
        }
    }

    public void P(int i) {
        if (i != this.L) {
            this.L = i;
            u();
        }
    }

    public void Q(boolean z) {
        if (this.V != z) {
            this.V = z;
            s();
        }
    }

    public void R(boolean z) {
        this.f0 = true;
        this.g0 = z;
    }

    public void S(int i) {
        T(this.F.getString(i));
    }

    public void T(CharSequence charSequence) {
        if (this.r0 != null) {
            throw new IllegalStateException("Preference already has a SummaryProvider set.");
        } else if (!TextUtils.equals(this.N, charSequence)) {
            this.N = charSequence;
            s();
        }
    }

    public void U(int i) {
        V(this.F.getString(i));
    }

    public void V(CharSequence charSequence) {
        if ((charSequence == null && this.M != null) || (charSequence != null && !charSequence.equals(this.M))) {
            this.M = charSequence;
            s();
        }
    }

    public final void W(boolean z) {
        if (this.c0 != z) {
            this.c0 = z;
            C3520lF0 lf0 = this.m0;
            if (lf0 != null) {
                lf0.w();
            }
        }
    }

    public boolean X() {
        return !r();
    }

    public boolean Y() {
        return this.G != null && this.X && q();
    }

    public final void Z() {
        List list;
        PreferenceScreen preferenceScreen;
        String str = this.Y;
        if (str != null) {
            C4375qF0 qf0 = this.G;
            Preference preference = null;
            if (!(qf0 == null || (preferenceScreen = qf0.g) == null)) {
                preference = preferenceScreen.b0(str);
            }
            if (preference != null && (list = preference.n0) != null) {
                list.remove(this);
            }
        }
    }

    public boolean f(Object obj) {
        XE0 xe0 = this.f9480J;
        return xe0 == null || xe0.a(this, obj);
    }

    /* renamed from: g */
    public int compareTo(Preference preference) {
        int i = this.L;
        int i2 = preference.L;
        if (i != i2) {
            return i - i2;
        }
        CharSequence charSequence = this.M;
        CharSequence charSequence2 = preference.M;
        if (charSequence == charSequence2) {
            return 0;
        }
        if (charSequence == null) {
            return 1;
        }
        if (charSequence2 == null) {
            return -1;
        }
        return charSequence.toString().compareToIgnoreCase(preference.M.toString());
    }

    public void h(Bundle bundle) {
        Parcelable parcelable;
        if (q() && (parcelable = bundle.getParcelable(this.Q)) != null) {
            this.p0 = false;
            F(parcelable);
            if (!this.p0) {
                throw new IllegalStateException("Derived class did not call super.onRestoreInstanceState()");
            }
        }
    }

    public void i(Bundle bundle) {
        if (q()) {
            this.p0 = false;
            Parcelable G2 = G();
            if (!this.p0) {
                throw new IllegalStateException("Derived class did not call super.onSaveInstanceState()");
            } else if (G2 != null) {
                bundle.putParcelable(this.Q, G2);
            }
        }
    }

    public Bundle j() {
        if (this.T == null) {
            this.T = new Bundle();
        }
        return this.T;
    }

    public long k() {
        return this.H;
    }

    public boolean l(boolean z) {
        if (!Y()) {
            return z;
        }
        o();
        return this.G.c().getBoolean(this.Q, z);
    }

    public String n(String str) {
        if (!Y()) {
            return str;
        }
        o();
        return this.G.c().getString(this.Q, str);
    }

    public void o() {
        C4375qF0 qf0 = this.G;
    }

    public CharSequence p() {
        C4527r90 r90 = this.r0;
        if (r90 != null) {
            return r90.a(this);
        }
        return this.N;
    }

    public boolean q() {
        return !TextUtils.isEmpty(this.Q);
    }

    public boolean r() {
        return this.U && this.a0 && this.b0;
    }

    public void s() {
        int indexOf;
        C3520lF0 lf0 = this.m0;
        if (lf0 != null && (indexOf = lf0.K.indexOf(this)) != -1) {
            lf0.F.d(indexOf, 1, this);
        }
    }

    public void t(boolean z) {
        List list = this.n0;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ((Preference) list.get(i)).z(z);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        CharSequence charSequence = this.M;
        if (!TextUtils.isEmpty(charSequence)) {
            sb.append(charSequence);
            sb.append(' ');
        }
        CharSequence p = p();
        if (!TextUtils.isEmpty(p)) {
            sb.append(p);
            sb.append(' ');
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public void u() {
        C3520lF0 lf0 = this.m0;
        if (lf0 != null) {
            lf0.w();
        }
    }

    public void v() {
        PreferenceScreen preferenceScreen;
        if (!TextUtils.isEmpty(this.Y)) {
            String str = this.Y;
            C4375qF0 qf0 = this.G;
            Preference preference = null;
            if (!(qf0 == null || (preferenceScreen = qf0.g) == null)) {
                preference = preferenceScreen.b0(str);
            }
            if (preference != null) {
                if (preference.n0 == null) {
                    preference.n0 = new ArrayList();
                }
                preference.n0.add(this);
                z(preference.X());
                return;
            }
            StringBuilder i = AbstractC2531fV.i("Dependency \"");
            i.append(this.Y);
            i.append("\" not found for preference \"");
            i.append(this.Q);
            i.append("\" (title: \"");
            i.append((Object) this.M);
            i.append("\"");
            throw new IllegalStateException(i.toString());
        }
    }

    public void w(C4375qF0 qf0) {
        SharedPreferences sharedPreferences;
        long j;
        this.G = qf0;
        if (!this.I) {
            synchronized (qf0) {
                j = qf0.b;
                qf0.b = 1 + j;
            }
            this.H = j;
        }
        o();
        if (Y()) {
            if (this.G != null) {
                o();
                sharedPreferences = this.G.c();
            } else {
                sharedPreferences = null;
            }
            if (sharedPreferences.contains(this.Q)) {
                I(null);
                return;
            }
        }
        Object obj = this.Z;
        if (obj != null) {
            I(obj);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void x(defpackage.C4886tF0 r9) {
        /*
        // Method dump skipped, instructions count: 271
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.Preference.x(tF0):void");
    }

    public void y() {
    }

    public void z(boolean z) {
        if (this.a0 == z) {
            this.a0 = !z;
            t(X());
            s();
        }
    }

    public Preference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, Ko1.a(context, R.attr.f7070_resource_name_obfuscated_RES_2130969153, 16842894), 0);
    }
}
