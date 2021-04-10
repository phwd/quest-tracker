package com.google.android.material.appbar;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AppBarLayout$BaseBehavior$SavedState extends AbsSavedState {
    public static final Parcelable.Creator CREATOR = new E7();
    public int H;
    public float I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9687J;

    public AppBarLayout$BaseBehavior$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.H = parcel.readInt();
        this.I = parcel.readFloat();
        this.f9687J = parcel.readByte() != 0;
    }

    @Override // androidx.customview.view.AbsSavedState
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.G, i);
        parcel.writeInt(this.H);
        parcel.writeFloat(this.I);
        parcel.writeByte(this.f9687J ? (byte) 1 : 0);
    }

    public AppBarLayout$BaseBehavior$SavedState(Parcelable parcelable) {
        super(parcelable);
    }
}
