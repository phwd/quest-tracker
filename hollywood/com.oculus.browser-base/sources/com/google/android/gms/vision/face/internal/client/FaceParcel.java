package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FaceParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C5057uF1();
    public final int F;
    public final int G;
    public final float H;
    public final float I;

    /* renamed from: J  reason: collision with root package name */
    public final float f9684J;
    public final float K;
    public final float L;
    public final float M;
    public final float N;
    public final LandmarkParcel[] O;
    public final float P;
    public final float Q;
    public final float R;
    public final zza[] S;

    public FaceParcel(int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, LandmarkParcel[] landmarkParcelArr, float f8, float f9, float f10, zza[] zzaArr) {
        this.F = i;
        this.G = i2;
        this.H = f;
        this.I = f2;
        this.f9684J = f3;
        this.K = f4;
        this.L = f5;
        this.M = f6;
        this.N = f7;
        this.O = landmarkParcelArr;
        this.P = f8;
        this.Q = f9;
        this.R = f10;
        this.S = zzaArr;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        int i3 = this.G;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(i3);
        float f = this.H;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeFloat(f);
        float f2 = this.I;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeFloat(f2);
        float f3 = this.f9684J;
        AbstractC5758yO0.o(parcel, 5, 4);
        parcel.writeFloat(f3);
        float f4 = this.K;
        AbstractC5758yO0.o(parcel, 6, 4);
        parcel.writeFloat(f4);
        float f5 = this.L;
        AbstractC5758yO0.o(parcel, 7, 4);
        parcel.writeFloat(f5);
        float f6 = this.M;
        AbstractC5758yO0.o(parcel, 8, 4);
        parcel.writeFloat(f6);
        AbstractC5758yO0.j(parcel, 9, this.O, i, false);
        float f7 = this.P;
        AbstractC5758yO0.o(parcel, 10, 4);
        parcel.writeFloat(f7);
        float f8 = this.Q;
        AbstractC5758yO0.o(parcel, 11, 4);
        parcel.writeFloat(f8);
        float f9 = this.R;
        AbstractC5758yO0.o(parcel, 12, 4);
        parcel.writeFloat(f9);
        AbstractC5758yO0.j(parcel, 13, this.S, i, false);
        float f10 = this.N;
        AbstractC5758yO0.o(parcel, 14, 4);
        parcel.writeFloat(f10);
        AbstractC5758yO0.n(parcel, l);
    }
}
