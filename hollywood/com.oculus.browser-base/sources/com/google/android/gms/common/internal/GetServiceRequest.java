package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C5907zF1();
    public final int F;
    public final int G;
    public int H;
    public String I;

    /* renamed from: J  reason: collision with root package name */
    public IBinder f9658J;
    public Scope[] K;
    public Bundle L;
    public Account M;
    public Feature[] N;
    public Feature[] O;
    public boolean P;
    public int Q;

    public GetServiceRequest(int i) {
        this.F = 4;
        this.H = UV.f9031a;
        this.G = i;
        this.P = true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        int i3 = this.G;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(i3);
        int i4 = this.H;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(i4);
        AbstractC5758yO0.g(parcel, 4, this.I, false);
        AbstractC5758yO0.d(parcel, 5, this.f9658J, false);
        AbstractC5758yO0.j(parcel, 6, this.K, i, false);
        AbstractC5758yO0.a(parcel, 7, this.L, false);
        AbstractC5758yO0.f(parcel, 8, this.M, i, false);
        AbstractC5758yO0.j(parcel, 10, this.N, i, false);
        AbstractC5758yO0.j(parcel, 11, this.O, i, false);
        boolean z = this.P;
        AbstractC5758yO0.o(parcel, 12, 4);
        parcel.writeInt(z ? 1 : 0);
        int i5 = this.Q;
        AbstractC5758yO0.o(parcel, 13, 4);
        parcel.writeInt(i5);
        AbstractC5758yO0.n(parcel, l);
    }

    public GetServiceRequest(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, Feature[] featureArr, Feature[] featureArr2, boolean z, int i4) {
        AbstractC4757sY d;
        this.F = i;
        this.G = i2;
        this.H = i3;
        if ("com.google.android.gms".equals(str)) {
            this.I = "com.google.android.gms";
        } else {
            this.I = str;
        }
        if (i < 2) {
            Account account2 = null;
            if (!(iBinder == null || (d = D0.d(iBinder)) == null)) {
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    C4587rY rYVar = (C4587rY) d;
                    Parcel d2 = rYVar.d(2, rYVar.c());
                    Account account3 = (Account) AbstractC4546rF1.a(d2, Account.CREATOR);
                    d2.recycle();
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    account2 = account3;
                } catch (RemoteException unused) {
                    Log.w("AccountAccessor", "Remote account accessor probably died");
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                } catch (Throwable th) {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    throw th;
                }
            }
            this.M = account2;
        } else {
            this.f9658J = iBinder;
            this.M = account;
        }
        this.K = scopeArr;
        this.L = bundle;
        this.N = featureArr;
        this.O = featureArr2;
        this.P = z;
        this.Q = i4;
    }
}
