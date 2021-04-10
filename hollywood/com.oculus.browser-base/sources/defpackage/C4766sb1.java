package defpackage;

import android.os.StrictMode;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: sb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4766sb1 extends AbstractC4936tb1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f11284a = new Object();
    public final AbstractC1526Za1 b;
    public final AbstractC0124Ca1 c;
    public final AbstractC3226ja1 d;
    public C1322Vq0 e = new C1322Vq0();
    public final Deque f = new ArrayDeque();
    public final Deque g = new ArrayDeque();
    public final Set h = new HashSet();
    public C4084ob1 i;
    public C3742mb1 j;
    public C3571lb1 k;
    public boolean l;
    public boolean m;
    public boolean n;
    public SparseIntArray o;
    public SparseIntArray p;
    public BS0 q;
    public AbstractC2032cb r;
    public List s;
    public Set t;
    public byte[] u;
    public boolean v;
    public AbstractC2032cb w;

    public C4766sb1(AbstractC1526Za1 za1, AbstractC0124Ca1 ca1, AbstractC3226ja1 ja1) {
        boolean z;
        AbstractC2387ef1 ef1;
        this.b = za1;
        this.c = ca1;
        this.d = ja1;
        C3070if1 if1 = C3070if1.f;
        this.q = PostTask.a(if1);
        this.s = new ArrayList();
        this.t = new HashSet();
        BS0 bs0 = this.q;
        C1169Td1 td1 = (C1169Td1) za1;
        Objects.requireNonNull(td1);
        Object obj = ThreadUtils.f10596a;
        PU0 pu0 = NU0.f8549a;
        boolean d2 = pu0.d("org.chromium.chrome.browser.tabmodel.TabPersistentStore.HAS_RUN_FILE_MIGRATION", false);
        boolean d3 = pu0.d("org.chromium.chrome.browser.tabmodel.TabPersistentStore.HAS_RUN_MULTI_INSTANCE_FILE_MIGRATION", false);
        if (!d2 || !d3) {
            synchronized (C1169Td1.f8971a) {
                if (C1169Td1.d == null) {
                    C1047Rd1 rd1 = new C1047Rd1(td1, d2, d3);
                    rd1.f();
                    bs0.b(rd1.e);
                    C1169Td1.d = rd1;
                }
            }
            z = true;
        } else {
            z = false;
        }
        Objects.requireNonNull(za1);
        if (!za1.c()) {
            if (z) {
                ef1 = this.q;
            } else {
                ef1 = ((AbstractC0624Ke1) PostTask.e.get(if1.m)).c(if1);
            }
            this.r = w(ef1, za1.b());
            int f2 = pu0.f("org.chromium.chrome.browser.tabmodel.TabPersistentStore.ACTIVE_TAB_ID", -1);
            if (f2 != -1) {
                C3059ib1 ib1 = new C3059ib1(this, f2);
                ib1.f();
                ef1.b(ib1.e);
                this.w = ib1;
            }
            C1169Td1 td12 = (C1169Td1) za1;
            if (!i()) {
                boolean[] zArr = {false, true};
                for (int i2 = 0; i2 < 2; i2++) {
                    boolean z2 = zArr[i2];
                    int count = ((AbstractC0246Ea1) this.c).l(z2).getCount();
                    for (int i3 = 0; i3 < count; i3++) {
                        C5383wB q2 = C5383wB.q(((AbstractC0246Ea1) this.c).l(z2).getTabAt(i3));
                        q2.H.b(q2.G.getId(), q2.I);
                    }
                }
            }
        }
    }

    public static void c(C4766sb1 sb1, C4596rb1 rb1, C0797Nb1 nb1, byte[] bArr) {
        boolean j2 = sb1.j(rb1, nb1, bArr);
        if (j2) {
            AbstractC1220Ua0.d("tabmodel", "Finishing tab restore, isIncognito: " + j2 + " cancelIncognito: " + sb1.n, new Object[0]);
        }
        if (!((j2 && sb1.n) || (!j2 && sb1.m))) {
            sb1.p(rb1, nb1, bArr, false);
        }
        sb1.k();
    }

    public static boolean i() {
        return CachedFeatureFlags.isEnabled("CriticalPersistedTabData");
    }

    public static void l(String str, long j2) {
        if (C2474f80.f9900a.f()) {
            AbstractC3364kK0.k(AbstractC2531fV.f("Android.StrictMode.TabPersistentStore.", str), SystemClock.uptimeMillis() - j2);
        }
    }

    public static byte[] m(C4596rb1 rb1) {
        if (!i()) {
            return null;
        }
        Boolean bool = rb1.d;
        if (bool == null) {
            bool = TP.f(rb1.f11206a);
        }
        if (bool == null) {
            return null;
        }
        int i2 = rb1.f11206a;
        boolean booleanValue = bool.booleanValue();
        int i3 = C5383wB.N;
        EnumC3169jC0 a2 = EnumC3169jC0.a(C5383wB.class, booleanValue);
        return a2.b().c(i2, a2.R);
    }

    public static int o(DataInputStream dataInputStream, AbstractC3400kb1 kb1, SparseBooleanArray sparseBooleanArray, boolean z) {
        boolean z2;
        boolean z3;
        int i2;
        String str;
        Boolean valueOf;
        int i3;
        long uptimeMillis = SystemClock.uptimeMillis();
        int readInt = dataInputStream.readInt();
        if (readInt == 5) {
            z3 = false;
            z2 = false;
        } else if (readInt < 3) {
            return 0;
        } else {
            z2 = readInt < 5;
            z3 = readInt < 4;
        }
        int readInt2 = dataInputStream.readInt();
        if (z2) {
            i2 = -1;
        } else {
            i2 = dataInputStream.readInt();
        }
        AbstractC1220Ua0.d("tabmodel", "Tab metadata, skipIncognitoCount? " + z2 + " incognitoCount: " + i2 + " totalCount: " + readInt2, new Object[0]);
        int readInt3 = dataInputStream.readInt();
        int readInt4 = dataInputStream.readInt();
        if (readInt2 < 0 || readInt3 >= readInt2 || readInt4 >= readInt2) {
            throw new IOException();
        }
        int i4 = 0;
        int i5 = 0;
        while (i5 < readInt2) {
            int readInt5 = dataInputStream.readInt();
            if (z3) {
                str = "";
            } else {
                str = dataInputStream.readUTF();
            }
            if (readInt5 >= i4) {
                i4 = readInt5 + 1;
            }
            if (sparseBooleanArray != null) {
                sparseBooleanArray.append(readInt5, true);
            }
            if (i2 < 0) {
                valueOf = null;
            } else {
                valueOf = Boolean.valueOf(i5 < i2);
            }
            if (kb1 != null) {
                i3 = i5;
                kb1.a(i5, readInt5, str, valueOf, i5 == readInt4, i5 == readInt3);
            } else {
                i3 = i5;
            }
            i5 = i3 + 1;
            i4 = i4;
        }
        if (z) {
            l("ReadMergedStateTime", uptimeMillis);
        }
        l("ReadSavedStateTime", uptimeMillis);
        return i4;
    }

    @Override // defpackage.AbstractC4936tb1
    public File b() {
        Objects.requireNonNull((C1169Td1) this.b);
        return AbstractC1102Sb1.a();
    }

    public final void d(Tab tab) {
        if (tab != null && !this.f.contains(tab) && ((TabImpl) tab).U) {
            String s2 = tab.s();
            if (!(s2 != null && s2.startsWith("content"))) {
                if (!AbstractC5154ur1.g(tab.s()) || tab.h() || tab.k()) {
                    this.f.addLast(tab);
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void e() {
        Throwable th;
        int i2 = 0;
        if (!NU0.f8549a.d("org.chromium.chrome.browser.tabmodel.TabPersistentStore.HAS_COMPUTED_MAX_ID", false)) {
            StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
            try {
                Object obj = AbstractC1102Sb1.f8901a;
                File[] listFiles = AbstractC1041Rb1.f8842a.listFiles();
                if (listFiles != null) {
                    int i3 = 0;
                    for (File file : listFiles) {
                        if (file.isDirectory()) {
                            File[] listFiles2 = file.listFiles();
                            if (listFiles2 != null) {
                                for (File file2 : listFiles2) {
                                    Pair c2 = AbstractC1224Ub1.c(file2.getName());
                                    if (c2 != null) {
                                        i3 = Math.max(i3, ((Integer) c2.first).intValue());
                                    } else if (file2.getName().startsWith("tab_state")) {
                                        DataInputStream dataInputStream = null;
                                        try {
                                            DataInputStream dataInputStream2 = new DataInputStream(new BufferedInputStream(new FileInputStream(file2)));
                                            try {
                                                i3 = Math.max(i3, o(dataInputStream2, null, null, false));
                                                O21.a(dataInputStream2);
                                            } catch (Throwable th2) {
                                                th = th2;
                                                dataInputStream = dataInputStream2;
                                                O21.a(dataInputStream);
                                                throw th;
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            O21.a(dataInputStream);
                                            throw th;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i2 = i3;
                }
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                C4184p81.a().b(i2);
                NU0.f8549a.m("org.chromium.chrome.browser.tabmodel.TabPersistentStore.HAS_COMPUTED_MAX_ID", true);
            } catch (Throwable th4) {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                throw th4;
            }
        }
    }

    public final void f(String str, boolean z) {
        if (z) {
            new C2717gb1(this, str).e(this.q);
        } else {
            PostTask.c(C3070if1.b, new RunnableC1863bb1(this, str));
        }
    }

    public final void g(String str) {
        Object obj = ThreadUtils.f10596a;
        File file = new File(b(), str);
        if (file.exists()) {
            if (!file.delete()) {
                AbstractC1220Ua0.a("tabmodel", AbstractC2531fV.e("Failed to delete file: ", file), new Object[0]);
            }
            if (this.t.remove(str) && this.t.isEmpty()) {
                Objects.requireNonNull((C1169Td1) this.b);
                C1169Td1.c.set(false);
            }
        }
    }

    public final C4596rb1 h(int i2) {
        for (C4596rb1 rb1 : this.g) {
            if (rb1.f11206a == i2) {
                return rb1;
            }
        }
        return null;
    }

    public final boolean j(C4596rb1 rb1, C0797Nb1 nb1, byte[] bArr) {
        if (nb1 != null) {
            StringBuilder i2 = AbstractC2531fV.i("#isIncognitoTabBeingRestored from tabState:  ");
            i2.append(nb1.h);
            AbstractC1220Ua0.d("tabmodel", i2.toString(), new Object[0]);
            return nb1.h;
        } else if (rb1.d != null) {
            StringBuilder i3 = AbstractC2531fV.i("#isIncognitoTabBeingRestored from tabDetails:  ");
            i3.append(rb1.d);
            AbstractC1220Ua0.d("tabmodel", i3.toString(), new Object[0]);
            return rb1.d.booleanValue();
        } else if (bArr != null) {
            return TP.f(rb1.f11206a).booleanValue();
        } else {
            AbstractC1220Ua0.d("tabmodel", "#isIncognitoTabBeingRestored defaulting to false", new Object[0]);
            return false;
        }
    }

    public final void k() {
        if (!this.l) {
            if (this.g.isEmpty()) {
                this.o = null;
                this.p = null;
                this.v = false;
                if (this.b.c()) {
                    PostTask.b(Zo1.f9374a, new RunnableC2375eb1(this), 0);
                    Iterator it = new HashSet(this.t).iterator();
                    while (it.hasNext()) {
                        f((String) it.next(), true);
                    }
                    Iterator it2 = this.e.iterator();
                    while (true) {
                        C1261Uq0 uq0 = (C1261Uq0) it2;
                        if (!uq0.hasNext()) {
                            break;
                        }
                        Objects.requireNonNull((C0307Fa1) uq0.next());
                    }
                }
                AbstractC1526Za1 za1 = this.b;
                C2546fb1 fb1 = new C2546fb1(this);
                C1169Td1 td1 = (C1169Td1) za1;
                Objects.requireNonNull(td1);
                synchronized (C1169Td1.b) {
                    AbstractC2032cb cbVar = C1169Td1.e;
                    if (cbVar != null) {
                        cbVar.b(true);
                    }
                    C1108Sd1 sd1 = new C1108Sd1(td1, fb1);
                    C1169Td1.e = sd1;
                    Executor executor = AbstractC2032cb.f9616a;
                    sd1.f();
                    ((ExecutorC1463Ya) executor).execute(sd1.e);
                }
                n();
                this.i = null;
                StringBuilder i2 = AbstractC2531fV.i("Loaded tab lists; counts: ");
                i2.append(((AbstractC0246Ea1) this.c).l(false).getCount());
                i2.append(",");
                i2.append(((AbstractC0246Ea1) this.c).l(true).getCount());
                AbstractC1220Ua0.d("tabmodel", i2.toString(), new Object[0]);
                return;
            }
            C4084ob1 ob1 = new C4084ob1(this, (C4596rb1) this.g.removeFirst());
            this.i = ob1;
            Objects.requireNonNull(ob1);
            if (i()) {
                C4596rb1 rb1 = ob1.f10561a;
                Boolean bool = rb1.d;
                if (bool == null) {
                    bool = TP.f(rb1.f11206a);
                }
                if (bool == null) {
                    ob1.b();
                    return;
                }
                TraceEvent.l0("LoadCriticalPersistedTabData", (long) ob1.f10561a.f11206a);
                long elapsedRealtime = SystemClock.elapsedRealtime();
                int i3 = ob1.f10561a.f11206a;
                boolean booleanValue = bool.booleanValue();
                Callback b2 = ob1.c.b(new C3913nb1(ob1, elapsedRealtime));
                int i4 = C5383wB.N;
                EnumC3169jC0 a2 = EnumC3169jC0.a(C5383wB.class, booleanValue);
                a2.b().a(i3, a2.R, b2);
                return;
            }
            ob1.b();
        }
    }

    public final void n() {
        Iterator it = this.e.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                PostTask.c(Zo1.f9374a, new RunnableC1683ab1((C0307Fa1) uq0.next()));
            } else {
                return;
            }
        }
    }

    public void p(C4596rb1 rb1, C0797Nb1 nb1, byte[] bArr, boolean z) {
        int i2;
        int i3;
        boolean r2;
        int i4;
        boolean j2 = j(rb1, nb1, bArr);
        if (nb1 == null && bArr == null) {
            if (rb1.d == null) {
                AbstractC1220Ua0.f("tabmodel", "Failed to restore tab: not enough info about its type was available.", new Object[0]);
                return;
            } else if (j2) {
                boolean g2 = AbstractC5154ur1.g(rb1.c);
                if (!(g2 && rb1.e.booleanValue()) && (!g2 || !z || this.n)) {
                    AbstractC1220Ua0.d("tabmodel", "Failed to restore Incognito tab: its TabState could not be restored.", new Object[0]);
                    return;
                }
            }
        }
        TabModel l2 = ((AbstractC0246Ea1) this.c).l(j2);
        if (l2.a() == j2) {
            SparseIntArray sparseIntArray = j2 ? this.p : this.o;
            if (rb1.e.booleanValue()) {
                i4 = ((AbstractC0246Ea1) this.c).l(j2).getCount();
            } else if (sparseIntArray.size() <= 0 || rb1.b <= sparseIntArray.keyAt(sparseIntArray.size() - 1)) {
                for (int i5 = 0; i5 < sparseIntArray.size(); i5++) {
                    if (sparseIntArray.keyAt(i5) > rb1.b) {
                        Tab d2 = AbstractC1160Ta1.d(l2, sparseIntArray.valueAt(i5));
                        i4 = d2 != null ? l2.i(d2) : -1;
                    }
                }
                i2 = 0;
                i3 = rb1.f11206a;
                if (nb1 == null || bArr != null) {
                    this.d.S(j2).a(nb1, bArr, rb1.f11206a, j2, i2);
                } else if (!AbstractC5154ur1.g(rb1.c) || z || rb1.e.booleanValue()) {
                    AbstractC1220Ua0.f("tabmodel", "Failed to restore TabState; creating Tab with last known URL.", new Object[0]);
                    Tab b2 = this.d.S(j2).b(new LoadUrlParams(rb1.c, 0), 3, null);
                    if (b2 != null) {
                        i3 = b2.getId();
                        l2.m(i3, i2);
                    } else {
                        return;
                    }
                } else {
                    AbstractC1220Ua0.d("tabmodel", "Skipping restore of non-selected NTP.", new Object[0]);
                    return;
                }
                if (z || (rb1.e.booleanValue() && i2 == 0)) {
                    r2 = ((AbstractC0246Ea1) this.c).r();
                    int count = ((AbstractC0246Ea1) this.c).i().getCount();
                    l2.x(AbstractC1160Ta1.e(l2, i3), 3);
                    boolean r3 = ((AbstractC0246Ea1) this.c).r();
                    if (!(!rb1.e.booleanValue() || r2 == r3 || count == 0)) {
                        this.c.e(r2);
                    }
                }
                sparseIntArray.put(rb1.b, i3);
                return;
            } else {
                i4 = sparseIntArray.size();
            }
            i2 = i4;
            i3 = rb1.f11206a;
            if (nb1 == null) {
            }
            this.d.S(j2).a(nb1, bArr, rb1.f11206a, j2, i2);
            r2 = ((AbstractC0246Ea1) this.c).r();
            int count2 = ((AbstractC0246Ea1) this.c).i().getCount();
            l2.x(AbstractC1160Ta1.e(l2, i3), 3);
            boolean r32 = ((AbstractC0246Ea1) this.c).r();
            this.c.e(r2);
            sparseIntArray.put(rb1.b, i3);
            return;
        }
        throw new IllegalStateException("Incognito state mismatch. Restored tab state: " + j2 + ". Model: " + l2.a());
    }

    public final void q(C4596rb1 rb1, boolean z) {
        C0797Nb1 nb1;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            if (NU0.f8549a.f("org.chromium.chrome.browser.tabmodel.TabPersistentStore.ACTIVE_TAB_ID", -1) != rb1.f11206a || this.w == null) {
                nb1 = AbstractC1224Ub1.e(b(), rb1.f11206a);
            } else {
                long uptimeMillis2 = SystemClock.uptimeMillis();
                nb1 = (C0797Nb1) this.w.g();
                l("RestoreTabPrefetchTime", uptimeMillis2);
            }
            l("RestoreTabTime", uptimeMillis);
            p(rb1, nb1, m(rb1), z);
        } catch (Exception e2) {
            AbstractC1220Ua0.d("tabmodel", "loadTabs exception: " + e2.toString(), e2);
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
    }

    public final void r(String str, int i2) {
        C4596rb1 rb1;
        C4084ob1 ob1 = this.i;
        C4596rb1 rb12 = null;
        if (ob1 == null || (!(str == null && ob1.f10561a.f11206a == i2) && (str == null || !TextUtils.equals(ob1.f10561a.c, str)))) {
            rb1 = null;
        } else {
            this.i.a(false);
            rb1 = this.i.f10561a;
            k();
        }
        if (rb1 == null) {
            if (str == null) {
                rb1 = h(i2);
            } else {
                Iterator it = this.g.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    C4596rb1 rb13 = (C4596rb1) it.next();
                    if (TextUtils.equals(rb13.c, str)) {
                        rb12 = rb13;
                        break;
                    }
                }
                rb1 = rb12;
            }
        }
        if (rb1 != null) {
            this.g.remove(rb1);
            q(rb1, false);
        }
    }

    public final void s(byte[] bArr) {
        if (!Arrays.equals(this.u, bArr)) {
            File b2 = b();
            String b3 = this.b.b();
            synchronized (f11284a) {
                File file = new File(b2, b3);
                C2203db dbVar = new C2203db(file);
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = dbVar.d();
                    fileOutputStream.write(bArr, 0, bArr.length);
                    dbVar.b(fileOutputStream);
                } catch (IOException unused) {
                    if (fileOutputStream != null) {
                        dbVar.a(fileOutputStream);
                    }
                    AbstractC1220Ua0.a("tabmodel", "Failed to write file: " + file.getAbsolutePath(), new Object[0]);
                }
            }
            this.u = bArr;
            if (C2474f80.f9900a.f()) {
                AbstractC3364kK0.d("Android.TabPersistentStore.MetadataFileSize", bArr.length);
            }
        }
    }

    public void t() {
        if (this.j == null) {
            if (!this.f.isEmpty()) {
                C3742mb1 mb1 = new C3742mb1(this, (Tab) this.f.removeFirst());
                this.j = mb1;
                mb1.e(this.q);
                return;
            }
            u();
        }
    }

    public void u() {
        C3571lb1 lb1 = this.k;
        if (lb1 != null) {
            lb1.b(true);
        }
        C3571lb1 lb12 = new C3571lb1(this, null);
        this.k = lb12;
        lb12.e(this.q);
    }

    public final C4426qb1 v() {
        ArrayList arrayList = new ArrayList();
        C4084ob1 ob1 = this.i;
        if (ob1 != null) {
            arrayList.add(ob1.f10561a);
        }
        for (C4596rb1 rb1 : this.g) {
            arrayList.add(rb1);
        }
        AbstractC0124Ca1 ca1 = this.c;
        Object obj = ThreadUtils.f10596a;
        AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) ca1;
        TabModel l2 = ea1.l(true);
        C4255pb1 pb1 = new C4255pb1(l2.index());
        for (int i2 = 0; i2 < l2.getCount(); i2++) {
            pb1.b.add(Integer.valueOf(l2.getTabAt(i2).getId()));
            pb1.c.add(l2.getTabAt(i2).s());
        }
        TabModel l3 = ea1.l(false);
        C4255pb1 pb12 = new C4255pb1(l3.index());
        for (int i3 = 0; i3 < l3.getCount(); i3++) {
            pb12.b.add(Integer.valueOf(l3.getTabAt(i3).getId()));
            pb12.c.add(l3.getTabAt(i3).s());
        }
        int index = l3.index();
        int i4 = -1;
        if (index != -1) {
            i4 = l3.getTabAt(index).getId();
        }
        NU0.f8549a.n("org.chromium.chrome.browser.tabmodel.TabPersistentStore.ACTIVE_TAB_ID", i4);
        AbstractC1220Ua0.d("tabmodel", "Appending tabs being restored to metadata lists, " + arrayList.size() + ", startingNormalCount: " + pb12.b.size() + ", startingIncognitoCount: " + pb1.b.size(), new Object[0]);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C4596rb1 rb12 = (C4596rb1) it.next();
            Boolean bool = rb12.d;
            if (bool == null || bool.booleanValue()) {
                pb1.b.add(Integer.valueOf(rb12.f11206a));
                pb1.c.add(rb12.c);
            } else {
                pb12.b.add(Integer.valueOf(rb12.f11206a));
                pb12.c.add(rb12.c);
            }
        }
        int size = pb12.b.size();
        int size2 = pb1.b.size();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeInt(5);
        dataOutputStream.writeInt(size2 + size);
        dataOutputStream.writeInt(size2);
        dataOutputStream.writeInt(pb1.f11075a);
        dataOutputStream.writeInt(pb12.f11075a + size2);
        AbstractC1220Ua0.d("tabmodel", "Serializing tab lists; counts: " + size + ", " + size2, new Object[0]);
        for (int i5 = 0; i5 < size2; i5++) {
            dataOutputStream.writeInt(((Integer) pb1.b.get(i5)).intValue());
            dataOutputStream.writeUTF((String) pb1.c.get(i5));
        }
        for (int i6 = 0; i6 < size; i6++) {
            dataOutputStream.writeInt(((Integer) pb12.b.get(i6)).intValue());
            dataOutputStream.writeUTF((String) pb12.c.get(i6));
        }
        dataOutputStream.close();
        return new C4426qb1(byteArrayOutputStream.toByteArray(), pb12, pb1);
    }

    public final AbstractC2032cb w(AbstractC2387ef1 ef1, String str) {
        C2888hb1 hb1 = new C2888hb1(this, str);
        hb1.f();
        ef1.b(hb1.e);
        return hb1;
    }
}
