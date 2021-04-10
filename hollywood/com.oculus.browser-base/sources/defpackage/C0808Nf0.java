package defpackage;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: Nf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0808Nf0 {

    /* renamed from: a  reason: collision with root package name */
    public final Bundle f8563a;
    public ArrayList b;
    public ArrayList c;

    public C0808Nf0(String str, String str2) {
        Bundle bundle = new Bundle();
        this.f8563a = bundle;
        bundle.putString("id", str);
        bundle.putString("name", str2);
    }

    public C0808Nf0 a(Collection collection) {
        if (collection != null) {
            if (!collection.isEmpty()) {
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    IntentFilter intentFilter = (IntentFilter) it.next();
                    if (intentFilter != null) {
                        if (this.c == null) {
                            this.c = new ArrayList();
                        }
                        if (!this.c.contains(intentFilter)) {
                            this.c.add(intentFilter);
                        }
                    } else {
                        throw new IllegalArgumentException("filter must not be null");
                    }
                }
            }
            return this;
        }
        throw new IllegalArgumentException("filters must not be null");
    }

    public C0869Of0 b() {
        ArrayList<? extends Parcelable> arrayList = this.c;
        if (arrayList != null) {
            this.f8563a.putParcelableArrayList("controlFilters", arrayList);
        }
        ArrayList<String> arrayList2 = this.b;
        if (arrayList2 != null) {
            this.f8563a.putStringArrayList("groupMemberIds", arrayList2);
        }
        return new C0869Of0(this.f8563a);
    }

    public C0808Nf0 c(int i) {
        this.f8563a.putInt("connectionState", i);
        return this;
    }

    public C0808Nf0 d(int i) {
        this.f8563a.putInt("playbackType", i);
        return this;
    }

    public C0808Nf0 e(int i) {
        this.f8563a.putInt("volume", i);
        return this;
    }

    public C0808Nf0 f(int i) {
        this.f8563a.putInt("volumeHandling", i);
        return this;
    }

    public C0808Nf0 g(int i) {
        this.f8563a.putInt("volumeMax", i);
        return this;
    }
}
