package defpackage;

import android.util.SparseArray;

/* renamed from: Va  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1280Va {

    /* renamed from: a  reason: collision with root package name */
    public final SparseArray f9093a = new SparseArray();
    public boolean b;

    public void a(int i, AbstractC1097Sa sa) {
        this.f9093a.put(i, sa);
        if (!this.b) {
            K00 a2 = K00.a();
            a2.b.add(new C1219Ua(this, null));
            this.b = true;
        }
    }

    public boolean b(int i) {
        return this.f9093a.get(i) != null;
    }

    public boolean c() {
        for (int i = 0; i < this.f9093a.size(); i++) {
            SparseArray sparseArray = this.f9093a;
            if (((AbstractC1097Sa) sparseArray.get(sparseArray.keyAt(i))).m() != null) {
                return true;
            }
        }
        return false;
    }

    public AbstractC1097Sa d(int i) {
        AbstractC1097Sa sa = (AbstractC1097Sa) this.f9093a.get(i);
        this.f9093a.remove(i);
        return sa;
    }
}
