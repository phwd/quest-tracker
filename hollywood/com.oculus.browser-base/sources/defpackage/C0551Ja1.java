package defpackage;

import android.os.Handler;
import android.os.StrictMode;
import android.os.SystemClock;
import android.util.Pair;
import android.util.SparseIntArray;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Ja1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0551Ja1 extends AbstractC0246Ea1 implements AbstractC3397ka1 {
    public final AtomicBoolean l = new AtomicBoolean(true);
    public final C4766sb1 m;
    public boolean n;
    public boolean o;
    public final AbstractC5953za1 p;
    public final C1280Va q;
    public AbstractC3780mo0 r;
    public TabContentManager s;
    public Tab t;
    public AbstractC0063Ba1 u;
    public final Q31 v = null;

    public C0551Ja1(Q31 q31, AbstractC3226ja1 ja1, AbstractC1526Za1 za1, AbstractC3739ma1 ma1, AbstractC3780mo0 mo0, C1280Va va, boolean z, boolean z2, boolean z3) {
        super(ja1, ma1, z3);
        C0307Fa1 fa1 = new C0307Fa1(this);
        this.n = z;
        this.o = z2;
        C4766sb1 sb1 = new C4766sb1(za1, this, ja1);
        this.m = sb1;
        sb1.e.b(fa1);
        this.p = new C0002Aa1(this);
        this.r = mo0;
        this.q = va;
    }

    @Override // defpackage.AbstractC0124Ca1, defpackage.AbstractC0246Ea1
    public void d() {
        for (int i = 0; i < this.f7969a.size(); i++) {
            ((TabModel) this.f7969a.get(i)).d();
        }
    }

    @Override // defpackage.AbstractC0124Ca1, defpackage.AbstractC0246Ea1
    public void destroy() {
        C4766sb1 sb1 = this.m;
        sb1.l = true;
        C1169Td1 td1 = (C1169Td1) sb1.b;
        td1.h = null;
        td1.i = true;
        C4084ob1 ob1 = sb1.i;
        if (ob1 != null) {
            ob1.a(true);
        }
        sb1.f.clear();
        sb1.g.clear();
        C3742mb1 mb1 = sb1.j;
        if (mb1 != null) {
            mb1.b(false);
        }
        C3571lb1 lb1 = sb1.k;
        if (lb1 != null) {
            lb1.b(true);
        }
        super.destroy();
    }

    @Override // defpackage.AbstractC0124Ca1, defpackage.AbstractC0246Ea1
    public void e(boolean z) {
        TabModel i = i();
        super.e(z);
        TabModel i2 = i();
        if (i != i2) {
            i2.x(i2.index(), 3);
            new Handler().post(new RunnableC0490Ia1(this));
        }
    }

    @Override // defpackage.AbstractC0124Ca1, defpackage.AbstractC0246Ea1
    public void f(AbstractC0063Ba1 ba1) {
        this.u = ba1;
    }

    @Override // defpackage.AbstractC0246Ea1
    public void s() {
        super.s();
    }

    public boolean t() {
        return this.l.get();
    }

    public void u(boolean z) {
        C4766sb1 sb1 = this.m;
        Objects.requireNonNull(sb1);
        long uptimeMillis = SystemClock.uptimeMillis();
        sb1.b.a();
        Objects.requireNonNull((C1169Td1) sb1.b);
        AbstractC2032cb cbVar = C1169Td1.d;
        if (cbVar != null) {
            try {
                cbVar.g();
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        C4766sb1.l("LoadStateTime", uptimeMillis);
        AbstractC1220Ua0.d("tabmodel", "#loadState, ignoreIncognitoFiles? " + z, new Object[0]);
        sb1.m = false;
        sb1.n = z;
        sb1.o = new SparseIntArray();
        sb1.p = new SparseIntArray();
        try {
            long uptimeMillis2 = SystemClock.uptimeMillis();
            sb1.e();
            if (sb1.r != null) {
                long uptimeMillis3 = SystemClock.uptimeMillis();
                DataInputStream dataInputStream = (DataInputStream) sb1.r.g();
                if (dataInputStream != null) {
                    C4766sb1.l("LoadStateInternalPrefetchTime", uptimeMillis3);
                    sb1.v = true;
                    C4766sb1.o(dataInputStream, new C2205db1(sb1, false, ((AbstractC0246Ea1) sb1.c).r()), null, false);
                    C4766sb1.l("LoadStateInternalTime", uptimeMillis2);
                }
            }
            if (sb1.s.size() > 0) {
                for (Pair pair : sb1.s) {
                    long uptimeMillis4 = SystemClock.uptimeMillis();
                    DataInputStream dataInputStream2 = (DataInputStream) ((AbstractC2032cb) pair.first).g();
                    if (dataInputStream2 != null) {
                        C4766sb1.l("MergeStateInternalFetchTime", uptimeMillis4);
                        sb1.t.add((String) pair.second);
                        Objects.requireNonNull((C1169Td1) sb1.b);
                        C1169Td1.c.set(true);
                        C4766sb1.o(dataInputStream2, new C2205db1(sb1, sb1.g.size() != 0, ((AbstractC0246Ea1) sb1.c).r()), null, true);
                        C4766sb1.l("MergeStateInternalTime", uptimeMillis4);
                    }
                }
                if (!sb1.t.isEmpty()) {
                    AbstractC3535lK0.a("Android.MergeState.ColdStart");
                }
                sb1.s.clear();
            }
        } catch (Exception e) {
            StringBuilder i = AbstractC2531fV.i("loadState exception: ");
            i.append(e.toString());
            AbstractC1220Ua0.d("tabmodel", i.toString(), e);
        }
        AbstractC1526Za1 za1 = sb1.b;
        int size = sb1.g.size();
        Objects.requireNonNull((C1169Td1) za1);
        AbstractC3364kK0.d("Tabs.CountAtStartup", size);
        Iterator it = sb1.e.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                sb1.g.size();
                Objects.requireNonNull((C0307Fa1) uq0.next());
            } else {
                return;
            }
        }
    }

    public void v(TabContentManager tabContentManager) {
        C3623lt ltVar = (C3623lt) this.k.z(false, false);
        C3623lt ltVar2 = (C3623lt) this.k.z(true, false);
        C4423qa1 qa1 = new C4423qa1(Profile.b(), this.o, ltVar, ltVar2, this.p, this.s, this.m, this.r, this.q, this, this.n);
        AbstractC5953za1 za1 = this.p;
        ltVar.f = qa1;
        ltVar.g = za1;
        N00 n00 = new N00(new O00(this.v, ltVar, ltVar2, za1, this.s, this.m, this.r, this.q, this));
        AbstractC5953za1 za12 = this.p;
        ltVar2.f = n00;
        ltVar2.g = za12;
        this.s = tabContentManager;
        q(qa1, n00);
        ((C1169Td1) this.m.b).h = this.s;
        c(new C0368Ga1(this));
        new C0429Ha1(this, this);
    }

    public void w(Tab tab, int i) {
        Tab tab2;
        boolean z = tab != null && tab.F() == 1;
        if (!(this.t == tab || tab == null || tab.isNativePage())) {
            AbstractC5109uc1.f11423a = SystemClock.uptimeMillis();
            AbstractC5109uc1.b = i;
            AbstractC5109uc1.c = false;
            AbstractC5109uc1.d = false;
        }
        Tab tab3 = this.t;
        if (!(tab3 == null || tab3 == tab || tab3.f())) {
            boolean z2 = (this.t.l() == null || this.t.l().I() == null) ? false : true;
            if (this.t.isInitialized() && z2) {
                if (!this.t.x() && ((!z || i != 2) && (tab2 = this.t) != null)) {
                    this.s.b(tab2);
                }
                this.t.N(0);
                C4766sb1 sb1 = this.m;
                sb1.d(this.t);
                sb1.t();
            }
            this.t = null;
        }
        if (tab == null) {
            super.s();
            return;
        }
        Tab tab4 = this.t;
        if (tab4 != tab || tab4.isHidden()) {
            this.t = tab;
            if (i != 1) {
                tab.y(i);
                tab.getId();
                tab.J();
                return;
            }
            return;
        }
        tab.z();
    }

    public void x(boolean z) {
        C4766sb1 sb1 = this.m;
        if (z) {
            while (!sb1.g.isEmpty() && sb1.o.size() == 0 && sb1.p.size() == 0) {
                TraceEvent j0 = TraceEvent.j0("LoadFirstTabState");
                try {
                    sb1.q((C4596rb1) sb1.g.removeFirst(), true);
                    if (j0 != null) {
                        j0.close();
                    }
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            }
        }
        sb1.k();
        return;
        throw th;
    }

    public void y() {
        d();
        C4766sb1 sb1 = this.m;
        Objects.requireNonNull(sb1);
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            C3571lb1 lb1 = sb1.k;
            if (lb1 != null) {
                lb1.b(true);
            }
            try {
                sb1.s(sb1.v().f11150a);
            } catch (IOException e) {
                AbstractC1220Ua0.f("tabmodel", "Error while saving tabs state; will attempt to continue...", e);
            }
            C4766sb1.l("SaveListTime", uptimeMillis);
            sb1.d(AbstractC1160Ta1.c(((AbstractC0246Ea1) sb1.c).l(false)));
            sb1.d(AbstractC1160Ta1.c(((AbstractC0246Ea1) sb1.c).l(true)));
            C3742mb1 mb1 = sb1.j;
            if (mb1 != null) {
                if (mb1.b(false)) {
                    C3742mb1 mb12 = sb1.j;
                    if (!mb12.m) {
                        sb1.d(mb12.i);
                    }
                }
                sb1.j = null;
            }
            long uptimeMillis2 = SystemClock.uptimeMillis();
            for (Tab tab : sb1.f) {
                int id = tab.getId();
                boolean a2 = tab.a();
                try {
                    C0797Nb1 a3 = AbstractC1163Tb1.a(tab);
                    if (a3 != null) {
                        AbstractC1224Ub1.f(AbstractC1224Ub1.a(sb1.b(), id, a2), a3, a2);
                    }
                } catch (OutOfMemoryError unused) {
                    AbstractC1220Ua0.a("tabmodel", "Out of memory error while attempting to save tab state.  Erasing.", new Object[0]);
                    sb1.a(id, a2);
                }
            }
            sb1.f.clear();
            C4766sb1.l("SaveTabsTime", uptimeMillis2);
            C4766sb1.l("SaveStateTime", uptimeMillis);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        }
    }
}
