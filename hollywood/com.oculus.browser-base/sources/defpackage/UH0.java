package defpackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* renamed from: UH0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UH0 extends AbstractC1821bI0 {
    public final Map b;

    public UH0(KH0... kh0Arr) {
        this.b = c(kh0Arr);
    }

    public static Map c(KH0[] kh0Arr) {
        HashMap hashMap = new HashMap();
        for (KH0 kh0 : kh0Arr) {
            if (!hashMap.containsKey(kh0)) {
                hashMap.put(kh0, null);
            } else {
                throw new IllegalArgumentException("Duplicate key: " + kh0);
            }
        }
        return hashMap;
    }

    public static KH0[] d(KH0[] kh0Arr, KH0[] kh0Arr2) {
        KH0[] kh0Arr3 = new KH0[(kh0Arr.length + kh0Arr2.length)];
        System.arraycopy(kh0Arr, 0, kh0Arr3, 0, kh0Arr.length);
        System.arraycopy(kh0Arr2, 0, kh0Arr3, kh0Arr.length, kh0Arr2.length);
        return kh0Arr3;
    }

    @Override // defpackage.AbstractC1821bI0
    public Collection a() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : this.b.entrySet()) {
            if (entry.getValue() != null) {
                arrayList.add((KH0) entry.getKey());
            }
        }
        return arrayList;
    }

    public float e(RH0 rh0) {
        IH0 ih0 = (IH0) this.b.get(rh0);
        if (ih0 == null) {
            return 0.0f;
        }
        return ih0.f8214a;
    }

    public int f(NH0 nh0) {
        JH0 jh0 = (JH0) this.b.get(nh0);
        if (jh0 == null) {
            return 0;
        }
        return jh0.f8282a;
    }

    public Object g(OH0 oh0) {
        LH0 lh0 = (LH0) this.b.get(oh0);
        if (lh0 == null) {
            return null;
        }
        return lh0.f8415a;
    }

    public boolean h(MH0 mh0) {
        GH0 gh0 = (GH0) this.b.get(mh0);
        if (gh0 == null) {
            return false;
        }
        return gh0.f8081a;
    }

    public Collection i() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : this.b.entrySet()) {
            arrayList.add((KH0) entry.getKey());
        }
        return arrayList;
    }

    public void j(QH0 qh0, boolean z) {
        GH0 gh0 = (GH0) this.b.get(qh0);
        if (gh0 == null) {
            gh0 = new GH0(null);
            this.b.put(qh0, gh0);
        } else if (gh0.f8081a == z) {
            return;
        }
        gh0.f8081a = z;
        b(qh0);
    }

    public void k(RH0 rh0, float f) {
        IH0 ih0 = (IH0) this.b.get(rh0);
        if (ih0 == null) {
            ih0 = new IH0(null);
            this.b.put(rh0, ih0);
        } else if (ih0.f8214a == f) {
            return;
        }
        ih0.f8214a = f;
        b(rh0);
    }

    public void l(SH0 sh0, int i) {
        JH0 jh0 = (JH0) this.b.get(sh0);
        if (jh0 == null) {
            jh0 = new JH0(null);
            this.b.put(sh0, jh0);
        } else if (jh0.f8282a == i) {
            return;
        }
        jh0.f8282a = i;
        b(sh0);
    }

    public void m(TH0 th0, Object obj) {
        LH0 lh0 = (LH0) this.b.get(th0);
        if (lh0 == null) {
            lh0 = new LH0(null);
            this.b.put(th0, lh0);
        } else if (!th0.b && Objects.equals(lh0.f8415a, obj)) {
            return;
        }
        lh0.f8415a = obj;
        b(th0);
    }

    public UH0(Collection collection) {
        this.b = c((KH0[]) collection.toArray(new KH0[collection.size()]));
    }

    public UH0(Map map, FH0 fh0) {
        this.b = map;
    }
}
