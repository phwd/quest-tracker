package androidx.preference;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ListPreference extends WE {
    public CharSequence[] A0;
    public String B0;
    public String C0;
    public boolean D0;
    public CharSequence[] z0;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ListPreference(android.content.Context r6, android.util.AttributeSet r7) {
        /*
            r5 = this;
            r0 = 2130968826(0x7f0400fa, float:1.7546317E38)
            r1 = 16842897(0x1010091, float:2.3693964E-38)
            int r0 = defpackage.Ko1.a(r6, r0, r1)
            r1 = 0
            r5.<init>(r6, r7, r0, r1)
            int[] r2 = defpackage.FJ0.b0
            android.content.res.TypedArray r2 = r6.obtainStyledAttributes(r7, r2, r0, r1)
            r3 = 2
            java.lang.CharSequence[] r3 = r2.getTextArray(r3)
            if (r3 != 0) goto L_0x001f
            java.lang.CharSequence[] r3 = r2.getTextArray(r1)
        L_0x001f:
            r5.z0 = r3
            r3 = 3
            r4 = 1
            java.lang.CharSequence[] r3 = r2.getTextArray(r3)
            if (r3 != 0) goto L_0x002d
            java.lang.CharSequence[] r3 = r2.getTextArray(r4)
        L_0x002d:
            r5.A0 = r3
            r3 = 4
            boolean r4 = r2.getBoolean(r3, r1)
            boolean r3 = r2.getBoolean(r3, r4)
            if (r3 == 0) goto L_0x004c
            r90 r3 = defpackage.C4527r90.f11185a
            if (r3 != 0) goto L_0x0045
            r90 r3 = new r90
            r3.<init>()
            defpackage.C4527r90.f11185a = r3
        L_0x0045:
            r90 r3 = defpackage.C4527r90.f11185a
            r5.r0 = r3
            r5.s()
        L_0x004c:
            r2.recycle()
            int[] r2 = defpackage.FJ0.o0
            android.content.res.TypedArray r6 = r6.obtainStyledAttributes(r7, r2, r0, r1)
            r7 = 33
            r0 = 7
            java.lang.String r7 = defpackage.Ko1.e(r6, r7, r0)
            r5.C0 = r7
            r6.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.ListPreference.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    @Override // androidx.preference.Preference
    public Object B(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    @Override // androidx.preference.Preference
    public void F(Parcelable parcelable) {
        if (!parcelable.getClass().equals(SavedState.class)) {
            super.F(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.F(savedState.getSuperState());
        c0(savedState.F);
    }

    @Override // androidx.preference.Preference
    public Parcelable G() {
        Parcelable G = super.G();
        if (this.X) {
            return G;
        }
        SavedState savedState = new SavedState(G);
        savedState.F = this.B0;
        return savedState;
    }

    @Override // androidx.preference.Preference
    public void H(Object obj) {
        c0(n((String) obj));
    }

    @Override // androidx.preference.Preference
    public void T(CharSequence charSequence) {
        super.T(charSequence);
        if (charSequence == null && this.C0 != null) {
            this.C0 = null;
        } else if (charSequence != null && !charSequence.equals(this.C0)) {
            this.C0 = charSequence.toString();
        }
    }

    public int a0(String str) {
        CharSequence[] charSequenceArr;
        if (str == null || (charSequenceArr = this.A0) == null) {
            return -1;
        }
        for (int length = charSequenceArr.length - 1; length >= 0; length--) {
            if (this.A0[length].equals(str)) {
                return length;
            }
        }
        return -1;
    }

    public CharSequence b0() {
        CharSequence[] charSequenceArr;
        int a0 = a0(this.B0);
        if (a0 < 0 || (charSequenceArr = this.z0) == null) {
            return null;
        }
        return charSequenceArr[a0];
    }

    public void c0(String str) {
        boolean z = !TextUtils.equals(this.B0, str);
        if (z || !this.D0) {
            this.B0 = str;
            this.D0 = true;
            if (Y() && !TextUtils.equals(str, n(null))) {
                o();
                SharedPreferences.Editor b = this.G.b();
                b.putString(this.Q, str);
                if (!this.G.e) {
                    b.apply();
                }
            }
            if (z) {
                s();
            }
        }
    }

    @Override // androidx.preference.Preference
    public CharSequence p() {
        C4527r90 r90 = this.r0;
        if (r90 != null) {
            return r90.a(this);
        }
        CharSequence b0 = b0();
        CharSequence p = super.p();
        String str = this.C0;
        if (str == null) {
            return p;
        }
        Object[] objArr = new Object[1];
        if (b0 == null) {
            b0 = "";
        }
        objArr[0] = b0;
        String format = String.format(str, objArr);
        if (TextUtils.equals(format, p)) {
            return p;
        }
        Log.w("ListPreference", "Setting a summary with a String formatting marker is no longer supported. You should use a SummaryProvider instead.");
        return format;
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new C4357q90();
        public String F;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.F = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.F);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
