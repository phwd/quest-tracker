package defpackage;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: Ig0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0507Ig0 {

    /* renamed from: a  reason: collision with root package name */
    public final List f8240a;
    public final boolean b;

    public C0507Ig0(List list, boolean z) {
        this.f8240a = list == null ? Collections.emptyList() : list;
        this.b = z;
    }

    public static C0507Ig0 a(Bundle bundle) {
        ArrayList arrayList = null;
        if (bundle == null) {
            return null;
        }
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("routes");
        if (parcelableArrayList != null && !parcelableArrayList.isEmpty()) {
            int size = parcelableArrayList.size();
            ArrayList arrayList2 = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                arrayList2.add(C0869Of0.b((Bundle) parcelableArrayList.get(i)));
            }
            arrayList = arrayList2;
        }
        return new C0507Ig0(arrayList, bundle.getBoolean("supportsDynamicGroupRoute", false));
    }

    public boolean b() {
        int size = this.f8240a.size();
        for (int i = 0; i < size; i++) {
            C0869Of0 of0 = (C0869Of0) this.f8240a.get(i);
            if (of0 == null || !of0.r()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder j = AbstractC2531fV.j("MediaRouteProviderDescriptor{ ", "routes=");
        j.append(Arrays.toString(this.f8240a.toArray()));
        j.append(", isValid=");
        j.append(b());
        j.append(" }");
        return j.toString();
    }
}
