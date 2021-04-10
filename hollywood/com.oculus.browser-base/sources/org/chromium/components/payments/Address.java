package org.chromium.components.payments;

import android.os.Bundle;
import java.util.regex.Pattern;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Address {

    /* renamed from: a  reason: collision with root package name */
    public static Pattern f10871a;
    public final String b;
    public final String[] c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;

    public Address() {
        this.b = "";
        this.c = new String[0];
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.k = "";
    }

    public static Address a(Bundle bundle) {
        return new Address(bundle.getString("countryCode", ""), bundle.getStringArray("addressLines"), bundle.getString("region", ""), bundle.getString("city", ""), bundle.getString("dependentLocality", ""), bundle.getString("postalCode", ""), bundle.getString("sortingCode", ""), bundle.getString("organization", ""), bundle.getString("recipient", ""), bundle.getString("phone", ""));
    }

    public Address(String str, String[] strArr, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.b = str;
        this.c = strArr;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = str6;
        this.i = str7;
        this.j = str8;
        this.k = str9;
    }
}
