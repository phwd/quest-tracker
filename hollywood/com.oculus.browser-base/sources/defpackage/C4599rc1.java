package defpackage;

import android.content.SharedPreferences;
import android.util.Log;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ContextUtils;

/* renamed from: rc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4599rc1 implements AbstractC4371qE {
    public static final long[] F;
    public C4429qc1 G;
    public final M2 H;
    public final SharedPreferences I;

    /* renamed from: J  reason: collision with root package name */
    public List f11208J;
    public List K = new LinkedList();
    public C3836n61 L;
    public AbstractC0124Ca1 M;
    public C1322Vq0 N;
    public int O;
    public long P;
    public int Q = 30000;

    static {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        TimeUnit timeUnit2 = TimeUnit.HOURS;
        TimeUnit timeUnit3 = TimeUnit.DAYS;
        F = new long[]{timeUnit.toMillis(1), timeUnit.toMillis(30), timeUnit2.toMillis(1), timeUnit2.toMillis(2), timeUnit2.toMillis(12), timeUnit3.toMillis(1), timeUnit3.toMillis(2), timeUnit3.toMillis(7), timeUnit3.toMillis(10)};
    }

    public C4599rc1(AbstractC0124Ca1 ca1, M2 m2) {
        SharedPreferences sharedPreferences = ContextUtils.getApplicationContext().getSharedPreferences("TabsuggestionsPreferences", 0);
        this.M = ca1;
        LinkedList linkedList = new LinkedList();
        this.f11208J = linkedList;
        linkedList.add(new C3232jc1());
        this.f11208J.add(new C4939tc1());
        this.N = new C1322Vq0();
        this.G = new C4429qc1(this, ca1);
        this.H = m2;
        m2.a(this);
        this.I = sharedPreferences;
    }

    public static List f(List list) {
        LinkedList linkedList = new LinkedList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C1529Zb1 zb1 = (C1529Zb1) it.next();
            int i = zb1.b;
            if (i != 0) {
                if (i != 1) {
                    Log.e("TabSuggestDetailed", String.format("Unknown action: %d", Integer.valueOf(i)));
                } else if (zb1.f9352a.size() >= 3) {
                    linkedList.add(zb1);
                }
            } else if (!zb1.f9352a.isEmpty()) {
                linkedList.add(zb1);
            }
        }
        Collections.sort(linkedList, new C3916nc1());
        return linkedList;
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        C4429qc1 qc1 = this.G;
        ((AbstractC0246Ea1) qc1.c).c.h(qc1.b);
        qc1.f11151a.destroy();
        this.H.b(this);
    }
}
