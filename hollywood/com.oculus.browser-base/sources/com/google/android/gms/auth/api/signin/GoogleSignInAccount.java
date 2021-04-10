package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new C1621aB1();
    public final int F;
    public String G;
    public String H;
    public String I;

    /* renamed from: J  reason: collision with root package name */
    public String f9636J;
    public Uri K;
    public String L;
    public long M;
    public String N;
    public List O;
    public String P;
    public String Q;
    public Set R = new HashSet();

    public GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List list, String str7, String str8) {
        this.F = i;
        this.G = str;
        this.H = str2;
        this.I = str3;
        this.f9636J = str4;
        this.K = uri;
        this.L = str5;
        this.M = j;
        this.N = str6;
        this.O = list;
        this.P = str7;
        this.Q = str8;
    }

    public static GoogleSignInAccount A(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("photoUrl", null);
        Uri parse = !TextUtils.isEmpty(optString) ? Uri.parse(optString) : null;
        long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString2 = jSONObject.optString("id");
        String optString3 = jSONObject.optString("tokenId", null);
        String optString4 = jSONObject.optString("email", null);
        String optString5 = jSONObject.optString("displayName", null);
        String optString6 = jSONObject.optString("givenName", null);
        String optString7 = jSONObject.optString("familyName", null);
        Long valueOf = Long.valueOf(parseLong);
        String string = jSONObject.getString("obfuscatedIdentifier");
        if (valueOf == null) {
            valueOf = Long.valueOf(System.currentTimeMillis() / 1000);
        }
        long longValue = valueOf.longValue();
        SE0.f(string);
        GoogleSignInAccount googleSignInAccount = new GoogleSignInAccount(3, optString2, optString3, optString4, optString5, parse, null, longValue, string, new ArrayList(hashSet), optString6, optString7);
        googleSignInAccount.L = jSONObject.optString("serverAuthCode", null);
        return googleSignInAccount;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        return googleSignInAccount.N.equals(this.N) && googleSignInAccount.x().equals(x());
    }

    public int hashCode() {
        return x().hashCode() + ((this.N.hashCode() + 527) * 31);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.g(parcel, 2, this.G, false);
        AbstractC5758yO0.g(parcel, 3, this.H, false);
        AbstractC5758yO0.g(parcel, 4, this.I, false);
        AbstractC5758yO0.g(parcel, 5, this.f9636J, false);
        AbstractC5758yO0.f(parcel, 6, this.K, i, false);
        AbstractC5758yO0.g(parcel, 7, this.L, false);
        long j = this.M;
        AbstractC5758yO0.o(parcel, 8, 8);
        parcel.writeLong(j);
        AbstractC5758yO0.g(parcel, 9, this.N, false);
        AbstractC5758yO0.k(parcel, 10, this.O, false);
        AbstractC5758yO0.g(parcel, 11, this.P, false);
        AbstractC5758yO0.g(parcel, 12, this.Q, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public Set x() {
        HashSet hashSet = new HashSet(this.O);
        hashSet.addAll(this.R);
        return hashSet;
    }
}
