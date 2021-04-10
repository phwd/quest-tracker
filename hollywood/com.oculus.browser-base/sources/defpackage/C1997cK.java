package defpackage;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;

/* renamed from: cK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1997cK {

    /* renamed from: a  reason: collision with root package name */
    public boolean f9599a = true;
    public int b = Integer.MAX_VALUE;
    public boolean c;
    public String d;
    public String e;
    public String f;
    public String g;
    public Drawable h;
    public String[] i = {null, null, null};
    public boolean j = true;

    public C1997cK(String str, String str2, String str3, String str4, Drawable drawable) {
        e(str, str2, str3, str4);
        this.h = drawable;
    }

    public String a() {
        return this.i[0];
    }

    public String b(String str, int i2) {
        StringBuilder sb = new StringBuilder(this.i[0]);
        if (!TextUtils.isEmpty(this.i[1])) {
            if (sb.length() > 0) {
                sb.append(str);
            }
            sb.append(this.i[1]);
        }
        if (!TextUtils.isEmpty(this.i[2])) {
            if (sb.length() > 0) {
                sb.append(str);
            }
            sb.append(this.i[2]);
        }
        if (i2 < 0 || sb.length() < i2) {
            return sb.toString();
        }
        return sb.substring(0, i2 / 2);
    }

    public String c() {
        return this.i[1];
    }

    public boolean d() {
        return this.f9599a;
    }

    public void e(String str, String str2, String str3, String str4) {
        this.g = str;
        String[] strArr = this.i;
        strArr[0] = str2;
        strArr[1] = str3;
        strArr[2] = str4;
    }

    public void f(String str) {
        this.i[1] = str;
    }
}
