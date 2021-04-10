package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: T60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class T60 {

    /* renamed from: a  reason: collision with root package name */
    public static T60 f8939a;
    public final Map b = new LinkedHashMap();
    public S60 c;

    public T60() {
        ArrayList arrayList = new ArrayList();
        N.MlwSZWfW(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            B60 b60 = (B60) it.next();
            this.b.put(b60.f7716a, b60);
        }
    }

    public static T60 a() {
        if (f8939a == null) {
            f8939a = new T60();
        }
        return f8939a;
    }

    public static void d(int i) {
        AbstractC3364kK0.g("LanguageSettings.Actions", i, 11);
    }

    public static void e(int i) {
        AbstractC3364kK0.g("LanguageSettings.PageImpression", i, 6);
    }

    public void b(String str, int i, boolean z) {
        if (i != 0) {
            N.MLlzo6h2(str, i);
            d(8);
            if (z) {
                c();
            }
        }
    }

    public final void c() {
        S60 s60 = this.c;
        if (s60 != null) {
            ((L60) s60).u();
        }
    }
}
