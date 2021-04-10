package defpackage;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* renamed from: Lx1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Lx1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f8450a;
    public final String b;
    public final Gx1 c;
    public final boolean d;

    public Lx1(String str, String str2, Gx1 gx1, boolean z) {
        this.f8450a = str;
        this.b = str2;
        this.c = gx1;
        this.d = z;
    }

    public static Parcelable[] a(List list) {
        Parcelable[] parcelableArr = new Parcelable[list.size()];
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            Lx1 lx1 = (Lx1) it.next();
            Objects.requireNonNull(lx1);
            Bundle bundle = new Bundle();
            bundle.putString("id", lx1.f8450a);
            bundle.putString("label", lx1.b);
            Gx1 gx1 = lx1.c;
            Objects.requireNonNull(gx1);
            Bundle bundle2 = new Bundle();
            bundle2.putString("currency", gx1.f8125a);
            bundle2.putString("value", gx1.b);
            bundle.putBundle("amount", bundle2);
            bundle.putBoolean("selected", lx1.d);
            parcelableArr[i] = bundle;
            i++;
        }
        return parcelableArr;
    }
}
