package defpackage;

import com.google.android.gms.cast.MediaQueueItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* renamed from: Je0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0562Je0 extends GL0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0684Le0 f8302a;

    public C0562Je0(C0684Le0 le0) {
        this.f8302a = le0;
    }

    @Override // defpackage.GL0
    public final void b() {
        long e = C0684Le0.e(this.f8302a.c);
        C0684Le0 le0 = this.f8302a;
        if (e != le0.b) {
            le0.b = e;
            le0.a();
            C0684Le0 le02 = this.f8302a;
            if (le02.b != 0) {
                le02.b();
            }
        }
    }

    @Override // defpackage.GL0
    public final void c(int[] iArr) {
        List c = GF1.c(iArr);
        if (!this.f8302a.e.equals(c)) {
            this.f8302a.i();
            this.f8302a.g.evictAll();
            this.f8302a.h.clear();
            C0684Le0 le0 = this.f8302a;
            le0.e = c;
            C0684Le0.f(le0);
            this.f8302a.k();
            this.f8302a.j();
        }
    }

    @Override // defpackage.GL0
    public final void d(int[] iArr, int i) {
        int i2;
        int length = iArr.length;
        if (i == 0) {
            i2 = this.f8302a.e.size();
        } else {
            i2 = this.f8302a.f.get(i, -1);
            if (i2 == -1) {
                this.f8302a.b();
                return;
            }
        }
        this.f8302a.i();
        this.f8302a.e.addAll(i2, GF1.c(iArr));
        C0684Le0.f(this.f8302a);
        Iterator it = this.f8302a.s.iterator();
        if (!it.hasNext()) {
            this.f8302a.j();
        } else {
            C5859z.a(it.next());
            throw null;
        }
    }

    @Override // defpackage.GL0
    public final void e(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            this.f8302a.g.remove(Integer.valueOf(i));
            int i2 = this.f8302a.f.get(i, -1);
            if (i2 == -1) {
                this.f8302a.b();
                return;
            } else {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        Collections.sort(arrayList);
        this.f8302a.i();
        C0684Le0.c(this.f8302a, GF1.b(arrayList));
        this.f8302a.j();
    }

    @Override // defpackage.GL0
    public final void f(MediaQueueItem[] mediaQueueItemArr) {
        HashSet hashSet = new HashSet();
        this.f8302a.h.clear();
        for (MediaQueueItem mediaQueueItem : mediaQueueItemArr) {
            int i = mediaQueueItem.G;
            this.f8302a.g.put(Integer.valueOf(i), mediaQueueItem);
            int i2 = this.f8302a.f.get(i, -1);
            if (i2 == -1) {
                this.f8302a.b();
                return;
            } else {
                hashSet.add(Integer.valueOf(i2));
            }
        }
        for (Integer num : this.f8302a.h) {
            int i3 = this.f8302a.f.get(num.intValue(), -1);
            if (i3 != -1) {
                hashSet.add(Integer.valueOf(i3));
            }
        }
        this.f8302a.h.clear();
        ArrayList arrayList = new ArrayList(hashSet);
        Collections.sort(arrayList);
        this.f8302a.i();
        C0684Le0.c(this.f8302a, GF1.b(arrayList));
        this.f8302a.j();
    }

    @Override // defpackage.GL0
    public final void g(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            this.f8302a.g.remove(Integer.valueOf(i));
            int i2 = this.f8302a.f.get(i, -1);
            if (i2 == -1) {
                this.f8302a.b();
                return;
            }
            this.f8302a.f.delete(i);
            arrayList.add(Integer.valueOf(i2));
        }
        if (!arrayList.isEmpty()) {
            Collections.sort(arrayList);
            this.f8302a.i();
            this.f8302a.e.removeAll(GF1.c(iArr));
            C0684Le0.f(this.f8302a);
            C0684Le0 le0 = this.f8302a;
            GF1.b(arrayList);
            Iterator it = le0.s.iterator();
            if (!it.hasNext()) {
                this.f8302a.j();
            } else {
                C5859z.a(it.next());
                throw null;
            }
        }
    }
}
