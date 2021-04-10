package defpackage;

import android.text.TextUtils;
import java.util.ArrayList;

/* renamed from: iM0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3027iM0 {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f10133a;
    public final int b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final boolean g;
    public final int h;
    public final int i;
    public final String j;
    public final String k;
    public final String l;
    public final String m;
    public final int n;
    public final long o;
    public final String p;
    public final String q;
    public final int r;

    public C3027iM0(boolean z, int i2, String str, String str2, String str3, String str4, boolean z2, int i3, int i4, String str5, String str6, String str7, String str8, int i5, long j2, String str9, String str10, int i6, AbstractC2856hM0 hm0) {
        this.f10133a = z;
        this.b = i2;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = z2;
        this.h = i3;
        this.i = i4;
        this.j = str5;
        this.k = str6;
        this.l = str7;
        this.m = str8;
        this.n = i5;
        this.o = j2;
        this.p = str9;
        this.q = str10;
        this.r = i6;
    }

    public String toString() {
        ArrayList arrayList = new ArrayList();
        if (this.f10133a) {
            arrayList.add("Network unavailable!");
        } else if (this.b != 200) {
            StringBuilder i2 = AbstractC2531fV.i("ResponseCode:");
            i2.append(this.b);
            arrayList.add(i2.toString());
        } else {
            if (this.g) {
                arrayList.add("Preventing preload!");
            }
            if (!TextUtils.isEmpty(this.c)) {
                StringBuilder i3 = AbstractC2531fV.i("Search for '");
                i3.append(this.c);
                i3.append("'");
                arrayList.add(i3.toString());
            }
            if (!TextUtils.isEmpty(this.d)) {
                StringBuilder i4 = AbstractC2531fV.i("displayed as '");
                i4.append(this.d);
                i4.append("'");
                arrayList.add(i4.toString());
            }
            if (!TextUtils.isEmpty(this.f)) {
                StringBuilder i5 = AbstractC2531fV.i("MID:'");
                i5.append(this.f);
                i5.append("'");
                arrayList.add(i5.toString());
            }
            if (!(this.h == 0 && this.i == 0)) {
                StringBuilder i6 = AbstractC2531fV.i("selection adjust:");
                i6.append(this.h);
                i6.append(",");
                i6.append(this.i);
                arrayList.add(i6.toString());
            }
            if (!TextUtils.isEmpty(this.j) && this.j.equals("en")) {
                StringBuilder i7 = AbstractC2531fV.i("mContextLanguage:'");
                i7.append(this.j);
                i7.append("'");
                arrayList.add(i7.toString());
            }
            if (!TextUtils.isEmpty(this.k)) {
                arrayList.add("has thumbnail URL");
            }
            if (!TextUtils.isEmpty(this.l)) {
                StringBuilder i8 = AbstractC2531fV.i("caption:'");
                i8.append(this.l);
                i8.append("'");
                arrayList.add(i8.toString());
            }
            if (!TextUtils.isEmpty(this.m)) {
                arrayList.add("has Quick Action URI");
            }
            if (!TextUtils.isEmpty(this.m)) {
                StringBuilder i9 = AbstractC2531fV.i("quick Action Category:");
                i9.append(this.n);
                arrayList.add(i9.toString());
            }
            if (this.o != 0) {
                arrayList.add("has loggedEventId");
            }
            if (!TextUtils.isEmpty(this.p)) {
                StringBuilder i10 = AbstractC2531fV.i("search Url full:'");
                i10.append(this.p);
                i10.append("'");
                arrayList.add(i10.toString());
            }
            if (!TextUtils.isEmpty(this.q)) {
                StringBuilder i11 = AbstractC2531fV.i("search Url preload:'");
                i11.append(this.q);
                i11.append("'");
                arrayList.add(i11.toString());
            }
            if (this.r != 0) {
                StringBuilder i12 = AbstractC2531fV.i("Card-Tag:");
                i12.append(this.r);
                arrayList.add(i12.toString());
            }
        }
        return TextUtils.join(", ", arrayList);
    }
}
