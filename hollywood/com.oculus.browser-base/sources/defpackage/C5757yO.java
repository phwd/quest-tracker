package defpackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: yO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5757yO extends J80 {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList f11677a = new ArrayList();
    public ArrayList b = new ArrayList();
    public final Map c;
    public int d;

    public C5757yO(AbstractC1600a41 a41, AbstractC4567rO rOVar) {
        HashMap hashMap = new HashMap();
        this.c = hashMap;
        hashMap.put("GeneralActions", a41);
        hashMap.put("FeedActions", rOVar);
    }

    @Override // defpackage.J80
    public int a() {
        return this.f11677a.size();
    }

    public int b(String str) {
        for (int i = 0; i < this.f11677a.size(); i++) {
            if (((AbstractC5417wO) this.f11677a.get(i)).f11544a.equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public AbstractC5417wO c(int i) {
        return (AbstractC5417wO) this.f11677a.get(i);
    }

    public void d(int i, int i2) {
        this.f11677a.subList(i, i + i2).clear();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((C0461Hm0) it.next()).F.f(i, i2);
        }
    }
}
