package com.google.android.gms.auth.api.signin;

import X.C0327Re;
import X.C1105sg;
import X.QN;
import X.S4;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static S4 A0D = C1105sg.A00;
    public static final Parcelable.Creator CREATOR = new QN();
    public Uri A00;
    public String A01;
    public String A02;
    public String A03;
    public String A04;
    public String A05;
    public String A06;
    public String A07;
    public List A08;
    public Set A09 = new HashSet();
    public long A0A;
    public String A0B;
    public final int A0C;

    public final boolean equals(Object obj) {
        if (obj != null) {
            if (obj != this) {
                if (obj instanceof GoogleSignInAccount) {
                    GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
                    if (googleSignInAccount.A0B.equals(this.A0B)) {
                        HashSet hashSet = new HashSet(googleSignInAccount.A08);
                        hashSet.addAll(googleSignInAccount.A09);
                        HashSet hashSet2 = new HashSet(this.A08);
                        hashSet2.addAll(this.A09);
                        if (hashSet.equals(hashSet2)) {
                            return true;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (this.A0B.hashCode() + 527) * 31;
        HashSet hashSet = new HashSet(this.A08);
        hashSet.addAll(this.A09);
        return hashCode + hashSet.hashCode();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A02(parcel, 1, this.A0C);
        C0327Re.A05(parcel, 2, this.A01);
        C0327Re.A05(parcel, 3, this.A02);
        C0327Re.A05(parcel, 4, this.A03);
        C0327Re.A05(parcel, 5, this.A04);
        C0327Re.A04(parcel, 6, this.A00, i);
        C0327Re.A05(parcel, 7, this.A05);
        long j = this.A0A;
        C0327Re.A03(parcel, 8, 8);
        parcel.writeLong(j);
        C0327Re.A05(parcel, 9, this.A0B);
        C0327Re.A06(parcel, 10, this.A08);
        C0327Re.A05(parcel, 11, this.A06);
        C0327Re.A05(parcel, 12, this.A07);
        C0327Re.A01(parcel, A002);
    }

    public GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List list, String str7, String str8) {
        this.A0C = i;
        this.A01 = str;
        this.A02 = str2;
        this.A03 = str3;
        this.A04 = str4;
        this.A00 = uri;
        this.A05 = str5;
        this.A0A = j;
        this.A0B = str6;
        this.A08 = list;
        this.A06 = str7;
        this.A07 = str8;
    }
}
