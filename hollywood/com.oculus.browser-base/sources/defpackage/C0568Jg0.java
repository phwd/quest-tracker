package defpackage;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: Jg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0568Jg0 {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList f8306a;

    public C0568Jg0() {
    }

    public C0568Jg0 a(Collection collection) {
        if (collection != null) {
            if (!collection.isEmpty()) {
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    b((String) it.next());
                }
            }
            return this;
        }
        throw new IllegalArgumentException("categories must not be null");
    }

    public C0568Jg0 b(String str) {
        if (str != null) {
            if (this.f8306a == null) {
                this.f8306a = new ArrayList();
            }
            if (!this.f8306a.contains(str)) {
                this.f8306a.add(str);
            }
            return this;
        }
        throw new IllegalArgumentException("category must not be null");
    }

    public C0629Kg0 c() {
        if (this.f8306a == null) {
            return C0629Kg0.f8380a;
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("controlCategories", this.f8306a);
        return new C0629Kg0(bundle, this.f8306a);
    }

    public C0568Jg0(C0629Kg0 kg0) {
        if (kg0 != null) {
            kg0.a();
            if (!kg0.c.isEmpty()) {
                this.f8306a = new ArrayList(kg0.c);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("selector must not be null");
    }
}
