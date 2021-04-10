package defpackage;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: wn1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5482wn1 {

    /* renamed from: a  reason: collision with root package name */
    public String f11569a;
    public String b;
    public Map c = new HashMap();
    public Map d = new HashMap();
    public final boolean[] e;

    public C5482wn1(String str, String str2, ArrayList arrayList, boolean z, boolean z2, boolean z3, boolean z4, boolean[] zArr) {
        boolean[] zArr2 = new boolean[3];
        this.e = zArr2;
        zArr2[0] = z;
        zArr2[1] = z2;
        zArr2[2] = z3;
        this.f11569a = str;
        this.b = str2;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C5312vn1 vn1 = (C5312vn1) it.next();
            this.c.put(vn1.f11498a, vn1.b);
            this.d.put(vn1.f11498a, vn1.c);
        }
    }

    public String a(String str) {
        return b(str) ? (String) this.c.get(str) : "";
    }

    public final boolean b(String str) {
        return !TextUtils.isEmpty(str) && this.c.containsKey(str);
    }

    public String toString() {
        return this.f11569a + " -> " + this.b + " - " + "Never Language:" + this.e[0] + " Always Language:" + this.e[2] + " Never Domain:" + this.e[1];
    }
}
