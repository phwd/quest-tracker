package defpackage;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import org.chromium.base.Callback;

/* renamed from: r20  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4506r20 {

    /* renamed from: a  reason: collision with root package name */
    public int f11178a;
    public Callback b;
    public ArrayList c;
    public int d = 0;

    public C4506r20(int i, Callback callback) {
        this.f11178a = i;
        this.b = callback;
        ArrayList arrayList = new ArrayList(Collections.nCopies(this.f11178a, null));
        this.c = arrayList;
        if (this.f11178a == 0) {
            this.b.onResult(Pair.create(arrayList, 0));
        }
    }

    public void a(C5922zL0 zl0, int i, int i2) {
        if (zl0 != null) {
            this.c.add(i, zl0);
        }
        this.d += i2;
        int i3 = this.f11178a - 1;
        this.f11178a = i3;
        if (i3 == 0) {
            this.c.removeAll(Collections.singleton(null));
            this.b.onResult(Pair.create(this.c, Integer.valueOf(this.d)));
        }
    }
}
