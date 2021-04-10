package defpackage;

import J.N;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;

/* renamed from: yA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5720yA {

    /* renamed from: a  reason: collision with root package name */
    public final C5550xA f11668a = new C5550xA();

    public String a() {
        Objects.requireNonNull(this.f11668a);
        return N.MMKf4EpW();
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Objects.requireNonNull(this.f11668a);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        N.MfiRqZsy(linkedHashSet);
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            if (((String) it.next()).equals(str)) {
                return false;
            }
        }
        return true;
    }
}
