package defpackage;

import android.util.SparseArray;
import java.util.ArrayList;

/* renamed from: OK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OK0 {

    /* renamed from: a  reason: collision with root package name */
    public SparseArray f8620a = new SparseArray();
    public int b = 0;

    public void a() {
        for (int i = 0; i < this.f8620a.size(); i++) {
            ((NK0) this.f8620a.valueAt(i)).f8541a.clear();
        }
    }

    public XK0 b(int i) {
        NK0 nk0 = (NK0) this.f8620a.get(i);
        if (nk0 == null || nk0.f8541a.isEmpty()) {
            return null;
        }
        ArrayList arrayList = nk0.f8541a;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (!((XK0) arrayList.get(size)).j()) {
                return (XK0) arrayList.remove(size);
            }
        }
        return null;
    }

    public final NK0 c(int i) {
        NK0 nk0 = (NK0) this.f8620a.get(i);
        if (nk0 != null) {
            return nk0;
        }
        NK0 nk02 = new NK0();
        this.f8620a.put(i, nk02);
        return nk02;
    }

    public long d(long j, long j2) {
        if (j == 0) {
            return j2;
        }
        return (j2 / 4) + ((j / 4) * 3);
    }

    public void e(int i, int i2) {
        NK0 c = c(i);
        c.b = i2;
        ArrayList arrayList = c.f8541a;
        while (arrayList.size() > i2) {
            arrayList.remove(arrayList.size() - 1);
        }
    }
}
