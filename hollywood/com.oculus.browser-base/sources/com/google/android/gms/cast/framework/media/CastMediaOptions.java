package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CastMediaOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C3854nC1();
    public static final NF1 F = new NF1("CastMediaOptions");
    public final String G;
    public final String H;
    public final C5567xF1 I;

    /* renamed from: J  reason: collision with root package name */
    public final NotificationOptions f9651J;
    public final boolean K;

    public CastMediaOptions(String str, String str2, IBinder iBinder, NotificationOptions notificationOptions, boolean z) {
        C5567xF1 xf1;
        this.G = str;
        this.H = str2;
        if (iBinder == null) {
            xf1 = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.media.IImagePicker");
            if (queryLocalInterface instanceof C5567xF1) {
                xf1 = (C5567xF1) queryLocalInterface;
            } else {
                xf1 = new C5567xF1(iBinder);
            }
        }
        this.I = xf1;
        this.f9651J = notificationOptions;
        this.K = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder;
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.g(parcel, 2, this.G, false);
        AbstractC5758yO0.g(parcel, 3, this.H, false);
        C5567xF1 xf1 = this.I;
        if (xf1 == null) {
            iBinder = null;
        } else {
            iBinder = xf1.f11531a;
        }
        AbstractC5758yO0.d(parcel, 4, iBinder, false);
        AbstractC5758yO0.f(parcel, 5, this.f9651J, i, false);
        boolean z = this.K;
        AbstractC5758yO0.o(parcel, 6, 4);
        parcel.writeInt(z ? 1 : 0);
        AbstractC5758yO0.n(parcel, l);
    }
}
