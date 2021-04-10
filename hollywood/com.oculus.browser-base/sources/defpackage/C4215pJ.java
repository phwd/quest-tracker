package defpackage;

import android.util.SparseArray;
import java.util.Collections;
import java.util.List;

/* renamed from: pJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4215pJ {

    /* renamed from: a  reason: collision with root package name */
    public final C4935tb0 f11061a;
    public int b = 2;
    public int c = 0;
    public List d = Collections.emptyList();
    public SparseArray e = new SparseArray();

    public C4215pJ(C4935tb0 tb0) {
        this.f11061a = tb0;
    }

    public final boolean a(C2848hJ hJVar, int i) {
        return hJVar.f11283a == 7 && hJVar.d == i;
    }

    public void b() {
        for (int i = 0; i < this.f11061a.size(); i++) {
            C2848hJ hJVar = (C2848hJ) this.f11061a.get(i);
            hJVar.c.e(hJVar.b);
        }
    }
}
