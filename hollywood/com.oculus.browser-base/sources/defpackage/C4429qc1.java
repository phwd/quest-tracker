package defpackage;

import J.N;
import android.content.SharedPreferences;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: qc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4429qc1 {

    /* renamed from: a  reason: collision with root package name */
    public AbstractC1099Sa1 f11151a;
    public AbstractC5783ya1 b;
    public AbstractC0124Ca1 c;
    public final /* synthetic */ C4599rc1 d;

    public C4429qc1(C4599rc1 rc1, AbstractC0124Ca1 ca1) {
        this.d = rc1;
        this.f11151a = new C4860t61(this, ca1);
        C5030u61 u61 = new C5030u61(this);
        this.b = u61;
        ((AbstractC0246Ea1) ca1).c.a(u61);
        this.c = ca1;
    }

    public void a() {
        boolean z;
        synchronized (this.d.K) {
            C4599rc1 rc1 = this.d;
            if (rc1.L != null) {
                Iterator it = rc1.N.iterator();
                while (true) {
                    C1261Uq0 uq0 = (C1261Uq0) it;
                    if (!uq0.hasNext()) {
                        break;
                    }
                    ((C2891hc1) ((AbstractC3745mc1) uq0.next())).c();
                }
            }
        }
        C4599rc1 rc12 = this.d;
        synchronized (rc12.I) {
            long j = rc12.I.getLong("LastTimestamp", -1);
            if (j != -1) {
                long j2 = rc12.I.getLong("BackoffCountKey", -1);
                if (j2 > 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long j3 = j2 - (currentTimeMillis - j);
                    SharedPreferences.Editor edit = rc12.I.edit();
                    edit.putLong("LastTimestamp", currentTimeMillis);
                    edit.putLong("BackoffCountKey", j3);
                    edit.apply();
                    z = j3 > 0;
                }
            }
            z = false;
        }
        if (!z) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime >= rc12.P) {
                rc12.P = elapsedRealtime + ((long) N.M37SqSAy("CloseTabSuggestions", "min_time_between_prefetches", rc12.Q));
                AbstractC3568la1 d2 = ((AbstractC0246Ea1) rc12.M).c.d();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < d2.getCount(); i++) {
                    Tab tabAt = d2.getTabAt(i);
                    if (tabAt != null) {
                        List N = d2.N(tabAt.getId());
                        if (N.size() > 1) {
                            ArrayList arrayList3 = new ArrayList();
                            for (int i2 = 0; i2 < N.size(); i2++) {
                                Tab tab = (Tab) N.get(i2);
                                if (!tab.x()) {
                                    arrayList3.add(tab);
                                }
                            }
                            int i3 = C5383wB.q(tabAt).R;
                            ArrayList arrayList4 = new ArrayList();
                            Iterator it2 = arrayList3.iterator();
                            while (it2.hasNext()) {
                                arrayList4.add(C3665m61.a((Tab) it2.next()));
                            }
                            arrayList2.add(new C3494l61(i3, arrayList4));
                        } else if (!tabAt.x()) {
                            arrayList.add(C3665m61.a(tabAt));
                        }
                    }
                }
                C3836n61 n61 = new C3836n61(arrayList, arrayList2);
                synchronized (rc12.K) {
                    rc12.O = 0;
                    rc12.L = n61;
                    rc12.K = new LinkedList();
                    for (AbstractC3403kc1 kc1 : rc12.f11208J) {
                        if (kc1.isEnabled()) {
                            rc12.O++;
                            kc1.a(n61, new C4087oc1(rc12));
                        }
                    }
                }
            }
        }
    }
}
