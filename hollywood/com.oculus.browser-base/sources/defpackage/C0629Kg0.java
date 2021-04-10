package defpackage;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: Kg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0629Kg0 {

    /* renamed from: a  reason: collision with root package name */
    public static final C0629Kg0 f8380a = new C0629Kg0(new Bundle(), null);
    public final Bundle b;
    public List c;

    public C0629Kg0(Bundle bundle, List list) {
        this.b = bundle;
        this.c = list;
    }

    public static C0629Kg0 b(Bundle bundle) {
        if (bundle != null) {
            return new C0629Kg0(bundle, null);
        }
        return null;
    }

    public void a() {
        if (this.c == null) {
            ArrayList<String> stringArrayList = this.b.getStringArrayList("controlCategories");
            this.c = stringArrayList;
            if (stringArrayList == null || stringArrayList.isEmpty()) {
                this.c = Collections.emptyList();
            }
        }
    }

    public boolean c() {
        a();
        return this.c.isEmpty();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0629Kg0)) {
            return false;
        }
        C0629Kg0 kg0 = (C0629Kg0) obj;
        a();
        kg0.a();
        return this.c.equals(kg0.c);
    }

    public int hashCode() {
        a();
        return this.c.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MediaRouteSelector{ ");
        sb.append("controlCategories=");
        a();
        sb.append(Arrays.toString(this.c.toArray()));
        sb.append(" }");
        return sb.toString();
    }
}
