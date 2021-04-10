package defpackage;

import java.util.Iterator;
import java.util.List;

/* renamed from: Uo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1256Uo implements Iterator {
    public final int F;
    public int G;
    public final /* synthetic */ C1317Vo H;

    public C1256Uo(C1317Vo vo) {
        this.H = vo;
        int size = vo.I.size();
        this.F = size;
        this.G = size - 1;
    }

    public boolean hasNext() {
        return this.G >= 0;
    }

    @Override // java.util.Iterator
    public Object next() {
        List list = this.H.I;
        int i = this.G;
        this.G = i - 1;
        return ((C1134So) list.get(i)).f8918a;
    }
}
