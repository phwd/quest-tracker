package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NotificationOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C4385qI1();
    public static final List F = Arrays.asList("com.google.android.gms.cast.framework.action.TOGGLE_PLAYBACK", "com.google.android.gms.cast.framework.action.STOP_CASTING");
    public static final int[] G = {0, 1};
    public final List H;
    public final int[] I;

    /* renamed from: J  reason: collision with root package name */
    public final long f9653J;
    public final String K;
    public final int L;
    public final int M;
    public final int N;
    public final int O;
    public final int P;
    public final int Q;
    public final int R;
    public final int S;
    public final int T;
    public final int U;
    public final int V;
    public final int W;
    public final int X;
    public final int Y;
    public final int Z;
    public final int a0;
    public final int b0;
    public final int c0;
    public final int d0;
    public final int e0;
    public final int f0;
    public final int g0;
    public final int h0;
    public final int i0;
    public final int j0;
    public final int k0;
    public final int l0;
    public final YF1 m0;

    public NotificationOptions(List list, int[] iArr, long j, String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25, int i26, int i27, IBinder iBinder) {
        YF1 yf1 = null;
        if (list != null) {
            this.H = new ArrayList(list);
        } else {
            this.H = null;
        }
        if (iArr != null) {
            this.I = Arrays.copyOf(iArr, iArr.length);
        } else {
            this.I = null;
        }
        this.f9653J = j;
        this.K = str;
        this.L = i;
        this.M = i2;
        this.N = i3;
        this.O = i4;
        this.P = i5;
        this.Q = i6;
        this.R = i7;
        this.S = i8;
        this.T = i9;
        this.U = i10;
        this.V = i11;
        this.W = i12;
        this.X = i13;
        this.Y = i14;
        this.Z = i15;
        this.a0 = i16;
        this.b0 = i17;
        this.c0 = i18;
        this.d0 = i19;
        this.e0 = i20;
        this.f0 = i21;
        this.g0 = i22;
        this.h0 = i23;
        this.i0 = i24;
        this.j0 = i25;
        this.k0 = i26;
        this.l0 = i27;
        if (iBinder != null) {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.media.INotificationActionsProvider");
            if (queryLocalInterface instanceof YF1) {
                yf1 = (YF1) queryLocalInterface;
            } else {
                yf1 = new YF1(iBinder);
            }
        }
        this.m0 = yf1;
    }

    public void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder;
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.i(parcel, 2, this.H, false);
        int[] iArr = this.I;
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        if (copyOf != null) {
            int l2 = AbstractC5758yO0.l(parcel, 3);
            parcel.writeIntArray(copyOf);
            AbstractC5758yO0.n(parcel, l2);
        }
        long j = this.f9653J;
        AbstractC5758yO0.o(parcel, 4, 8);
        parcel.writeLong(j);
        AbstractC5758yO0.g(parcel, 5, this.K, false);
        int i2 = this.L;
        AbstractC5758yO0.o(parcel, 6, 4);
        parcel.writeInt(i2);
        int i3 = this.M;
        AbstractC5758yO0.o(parcel, 7, 4);
        parcel.writeInt(i3);
        int i4 = this.N;
        AbstractC5758yO0.o(parcel, 8, 4);
        parcel.writeInt(i4);
        int i5 = this.O;
        AbstractC5758yO0.o(parcel, 9, 4);
        parcel.writeInt(i5);
        int i6 = this.P;
        AbstractC5758yO0.o(parcel, 10, 4);
        parcel.writeInt(i6);
        int i7 = this.Q;
        AbstractC5758yO0.o(parcel, 11, 4);
        parcel.writeInt(i7);
        int i8 = this.R;
        AbstractC5758yO0.o(parcel, 12, 4);
        parcel.writeInt(i8);
        int i9 = this.S;
        AbstractC5758yO0.o(parcel, 13, 4);
        parcel.writeInt(i9);
        int i10 = this.T;
        AbstractC5758yO0.o(parcel, 14, 4);
        parcel.writeInt(i10);
        int i11 = this.U;
        AbstractC5758yO0.o(parcel, 15, 4);
        parcel.writeInt(i11);
        int i12 = this.V;
        AbstractC5758yO0.o(parcel, 16, 4);
        parcel.writeInt(i12);
        int i13 = this.W;
        AbstractC5758yO0.o(parcel, 17, 4);
        parcel.writeInt(i13);
        int i14 = this.X;
        AbstractC5758yO0.o(parcel, 18, 4);
        parcel.writeInt(i14);
        int i15 = this.Y;
        AbstractC5758yO0.o(parcel, 19, 4);
        parcel.writeInt(i15);
        int i16 = this.Z;
        AbstractC5758yO0.o(parcel, 20, 4);
        parcel.writeInt(i16);
        int i17 = this.a0;
        AbstractC5758yO0.o(parcel, 21, 4);
        parcel.writeInt(i17);
        int i18 = this.b0;
        AbstractC5758yO0.o(parcel, 22, 4);
        parcel.writeInt(i18);
        int i19 = this.c0;
        AbstractC5758yO0.o(parcel, 23, 4);
        parcel.writeInt(i19);
        int i20 = this.d0;
        AbstractC5758yO0.o(parcel, 24, 4);
        parcel.writeInt(i20);
        int i21 = this.e0;
        AbstractC5758yO0.o(parcel, 25, 4);
        parcel.writeInt(i21);
        int i22 = this.f0;
        AbstractC5758yO0.o(parcel, 26, 4);
        parcel.writeInt(i22);
        int i23 = this.g0;
        AbstractC5758yO0.o(parcel, 27, 4);
        parcel.writeInt(i23);
        int i24 = this.h0;
        AbstractC5758yO0.o(parcel, 28, 4);
        parcel.writeInt(i24);
        int i25 = this.i0;
        AbstractC5758yO0.o(parcel, 29, 4);
        parcel.writeInt(i25);
        int i26 = this.j0;
        AbstractC5758yO0.o(parcel, 30, 4);
        parcel.writeInt(i26);
        int i27 = this.k0;
        AbstractC5758yO0.o(parcel, 31, 4);
        parcel.writeInt(i27);
        int i28 = this.l0;
        AbstractC5758yO0.o(parcel, 32, 4);
        parcel.writeInt(i28);
        YF1 yf1 = this.m0;
        if (yf1 == null) {
            iBinder = null;
        } else {
            iBinder = yf1.f11531a;
        }
        AbstractC5758yO0.d(parcel, 33, iBinder, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
